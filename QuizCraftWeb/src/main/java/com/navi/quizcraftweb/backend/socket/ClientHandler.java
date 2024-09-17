package com.navi.quizcraftweb.backend.socket;

import com.navi.quizcraftweb.backend.dao.Connection;

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

            String request = in.readLine();
            System.out.println("Received request: " + request);

            if ("triv".equals(request)) {
                out.println(Connection.connectUsersDB().users);
            } else {
                out.println("Unknown request");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
