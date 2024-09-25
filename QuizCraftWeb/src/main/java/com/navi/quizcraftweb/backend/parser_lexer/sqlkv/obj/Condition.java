package com.navi.quizcraftweb.backend.parser_lexer.sqlkv.obj;
import com.navi.quizcraftweb.backend.model.CollectedData;
import com.navi.quizcraftweb.backend.model.Component;
import com.navi.quizcraftweb.backend.model.Trivia;
import com.navi.quizcraftweb.backend.parser_lexer.ErrorsLP;
import lombok.*;

@Getter @Setter
public class Condition {
    /* field */
    public static final int USUARIO = 1;
    public static final int TIEMPO_TOTAL = 2;
    public static final int PUNTEO = 3;
    /* type */
    public static final int AND = 1;
    public static final int OR = 2;

    private int field;
    private String sign;
    private String value;
    private int type;

    public Condition(int field, String sign, String value) {
        this.field = field;
        this.sign = sign;
        this.value = value;
        this.type = 0;
    }
    public boolean filterTrivia(CollectedData data){
        return switch (sign) {
            case "=" -> filterE(data);
            case ">" -> filterG(data);
            case "<" -> filterL(data);
            case ">=" -> filterGE(data);
            case "<=" -> filterLE(data);
            default -> false;
        };
    }
/*    public boolean filterData(Component component){
        return switch (sign) {
            case "=" -> filterE(component);
            case ">" -> filterG(component);
            case "<" -> filterL(component);
            case ">=" -> filterGE(component);
            case "<=" -> filterLE(component);
            default -> false;
        };
    }*/

    public boolean filterE(CollectedData data){
        if(isNumber(value)){
            try{
                if (field == TIEMPO_TOTAL) {
                    return data.getTime() == Integer.parseInt(value);
                }
                else if(field == PUNTEO){
                    return data.getScore() == Integer.parseInt(value);
                }
                ErrorsLP.addError(value, 1, 1, "Semantico", "No se puede comparar un numero en este campo");
                return false;
            }catch (Exception e){
                return false;
            }
        }
        if (type == USUARIO) {
            return data.getUsername().equals(value);
        }
        ErrorsLP.addError(value, 1, 1, "Semantico", "No se puede comparar un string en este campo");
        return false;
    }


    public boolean filterG(CollectedData data){
        return false;
    }
    public boolean filterL(CollectedData data){
        return false;
    }
    public boolean filterGE(CollectedData data){
        return false;
    }
    public boolean filterLE(CollectedData data){
        return false;
    }

    public boolean isNumber(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
