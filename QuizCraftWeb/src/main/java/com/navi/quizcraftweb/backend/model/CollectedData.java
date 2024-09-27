package com.navi.quizcraftweb.backend.model;
import lombok.*;

@Getter @Setter
public class CollectedData {
    private String username;
    private String trivia;
    private int time;
    private int score;
    private boolean done;

    public CollectedData(){

    }

    public CollectedData(String username, String trivia, int time, int score, boolean done) {
        this.username = username;
        this.trivia = trivia;
        this.time = time;
        this.score = score;
        this.done = done;
    }

    public String dbString(){
        return "\t{\n" +
                "\t\t\"USUARIO\":\"" + username + "\",\n"+
                "\t\t\"TRIVIA\":\"" + trivia + "\",\n"+
                "\t\t\"TIEMPO_TOTAL\":" + time + ",\n"+
                "\t\t\"ESTADO\":\"" + dbDone() + "\",\n"+
                "\t\t\"PUNTEO\":" + score + "\n"+
                "\t}"
                ;
    }
    public String dbDone(){
        if (done) return "Completado";
        else return "Fallo";
    }

    @Override
    public String toString() {
        return "CollectedData{" +
                "username='" + username + '\'' +
                ", trivia='" + trivia + '\'' +
                ", time=" + time +
                ", score=" + score +
                ", done=" + done +
                '}';
    }
}
