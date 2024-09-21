package com.navi.quizcraftapp.model;

import java.util.ArrayList;
import java.util.Date;

public class Trivia {
    private String idTrivia;
    private String name;
    private String topic;
    private int questionTime;
    private String createUser;
    private Date createDate;
    //private Date updateDate;

    private int amountOfComponents;
    private ArrayList<Component> components;
    private ArrayList<CollectedData> collectedData;
    private ArrayList<String> answers;

    public Trivia(String idTrivia, String name, String topic, int questionTime, String createUser ,String date){
        this.idTrivia = idTrivia;
        this.name = name;
        this.questionTime = questionTime;
        this.createUser = createUser;
        this.topic = topic;
        this.createDate = ModelUtils.stringToDate(date);
        components = new ArrayList<>();
        collectedData = new ArrayList<>();
    }

    public Trivia(String idTrivia, String name, String topic, int questionTime, String createUser ,String date, ArrayList<Component> components, ArrayList<CollectedData> collectedData){
        this.idTrivia = idTrivia;
        this.name = name;
        this.questionTime = questionTime;
        this.createUser = createUser;
        this.topic = topic;
        this.createDate = ModelUtils.stringToDate(date);
        this.components = components;
        this.collectedData = collectedData;
    }

    public String getIdTrivia() {
        return idTrivia;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public int getQuestionTime() {
        return questionTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getAmountOfComponents() {
        return amountOfComponents;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
