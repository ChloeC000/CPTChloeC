import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTTools{

	public static void GamePlay(Console con){
		String strQuizName[];
		String strQuestion[][];
		String strTempQuestion[][];		
		String strTempReadLine;
		String strQuizFileName;
		String strPlayerName;
		int intXLoc;
		int intYLoc;
		int intQuizCount;
		int intCounter;
		int intLineNo;
		int intQuestionNo;
		int intProperty;
		int intPropertySize;
		int intTotalQuestions;		
		double dblPlayerScore;
		double dblTotalScore;
		char chrGetChoice;
		char chrChoice;
		
		BufferedImage imgGameMenuBKG = con.loadImage("GamePlayMenuBackground.jpg");
		BufferedImage imgGameBKG = con.loadImage("gamebackground2.jpg");
		intQuizCount = 0;
		intLineNo = 0;
		intQuestionNo = 0;
		intProperty = 0;
		intPropertySize = 6;   // Question line + 4 option lines + real answer line in the txt file
		intXLoc = 50;
		intYLoc = 100;		
		dblPlayerScore = 0;
		dblTotalScore = 0.0;
		
		con.setDrawColor(Color.BLUE);
		con.drawImage(imgGameMenuBKG, 0, 0);
		con.println("Here are your available questions");
		TextInputFile Menu = new TextInputFile("Quizzes.txt");
		while(Menu.eof() == false){
			strTempReadLine = Menu.readLine();
			intQuizCount += 1;
		}		
		Menu.close(); 
		strQuizName = new String[intQuizCount];
		intQuizCount = 0;
		Menu = new TextInputFile("Quizzes.txt");
		while(Menu.eof() == false){
			strQuizName[intQuizCount] = Menu.readLine();
			con.drawString((intQuizCount + 1) + ": " + strQuizName[intQuizCount], intXLoc, intYLoc);
			intQuizCount += 1;				
			intYLoc += 80;
		}
		Menu.close(); 
		con.repaint();
		System.out.println("No. of Quizzes: " + intQuizCount);
		
		chrGetChoice = ' ';
		strQuizFileName = "";
		while (chrGetChoice != 'q' && chrGetChoice != 'Q') {
			chrGetChoice = con.getChar();
			intCounter = 0;
			for(intCounter = 0; intCounter <= intQuizCount; intCounter++){
				chrChoice = (char)(intCounter + '0');
				if (chrGetChoice == chrChoice) {
					strQuizFileName = strQuizName[intCounter - 1];					
					chrGetChoice = 'q';
				}				
			}
			con.repaint();	
			con.println("");
		}
		con.clear();
		con.drawImage(imgGameMenuBKG, 0, 0);		
		con.print("Please input your name: ");
		strPlayerName = con.readLine();
		
		con.clear();
		con.drawImage(imgGameBKG, 0, 0);
		DisplayHeading(con, strPlayerName, strQuizFileName, dblTotalScore);			
		con.repaint();	
		
		// Count the no. of questions by the total no. of lines in the text file / 6 where 6 = question line + 4 option lines + real answer line.
		intLineNo = 0;
		TextInputFile Quiz = new TextInputFile(strQuizFileName + ".txt");
		while(Quiz.eof() == false){
			strTempReadLine = Quiz.readLine();
			intLineNo += 1;
		}		
		Quiz.close(); 
		
		intTotalQuestions = intLineNo / intPropertySize;
		// Debug message of total no. of questions
		System.out.println("Total no. of questions: " + intTotalQuestions);
		strQuestion = new String[intTotalQuestions][intPropertySize + 1];
		strTempQuestion = new String[1][intPropertySize + 1];
				
		// Read the quiz file		
		intLineNo = 0;
		Quiz = new TextInputFile(strQuizFileName + ".txt");
		while(Quiz.eof() == false){
			strQuestion[intQuestionNo][intProperty] = Quiz.readLine();
			intProperty += 1;
			intLineNo += 1;
			//When it reads for every 7 lines, assign the random no. and jump to the next question
			if (intLineNo % 6 == 0 && intLineNo > 0) {
				strQuestion[intQuestionNo][intPropertySize] = ((int)(Math.random() * 100 + 1)) + "";
				intQuestionNo += 1;
				intProperty = 0;			 
			}
		}		
		Quiz.close(); 
		
		// Bubble Sort
	    int intRow;
		int intRow2;
		int intRow3;
		for(intRow2 = 0; intRow2 < intTotalQuestions - 1; intRow2++){
			for(intRow = 0; intRow < intTotalQuestions - 1 - intRow2; intRow++){
				// Bubble sort. If left is bigger than right
				if(Integer.parseInt(strQuestion[intRow][intPropertySize]) > Integer.parseInt(strQuestion[intRow + 1][intPropertySize])){
					// Take the left item. Use for-loop to assign values
					for(intRow3 = 0; intRow3 <= intPropertySize; intRow3++){
						strTempQuestion[0][intRow3] = strQuestion[intRow][intRow3];								
					}
					// Right item moves to the left. Use for-loop to assign values
					for(intRow3 = 0; intRow3 <= intPropertySize; intRow3++){
						strQuestion[intRow][intRow3] = strQuestion[intRow + 1][intRow3];
					}
					// Put temporary value on the right. Use for-loop to assign values
					for(intRow3 = 0; intRow3 <= intPropertySize; intRow3++){
						strQuestion[intRow + 1][intRow3] = strTempQuestion[0][intRow3];
					}
				}
			}
		}
		
		// Start the game
		// Print the Bubble Sort result on the system window unsing System.out.println
		System.out.println("*** After Bubble Sort ***");
		int intCurrentQuestion;
        int intCurrentProperty;  
		for(intCurrentQuestion = 0; intCurrentQuestion <= intTotalQuestions - 1; intCurrentQuestion++){
			intXLoc = 80;
			intYLoc = 270;		
			con.drawString("Question " + (intCurrentQuestion + 1) + ": " + strQuestion[intCurrentQuestion][0], 80, 70);			
			for(intCurrentProperty = 1; intCurrentProperty < intPropertySize - 1; intCurrentProperty++){
				con.drawString((char) (64 + intCurrentProperty) + ") " + strQuestion[intCurrentQuestion][intCurrentProperty], 80, intYLoc);
				intYLoc += 100;
				con.repaint();					
				// Debug message of each question, options and real answer
				System.out.println(strQuestion[intCurrentQuestion][intCurrentProperty]);
		    }
			chrGetChoice = con.getChar();
			System.out.println(strQuestion[intCurrentQuestion][5]);
			if (chrGetChoice == strQuestion[intCurrentQuestion][5].charAt(0)){
				con.drawString("Correct!", 80, intYLoc);
				dblPlayerScore += 1;
				dblTotalScore = Math.round((100.0 * dblPlayerScore / intTotalQuestions) * 100.0) / 100.0;
				System.out.println("Player score: " + dblPlayerScore);
				System.out.println("Total score: " + dblTotalScore);
			} else {
				con.drawString("Correct answer is " + strQuestion[intCurrentQuestion][5].charAt(0), 80, intYLoc);
			}			
			con.repaint();	
			chrGetChoice = con.getChar();
			con.drawImage(imgGameBKG, 0, 0);
			DisplayHeading(con, strPlayerName, strQuizFileName, dblTotalScore);			
			con.repaint();				
	    }
	    con.drawString(strPlayerName + ", your " + strQuizFileName + " quiz is done. Your score is " + dblTotalScore + "%.", 80, 350);
	    con.repaint();		
	    TextOutputFile HighScore = new TextOutputFile("highscore.txt", true);
	    HighScore.println(strPlayerName);
	    HighScore.println(strQuizFileName);
	    HighScore.println(dblTotalScore + "");
	    HighScore.close();
	}
	
	public static void DisplayHeading(Console con, String strPlayer, String strQuiz, double dlbScore){
			con.drawString("Player: " + strPlayer, 50, 0);
			con.drawString("Quiz: " + strQuiz, 500, 0);		
			con.drawString("Score: " + dlbScore + "%", 1000, 0);	
	}	
	
	public static void ViewHighScore(Console con){
		String strPlayer[][];		
		String strTempPlayer[][];		
		String strTempReadLine;
		int intLineNo;
		int intTotalPlayer;		
		int intProperty;
		int intPropertySize;
		int intCounter;
		int intYLoc;
		
		intLineNo = 0;
		intPropertySize = 2;   // The player's name, quiz and score
		TextInputFile HighScore = new TextInputFile("highscore.txt");
		while(HighScore.eof() == false){
			strTempReadLine = HighScore.readLine();
			intLineNo += 1;
		}		
		HighScore.close(); 
		intTotalPlayer = intLineNo / (intPropertySize + 1);		
		System.out.println("Total palyer: " + intTotalPlayer);
		strTempPlayer = new String[1][intPropertySize + 1];
		strPlayer = new String[intTotalPlayer][intPropertySize + 1];
		intProperty = 0;
		intCounter = 0;
		HighScore = new TextInputFile("highscore.txt");
		while(HighScore.eof() == false){
			System.out.println("intCounter: " + intCounter);
			System.out.println("intProperty: " + intProperty);
			strPlayer[intCounter][intProperty] = HighScore.readLine();			
			if (intProperty == intPropertySize) {
				intCounter += 1;
				intProperty = 0;
			} else {
				intProperty += 1;			
			}			
		}
		
		// Bubble Sort
	    int intRow;
		int intRow2;
		int intRow3;
		for(intRow2 = 0; intRow2 < intTotalPlayer - 1; intRow2++){
			for(intRow = 0; intRow < intTotalPlayer - 1 - intRow2; intRow++){
				// Bubble sort. If left is bigger than right
				if(Double.parseDouble(strPlayer[intRow][intPropertySize]) < Double.parseDouble(strPlayer[intRow + 1][intPropertySize])){
					// Take the left item. Use for-loop to assign values
					for(intRow3 = 0; intRow3 <= intPropertySize; intRow3++){
						strTempPlayer[0][intRow3] = strPlayer[intRow][intRow3];								
					}
					// Right item moves to the left. Use for-loop to assign values
					for(intRow3 = 0; intRow3 <= intPropertySize; intRow3++){
						strPlayer[intRow][intRow3] = strPlayer[intRow + 1][intRow3];
					}
					// Put temporary value on the right. Use for-loop to assign values
					for(intRow3 = 0; intRow3 <= intPropertySize; intRow3++){
						strPlayer[intRow + 1][intRow3] = strTempPlayer[0][intRow3];
					}
				}
			}
		}		
		
		// Show the sortedd result from high score to low score
		intYLoc = 100;		
		for(intCounter = 0; intCounter < intTotalPlayer; intCounter++){
			con.drawString(strPlayer[intCounter][0], 100, intYLoc);
			con.drawString(strPlayer[intCounter][1], 500, intYLoc);
			con.drawString(strPlayer[intCounter][2], 800, intYLoc);
			con.repaint();				
			intYLoc += 100;
			con.sleep(250);			
		}	
	}			
}
