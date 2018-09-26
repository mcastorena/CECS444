import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File; 
import java.util.Scanner;

public class LexerProgram {
	
	private Map<String, Integer> tokenCollection;	//Contains all token values from A4 Lexicon
	private boolean isChecked, isID = true;			//Checks for any syntax and semantics errors
	private boolean isNotComment = true;			//Checks for comments
	
	/**
	 * Default Constructor for a LexerProgram object, it initializes
	 * values in the LexerProgram class property tokenCollection that
	 * holds all token values from the A4 Lexicon
	 */
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
	
	/**
	 * ReadPrintFileTokens reads a file of source code and prints out all tokens
	 * that exist in the source code based on the A4 Lexicon
	 * 
	 * @param fileName File object that will be read and parsed by a scanner
	 * @throws Exception if files is not found or types are not parsed correctly
	 */
	public void ReadPrintFileTokens(File fileName) throws Exception{
		Scanner scLines = new Scanner(fileName);						//Scanner to read string values line by line
		String currentStr;												//String used to reference the most recently scanned string from source code text
		String collectStr = "";											//String used for values stored between double quotes
		int lineCount = 1;												//Integer that keeps track of the current line scanned in source code text
		ArrayList<String> lineStrings = new ArrayList<String>();		//ArrayList of all string values within a line from source code text 
		ArrayList<String> allIdentifiers = new ArrayList<String>();		//ArrayList of any identifiers referenced in source code text
		while(scLines.hasNextLine() && (isChecked || isID)){
			String lineRead = scLines.nextLine();
			Scanner sc = new Scanner(lineRead);
			isNotComment = true;
			while(sc.hasNext() && (isChecked || isID)){
				collectStr = "";
				currentStr = sc.next();
				lineStrings.add(currentStr);
				if(!isNotComment){
					isID = true;
				}
				if(!currentStr.equals("//") && isNotComment){
					isChecked = false;
					if(lineStrings.size()>2){
						isID = false;
					}
					else{
						isID = true;
					}
					TokenCollectionCheck(currentStr, lineCount);
					NumberTokenizer(currentStr, lineCount);
					AddIdentifierToken(currentStr, lineCount, lineStrings, allIdentifiers);
					QuoteTokenizer(currentStr, lineCount, collectStr, sc);
					UseIdentifierToken(currentStr, lineCount, allIdentifiers);
					if(!isChecked && !isID){
						System.out.println("(Tok: 99 line= "+(lineCount-1)+" str= \"error\")");
					}
				}
				else{
					isNotComment = false;
				}
			}
			lineCount++;
			lineStrings.clear();
			sc.close();
		}
		System.out.println("(Tok: 0 line= "+(lineCount-1)+" str= \"\")");
		scLines.close();
	}
	
	/**
	 * AddIdentifierToken parses and adds a new identifier to the collection of all identifiers referenced
	 * in the source code and then prints the resulting tokens
	 * 
	 * @param currentStr String value of the most recently parsed String from the source code
	 * @param lineCount Integer value that keeps track of the current line of the source code
	 * @param lineStrings ArrayList of String objects of all string values in a single line from the source code
	 * @param allIdentifiers ArrayList of String objects of all identifiers that have been instantiated and referenced in the source code
	 */
	public void AddIdentifierToken(String currentStr, int lineCount, ArrayList<String> lineStrings, ArrayList<String> allIdentifiers){
		if(lineStrings.size() == 2){
			if(lineStrings.get(1).equals("=")){
				System.out.println("(Tok: 2 line= "+lineCount+" str= \""+lineStrings.get(0)+"\")");
				System.out.println("(Tok: 45 line= "+lineCount+" str= \""+currentStr+"\")");
				isChecked = true;
				allIdentifiers.add(lineStrings.get(0));
			}
		}
	}
	
	/**
	 * UseIdentifierToken checks if the current String value is one of the identifier that was previously
	 * referenced in the source code text file and then prints out the resulting tokens
	 * 
	 * @param currentStr String value of the most recently parsed String from the source code
	 * @param lineCount Integer value that keeps track of the current line of the source code
	 * @param allIdentifiers ArrayList of String objects of all identifiers that have been instantiated and referenced in the source code
	 */
	public void UseIdentifierToken(String currentStr, int lineCount, ArrayList<String> allIdentifiers){
		if(allIdentifiers.contains(currentStr)){
			System.out.println("(Tok: 2 line= "+lineCount+" str= \""+currentStr+"\")");
			isChecked = true;
		}
		
		if(tokenCollection.containsKey(currentStr.substring(currentStr.length()-1))){
			if(allIdentifiers.contains(currentStr.substring(0, currentStr.length()-1))){
				System.out.println("(Tok: 2 line= "+lineCount+" str= \""+currentStr.substring(0, currentStr.length()-1)+"\")");
				System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
				isChecked = true;
			}
		}
	}
	
	/**
	 * TokenCollectionCheck uses a mapping of token key and values based on the A4 Lexicon to see
	 * if the currently scanned String is a value from the A4 Lexicon
	 * 
	 * @param currentStr String value of the most recently parsed String from the source code
	 * @param lineCount Integer value that keeps track of the current line of the source code
	 */
	public void TokenCollectionCheck(String currentStr, int lineCount){
		if((tokenCollection.containsKey(currentStr) && !currentStr.equals("="))||(tokenCollection.containsKey(currentStr.substring(0, currentStr.length()-1)))){
			if(tokenCollection.containsKey(currentStr)){
				System.out.println("(Tok: "+tokenCollection.get(currentStr)+" line= "+lineCount+" str= \""+currentStr+"\")");
				isChecked = true;
			}
			else{
				System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(0, currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(0, currentStr.length()-1)+"\")");
				System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
				isChecked = true;
			}
		}
	}
	
