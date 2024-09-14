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
    public static void createAdmin(){
        File userHome = new File(System.getProperty("user.home"));
        String appFolderName = "QuizCraft";
        File appFolder = new File(userHome, appFolderName);

        if(!appFolder.exists()) {
            appFolder.mkdirs();
        }

        User admin = new User("admin","1234","Admin","CUNOC");
        String userText = "db.user(\n" + admin.dbString() + "\n)";
        File file = new File(appFolder, "users.db");
        if(!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(userText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
