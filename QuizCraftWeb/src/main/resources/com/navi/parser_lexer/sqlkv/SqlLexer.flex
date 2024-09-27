package com.navi.quizcraftweb.backend.parser_lexer.sqlkv;
import java_cup.runtime.*;
import static com.navi.quizcraftweb.backend.parser_lexer.sqlkv.sym.*;
import com.navi.quizcraftweb.backend.parser_lexer.*;

%% //separador de area

%public
%class SqlLexer
%cup
%line
%column


LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* Reserved words*/
seleccionar = (SELECCIONAR)
reporte = (REPORTE)
filtrar = (FILTRAR)
por = (POR)
usuario = (USUARIO)
tiempo_total = (TIEMPO_TOTAL)
punteo = (PUNTEO)
/* Operators */
RelatedOperations = "<=" | ">=" | "=" | "<" | ">"
and = (AND)
or = (OR)

/* Numbers */
Digit = [0-9]+
Decimal = {Digit}\.{Digit}

/* Strings */
Identifier = [-_$][-_$a-zA-Z0-9]+
Q = [\"]
q = [\']
StringContent = [^\"\'\\\\]*[-_$a-zA-Z][^\"\'\\\\]*
String = {Q}{StringContent}{Q} | {q}{StringContent}{q}


/* Structures */
Comma = [,]

%{
    private Symbol symbol(int type){
        return new Symbol(type, yyline+1,yycolumn+1);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
    private void error(){
        ErrorsLP.addError(yytext(), yyline+1, yycolumn+1, "Error Léxico","Caracter desconocido");
    }
%}

%%
//Reglas lexicas

{seleccionar}
{return symbol(SELECCIONAR, yytext());}
{reporte}
{return symbol(REPORTE, yytext());}
{filtrar}
{return symbol(FILTRAR, yytext());}
{por}
{return symbol(POR, yytext());}
{usuario}
{return symbol(USUARIO, yytext());}
{tiempo_total}
{return symbol(TIEMPO_TOTAL, yytext());}
{punteo}
{return symbol(PUNTEO, yytext());}

{Digit}
{return symbol(DIGIT, yytext());}
{Decimal}
{return symbol(DIGIT, yytext());}

{Identifier}
{return symbol(ID, yytext());}
{String}
{return symbol(STRING, yytext());}
{RelatedOperations}
{return symbol(REL_OP, yytext());}
{and}
{return symbol(AND, yytext());}
{or}
{return symbol(OR, yytext());}

{Comma}
{return symbol(COMMA, yytext());}

{WhiteSpace}            { /**/ }

[\^´°¬|!$%&?¡¿\w]+
{ErrorsLP.addError(yytext(), yyline+1, yycolumn+1, "Error Léxico","Cadena no definida");}
[^]                 {error(); }


<<EOF>>             {return symbol(EOF); }