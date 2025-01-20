import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTChloeC{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice Game", 1280, 720);
		//Declare variables
		String strQuizName[];
		String strQuestion[][];
		String strTempQuestion[][];				
		String strPlayer[][];		
		String strTempPlayer[][];			
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
		int intCurrentQuestion;
		int intCurrentProperty;  		
		int intTotalQuestions;		
		int intTotalPlayer;		
		int intPageSize;
		int intRow;
		int intRow2;
		int intRow3;		
		double dblPlayerScore;
		double dblTotalScore;
		char chrGetChoice;
		char chrChoice;
		
		// Init variables
		con.setTextColor(Color.BLACK);
		con.setDrawColor(Color.BLACK);
		// Set the font to Comic San Bold
		con.setDrawFont(con.loadFont("comicbd.ttf", 30));
		con.setTextFont(con.loadFont("comicbd.ttf", 30));	
		// Set all the background images	
		BufferedImage imgMainMenuBKG = con.loadImage("MainMenuBackground.jpg");
		BufferedImage imgQuizMenuBKG = con.loadImage("QuizMenuBackground.jpg");
		BufferedImage imgGameMenuBKG = con.loadImage("GamePlayMenuBackground.jpg");
		BufferedImage imgGameBKG = con.loadImage("GameBackground.jpg");		
		BufferedImage imgFullMarkBKG = con.loadImage("FullMarkBackground.jpg");		
		BufferedImage imgResultBKG = con.loadImage("ResultBackground.jpg");		
		BufferedImage imgScoreListBKG = con.loadImage("HighScoreList.jpg");		
		BufferedImage imgHelpBKG = con.loadImage("HelpBackground.jpg");		
		chrChoice = ' ';
		
		// Show the main menu and accpet the menu choice
		RefreshBackgroundImage(con, imgMainMenuBKG);	
		while (chrChoice != 'q' && chrChoice != 'Q') {
			chrChoice = con.getChar();
			// Geme session
			if (chrChoice == 'p' || chrChoice == 'P') {
				intQuizCount = 0;
				intLineNo = 0;
				intQuestionNo = 0;
				intProperty = 0;
				intPropertySize = 6;   // Question line + 4 option lines + real answer line in the txt file
				intXLoc = 50;
				intYLoc = 100;		
				dblPlayerScore = 0;
				dblTotalScore = 0.0;
				RefreshBackgroundImage(con, imgQuizMenuBKG);	
				con.drawString("Here are your available questions", 10, 10);
				intQuizCount = CountTxtLine("Quizzes.txt");
				strQuizName = new String[intQuizCount];
				System.out.println("No. of Quizzes: " + intQuizCount);				
				intQuizCount = 0;
				TextInputFile Menu = new TextInputFile("Quizzes.txt");
				while(Menu.eof() == false){
					strQuizName[intQuizCount] = Menu.readLine();
					con.drawString((intQuizCount + 1) + ": " + strQuizName[intQuizCount], intXLoc, intYLoc);
					intQuizCount += 1;				
					intYLoc += 80;
				}
				Menu.close(); 
				con.repaint();
				
				// Accept the quiz choice by entering the quiz no.
				chrGetChoice = ' ';
				strQuizFileName = "";
				while (chrGetChoice != 'q') {
					chrGetChoice = con.getChar();
					for(intCounter = 0; intCounter <= intQuizCount; intCounter++){
						chrChoice = (char)(intCounter + '0');
						if (chrGetChoice == chrChoice) {
							strQuizFileName = strQuizName[intCounter - 1];					
							chrGetChoice = 'q';
						}				
					}	
				}		
				
				// Get the player's name
				RefreshBackgroundImage(con, imgQuizMenuBKG);	
				con.print("Please input your name: ");
				strPlayerName = con.readLine();
				
				// Prepare the game by showing the game screen, reading the quiz file and displaying the questions
				con.clear();
				RefreshBackgroundImage(con, imgGameBKG);	
				DisplayHeading(con, strPlayerName, strQuizFileName, dblTotalScore);			
				con.repaint();	
				
				// Count the no. of questions by the total no. of lines in the text file / 6 where 6 = question line + 4 option lines + real answer line.
				intLineNo = CountTxtLine(strQuizFileName + ".txt");
				intTotalQuestions = intLineNo / intPropertySize;
				// Debug message of total no. of questions
				System.out.println("Total no. of questions: " + intTotalQuestions);
				strQuestion = new String[intTotalQuestions][intPropertySize + 1];
				strTempQuestion = new String[1][intPropertySize + 1];
						
				// Read the quiz file		
				intLineNo = 0;
				TextInputFile Quiz = new TextInputFile(strQuizFileName + ".txt");				
				while(Quiz.eof() == false){
					strQuestion[intQuestionNo][intProperty] = Quiz.readLine();
					intProperty += 1;
					intLineNo += 1;
					// When it reads for every 7 lines, assign the random no. and jump to the next question
					if (intLineNo % 6 == 0) {
						strQuestion[intQuestionNo][intPropertySize] = ((int)(Math.random() * 100 + 1)) + "";
						intQuestionNo += 1;
						intProperty = 0;			 
					}
				}		
				Quiz.close(); 
				
				// Bubble Sort. Sort the questions based on the random number from small to large
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
				// Print the debug Bubble Sort result on the system window unsing System.out.println
				System.out.println("*** After Bubble Sort ***");
				for(intCurrentQuestion = 0; intCurrentQuestion <= intTotalQuestions - 1; intCurrentQuestion++){
					intXLoc = 80;
					intYLoc = 170;		
					con.drawString("Question " + (intCurrentQuestion + 1) + ": " + strQuestion[intCurrentQuestion][0], intXLoc, 70);			
					for(intCurrentProperty = 1; intCurrentProperty < intPropertySize - 1; intCurrentProperty++){
						con.drawString((char) (64 + intCurrentProperty) + ") " + strQuestion[intCurrentQuestion][intCurrentProperty], intXLoc, intYLoc);
						intYLoc += 100;
						con.repaint();					
						// Debug message of each question, options and real answer
						System.out.println(strQuestion[intCurrentQuestion][intCurrentProperty]);
					}
					chrGetChoice = con.getChar();
					System.out.println(strQuestion[intCurrentQuestion][5]);
					if (chrGetChoice == 'm' || chrGetChoice == 'q' || chrGetChoice == 'M' || chrGetChoice == 'Q') {
						// Exit the loop
						con.drawString("See you next time.", intXLoc, intYLoc);
						con.repaint();	
						con.getChar();		
						intCurrentQuestion = intTotalQuestions;  
						if (chrGetChoice == 'q' || chrGetChoice == 'Q') {
							chrChoice = 'q';
						}
					} else if (chrGetChoice == strQuestion[intCurrentQuestion][5].charAt(0)){
						// Answer correctly
						con.drawString("Correct!", intXLoc, intYLoc);
						con.repaint();							
						dblPlayerScore += 1;
						dblTotalScore = Math.round((100.0 * dblPlayerScore / intTotalQuestions) * 100.0) / 100.0;
						System.out.println("Player score: " + dblPlayerScore);
						System.out.println("Total score: " + dblTotalScore);
						chrGetChoice = con.getChar();					
						RefreshBackgroundImage(con, imgGameBKG);	
						DisplayHeading(con, strPlayerName, strQuizFileName, dblTotalScore);			
						con.repaint();	
					} else {
						// Answer incorrectly
						con.drawString("Correct answer is " + strQuestion[intCurrentQuestion][5].charAt(0), intXLoc, intYLoc);
						con.repaint();	
						chrGetChoice = con.getChar();					
						RefreshBackgroundImage(con, imgGameBKG);	
						DisplayHeading(con, strPlayerName, strQuizFileName, dblTotalScore);			
						con.repaint();	
					}		
				}
				
				// End the quiz
				// Show the quiz result screen
				if (dblTotalScore < 100.0) {
					// Show the not full mark screen
					RefreshBackgroundImage(con, imgResultBKG);	
					if (dblTotalScore < 50.0) {
						con.drawString("You failed!", 530, 100);	
						con.drawString("Try again " + strPlayerName + ". I believe you can do it!", 300, 200);						
					} else {
						con.drawString("You Passed!", 530, 100);	
						con.drawString("Congratulations " + strPlayerName + ". You did it great!", 300, 200);						
					}					
				} else {
					// Show the full mark screen		
					RefreshBackgroundImage(con, imgFullMarkBKG);
					con.drawString("You got FULL MARKS!", 430, 100);		
					con.drawString("Congratulations " + strPlayerName + "!", 430, 200);	
				}
				con.drawString("Quiz: " + strQuizFileName, 430, 10);
				con.drawString("Your score is: " + (int) dblPlayerScore + " / " + intTotalQuestions + " = " + dblTotalScore + "%.", 400, 300);
				con.repaint();		
				
				// Save the player's info to the high score txt file
				TextOutputFile HighScore = new TextOutputFile("highscore.txt", true);
				HighScore.println(strPlayerName);
				HighScore.println(strQuizFileName);
				HighScore.println(dblTotalScore + "");
				HighScore.close();						
				con.getChar();
				if (chrGetChoice != 'q' || chrGetChoice != 'Q'){
					// Back to the main menu
					RefreshBackgroundImage(con, imgMainMenuBKG);	
				}				
			} else if (chrChoice == 'v' || chrChoice == 'V') {
				// View high score session
				RefreshBackgroundImage(con, imgScoreListBKG);	
				intLineNo = 0;
				intPropertySize = 2;   // The player's name, quiz and score in the txt file
				intLineNo = CountTxtLine("highscore.txt");
				intTotalPlayer = intLineNo / (intPropertySize + 1);		
				System.out.println("Total palyers: " + intTotalPlayer);
				strTempPlayer = new String[1][intPropertySize + 1];
				strPlayer = new String[intTotalPlayer][intPropertySize + 1];
				intProperty = 0;
				intCounter = 0;
				
				// Read the high score records from the txt file
				TextInputFile HighScore = new TextInputFile("highscore.txt");				
				while(HighScore.eof() == false){
					strPlayer[intCounter][intProperty] = HighScore.readLine();			
					if (intProperty == intPropertySize) {
						intCounter += 1;
						intProperty = 0;
					} else {
						intProperty += 1;			
					}			
				}
				
				// Bubble Sort the high score record based on the score from high to low
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
				
				// Show the sortedd result from high score to low score page by page
				intYLoc = 100;		
				intPageSize = 600;
				chrGetChoice = ' ';
				for(intCounter = 0; intCounter < intTotalPlayer; intCounter++){
					// Display the heading session
					con.drawString("High Score List", 500, 10);
					con.drawString("Name", 200, 50);
					con.drawString("Quiz", 650, 50);
					con.drawString("Score", 1000, 50);
					con.drawLine(200, 100, 1150, 100);
					// Display the player session
					con.drawString(strPlayer[intCounter][0], 200, intYLoc);
					con.drawString(strPlayer[intCounter][1], 650, intYLoc);
					con.drawString(strPlayer[intCounter][2] + "%", 1000, intYLoc);
					intYLoc += 100;
					con.sleep(250);			
					// Show 5 players for each page
					if (intYLoc == intPageSize) {
						con.drawString("Press any key to continue...", 100, intYLoc);
						con.repaint();	
						chrGetChoice = con.getChar();
						RefreshBackgroundImage(con, imgScoreListBKG);	
						if (chrGetChoice == 'm' || chrGetChoice == 'q' || chrGetChoice == 'M' || chrGetChoice == 'Q') {
							// Exit the loop
							intCounter = intTotalPlayer;	
							chrChoice = chrGetChoice;
							con.drawString("See you next time", 500, 350);
							con.repaint();	
						} else {
							intYLoc = 100;				
						}																	
					}
					con.repaint();								
				}	
				con.drawString("Press any key to continue...", 100, intYLoc);
				con.getChar();					
				RefreshBackgroundImage(con, imgMainMenuBKG);						
			} else if (chrChoice == 'h' || chrChoice == 'H') {
				// Help session
				RefreshBackgroundImage(con, imgHelpBKG);	
				con.getChar();
				RefreshBackgroundImage(con, imgMainMenuBKG);	
			}
		}
		// Exit the quiz
		con.closeConsole();
	}
	
	// Declare methods
	// Method to display the player's name, quiz and the score in the gam play session
	public static void DisplayHeading(Console con, String strPlayer, String strQuiz, double dlbScore){
		con.drawString("Player: " + strPlayer, 50, 0);
		con.drawString("Quiz: " + strQuiz, 500, 0);		
		con.drawString("Score: " + dlbScore + "%", 1000, 0);	
	}	
	
	// Refresh the background image
	public static void RefreshBackgroundImage(Console con, BufferedImage imgBackground){
		con.drawImage(imgBackground, 0, 0);		
		con.repaint();
	}
	
	// Count and return the total no. of lines in a given txt file
	public static int CountTxtLine(String strTxtFile){
		String strTempLine;
		int intTotalLine;
		
		intTotalLine = 0;
		TextInputFile file = new TextInputFile(strTxtFile);
		while(file.eof() == false){
			strTempLine = file.readLine();
			intTotalLine += 1;
		}		
		file.close();
		return intTotalLine;
	}	
}
