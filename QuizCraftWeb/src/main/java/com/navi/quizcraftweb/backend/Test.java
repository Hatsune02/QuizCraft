package com.navi.quizcraftweb.backend;

import com.navi.quizcraftweb.backend.dao.Connection;
import com.navi.quizcraftweb.backend.dao.TriviaDAO;
import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.model.CollectedData;
import com.navi.quizcraftweb.backend.model.Component;
import com.navi.quizcraftweb.backend.model.Trivia;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.TError;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestLexer;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.CompileSQLKV;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.SqlLexer;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.SqlParser;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String text = """
                terminal SELECCIONAR, REPORTE, FILTRAR, POR, USUARIO,
                        TIEMPO_TOTAL, PUNTEO, COMMA, AND, OR;
                
                terminal String ID, DIGIT, STRING, REL_OP, ERROR;
                
                
                non terminal instruction;
                
                non terminal String expr;
                
                non terminal Integer reserved_word;
                
                non terminal ArrayList<String> listC;
                
                non terminal Condition condition;
                
                non terminal ArrayList<Condition> conditions, filter;
                
                non terminal s;
                
                
                start with s;
                
                s ::= instruction;
                
                instruction ::= SELECCIONAR REPORTE
                                {:
                                if(ErrorsLP.getErrors().isEmpty()) Query.selectAll();
                                :}
                                | SELECCIONAR REPORTE listC:i
                                {:
                                if(ErrorsLP.getErrors().isEmpty()) Query.select(i);
                                :}
                                | SELECCIONAR REPORTE filter:f
                                {:
                                if(ErrorsLP.getErrors().isEmpty()) Query.selectAll(f);
                                :}
                                | SELECCIONAR REPORTE listC:i filter:f
                                {:
                                if(ErrorsLP.getErrors().isEmpty()) Query.select(i, f);
                                :};
                
                filter ::= FILTRAR POR conditions:c
                            {: RESULT = c; :}
                            |FILTRAR POR error
                            ;
                
                conditions ::= condition:c
                            {:
                            ArrayList<Condition> conditions = new ArrayList<>();
                            conditions.add(c);
                            RESULT = conditions;
                            :}
                            | conditions:cs AND condition:c
                            {:
                            c.setType(Condition.AND);
                            cs.add(c);
                            RESULT = cs;
                            :}
                            | conditions:cs OR condition:c
                            {:
                            c.setType(Condition.OR);
                            cs.add(c);
                            RESULT = cs;
                            :}
                            | conditions AND error
                            | conditions OR error
                            ;
                
                condition ::= reserved_word:f REL_OP:s expr:v
                            {:
                            RESULT = new Condition(f,s,v);
                            :}
                            | reserved_word REL_OP error
                            ;
                
                listC       ::= ID:id
                            {:
                            ArrayList<String> trivias = new ArrayList<>();
                            trivias.add(id);
                            RESULT = trivias;
                            :}
                            | listC:t COMMA ID:id
                            {:
                            t.add(id);
                            RESULT = t;
                            :}
                            | error
                            | listC error
                            ;
                reserved_word ::= USUARIO           {: RESULT = Condition.USUARIO;:}
                                | TIEMPO_TOTAL      {: RESULT = Condition.TIEMPO_TOTAL;:}
                                | PUNTEO            {: RESULT = Condition.PUNTEO;:}
                                | reserved_word error
                                ;
                
                expr        ::= DIGIT:d {:RESULT = d;:}
                            | STRING:s {:RESULT = s.toString().replace("\\"","");:}
                            | expr error
                            ;
                
                
                """;

        String output = text.replaceAll("\\{[^\\}]*\\}", "");

        System.out.println(output);
    }
}
