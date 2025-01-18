import arc.*;

public class CPTChloeC{
	public static void main(String[]args){
		Console con = new Console();
	
	//variables//
	//
int int2;
	int intCount;
	int intNumOfQuiz;
	int intNumOfQuestion;
	int intQuizLine;
	int intPropertySize;
	int intRow;
	int intColumn;
	int intMark;
	int intTest;
	String strChoice1;
	String strChoice2;
	String strPlayerName;
	String strQuizTopics[];
	String strQuizChosen;
	String strQuestion[][];
	String strQuizzTXT;
	String strQuizReadLine;
	String strQuizName;
	
	
	//initialize variables
	strChoice1 = "";
	intMark = 0;
	intPropertySize = 6;
	intQuizLine = 0;
	intNumOfQuiz = 0;
	intNumOfQuestion = 0;
	strPlayerName = "";
	strQuizChosen = "";
	strQuestion = new String[intNumOfQuestion][6];

	
	//main manu options
	con.println("Play Game (P)");
	con.println("View High Score (V)");
	con.println("Quit (Q)");
	
	//player's choice
	strChoice1 = con.readLine();	
	
	//if statement 
	//if player choose Play Game
	if(strChoice1.equalsIgnoreCase("P")){
		con.println("Here are your available quizzes: ");
		
		TextInputFile Quizzes = new TextInputFile("Quizzes.txt");
		while(Quizzes.eof()== false){
			strQuizChosen = Quizzes.readLine();
			intNumOfQuiz = intNumOfQuiz + 1;
		}
			
			strQuizTopics = new String[intNumOfQuiz];
			Quizzes = new TextInputFile("Quizzes.txt");
			
			//using one dimensional array to print all lines in the Quizzes.txt
			for(intCount = 0 ; intCount < intNumOfQuiz ; intCount++){
				strQuizTopics[intCount] = Quizzes.readLine();
			}
		
			for(intCount = 0 ; intCount < intNumOfQuiz ; intCount++){
				con.println(strQuizTopics[intCount]);
			}
			
			con.println("");
			//player will choose one of the quizzes
			con.println("Which quiz would you like to try? ");
			strChoice2 = con.readLine();
			
			
			//ask for player's name
			con.println("What is your name? ");
			strPlayerName = con.readLine();
			con.println("");
			
			strQuizzTXT = strChoice2 + ".txt";
			//con.println("TEST: " + strQuizzTXT);
			//open text file
			TextInputFile ChosenQuiz = new TextInputFile("ScienceQuiz.txt");
			
			//when the player chooses quiz
			// open the text file according to the quiz that the player chooses
			
			con.println("TEST" + intNumOfQuiz);
			for(intCount = 0 ; intCount < intNumOfQuiz ; intCount++){
				if(strChoice2.equalsIgnoreCase(strQuizTopics[intCount])){
					strQuizChosen = strQuizTopics[intCount];
					con.println("TEST: " + strQuizChosen);
					
				}
			}
			
				
				intRow = 0;
				intColumn = 0;
				//intNumOfQuestion = 25;
				
										
					strQuestion = new String[intNumOfQuestion][6];
	
					while(ChosenQuiz.eof() == false){
						strQuestion[intRow][intColumn] = ChosenQuiz.readLine();
						intQuizLine += 1;
						//con.println("TEST2: " + intQuizLine);
						
						if(intColumn == 5){
							intRow += 1;
							intColumn = 0;
							//con.println("TEST55");

						}else if(intColumn < 5){
							intColumn += 1;
						}
					}
					
					con.println(intQuizLine / intPropertySize);
					intNumOfQuestion = intQuizLine / intPropertySize;
					
					//con.println("ccc" + intNumOfQuestion);
					for(intRow = 0 ; intRow < intNumOfQuestion ; intRow++){
						for(intColumn = 0 ; intColumn < 6 ; intColumn++){
							System.out.println(strQuestion[intRow][intColumn]);
						}
					}
	
	
				
		}
	
	}
	//sciencequiz
				
				
		
				
			/*
			//if player choose View High Score
			}else if(strChoice1.equalsIgnoreCase("V")){
			TextOutputFile HighScore = new TextOutputFile("HighScore.txt");
			con.println(strPlayerName + "   " + strQuizChosen + "   " + intMark);
		//if player choose Quit
		}else if(strChoice1.equalsIgnoreCase("Q")){
			
	*/

	
	/*
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
	*/
	
}


