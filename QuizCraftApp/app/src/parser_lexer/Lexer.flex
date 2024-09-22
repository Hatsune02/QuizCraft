package com.navi.quizcraftapp.parser_lexer;
import java_cup.runtime.*;
import static com.navi.quizcraftapp.parser_lexer.sym.*;
%% //separador de area

%public
%class Lexer
%cup
%line
%column

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* ___Reserved words___ */
db_user = (db\.user)
db_trivia = (db\.trivia)
envio_respuesta = (envio_respuesta)
fin_envio_respuesta = (fin_envio_respuesta)
    /* parameters */

usuario = (\"USUARIO\")
password = (\"PASSWORD\")
insitucion = (\"INSTITUCION\")
nombre = (\"NOMBRE\")
fecha_creacion = (\"FECHA_CREACION\")
fecha_modificacion = (\"FECHA_MODIFICACION\")

id_trivia = (\"ID_TRIVIA\")
tiempo_pregunta = (\"TIEMPO_PREGUNTA\")
usuario_creacion = (\"USUARIO_CREACION\")
tema = (\"TEMA\")

id = (\"ID\")
trivia = (\"TRIVIA\")
clase = (\"CLASE\")
indice = (\"INDICE\")
texto_visible = (\"TEXTO_VISIBLE\")
opciones = (\"OPCIONES\")
filas = (\"FILAS\")
columnas = (\"COLUMNAS\")
respuesta = (\"RESPUESTA\")

campo_texto = (\"CAMPO_TEXTO\")
area_texto = (\"AREA_TEXTO\")
checkbox = (\"CHECKBOX\")
radio = (\"RADIO\")
fichero = (\"FICHERO\")
combo = (\"COMBO\")
combo = (\"NONE\")

estructura = (\"ESTRUCTURA\")
datos_recopilados = (\"DATOS_RECOPILADOS\")

tiempo_total = (\"TIEMPO_TOTAL\")
punteo = (\"PUNTEO\")
estado = (\"ESTADO\")

/* Structures */

LBrace = [\{]
RBrace = [\}]
LParen = [\(]
RParen = [\)]
Colon = [:]
Comma = [,]
LThan = [<]
GThan = [>]
EX = [!]

/* Strings */

Q = [\"]
StringContent = [^\"]+
String = {Q}{StringContent}{Q}

/* Others */
Integer = [0-9]+


%{
    private Symbol symbol(int type){
        return new Symbol(type, yyline+1,yycolumn+1);
    }
    private Symbol symbol(int type, Object value){
        //System.out.println(type + " line: " + (yyline+1) + " col: "+(yycolumn+1) + " " + value);
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
    private void error(){
        //ErrorsLP.addError(yytext(), yyline+1, yycolumn+1, "Error Léxico","Cadena no definida");
    }
%}

%%
{envio_respuesta}
{return symbol(ENVIO_RESPUESTA, yytext());          }
{fin_envio_respuesta}
{return symbol(FIN_ENVIO_RESPUESTA, yytext());          }

{usuario}
{return symbol(USUARIO, yytext());          }
{password}
{return symbol(PASSWORD, yytext());          }
{insitucion}
{return symbol(INSTITUCION, yytext());          }
{nombre}
{return symbol(NOMBRE, yytext());          }
{fecha_creacion}
{return symbol(FECHA_CREACION, yytext());          }
{fecha_modificacion}
{return symbol(FECHA_MODIFICACION, yytext());          }


{id_trivia}
{return symbol(ID_TRIVIA, yytext());          }
{tiempo_pregunta}
{return symbol(TIEMPO_PREGUNTA, yytext());          }
{usuario_creacion}
{return symbol(USUARIO_CREACION, yytext());          }
{tema}
{return symbol(TEMA, yytext());          }

{id}
{return symbol(ID, yytext());}
{trivia}
{return symbol(TRIVIA, yytext());}
{clase}
{return symbol(CLASE, yytext());}
{indice}
{return symbol(INDICE, yytext());}
{texto_visible}
{return symbol(TEXTO_VISIBLE, yytext());}
{opciones}
{return symbol(OPCIONES, yytext());}
{filas}
{return symbol(FILAS, yytext());}
{columnas}
{return symbol(COLUMNAS, yytext());}
{respuesta}
{return symbol(RESPUESTA, yytext());}

{campo_texto}
{return symbol(CAMPO_TEXTO, yytext());}
{area_texto}
{return symbol(AREA_TEXTO, yytext());}
{checkbox}
{return symbol(CHECKBOX, yytext());}
{radio}
{return symbol(RADIO, yytext());}
{fichero}
{return symbol(FICHERO, yytext());}
{combo}
{return symbol(COMBO, yytext());}

{estructura}
{return symbol(ESTRUCTURA, yytext());}
{datos_recopilados}
{return symbol(DATOS_RECOPILADOS, yytext());}
{tiempo_total}
{return symbol(TIEMPO_TOTAL, yytext());}
{estado}
{return symbol(ESTADO, yytext());}
{punteo}
{return symbol(PUNTEO, yytext());}


{LBrace}
{return symbol(LBRACE, yytext());}
{RBrace}
{return symbol(RBRACE, yytext());}
{LParen}
{return symbol(LPAREN, yytext());}
{RParen}
{return symbol(RPAREN, yytext());}
{Colon}
{return symbol(COLON, yytext());}
{Comma}
{return symbol(COMMA, yytext());}
{LThan}
{return symbol(LT, yytext());}
{GThan}
{return symbol(GT, yytext());}
{EX}
{return symbol(EX, yytext());}


{Integer}
{return symbol(DIGIT, Integer.parseInt(yytext()));}
{String}
{return symbol(STRING, yytext());}


{WhiteSpace} { /* ignore */ }

[\^°¬¡¿\w]+
{}

[^]                 {error(); }


<<EOF>>             {return symbol(EOF); }