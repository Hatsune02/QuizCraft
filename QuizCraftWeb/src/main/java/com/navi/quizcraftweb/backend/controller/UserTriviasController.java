package com.navi.quizcraftweb.backend.controller;

import com.navi.quizcraftweb.backend.model.Trivia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/trivias")
public class UserTriviasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trivia> triviaList = new ArrayList<>();
        triviaList.add(new Trivia("triv1", "trivia 1", 40, "Yo", "topic 1", "2024-08-07"));
        triviaList.add(new Trivia("triv2", "trivia 2", 30,"Yo", "topic 2"));
        triviaList.add(new Trivia("triv3", "trivia 3", 50,"Yo", "topic 3"));
        triviaList.add(new Trivia("triv4", "trivia 4", 80,"Yo", "topic 4"));
        triviaList.add(new Trivia("triv4", "trivia 4", 80,"Yo", "topic 4"));

        request.setAttribute("triviaList", triviaList);
        request.getRequestDispatcher("user_trivias.jsp").forward(request, response);
    }
}
