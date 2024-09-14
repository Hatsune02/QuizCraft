package com.navi.quizcraftweb.backend.dao;

import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.db.objs.Position;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> select(){
        return Utils.connectUsersDB().users;
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
        DBParser parser = Utils.connectUsersDB();
        ArrayList<String> usernames = parser.idsUser;
        Position finalPos = parser.finalPos;

        for(String u : usernames){
            if(user.getUsername().equals(u)){
                valid = false;
                break;
            }
        }
        if(valid){
            int position = Utils.calculatePosition(Utils.text, finalPos.getLine1(), finalPos.getCol1());
            String insertText = user.dbString() + "\n";
            if(!usernames.isEmpty()) insertText = ",\n"+insertText;

            Utils.insertTextUser(position, insertText);
        }
        else{
            System.out.println("Usuario ya existente");
        }
    }
    public void updateUser(User user, String oldUsername){
        boolean valid = false;
        User actualUser = new User();
        DBParser parser = Utils.connectUsersDB();
        ArrayList<Position> positions = parser.positions;
        int pos = 0;

        for(User u : parser.users){
            if(u.getUsername().equals(oldUsername)){
                actualUser = u;
                valid = true;
                break;
            }
            pos++;
        }

        if(valid){
            Position position = positions.get(pos);
            int startPosition = Utils.calculatePosition(Utils.text, position.getLine1(), position.getCol1());
            int endPosition = Utils.calculatePosition(Utils.text, position.getLine2(), position.getCol2());

            if(user.getUsername()!=null) actualUser.setUsername(user.getUsername());
            if(user.getPassword()!=null) actualUser.setPassword(user.getPassword());
            if(user.getInstitution()!=null) actualUser.setInstitution(user.getInstitution());
            actualUser.setUpdateDate(user.getUpdateDate());

            Utils.updateTextUser(startPosition, endPosition+1, actualUser.dbString());
        }
    }
    public void deleteUser(String username){
        if(username.equals("admin")){
            System.out.println("no se puede eliminar al admin");
            return;
        }

        boolean valid = false;
        DBParser parser = Utils.connectUsersDB();
        ArrayList<String> usernames = parser.idsUser;
        ArrayList<Position> positions = parser.positions;
        int pos = 0;

        for(String u : usernames){
            if(u.equals(username)){
                valid = true;
                break;
            }
            pos++;
        }
        if(valid){
            Position position = positions.get(pos);
            int startPosition = Utils.calculatePosition(Utils.text, position.getLine1(), position.getCol1());
            int endPosition = Utils.calculatePosition(Utils.text, position.getLine2(), position.getCol2());
            if(Utils.text.charAt(startPosition-2) == ','){
                Utils.deleteTextUser(startPosition-3, endPosition+1);
            }
            else {
                if(Utils.text.charAt(endPosition+2) == ','){
                    Utils.deleteTextUser(startPosition-1, endPosition+3);
                }
                else{
                    Utils.deleteTextUser(startPosition-1, endPosition+1);
                }
            }
        }
        else{
            System.out.println("Usuario no encontrado");
        }
    }
}
