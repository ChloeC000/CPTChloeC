import arc.*;

public class CPTChloeC{
	public static void main(String[]args){
		Console con = new Console();
	
	//variables
	String strChoice1;
	String strName;
	String strQuizTopic;
	int intMark;
	String strQuiz1;
	String strQuiz2;
	String strQuiz3;
	
	
	intMark = 0;
	
	con.println("Play Game (P)");
	con.println("View High Score (V)");
	con.println("Quit (Q)");
	
	strChoice1 = con.readLine();
	
	if(strChoice1.equalsIgnoreCase("P")){	
		con.println("What is your name?");
		strName = con.readLine();
		
	}else if(strChoice1.equalsIgnoreCase("V")){
		TextOutputFile HighScore = new TextOutputFile("HighScore.txt");
		con.println(strName + "   " + strQuizTopic + "   " + intMark);
		
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

