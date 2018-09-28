/**
Authors:
Minhkhoa Vu	minhkhoavu954@gmail.com
Anthony Myers	anthonysmyers@yahoo.com
Juan Espinoza	jaespin30@yahoo.com
Miguel Casterona miguel.angel.castorena@gmail.com

MainApplication.java Description
This is the main file of our lexer program. It prompts the user for the filepath of the text file to be tokenized,
creates a file object from the text file, and passes the file object to the lexer to be tokenized after some file checks.
*/


import java.io.File;
import java.util.Scanner;

public class MainApplication {

	public static void main(String args[]) throws Exception
	{
		Scanner in = new Scanner(System.in);
		LexerProgram lp = new LexerProgram();
		System.out.print("Enter the path of the file: ");
		String fileName = in.nextLine();
		File tokenFile= new File(fileName);										//create file from specified file path
		if(tokenFile.exists()) {												//check if tokenFile exists
			if(tokenFile.isFile() && tokenFile.canRead()) {						//check if tokenFile is a file that can be read
				lp.ReadPrintFileTokens(tokenFile);								//pass file to lexer
			}else {
				System.out.println("File cannot be read.");						//print error message
			}
		}else {
			System.out.println("File does not exist at specified path.");		//print error message
		}
		
		in.close();
		
	}
}
