package com.navi.quizcraftapp.parser_lexer;

import java.io.*;
import java.util.ArrayList;

import com.navi.quizcraftapp.model.*;


public class Compile {
    private static Parser parser;

    private static void compile (String text){
        try{
            Reader reader = new StringReader(text);
            Lexer lexer = new Lexer(reader);
            parser = new Parser(lexer);
            try{
                parser.parse();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public static User getUser(String text){
        compile(text);
        return parser.user;
    }
    public static ArrayList<Trivia> getTrivias(String text){
        compile(text);
        return parser.trivias;
    }
}
