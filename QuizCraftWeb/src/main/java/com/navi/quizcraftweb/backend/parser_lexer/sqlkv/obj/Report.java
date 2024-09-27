package com.navi.quizcraftweb.backend.parser_lexer.sqlkv.obj;
import com.navi.quizcraftweb.backend.model.CollectedData;
import lombok.*;

@Getter @Setter
public class Report {
    private String nameTrivia;
    private String topic;
    private int questions;

    private String idTrivia;
    private String username;
    private int time;
    private int score;
    private boolean done;

    public Report(String nameTrivia, String topic, int questions, CollectedData data) {
        this.nameTrivia = nameTrivia;
        this.topic = topic;
        this.questions = questions;
        this.idTrivia = data.getTrivia();
        this.username = data.getUsername();
        this.time = data.getTime();
        this.score = data.getScore();
        this.done = data.isDone();
    }

    @Override
    public String toString() {
        return "Report{" +
                "nameTrivia='" + nameTrivia + '\'' +
                ", topic='" + topic + '\'' +
                ", questions=" + questions +
                ", idTrivia='" + idTrivia + '\'' +
                ", username='" + username + '\'' +
                ", time=" + time +
                ", score=" + score +
                ", done=" + done +
                '}';
    }
}
