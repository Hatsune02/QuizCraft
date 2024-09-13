package com.navi.quizcraftweb.backend.dao;

import com.navi.quizcraftweb.backend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> select(){
        return Utils.usersListDB();
    }
    public User viewUser(String username){
        for(User user : select()){
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public User login(String username, String password){
        for(User user : select()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

    public void insertUser(User user){
        boolean valid = true;
        for(String u : Utils.usernamesDB()){
            if(user.getUsername().equals(u)){
                valid = false;
                break;
            }
        }
        if(valid){

        }
        else{

        }
    }

    public void updateUser(User user, String oldUsername){
        boolean valid = false;
        for(User u : select()){
            if(u.getUsername().equals(oldUsername)){
                valid = true;
                break;
            }
        }
        if(valid){

        }
    }
    public void deleteUser(String username){
        boolean valid = false;
        for(User u : select()){
            if(u.getUsername().equals(username)){
                valid = true;
                break;
            }
        }
        if(valid){

        }
    }
}
