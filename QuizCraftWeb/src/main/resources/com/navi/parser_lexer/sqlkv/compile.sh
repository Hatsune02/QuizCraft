#! /bin/bash
echo "STARTING JFLEX COMPILING"
java -jar /home/dog/flexycup/jflex-full-1.9.1.jar -d ../../../../../java/com/navi/quizcraftweb/backend/parser_lexer/sqlkv SqlLexer.flex

echo "STARTING CUP COMPILING"
java -jar /home/dog/flexycup/java-cup-11b.jar -parser SqlParser SqlParser.cup
mv SqlParser.java ../../../../../java/com/navi/quizcraftweb/backend/parser_lexer/sqlkv/SqlParser.java
mv sym.java ../../../../../java/com/navi/quizcraftweb/backend/parser_lexer/sqlkv/sym.java