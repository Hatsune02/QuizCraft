package com.navi.quizcraftweb.backend.parser_lexer.request;

import com.navi.quizcraftweb.backend.dao.UserDAO;
import com.navi.quizcraftweb.backend.model.User;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBLexer;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.request.objs.RequestXSON;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

public class CompileRequest {
    private static Reader reader;
    private static RequestLexer lexer;
    private static RequestParser parser;
    public static ArrayList<RequestXSON> requests;
    private static UserDAO userDAO;

    public static void Compile(String text){
        reader = new StringReader(text);
        lexer = new RequestLexer(reader);
        parser = new RequestParser(lexer);
        requests = new ArrayList<>();
        userDAO = new UserDAO();
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
    public static void verifyRequests(String text){
        Compile(text);
        if(parser.usernames.isEmpty()){
            
        }
    }
}
