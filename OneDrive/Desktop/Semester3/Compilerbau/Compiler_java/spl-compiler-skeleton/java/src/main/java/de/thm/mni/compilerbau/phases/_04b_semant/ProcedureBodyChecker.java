package de.thm.mni.compilerbau.phases._04b_semant;

import de.thm.mni.compilerbau.CommandLineOptions;
import de.thm.mni.compilerbau.absyn.*;
import de.thm.mni.compilerbau.absyn.visitor.DoNothingVisitor;
import de.thm.mni.compilerbau.table.*;
import de.thm.mni.compilerbau.types.ArrayType;
import de.thm.mni.compilerbau.types.PrimitiveType;
import de.thm.mni.compilerbau.types.Type;
import de.thm.mni.compilerbau.utils.NotImplemented;
import de.thm.mni.compilerbau.utils.SplError;

import javax.swing.text.Position;

/**
 * This class is used to check if the currently compiled SPL program is semantically valid.
 * The body of each procedure has to be checked, consisting of {@link Statement}s, {@link Variable}s and {@link Expression}s.
 * Each node has to be checked for type issues or other semantic issues.
 * Calculated {@link Type}s can be stored in and read from the dataType field of the {@link Expression} and {@link Variable} classes.
 */
public class ProcedureBodyChecker {

    private final CommandLineOptions options;

    public ProcedureBodyChecker(CommandLineOptions options) {
        this.options = options;
    }

    public void checkProcedures(Program program, SymbolTable globalTable) {
        boolean mainExists = false;
       checkNode(program, globalTable);
        for (GlobalDefinition definition : program.definitions) { // On parcourt le programme pour vérifier s'il y'a une main
            if (definition instanceof ProcedureDefinition procedure) {
                // check if the main exists
                if ("main".equals(procedure.name.toString())) {
                    mainExists = true;
                    if (!procedure.parameters.isEmpty()) { // Si la main() a des parametres
                        throw SplError.MainMustNotHaveParameters();
                    }
                    break;
                }
            }  else if ( definition instanceof  TypeDefinition typeDefinition ) {
                         if ("main".equals(typeDefinition.name.toString())) {
                             throw  SplError.MainIsNotAProcedure() ;
                         }
        }
        }
        if (!mainExists) {
            throw  SplError.MainIsMissing() ;
        }
// Tester le cas oú il n'ya pas de main et il y'a également d'autres cas
    }


    /**
     * @param expression Expression actuelle
     * @param table      la table globale
     * @return renvoit le type  des expressions de la table actuelle
     */
    static Type getType(Expression expression, SymbolTable table) {
        switch (expression) {
            case BinaryExpression binaryExpression -> {
                /*
                 *   z := x + y; // BinaryExpression
                 *     z := x * 2; // BinaryExpression
                 *     z := x - y; // BinaryExpression
                 *     z := x / y; // BinaryExpression
                 */
                Type leftType = getType(binaryExpression.leftOperand, table);
                Type rightType = getType(binaryExpression.rightOperand, table);
                assert leftType != null;
                if (!leftType.equals(rightType)) { // On vérifie si le type de gauche est le meme que le type de droit
                    throw SplError.OperandTypeMismatch(binaryExpression.position, binaryExpression.operator, leftType, rightType);
                }
                if (binaryExpression.operator.isArithmetic()){
                    return PrimitiveType.intType ; // On vérifie si le type est un Binaryexpression
                } else if (binaryExpression.operator.isComparison()) {
                    return PrimitiveType.boolType ;

                }
                return null ;
            }
            case IntLiteral intLiteral -> {  // On verifie si c'est un intLiteral
                //        x := 5;       // IntLiteral
                //       y := x + 3;   // IntLiteral

                return PrimitiveType.intType; // Renvoit uniquement le type entier
            }
            case UnaryExpression unaryExpression -> {
                // x:= -y
                Type operandType = getType(unaryExpression.operand, table);
                if (!operandType.equals(PrimitiveType.intType)) { // On vérifie si le type est un entier
                    throw SplError.OperandTypeMismatch(unaryExpression.position, unaryExpression.operator, operandType);
                }
                return PrimitiveType.intType;  // On vérifie si c'est une expression unaire  x:= -y
            }
            case VariableExpression variableExpression -> {
                //  y := x + 3; // `x` et `3` sont des `VariableExpression`
                return getType(variableExpression.variable, table);  // On renvoit juste la valeur du type obtenu au niveau de getType
            }

            // Ajoutez d'autres cas pour d'autres types d'expressions
            case null, default -> throw new NotImplemented();
        }
    }


