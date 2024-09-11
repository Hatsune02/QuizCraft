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
    private int questionTime;
    private String createUser;
    private String topic;
    private Date createDate;
    //private Date updateDate;

    private int amountOfComponents;
    private HashMap<String, Component> components;
    //private ArrayList<Component> components;

    public Trivia(){

    }

    public void setNewTrivia(HashMap<Integer, Parameter> parameters, boolean hasCreateUser, boolean hasDate){
        idTrivia = (String) parameters.get(Parameter.ID_TRIVIA).getParameter();
        name = (String) parameters.get(Parameter.NAME).getParameter();
        questionTime = (int) parameters.get(Parameter.QUESTION_TIME).getParameter();
        topic = (String) parameters.get(Parameter.TOPIC).getParameter();
        if(hasCreateUser) createUser = (String) parameters.get(Parameter.CREATE_USER).getParameter();
        if(hasDate) createDate = (Date) parameters.get(Parameter.CREATE_DATE).getParameter();
    }

    public void setUpdateTrivia(HashMap<Integer, Parameter> parameters, boolean hasQuestionTime, boolean hasName, boolean hasTopic){
        idTrivia = (String) parameters.get(Parameter.ID_TRIVIA).getParameter();
        if(hasQuestionTime) questionTime = (int) parameters.get(Parameter.QUESTION_TIME).getParameter();
        if(hasName) name = (String) parameters.get(Parameter.NAME).getParameter();
        if(hasTopic) topic = (String) parameters.get(Parameter.TOPIC).getParameter();
    }

    @Override
    public String toString() {
        return "Trivia{" +
                "idTrivia='" + idTrivia + '\'' +
                ", name='" + name + '\'' +
                ", questionTime=" + questionTime +
                ", createUser='" + createUser + '\'' +
                ", topic='" + topic + '\'' +
                ", createDate=" + createDate +
                ", amountOfComponents=" + amountOfComponents +
                ", components=" + components +
                '}';
    }

    public String dbString(){
        return "{\n" +
                "\t\"ID_TRIVIA\":\"" + idTrivia + "\",\n"+
                "\t\"NOMBRE\":\"" + idTrivia + "\",\n"+
                "\t\"TIEMPO_PREGUNTA\":\"" + idTrivia + "\",\n"+
                "\t\"USUARIO_CREACION\":\"" + idTrivia + "\",\n"+
                "\t\"TEMA\":\"" + idTrivia + "\",\n"+
                "\t\"FECHA_CREACION\":\"" + idTrivia + "\",\n"+
                "\t\"ESTRUCTURA\":(\n" + idTrivia +
                "\t)\n" +
                "\t\"DATOS_RECOPILADOS\":(\n" +
                "\t)\n" +
                "}"
                ;
    }
}
