import arc.*;

public class CPTChloeC{
	public static void main(String[]args){
		Console con = new Console();
	
	String strChoice1;
	String strName;
	String strQuiz1;
	String strQuiz2;
	String strQuiz3;
	
	con.println("Play Game (P)");
	con.println("View High Score (V)");
	con.println("Quit (Q)");
	
	strChoice1 = con.readLine();
	if(strChoice1.equalsIgnoreCase("P")){	
		con.println("What is your name?");
		strName = con.readLine();
		
	}else if(strChoice1.equalsIgnoreCase("V")){
		TextInputFile HightScore = new TextInputFile("HighScore.txt");
		
	}else if(strChoice1.equalsIgnoreCase("Q")){
		
	}

	
	
	TextInputFile Quizzes = new TextInputFile("quizzes.txt");
	strQuiz1 = con.readLine();
	strQuiz2 = con.readLine();
	strQuiz3 = con.readLine();
	
	/*
	TextInputFile science = new TextInputFile("ScienceQuiz.txt");
	TextInputFile movie = new TextInputFile("MovieDramaQuiz.txt");
	TextInputFile history = new TextInputFile("HistoryQuiz.txt");
	*/
	
	
	}
}

