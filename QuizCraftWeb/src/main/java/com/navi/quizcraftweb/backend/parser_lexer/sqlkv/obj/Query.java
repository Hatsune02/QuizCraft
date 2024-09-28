package com.navi.quizcraftweb.backend.parser_lexer.sqlkv.obj;

import com.navi.quizcraftweb.backend.dao.TriviaDAO;
import com.navi.quizcraftweb.backend.model.CollectedData;
import com.navi.quizcraftweb.backend.model.Trivia;

import java.util.ArrayList;
import java.util.Iterator;

public class Query {
    private static TriviaDAO triviaDAO;
    public static ArrayList<Report> reports = new ArrayList<>();

    public static void selectAll(){
        reports = new ArrayList<>();
        triviaDAO = new TriviaDAO();
        var trivias = triviaDAO.select();
        for (Trivia t : trivias) {
            reports.addAll(showData(t));
        }
    }
    public static void selectAll(ArrayList<Condition> conditions){
        reports = new ArrayList<>();
        triviaDAO = new TriviaDAO();
        var trivias = triviaDAO.select();
        for (Trivia t : trivias) {
            reports.addAll(showData(t, conditions));
        }
    }

    public static void select(ArrayList<String> idTrivias){
        reports = new ArrayList<>();
        triviaDAO = new TriviaDAO();
        var trivias = triviaDAO.select();
        for (Trivia t : trivias) {
            if(idTrivias.contains(t.getIdTrivia())){
                reports.addAll(showData(t));
            }
        }
    }
    public static void select(ArrayList<String> idTrivias, ArrayList<Condition> conditions){
        reports = new ArrayList<>();
        triviaDAO = new TriviaDAO();
        var trivias = triviaDAO.select();
        for (Trivia t : trivias) {
            if(idTrivias.contains(t.getIdTrivia())){
                reports.addAll(showData(t, conditions));
            }
        }
    }

    private static ArrayList<Report> showData(Trivia trivia){
        ArrayList<Report> reports = new ArrayList<>();
        for(CollectedData data: trivia.getCollectedData()){
            var report = new Report(trivia.getName(), trivia.getTopic(), trivia.getComponents().size(), data);
            reports.add(report);
        }
        return reports;
    }

    private static ArrayList<Report> showData(Trivia trivia, ArrayList<Condition> conditions){
        ArrayList<Report> reports = new ArrayList<>();
        for(CollectedData data: trivia.getCollectedData()){
            if(conditions(data, conditions)){
                var report = new Report(trivia.getName(), trivia.getTopic(), trivia.getComponents().size(), data);
                reports.add(report);
            }
        }
        return reports;
    }

    public static boolean conditions(CollectedData data, ArrayList<Condition> filter){
        Iterator<Condition> iterator = filter.iterator();

        // Evaluamos la primera condición sin necesidad de verificar el tipo
        Condition firstCondition = iterator.next();
        boolean result = firstCondition.filterTrivia(data);

        // Evaluamos las siguientes condiciones con los operadores AND y OR
        while (iterator.hasNext()) {
            Condition currentCondition = iterator.next();

            if (currentCondition.getType() == Condition.AND) {
                // Si ya es false con AND, podemos terminar temprano
                result = result && currentCondition.filterTrivia(data);
                if (!result) break;
            } else if (currentCondition.getType() == Condition.OR) {
                // Si ya es true con OR, podemos terminar temprano
                result = result || currentCondition.filterTrivia(data);
                if (result) break;
            }
        }

        return result;
    }
}
