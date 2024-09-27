package com.navi.quizcraftweb.backend.controller;
import java.io.*;

import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.dao.Connection;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String solicitude = request.getParameter("request");
        Connection.createAdmin();
        User user = CompileRequest.verifyRequestLogin(solicitude);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("request");
        } else {
            response.sendRedirect("/QuizCraftWeb?error=invalid");
        }
    }
}
