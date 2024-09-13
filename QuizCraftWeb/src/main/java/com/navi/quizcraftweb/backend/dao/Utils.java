package com.navi.quizcraftweb.backend.dao;

import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;

import java.io.*;
import java.util.List;

public class Utils {
    private static Reader reader;
    private static DBLexer lexer;
    private static DBParser parser;

    public static String readFileAsString(File file) {
        if (file.exists()) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n"); // Agregar línea al StringBuilder
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
                return null; // En caso de error, devolver null
            }
            return content.toString(); // Convertir StringBuilder a String y devolver
        } else {
            System.out.println("El archivo no existe: " + file.getAbsolutePath());
            return "";
        }
    }

    public static List<User> usersListDB(){
        connectUsersDB();
        return parser.users;
    }

    public static List<String> usernamesDB(){
        connectUsersDB();
        return parser.idUsers;
    }

    private static void connectUsersDB() {
        File userHome = new File(System.getProperty("user.home"));
        String appFolderName = "QuizCraft/users.db";
        File appFolder = new File(userHome, appFolderName);

        String text = readFileAsString(appFolder);
        if(text == null) text = "";

        reader = new StringReader(text);
        lexer = new DBLexer(reader);
        parser = new DBParser(lexer);
        try{
            parser.parse();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
