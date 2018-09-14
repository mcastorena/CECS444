import java.util.ArrayList;
//import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
//import java.util.List;
import java.io.File; 
import java.util.Scanner;
//import java.util.regex.Pattern;

public class LexerProgram {
	private Map<String, Integer> tokenCollection;
	
	public LexerProgram(){
		tokenCollection =  new HashMap<String, Integer>();
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
	
	public void ReadPrintFileTokens(File fileName) throws Exception{
		//File tokenFile= new File(fileName);
		Scanner scLines = new Scanner(fileName);
		String currentStr;
		String collectStr = "";
		int count = 1;
		//boolean startQuote = false;
		int intCheck;
		double doubleCheck;
		ArrayList<String> lineStrings = new ArrayList<String>();
		ArrayList<String> allIdentifiers = new ArrayList<String>();
		while(scLines.hasNextLine()){
			String lineRead = scLines.nextLine();
			Scanner sc = new Scanner(lineRead);
			while(sc.hasNext()){
				collectStr = "";
				currentStr = sc.next();
				lineStrings.add(currentStr);
				if(!currentStr.equals("//")){
					if(tokenCollection.containsKey(currentStr) && !currentStr.equals("=")){
						System.out.println("(Tok: "+tokenCollection.get(currentStr)+" line= "+count+" str= \""+currentStr+"\")");
						//System.out.print(currentStr);
					}
					else{
						if(!currentStr.contains(".")){
							try{
								intCheck = Integer.parseInt(currentStr);
								System.out.println("(Tok: 3 line= "+count+" str= \""+currentStr+"\" int= "+intCheck+")");
								//System.out.print(currentStr);
							}
							catch(NumberFormatException e){ }
						}
						if(currentStr.contains(".")){
							try{
								doubleCheck = Double.parseDouble(currentStr);
								System.out.println("(Tok: 4 line= "+count+" str= \""+currentStr+"\" float= "+doubleCheck+")");
								//System.out.print(currentStr);
							}
							catch(NumberFormatException e){	}
						}
					}
					if(lineStrings.size() > 1 && lineStrings.size() < 3){
						if(lineStrings.get(1).equals("=")){
							System.out.println("(Tok: 2 line= "+count+" str= \""+lineStrings.get(0)+"\")");
							System.out.println("(Tok: 45 line= "+count+" str= \""+currentStr+"\")");
							allIdentifiers.add(lineStrings.get(0));
							//System.out.print(currentStr);
						}
					}
					if(currentStr.charAt(0) == '"' && currentStr.charAt(currentStr.length()-1) == '"' && currentStr.length() > 1){
						System.out.println("(Tok: 5 line= "+count+" str= \""+currentStr.substring(1, currentStr.length()-1)+"\")");
						//System.out.print(currentStr);
					}
					if(currentStr.equals("\"") /*&& !startQuote*/){
						//sc.useDelimiter("\" ");
						//System.out.print(currentStr);
						//startQuote = true;
						currentStr = sc.next();
						while(!currentStr.equals("\"")){
							if(!currentStr.equals("\"")){
								if(collectStr.equals("")){
									collectStr = currentStr;
								}
								else{
									collectStr = collectStr+" "+currentStr;
								}
							}
							currentStr = sc.next();
						}
						System.out.println("(Tok: 5 line= "+count+" str= \" "+collectStr+" \")");
						//System.out.print(currentStr);
					}
					if(currentStr.charAt(0) == '"' && currentStr.length() > 1 && currentStr.charAt(currentStr.length()-1) != '"'){
						//collectStr = currentStr.substring(1, currentStr.length()-1);
						while(!currentStr.equals("\"")){
							if(!currentStr.equals("\"")){
								if(collectStr.equals("")){
									collectStr = currentStr.substring(1);
								}
								else{
									collectStr = collectStr+" "+currentStr;
								}
							}
							currentStr = sc.next();
						}
						System.out.println("(Tok: 5 line= "+count+" str= \""+collectStr+" \")");
					}
					/*if(currentStr.equals("\"") && startQuote){
						startQuote = false;
					}*/
					if(allIdentifiers.contains(currentStr)){
						System.out.println("(Tok: 2 line= "+count+" str= \""+currentStr+"\")");
					}
					if(currentStr.equals("\n")){
						
					}
				}
			}
			count++;
			lineStrings.clear();
			sc.close();
		}
		System.out.println("(Tok: 0 line= "+(count-1)+" str= \"\")");
		scLines.close();
	}
}
