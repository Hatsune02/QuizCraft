package com.navi.quizcraftweb.backend.dao;

import com.navi.quizcraftweb.backend.model.*;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import com.navi.quizcraftweb.backend.parser_lexer.TError;
import com.navi.quizcraftweb.backend.parser_lexer.db.DBParser;
import com.navi.quizcraftweb.backend.parser_lexer.db.objs.Position;

import java.util.*;

public class TriviaDAO {
    public List<Trivia> select(){
        return Connection.connectTriviaDB().trivias;
    }
    public Trivia viewTrivia(String idTrivia){
        for(Trivia trivia : select()){
            if(trivia.getIdTrivia().equals(idTrivia)) return trivia;
        }
        return null;
    }
    public void insertTrivia(Trivia trivia){
        boolean valid = true;
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<String> idsTrivia = parser.idsTrivia;
        Position finalPos = parser.finalPosTrivia;

        for(String i : idsTrivia){
            if(trivia.getIdTrivia().equals(i)){
                valid = false;
                break;
            }
        }
        if(valid){
            int position = Connection.calculatePosition(Connection.text, finalPos.getLine1(), finalPos.getCol1());
            String insertText = trivia.dbString() + "\n";
            if(!idsTrivia.isEmpty()) insertText = ",\n"+insertText;
            Connection.insertTextTrivia(position, insertText);
        }
        else{
            System.out.println("Trivia ya existente");
        }
    }
    public void updateTrivia(Trivia trivia){
        boolean valid = false;
        Trivia actualTrivia = new Trivia();
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<Position> positions = parser.positionsTrivia;
        int pos = 0;

        for(Trivia t : parser.trivias){
            if(t.getIdTrivia().equals(trivia.getIdTrivia())){
                actualTrivia = t;
                valid = true;
                break;
            }
            pos++;
        }

        if(valid){
            Position position = positions.get(pos);
            int startPosition = Connection.calculatePosition(Connection.text, position.getLine1(), position.getCol1());
            int endPosition = Connection.calculatePosition(Connection.text, position.getLine2(), position.getCol2());

            if(trivia.getName()!=null) actualTrivia.setName(trivia.getName());
            if(trivia.getTopic()!=null) actualTrivia.setTopic(trivia.getTopic());
            if(trivia.getQuestionTime()!=0) actualTrivia.setQuestionTime(trivia.getQuestionTime());

            Connection.updateTextTrivia(startPosition, endPosition+1, actualTrivia.dbString());
        }
        else {
            System.out.println("Trivia no encontrada");
        }
    }
    public void deleteTrivia(String idTrivia){
        boolean valid = false;
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<String> idsTrivia = parser.idsTrivia;
        ArrayList<Position> positions = parser.positionsTrivia;
        int pos = 0;

        for(String i : idsTrivia){
            if(i.equals(idTrivia)){
                valid = true;
                break;
            }
            pos++;
        }
        if(valid){
            Position position = positions.get(pos);
            int startPosition = Connection.calculatePosition(Connection.text, position.getLine1(), position.getCol1());
            int endPosition = Connection.calculatePosition(Connection.text, position.getLine2(), position.getCol2());
            if(Connection.text.charAt(startPosition-2) == ','){
                Connection.deleteTextTrivia(startPosition-3, endPosition+1);
            }
            else {
                if(Connection.text.charAt(endPosition+2) == ','){
                    Connection.deleteTextTrivia(startPosition-1, endPosition+3);
                }
                else{
                    Connection.deleteTextTrivia(startPosition-1, endPosition+1);
                }
            }
        }
        else{
            System.out.println("Trivia no encontrado");
        }
    }

