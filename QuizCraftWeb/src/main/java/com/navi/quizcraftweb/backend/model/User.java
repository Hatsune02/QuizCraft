package com.navi.quizcraftweb.backend.model;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.Parameter;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Getter @Setter
public class User {
    private String username;
    private String password;
    private String name;
    private String institution;
    private Date createDate;
    private Date updateDate;

    public User(){}
    public User(String username, String password, String name, String institution, String createDate, int type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.createDate = ModelUtils.stringToDate(createDate);

    }
    public User(String username, String password, String name, String institution, String updateDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.updateDate = ModelUtils.stringToDate(updateDate);
    }
    public User(String username, String password, String name, String institution) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.createDate = new Date();
    }

    public void setCreateDateString(String d) {
        createDate = ModelUtils.stringToDate(d);
    }
    public void setUpdateDateString(String d) {
        updateDate = ModelUtils.stringToDate(d);
    }



    public void setNewUser(HashMap<Integer, Parameter> parameters, boolean hasDate){
        username = (String) parameters.get(Parameter.USER).getParameter();
        password = (String) parameters.get(Parameter.PASSWORD).getParameter();
        name = (String) parameters.get(Parameter.NAME).getParameter();
        institution = (String) parameters.get(Parameter.INSTITUTION).getParameter();
        if(hasDate) createDate = ModelUtils.stringToDate((String) parameters.get(Parameter.CREATE_DATE).getParameter());
        else createDate = new Date();
    }

    public void setUpdateUser(HashMap<Integer, Parameter> parameters, boolean hasNewUser, boolean hasNewPassword, boolean hasInstitution){
        if(hasNewUser) username = (String) parameters.get(Parameter.NEW_USER).getParameter();
        if(hasNewPassword) password = (String) parameters.get(Parameter.NEW_PASSWORD).getParameter();
        if(hasInstitution) institution = (String) parameters.get(Parameter.INSTITUTION).getParameter();
        updateDate = new Date();

    }
    public void setLogin(HashMap<Integer, Parameter> parameters){
        username = (String) parameters.get(Parameter.USER).getParameter();
        password = (String) parameters.get(Parameter.PASSWORD).getParameter();
    }



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", institution='" + institution + '\'' +
                ", createDate=" + ModelUtils.dateToString(createDate) +
                ", updateDate=" + ModelUtils.dateToString(updateDate) +
                '}';
    }
    public String dbString(){
        return "{\n" +
                "\t\"USUARIO\":\""+username+"\",\n"+
                "\t\"PASSWORD\":\""+password+"\",\n"+
                "\t\"NOMBRE\":\""+name+"\",\n" +
                "\t\"INSTITUCION\":\""+institution+"\",\n" +
                "\t\"FECHA_CREACION\":\""+ModelUtils.dateToString(createDate)+"\",\n" +
                "\t\"FECHA_MODIFICACION\":\""+ModelUtils.dateToString(updateDate)+"\"\n" +
                "}"
                ;

    }
}
