package com.navi.quizcraftweb.backend;

import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.CompileRequest;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestLexer;
import com.navi.quizcraftweb.backend.parser_lexer.request.RequestParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        RequestLexer lexer;
        Reader reader;
        RequestParser parser = null;
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

        User user = new User("jojo","1233","JOJO","JOJO");
        UserDAO userDAO = new UserDAO();

        //userDAO.insertUser(user);
        //userDAO.deleteUser("jhon");
        //userDAO.updateUser(user, "juan");


        /*try{
            String text = """
                    <?xson version="1.0" ?>\s
                    <!realizar_solicitudes>\s
                      <!realizar_solicitud: "USUARIO_NUEVO" >\s
                        { "DATOS_USUARIO":[{\s
                          "USUARIO": "juanito619",\s
                          "PASSWORD": "12345678",\s
                          "NOMBRE": "JUAN PEREZ",\s
                          "INSTITUCION": "CUNOC"\s
                        }]\s
                        }\s
                      <fin_solicitud_realizada!>
                      <!realizar_solicitud: "MODIFICAR_USUARIO" >\s
                        { "DATOS_USUARIO":[{\s
                          "USUARIO_ANTIGUO": "juanito619",\s
                          "USUARIO_NUEVO": "juanito619lopez",\s
                          "NUEVO_PASSWORD": "123456789"\s
                        }\s
                        ]}\s
                      <fin_solicitud_realizada!>\s
                      <!realizar_solicitud: "ELIMINAR_USUARIO" >\s
                        { "DATOS_USUARIO":[{\s
                          "USUARIO": "juanito619lopez"\s
                        }\s
                        ]}\s
                      <fin_solicitud_realizada!>
                      <!realizar_solicitud: "LOGIN_USUARIO" >\s
                        { "DATOS_USUARIO":[{\s
                          "USUARIO": "juanito619",\s
                          "PASSWORD": "12345678"\s
                        }\s
                        ]}\s
                      <fin_solicitud_realizada!> \s
                      <!realizar_solicitud: " NUEVA_TRIVIA" >\s
                        { "PARAMETROS_TRIVIA":[{\s
                          "ID_TRIVIA": "$trivia1",\s
                          "TIEMPO_PREGUNTA": 45,\s
                          "NOMBRE": "Cultura de Guatemala",\s
                          "TEMA": "cultura"\s
                        }\s
                        ]}\s
                      <fin_solicitud_realizada!>\s
                      <!realizar_solicitud: "ELIMINAR_TRIVIA" >\s
                        { "PARAMETROS_TRIVIA":[{\s
                          "ID_TRIVIA": "$trivia1"\s
                        }\s
                        ]}\s
                      <fin_solicitud_realizada!>\s
                      <!realizar_solicitud: "MODIFICAR_TRIVIA" >\s
                          { "PARAMETROS_TRIVIA":[{\s
                                    "ID_TRIVIA": "$trivia1",\s
                                    "TIEMPO_PREGUNTA": 30,\s
                                    "NOMBRE": "Comidas tipicas de Guatemala ",\s
                                    "TEMA": "Cultura"\s
                            }\s
                            ]}\s
                      <fin_solicitud_realizada!>
                      <!realizar_solicitud: "AGREGAR_COMPONENTE" >\s
                          { "PARAMETROS_COMPONENTE":[{\s
                                    "ID": "$_text_nombre_autor",\s
                                    "TRIVIA": "$trivia1",\s
                                    "CLASE": "CAMPO_TEXTO",\s
                                    "TEXTO_VISIBLE": "Nombre del autor de la letra del Himno de Guatemala: ",\s
                                    "RESPUESTA": "Jose Joaquin Palma"\s
                            }\s
                            ]}\s
                      <fin_solicitud_realizada!>\s
                      <!realizar_solicitud: "AGREGAR_COMPONENTE" >\s
                          { "PARAMETROS_COMPONENTE":[{\s
                                    "ID": "$_soldado_presidente",\s
                                    "TRIVIA": "$trivia1",\s
                                    "RESPUESTA": "Jacobo Arbenz",\s
                                    "CLASE": "RADIO",\s
                                    "TEXTO_VISIBLE": "A quie se le conoce como el soldado del pueblo? ",\s
                                    "OPCIONES": "Jacobo Arbenz|Jose Arebalo|Jorge Ubico|Otro"\s
                            }\s
                            ]}\s
                      <fin_solicitud_realizada!>\s
                      <!realizar_solicitud: "ELIMINAR_COMPONENTE" >\s
                          { "PARAMETROS_COMPONENTE":[{\s
                                    "ID": "$_soldado_presidente",\s
                                    "TRIVIA": "$trivia1"\s
                            }\s
                            ]}\s
                      <fin_solicitud_realizada!>\s
                      <!realizar_solicitud: "MODIFICAR_COMPONENTE" >\s
                          { "PARAMETROS_COMPONENTE":[{\s
                                    "ID": "$_soldado_presidente",\s
                                    "TRIVIA": "$trivia1",\s
                                    "CLASE": "CHECKBOX",\s
                                    "INDICE": "1",\s
                                    "TEXTO_VISIBLE": "Quienes fueron parte de la revolucion del 20 de octubre? ",\s
                                    "OPCIONES": "Jacobo Arbenz|Jose Arebalo|Jorge Ubico|Maria Chichilla",\s
                                    "RESPUESTA": "Jacobo Arbenz|Jose Arebalo"\s
                            }\s
                            ]}\s
                      <fin_solicitud_realizada!>\s
                     \s
                    <!fin_solicitudes_realizada>\s
                    
                    """;
            reader = new StringReader(text);
            lexer = new RequestLexer(reader);
            parser = new RequestParser(lexer);
            try{
                ErrorsLP.getErrors().clear();
                //parser.parse();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        if(!ErrorsLP.getErrors().isEmpty()) {
            ErrorsLP.getErrors().forEach(System.out::println);
        }
        else{
            for(RequestXSON r: parser.requests){
                System.out.println(r.toString());
            }
        }*/

    }
}