    public void insertComponent(Component component){
        boolean validTrivia = false;
        boolean valid = true;
        Trivia trivia = new Trivia();
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<Trivia> trivias = parser.trivias;
        HashMap<String, Position> finalPos = parser.finalPosComponent;

        for(Trivia t: trivias){
            if(component.getTrivia().equals(t.getIdTrivia())){
                trivia = t;
                for(Component c: t.getComponents()){
                    if(c.getId().equals(component.getId())){
                        valid = false;
                        break;
                    }
                }
                validTrivia = true;
                break;
            }
        }
        if(!validTrivia) {
            System.out.println("Trivia no encontrada");
            return;
        }
        if(valid){
            component.setIndex(trivia.getComponents().size()+1);
            int position = Connection.calculatePosition(Connection.text, finalPos.get(trivia.getIdTrivia()).getLine1(), finalPos.get(trivia.getIdTrivia()).getCol1());
            String insertText = component.dbString() + "\n";
            if(!trivia.getComponents().isEmpty()) insertText = "\t,\n"+insertText;

            Connection.insertTextTrivia(position, insertText);
        }
        else{
            System.out.println("Componente ya existente");
        }
    }
    public void updateComponent(Component component){
        boolean validTrivia = false;
        boolean valid = false;
        Trivia trivia = new Trivia();
        Component actualComponent = new Component();
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<Trivia> trivias = parser.trivias;
        HashMap<String, Position> positions = parser.positionsComponents;

        for(Trivia t: trivias){
            if(component.getTrivia().equals(t.getIdTrivia())){
                trivia = t;
                for(Component c: t.getComponents()){
                    if(c.getId().equals(component.getId())){
                        actualComponent = c;
                        valid = true;
                        break;
                    }
                }
                validTrivia = true;
                break;
            }
        }
        if(!validTrivia) {
            System.out.println("Trivia no encontrada");
            return;
        }

        if(!valid){
            System.out.println("Componenente no encontrado");
            return;
        }

        Position position = positions.get(trivia.getIdTrivia());
        int startPosition = Connection.calculatePosition(Connection.text, position.getLine1(), position.getCol1());
        int endPosition = Connection.calculatePosition(Connection.text, position.getLine2(), position.getCol2());

        if(component.getVisibleText()!=null) actualComponent.setVisibleText(component.getVisibleText());
        if(!component.getAnswer().isEmpty()) actualComponent.setAnswer(component.getAnswer());
        if(component.getIndex()!=0) {
            int newIndex = component.getIndex();
            int actualIndex = actualComponent.getIndex();
            trivia.reOrderComponents(newIndex, actualIndex);
        }
        if(component.getClase()!=0){
            actualComponent.setOptions(new ArrayList<>());
            actualComponent.setLine(0);
            actualComponent.setColumns(0);
            actualComponent.setClase(component.getClase());
            actualComponent.setLine(component.getLine());
            actualComponent.setColumns(component.getColumns());
            actualComponent.setOptions(component.getOptions());
        }
        String txt = "(\n" + trivia.dbComponents() + "\t";
        Connection.updateTextTrivia(startPosition, endPosition+1, txt);
    }
    public void deleteComponent(String idTrivia, String idComponent){
        boolean validTrivia = false;
        boolean valid = false;
        Trivia trivia = new Trivia();
        Component actualComponent = new Component();
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<Trivia> trivias = parser.trivias;
        HashMap<String, Position> positions = parser.positionsComponents;

        for(Trivia t: trivias){
            if(idTrivia.equals(t.getIdTrivia())){
                trivia = t;
                for(Component c: t.getComponents()){
                    if(c.getId().equals(idComponent)){
                        actualComponent = c;
                        valid = true;
                        break;
                    }
                }
                validTrivia = true;
                break;
            }
        }
        if(!validTrivia) {
            System.out.println("Trivia no encontrada");
            return;
        }

        if(!valid){
            System.out.println("Componenente no encontrado");
            return;
        }

        Position position = positions.get(trivia.getIdTrivia());
        int startPosition = Connection.calculatePosition(Connection.text, position.getLine1(), position.getCol1());
        int endPosition = Connection.calculatePosition(Connection.text, position.getLine2(), position.getCol2());

        trivia.removeComponent(actualComponent.getIndex()-1);

        String txt = "(\n" + trivia.dbComponents() + "\t";
        Connection.updateTextTrivia(startPosition, endPosition+1, txt);

    }

    public void addCollectedData(CollectedData data){
        boolean valid = false;
        Trivia actualTrivia = new Trivia();
        DBParser parser = Connection.connectTriviaDB();
        ArrayList<Position> positions = parser.positionsTrivia;
        int pos = 0;

        for(Trivia t : parser.trivias){
            if(t.getIdTrivia().equals(data.getTrivia())){
                actualTrivia = t;
                valid = true;
                break;
            }
            pos++;
        }

        if(valid){
            Position position = positions.get(pos);
            int startPosition = Connection.calculatePosition(Connection.text, position.getLine1(), position.getCol1());
            int endPosition = Connection.calculatePosition(Connection.text, position.getLine2(), position.getCol2());

            actualTrivia.addData(data);
            Connection.updateTextTrivia(startPosition, endPosition+1, actualTrivia.dbString());
        }
        else {
            System.out.println("Trivia no encontrada");
        }
    }
}
