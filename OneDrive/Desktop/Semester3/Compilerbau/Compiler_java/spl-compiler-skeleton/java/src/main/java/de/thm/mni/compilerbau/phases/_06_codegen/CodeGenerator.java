package de.thm.mni.compilerbau.phases._06_codegen;

import de.thm.mni.compilerbau.CommandLineOptions;
import de.thm.mni.compilerbau.absyn.*;
import de.thm.mni.compilerbau.table.Entry;
import de.thm.mni.compilerbau.table.ProcedureEntry;
import de.thm.mni.compilerbau.table.SymbolTable;
import de.thm.mni.compilerbau.table.VariableEntry;
import de.thm.mni.compilerbau.utils.NotImplemented;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to generate the assembly code for the compiled program.
 * This code is emitted via the {@link CodePrinter} in the output field of this class.
 */
public class CodeGenerator {
    final CommandLineOptions options;
    final CodePrinter output;
private  Register actualRegister ;
    /**
     * Initializes the code generator.
     *
     * @param options The command line options passed to the compiler
     * @param output  The PrintWriter to the output file.
     */
    public CodeGenerator(CommandLineOptions options, PrintWriter output) throws IOException {
        this.options = options;
        this.output = new CodePrinter(output);
        this.actualRegister = Register.FIRST_FREE_USE ;
    }

    public void generateCode(Program program, SymbolTable table) {
        assemblerProlog();

        //TODO (assignment 6): generate eco32 assembler code for the spl program


        /**
         * Bei der Arrayberechnung :
         * 1. Indexberechnung
         * 2 . Größe des Arrays
         * 3. Testen , dass
         */

        /**
         * Für die If-schleife springen wir zu einem anderen Lebel
         * op1 und op2
         */
        /**
         * Für die while Schleife das gleiche .
         */

        /**
         *
         */
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
            generateStatement(statement,localeTabelle);  // Bekommt die globale Tabelle und generiert den Code dafür
        }

