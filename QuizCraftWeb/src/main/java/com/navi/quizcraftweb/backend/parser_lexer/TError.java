package com.navi.quizcraftweb.backend.parser_lexer;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class TError {
    private String lexeme;
    private int line;
    private int column;
    private String type;
    private String description;


}
