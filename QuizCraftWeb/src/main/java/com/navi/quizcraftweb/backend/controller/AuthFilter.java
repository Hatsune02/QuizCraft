package com.navi.quizcraftweb.backend.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // false para no crear una nueva sesión si no existe

        String loginURI = "/QuizCraftWeb"; // Página principal
        String userPageURI = "/QuizCraftWeb/request"; // Página de usuario

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        // Si no está logueado y trata de ir a la página de usuario, lo rediriges al login
        if (!loggedIn && httpRequest.getRequestURI().startsWith(userPageURI)) {
            httpResponse.sendRedirect(loginURI);
        } else {
            chain.doFilter(request, response);
        }
    }
}
