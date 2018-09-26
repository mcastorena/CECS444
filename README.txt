CECS 444 Sec 01 5559
Project 1 - A4 Lexicon
Team LBC
Minhkhoa Vu	minhkhoavu954@gmail.com
Anthony Myers	anthonysmyers@yahoo.com
Juan Espinoza	jaespin30@yahoo.com
Miguel Casterona miguel.angel.castorena@gmail.com

Introduction
This program takes .txt file that contains A4 Language code and produces a list of tokens for the program.

Contents
LexerProgram.java
MainApplication.java
README.txt

External Requirements
1. Java must be installed.
2. Must have a properly formatted .txt file with A4 Language code for tokenization

Setup and Installation
N/A

Sample Invocation and Results

Enter the path of the file: /Users/mcastro/git/CECS4441/444-p1_TBD/lexer-sample-2.txt
(Tok: 11 line= 1 str= "main")
(Tok: 33 line= 1 str= "{")
(Tok: 23 line= 2 str= "print")
(Tok: 37 line= 2 str= "(")
(Tok: 5 line= 2 str= "Input length> ")
(Tok: 2 line= 3 str= "length")
(Tok: 45 line= 3 str= "=")
(Tok: 22 line= 3 str= "input")
(Tok: 37 line= 3 str= "(")
(Tok: 15 line= 3 str= "float")
(Tok: 23 line= 4 str= "print")
(Tok: 37 line= 4 str= "(")
(Tok: 5 line= 4 str= "Input width> ")
(Tok: 2 line= 5 str= "width")
(Tok: 45 line= 5 str= "=")
(Tok: 22 line= 5 str= "input")
(Tok: 37 line= 5 str= "(")
(Tok: 15 line= 5 str= "float")
(Tok: 23 line= 6 str= "print")
(Tok: 37 line= 6 str= "(")
(Tok: 5 line= 6 str= "Input height> ")
(Tok: 2 line= 7 str= "height")
(Tok: 45 line= 7 str= "=")
(Tok: 22 line= 7 str= "input")
(Tok: 37 line= 7 str= "(")
(Tok: 15 line= 7 str= "float")
(Tok: 2 line= 8 str= "volume")
(Tok: 45 line= 8 str= "=")
(Tok: 2 line= 8 str= "length")
(Tok: 41 line= 8 str= "*")
(Tok: 2 line= 8 str= "width")
(Tok: 41 line= 8 str= "*")
(Tok: 23 line= 9 str= "print")
(Tok: 37 line= 9 str= "(")
(Tok: 5 line= 9 str= "Volume= ")
(Tok: 6 line= 9 str= ",")
(Tok: 2 line= 9 str= "volume")
(Tok: 34 line= 10 str= "}")
(Tok: 0 line= 10 str= "")

lexer-sample-2.txt contents:

prog main { // Find the volume of a cube
    print ( "Input length> " );
    length = input ( float );
    print ( "Input width> " );
    width = input ( float );
    print ( "Input height> " );
    height = input ( float );
    volume = length * width * height;
    print ( "Volume= " , volume );
}


Features
This program takes A4 language code in .txt files and outputs a list of tokens for that program.

Bugs
N/A, project runs with no issues
