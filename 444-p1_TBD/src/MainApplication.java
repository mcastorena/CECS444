
import java.util.Scanner;

public class MainApplication {

	public static void main(String args[]) throws Exception
	{
		Scanner in = new Scanner(System.in);
		LexerProgram lp = new LexerProgram();
		System.out.println("Enter the name of the File ");
		String file = in.nextLine();
		
		lp.ReadPrintFileTokens(file);
		
	}
}
