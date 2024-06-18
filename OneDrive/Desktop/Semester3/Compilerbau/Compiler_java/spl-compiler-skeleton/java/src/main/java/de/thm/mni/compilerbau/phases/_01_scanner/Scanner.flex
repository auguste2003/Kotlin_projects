package de.thm.mni.compilerbau.phases._01_scanner;

import de.thm.mni.compilerbau.utils.SplError;
import de.thm.mni.compilerbau.phases._02_03_parser.Sym;
import de.thm.mni.compilerbau.absyn.Position;
import de.thm.mni.compilerbau.table.Identifier;
import de.thm.mni.compilerbau.CommandLineOptions;
import java_cup.runtime.*;

%%


%class Scanner
%public
%line
%column
%cup

%eofval{
    return new java_cup.runtime.Symbol(Sym.EOF, yyline + 1, yycolumn + 1);   //This needs to be specified when using a custom sym class name
%eofval}

// Méthodes d'assistance pour créer des symboles

%{
    public CommandLineOptions options = null ;
private Symbol symbol(int type) {
return new Symbol(type, yyline + 1, yycolumn + 1);
}
private Symbol symbol(int type, Object value) {
return new Symbol(type, yyline + 1, yycolumn + 1, value);
}
%}

// Définitions de classes de caractères

D=[0-9]+
L=[a-zA-Z]
H=[0-9A-Fa-f]
ID={L}({L}|{D})*
DECNUM={D}+
HEXNUM=0x{H}+

%%


/* Mots-clés */
// Schlüßelwörter
var      { return symbol(Sym.VAR); }
type     { return symbol(Sym.TYPE); }
proc     { return symbol(Sym.PROC); }
array    { return symbol(Sym.ARRAY); }
else    { return symbol(Sym.ELSE); }
while    { return symbol(Sym. WHILE); }
ref   { return symbol(Sym.REF); }
if    { return symbol(Sym.IF); }
of    { return symbol(Sym.OF); }
ref    { return symbol(Sym.REF); }

// Operatoren
"<"       { return symbol(Sym.LT); }
"#"       { return symbol(Sym.NE); }
":="       { return symbol(Sym.ASGN); }
"+"       { return symbol(Sym.PLUS); }
"/"       { return symbol(Sym.SLASH); }
"*"       { return symbol(Sym.STAR); }
">"       { return symbol(Sym.GT); }
"<="      { return symbol(Sym.LE); }
"-"       { return symbol(Sym.MINUS); }
">="      { return symbol(Sym.GE); }

// DiKlammern
"("       { return symbol(Sym.LPAREN); }
")"       { return symbol(Sym.RPAREN); }
"["       { return symbol(Sym.LBRACK); }
"]"       { return symbol(Sym.RBRACK); }
"{"       { return symbol(Sym.LCURL); }
"}"       { return symbol(Sym.RCURL); }

  // '{L}|{D}|{H}|{ID}|.|\n| '  {return symbol(Sym.EQ);}
 "=" { return symbol(Sym.EQ); }

// Eingabeende-token

":"                       { return symbol(Sym.COLON); }
";"                       { return symbol(Sym.SEMIC); }
","                 {return symbol(Sym.COMMA);}



// ... autres mots-clés

/* Identificateurs */

// SPL Bezeichner + numerische Zahlen + Hexazahlen + Charakter
[a-zA-Z_][\w]* { return symbol(Sym.IDENT, new Identifier(yytext())); }  // Identificateurs  // [\w]* est [a-zA-Z0-9_]
   // Nombres négatifs
[\d]+ { return symbol(Sym.INTLIT, Integer.parseInt(yytext())); }         // Nombres décimaux positifs
0[xX][\dA-Fa-f]+ { return symbol(Sym.INTLIT, Integer.parseInt(yytext().substring(2), 16)); }  // Hexadécimaux
'.' { return symbol(Sym.INTLIT, (int)(yytext().charAt(1))); }



'\\n' { return symbol(Sym.INTLIT, (int)('\n')); }


//’   { return symbol(Sym.INTLIT); }
//.   { return symbol(Sym.INTLIT); }
//\n  { return symbol(Sym.INTLIT); }
/* Nombres entiers */



/* Espaces blancs */
 [ \t\n\r]             { /* ignore les espaces blancs */ }

/* Commentaires */
"//"[^\n]*            { /* ignore les commentaires sur une ligne */ }

[^]		{throw SplError.LexicalError(new Position(yyline + 1, yycolumn + 1), yytext().charAt(0));}