    /**
     * La méthode présente retourne les differents types des variables .
     * @param variable pour les différentes variables de la méthode
     * @param table la table de symboles
     * @return
     */
    static Type getType(Variable variable, SymbolTable table) {
        // Vérification pour NamedVariable
        if (variable instanceof NamedVariable namedVariable) { // On vérifie si le type est une varible
            /*
             * a := 5;       // NamedVariable 'a'
    b := 3;       // NamedVariable 'b'
    c := a + b;   // NamedVariable 'a' et 'b'
             */

            Entry entry = table.lookup(namedVariable.name, SplError.UndefinedIdentifier(namedVariable.position, namedVariable.name));
            if (!(entry instanceof VariableEntry)) { // Si c'est le cas , on vérifi que c'est bien une variable qui est en entré
                throw SplError.NotAVariable(namedVariable.position, namedVariable.name);
            }
            if(  ((VariableEntry) entry).type instanceof PrimitiveType primitiveType){
                if (primitiveType.equals(PrimitiveType.intType)) {
                    return PrimitiveType.intType;
                }else if (primitiveType.equals(PrimitiveType.boolType)){
                    return PrimitiveType.boolType;
                }
            }
            return ((VariableEntry) entry).type;



        } else if (variable instanceof ArrayAccess arrayAccess) { // On vérifie s'il s'agit d'un aray . Si c'est le cas , on vérifie son index et sa valleur
            /*
             *arr[index] := 42; // ArrayAccess pour affectation
    value := arr[index]; // ArrayAccess pour lecture
             */

            // Vérification pour ArrayAccess
            Type arrayType = (Type)getType(arrayAccess.array, table);

            if (!(arrayType instanceof ArrayType)) {
                throw SplError.IndexingNonArray(arrayAccess.position, arrayType);
            }
            // Vérifie que l'index est de type int
            // recupere le type de l'index
            Type indexType = getType(arrayAccess.index, table);
            if (!indexType.equals(PrimitiveType.intType)) {
                throw SplError.IndexTypeMismatch(arrayAccess.position, indexType);
            }
            return ((ArrayType) arrayType).baseType; // On renvoit le type de l'array actuel
        }
        // Ajoutez d'autres cas pour d'autres types de variables
        else {
          return null ;
        }
    }


    static Type getType(TypeExpression typeExpression, SymbolTable table) {
        switch (typeExpression) {
            // Un NamedTypeExpression fait référence à un type défini par l'utilisateur ou à un type primitif
            /*
            type MyInt = int;

proc main() {
    var x: MyInt;
    x := 42;
}

             */
            case NamedTypeExpression namedTypeExpression -> { //
                Entry entry = table.lookup(namedTypeExpression.name, SplError.NotAType(namedTypeExpression.position, namedTypeExpression.name));
                if (!(entry instanceof TypeEntry)) {
                    throw SplError.NotAType(namedTypeExpression.position, namedTypeExpression.name);
                }
                return ((TypeEntry) entry).type;
            }
            case ArrayTypeExpression arrayTypeExpression -> {
                /*
                type IntArray = array[10] of int;

proc main() {
    var arr: IntArray;
    arr[0] := 42;
    arr[1] := arr[0] + 1;
}
                 */
                return getType(arrayTypeExpression.baseType, table);
            }
            case null, default -> throw new NotImplemented();
        }
    }



