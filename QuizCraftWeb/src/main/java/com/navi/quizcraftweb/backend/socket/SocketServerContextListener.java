package com.navi.quizcraftweb.backend.socket;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@WebListener
public class SocketServerContextListener implements ServletContextListener {
    private Thread socketServerThread;
    private ServerSocket serverSocket;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        contextDestroyed(sce);
        socketServerThread = new Thread(() -> {
            try {
                int port = 5000;
                serverSocket = new ServerSocket(port);
                System.out.println("Servidor escuchando en el puerto " + port);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        socketServerThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (socketServerThread != null && socketServerThread.isAlive()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socketServerThread.interrupt();
        }
    }
}