	/**
	 * QuoteTokenizer parses any Strings that were in double quotes from the original source code text
	 * and prints out the resulting tokens
	 * 
	 * @param currentStr String value of the most recently parsed String from the source code
	 * @param lineCount Integer value that keeps track of the current line of the source code
	 * @param collectStr
	 * @param sc
	 */
	public void QuoteTokenizer(String currentStr, int lineCount, String collectStr, Scanner sc){
		if(currentStr.charAt(0) == '"' && currentStr.charAt(currentStr.length()-1) == '"' && currentStr.length() > 1){
			System.out.println("(Tok: 5 line= "+lineCount+" str= \""+currentStr.substring(1, currentStr.length()-1)+"\")");
			isChecked = true;
		}
		if(currentStr.charAt(0) == '"' && tokenCollection.containsKey(currentStr.substring(currentStr.length()-1)) && currentStr.length() > 1 && currentStr.charAt(currentStr.length()-2) == '"' ){
			System.out.println("(Tok: 5 line= "+lineCount+" str= \""+currentStr.substring(1, currentStr.length()-2)+"\")");
			System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
			isChecked = true;
		}
		if(currentStr.equals("\"") && currentStr.length() == 1){
			currentStr = sc.next();
			while(!currentStr.equals("\"") && !currentStr.equals("\",")){
				if(!currentStr.equals("\"") && !currentStr.equals("\",")){
					if(collectStr.equals("")){
						collectStr = currentStr;
					}
					else{
						collectStr = collectStr+" "+currentStr;
					}
				}
				currentStr = sc.next();
			}
			if(currentStr.equals("\"")){
				System.out.println("(Tok: 5 line= "+lineCount+" str= \" "+collectStr+" \")");
				isChecked = true;
			}
			if(tokenCollection.containsKey(currentStr.substring(currentStr.length()-1))){
				System.out.println("(Tok: 5 line= "+lineCount+" str= \" "+collectStr+" \")");
				System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
				isChecked = true;
			}
		}
		if(currentStr.charAt(0) == '"' && currentStr.length() > 1 && currentStr.charAt(currentStr.length()-1) != '"' && currentStr.charAt(currentStr.length()-2) != '"'){
			while(!currentStr.equals("\"") && !currentStr.equals("\",")){
				if(!currentStr.equals("\"") && !currentStr.equals("\",")){
					if(collectStr.equals("")){
						collectStr = currentStr.substring(1);
					}
					else{
						collectStr = collectStr+" "+currentStr;
					}
				}
				currentStr = sc.next();
			}
			if(currentStr.equals("\"")){
				System.out.println("(Tok: 5 line= "+lineCount+" str= \""+collectStr+" \")");
				isChecked = true;
			}
			if(tokenCollection.containsKey(currentStr.substring(currentStr.length()-1))){
				System.out.println("(Tok: 5 line= "+lineCount+" str= \""+collectStr+" \")");
				System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
				isChecked = true;
			}
		}
	}
	
	/**
	 * NumberTokenizer checks to see if the currently scanned String can be parsed as either an 
	 * Integer value or a Float value and then prints out the resulting token
	 * 
	 * @param currentStr String value of the most recently parsed String from the source code
	 * @param lineCount Integer value that keeps track of the current line of the source code
	 */
	public void NumberTokenizer(String currentStr, int lineCount){
		if(!currentStr.contains(".")){
			try{
				int intCheck = Integer.parseInt(currentStr);
				System.out.println("(Tok: 3 line= "+lineCount+" str= \""+currentStr+"\" int= "+intCheck+")");
				isChecked = true;
			}
			catch(NumberFormatException e){ }
		}
		if(currentStr.contains(".")){
			try{
				double doubleCheck = Double.parseDouble(currentStr);
				System.out.println("(Tok: 4 line= "+lineCount+" str= \""+currentStr+"\" float= "+doubleCheck+")");
				isChecked = true;
			}
			catch(NumberFormatException e){	}
		}
		if(currentStr.contains(",") || currentStr.contains(";")){
			if(!currentStr.contains(".")){
				try{
					int intCheck = Integer.parseInt(currentStr.substring(0, currentStr.length()-1));
					System.out.println("(Tok: 3 line= "+lineCount+" str= \""+currentStr.substring(0, currentStr.length()-1)+"\" int= "+intCheck+")");
					System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
					isChecked = true;
				}
				catch(NumberFormatException e){ }
			}
			if(currentStr.contains(".")){
				try{
					double doubleCheck = Double.parseDouble(currentStr.substring(0, currentStr.length()-1));
					System.out.println("(Tok: 4 line= "+lineCount+" str= \""+currentStr.substring(0, currentStr.length()-1)+"\" float= "+doubleCheck+")");
					System.out.println("(Tok: "+tokenCollection.get(currentStr.substring(currentStr.length()-1))+" line= "+lineCount+" str= \""+currentStr.substring(currentStr.length()-1)+"\")");
					isChecked = true;
				}
				catch(NumberFormatException e){	}
			}
		}
		
	}
}


