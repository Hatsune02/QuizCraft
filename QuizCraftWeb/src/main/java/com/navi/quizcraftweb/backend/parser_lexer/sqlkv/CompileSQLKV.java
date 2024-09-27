package com.navi.quizcraftweb.backend.parser_lexer.sqlkv;

import com.navi.quizcraftweb.backend.dao.TriviaDAO;
import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestLexer;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestParser;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.obj.Query;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

public class CompileSQLKV {
    private static Reader reader;
    private static SqlLexer lexer;
    private static SqlParser parser;

    public static void compile(String text){
        reader = new StringReader(text);
        lexer = new SqlLexer(reader);
        parser = new SqlParser(lexer);
        try{
            ErrorsLP.clearErrors();
            parser.parse();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(ErrorsLP.getErrors().isEmpty()){
            Query.reports.forEach(System.out::println);
        }
        else{
            ErrorsLP.getErrors().forEach(System.out::println);
        }
    }
}
