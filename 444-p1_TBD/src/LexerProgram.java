
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File; 
import java.util.Scanner;

public class LexerProgram 
{
	private Map<String, Integer> tokenCollection;
	
	/**
	 * default constructor
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
	 * read file and display corresponding token value
	 * @param fileName - name of file containing a program
	 * @throws Exception
	 */
	public void ReadPrintFileTokens(File fileName) throws Exception
	{
		Scanner scLines = new Scanner(fileName);
		String currentStr;
		String collectStr = "";
		int count = 1;
		//int intCheck;
		//double doubleCheck;
		ArrayList<String> lineStrings = new ArrayList<String>(); //list of texts
		ArrayList<String> allIdentifiers = new ArrayList<String>(); //list of variables
		while(scLines.hasNextLine())
		{
			String lineRead = scLines.nextLine();
			
			//read each line in the file
			Scanner sc = new Scanner(lineRead);
			while(sc.hasNext())
			{
				collectStr = "";
				currentStr = sc.next(); //read current text before the space
				//add text to list
				lineStrings.add(currentStr);
				if(!currentStr.equals("//")) //current text is not a comment sign
				{
					//find text in the token collection
					if(tokenCollection.containsKey(currentStr) && !currentStr.equals("="))
					{
						System.out.println("(Tok: "+tokenCollection.get(currentStr)+" line= "+count+" str= \""+currentStr+"\")");
					}
					else
					{
						//text might be an integer
						if(!currentStr.contains("."))
						{
							getIntegerValue(currentStr,count);
						}
						//text might be a decimal/double
						if(currentStr.contains("."))
						{
							getDoubleValue(currentStr, count);
						}
					}
					//text may contain an identifier and assignment operator
					if(lineStrings.size() > 1 && lineStrings.size() < 3)
					{
						//second element in list of texts is assignment
						if(lineStrings.get(1).equals("="))
						{
							System.out.println("(Tok: 2 line= "+count+" str= \""+lineStrings.get(0)+"\")");
							System.out.println("(Tok: 45 line= "+count+" str= \""+currentStr+"\")");
							allIdentifiers.add(lineStrings.get(0)); //add identifier to list
						}
					}
					//text is a string
					if(currentStr.charAt(0) == '"' && currentStr.charAt(currentStr.length()-1) == '"' && currentStr.length() > 1)
					{
						System.out.println("(Tok: 5 line= "+count+" str= \""+currentStr.substring(1, currentStr.length()-1)+"\")");
					}
					//text is in quotes
					if(currentStr.equals("\""))
					{
						currentStr = sc.next();
						getTextInQuotes(sc,currentStr,collectStr);
						System.out.println("(Tok: 5 line= "+count+" str= \" "+collectStr+" \")");
					}
					//text is a string in quotes
					if(currentStr.charAt(0) == '"' && currentStr.length() > 1 && currentStr.charAt(currentStr.length()-1) != '"')
					{
						getStringInQuotes(sc,currentStr,collectStr,count);
					}
					//text is an identifier in list of identifiers
					if(allIdentifiers.contains(currentStr))
					{
						getIdentifier(currentStr, count);
					}
					if(currentStr.equals("\n"))
					{
						//if new line character do nothing
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
	
	/**
	 * collect text enclosed in quotes
	 * @param sc - Scanner object iterating through quoted text
	 * @param currentStr - current text
	 * @param collectStr - total text enclosed in quotes
	 */
	public void getTextInQuotes(Scanner sc,String currentStr,String collectStr) 
	{
		while(!currentStr.equals("\""))
		{
			if(!currentStr.equals("\""))
			{
				if(collectStr.equals(""))
				{
					collectStr = currentStr;
				}
				else{
					collectStr = collectStr+" "+currentStr;
				}
			}
			currentStr = sc.next();
		}
	}
	
	/**
	 * convert string to integer and display the integer value
	 * @param currentStr - number as a string
	 * @param count - line containing currentStr
	 */
	public void getIntegerValue(String currentStr,int count)
	{
		try
		{
			int intCheck = Integer.parseInt(currentStr);
			System.out.println("(Tok: 3 line= "+count+" str= \""+currentStr+"\" int= "+intCheck+")");
		}
		catch(NumberFormatException e){ }
	}
	/**
	 * convert string that contains a "." character to a double and display double value
	 * @param currentStr - double as a string
	 * @param count - line containing currentStr
	 */
	public void getDoubleValue(String currentStr, int count)
	{
		double doubleCheck;
		try
		{
			doubleCheck = Double.parseDouble(currentStr);
			System.out.println("(Tok: 4 line= "+count+" str= \""+currentStr+"\" float= "+doubleCheck+")");
		}
		catch(NumberFormatException e){	}
	}
	/**
	 * 
	 * @param currentStr - string that is an identifier in a list of identifiers
	 * @param count - line containing currentStr
	 */
	public void getIdentifier(String currentStr, int count)
	{
		System.out.println("(Tok: 2 line= "+count+" str= \""+currentStr+"\")");
	}
	
	/**
	 * get string contained in quotes
	 * @param sc - scanner to read through quoted text
	 * @param currentStr - current string being read
	 * @param collectStr - total string value from currentStr
	 */
	public void getStringInQuotes(Scanner sc, String currentStr, String collectStr, int count) 
	{
		while(!currentStr.equals("\""))
		{
			if(!currentStr.equals("\""))
			{
				if(collectStr.equals(""))
				{
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
}
