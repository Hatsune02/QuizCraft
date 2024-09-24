package com.navi.quizcraftweb.backend.parser_lexer.request.objs;
import lombok.*;

@Getter @Setter
public class RequestXSON <T> {
    public static final int USUARIO_NUEVO = 1;
    public static final int MODIFICAR_USUARIO = 2;
    public static final int ELIMINAR_USUARIO = 3;
    public static final int LOGIN_USUARIO = 4;
    public static final int NUEVA_TRIVIA = 5;
    public static final int MODIFICAR_TRIVIA = 6;
    public static final int ELIMINAR_TRIVIA = 7;
    public static final int AGREGAR_COMPONENTE = 8;
    public static final int MODIFICAR_COMPONENTE = 9;
    public static final int ELIMINAR_COMPONENTE = 10;
    public static final int VER_TRIVIAS = 11;
    public static final int ADD_DATA = 12;


    public static final String[] REQUESTS = {"USUARIO_NUEVO", "MODIFICAR_USUARIO", "ELIMINAR_USUARIO",
            "LOGIN_USUARIO", "NUEVA_TRIVIA", "MODIFICAR_TRIVIA", "ELIMINAR_TRIVIA", "AGREGAR_COMPONENTE",
            "MODIFICAR_COMPONENTE", "ELIMINAR_COMPONENTE"};

    private String id, id2;
    private T data;
    private int type;
    private int line, col;

    public RequestXSON(String id, int type){
        this.id = id;
        this.type = type;
    }
    public RequestXSON(T data, int type) {
        this.data = data;
        this.type = type;
    }
    public RequestXSON(String id, T data, int type) {
        this.id = id;
        this.data = data;
        this.type = type;
    }
    public RequestXSON(String id, String id2, int type) {
        this.id = id;
        this.id2 = id2;
        this.type = type;
    }
    public void setLineCol(int line, int col){
        this.line = line;
        this.col = col;
    }








    @Override
    public String toString() {
        return "RequestXSON{" +
                "id='" + id + '\'' +
                ", id2='" + id2 + '\'' +
                ", data=" + data +
                ", type=" + type +
                '}';
    }
}
