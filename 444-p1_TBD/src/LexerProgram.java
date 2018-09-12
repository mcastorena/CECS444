import java.util.Dictionary;
import java.awt.List;
import java.io.File; 
import java.util.Scanner;

public class LexerProgram {
	private Dictionary<String, Integer> tokenCollection;
	
	public LexerProgram(){
		tokenCollection.put(",", 6);
		tokenCollection.put(";", 7);
		tokenCollection.put("prog", 10);
		tokenCollection.put("main", 11);
		tokenCollection.put("fcn", 12);
		tokenCollection.put("class", 13);
		tokenCollection.put("float", 15);
		tokenCollection.put("int", 16);
		tokenCollection.put("string", 17);
		tokenCollection.put("if", 18);
		tokenCollection.put("elseif", 19);
		tokenCollection.put("else", 20);
		tokenCollection.put("while", 21);
		tokenCollection.put("input", 22);
		tokenCollection.put("print", 23);
		tokenCollection.put("new", 24);
		tokenCollection.put("return", 25);
		tokenCollection.put("var", 26);
		tokenCollection.put("<", 31);
		tokenCollection.put(">", 32);
		tokenCollection.put("{", 33);
		tokenCollection.put("}", 34);
		tokenCollection.put("[", 35);
		tokenCollection.put("]", 36);
		tokenCollection.put("(", 37);
		tokenCollection.put(")", 38);
		tokenCollection.put("*", 41);
		tokenCollection.put("^", 42);
		tokenCollection.put(":", 43);
		tokenCollection.put(".", 44);
		tokenCollection.put("=", 45);
		tokenCollection.put("-", 46);
		tokenCollection.put("+", 47);
		tokenCollection.put("/", 48);
		tokenCollection.put("->", 51);
		tokenCollection.put("==", 52);
		tokenCollection.put("!=", 53);
		tokenCollection.put("<=", 54);
		tokenCollection.put(">=", 55);
		tokenCollection.put("<<", 56);
		tokenCollection.put(">>", 57);
	}
	
	public void ReadPrintFileTokens(String fileName) throws Exception{
		File tokenFile= new File(fileName);
		Scanner sc = new Scanner(tokenFile);
		String currentStr;
		int count = 0;
		int intCheck;
		double doubleCheck;
		List lineStrings = new List();
		while(sc.hasNextLine()){
			while(sc.hasNext()){
				currentStr = sc.next();
				lineStrings.add(currentStr);
				if(!currentStr.equals("//")){
					if(tokenCollection.keys().equals(currentStr)){
						System.out.println("(Tok: "+tokenCollection.get(currentStr)+" line= "+count+" str= \""+currentStr+"\"");
					}
					else{
						try{
							intCheck = Integer.parseInt(currentStr);
							System.out.println("(Tok: 3 line= "+count+" str= \""+currentStr+"\" int= "+intCheck);
						}
						catch(NumberFormatException e){ }
						try{
							doubleCheck = Double.parseDouble(currentStr);
							System.out.println("(Tok: 4 line= "+count+" str= \""+currentStr+"\" int= "+doubleCheck);
						}
						catch(NumberFormatException e){	}
					}
					if(lineStrings.getItemCount() > 1){
						if(lineStrings.getItem(1).equals("=")){
							System.out.println("(Tok: 2 line= "+count+" str= \""+currentStr+"\")");
						}
					}
				}
				count++;
				lineStrings.removeAll();
				sc.nextLine();
			}
		}
		sc.close();
	}
	

}
