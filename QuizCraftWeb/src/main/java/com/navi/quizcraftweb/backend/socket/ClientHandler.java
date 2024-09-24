package com.navi.quizcraftweb.backend.socket;

import com.navi.quizcraftweb.backend.dao.Connection;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String line;
            StringBuilder request = new StringBuilder();
            while ((line = in.readLine()) != null) {
                if (line.contains("EOF")) {
                    request.append(line.replace("EOF", ""));  // Eliminar "EOF"
                    break;
                }
                request.append(line).append("\n");
            }
            System.out.println("Received request: " + request);

            String response = CompileRequest.executeSocketRequest(request.toString());
            out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
