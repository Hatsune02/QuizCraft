package com.navi.quizcraftapp.socket;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.*;
import java.net.Socket;

public class SocketManager {
    private String serverIp;
    private int serverPort;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public SocketManager(Context context, int port) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        serverIp = sharedPreferences.getString("ip_address", "192.168.0.106"); // IP por defecto
        this.serverPort = port;
    }

    // Conexión al servidor
    public void connect() throws IOException {
        socket = new Socket(serverIp, serverPort);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // Método para enviar datos al servidor
    public void sendData(String data) throws IOException {
        writer.write(data + "EOF");
        writer.newLine();  // Permite mandar varias líneas
        writer.flush();    // Asegura que se envíe inmediatamente
    }

    // Método para recibir datos del servidor
    public String receiveData() throws IOException {
        return reader.readLine();
    }

    // Cerrar la conexión
    public void close() throws IOException {
        if (writer != null) writer.close();
        if (reader != null) reader.close();
        if (socket != null) socket.close();
    }
}
