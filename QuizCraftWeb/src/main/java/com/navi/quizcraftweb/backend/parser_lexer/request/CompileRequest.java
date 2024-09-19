package com.navi.quizcraftweb.backend.parser_lexer.request;

import com.navi.quizcraftweb.backend.dao.Connection;
import com.navi.quizcraftweb.backend.dao.TriviaDAO;
import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.model.Component;
import com.navi.quizcraftweb.backend.model.Trivia;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompileRequest {
    private static Reader reader;
    private static RequestLexer lexer;
    private static RequestParser parser;
    public static ArrayList<RequestXSON> requests;
    private static UserDAO userDAO;
    private static TriviaDAO triviaDAO;

    public static void Compile(String text){
        reader = new StringReader(text);
        lexer = new RequestLexer(reader);
        parser = new RequestParser(lexer);
        requests = new ArrayList<>();
        userDAO = new UserDAO();
        triviaDAO = new TriviaDAO();
        try{
            ErrorsLP.clearErrors();
            parser.parse();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        if(ErrorsLP.getErrors().isEmpty()){
            requests = parser.requests;
        }
    }
    public static User verifyRequestLogin(String text){
        Compile(text);
        User user = null;
        if(requests.size() == 1){
            if(requests.get(0).getType() == RequestXSON.LOGIN_USUARIO){
                User u = (User) requests.get(0).getData();
                user = userDAO.login(u.getUsername(), u.getPassword());
            }
        }
        return user;
    }
    public static boolean verifyRequests(String text){
        boolean verify = true;
        DBParser parserU = Connection.connectUsersDB();
        DBParser parserT = Connection.connectTriviaDB();
        var users = parserU.idsUser;
        var trivias = parserT.idsTrivia;
        var components = parserT.idsComponent;
        Compile(text);
        for(RequestXSON<?> request : requests){
            switch (request.getType()){
                case RequestXSON.USUARIO_NUEVO -> {
                    User u = (User) request.getData();
                    if(users.contains(u.getUsername())){
                        ErrorsLP.addError(u.getUsername(), request.getLine(), request.getCol(),"Semantico", "Usuario ya existente");
                        verify = false;
                    }
                    else users.add(u.getUsername());
                }
                case RequestXSON.MODIFICAR_USUARIO -> {
                    String u = request.getId();
                    if(!users.contains(u)){
                        ErrorsLP.addError(u, request.getLine(), request.getCol(),"Semantico", "Usuario no encontrado");
                        verify = false;
                    }
                }
                case RequestXSON.ELIMINAR_USUARIO -> {
                    String u = request.getId();
                    if(users.contains(u)){
                        users.remove(u);
                    }
                    else {
                        ErrorsLP.addError(u, request.getLine(), request.getCol(),"Semantico", "Usuario no encontrado");
                        verify = false;
                    }
                }
                case RequestXSON.LOGIN_USUARIO -> {
                    ErrorsLP.addError("LOGIN_USUARIO", request.getLine(), request.getCol(),"Semantico", "No puedes iniciar sesion desde aqui.");
                }
                case RequestXSON.NUEVA_TRIVIA -> {
                    Trivia t = (Trivia) request.getData();
                    if(trivias.contains(t.getIdTrivia()) && users.contains(t.getCreateUser())){
                        ErrorsLP.addError(t.getIdTrivia(), request.getLine(), request.getCol(), "Semantico", "Trivia ya existente");
                        verify = false;
                    }
                    else {
                        if(users.contains(t.getCreateUser())){
                            trivias.add(t.getIdTrivia());
                        }
                        else {
                            ErrorsLP.addError(t.getCreateUser(), request.getLine(), request.getCol(),"Semantico", "Usuario creador no encontrado");
                            verify = false;
                        }
                    }
                }
                case RequestXSON.MODIFICAR_TRIVIA -> {
                    Trivia t = (Trivia) request.getData();
                    if(!trivias.contains(t.getIdTrivia())){
                        ErrorsLP.addError(t.getIdTrivia(), request.getLine(), request.getCol(),"Semantico", "Trivia no encontrada");
                        verify = false;
                    }
                }
                case RequestXSON.ELIMINAR_TRIVIA -> {
                    String t = request.getId();
                    if(trivias.contains(t)){
                        trivias.remove(t);
                    }
                    else {
                        ErrorsLP.addError(t, request.getLine(), request.getCol(),"Semantico", "Trivia no encontrada");
                        verify = false;
                    }
                }
                case RequestXSON.AGREGAR_COMPONENTE -> {
                    Component c = (Component) request.getData();
                    if(trivias.contains(c.getTrivia())){
                        ArrayList<String> cs = components.get(c.getTrivia());
                        if(cs != null){
                            if(cs.contains(c.getId())){
                                ErrorsLP.addError(c.getId(), request.getLine(), request.getCol(),"Semantico", "Componente ya existente");
                                verify = false;
                            }
                            else components.get(c.getTrivia()).add(c.getId());
                        }
                        else {
                            ArrayList<String> comps = new ArrayList<>();
                            comps.add(c.getId());
                            components.put(c.getTrivia(), comps);
                        }
                    }
                    else {
                        /*No hay trivia*/
                        ErrorsLP.addError(c.getId(), request.getLine(), request.getCol(),"Semantico", "Trivia no existente");
                        verify = false;
                    }
                }
                case RequestXSON.MODIFICAR_COMPONENTE -> {
                    Component c = (Component) request.getData();
                    ArrayList<String> cs = components.get(c.getTrivia());
                    if(cs != null){
                        if(!cs.contains(c.getId())){
                            ErrorsLP.addError(c.getId(), request.getLine(), request.getCol(),"Semantico", "Componente no encontrado");
                            verify = false;
                        }
                    }
                    else {
                        ErrorsLP.addError(c.getId(), request.getLine(), request.getCol(),"Semantico", "Componente no encontrado");
                        verify = false;
                    }


                }
                case RequestXSON.ELIMINAR_COMPONENTE -> {
                    String c = request.getId();
                    String t = request.getId2();

                    var cs = components.get(t);
                    if(cs != null){
                        if(cs.contains(c)){
                            components.get(t).remove(c);
                        }
                        else {
                            ErrorsLP.addError(c, request.getLine(), request.getCol(),"Semantico", "Componente no encontrado");
                            verify = false;
                        }
                    }
                    else {
                        ErrorsLP.addError(c, request.getLine(), request.getCol(),"Semantico", "Componente no encontrado");
                        verify = false;
                    }
                }
            }
        }

        return verify;
    }
    public static void execute(String text){
        if(verifyRequests(text)){
            for(RequestXSON<?> request : requests){
                switch (request.getType()){
                    case RequestXSON.USUARIO_NUEVO -> {
                        User u = (User) request.getData();
                        userDAO.insertUser(u);
                    }
                    case RequestXSON.MODIFICAR_USUARIO -> {
                        User u = (User) request.getData();
                        String oldUser = request.getId();
                        userDAO.updateUser(u, oldUser);
                    }
                    case RequestXSON.ELIMINAR_USUARIO -> {
                        userDAO.deleteUser(request.getId());
                    }
                    case RequestXSON.NUEVA_TRIVIA -> {
                        Trivia t = (Trivia) request.getData();
                        triviaDAO.insertTrivia(t);
                    }
                    case RequestXSON.MODIFICAR_TRIVIA -> {
                        Trivia t = (Trivia) request.getData();
                        triviaDAO.updateTrivia(t);
                    }
                    case RequestXSON.ELIMINAR_TRIVIA -> {
                        String t = request.getId();
                        triviaDAO.deleteTrivia(t);
                    }
                    case RequestXSON.AGREGAR_COMPONENTE -> {
                        Component c = (Component) request.getData();
                        triviaDAO.insertComponent(c);
                    }
                    case RequestXSON.MODIFICAR_COMPONENTE -> {
                        Component c = (Component) request.getData();
                        triviaDAO.updateComponent(c);
                    }
                    case RequestXSON.ELIMINAR_COMPONENTE -> {
                        String t = request.getId();
                        String c = request.getId2();
                        triviaDAO.deleteComponent(t, c);
                    }
                }
            }
        }
    }
}
