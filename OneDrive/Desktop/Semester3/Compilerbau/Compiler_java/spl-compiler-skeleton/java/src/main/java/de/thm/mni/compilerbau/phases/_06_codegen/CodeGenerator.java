package de.thm.mni.compilerbau.phases._06_codegen;

import de.thm.mni.compilerbau.CommandLineOptions;
import de.thm.mni.compilerbau.absyn.*;
import de.thm.mni.compilerbau.table.*;
import de.thm.mni.compilerbau.types.ArrayType;
import de.thm.mni.compilerbau.types.PrimitiveType;
import de.thm.mni.compilerbau.types.Type;
import de.thm.mni.compilerbau.utils.NotImplemented;
import de.thm.mni.compilerbau.utils.SplError;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * This class is used to generate the assembly code for the compiled program.
 * This code is emitted via the {@link CodePrinter} in the output field of this class.
 */
public class CodeGenerator {
    final CommandLineOptions options;
    final CodePrinter output;
    public  SymbolTable globalTable; // Globale Symboltabelle hinzufügen

    /**
     * Initializes the code generator.
     *
     * @param options The command line options passed to the compiler
     * @param output  The PrintWriter to the output file.
     */
    public CodeGenerator(CommandLineOptions options, PrintWriter output) throws IOException {
        this.options = options;
        this.output = new CodePrinter(output);
    }

    public void generateCode(Program program, SymbolTable table) {
        assemblerProlog();
            //TODO (assignment 6): generate eco32 assembler code for the spl program
        this.globalTable = table; // Initialisieren


        for(GlobalDefinition definition : program.definitions){
            if (definition instanceof ProcedureDefinition){
                generateProcedure((ProcedureDefinition) definition,table);
            }
        }
      //  this.output.emitInstruction("add", new Register(8), new Register(8), new Register(9));

      //  throw new NotImplemented();
    }

