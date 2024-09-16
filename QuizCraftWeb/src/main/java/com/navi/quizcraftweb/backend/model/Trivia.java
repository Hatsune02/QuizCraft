package com.navi.quizcraftweb.backend.model;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.Parameter;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Getter @Setter
public class Trivia {
    private String idTrivia;
    private String name;
    private String topic;
    private int questionTime;
    private String createUser;
    private Date createDate;
    //private Date updateDate;

    private int amountOfComponents;
    //private HashMap<String, Component> components;
    private ArrayList<Component> components;
    private ArrayList<CollectedData> collectedData;
    private ArrayList<String> answers;

    public Trivia(){
        components = new ArrayList<>();
        collectedData = new ArrayList<>();
    }

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
    public Trivia(String idTrivia, String name, String topic, int questionTime, String createUser){
        this.idTrivia = idTrivia;
        this.name = name;
        this.questionTime = questionTime;
        this.createUser = createUser;
        this.topic = topic;
        this.createDate = new Date();
        components = new ArrayList<>();
        collectedData = new ArrayList<>();
    }
    public void setCreateDateString(String d) {
        createDate = ModelUtils.stringToDate(d);
    }
    public String getCreateDateString() {
        return ModelUtils.dateToString(createDate);
    }

    public void setNewTrivia(HashMap<Integer, Parameter> parameters, boolean hasCreateUser, boolean hasDate){
        idTrivia = (String) parameters.get(Parameter.ID_TRIVIA).getParameter();
        name = (String) parameters.get(Parameter.NAME).getParameter();
        questionTime = (int) parameters.get(Parameter.QUESTION_TIME).getParameter();
        topic = (String) parameters.get(Parameter.TOPIC).getParameter();
        if(hasCreateUser) createUser = (String) parameters.get(Parameter.CREATE_USER).getParameter();
        if(hasDate) createDate = ModelUtils.stringToDate((String) parameters.get(Parameter.CREATE_DATE).getParameter());
        else  createDate = new Date();
    }

    public void setUpdateTrivia(HashMap<Integer, Parameter> parameters, boolean hasQuestionTime, boolean hasName, boolean hasTopic){
        idTrivia = (String) parameters.get(Parameter.ID_TRIVIA).getParameter();
        if(hasQuestionTime) questionTime = (int) parameters.get(Parameter.QUESTION_TIME).getParameter();
        if(hasName) name = (String) parameters.get(Parameter.NAME).getParameter();
        if(hasTopic) topic = (String) parameters.get(Parameter.TOPIC).getParameter();
    }

    public void addComponent(Component component){
        component.setIndex(components.size()+1);
        components.add(component);
    }

    public void addData(CollectedData data){
        collectedData.add(data);
    }

    public void resetIndexes(){
        for (int i = 0; i < components.size(); i++) {
            components.get(i).setIndex(i+1);
        }
    }

    public void reOrderComponents(int actualPosition, int newPosition){
        if(newPosition >= components.size()) newPosition = components.size()-1;
        Component component = components.remove(actualPosition-1);
        components.add(newPosition-1, component);
        resetIndexes();
    }

    public void removeComponent(int index){
        components.remove(index);
        resetIndexes();
    }

    @Override
    public String toString() {
        return "Trivia{" +
                "idTrivia='" + idTrivia + '\'' +
                ", name='" + name + '\'' +
                ", questionTime=" + questionTime +
                ", createUser='" + createUser + '\'' +
                ", topic='" + topic + '\'' +
                ", createDate=" + ModelUtils.dateToString(createDate) +
                ", amountOfComponents=" + amountOfComponents +
                ", components=" + components +
                '}';
    }

    public String dbString(){
        return "{\n" +
                "\t\"ID_TRIVIA\":\"" + idTrivia + "\",\n"+
                "\t\"NOMBRE\":\"" + name + "\",\n"+
                "\t\"TEMA\":\"" + topic + "\",\n"+
                "\t\"TIEMPO_PREGUNTA\":" + questionTime + ",\n"+
                "\t\"USUARIO_CREACION\":\"" + createUser + "\",\n"+
                "\t\"FECHA_CREACION\":\"" + ModelUtils.dateToString(createDate) + "\",\n"+
                "\t\"ESTRUCTURA\":(\n" + dbComponents() +
                "\t),\n" +
                "\t\"DATOS_RECOPILADOS\":(\n" + dbCollectedData() +
                "\t)\n" +
                "}"
                ;
    }

    public String dbComponents(){
        if(components.isEmpty()){
            return "";
        }
        StringBuilder text = new StringBuilder();

        for (Component component : components) {
            text.append(component.dbString()).append("\n\t,\n");
        }
        return text.substring(0, text.length() - 3);
    }
    public String dbCollectedData(){
        if(collectedData.isEmpty()){
            return "";
        }
        StringBuilder text = new StringBuilder();

        for (CollectedData data : collectedData) {
            text.append(data.dbString()).append("\n\t,\n");
        }
        return text.substring(0, text.length() - 3);
    }
}
