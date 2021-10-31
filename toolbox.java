//Video Poker Toolbox
//Louis Sun
//April 8, 2021
//Version 7.27

import arc.*;
import java.awt.*;
import java.awt.image.*;

public class toolbox {	
	public static String startPlay(Console con, Font headings, Font normal) {
		//The first step in playing the game - choosing a menu option!
		//Returns a valid menu option (a string value) to strMenuChoice in the main program
		
		displayPlay(con, headings, normal); //Displays the main menu screen
		
		String strMenuChoice;
		strMenuChoice = con.readLine();
		//Input a menu choice
		//The while loop forces the user to input a valid menu choice and follow the instructions
		//The user will be stuck at the main menu until they input a valid choice
		while (!strMenuChoice.equalsIgnoreCase("p") && !strMenuChoice.equalsIgnoreCase("v") && !strMenuChoice.equalsIgnoreCase("h") && !strMenuChoice.equalsIgnoreCase("q") && !strMenuChoice.equalsIgnoreCase("s")) {
			System.out.println("Error Play");
			clearscreen(con);
			con.println("Please enter a valid character!");
			con.sleep(1000);
			clearscreen(con);
			
			displayPlay(con, headings, normal);
			strMenuChoice = con.readLine();
		}
		return strMenuChoice;
	}
	
	public static void displayPlay(Console con, Font headings, Font normal) {
		//Draw everything in the main menu
		
		con.setDrawFont(headings);
		con.drawString("Video Poker", 480, 67);
		con.setDrawFont(normal);
		con.setTextFont(normal);
		con.drawString("(P)lay Game" , 545, 200);
		con.drawString("(V)iew High Scores", 500, 250);
		con.drawString("(H)elp", 580, 300);
		con.drawString("(Q)uit", 580, 350);
		con.drawString("What would you like to do?", 405, 500);
		con.drawLine(775, 530, 835, 530);
		con.repaint();
		
		printlines(con, 20);		
		con.print("                                                            ");
	}
	
	public static String getUsername(Console con, Font headings, Font normal) {
		//sets up the username for the player
		//returns a username (a string value) to strUsername in the main program
		con.setDrawFont(headings);
		con.setTextFont(normal);
		
		String strUsername;
		while (true) {
			displayUsername(con); //displays the username screen
			strUsername = con.readLine(); //the player inputs their username
			if (strUsername.length() > 20) {
				//Username cannot be longer than 20 characters
				System.out.println("Error Username");
				clearscreen(con);
				con.println("Your username cannot be longer than 20 characters!");
				con.sleep(1000);
				clearscreen(con);
			} else {
				//valid username
				if (strUsername.equals("strongertogether")) {
					//checks to see if the username is "strongertogether"
					//if it is, the user will see some more things on the screen and he/she gains an advantage
					con.sleep(1000);
					con.drawString("We are stronger together...", 370, 350);
					con.repaint();
					con.sleep(2000);
					con.drawString("You start with $2000!", 400, 440);
					con.repaint();
					con.sleep(2000);
				}
				clearscreen(con);
				System.out.println("Username: " + strUsername);
				return strUsername; //returns the username
			}
		}
	}
	
	public static void displayUsername(Console con) {
		//displays the screen that the user will see when they enter their username
		con.drawString("Enter your username:", 405, 200);
		con.drawLine(400, 305, 830, 305);
		con.repaint();
		printlines(con, 11);
		con.print("                               ");
	}
	
	public static int startBet(Console con, int intMoney, Font headings, Font other, Font normal) {
		//sets the intBet variable to a proper number based on how much money the user has and how much the user inputs as their bet
		
		int intBet;
		while (true) {
			displayBet(con, intMoney, headings, other, normal); //display the betting screen
			intBet = con.readInt();
			
			if (intBet > intMoney) {
				//You cannot bet more than the amount of money you have
				clearscreen(con);
				con.println("You cannot bet this much money. You do not have $" + intBet + ".");
				con.sleep(1000);
				clearscreen(con);
			} else {
				System.out.println("Bet: " + intBet);
				con.sleep(800);
				con.drawString("Shuffling Cards...", 450, 500);
				con.repaint();
				con.sleep(1000);
				clearscreen(con);
				return intBet;
			}
		}
	}
	
