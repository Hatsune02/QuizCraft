#! /bin/bash
echo "STARTING JFLEX COMPILING"
java -jar /home/dog/flexycup/jflex-full-1.9.1.jar -d ../main/java/com/navi/quizcraftapp/parser_lexer/ Lexer.flex

echo "STARTING CUP COMPILING"
java -jar /home/dog/flexycup/java-cup-11b.jar -parser Parser Parser.cup
mv Parser.java ../main/java/com/navi/quizcraftapp/parser_lexer/Parser.java
mv sym.java ../main/java/com/navi/quizcraftapp/parser_lexer/sym.java