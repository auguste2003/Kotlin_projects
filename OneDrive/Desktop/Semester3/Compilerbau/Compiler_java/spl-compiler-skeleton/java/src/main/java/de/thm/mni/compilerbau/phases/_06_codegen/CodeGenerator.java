package de.thm.mni.compilerbau.phases._06_codegen;

import de.thm.mni.compilerbau.CommandLineOptions;
import de.thm.mni.compilerbau.absyn.*;
import de.thm.mni.compilerbau.table.Entry;
import de.thm.mni.compilerbau.table.ProcedureEntry;
import de.thm.mni.compilerbau.table.SymbolTable;
import de.thm.mni.compilerbau.utils.NotImplemented;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to generate the assembly code for the compiled program.
 * This code is emitted via the {@link CodePrinter} in the output field of this class.
 */
public class CodeGenerator {
    final CommandLineOptions options;
    final CodePrinter output;

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

        // Allokieren des Frames
        int frameSize = procedureEntry.stackLayout.frameSize(); // dann holen wir den framesize der Procedure
        output.emitInstruction("sub", Register.STACK_POINTER, Register.STACK_POINTER, frameSize);
        output.emitInstruction("stw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset());
        output.emitInstruction("add", Register.FRAME_POINTER, Register.STACK_POINTER, frameSize);
        output.emitInstruction("stw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset());

        for (Statement statement : procedure.body) {
            generateStatement(statement);
        }

        // Wiederherstellung des Rückkehrregisters und des Frame-Pointers, dann Freigabe des Frames
        output.emitInstruction("ldw", Register.RETURN_ADDRESS, Register.FRAME_POINTER, procedureEntry.stackLayout.oldReturnAddressOffset());
        output.emitInstruction("ldw", Register.FRAME_POINTER, Register.STACK_POINTER, procedureEntry.stackLayout.oldFramePointerOffset());
        output.emitInstruction("add", Register.STACK_POINTER, Register.STACK_POINTER, frameSize);
        output.emitInstruction("jr", Register.RETURN_ADDRESS);
    }

    private void generateStatement(Statement statement) {
        if (statement instanceof CallStatement) {
            generateCallStatement((CallStatement) statement);
        }else if (statement instanceof AssignStatement assignment) {
            if (assignment.target instanceof  ArrayAccess arrayAccess) {

            } else if (assignment.target instanceof NamedVariable) {
                
            }

        }
        
        // Weitere Fälle für andere Arten von Anweisungen hinzufügen
    }

    private void generateCallStatement(CallStatement statement) {
        // Argumente laden
        for (int i = 0; i < statement.arguments.size(); i++) {
            Expression argument = statement.arguments.get(i);
            Register argRegister = Register.FIRST_FREE_USE.next();

            if (argument instanceof IntLiteral intLiteral) {
                // Wenn das Argument ein IntLiteral ist, laden Sie den unmittelbaren Wert
                Register register = getRegisterForExpression(intLiteral);
                output.emitInstruction("add", register,new Register(0) ,String.valueOf(intLiteral.value));
            } else if (argument instanceof VariableExpression variableExpression) {
                // Wenn das Argument eine VariableExpression ist, laden Sie den Wert der Variable
                Register varRegister = getRegisterForVariable(variableExpression.variable);
                output.emitInstruction("add", varRegister, Register.FRAME_POINTER, 0);
                output.emitInstruction("ldw", varRegister, varRegister, 0);
            } else if (argument instanceof BinaryExpression binaryExpression) {
                // Wenn das Argument eine BinaryExpression ist, berechnen Sie den Ausdruck
                generateBinaryExpression(binaryExpression, argRegister);
            }else if (argument instanceof UnaryExpression unaryExpression) {

            }
            // Weitere Fälle für andere Argumenttypen hinzufügen
        }
        // Argumente auf den Stapel speichern
        for (int i = 0; i < statement.arguments.size(); i++) {
            Register argRegister = Register.FIRST_FREE_USE.minus(i);
            output.emitInstruction("stw", argRegister, Register.STACK_POINTER, i * 4);
        }

        // Funktionsaufruf
        output.emitInstruction("jal", statement.procedureName.toString());
    }

    private void generateBinaryExpression(BinaryExpression expression, Register targetRegister) {
        // Berechnung des linken und rechten Operanden
        Register leftRegister = getRegisterForExpression(expression.leftOperand);
        Register rightRegister = getRegisterForExpression(expression.rightOperand);

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

    private Register getRegisterForExpression(Expression expression) {
        if (expression instanceof IntLiteral intLiteral) {
            return Register.FIRST_FREE_USE;
        } else if (expression instanceof VariableExpression variableExpression) {
            return getRegisterForVariable(variableExpression.variable);
        } else if (expression instanceof BinaryExpression binaryExpression) {
            Register targetRegister = Register.FIRST_FREE_USE.next();
            generateBinaryExpression(binaryExpression, targetRegister);
            return targetRegister;
        }
        // Weitere Fälle für andere Ausdruckstypen hinzufügen
        throw new UnsupportedOperationException("Expression type not supported");
    }

    private Register getRegisterForVariable(Variable variable) {
        // Beispielmethode, um einen Register für eine Variable zu erhalten
        // Diese Methode sollte erweitert werden, um tatsächliche Variablenregister zu verwalten
        return Register.FIRST_FREE_USE;
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