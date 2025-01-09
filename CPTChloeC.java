import arc.*;

public class CPTChloeC{
	public static void main(String[]args){
		Console con = new Console();
	
	String strName;
	
	con.println("What is your name?");
	strName = con.readLine();
	
	con.println("play game");
	con.println("view high score");
	con.println("quit");
	
	
	TextInputFile science = new TextInputFile("ScienceQuiz.txt");
	TextInputFile movie = new TextInputFile("MovieDramaQuiz.txt");
	
	
	}
}

