package com.navi.quizcraftapp.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestTrivias {
    public static ArrayList<Trivia> trivias = new ArrayList<>();
    public static void initializateTrivias(){
        Trivia trivia1 = new Trivia("trivia1", "nombre Trivia", "Topic1", 10, "yo", "2004-08-08");
        Trivia trivia2 = new Trivia("trivia2", "nombre Trivia2", "Topic2", 100, "yo", "2004-08-08");
        ArrayList<String> options1 = new ArrayList<>();
        ArrayList<String> options2 = new ArrayList<>();
        ArrayList<String> options3 = new ArrayList<>();
        ArrayList<String> optionsNull = new ArrayList<>();
        ArrayList<String> answer1 = new ArrayList<>();
        ArrayList<String> answer2 = new ArrayList<>();
        ArrayList<String> answer3 = new ArrayList<>();
        ArrayList<String> answer4 = new ArrayList<>();
        ArrayList<String> answer5 = new ArrayList<>();

        options1.add("Papaya");
        options1.add("Pera");
        options1.add("Melon");

        options2.add("Luka");
        options2.add("Miku");
        options2.add("Rin");

        options3.add("Ribon");
        options3.add("Lila");
        options3.add("Frambuesa");


        answer1.add("Gatos");
        answer2.add("Perros no");
        answer3.add("Melon");
        answer4.add("Miku");
        answer5.add("Ribon");


        Component component1 = new Component("comp1","trivia1",Component.CAMPO_TEXTO, 1, "texto visible 1", optionsNull, 0, 0, answer1);
        Component component2 = new Component("comp2","trivia1",Component.AREA_TEXTO, 2, "texto visible 2", optionsNull, 3, 10, answer2);
        Component component3 = new Component("comp3","trivia1",Component.CHECKBOX, 3, "texto visible 3", options1, 0, 0, answer3);
        Component component4 = new Component("comp4","trivia1",Component.RADIO, 4, "texto visible 4", options2, 0, 0, answer4);
        Component component5 = new Component("comp5","trivia1",Component.COMBO, 5, "texto visible 5", options3, 0, 0, answer5);

        trivia1.addComponent(component1);
        trivia1.addComponent(component2);
        trivia1.addComponent(component3);
        trivia1.addComponent(component4);
        trivia1.addComponent(component5);

        trivias.add(trivia1);
        trivias.add(trivia2);
    }
}
