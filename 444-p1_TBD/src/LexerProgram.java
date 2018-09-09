import java.util.Dictionary; 
import java.io.File; 
import java.util.Scanner;

public class LexerProgram {
	private Dictionary tokenCollection;
	
	public LexerProgram(){
		tokenCollection.put(6, ',');
		tokenCollection.put(7, ';');
		tokenCollection.put(10, "prog");
		tokenCollection.put(11, "main");
		tokenCollection.put(12, "fcn");
		tokenCollection.put(13, "class");
		tokenCollection.put(15, "float");
		tokenCollection.put(16, "int");
		tokenCollection.put(17, "string");
		tokenCollection.put(18, "if");
		tokenCollection.put(19, "elseif");
		tokenCollection.put(20, "else");
		tokenCollection.put(21, "while");
		tokenCollection.put(22, "input");
		tokenCollection.put(23, "print");
		tokenCollection.put(24, "new");
		tokenCollection.put(25, "return");
		tokenCollection.put(26, "var");
		tokenCollection.put(31, '<');
		tokenCollection.put(32, '>');
		tokenCollection.put(33, '{');
		tokenCollection.put(34, '}');
		tokenCollection.put(35, '[');
		tokenCollection.put(36, ']');
		tokenCollection.put(37, '(');
		tokenCollection.put(38, ')');
		tokenCollection.put(41, '*');
		tokenCollection.put(42, '^');
		tokenCollection.put(43, ':');
		tokenCollection.put(44, '.');
		tokenCollection.put(45, '=');
		tokenCollection.put(46, '-');
		tokenCollection.put(47, '+');
		tokenCollection.put(48, '/');
		tokenCollection.put(51, "->");
		tokenCollection.put(52, "==");
		tokenCollection.put(53, "!=");
		tokenCollection.put(54, "<=");
		tokenCollection.put(55, ">=");
		tokenCollection.put(56, "<<");
		tokenCollection.put(57, ">>");
	}
	

}
