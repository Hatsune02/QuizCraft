package com.navi.quizcraftweb.backend.controller;
import java.io.*;

import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.dao.Utils;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String solicitude = request.getParameter("request");
        Utils.createAdmin();
        User user = null;
        CompileRequest.Compile(solicitude);
        var requests = CompileRequest.requests;
        if(requests.size() == 1){
            if(requests.get(0).getType() == RequestXSON.LOGIN_USUARIO){
                User u = (User) requests.get(0).getData();
                user = userDAO.login(u.getUsername(), u.getPassword());
            }
        }

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("request");
        } else {
            response.sendRedirect("/QuizCraftWeb?error=invalid");
        }
    }
}
