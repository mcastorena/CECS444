CECS 444 Sec 01 5559
Project 1 - A4 Lexicon
Team LBC
Minhkhoa Vu	minhkhoavu954@gmail.com
Anthony Myers	anthonysmyers@yahoo.com
Juan Espinoza	jaespin30@yahoo.com
Miguel Castorena miguel.angel.castorena@gmail.com

Introduction
This program takes .txt file that contains A4 Language code and produces a list of tokens for the program.

Algorithm
N/A

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

Enter the path of the file: /Users/mcastro/git/CECS444/444-p1_TBD/a4-sample-1.txt
(Tok: 10 line= 1 str= "prog")
(Tok: 11 line= 1 str= "main")
(Tok: 33 line= 1 str= "{")
(Tok: 23 line= 1 str= "print")
(Tok: 37 line= 1 str= "(")
(Tok: 5 line= 1 str= "ASCII:")
(Tok: 6 line= 1 str= ",")
(Tok: 5 line= 1 str= " A= ")
(Tok: 6 line= 1 str= ",")
(Tok: 3 line= 1 str= "65" int= 65)
(Tok: 6 line= 1 str= ",")
(Tok: 5 line= 1 str= " Z= ")
(Tok: 6 line= 1 str= ",")
(Tok: 3 line= 1 str= "90" int= 90)
(Tok: 38 line= 1 str= ")")
(Tok: 7 line= 1 str= ";")
(Tok: 34 line= 1 str= "}")
(Tok: 0 line= 1 str= "")

a4-sample-1.txt contents:
prog main { print( "ASCII:", " A= ", 65, " Z= ", 90 ); }

Enter the path of the file: /Users/mcastro/git/CECS444/444-p1_TBD/A4-sample-2.txt
(Tok: 10 line= 1 str= "prog")
(Tok: 11 line= 1 str= "main")
(Tok: 33 line= 1 str= "{")
(Tok: 2 line= 2 str= "pi")
(Tok: 45 line= 2 str= "=")
(Tok: 4 line= 2 str= "3.14" float= 3.14)
(Tok: 7 line= 2 str= ";")
(Tok: 23 line= 3 str= "print")
(Tok: 37 line= 3 str= "(")
(Tok: 5 line= 3 str= "Input radius> ")
(Tok: 38 line= 3 str= ")")
(Tok: 7 line= 3 str= ";")
(Tok: 2 line= 4 str= "rx")
(Tok: 45 line= 4 str= "=")
(Tok: 22 line= 4 str= "input")
(Tok: 37 line= 4 str= "(")
(Tok: 15 line= 4 str= "float")
(Tok: 38 line= 4 str= ")")
(Tok: 7 line= 4 str= ";")
(Tok: 2 line= 5 str= "circum")
(Tok: 45 line= 5 str= "=")
(Tok: 3 line= 5 str= "2" int= 2)
(Tok: 41 line= 5 str= "*")
(Tok: 2 line= 5 str= "pi")
(Tok: 41 line= 5 str= "*")
(Tok: 2 line= 5 str= "rx")
(Tok: 7 line= 5 str= ";")
(Tok: 23 line= 6 str= "print")
(Tok: 37 line= 6 str= "(")
(Tok: 5 line= 6 str= "Circumf= ")
(Tok: 6 line= 6 str= ",")
(Tok: 2 line= 6 str= "circum")
(Tok: 38 line= 6 str= ")")
(Tok: 7 line= 6 str= ";")
(Tok: 34 line= 7 str= "}")
(Tok: 0 line= 7 str= "")

A4-sample-2.txt contents:
prog main { // Find the circumference of a circle.
pi = 3.14;
print( "Input radius> " );
rx = input ( float );
circum = 2 * pi * rx;
print( "Circumf= ", circum );
}

Features
This program takes A4 language code in .txt files and outputs a list of tokens for that program.

Bugs
There is only one bug in the program. If the .txt file does not have the proper spacing between token values, 
the program will not run correctly. 
As long as the .txt file is properly formatted, the program will run with no errors.
