package com.navi.quizcraftweb.backend;

import com.navi.quizcraftweb.backend.dao.Connection;
import com.navi.quizcraftweb.backend.dao.TriviaDAO;
import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.model.CollectedData;
import com.navi.quizcraftweb.backend.model.Component;
import com.navi.quizcraftweb.backend.model.Trivia;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.TError;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestLexer;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String login = """
                <?xson version="1.0" ?>
                <!realizar_solicitud: "LOGIN_USUARIO" >
                    { "DATOS_USUARIO":[{
                        "USUARIO": "admin",
                        "PASSWORD": "1234"
                    }
                    ]}
                <fin_solicitud_realizada!>
                """;

/*        int port = 5000;
        try {
            // Crear un ServerSocket que escuche en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Streams para leer y escribir datos al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                // Leer el mensaje del cliente
                String line;
                StringBuilder clientMessage = new StringBuilder();
                while ((line = input.readLine()) != null) {
                    if (line.contains("EOF")) {
                        clientMessage.append(line.replace("EOF", ""));  // Eliminar "EOF"
                        break;
                    }
                    clientMessage.append(line).append("\n");
                }
                System.out.println("Mensaje recibido del cliente: " + clientMessage);

                // Enviar una respuesta al cliente

                String serverResponse = CompileRequest.executeSocketRequest(clientMessage.toString());
                output.println(serverResponse);

                // Cerrar conexiones
                input.close();
                output.close();
                clientSocket.close();

                System.out.println("Conexión cerrada con el cliente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo de texto");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Leer el contenido del archivo
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                System.out.println("Contenido del archivo:\n" + content);
                CompileRequest.execute(content);
                var errors = ErrorsLP.getErrors();
                errors.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }



       /* Connection.createAdmin();
        User user = new User("jojo","1233","JOJO","JOJO");
        UserDAO userDAO = new UserDAO();
        TriviaDAO triviaDAO = new TriviaDAO();
        Trivia trivia = new Trivia("trivia1","nombre Trivia","Topic1", 10, "yo", "2004-08-08", new ArrayList<>(), new ArrayList<>());
        Trivia trivia2 = new Trivia("triv2", "trivia 2", "topic 2", 30,"El", "2004-08-05", new ArrayList<>(), new ArrayList<>());
        Trivia trivia3 = new Trivia("triv3", "trivia 3", "topic 3", 50,"Yo", "2004-08-08", new ArrayList<>(), new ArrayList<>());
        Trivia trivia4 = new Trivia("triv3", "trivia 4", "topic 4", 60,"Yo", "2004-08-05", new ArrayList<>(), new ArrayList<>());


        ArrayList<String> respuesta = new ArrayList<>();
        respuesta.add("Ganzo");

        ArrayList<String> options = new ArrayList<>();
        options.add("Porro");
        options.add("Amarillo");
        options.add("xdxd");

        ArrayList<String> respuesta2 = new ArrayList<>();
        respuesta2.add("Porro");
        respuesta2.add("Lamida");

        Component component = new Component("comp1", "trivia1",1,0,"texto visible xd", respuesta);
        Component component2 = new Component("comp2", "trivia1",3,0,"texto visible 2 xd", respuesta2);
        component2.setOptions(options);

        CollectedData data = new CollectedData("usuario", "trivia1", 20, 100, true);

        trivia.addComponent(component);
        trivia.addComponent(component2);
        trivia.addData(data);

        triviaDAO.insertTrivia(trivia);
        triviaDAO.insertTrivia(trivia2);
        triviaDAO.insertTrivia(trivia3);
        triviaDAO.updateTrivia(trivia4);
        triviaDAO.deleteTrivia("trivia1");
        Component component3 = new Component();
        component3.setId("comp1");
        component3.setTrivia("triv2");

        component3.setVisibleText("texto nuevo xd");
        component3.setClase(3);
        component3.setOptions(options);

        component3.setIndex(2);

        Component component4 = new Component("comp2", "triv2",3,0,"texto visible 2 xd", respuesta2);
        component4.setOptions(options);

        triviaDAO.deleteComponent("triv2", "comp1");*/
        //userDAO.insertUser(user);
        //userDAO.deleteUser("jhon");
        //userDAO.updateUser(user, "juan");


    }
}