	public static void displayBet(Console con, int intMoney, Font headings, Font other, Font normal) {
		//displays/draws the betting screen that the user will see when he/she enters their bet
		con.setDrawFont(headings);
		con.setTextFont(normal);
		con.drawString("You have $" + intMoney, 440, 150);
		con.setDrawFont(other);
		con.drawString("How much do you want to bet? No decimals.", 250, 300);
		con.drawLine(420, 400, 850, 400);
		con.repaint();
		printlines(con, 15);
		con.print("                                          ");
	}
	
	public static int[][] createDeck(int intDeck[][]) {
		//creates the intDeck[][] array
		int intCount;
		for (intCount = 0; intCount < 52; intCount++) {
			//column 0 stores the value (1-13, where 1 is A, 11 is J, 12 is Q, 13 is K)
			intDeck[intCount][0] = intCount % 13 + 1;
			//column 1 stores the suit (1 = diamonds, 2 = clubs, 3 = hearts, 4 = spades)
			intDeck[intCount][1] = intCount / 13 + 1;
			//column 2 stores a random value from 1-100 for shuffling purposes
			intDeck[intCount][2] = (int)(Math.random()*100+1);
		}
		return intDeck;
	}
	
	public static void shuffleDeck(int intDeck[][]) {
		//shuffles the deck by bubble sorting column 2 of the intDeck[][] array
		int intNum;
		int intRow;
		int intTmp[];
		int intLeft;
		int intRight;
		
		for (intNum = 0; intNum < 51; intNum++) {
			for (intRow = 0; intRow < 51; intRow++) {
				intLeft = intDeck[intRow][2]; 
				intRight = intDeck[intRow+1][2];
				if (intLeft > intRight) {
					intTmp = intDeck[intRow];
					intDeck[intRow] = intDeck[intRow+1];
					intDeck[intRow+1] = intTmp;
				}
			}
		}
	}
	
	public static void displayandcheckDeck(int intDeck[][]) {
		//checks if the deck contains all 52 cards (for debugging purposes)
		boolean blnvis[];
		blnvis = new boolean[52];
		
		int intCount;
		int intNum;
		intNum = 0;
		for (intCount = 0; intCount < 52; intCount++) {
			System.out.println(intDeck[intCount][0] + " " + intDeck[intCount][1] + " " + intDeck[intCount][2]);
			blnvis[(intDeck[intCount][1] - 1) * 13 + intDeck[intCount][0] - 1] = true;
		}
		
		for (intCount = 0; intCount < 52; intCount++) {
			if (blnvis[intCount] == true) {
				intNum++;
			}
		}
		if (intNum == 52) {
			System.out.println("Deck is complete");
		}
	}
	
	public static boolean swapcards(Console con, int intHand[][], int intPositions[][], int intDeck[][], Font headings, Font other, Font normal) {
		//the player chooses the cards that they want to swap
		con.setDrawFont(normal);
		con.setTextFont(normal);
		
		con.drawString("Enter the card numbers that you would like to swap. Type ok when you are done", 100, 435);
		con.repaint();
		
		printlines(con, 19); 
		con.print("                                              ");
		
		boolean blnswap[]; //blnswap[i] = true, means that the i-th card has been swapped; a card cannot be swapped twice
		
		//temporary array that stores the current hand that the player has
		//if the player makes an invalid swap, the current hand will not be changed
		int intTmp[][]; 
		String strPos; //the card number that the player will input in String format
		int intPos; //the card number that the player will input in Integer format
		int intTop; //keeps track of the top of deck. Stores the row number that the next card should be drawn from.
		int intRow;
		blnswap = new boolean[5];
		intTmp = new int[5][2];
		
		intTop = 5; //The cards from 0-4 have already been drawn
		for (intRow = 0; intRow < 5; intRow++) {
			//copy the intHand[][] array to intTmp[][]
			intTmp[intRow][0] = intHand[intRow][0];
			intTmp[intRow][1] = intHand[intRow][1];
		}
			
		strPos = con.readLine();
		while (!strPos.equalsIgnoreCase("ok")) {
			if (!strPos.equals("0") && !strPos.equals("1") && !strPos.equals("2") && !strPos.equals("3") && !strPos.equals("4")) {
				//the player entered an invalid swap
				clearscreen(con);
				con.println("Please enter a proper card number!");
				con.sleep(1000);
				clearscreen(con);
				
				for (intRow = 0; intRow < 5; intRow++) {
					//the player's hand is reset to their original values
					intHand[intRow][0] = intTmp[intRow][0];
					intHand[intRow][1] = intTmp[intRow][1];
				}
				
				return false; //Redo the swaps
			}
			intPos = Integer.parseInt(strPos);
			if (blnswap[intPos] == true) { //check if the card has already been swapped. If it has, this is also an invalid swap
				clearscreen(con);
				con.println("You cannot swap the same card twice!");
				con.sleep(2000);
				clearscreen(con);
				
				for (intRow = 0; intRow < 5; intRow++) {
					//the player's hand is reset to their original values
					intHand[intRow][0] = intTmp[intRow][0];
					intHand[intRow][1] = intTmp[intRow][1];
				}
				
				return false;
			}
			//valid swap
			System.out.println("Swap Card: " + intPos);
			blnswap[intPos] = true;
			displayswap(con, intPos, intPositions); //display that the current card has been swapped
			drawcard(intPos, intTop, intHand, intDeck); //replace the intPos-th card in intHand[][] with the intTop-th card in intDeck[][]
			intTop = intTop + 1; //The card at intDeck[intTop] has been taken, increment the intTop value
			con.print("                                              ");
			strPos = con.readLine();
		}
		return true;
	}
	