    public static void checkNode(Node node, SymbolTable table) {
        switch (node) {
            case ArrayAccess arrayAccess -> { // Si le noeud est un array acces , on vérifie son type et son index
                // Implémentation pour ArrayAccess
                // Vérification pour ArrayAccess

                Type arrayType = getType(arrayAccess.array, table);
                if (!(arrayType instanceof ArrayType)) {
                    throw SplError.IndexingNonArray(arrayAccess.position, arrayType);
                }
                // Vérifie que l'index est de type int
                // recupere le type de l'index
                Type indexType = getType(arrayAccess.index, table);
                if (!indexType.equals(PrimitiveType.intType)) {
                    throw SplError.IndexTypeMismatch(arrayAccess.position, indexType);
                }
            }
            case ArrayTypeExpression arrayTypeExpression -> {
                // Implémentation pour ArrayTypeExpression
                Type baseType = getType(arrayTypeExpression.baseType, table);
                // Vérifiez que le type de base est soit un PrimitiveType soit un ArrayType
                if (!(baseType instanceof PrimitiveType) && !(baseType instanceof ArrayType)) {
                    throw SplError.IndexingNonArray(arrayTypeExpression.position, baseType);
                }
            }

            case AssignStatement assignStatement -> { // Si le type est un assignstatement , il faut vérifier le type de gauche et de droite
                Type lhsType = getType(assignStatement.target, table);
                Type rhsType = getType(assignStatement.value, table);

                if (!lhsType.equals(rhsType)) {
                    throw SplError.IllegalAssignment(assignStatement.position, lhsType, rhsType);
                }
            }
            case BinaryExpression binaryExpression -> {
                Type leftType = getType(binaryExpression.leftOperand, table);
                Type rightType = getType(binaryExpression.rightOperand, table);
                if (!leftType.equals(rightType)) { // On vérifie si le type de gauche est le meme que le type de droit
                    throw SplError.OperandTypeMismatch(binaryExpression.position, binaryExpression.operator, leftType, rightType);
                }
            }
            case CallStatement callStatement -> {
                // Implémentation pour CallStatement
                callStatement.arguments.forEach(argument -> checkNode(argument, table));
                Entry entry = table.lookup(callStatement.procedureName, SplError.CallOfNonProcedure(callStatement.position, callStatement.procedureName));
                // Vérification que 'entré est bien une procédure
                if (!(entry instanceof ProcedureEntry procedureEntry)) {
                    throw SplError.CallOfNonProcedure(callStatement.position, callStatement.procedureName);
                }

                //Vérifier que le nombre d'argument conrespond au nombre de parametres
                int expected = procedureEntry.parameterTypes.size();
                int actual = callStatement.arguments.size();
                if (expected != actual) {
                    throw SplError.ArgumentCountMismatch(callStatement.position, callStatement.procedureName, expected, actual);
                }

                // On parcourt tous les parametres qu'on a actuellement
                for (int i = 0; i < actual; i++) {
                    Expression argument = callStatement.arguments.get(i);
                    Type argumentType = getType(callStatement.arguments.get(i), table);
                    ParameterType parameterType = procedureEntry.parameterTypes.get(i);
                    if (argumentType != parameterType.type) {
                        throw SplError.ArgumentTypeMismatch(callStatement.position, callStatement.procedureName, i + 1, parameterType.type, argumentType);
                    } else if (parameterType.isReference && !(argument instanceof VariableExpression) ) {
                        throw SplError.ArgumentMustBeAVariable(callStatement.position, callStatement.procedureName, i + 1);
                    }
                }
            }
            case CompoundStatement compoundStatement -> {
                // Implémentation pour CompoundStatement
                compoundStatement.statements.forEach(statement -> checkNode(statement, table));
            }
            case EmptyStatement emptyStatement -> {
                // Implémentation pour EmptyStatement
            }
            case IntLiteral intLiteral -> {
                // Implémentation pour IntLiteral
               /* Type intType = getType(intLiteral,table);
                if(!(intType.equals(PrimitiveType.intType))){
                    throw SplError.  ;
                };

                */

            }
            case UnaryExpression unaryExpression -> {
                Type operandType = getType(unaryExpression,table);
                if ( operandType != PrimitiveType.intType) {  // On récupere le type de la variable et de l'expression et vérifie si cela passe
                    throw SplError.OperandTypeMismatch(unaryExpression.position,unaryExpression.operator,operandType);
                }
            }
            case VariableExpression variableExpression -> {
                // Implémentation pour VariableExpression
                checkNode(variableExpression.variable,table); // On va aller vérifier pour les ArrayAcces et namedVariable
            }
            case Expression expression -> {
                // Implémentation pour Expression
               checkNode(expression,table); // On visite uniquement les expressions
            }
            case ProcedureDefinition procedureDefinition -> {
                ProcedureEntry procedureEntry = (ProcedureEntry) table.lookup(procedureDefinition.name);

               SymbolTable localTable = procedureEntry.localTable;

                // Implémentation pour ProcedureDefinition
                 // Faut une table locale pour cela
                procedureDefinition.body.forEach(statement -> checkNode(statement,localTable)); // On vérifie les types des statment deans la procedure
                procedureDefinition.variables.forEach(variableDefinition -> checkNode(variableDefinition,localTable)); // On vérifie les types des variables dans la procedure
                procedureDefinition.parameters.forEach(parameterDefinition -> checkNode(parameterDefinition,localTable)); // On vérifie les types des parametres dans la procedure
                // Implémentation pour ProcedureDefinition
            }
            case TypeDefinition typeDefinition -> {
                // Implémentation pour TypeDefinition
                checkNode(typeDefinition.typeExpression,table); // On vérifie s'il y'a dßeja ce type définit
            }
            case GlobalDefinition globalDefinition -> {
                // Implémentation pour GlobalDefinition

                /**
                 * Frage : Wie sollte ich mich auf de procedureDefinition und TypeDefinition begrenzen ?
                 */
                checkNode( globalDefinition, table); // On virifie le type de procedureDefinition et TypeDefinition

            }
            case VariableDefinition variableDefinition ->{
              //   Type type = getType(variableDefinition.typeExpression,table);
                // On vérifie qu'on a bien un varibleEntry
                 Entry variableEntry = table.lookup(variableDefinition.name,SplError.NotAVariable(variableDefinition.position,variableDefinition.name));
            if(!(variableEntry instanceof VariableEntry)) {
                throw SplError.NotAVariable(variableDefinition.position,variableDefinition.name);
            }
            }
            case IfStatement ifStatement -> {
                // Implémentation pour IfStatement
                Type type = getType(ifStatement.condition, table);
                // verifier si le type est boolean
                if (!type.equals(PrimitiveType.boolType)) {
                    throw SplError.IfConditionMustBeBoolean(ifStatement.position, type);
                }
                // on visite également le statement apres le then
                checkNode(ifStatement.thenPart, table);
                // Si possible on vosote égamelement celui du else
                if (ifStatement.elsePart != null) {
                    checkNode(ifStatement.elsePart, table);
                }
            }
            case NamedTypeExpression namedTypeExpression -> {
                // Implémentation pour NamedTypeExpression
                Entry entry = table.lookup(namedTypeExpression.name, SplError.UndefinedIdentifier(namedTypeExpression.position, namedTypeExpression.name));
                if (!(entry instanceof TypeEntry)) {
                    throw SplError.NotAType(namedTypeExpression.position, namedTypeExpression.name);
                }
            }
            case NamedVariable namedVariable -> {
                // Implémentation pour NamedVariable

                Entry entry = table.lookup(namedVariable.name, SplError.UndefinedIdentifier(namedVariable.position, namedVariable.name));

                if (!(entry instanceof VariableEntry)) {
                    throw SplError.NotAVariable(namedVariable.position, namedVariable.name);
                }
            }
            case ParameterDefinition parameterDefinition -> {
                Entry entry = table.lookup(parameterDefinition.name, SplError.UndefinedIdentifier(parameterDefinition.position, parameterDefinition.name));
                if (!(entry instanceof VariableEntry)) {
                    throw SplError.NotAParameter(parameterDefinition.position, parameterDefinition.name);
                }
                if (!parameterDefinition.isReference && ((VariableEntry) entry).type instanceof ArrayType) {
                    throw SplError.ParameterMustBeReference(parameterDefinition.position, parameterDefinition.name, ((VariableEntry) entry).type);
                }
            }
            case Position position -> {
                // Implémentation pour Position
            }
            case Program program -> {
                // Implémentation pour Program

                program.definitions.forEach(definition -> checkNode(definition, table));

            }
            case WhileStatement whileStatement -> {
                // Implémentation pour WhileStatement
                Type type = getType(whileStatement.condition, table);
                // On vérifie également si le type est boolean
                if (!type.equals(PrimitiveType.boolType)) {
                    throw SplError.WhileConditionMustBeBoolean(whileStatement.position, type);
                }
                // On verifie egamelement les statements du body
                checkNode(whileStatement.body, table);
            }
            case Statement statement -> {
                // Implémentation pour Statement
                checkNode(statement, table);
            }
            case TypeExpression typeExpression -> {
                checkNode(typeExpression,table);
                // Implémentation pour TypeExpression

            }
            case Variable variable -> {
                checkNode(variable,table);
                // Implémentation pour Variable
            }
            default -> throw new NotImplemented();
        }
    }

}
