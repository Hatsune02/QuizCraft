package com.navi.quizcraftweb.backend.controller;
import java.io.*;

import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginController {
    UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String solicitude = request.getParameter("request");

        User user = null;
        CompileRequest.Compile(solicitude);
        var requests = CompileRequest.requests;
        if(requests.size() == 1){
            if(requests.get(0).getType() == RequestXSON.LOGIN_USUARIO){
                User u = (User) requests.get(0).getData();
                user = userDAO.login(u.getUsername(), u.getPassword());
                System.out.printf("se encontro el usuario %s\n", u.getUsername());
            }

        }

        if (user != null) {
            //request.setAttribute("mensaje", solicitude);
            request.getRequestDispatcher("user_page.jsp").forward(request, response);
        } else {
            // Si el texto es inválido, regresar al login con un mensaje de error
            //request.setAttribute("errorMessage", "El área de texto no puede estar vacía.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
