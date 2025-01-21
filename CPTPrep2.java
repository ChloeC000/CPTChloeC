import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTPrep2{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice Game", 1280, 720);
		//Declare variables
		String strFileName;
		String strQuestion[][];
		String strTempQuestion[][];
		String strTempReadLine;
		char chrChoice;
		int intLineNo;
		int intQuestionNo;
		int intProperty;
		int intPropertySize;
		int intTotalQuestions;
		
		con.setTextColor(Color.BLUE);
		con.setDrawColor(Color.BLUE);
		con.setDrawFont(con.loadFont("comicbd.ttf", 30));
		con.setTextFont(con.loadFont("comicbd.ttf", 30));
		
		BufferedImage imgGameBKG = con.loadImage("gamebackground2.jpg");
		con.drawImage(imgBackground, 0, 0);		
		con.println("");
        chrChoice = ' ';
		while (chrChoice != 'q' && chrChoice != 'Q') {
			chrChoice = con.getChar();
			if (chrChoice == 'p' || chrChoice == 'P') {
				CPTTools.GamePlay(con);
			} else if (chrChoice == 'v' || chrChoice == 'V') {
				CPTTools.ViewHighScore(con);
			}
		}
		con.closeConsole();
	}
}
