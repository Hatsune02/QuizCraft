package com.navi.quizcraftapp.model;

import java.io.Serializable;

public class CollectedData implements Serializable {
    private String username;
    private String trivia;
    private int time;
    private int score;
    private boolean done;
    public CollectedData(String username, String trivia, int time, int score, boolean done){
        this.username = username;
        this.trivia = trivia;
        this.time = time;
        this.score = score;
        this.done = done;
    }
}
