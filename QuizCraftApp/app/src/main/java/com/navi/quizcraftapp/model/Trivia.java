package com.navi.quizcraftapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Trivia implements Serializable {
    private String idTrivia;
    private String name;
    private String topic;
    private int questionTime;
    private String createUser;
    private Date createDate;
    //private Date updateDate;
    private ArrayList<Component> components;
    private ArrayList<CollectedData> collectedData;

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

    public ArrayList<Component> getComponents() {
        return components;
    }

    public ArrayList<CollectedData> getCollectedData() {
        return collectedData;
    }


    public String getDate(){
        return ModelUtils.dateToString(createDate);
    }

    public void setIdTrivia(String idTrivia) {
        this.idTrivia = idTrivia;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setQuestionTime(int questionTime) {
        this.questionTime = questionTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component){
        this.components.add(component);
    }

    public void setCollectedData(ArrayList<CollectedData> collectedData) {
        this.collectedData = collectedData;
    }
}
