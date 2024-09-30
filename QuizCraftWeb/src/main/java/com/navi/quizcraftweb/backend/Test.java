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
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.CompileSQLKV;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.SqlLexer;
import com.navi.quizcraftweb.backend.parser_lexer.sqlkv.SqlParser;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String text = """
                <?xson version="1.0" ?>
                <!realizar_solicitud: "LOGIN_USUARIO" >
                    { "DATOS_USUARIO":[{
                        "USUARIO": "admin",
                        "PASSWORD": "1234"
                    }
                    ]}
                <fin_solicitud_realizada!>
                """;

/*        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo de texto");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Leer el contenido del archivo
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                System.out.println("Contenido del archivo:\n" + content);
                User u = new User();
                u.setUsername("admin");
                CompileRequest.userSession = u;
                CompileRequest.execute(content);
                var errors = ErrorsLP.getErrors();
                errors.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }*/
        CompileRequest.verifyRequestLogin(text);
        ErrorsLP.getErrors().forEach(System.out::println);

    }
}