        // Wiederherstellung des Rückkehrregisters und des Frame-Pointers, dann Freigabe des Frames
        output.emitInstruction("ldw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset(),"   ; restore return register");
        output.emitInstruction("ldw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset(),"   ; restore old frame pointer");
        output.emitInstruction("add", Register.STACK_POINTER, Register.STACK_POINTER, frameSize,"   ; release frame");
        output.emitInstruction("jr", Register.RETURN_ADDRESS,"   ; return");
    }

    // Exemple d'utilisation dans generateStatement
    private void generateStatement(Statement statement, SymbolTable localTable) {
        if (statement instanceof CallStatement) {
            generateCallStatement((CallStatement) statement,localTable);
        } else if (statement instanceof AssignStatement assignment) {
            if (assignment.target instanceof ArrayAccess arrayAccess) {
                Register targetRegister = getRegisterForVariable(assignment.target,localTable);
                Register valueRegister = getRegisterForExpression(assignment.value,localTable);
                generateArrayAccess(arrayAccess, targetRegister,localTable);
                output.emitInstruction("stw", valueRegister, targetRegister, 0);
            } else if (assignment.target instanceof NamedVariable namedVariable) {
                generateNamedOfVariable(namedVariable, localTable);
                Register targetRegister = getRegisterForVariable(namedVariable,
                        localTable);
                Register valueRegister = getRegisterForExpression(assignment.value,
                        localTable);
                output.emitInstruction("stw", valueRegister, targetRegister, 0);
            }
        } else if (statement instanceof IfStatement ifStatement) {
            generateIfStatement(ifStatement, localTable);
        } else if (statement instanceof WhileStatement whileStatement) {
            generateWhileStatement(whileStatement, localTable);
        }
        // Ajouter d'autres cas pour d'autres types d'instructions
    }


    private void generateCallStatement(CallStatement statement, SymbolTable localTable) {
        List<Register> argumentRegisters = new ArrayList<>();

        // Charger les arguments
        for (int i = 0; i < statement.arguments.size(); i++) {
            Expression argument = statement.arguments.get(i);
            Register argRegister;

            if (argument instanceof IntLiteral intLiteral) {
                argRegister = getNextFreeRegister();
                output.emitInstruction("add", argRegister, Register.NULL, intLiteral.value, "load immediate value");
            } else if (argument instanceof VariableExpression variableExpression) {
                argRegister = getNextFreeRegister();
                Register varAddressRegister = getNextFreeRegister();
                loadVariable(variableExpression.variable, varAddressRegister, localTable);
                output.emitInstruction("ldw", argRegister, varAddressRegister, 0, "load variable value");
            } else if (argument instanceof BinaryExpression binaryExpression) {
                argRegister = getNextFreeRegister();
                generateBinaryExpression(binaryExpression, argRegister,localTable);
            } else if (argument instanceof UnaryExpression unaryExpression) {
                argRegister = getNextFreeRegister();
             //   generateUnaryExpression(unaryExpression, argRegister);
            } else {
                throw new UnsupportedOperationException("Unsupported argument type");
            }
            argumentRegisters.add(argRegister);
        }

        // Stocker les arguments sur la pile
        for (int i = 0; i < argumentRegisters.size(); i++) {
            Register argRegister = argumentRegisters.get(i);
            output.emitInstruction("stw", argRegister, Register.STACK_POINTER, i * 4, "store argument");
        }

        // Appel de la fonction
        output.emitInstruction("jal", statement.procedureName.toString(), "call function");
    }

    private void loadVariable(Variable variable, Register addressRegister, SymbolTable localTable) {
        if (variable instanceof NamedVariable namedVariable) {
            VariableEntry varEntry = (VariableEntry) localTable.lookup(namedVariable.name);
            output.emitInstruction("add", addressRegister, Register.FRAME_POINTER, varEntry.offset, "load variable address");
        } else {
            throw new UnsupportedOperationException("Unsupported variable type");
        }
    }

    private Register getRegisterForVariable(Variable variable,SymbolTable localTable) {
        // Beispielmethode, um einen Register für eine Variable zu erhalten
        // Diese Methode sollte erweitert werden, um tatsächliche Variablenregister zu verwalten
        Register variableRegister = actualRegister;
        if(variable instanceof ArrayAccess arrayAccess){
            return variableRegister ;
        }else if (variable instanceof NamedVariable namedVariable){
            VariableEntry varEntry = (VariableEntry) localTable.lookup(namedVariable.name);
            Register varRegister = getNextFreeRegister();
            output.emitInstruction("add", varRegister, Register.FRAME_POINTER, varEntry.offset, "load variable address");
            return varRegister;
        }
        actualRegister.next() ;
        throw new UnsupportedOperationException("Variable  type not supported");
    }


    private void generateIfStatement(IfStatement statement, SymbolTable localTable) {
        String elseLabel = "else" + statement.hashCode();
        String endLabel = "endif" + statement.hashCode();

        // Générer le code pour l'expression conditionnelle
        Register conditionRegister = getRegisterForExpression(statement.condition,localTable);

        // Instruction de saut conditionnel vers le label else
        output.emitInstruction("bz", conditionRegister, elseLabel);

        // Générer le code pour le bloc then
        generateStatement(statement.thenPart, localTable);

        // Sauter à la fin de l'instruction if
        output.emitInstruction("j", endLabel);

        // Label else
        output.emitLabel(elseLabel);
        if (statement.elsePart != null) {
            generateStatement(statement.elsePart, localTable);
        }

        // Label end if
        output.emitLabel(endLabel);
    }

    private void generateWhileStatement(WhileStatement statement, SymbolTable localTable) {
        String startLabel = "while" + statement.hashCode();
        String endLabel = "endwhile" + statement.hashCode();

        // Label début de la boucle
        output.emitLabel(startLabel);

        // Générer le code pour l'expression conditionnelle
        Register conditionRegister = getRegisterForExpression(statement.condition,localTable);

        // Instruction de saut conditionnel vers la fin de la boucle
        output.emitInstruction("bz", conditionRegister, endLabel);

        // Générer le code pour le corps de la boucle
        generateStatement(statement.body, localTable);

        // Sauter au début de la boucle
        output.emitInstruction("j", startLabel);

        // Label fin de la boucle
        output.emitLabel(endLabel);
    }

    private void generateArrayAccess(ArrayAccess arrayAccess, Register targetRegister,SymbolTable localTable) {
        Register baseRegister = getRegisterForVariable(arrayAccess.array,localTable);
        Register indexRegister = getRegisterForExpression(arrayAccess.index,localTable);

        // Calcul de l'adresse de l'élément du tableau
        output.emitInstruction("mul", indexRegister, indexRegister, 4); // Suppose que chaque élément a une taille de 4 octets
        output.emitInstruction("add", targetRegister, baseRegister, indexRegister);
    }

    private  void generateNamedOfVariable(NamedVariable namedVariable,SymbolTable localeTabelle) {
        Register register = Register.FIRST_FREE_USE ;
       // SymbolTable localTable = new SymbolTable(localeTabelle) ; // Wir benutzen die lokale Tabelle um die Variable zu holen
    VariableEntry variableEntry = (VariableEntry)localeTabelle.lookup(namedVariable.name);  // Wir holen die Variable aus der Symbole Tabelle

output.emitInstruction("add",register,Register.FRAME_POINTER,variableEntry.offset);
// if ( variableEntry.type == )
    //output.emitInstruction("add",register.next(),Register.FRAME_POINTER,variableEntry.offset);
   // output.emitInstruction("stw",register.next(),register,0);



}


    private Register getNextFreeRegister() {
        Register nextRegister = actualRegister;
        actualRegister = actualRegister.next();
        return nextRegister;
    }


    private void generateBinaryExpression(BinaryExpression expression, Register targetRegister,SymbolTable localTabel) {
        // Berechnung des linken und rechten Operanden
        Register leftRegister = getNextFreeRegister();
        Register rightRegister = getNextFreeRegister();

        if (expression.leftOperand instanceof IntLiteral leftLiteral) {
            output.emitInstruction("add", leftRegister, Register.NULL, leftLiteral.value, "load left operand");
        } else {
            leftRegister = getRegisterForExpression(expression.leftOperand,localTabel);
        }

        if (expression.rightOperand instanceof IntLiteral rightLiteral) {
            output.emitInstruction("add", rightRegister, Register.NULL, rightLiteral.value, "load right operand");
        } else {
            rightRegister = getRegisterForExpression(expression.rightOperand,localTabel);
        }


        switch (expression.operator) {
            case ADD:
                output.emitInstruction("add", targetRegister, leftRegister, rightRegister);
                break;
            case SUB:
                output.emitInstruction("sub", targetRegister, leftRegister, rightRegister);
                break;
            case MUL:
                output.emitInstruction("mul", targetRegister, leftRegister, rightRegister);
                break;
            case DIV:
                output.emitInstruction("div", targetRegister, leftRegister, rightRegister);
                break;
            case EQU:
                output.emitInstruction("eq", targetRegister, leftRegister, rightRegister);
                break;
            case NEQ:
                output.emitInstruction("neq", targetRegister, leftRegister, rightRegister);
                break;
            case LST:
                output.emitInstruction("lt", targetRegister, leftRegister, rightRegister);
                break;
            case LSE:
                output.emitInstruction("le", targetRegister, leftRegister, rightRegister);
                break;
            case GRT:
                output.emitInstruction("gt", targetRegister, leftRegister, rightRegister);
                break;
            case GRE:
                output.emitInstruction("ge", targetRegister, leftRegister, rightRegister);
                break;

            // Weitere Fälle für andere Operatoren hinzufügen
        }

    }

    /**
     * Ich muss meinen Register verwalten
     * Ich muss für die Variables auch jedes mal
     * @param expression
     * @return
     */
    private Register getRegisterForExpression(Expression expression,SymbolTable localTabel) {
        Register expressionRegister = actualRegister; // Initialisation du registre
        if (expression instanceof IntLiteral) {
            return expressionRegister;
        } else if (expression instanceof VariableExpression variableExpression) {
            return getRegisterForVariable(variableExpression.variable,localTabel);
        } else if (expression instanceof BinaryExpression binaryExpression) {
            generateBinaryExpression(binaryExpression, expressionRegister,localTabel);
            return expressionRegister;
        } else if (expression instanceof UnaryExpression unaryExpression) {
         //   generateUnaryExpression(unaryExpression, expressionRegister);
            return expressionRegister;
        }
        actualRegister = actualRegister.next(); // Incrémenter le registre actuel
        throw new UnsupportedOperationException("Expression type not supported");
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
        output.emit("\t.align\t4");
    }
}

/**
 * Es muss noch genau implementiert werden , wie die Benary expression aussehen sollten . Das muss die Refenceimplementierung ähneln .
 */