	public static void displayswap(Console con, int intIdx, int intPositions[][]) {
		//show that the card at the intIdx-th position has been swapped by drawing an image 
		//of the back of a card at the coordinates (intPositions[intIdx][0], intPositions[intIdx][1])
		BufferedImage imgCardBack = con.loadImage("blue_back.png");
		con.drawImage(imgCardBack, intPositions[intIdx][0], intPositions[intIdx][1]);
	}
	
	public static void drawcard(int intIdx, int intDeckPos, int intHand[][], int intDeck[][]) {
		//draw a card from the Deck and place it into player's Hand
		//transfer the values in intDeck[intDeckPos][0/1] to intHand[intIdx][0/1]
		intHand[intIdx][0] = intDeck[intDeckPos][0];
		intHand[intIdx][1] = intDeck[intDeckPos][1];
	}
	
	public static void displayHand(Console con, int intHand[][], int intPositions[][], Font headings, Font other, Font normal) {
		//Display the player's hand of 5 cards
		con.setTextFont(normal);
		con.setDrawFont(headings);
		con.drawString("Your Hand:", 500, 10);
		con.repaint();
		con.setDrawFont(other);
		
		int intCount;
		for (intCount = 0; intCount < 5; intCount++) {
			//display each card individually
			displaycard(con, intCount, intHand, intPositions);
		}
	}
	
	public static void displayWinHand(Console con, int intHand[][], int intPositions[][], Font headings, Font other, Font normal) {
		//draw the winning scenario (the player doesn't lose all the money that they bet)
		con.setDrawFont(headings);
		con.setTextFont(other);
		con.drawString("YOU WIN!", 520, 10);
		con.setDrawFont(normal);
		con.repaint();
		
		int intCount;
		for (intCount = 0; intCount < 5; intCount++) {
			//display the player's hand by displaying each card individually
			displaycard(con, intCount, intHand, intPositions);
		}
		con.sleep(500);
	}
	
	public static void displayLoseHand(Console con, int intHand[][], int intPositions[][], Font headings, Font other, Font normal) {
		//display the losing scenario (the player loses all the money that they bet)
		con.setDrawFont(headings);
		con.setTextFont(other);
		con.drawString("YOU LOSE!", 500, 10);
		con.setDrawFont(other);
		con.repaint();
		
		int intCount;
		for (intCount = 0; intCount < 5; intCount++) {
			//display the player's hand by displaying each card individually
			displaycard(con, intCount, intHand, intPositions);
		}
		con.sleep(500);
	}
	
