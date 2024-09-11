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
        this.createDate = stringToDate(createDate);

    }
    public User(String username, String password, String name, String institution, String updateDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.updateDate = stringToDate(updateDate);
    }
    public User(String username, String password, String name, String institution) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.createDate = new Date();
    }

    public Date stringToDate(String date) {
        Date d;
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return d;
    }

    public String dateToString(Date date) {
        if (date == null) {
            return "null";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public void setNewUser(HashMap<Integer, Parameter> parameters, boolean hasDate){
        username = (String) parameters.get(Parameter.USER).getParameter();
        password = (String) parameters.get(Parameter.PASSWORD).getParameter();
        name = (String) parameters.get(Parameter.NAME).getParameter();
        institution = (String) parameters.get(Parameter.INSTITUTION).getParameter();
        if(hasDate) createDate = stringToDate((String) parameters.get(Parameter.CREATE_DATE).getParameter());
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
                ", createDate=" + dateToString(createDate) +
                ", updateDate=" + dateToString(updateDate) +
                '}';
    }
    public String dbString(){
        return "{\n" +
                "\t\"USUARIO\":\""+username+"\",\n"+
                "\t\"PASSWORD\":\""+password+"\",\n"+
                "\t\"NOMBRE\":\""+name+"\",\n" +
                "\t\"INSTITUCION\":\""+institution+"\",\n" +
                "\t\"FECHA_CREACION\":\""+dateToString(createDate)+"\",\n" +
                "\t\"FECHA_MODIFICACION\":\""+dateToString(updateDate)+"\",\n" +
                "}"
                ;

    }
}
