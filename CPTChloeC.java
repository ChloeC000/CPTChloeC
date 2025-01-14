import arc.*;

public class CPTChloeC{
	public static void main(String[]args){
		Console con = new Console();
	
	//variables
	String strChoice1;
	String strPlayerName;
	String strQuizTopic;
	String strScience[][];
	int intMark;
	String strQuizName;
	
	
	//initialize variables
	intMark = 0;
	strPlayerName = "";
	strQuizTopic = "";
	
	//main manu options
	con.println("Play Game (P)");
	con.println("View High Score (V)");
	con.println("Quit (Q)");
	//player's choice
	strChoice1 = con.readLine();
	
	//if statement 
	//if player choose Play Game
	if(strChoice1.equalsIgnoreCase("P")){	
		con.println("What is your name?");
		strPlayerName = con.readLine();
	//if player choose View High Score
	}else if(strChoice1.equalsIgnoreCase("V")){
		TextOutputFile HighScore = new TextOutputFile("HighScore.txt");
		con.println(strPlayerName + "   " + strQuizTopic + "   " + intMark);
	//if player choose Quit
	}else if(strChoice1.equalsIgnoreCase("Q")){
		
	}

	
	
	TextInputFile Quizzes = new TextInputFile("quizzes.txt");
	
	
	/*
	TextInputFile science = new TextInputFile("ScienceQuiz.txt");
	TextInputFile movie = new TextInputFile("MovieDramaQuiz.txt");
	TextInputFile history = new TextInputFile("HistoryQuiz.txt");
	*/
	
	
	}
}