	public static void displaycard(Console con, int intIdx, int intHand[][], int intPositions[][]) {	
		//displays one card
		//displays the card at position intIdx
		//the position of that card will be determined by intPositions[][]
			//x-coordinate = intPositions[intIdx][0]
			//y-coordinate = intPositions[intIdx][1]
			
		//Load the images			
		BufferedImage imgAD = con.loadImage("AD.png");
		BufferedImage img2D = con.loadImage("2D.png");
		BufferedImage img3D = con.loadImage("3D.png");
		BufferedImage img4D = con.loadImage("4D.png");
		BufferedImage img5D = con.loadImage("5D.png");
		BufferedImage img6D = con.loadImage("6D.png");
		BufferedImage img7D = con.loadImage("7D.png");
		BufferedImage img8D = con.loadImage("8D.png");
		BufferedImage img9D = con.loadImage("9D.png");
		BufferedImage img10D = con.loadImage("10D.png");
		BufferedImage imgJD = con.loadImage("JD.png");
		BufferedImage imgQD = con.loadImage("QD.png");
		BufferedImage imgKD = con.loadImage("KD.png");
		
		BufferedImage imgAC = con.loadImage("AC.png");
		BufferedImage img2C = con.loadImage("2C.png");
		BufferedImage img3C = con.loadImage("3C.png");
		BufferedImage img4C = con.loadImage("4C.png");
		BufferedImage img5C = con.loadImage("5C.png");
		BufferedImage img6C = con.loadImage("6C.png");
		BufferedImage img7C = con.loadImage("7C.png");
		BufferedImage img8C = con.loadImage("8C.png");
		BufferedImage img9C = con.loadImage("9C.png");
		BufferedImage img10C = con.loadImage("10C.png");
		BufferedImage imgJC = con.loadImage("JC.png");
		BufferedImage imgQC = con.loadImage("QC.png");
		BufferedImage imgKC = con.loadImage("KC.png");
		
		BufferedImage imgAH = con.loadImage("AH.png");
		BufferedImage img2H = con.loadImage("2H.png");
		BufferedImage img3H = con.loadImage("3H.png");
		BufferedImage img4H = con.loadImage("4H.png");
		BufferedImage img5H = con.loadImage("5H.png");
		BufferedImage img6H = con.loadImage("6H.png");
		BufferedImage img7H = con.loadImage("7H.png");
		BufferedImage img8H = con.loadImage("8H.png");
		BufferedImage img9H = con.loadImage("9H.png");
		BufferedImage img10H = con.loadImage("10H.png");
		BufferedImage imgJH = con.loadImage("JH.png");
		BufferedImage imgQH = con.loadImage("QH.png");
		BufferedImage imgKH = con.loadImage("KH.png");
		
		BufferedImage imgAS = con.loadImage("AS.png");
		BufferedImage img2S = con.loadImage("2S.png");
		BufferedImage img3S = con.loadImage("3S.png");
		BufferedImage img4S = con.loadImage("4S.png");
		BufferedImage img5S = con.loadImage("5S.png");
		BufferedImage img6S = con.loadImage("6S.png");
		BufferedImage img7S = con.loadImage("7S.png");
		BufferedImage img8S = con.loadImage("8S.png");
		BufferedImage img9S = con.loadImage("9S.png");
		BufferedImage img10S = con.loadImage("10S.png");
		BufferedImage imgJS = con.loadImage("JS.png");
		BufferedImage imgQS = con.loadImage("QS.png");
		BufferedImage imgKS = con.loadImage("KS.png");
		
		int intValue;
		int intSuit;
		
		intValue = intHand[intIdx][0];
		intSuit = intHand[intIdx][1];
		
		System.out.print("Idx: " + intIdx + ", Value: " + intValue + ", Suit: " + intSuit + " --> " );
		
		//draw the card image
		if (intValue == 1 && intSuit == 1) {
			System.out.println("Ace of Diamonds");
			con.drawImage(imgAD, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 2 && intSuit == 1) {
			System.out.println("Two of Diamonds");
			con.drawImage(img2D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 3 && intSuit == 1) {
			System.out.println("Three of Diamonds");
			con.drawImage(img3D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 4 && intSuit == 1) {
			System.out.println("Four of Diamonds");
			con.drawImage(img4D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 5 && intSuit == 1) {
			System.out.println("Five of Diamonds");
			con.drawImage(img5D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 6 && intSuit == 1) {
			System.out.println("Six of Diamonds");
			con.drawImage(img6D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 7 && intSuit == 1) {
			System.out.println("Seven of Diamonds");
			con.drawImage(img7D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 8 && intSuit == 1) {
			System.out.println("Eight of Diamonds");
			con.drawImage(img8D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 9 && intSuit == 1) {
			System.out.println("Nine of Diamonds");
			con.drawImage(img9D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 10 && intSuit == 1) {
			System.out.println("Ten of Diamonds");
			con.drawImage(img10D, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 11 && intSuit == 1) {
			System.out.println("Jack of Diamonds");
			con.drawImage(imgJD, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 12 && intSuit == 1) {
			System.out.println("Queen of Diamonds");
			con.drawImage(imgQD, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 13 && intSuit == 1) {
			System.out.println("King of Diamonds");
			con.drawImage(imgKD, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 1 && intSuit == 2) {
			System.out.println("Ace of Clubs");
			con.drawImage(imgAC, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 2 && intSuit == 2) {
			System.out.println("Two of Clubs");
			con.drawImage(img2C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 3 && intSuit == 2) {
			System.out.println("Three of Clubs");
			con.drawImage(img3C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 4 && intSuit == 2) {
			System.out.println("Four of Clubs");
			con.drawImage(img4C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 5 && intSuit == 2) {
			System.out.println("Five of Clubs");
			con.drawImage(img5C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 6 && intSuit == 2) {
			System.out.println("Six of Clubs");
			con.drawImage(img6C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 7 && intSuit == 2) {
			System.out.println("Seven of Clubs");
			con.drawImage(img7C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 8 && intSuit == 2) {
			System.out.println("Eight of Clubs");
			con.drawImage(img8C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 9 && intSuit == 2) {
			System.out.println("Nine of Clubs");
			con.drawImage(img9C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 10 && intSuit == 2) {
			System.out.println("Ten of Clubs");
			con.drawImage(img10C, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 11 && intSuit == 2) {
			System.out.println("Jack of Clubs");
			con.drawImage(imgJC, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 12 && intSuit == 2) {
			System.out.println("Queen of Clubs");
			con.drawImage(imgQC, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 13 && intSuit == 2) {
			System.out.println("King of Clubs");
			con.drawImage(imgKC, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 1 && intSuit == 3) {
			System.out.println("Ace of Hearts");
			con.drawImage(imgAH, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 2 && intSuit == 3) {
			System.out.println("Two of Hearts");
			con.drawImage(img2H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 3 && intSuit == 3) {
			System.out.println("Three of Hearts");
			con.drawImage(img3H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 4 && intSuit == 3) {
			System.out.println("Four of Hearts");
			con.drawImage(img4H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 5 && intSuit == 3) {
			System.out.println("Five of Hearts");
			con.drawImage(img5H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 6 && intSuit == 3) {
			System.out.println("Six of Hearts");
			con.drawImage(img6H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 7 && intSuit == 3) {
			System.out.println("Seven of Hearts");
			con.drawImage(img7H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 8 && intSuit == 3) {
			System.out.println("Eight of Hearts");
			con.drawImage(img8H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 9 && intSuit == 3) {
			System.out.println("Nine of Hearts");
			con.drawImage(img9H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 10 && intSuit == 3) {
			System.out.println("Ten of Hearts");
			con.drawImage(img10H, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 11 && intSuit == 3) {
			System.out.println("Jack of Hearts");
			con.drawImage(imgJH, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 12 && intSuit == 3) {
			System.out.println("Queen of Hearts");
			con.drawImage(imgQH, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 13 && intSuit == 3) {
			System.out.println("King of Hearts");
			con.drawImage(imgKH, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 1 && intSuit == 4) {
			System.out.println("Ace of Spades");
			con.drawImage(imgAS, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 2 && intSuit == 4) {
			System.out.println("Two of Spades");
			con.drawImage(img2S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 3 && intSuit == 4) {
			System.out.println("Three of Spades");
			con.drawImage(img3S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 4 && intSuit == 4) {
			System.out.println("Four of Spades");
			con.drawImage(img4S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 5 && intSuit == 4) {
			System.out.println("Five of Spades");
			con.drawImage(img5S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 6 && intSuit == 4) {
			System.out.println("Six of Spades");
			con.drawImage(img6S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 7 && intSuit == 4) {
			System.out.println("Seven of Spades");
			con.drawImage(img7S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 8 && intSuit == 4) {
			System.out.println("Eight of Spades");
			con.drawImage(img8S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 9 && intSuit == 4) {
			System.out.println("Nine of Spades");
			con.drawImage(img9S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 10 && intSuit == 4) {
			System.out.println("Ten of Spades");
			con.drawImage(img10S, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 11 && intSuit == 4) {
			System.out.println("Jack of Spades");
			con.drawImage(imgJS, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 12 && intSuit == 4) {
			System.out.println("Queen of Spades");
			con.drawImage(imgQS, intPositions[intIdx][0], intPositions[intIdx][1]);
		} else if (intValue == 13 && intSuit == 4) {
			System.out.println("King of Spades");
			con.drawImage(imgKS, intPositions[intIdx][0], intPositions[intIdx][1]);
		}
		
		String strIdx;
		if (intIdx == 0) {
			strIdx = "0";
		} else if (intIdx == 1) {
			strIdx = "1";
		} else if (intIdx == 2) {
			strIdx = "2";
		} else if (intIdx == 3) {
			strIdx = "3";
		} else if (intIdx == 4) {
			strIdx = "4";
		} else {
			strIdx = "5";
		}
		
		//draw the card position underneath the card (0,1,2,3,4)
		con.drawString(strIdx, (2*intPositions[intIdx][0] + 200)/2 - 10, intPositions[intIdx][1] + 300);
		con.repaint();
	}
	
	public static boolean JacksOrBetter(int intHand[][]) {
		//checks to see if the current hand is a Jacks or Better. Returns true if it is
		
		int intValue;
		int intCount;
		int intRow;
		for (intValue = 1; intValue <= 13; intValue++) {
			if (intValue == 1 || intValue == 11 || intValue == 12 || intValue == 13) {
				intCount = 0;
				for (intRow = 0; intRow < 5; intRow++) {
					if (intHand[intRow][0] == intValue) {
						intCount = intCount + 1;
					}
				}
				if (intCount == 2) {
					return true;	
				}
			}
		}
		return false;
	}
	
	public static boolean TwoPair(int intHand[][]) {
		//checks to see if the current hand is a two pair. Returns true if it is
		
		int intPairs; //stores the number of distinct pairs in the current hand
		int intValue;
		int intRow;
		int intCount;
		intPairs = 0;
		
		for (intValue = 1; intValue <= 13; intValue++) { //Test all possible values (1-13)
			intCount = 0;
			//intCount stores how many cards in intHand[][] have the same value as intValue
			for (intRow = 0; intRow < 5; intRow++) {
				if (intHand[intRow][0] == intValue) {
					intCount = intCount + 1;
				}
			}
			if (intCount == 2) { 
				//there is one pair with a value of intValue
				//increment intPairs
				intPairs = intPairs + 1;
			}
		}
		if (intPairs == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean FullHouse(int intHand[][]) {
		//checks to see if the current hand is a full house. Returns true if it is
		
		//a full house is when there is a pair and a triple
		 if (Pair(intHand) && ThreeOfAKind(intHand)) { 
			 return true; 
		 } else { 
			 return false;
		 }
	}
	
	public static boolean Pair(int intHand[][]) {
		//checks to see if the current hand has a pair. Returns true if it does.
		
		int intValue;
		int intRow;
		int intCount;
		for (intValue = 1; intValue <= 13; intValue++) { //Test all possible values (1-13)
			intCount = 0;
			//intCount stores how many cards in intHand[][] have the same value as intValue
			for (intRow = 0; intRow < 5; intRow++) {
				if (intHand[intRow][0] == intValue) {
					intCount = intCount + 1;
				}
			}
			if (intCount == 2) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean ThreeOfAKind(int intHand[][]) {
		//checks to see if the current hand is a three of a kind. returns true if it is
		
		int intCount;
		int intValue;
		int intRow;
		for (intValue = 1; intValue <= 13; intValue++) { //Test all possible values (1-13)
			intCount = 0;
			//intCount stores how many cards in intHand[][] have the same value as intValue
			for (intRow = 0; intRow < 5; intRow++) {
				if (intHand[intRow][0] == intValue) {
					intCount = intCount + 1;
				}
			}
			if (intCount == 3) { 
				return true;
			}
		}
		return false;
	}
	
	public static boolean FourOfAKind(int intHand[][]) {
		//checks if the current hand is a four of a kind. Returns true if it is.
		
		int intValue;
		int intCount;
		int intRow;
		
		for (intValue = 1; intValue <= 13; intValue++) { //Test all possible values (1-13)
			intCount = 0;
			//intCount stores how many cards in intHand[][] have the same value as intValue
			for (intRow = 0; intRow < 5; intRow++) {
				if (intHand[intRow][0] == intValue) {
					intCount = intCount + 1;
				}
			}
			if (intCount == 4) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean StraightFlush(int intHand[][]) {
		//checks if the current hand is a straight flush. Returns true if it is
		
		//must be both a straight and a flush
		if (Straight(intHand) && Flush(intHand)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean RoyalFlush(int intHand[][]) {
		//checks if the current hand is a royal flush. Returns true if it is
		
		int intFreq[]; //frequency array
		intFreq = new int[15]; //intFreq[i] = how many cards have a value of i
		
		int intRow;
		int intValue;
		for (intRow = 0; intRow < 5; intRow++) {
			intValue = intHand[intRow][0];
			if (intValue == 1) {
				//here, the ace must take the value of 14
				intFreq[14] = intFreq[14] + 1;
			} else {
				intFreq[intValue] = intFreq[intValue] + 1;
			}
		}
		//there must be exactly one 10, one jack, one queen, one king, and one ace
		//they also must all have the same suit (it must also be a flush)
		if (Flush(intHand) == true && intFreq[10] == 1 && intFreq[11] == 1 && intFreq[12] == 1 && intFreq[13] == 1 && intFreq[14] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean Straight(int intHand[][]) {
		//checks to see if the current hand is a straight. Returns true if it is
		
		int intFreq[]; //frequency array
		intFreq = new int[15]; //intFreq[i] = how many cards have a value of i
		
		int intRow;
		int intValue;
		for (intRow = 0; intRow < 5; intRow++) {
			intValue = intHand[intRow][0];
			if (intValue == 1) {
				//ace can be both a 1 or a 14
				intFreq[1] = intFreq[1] + 1;
				intFreq[14] = intFreq[14] + 1;
			} else {
				intFreq[intValue] = intFreq[intValue] + 1;
			}
		}
		
		for (intRow = 5; intRow <= 14; intRow++) {
			//If you have 5 consecutive numbers, it's a straight
			if (intFreq[intRow-4] == 1 && intFreq[intRow-3] == 1 && intFreq[intRow-2] == 1 && intFreq[intRow-1] == 1 && intFreq[intRow] == 1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean Flush(int intHand[][]) {
		//checks to see if the current hand is a flush. Returns true if it is
		
		//they must all have the same suit
		if (intHand[0][1] == intHand[1][1] && intHand[1][1] == intHand[2][1] && intHand[2][1] == intHand[3][1] && intHand[3][1] == intHand[4][1]) {
			return true;
		} else { 
			return false; 
		}
	}
	
	public static void printlines(Console con, int intTimes) {
		//Console will output intTimes empty lines
		//This is used so that input can be read somewhere else on the screen
		
		int intNum;
		for (intNum = 0; intNum < intTimes; intNum++) {
			con.println();
		}
	}
	
	public static void initializepositions(int intPositions[][], int intPositions2[][]) {
		//intPositions stores the coordinates of where the 5 cards will be drawn when the player is setting up his hand
		//intPositions2 stores the coordinates of where the 5 cards will be drawn in the win and lose scenarios
		
		intPositions[0][0] = 40;
		intPositions[0][1] = 75;
		intPositions[1][0] = 290;
		intPositions[1][1] = 75;
		intPositions[2][0] = 540;
		intPositions[2][1] = 75;
		intPositions[3][0] = 790;
		intPositions[3][1] = 75;
		intPositions[4][0] = 1040;
		intPositions[4][1] = 75;
		
		intPositions2[0][0] = 40;
		intPositions2[0][1] = 150;
		intPositions2[1][0] = 290;
		intPositions2[1][1] = 150;
		intPositions2[2][0] = 540;
		intPositions2[2][1] = 150;
		intPositions2[3][0] = 790;
		intPositions2[3][1] = 150;
		intPositions2[4][0] = 1040;
		intPositions2[4][1] = 150;
	}
	
	public static void clearscreen(Console con) {
		//clears the console screen
		
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1920, 650);
		con.setDrawColor(Color.WHITE);
	}
}
