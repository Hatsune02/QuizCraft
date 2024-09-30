package com.navi.quizcraftweb.backend.parser_lexer.sqlkv;

import java.util.HashMap;

public class Validate {
    private static HashMap<String, String> SYMBOLNAMES;

    public static HashMap<String, String> getSymbolNames(){
        if(SYMBOLNAMES == null) {
            SYMBOLNAMES = new HashMap<>();
            SYMBOLNAMES.put("COMMA", ",");
            SYMBOLNAMES.put("ID", "Identificador de trivia");
            SYMBOLNAMES.put("DIGIT", "Número");

            SYMBOLNAMES.put("STRING", "Cadena de texto valida");
            SYMBOLNAMES.put("REL_OP", "<=' o '>=' o '=' o '<' o '>");

        }
        return SYMBOLNAMES;
    }
}
