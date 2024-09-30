package com.navi.quizcraftweb.backend.controller;

import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/request")
public class UserPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obtainUser(request);
        viewErrors(request);
        request.getRequestDispatcher("user_requests.jsp").forward(request, response);
    }
    private void obtainUser(HttpServletRequest request){
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String solicitude = request.getParameter("solicitude");
        request.setAttribute("solicitude", solicitude);
        String serverResponse = CompileRequest.execute(solicitude);
        var errors = ErrorsLP.getErrors();
        StringBuilder responseText = new StringBuilder();
        if (errors.isEmpty()) {
            responseText.append(serverResponse);
        } else {
            errors.forEach(e -> responseText.append(e).append("\n"));
        }

        request.setAttribute("serverResponse", responseText.toString());
        request.getRequestDispatcher("user_requests.jsp").forward(request, response);
    }
    private void viewErrors(HttpServletRequest request) throws ServletException, IOException {
        var errors = ErrorsLP.getErrors();
        StringBuilder responseText = new StringBuilder();
        if (!errors.isEmpty()) {
            errors.forEach(e -> responseText.append(e).append("\n"));
        }
        request.setAttribute("serverResponse", responseText.toString());
    }
}
