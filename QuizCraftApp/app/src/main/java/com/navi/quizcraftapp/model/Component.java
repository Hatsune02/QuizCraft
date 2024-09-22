package com.navi.quizcraftapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Component implements Serializable {
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

    public Component(){}

    public Component(String id, String trivia, int clase, int index, String visibleText, ArrayList<String> options, int line, int columns, ArrayList<String> answer){
        this.id = id;
        this.trivia = trivia;
        this.clase = clase;
        this.index = index;
        this.visibleText = visibleText;
        this.options = options;
        this.line = line;
        this.columns = columns;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getTrivia() {
        return trivia;
    }

    public int getClase() {
        return clase;
    }

    public int getIndex() {
        return index;
    }

    public String getVisibleText() {
        return visibleText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getLine() {
        return line;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTrivia(String trivia) {
        this.trivia = trivia;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setVisibleText(String visibleText) {
        this.visibleText = visibleText;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
