//Video Poker
//Louis Sun
//April 8, 2021
//Version 7.27

import arc.*;
import java.awt.*;
import java.awt.image.*;

public class videopokermain {
	public static void main(String[] args) {
		//loading console and fonts		
		Console con = new Console("Video Poker", 1275, 650);
		Font headings = con.loadFont("nk57-monospace-no-rg.ttf", 40);
		Font other = con.loadFont("nk57-monospace-no-rg.ttf", 30);
		Font normal = con.loadFont("nk57-monospace-no-rg.ttf", 20);
		
		//Main Menu
		String strMenuChoice;
		strMenuChoice = toolbox.startPlay(con, headings, normal);
		System.out.println("Menu Choice: " + strMenuChoice);
		toolbox.clearscreen(con);
		
		while (!strMenuChoice.equalsIgnoreCase("q")) { //Keep running the game as long as the user doesn't quit
			if (strMenuChoice.equalsIgnoreCase("p")) {
				System.out.println("Play Game!"); 
				
				//Play Game
				String strUsername; //Username
				String strReplay; //Does the user want to keep playing?
				int intMoney; //Total amount of money the user has (his/her total score)
				TextOutputFile txtScores; //High scores file
				strUsername = toolbox.getUsername(con, other, normal);
				
				intMoney = 1000;
				strReplay = "y";
				if (strUsername.equals("strongertogether")) {
					//User gains a money advantage if they enter "strongertogether" as their username
					intMoney = 2000;
				}
				
				while (strReplay.equalsIgnoreCase("y")) {
					int intCount;
					int intBet;
					int intDeck[][];
					int intHand[][];
					int intPositions[][];
					int intPositions2[][];
					intDeck = new int[52][3];
					intHand = new int[5][2]; 
					intPositions = new int[5][2];		
					intPositions2 = new int[5][2];
					toolbox.initializepositions(intPositions, intPositions2); //initialize the intPositions[][] and intPositions2[][] arrays
					
					intBet = toolbox.startBet(con, intMoney, headings, other, normal); //initialize the intBet variable
					intMoney = intMoney - intBet; //subtract intBet from intMoney
					System.out.println("You now have this much money after betting: " + intMoney);
					intDeck = toolbox.createDeck(intDeck); //initialize the deck
					//toolbox.displayandcheckDeck(intDeck);
					toolbox.shuffleDeck(intDeck); //shuffle the deck
					//toolbox.displayandcheckDeck(intDeck);
					for (intCount = 0; intCount < 5; intCount++) {
						//draw the first 5 cards in intDeck[][]
						//transfer the first 5 cards in intDeck[][] to intHand[][]
						toolbox.drawcard(intCount, intCount, intHand, intDeck);
					}
					toolbox.displayHand(con, intHand, intPositions, headings, other, normal); //display the hand of 5 cards
					
					//swap cards
					//the player must make a valid sequence of swaps
					//otherwise, the player will see an error message, and their hand and swaps will be reset
					while (!toolbox.swapcards(con, intHand, intPositions, intDeck, headings, other, normal)) {
						toolbox.displayHand(con, intHand, intPositions, headings, other, normal);
					}
					//display their new hand after the valid sequence of swaps
					toolbox.displayHand(con, intHand, intPositions, headings, other, normal);
					con.sleep(1300);
					toolbox.clearscreen(con);
				
					con.setDrawFont(other);
				
					if (toolbox.RoyalFlush(intHand)) { //check to see if the player got a royal flush
						System.out.println("Royal Flush");
						intMoney = intMoney + intBet * 800;
					
						con.drawString("You got: ROYAL FLUSH", 430, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.StraightFlush(intHand)) { //checks to see if the player got a straight flush
						System.out.println("Straight Flush");
						intMoney = intMoney + intBet * 50;
					
						con.drawString("You got: STRAIGHT FLUSH", 410, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.FourOfAKind(intHand)) { //checks to see if the player got a four of a kind
						System.out.println("Four of a Kind");
						intMoney = intMoney + intBet * 25;
					
						con.drawString("You got: FOUR OF A KIND", 400, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.FullHouse(intHand)) { //checks to see if the player got a full house
						System.out.println("Full House");
						intMoney = intMoney + intBet * 9;
					
						con.drawString("You got: FULL HOUSE", 420, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.Flush(intHand)) { //checks to see if the player got a flush
						System.out.println("Flush");
						intMoney = intMoney + intBet * 6;
					
						con.drawString("You got: FLUSH", 480, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.Straight(intHand)) { //checks to see if the player got a straight
						System.out.println("Straight");
						intMoney = intMoney + intBet * 4;
				
						con.drawString("You got: STRAIGHT", 460, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.ThreeOfAKind(intHand)) { //checks to see if the player got a three of a kind
						System.out.println("Three of a Kind");
						intMoney = intMoney + intBet * 3;
					
						con.drawString("You got: THREE OF A KIND", 400, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.TwoPair(intHand)) { //checks to see if the player got a two pair
						System.out.println("Two Pair");
						intMoney = intMoney + intBet * 2;
					
						con.drawString("You got: TWO PAIR", 455, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else if (toolbox.JacksOrBetter(intHand)) { //checks to see if the player got a jacks or better
						System.out.println("Jacks or Better");
						intMoney = intMoney + intBet;
					
						con.drawString("You got: JACKS OR BETTER", 380, 80);
						toolbox.displayWinHand(con, intHand, intPositions2, headings, other, normal);
					} else { //player did not get anything
						System.out.println("Nothing");
					
						con.drawString("You got: NOTHING", 455, 80);
						toolbox.displayLoseHand(con, intHand, intPositions2, headings, other, normal);
					}
					System.out.println("You now have: $" + intMoney);
					con.setDrawFont(other);
				
					con.drawString("You Now Have: $" + intMoney, 445, 500);
					con.repaint();
					con.sleep(400);
					
					if (intMoney == 0) { 
						//Game over. The player can't play anymore. No more replays
						System.out.println("Game Over");
						con.drawString("GAME OVER!", 535, 560);
						con.repaint();
						con.sleep(2000);
						strReplay = "n";
					} else {
						//Otherwise, the player still has money. Ask if they want to play again
						con.drawString("Play Again? (Y/N)", 425, 580);
						con.drawLine(765, 630, 820, 630);
						con.repaint();
				
						toolbox.printlines(con, 27);
						con.print("                                       ");
						strReplay = con.readLine(); //y = yes replay, n = no replay
						System.out.println("Replay? " + strReplay);
					}
					toolbox.clearscreen(con);
				}
				//no more replays
				//Print their username and score to the high scores file, and then close the file
				txtScores = new TextOutputFile("highscores.txt", true);
				txtScores.println(strUsername);
				txtScores.println(intMoney);
				txtScores.close();
			} else if (strMenuChoice.equalsIgnoreCase("v")) {
				System.out.println("View high scores"); //View high scores
				String strOk;
				strOk = "";
				while (!strOk.equalsIgnoreCase("ok")) {
					con.sleep(500);
					con.setDrawFont(headings);
					con.setTextFont(other);
					con.drawString("High Scores", 485, 35);
					con.repaint();
					con.sleep(500);
					
					toolbox.printlines(con, 4);
					
					int intCount;
					String strScore;
					String strName;
					String strHighScores[][]; //high scores array. column 0 stores the name. column 1 stores the score
					String strSpaces = "                                                                                             "; //used for formatting columns
					TextInputFile txtScores;
									
					intCount = 0;
					txtScores = new TextInputFile("highscores.txt");
					
					//count how many players there are, so I can initialize my strHighScores[][] to have intCount rows
					while (txtScores.eof() == false) {
						strName = txtScores.readLine();
						strScore = txtScores.readLine();
						intCount = intCount + 1;	
					}
					txtScores.close();					
					
					strHighScores = new String[intCount][2];
					txtScores = new TextInputFile("highscores.txt"); //reopen the highscores file
					intCount = 0;
					
					//insert the names and score into strHighScores[][]
					while (txtScores.eof() == false) {
						strName = txtScores.readLine();
						strScore = txtScores.readLine();
						strHighScores[intCount][0] = strName;
						strHighScores[intCount][1] = strScore;
						intCount = intCount + 1;
					}
					txtScores.close();
					System.out.println("There are " + intCount + " players in the high scores file");
					
					//Bubble sort. Sort strHighScores[][] in decreasing order using the score.
					int intNum;
					int intRow;
					String strTemp[];
					String strLeft;
					String strRight;
					
					for (intNum = 0; intNum < intCount - 1; intNum++) {
						for (intRow = 0; intRow < intCount - 1; intRow++) {
							strLeft = strHighScores[intRow][1]; 
							strRight = strHighScores[intRow+1][1];
							if (Integer.parseInt(strLeft) < Integer.parseInt(strRight)) {
								strTemp = strHighScores[intRow];
								strHighScores[intRow] = strHighScores[intRow+1];
								strHighScores[intRow+1] = strTemp;
							}
						}
					}
					
					//Output the high scores array. At most 10 players will be displayed
					System.out.println("High Scores Array:");
					for (intRow = 0; intRow < Math.max(10, intCount); intRow++) {
						if (intRow >= intCount) {
							strName = "____";
							strScore = "____";
						} else {
							strName = strHighScores[intRow][0];
							strScore = strHighScores[intRow][1];
						}
						System.out.println(strName + ": " + strScore);
						
						if (intRow < 10) {
							con.print(strSpaces.substring(0, 15));
							con.print(((intRow+1) + ": " + strName + strSpaces).substring(0, 30));
							con.println(strScore);
							con.sleep(500);
						}
					}
					
					con.setDrawFont(normal);
					con.drawString("Type ok to return to main menu", 425, 550);
					con.drawLine(550, 630, 700, 630);
					con.repaint();
					
					toolbox.printlines(con, 2);
					con.print("                              ");
										
					strOk = con.readLine();
					toolbox.clearscreen(con);
					if (!strOk.equalsIgnoreCase("ok")) {
						con.println("Please enter ok");
						con.sleep(1000);
						toolbox.clearscreen(con);
					}
					System.out.println(strOk);
				}
			} else if (strMenuChoice.equalsIgnoreCase("h")) {
				System.out.println("Help"); //Help
				String strOk;
				strOk = "";
				
				while (!strOk.equalsIgnoreCase("ok")) {
					con.setDrawFont(other);
					con.setTextFont(normal);
					con.drawString("1: Enter your bet", 440, 10);
					con.drawString("2: You are given 5 cards. Each card can be swapped once", 95, 70);
					con.drawString("3: After swapping, your winnings will be determined: ", 120, 130);
					toolbox.printlines(con, 8);
					String strSpaces = "                                                                                  ";
					con.print(strSpaces.substring(0, 8));
					con.println("- Royal Flush: [10, J, Q, K, A], same suit --> You win 800x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Straight Flush: [5 consecutive numbers], same suit --> You win 50x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Four of a Kind: [4 cards of the same value] --> You win 25x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Full House: [Pair + Three of a Kind] --> You win 9x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Flush: [All cards have same suit] --> You win 6x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Straight: [5 consecutive cards] --> You win 4x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Three of a Kind: [3 cards of the same value] --> You win 3x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Two Pair: [2 different pairs] --> You win 2x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- Jacks or Better: [Pair of J, Q, K, or A] --> You win 1x your bet");
					con.print(strSpaces.substring(0, 8));
					con.println("- All other: You lose your bet");
					con.drawString("4: You can play again, or stop playing and get added to High", 20, 470);
					con.drawString("Scores. If you have $0 left, game over (automatically added)", 20, 505);
					
					con.setDrawFont(normal);
					con.drawString("Type ok to return to main menu", 425, 565);
					con.drawLine(550, 625, 700, 625);
					toolbox.printlines(con, 6);
					con.print("                                              ");
					con.repaint();
					strOk = con.readLine();
					toolbox.clearscreen(con);
					if (!strOk.equalsIgnoreCase("ok")) {
						con.println("Please enter ok");
						con.sleep(1000);
						toolbox.clearscreen(con);
					}
					System.out.println(strOk);
				}
			} else if (strMenuChoice.equalsIgnoreCase("s")) {
				//Secret menu
				String strOk;
				strOk = "";
				while (!strOk.equalsIgnoreCase("ok")) {
					con.setDrawFont(headings);
					con.drawString("Why did the scarecrow win an award?", 170, 200);
					con.repaint();
					con.sleep(2500);
					con.setDrawFont(normal);
					con.drawString("Because he was outstanding in his field", 355, 300);
					con.repaint();
					con.sleep(2500);
					
					con.drawString("Enter \"ok\" to go back",  480, 490);
					con.drawLine(550, 555, 700, 555);
					con.repaint();
					toolbox.printlines(con, 21);
					con.print("                                             ");
					strOk = con.readLine();
					toolbox.clearscreen(con);
					if (!strOk.equalsIgnoreCase("ok")) {
						con.println("Please enter ok");
						con.sleep(1000);
						toolbox.clearscreen(con);
					}
					System.out.println(strOk);
				}
			}
			
			strMenuChoice = toolbox.startPlay(con, headings, normal);
			System.out.println("Menu Choice: " + strMenuChoice);
			toolbox.clearscreen(con);
		}
		
		//Closing animation
		int intCount;
		BufferedImage imgClose;
		imgClose = con.loadImage("thanksforplaying.png");
		for (intCount = 0; intCount < 500; intCount++) {
			con.setDrawColor(new Color(148, 0, 211));
			con.fillRect(0, 0+intCount, 182, 182);
			con.setDrawColor(new Color(75, 0, 130));
			con.fillRect(182, 0+intCount, 182, 182);
			con.setDrawColor(new Color(0, 0, 255));
			con.fillRect(364, 0+intCount, 182, 182);
			con.setDrawColor(new Color(0, 255, 0));
			con.fillRect(546, 0+intCount, 182, 182);
			con.setDrawColor(new Color(255, 255, 0));
			con.fillRect(728, 0+intCount, 182, 182);
			con.setDrawColor(new Color(255, 127, 0));
			con.fillRect(910, 0+intCount, 182, 182);
			con.setDrawColor(new Color(255, 0, 0));
			con.fillRect(1092, 0+intCount, 182, 182);
			if (intCount > 200) {
				con.drawImage(imgClose, 375, intCount-400);
			}
			con.repaint();
			con.sleep(8);
			if (intCount < 499) {
				toolbox.clearscreen(con);
			} else if (intCount == 499) {
				toolbox.clearscreen(con);
				con.drawImage(imgClose, 375, intCount-400);
				con.repaint();
			}
		}
		con.sleep(1000);
		con.closeConsole();		
	}
}
