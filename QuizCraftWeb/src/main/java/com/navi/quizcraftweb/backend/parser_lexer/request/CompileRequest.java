package com.navi.quizcraftweb.backend.parser_lexer.request;

import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

public class CompileRequest {
    private static Reader reader;
    private static RequestLexer lexer;
    private static RequestParser parser;
    public static ArrayList<RequestXSON> requests;

    public static void Compile(String text){
        reader = new StringReader(text);
        lexer = new RequestLexer(reader);
        parser = new RequestParser(lexer);
        requests = new ArrayList<>();
        try{
            ErrorsLP.clearErrors();
            parser.parse();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(ErrorsLP.getErrors().isEmpty()){
            requests = parser.requests;
        }
    }
}
