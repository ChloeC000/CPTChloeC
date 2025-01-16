import arc.*;

public class CPTChloeC{
	public static void main(String[]args){
		Console con = new Console();
	
	//variables
	String strChoice1;
	String strChoice2;
	String strPlayerName;
	String strQuizTopic;
	String strQuestion[][];
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
		con.println("");
		con.println("Which quiz do you want to try?" );
		strChoice2 = con.readLine();
		//when the player chooses science quiz
		if(strChoice2.equalsIgnoreCase("Science")){	
			TextOutputFile HighScore = new TextOutputFile("ScienceQuiz.txt");
		}
		

	//if player choose View High Score
	}else if(strChoice1.equalsIgnoreCase("V")){
		TextOutputFile HighScore = new TextOutputFile("HighScore.txt");
		con.println(strPlayerName + "   " + strQuizTopic + "   " + intMark);
	//if player choose Quit
	}else if(strChoice1.equalsIgnoreCase("Q")){
		
	}

	
	
	import arc.*;
	import java.awt.Graphics;
	import javax.swing.*;
	import javax.swing.JComponent;

	class LineDrawing extends JComponent {  
		public void paint(Graphics g)
		{
			g.drawLine(100, 75, 125, 150);
			g.drawLine(125, 75, 150, 150);
			g.drawString("TEST", 700, 700);
		}
	}

	public class graphictest{
		public static void main (String [] args){

		JFrame frame = new JFrame("MC Quiz");
			   frame.setSize(1020, 800);  
			   frame.getContentPane().add(new LineDrawing ());
			   frame.setVisible(true);  
		}
	}
		
	
	/*
	TextInputFile science = new TextInputFile("ScienceQuiz.txt");
	TextInputFile movie = new TextInputFile("MovieDramaQuiz.txt");
	TextInputFile history = new TextInputFile("HistoryQuiz.txt");
	*/
	
	
	}
}

