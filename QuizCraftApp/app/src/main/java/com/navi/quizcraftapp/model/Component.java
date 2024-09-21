package com.navi.quizcraftapp.model;

import java.util.ArrayList;

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

    public Component(){}
}
