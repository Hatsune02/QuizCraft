package com.navi.quizcraftweb.backend.controller;

import com.navi.quizcraftweb.backend.dao.TriviaDAO;
import com.navi.quizcraftweb.backend.model.Trivia;
import com.navi.quizcraftweb.backend.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/trivias")
@MultipartConfig
public class UserTriviasController extends HttpServlet {
    private User user;
    private TriviaDAO triviaDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obtainUser(request);
        triviaDAO = new TriviaDAO();
        String triviaId = request.getParameter("id");
        String idExport = request.getParameter("id_export");
        if (triviaId != null && !triviaId.isEmpty()) {
            showTrivia(triviaId, request, response);
        }
        else if(idExport != null && !idExport.isEmpty()) {
            exportTrivia(idExport, response);
        }
        else{
            showTrivias(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("import".equals(action)) {
            showImportContent(request, response);
        }
        else{
            saveImportTrivia(request, response);
        }
    }

    private void obtainUser(HttpServletRequest request){
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
    }

    private void showTrivia(String triviaId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Trivia trivia = triviaDAO.viewTrivia(triviaId);

        if (trivia != null) {
            request.setAttribute("trivia", trivia);
            request.getRequestDispatcher("view_trivia.jsp").forward(request, response);
        } else {
            response.sendRedirect("trivias?error=notfound");
        }
    }

    private void exportTrivia(String idExport, HttpServletResponse response) throws IOException{
        Trivia trivia = triviaDAO.viewTrivia(idExport);
        if (trivia != null) {
            String fileName = "trivia_" + trivia.getIdTrivia() + ".xtriv";
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            trivia.setCollectedData(new ArrayList<>());
            String triviaContent = "new.trivia (\n" + trivia.dbString()+ ")";

            try (PrintWriter writer = response.getWriter()) {
                writer.write(triviaContent);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trivia no encontrada");
        }
    }

    private void showTrivias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trivia> triviaList = triviaDAO.select().stream()
                .filter(trivia -> user.getUsername().equals(trivia.getCreateUser()))
                .collect(Collectors.toList());

        request.setAttribute("triviaList", triviaList);
        request.getRequestDispatcher("user_trivias.jsp").forward(request, response);
    }

    private void showImportContent(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        Part filePart = request.getPart("triviaFile");

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        request.setAttribute("importedContent", content.toString());

        request.getRequestDispatcher("show_import_trivia.jsp").forward(request, response);
    }
    private void saveImportTrivia(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String newContent = request.getParameter("importedContent");
        triviaDAO = new TriviaDAO();
        triviaDAO.insertImportTrivia(newContent, user.getUsername());

        response.sendRedirect("request");
    }
}
