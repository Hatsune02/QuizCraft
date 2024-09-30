package com.navi.quizcraftweb.backend.parser_lexer.request.objs;
import lombok.*;

@Getter @Setter
public class Parameter<T> {
    public static final int USER = 0;
    public static final int PASSWORD = 1;
    public static final int NAME = 2;
    public static final int INSTITUTION = 3;
    public static final int CREATE_DATE = 4;
    public static final int OLD_USER = 5;
    public static final int NEW_USER = 6;
    public static final int NEW_PASSWORD = 7;
    public static final int UPDATE_DATE = 8;
    public static final int ID_TRIVIA = 9;
    public static final int QUESTION_TIME = 10;
    public static final int CREATE_USER = 11;
    public static final int TOPIC = 12;
    public static final int ID = 13;
    public static final int TRIVIA = 14;
    public static final int CLASS = 15;
    public static final int INDEX = 16;
    public static final int VISIBLE_TEXT = 17;
    public static final int OPTIONS = 18;
    public static final int LINE = 19;
    public static final int COLUMNS = 20;
    public static final int ANSWER = 21;
    public static final String[] TYPES = {"USUARIO", "PASSWORD", "NOMBRE", "INSTITUCION",
    "FECHA_CREACION", "USUARIO_ANTIGUO", "NUEVO_USUARIO", "NUEVO_PASSWORD", "FECHA_MODIFICACION",
    "ID_TRIVIA", "TIEMPO_PREGUNTA", "USUARIO_CREACION", "TEMA", "ID", "TRIVIA", "CLASE",
    "INDICE", "TEXTO_VISIBLE", "OPCIONES", "FILAS", "COLUMNAS", "RESPUESTA",
    };

    private T parameter;
    private int type;
    private int line, column;

    public Parameter(){

    }
    public Parameter(T parameter, int type, int line, int column) {
        this.parameter = parameter;
        this.type = type;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "parameter=" + parameter +
                ", type=" + type + " ("+TYPES[type]+")"+
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    public String getTypeStr(){
        if(type <= TYPES.length) return TYPES[type];
        else return "";
    }
}
