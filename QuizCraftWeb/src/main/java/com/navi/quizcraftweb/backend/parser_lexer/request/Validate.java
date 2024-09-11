package com.navi.quizcraftweb.backend.parser_lexer.request;

import java.util.HashMap;

public class Validate {
    private static HashMap<String, String> SYMBOLNAMES;

    public static HashMap<String, String> getSymbolNames(){
        if(SYMBOLNAMES == null) {
            SYMBOLNAMES = new HashMap<>();
            SYMBOLNAMES.put("COMMA", ",");
            SYMBOLNAMES.put("LPAREN", "(");
            SYMBOLNAMES.put("RPAREN", ")");
            SYMBOLNAMES.put("PLUS", "+");
            SYMBOLNAMES.put("MINUS", "-");
            SYMBOLNAMES.put("DIVISION", "/");
            SYMBOLNAMES.put("TIMES", "*");
            SYMBOLNAMES.put("LINE", "línea");
            SYMBOLNAMES.put("CIRCLE", "circulo");
            SYMBOLNAMES.put("SQUARE", "cuadrado");
            SYMBOLNAMES.put("RECTANGLE", "rectángulo");
            SYMBOLNAMES.put("POLYGON", "poligono");
            SYMBOLNAMES.put("GRAFICAR", "graficar");
            SYMBOLNAMES.put("ANIMAR", "animar");
            SYMBOLNAMES.put("OBJETO", "objeto");
            SYMBOLNAMES.put("ANTERIOR", "anterior");
            SYMBOLNAMES.put("CURVE", "curva");
            SYMBOLNAMES.put("BLUE", "color");
            SYMBOLNAMES.put("RED", "color");
            SYMBOLNAMES.put("YELLOW", "color");
            SYMBOLNAMES.put("GREEN", "color");
            SYMBOLNAMES.put("SKY", "color");
            SYMBOLNAMES.put("CYAN", "color");
            SYMBOLNAMES.put("BLACK", "color");
            SYMBOLNAMES.put("PINK", "color");
            SYMBOLNAMES.put("PURPLE", "color");
            SYMBOLNAMES.put("ID", "Un nombre válido");
            SYMBOLNAMES.put("DIGIT", "Un número");
        }
        return SYMBOLNAMES;
    }
}