    private void generateProcedure(ProcedureDefinition procedure,SymbolTable globaltable) {
        String procedureName = procedure.name.toString();
        output.emitExport(procedureName); // print the procedure name
        output.emitLabel(procedureName); // print the label of the procedure

        // Wir brauchen den frame size
        ProcedureEntry procedureEntry =(ProcedureEntry) globaltable.lookup(procedure.name);
        SymbolTable localeTabelle = procedureEntry.localTable ;   // Die lokale Tabelle muss man übergeben
        // Allokieren des Frames
        int frameSize = procedureEntry.stackLayout.frameSize(); // dann holen wir den framesize der Procedure
        output.emitInstruction("sub", Register.STACK_POINTER, Register.STACK_POINTER, frameSize,"  ; allocate frame");
        output.emitInstruction("stw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset(),"   ; save old frame pointer");
        output.emitInstruction("add", Register.FRAME_POINTER, Register.STACK_POINTER, frameSize,"   ; setup new frame pointer");
        output.emitInstruction("stw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset(),"   ; save return register");

        for (Statement statement : procedure.body) {
            generateCodeForStatement(statement,localeTabelle);  // Bekommt die globale Tabelle und generiert den Code dafür
        }

        // Wiederherstellung des Rückkehrregisters und des Frame-Pointers, dann Freigabe des Frames
        output.emitInstruction("ldw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset(),"   ; restore return register");
        output.emitInstruction("ldw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset(),"   ; restore old frame pointer");
        output.emitInstruction("add", Register.STACK_POINTER, Register.STACK_POINTER, frameSize,"   ; release frame");
        output.emitInstruction("jr", Register.RETURN_ADDRESS,"   ; return");
    }

    // Generierung für  generateStatement
    private void generateCodeForStatement(Statement statement, SymbolTable localTable) {
        if (statement instanceof CallStatement) {
         generateCodeForCallStatement((CallStatement) statement, localTable);
        }else if (statement instanceof AssignStatement assignment) {
            generateCodeForAssignment(assignment, localTable);
        } else if (statement instanceof IfStatement ifStatement) {
          generateCodeForIfStatement(ifStatement, localTable);
        } else if (statement instanceof WhileStatement whileStatement) {
            generateWhileStatement(whileStatement, localTable);
        } else if (statement instanceof CompoundStatement compoundStatement ) {
            generateCompoundStatement(compoundStatement, localTable);
        } else if (statement instanceof EmptyStatement) {
            //      generateCodeForEmptyStatement(whileStatement, localTable);
        }

    }
    // Generierung für  `WhileStatement`
    private void generateWhileStatement(WhileStatement whileStatement, SymbolTable localTable) {
        String labelStart = createNewLabel("L");
        String labelEnd = createNewLabel("L");

        // Étiquette de début de boucle
        output.emitLabel(labelStart);

        // Générer le code pour évaluer la condition
       generateBranch((BinaryExpression) whileStatement.condition, labelEnd,localTable);

        // Générer le code pour le corps de la boucle
        generateCodeForStatement(whileStatement.body, localTable);

        // Sauter au début de la boucle pour répéter
        output.emitInstruction("j", labelStart );

        // Étiquette de fin de boucle
        output.emitLabel(labelEnd);
    }

    // Méthode pour générer le code pour un `CompoundStatement`
    private void generateCompoundStatement(CompoundStatement compoundStatement, SymbolTable localTable) {
        for (Statement stmt : compoundStatement.statements) {
            generateCodeForStatement(stmt, localTable);
        }
    }
    private void generateCodeForIfStatement(IfStatement ifStatement, SymbolTable localTable) {

        // Créer des étiquettes uniques
        String labelElse = createNewLabel("L1");
        String labelEnd = createNewLabel("L2");

        // Générer le code pour évaluer la condition
        generateBranch((BinaryExpression) ifStatement.condition, labelElse,localTable);

        // Générer le code pour le bloc `then`
        generateCodeForStatement(ifStatement.thenPart, localTable);
        // Sauter à la fin après avoir exécuté le bloc `then`
        output.emitInstruction("j", labelEnd );

        // Générer le code pour le bloc `else`
        output.emitLabel(labelElse);
            generateCodeForStatement(ifStatement.elsePart, localTable);

        // Étiquette de fin de l'instruction `if`
        output.emitLabel(labelEnd);
    }
    // Méthode pour créer une nouvelle étiquette unique
    private String createNewLabel(String base) {
        return base  + labelCounter++;
    }
    // Variables de classe pour la gestion des étiquettes
    private int labelCounter = 0;

    private void generateCodeForCallStatement(CallStatement statement, SymbolTable localTable) {

        ProcedureEntry entry = (ProcedureEntry) localTable.lookup(statement.procedureName);

        // Charger les arguments
        for (int i = 0; i < statement.arguments.size(); i++) {
            Expression argument = statement.arguments.get(i);

            if(entry.parameterTypes.get(i).isReference){
                Register argRegister = generateVariable(((VariableExpression) argument).variable, Register.FIRST_FREE_USE, localTable);
            } else {
                Register argRegister = generateExpression(argument, Register.FIRST_FREE_USE, localTable);
            }

            // Stocker l'argument sur la pile
            output.emitInstruction("stw", Register.FIRST_FREE_USE, Register.STACK_POINTER, entry.parameterTypes.get(i).offset, "store argument");
        }

        // Wir rufen  die Funktion
        output.emitInstruction("jal", statement.procedureName.toString(), "call function");
    }

    private Register generateVariable(Variable variable, Register target, SymbolTable localTable) {
        switch (variable) {
            case ArrayAccess arrayAccess -> {
                var baseAddressRegister = generateVariable(arrayAccess.array, target, localTable);

                // Générer le code pour l'expression d'index actuelle
                var offsetRegister = generateExpression(arrayAccess.index, target.next(), localTable);

                // Vérification des limites de l'index
                ArrayType arrayType = (ArrayType) getType(arrayAccess.array, localTable);
                int upperBound = arrayType.arraySize;
                Register upperBoundRegister = target.next().next(); // $10 dans la référence
                output.emitInstruction("add", upperBoundRegister, Register.NULL, upperBound, "Load upper bound for dimension");

                // Vérifier si l'index est hors limites
                output.emitInstruction("bgeu", offsetRegister, upperBoundRegister, "_indexError", "Check if index is out of bounds");

                // Obtenir la taille des éléments
                int elementSize = arrayType.baseType.byteSize;

                // Multiplier l'index par la taille des éléments pour obtenir l'offset partiel
                output.emitInstruction("mul", offsetRegister, offsetRegister, elementSize, "Calculate partial offset for current dimension");

                // Ajouter l'offset partiel à l'offset total
                output.emitInstruction("add", target, baseAddressRegister, offsetRegister, "Add partial offset for current dimension");
            }
            case NamedVariable namedVariable -> {
                VariableEntry variableEntry = (VariableEntry) localTable.lookup(namedVariable.name);
                int offset = variableEntry.offset;

                output.emitInstruction("add", target, Register.FRAME_POINTER, offset, "Calculate address of variable " + namedVariable.name);

                if (variableEntry.isReference) {
                    output.emitInstruction("ldw", target, target, 0, "Load reference address for variable " + namedVariable.name);
                }
            }
        }

        return target;
    }

    private void generateCodeForAssignment(AssignStatement assignment, SymbolTable localTable) {

        Register targetRegister = generateVariable(assignment.target, Register.FIRST_FREE_USE, localTable);
        Register valueRegister = generateExpression(assignment.value, Register.FIRST_FREE_USE.next(), localTable);
        // Générer l'expression et charger la valeur dans valueRegister

        output.emitInstruction("stw", valueRegister, targetRegister, 0, "Store value into variable for assignstatement  ");

    }

    private Type getType(Variable variable, SymbolTable table) {
        if (variable instanceof NamedVariable namedVariable) {
            VariableEntry entry = (VariableEntry) table.lookup(namedVariable.name);
            return entry.type;
        } else if (variable instanceof ArrayAccess arrayAccess) {
            ArrayType arrayType = (ArrayType) getType(arrayAccess.array, table);
            return arrayType.baseType;
        }
        return null;
    }

    // Méthode pour générer du code pour une expression générale
    private Register generateExpression(Expression expression, Register targetRegister, SymbolTable localTable) {
        if (expression instanceof IntLiteral intLiteral) {
            output.emitInstruction("add", targetRegister, Register.NULL, intLiteral.value, "load  integer literal for expression");
        } else if (expression instanceof VariableExpression variableExpression) {
            generateVariableExpression(variableExpression, targetRegister, localTable);
        } else if (expression instanceof BinaryExpression binaryExpression) {
            generateBinaryExpression(binaryExpression, targetRegister, localTable);
        } else if (expression instanceof UnaryExpression unaryExpression) {
            generateUnaryExpression(unaryExpression, targetRegister, localTable);
        } else {
            throw new UnsupportedOperationException("Unsupported expression type");
        }

        return targetRegister;
    }
    private void generateVariableExpression(VariableExpression variableExpression, Register targetRegister, SymbolTable localTable) {
        generateVariable(variableExpression.variable, targetRegister, localTable);
        output.emitInstruction("ldw", targetRegister, targetRegister, 0);
    }


    // Methode zur Code-Generierung für eine BinaryExpression
    private void generateBinaryExpression(BinaryExpression binaryExpression, Register targetRegister, SymbolTable localTable) {
        // Generiere Code für den linken Operanden
        var left = generateExpression(binaryExpression.leftOperand, targetRegister, localTable);

        // Generiere Code für den rechten Operanden
        var right= generateExpression(binaryExpression.rightOperand, targetRegister.next(), localTable);

        // Generiere die binäre Instruktion
        switch (binaryExpression.operator) {
            case ADD:
                output.emitInstruction("add", targetRegister, left, right);
                break;
            case SUB:
                output.emitInstruction("sub", targetRegister, left, right);
                break;
            case MUL:
                output.emitInstruction("mul", targetRegister, left, right);
                break;
            case DIV:
                output.emitInstruction("div", targetRegister, left, right);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported binary operator");
        }
    }

    void generateBranch(BinaryExpression condition, String target, SymbolTable localTable) {
        // Code für den linken Operanden generieren
        Register leftRegister = generateExpression(condition.leftOperand, Register.FIRST_FREE_USE, localTable);

        // Code für den rechten Operanden generieren
        Register rightRegister = generateExpression(condition.rightOperand, Register.FIRST_FREE_USE.next(), localTable);

        // Den umgekehrten Operator erhalten
        BinaryExpression.Operator flippedOperator = condition.operator.flipComparison();

        // Bedingten Sprung basierend auf dem umgekehrten Operator generieren
        switch (flippedOperator) {
            case EQU:
                // Springe zum Ziel, wenn die Operanden gleich sind
                output.emitInstruction("beq", leftRegister, rightRegister, target);
            break;
            case NEQ:
                // Springe zum Ziel, wenn die Operanden ungleich sind
                output.emitInstruction("bne", leftRegister, rightRegister, target);
                break;
            case LST:
                // Springe zum Ziel, wenn der linke Operand kleiner als der rechte Operand ist
                output.emitInstruction("blt", leftRegister, rightRegister, target);
                break;
            case LSE:
                // Springe zum Ziel, wenn der linke Operand kleiner oder gleich dem rechten Operand ist
                output.emitInstruction("ble", leftRegister, rightRegister, target);
                break;
            case GRT:
                // Springe zum Ziel, wenn der linke Operand größer als der rechte Operand ist
                output.emitInstruction("bgt", leftRegister, rightRegister, target);
                break;
            case GRE:
                // Springe zum Ziel, wenn der linke Operand größer oder gleich dem rechten Operand ist
                output.emitInstruction("bge", leftRegister, rightRegister, target);
                break;
            default:
                throw new IllegalArgumentException("Nicht unterstützter Bedingungsoperator: " + condition.operator);
        }
    }


    // Methode zur Code-Generierung für eine UnaryExpression
    private void generateUnaryExpression(UnaryExpression unaryExpression, Register targetRegister, SymbolTable localTable) {
        // Generiere Code für den Operanden
        generateExpression(unaryExpression.operand, targetRegister, localTable);

        // Generiere die unäre Instruktion
        if (unaryExpression.operator == UnaryExpression.Operator.MINUS) {
            output.emitInstruction("sub", targetRegister, Register.NULL, targetRegister, "negate the expression");
        } else {
            throw new UnsupportedOperationException("Unsupported unary operator");
        }
    }



    /**
     * Emits needed import statements, to allow usage of the predefined functions and sets the correct settings
     * for the assembler.
     */
    private void assemblerProlog() {
        output.emitImport("printi");
        output.emitImport("printc");
        output.emitImport("readi");
        output.emitImport("readc");
        output.emitImport("exit");
        output.emitImport("time");
        output.emitImport("clearAll");
        output.emitImport("setPixel");
        output.emitImport("drawLine");
        output.emitImport("drawCircle");
        output.emitImport("_indexError");
        output.emit("");
        output.emit("\t.code");
        output.emit("\t.align\t4\n");
    }
}
/**
 *
 public void generateCode(Program program, SymbolTable table) {
 assemblerProlog();
 for (GlobalDefinition definition : program.definitions) {
 if (definition instanceof ProcedureDefinition) {
 generateProcedure((ProcedureDefinition) definition, table);
 }
 }
 }

 private void generateProcedure(ProcedureDefinition procedure, SymbolTable globalTable) {
 String procedureName = procedure.name.toString();
 output.emitExport(procedureName);
 output.emitLabel(procedureName);

 ProcedureEntry procedureEntry = (ProcedureEntry) globalTable.lookup(procedure.name);
 SymbolTable localTable = procedureEntry.localTable;
 int frameSize = procedureEntry.stackLayout.frameSize();

 output.emitInstruction("sub", Register.STACK_POINTER, Register.STACK_POINTER, frameSize, "; allocate frame");
 output.emitInstruction("stw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset(), "; save old frame pointer");
 output.emitInstruction("add", Register.FRAME_POINTER, Register.STACK_POINTER, frameSize, "; setup new frame pointer");
 output.emitInstruction("stw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset(), "; save return register");

 for (Statement statement : procedure.body) {
 statement.accept(this, localTable);  // Using the Visitor pattern
 }

 output.emitInstruction("ldw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset(), "; restore return register");
 output.emitInstruction("ldw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset(), "; restore old frame pointer");
 output.emitInstruction("add", Register.STACK_POINTER, Register.STACK_POINTER, frameSize, "; release frame");
 output.emitInstruction("jr", Register.RETURN_ADDRESS, "; return");
 }

 @Override
 public void visit(CallStatement callStatement, SymbolTable table) {
 for (Expression arg : callStatement.arguments) {
 Register reg = allocateRegister();
 arg.accept(this, table);
 output.emitInstruction("stw", reg, Register.STACK_POINTER, 0);  // Adjust the offset as needed
 freeRegister(reg);
 }
 output.emitInstruction("jal", callStatement.procedureName.toString());
 }

 @Override
 public void visit(BinaryExpression expr, SymbolTable table) {
 expr.leftOperand.accept(this, table);
 Register leftReg = allocateRegister();
 output.emitInstruction("stw", leftReg, Register.STACK_POINTER, 0);
 freeRegister(leftReg);

 expr.rightOperand.accept(this, table);
 Register rightReg = allocateRegister();
 output.emitInstruction("stw", rightReg, Register.STACK_POINTER, 4);
 freeRegister(rightReg);

 Register resultReg = allocateRegister();
 output.emitInstruction("ldw", resultReg, Register.STACK_POINTER, 0);
 output.emitInstruction("ldw", rightReg, Register.STACK_POINTER, 4);
 output.emitInstruction("add", resultReg, resultReg, rightReg);
 freeRegister(rightReg);
 freeRegister(resultReg);
 }

 // Implement other visit methods for different node types...

 private void assemblerProlog() {
 output.emitImport("printi");
 output.emitImport("printc");
 output.emitImport("readi");
 output.emitImport("readc");
 output.emitImport("exit");
 output.emitImport("time");
 output.emitImport("clearAll");
 output.emitImport("setPixel");
 output.emitImport("drawLine");
 output.emitImport("drawCircle");
 output.emitImport("_indexError");
 output.emit("");
 output.emit("\t.code");
 output.emit("\t.align\t4");
 }
 }
 */

/**
 * Es muss noch genau implementiert werden , wie die Benary expression aussehen sollten . Das muss die Refenceimplementierung ähneln .
 */