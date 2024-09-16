package com.navi.quizcraftweb.backend.model;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.Parameter;
import lombok.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Getter @Setter
public class Component {
    public static final int CAMPO_TEXTO = 1;
    public static final int AREA_TEXTO = 2;
    public static final int CHECKBOX = 3;
    public static final int RADIO = 4;
    public static final int FICHERO = 5;
    public static final int COMBO = 6;
    public static final String[] CLASS_CONTENT = {"CAMPO_TEXTO", "AREA_TEXTO", "CHECKBOX", "RADIO", "FICHERO", "COMBO"};

    private String id;
    private String trivia;
    private int clase;
    private int index;
    private String visibleText;
    private ArrayList<String> options = new ArrayList<>();
    private int line, columns;
    private ArrayList<String> answer = new ArrayList<>();

    public Component() {}

    public Component(String id, String trivia, int clase, int index, String visibleText, ArrayList<String> answer) {
        this.id = id;
        this.trivia = trivia;
        this.clase = clase;
        this.index = index;
        this.visibleText = visibleText;
        this.answer = answer;
    }

    public void setNewComponent(HashMap<Integer, Parameter> parameters, boolean hasOptions, boolean  hasLine, boolean hasColumns){
        id = (String) parameters.get(Parameter.ID).getParameter();
        trivia = (String) parameters.get(Parameter.TRIVIA).getParameter();
        clase = (Integer) parameters.get(Parameter.CLASS).getParameter();
        visibleText = (String) parameters.get(Parameter.VISIBLE_TEXT).getParameter();
        answer = (ArrayList<String>) parameters.get(Parameter.ANSWER).getParameter();
        if(hasOptions) options = (ArrayList<String>) parameters.get(Parameter.OPTIONS).getParameter();
        if(hasLine) line = (Integer) parameters.get(Parameter.LINE).getParameter();
        if(hasColumns) columns = (Integer) parameters.get(Parameter.COLUMNS).getParameter();
    }

    public void setUpdateComponent(HashMap<Integer, Parameter> parameters, boolean hasClass, boolean hasVisibleText, boolean hasAnswer,boolean hasOptions, boolean  hasLine, boolean hasColumns){
        id = (String) parameters.get(Parameter.ID).getParameter();
        trivia = (String) parameters.get(Parameter.TRIVIA).getParameter();
        index = (int) parameters.get(Parameter.INDEX).getParameter();
        if(hasClass) clase = (Integer) parameters.get(Parameter.CLASS).getParameter();
        if(hasVisibleText) visibleText = (String) parameters.get(Parameter.VISIBLE_TEXT).getParameter();
        if(hasAnswer) answer = (ArrayList<String>) parameters.get(Parameter.ANSWER).getParameter();
        if(hasOptions) options = (ArrayList<String>) parameters.get(Parameter.OPTIONS).getParameter();
        if(hasLine) line = (Integer) parameters.get(Parameter.LINE).getParameter();
        if(hasColumns) columns = (Integer) parameters.get(Parameter.COLUMNS).getParameter();

    }

    @Override
    public String toString() {
        return "Component{" +
                "id='" + id + '\'' +
                ", trivia='" + trivia + '\'' +
                ", clase=" + clase +
                ", index=" + index +
                ", visibleText='" + visibleText + '\'' +
                ", options='" + options + '\'' +
                ", line=" + line +
                ", columns=" + columns +
                ", answer=" + answer +
                '}';
    }

    public String dbString(){
        return "\t{\n" +
                "\t\t\"ID\":\"" + id + "\",\n"+
                "\t\t\"TRIVIA\":\"" + trivia + "\",\n"+
                "\t\t\"CLASE\":\"" + dbClass() + "\",\n"+
                "\t\t\"INDICE\":" + index + ",\n"+
                "\t\t\"TEXTO_VISIBLE\":\"" + visibleText + "\",\n"+
                "\t\t\"OPCIONES\":\"" + dbOptions() + "\",\n"+
                "\t\t\"FILAS\":" + line + ",\n"+
                "\t\t\"COLUMNAS\":" + columns + ",\n"+
                "\t\t\"RESPUESTA\":\"" + dbAnswer() + "\"\n"+
                "\t}"
                ;
    }
    public String dbOptions(){
        if(options.isEmpty()) return "null";

        StringBuilder text = new StringBuilder();
        for (String option : options) {
            text.append(option).append("|");
        }

        return text.substring(0, text.length() - 1);
    }
    public String dbAnswer(){
        if(answer.isEmpty()) return "null";

        StringBuilder text = new StringBuilder();
        for (String answer : answer) {
            text.append(answer).append("|");
        }

        return text.substring(0, text.length() - 1);
    }
    public String dbClass(){
        if(clase!=0){
            return CLASS_CONTENT[clase-1];
        }
        return "NONE";
    }
}
