package de.thm.mni.compilerbau.phases._05_varalloc;

import de.thm.mni.compilerbau.CommandLineOptions;
import de.thm.mni.compilerbau.absyn.*;
import de.thm.mni.compilerbau.table.ParameterType;
import de.thm.mni.compilerbau.table.ProcedureEntry;
import de.thm.mni.compilerbau.table.SymbolTable;
import de.thm.mni.compilerbau.table.VariableEntry;
import de.thm.mni.compilerbau.types.ArrayType;
import de.thm.mni.compilerbau.types.Type;
import de.thm.mni.compilerbau.utils.*;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class is used to calculate the memory needed for variables and stack frames of the currently compiled SPL program.
 * Those value have to be stored in their corresponding fields in the {@link ProcedureEntry}, {@link VariableEntry} and
 * {@link ParameterType} classes.
 */
public class VarAllocator {
    public static final int REFERENCE_BYTESIZE = 4;

    private final CommandLineOptions options;

    /**
     * @param options The options passed to the compiler
     */
    public VarAllocator(CommandLineOptions options) {
        this.options = options;
    }

    public void allocVars(Program program, SymbolTable table) {
        //TODO (assignment 5): Allocate stack slots for all parameters and local variables
// Die Methode bekommt das Programm , die Symbole Tabelle und
        // Die Prozeduren durchlaufen um für die verschiedenen Variablen und Prozeduren Platz zu allokieren .
        //


        // Wenn es keine ProzedurAufruf vorkommt , outgoingAreaSize = -1
 // Jeder Type zeigt eine Größe
        // Jedes Argument jede Parameter , jede lokale Variable speichert ein Offset relativ zum Frame-pointer
        // Jede Procezedur speichert die Größe von 3 Bereichen
        // - Eingehende Argumente
        // - lokale Variablen
        // - Ausgehende Argumente
        //  Die Größe des Argumentsbereichs muss man ermitteln und speichern
        // ECO32 ist eine 32-bit Maschine, daher sind Worte immer 4 Bytes lang
        // Integer benötigt 4 Byte
        // Adressen benötigen 4 Byte
        // Boolean benötigt 4 Byte
        // Implementieren der Berechnungen mittels Visitor-Pattern oder Type-Switches
        //TODO: Uncomment this when the above exception is removed!

        for (GlobalDefinition definition : program.definitions) {
            if (definition instanceof ProcedureDefinition procedure) {
                allocateProcedureVars(procedure, table);
            }
        }
        // Afficher les différentes variables
        formatVars(program, table);
    }
    private void allocateProcedureVars(ProcedureDefinition procedure, SymbolTable table) {

        ProcedureEntry procedureEntry = (ProcedureEntry) table.lookup(procedure.name);

        SymbolTable localTable = procedureEntry.localTable;

        StackLayout stackLayout = new StackLayout();

        int offset = 0;

        // Wir gehen alle Parameter durch
        for(int i =0 ; i < procedure.parameters.size() ; i++) {
    // Wir holen einen Parameter
            ParameterDefinition parameter = procedure.parameters.get(i);
            VariableEntry variableEntry = (VariableEntry) localTable.lookup(parameter.name);
            Type variableType = variableEntry.type ;
            int paramSize = variableType.byteSize;
                if(variableEntry.isReference){
                    paramSize=REFERENCE_BYTESIZE ;
                }

            variableEntry.offset = offset;
            stackLayout.argumentAreaSize = (stackLayout.argumentAreaSize == null ? 0 : stackLayout.argumentAreaSize) + paramSize;
            procedureEntry.parameterTypes.get(i).offset = offset;
            offset += paramSize;
        }
        offset = 0 ;
        // Allocate space for local variables
        for (VariableDefinition variable : procedure.variables) {
            VariableEntry varEntry = (VariableEntry) localTable.lookup(variable.name);
            Type variableType = varEntry.type ;
            int varSize = variableType.byteSize;
            varEntry.offset = -offset - varSize;
            stackLayout.localVarAreaSize = (stackLayout.localVarAreaSize == null ? 0 : stackLayout.localVarAreaSize) + varSize;
            offset += varSize;
        }

        // find the callStatement with the max callSize

        stackLayout.outgoingAreaSize = getMaxCallSize(procedure.body, table);

        procedureEntry.stackLayout.argumentAreaSize = stackLayout.argumentAreaSize;
        procedureEntry.stackLayout.localVarAreaSize = stackLayout.localVarAreaSize;
        procedureEntry.stackLayout.outgoingAreaSize = stackLayout.outgoingAreaSize;
    }

    private int getMaxCallSize(List<Statement> statements, SymbolTable globalTable) {
        int maxCallSize = 0;
        for (Statement statement : statements) {
            if (statement instanceof CallStatement callStatement) {
                int callSize = 0;
                ProcedureEntry calleeEntry = (ProcedureEntry) globalTable.lookup(callStatement.procedureName);
                for (ParameterType paramType : calleeEntry.parameterTypes) {
                    callSize += paramType.isReference ? REFERENCE_BYTESIZE : getSize(paramType.type);
                }
                if (callSize > maxCallSize) {
                    maxCallSize = callSize;
                }
            } else if (statement instanceof CompoundStatement compoundStatement) {
                maxCallSize = Math.max(maxCallSize, getMaxCallSize(compoundStatement.statements, globalTable));
            }
        }
        return maxCallSize;
    }

