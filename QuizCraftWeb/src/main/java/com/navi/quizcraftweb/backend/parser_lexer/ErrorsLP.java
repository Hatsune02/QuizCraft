package com.navi.quizcraftweb.backend.parser_lexer;

import java.util.ArrayList;
import lombok.*;

public class ErrorsLP {
    @Getter
    private static ArrayList<TError> errors = new ArrayList<>();

    public static void addError(String lexeme, int line, int col, String type, String description){
        errors.add(new TError(lexeme, line, col, type, description));
    }

    public static void clearErrors(){
        errors.clear();
    }
}
