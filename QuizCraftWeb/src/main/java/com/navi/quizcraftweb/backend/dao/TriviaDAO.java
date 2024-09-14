package com.navi.quizcraftweb.backend.dao;

import com.navi.quizcraftweb.backend.model.Trivia;

import java.util.List;

public class TriviaDAO {
    public List<Trivia> select(){
        return Utils.connectUsersDB().trivias;
    }
}
