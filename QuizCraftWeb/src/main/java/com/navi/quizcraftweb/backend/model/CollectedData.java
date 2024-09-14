package com.navi.quizcraftweb.backend.model;
import lombok.*;

@Getter @Setter
public class CollectedData {
    private String username;
    private String trivia;
    private int time;
    private int score;

    public String dbString(){
        return "\t{\n" +
                "\t\t\"USUARIO\":\"" + username + "\",\n"+
                "\t\t\"TRIVIA\":\"" + trivia + "\",\n"+
                "\t\t\"TIEMPO_TOTAL\":" + time + ",\n"+
                "\t\t\"PUNTEO\":" + score + "\n"+
                "\t}"
                ;
    }
}