    private int getSize(Type type) {
        if (type instanceof ArrayType) {
            return ((ArrayType) type).baseType.byteSize;
        } else {
            return type.byteSize;
        }
    }


    /**
     * Formats and prints the variable allocation to a human-readable format
     * The stack layout
     *
     * @param program The abstract syntax tree of the program
     * @param table   The symbol table containing all symbols of the spl program
     */
    private static void formatVars(Program program, SymbolTable table) {
        program.definitions.stream().filter(dec -> dec instanceof ProcedureDefinition).map(dec -> (ProcedureDefinition) dec).forEach(procDec -> {
            ProcedureEntry entry = (ProcedureEntry) table.lookup(procDec.name);

            var isLeafOptimized = false; // This is a remainder from a bonus assignment, but I refuse to adjust this entire mess of a method
            var varparBasis = (isLeafOptimized ? "SP" : "FP");

            AsciiGraphicalTableBuilder ascii = new AsciiGraphicalTableBuilder();
            ascii.line("...", AsciiGraphicalTableBuilder.Alignment.CENTER);

            {
                final var zipped = IntStream.range(0, procDec.parameters.size()).boxed()
                        .map(i -> new Pair<>(procDec.parameters.get(i), new Pair<>(((VariableEntry) entry.localTable.lookup(procDec.parameters.get(i).name)), entry.parameterTypes.get(i))))
                        .sorted(Comparator.comparing(p -> Optional.ofNullable(p.second.first.offset).map(o -> -o).orElse(Integer.MIN_VALUE)));

                zipped.forEach(v -> {
                    boolean consistent = Objects.equals(v.second.first.offset, v.second.second.offset);

                    ascii.line("par " + v.first.name.toString(), "<- " + varparBasis + " + " +
                                    (consistent
                                            ? Objects.toString(v.second.first.offset, "NULL")
                                            : String.format("INCONSISTENT(%s/%s)", Objects.toString(v.second.first.offset, "NULL"), Objects.toString(v.second.second.offset, "NULL"))),
                            AsciiGraphicalTableBuilder.Alignment.LEFT);
                });
            }

            ascii.sep("BEGIN", "<- " + varparBasis);
            if (!procDec.variables.isEmpty()) {
                procDec.variables.stream()
                        .map(v -> new AbstractMap.SimpleImmutableEntry<>(v, ((VariableEntry) entry.localTable.lookup(v.name))))
                        .sorted(Comparator.comparing(e -> Try.execute(() -> -e.getValue().offset).getOrElse(0)))
                        .forEach(v -> ascii.line("var " + v.getKey().name.toString(),
                                "<- " + varparBasis + " - " + Optional.ofNullable(v.getValue().offset).map(o -> -o).map(Objects::toString).orElse("NULL"),
                                AsciiGraphicalTableBuilder.Alignment.LEFT));

                if (!isLeafOptimized) ascii.sep("");
            }

            if (isLeafOptimized) ascii.close("END");
            else {
                ascii.line("Old FP",
                        "<- SP + " + Try.execute(entry.stackLayout::oldFramePointerOffset).map(Objects::toString).getOrElse("UNKNOWN"),
                        AsciiGraphicalTableBuilder.Alignment.LEFT);

                ascii.line("Old Return",
                        "<- FP - " + Try.execute(() -> -entry.stackLayout.oldReturnAddressOffset()).map(Objects::toString).getOrElse("UNKNOWN"),
                        AsciiGraphicalTableBuilder.Alignment.LEFT);

                if (entry.stackLayout.outgoingAreaSize == null || entry.stackLayout.outgoingAreaSize > 0) {

                    ascii.sep("outgoing area");

                    if (entry.stackLayout.outgoingAreaSize != null) {
                        var max_args = entry.stackLayout.outgoingAreaSize / 4;

                        for (int i = 0; i < max_args; ++i) {
                            ascii.line(String.format("arg %d", max_args - i),
                                    String.format("<- SP + %d", (max_args - i - 1) * 4),
                                    AsciiGraphicalTableBuilder.Alignment.LEFT);
                        }
                    } else {
                        ascii.line("UNKNOWN SIZE", AsciiGraphicalTableBuilder.Alignment.LEFT);
                    }
                }

                ascii.sep("END", "<- SP");
                ascii.line("...", AsciiGraphicalTableBuilder.Alignment.CENTER);
            }

            System.out.printf("Variable allocation for procedure '%s':\n", procDec.name);
            System.out.printf("  - size of argument area = %s\n", Objects.toString(entry.stackLayout.argumentAreaSize, "NULL"));
            System.out.printf("  - size of localvar area = %s\n", Objects.toString(entry.stackLayout.localVarAreaSize, "NULL"));
            System.out.printf("  - size of outgoing area = %s\n", Objects.toString(entry.stackLayout.outgoingAreaSize, "NULL"));
            System.out.printf("  - frame size = %s\n", Try.execute(entry.stackLayout::frameSize).map(Objects::toString).getOrElse("UNKNOWN"));
            System.out.println();
            if (isLeafOptimized) System.out.println("  Stack layout (leaf optimized):");
            else System.out.println("  Stack layout:");
            System.out.println(ascii.toString().indent(4));
            System.out.println();
        });
    }
}
