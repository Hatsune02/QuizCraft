package com.navi.quizcraftapp.model;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String name;
    private String institution;
    private Date createDate;
    private Date updateDate;

    public User(){}
    public User(String username, String password, String name, String institution, String createDate, String updateDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.createDate = ModelUtils.stringToDate(createDate);
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
