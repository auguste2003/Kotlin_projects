package de.thm.mni.compilerbau.phases._04a_tablebuild;

import de.thm.mni.compilerbau.CommandLineOptions;
import de.thm.mni.compilerbau.absyn.*;
import de.thm.mni.compilerbau.absyn.visitor.DoNothingVisitor;
import de.thm.mni.compilerbau.table.*;
import de.thm.mni.compilerbau.types.ArrayType;
import de.thm.mni.compilerbau.types.Type;
import de.thm.mni.compilerbau.utils.NotImplemented;
import de.thm.mni.compilerbau.utils.SplError;

import javax.print.DocFlavor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to create and populate a {@link SymbolTable} containing entries for every symbol in the currently
 * compiled SPL program.
 * Every declaration of the SPL program needs its corresponding entry in the {@link SymbolTable}.
 * <p>
 * Calculated {@link Type}s can be stored in and read from the dataType field of the {@link Expression},
 * {@link TypeExpression} or {@link Variable} classes.
 */
public class TableBuilder {
    private final CommandLineOptions options;

    public TableBuilder(CommandLineOptions options) {

        this.options = options;

    }

    public SymbolTable buildSymbolTable(Program program) {
        //TODO (assignment 4a): Initialize a symbol table with all predefined symbols and fill it with user-defined symbols
        SymbolTable globalTable = TableInitializer.initializeGlobalTable();

// Ubitialisation der Symbole
        class nothing extends DoNothingVisitor {
            // Utiliser la méthode initializeGlobalTable pour obtenir la table des symboles globales initialisée

            //  use the programm to enter all identifiers
            @Override   // Visit the programm
            public void visit(Program program) {
                program.definitions.forEach(node -> node.accept(this));
            }

            /**
             * TypeDeclaration - Ajoute un nouveau type.
             * ProcedureDeclaration - Ajoute une nouvelle procédure.
             * ParameterDeclaration - Ajoute un paramètre de procédure.
             * VariableDeclaration - Ajoute une nouvelle variable.
             */

            @Override  // Visite the typeDefinition
            public void visit(TypeDefinition typeDefinition) {
                typeDefinition.typeExpression.accept(this);
                TypeEntry type = new TypeEntry(getType(typeDefinition.typeExpression,globalTable));
                SplError error = SplError.RedefinitionOfIdentifier(typeDefinition.position, typeDefinition.name);
                globalTable.enter(typeDefinition.name, type, error);

            }


            @Override // Visit the procedure definition
              public void visit(ProcedureDefinition procedureDefinition) {
                SymbolTable localTable = new SymbolTable(globalTable); // Déklarer et gérer les variables locales .


                // Process parameters first
                List<ParameterType> parameterTypes = procedureDefinition.parameters.stream()
                        .map(param -> {
                            param.typeExpression.accept(this);
                            localTable.enter(param.name,
                                    new VariableEntry(getType(param.typeExpression,localTable), true),
                                    SplError.RedefinitionOfIdentifier(param.position, param.name));
                            return new ParameterType(getType(param.typeExpression,localTable), param.isReference);
                        }).collect(Collectors.toList()); // Récuperer la liste de parametres


               procedureDefinition.variables
                        .forEach(var -> {
                            var.typeExpression.accept(this);
                            localTable.enter(var.name,
                                    new VariableEntry(getType(var.typeExpression,localTable), false),
                                    SplError.RedefinitionOfIdentifier(var.position, var.name));

                        }); // Récuperer la liste de parametres

                /**
                 *    List<ParameterType> parameterTypes = new ArrayList<>();
                 *  procedureDefinition.parameters.forEach(param -> {
                 *             param.typeExpression.accept(this);
                 *             localTable.enter(param.name,
                 *                     new VariableEntry(param.typeExpression.dataType, true),
                 *                     SplError.NotAParameter(param.position, param.name));
                 *             parameterTypes.add(new ParameterType(param.typeExpression.dataType, param.isReference));
                 *         });
                 */
                ProcedureEntry procedureEntry = new ProcedureEntry(localTable, parameterTypes);
                SplError error = SplError.RedefinitionOfIdentifier(procedureDefinition.position, procedureDefinition.name);
                globalTable.enter(procedureDefinition.name, procedureEntry, error);

                // Process local variables and the body of the procedure
             //   procedureDefinition.body.forEach(statement -> statement.accept(this));

                // Print the symbol table at the end of the procedure
                if( options.phaseOption ==  CommandLineOptions.PhaseOption.TABLES){
                    printSymbolTableAtEndOfProcedure(procedureDefinition.name, procedureEntry);
                }

            }

            Type getType(TypeExpression typeExpression, SymbolTable table ){
               switch (typeExpression){
                   case NamedTypeExpression namedTypeExpression :
                     Entry entry =   table.lookup(namedTypeExpression.name,SplError.UndefinedIdentifier(namedTypeExpression.position, namedTypeExpression.name));
                     if(!(entry instanceof TypeEntry)) {
                         throw SplError.NotAType(namedTypeExpression.position, namedTypeExpression.name);
                     }
                     return  ((TypeEntry) entry).type ;
                   case ArrayTypeExpression arrayTypeExpression :
                     return new ArrayType(getType(arrayTypeExpression.baseType,table),arrayTypeExpression.arraySize);
               }
            }

        }




        program.accept(new nothing());
        return  globalTable ;

    }

    /**
     * Prints the local symbol table of a procedure together with a heading-line
     * NOTE: You have to call this after completing the local table to support '--tables'.
     *
     * @param name  The name of the procedure
     * @param entry The entry of the procedure to print
     */
    static void printSymbolTableAtEndOfProcedure(Identifier name, ProcedureEntry entry) {
        System.out.format("Symbol table at end of procedure '%s':\n", name);
        System.out.println(entry.localTable.toString());
    }
}
