package com.navi.quizcraftweb.backend.parser_lexer.db.objs;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Position {
    private int line1, col1;
    private int line2, col2;
    public Position(int line1, int col1) {
        this.line1 = line1;
        this.col1 = col1;
    }
}
