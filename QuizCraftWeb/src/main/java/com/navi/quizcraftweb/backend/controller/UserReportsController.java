package com.navi.quizcraftweb.backend.controller;
import com.navi.quizcraftweb.backend.model.CollectedData;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.TError;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.CompileSQLKV;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.obj.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/reports")
public class UserReportsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obtainUser(request);
        request.getRequestDispatcher("user_reports.jsp").forward(request, response);
    }
    private void obtainUser(HttpServletRequest request){
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        List<Report> reports = new ArrayList<>();
        String fullHistory = request.getParameter("fullHistory");
        String errorMessage;

        CompileSQLKV.compile(command);

        if(ErrorsLP.getErrors().isEmpty()){
            reports = Query.reports;
            fullHistory = (fullHistory == null ? "" : fullHistory) + "\n" + command;
        }
        else{
            errorMessage = "Error: Comando no válido.";
            fullHistory = (fullHistory == null ? "" : fullHistory) + "\n" + command + "\n" + errorMessage;
            var errors = ErrorsLP.getErrors();
            for (TError e: errors){
                fullHistory = fullHistory + "\n" + command + "\n" + e;
            }
        }

        request.setAttribute("reports", reports);
        request.setAttribute("commandHistory", fullHistory);

        request.getRequestDispatcher("user_reports.jsp").forward(request, response);
    }
}
