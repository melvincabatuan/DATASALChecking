/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

package datasal.Narag.yahtzee;
       
import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import javax.swing.JFrame;
import datasal.Narag.myarraylist.MyArrayList;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	/* Private instance variables */
	private int nPlayers; //number of players
	private String[] playerNames; //an array of player names
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator(); //random number generator
	private int[] dieResults = new int [N_DICE]; //stores the most recently rolled dice numbers
	private int[][] categoryScores; //stores the score for each category for each player
	private int category; //selected category
	private int[][] selectedCategories; //stores the already selected categories
	private int playerNumber = 0;
        private MyArrayList hiscore = new MyArrayList();
        private MyArrayList numscores = new MyArrayList();
        
	public void runYahtzee() {
		new Yahtzee().start();
	}
	
        public void init(){
            hiscore.createList();
        }
        
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		while(true) {
			if(nPlayers <= MAX_PLAYERS) break;
			nPlayers = dialog.readInt("You can only enter up to " + MAX_PLAYERS +" number of players. Enter number of players");
		}
		playerNames = new String[nPlayers];
		categoryScores =  new int [nPlayers + 1][N_CATEGORIES+1];
		selectedCategories = new int[nPlayers+1][N_CATEGORIES+1];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for(int j=1; j <= nPlayers; j++) {
				initializeFirstRoll(j);
				secondAndThirdRoll(j);
				selectCategory(j);
			}
		}
		calculateResults();
		calculateWinner();
	}

	
	private void initializeFirstRoll(int playerNumber) {
		for(int i = 0; i < N_DICE; i++) {
			int dieRoll = rgen.nextInt(1,6);
			dieResults[i] = dieRoll;
		}
		display.printMessage(playerNames[playerNumber - 1] + "'s turn! Click the " + "\"Roll Dice\" " + "button to roll the dice.");
		display.waitForPlayerToClickRoll(playerNumber);
		display.displayDice(dieResults);
	}
	
	/* For the second and third roll, 
	 * the player selects the dice he or she wants to re-roll, 
	 * the selected dice are re-rolled, and the new 
	 * dice values are displayed and stored in the diceResults array */
	private void secondAndThirdRoll(int playerNumber) {
		for (int i = 0; i < 2; i++) {
			display.printMessage("Select the dice you wish to re-roll and click " + "\"Roll Again\"");
			display.waitForPlayerToSelectDice();
			for(int j = 0; j < N_DICE; j++) {
				if(display.isDieSelected(j) == true) {
					int dieRoll = rgen.nextInt(1,6);
					dieResults[j] = dieRoll;
				}
			}
			display.displayDice(dieResults);
		}
	}

	/* Pre-condition: The player has rolled the dice three times. 
	 * The player selects the category for the dice. 
	 * The player cannot select a category that he/she already chose in a previous turn.*/
	private void selectCategory(int playerNumber) {
		while(true) {
			display.printMessage("Select a category for this roll");
			category = display.waitForPlayerToSelectCategory();
			if(selectedCategories[playerNumber][category] == 0) {
				calculateCategoryScore(playerNumber);
				break;
			}
		}	
	}
	
	
	private void calculateCategoryScore(int playerNumber) {
		selectedCategories[playerNumber][category] = 1;
		int totalScore;
		if(checkCategory(dieResults, category) == true) {
			setCategoryScore(playerNumber, category);
			int score = categoryScores[playerNumber][category];
			display.updateScorecard(category, playerNumber, score);
			calculateTotalScores(playerNumber);
			totalScore = categoryScores[playerNumber][TOTAL];
			display.updateScorecard(TOTAL, playerNumber, totalScore);
			}
		else {
			categoryScores[playerNumber][category] = 0;
			display.updateScorecard(category, playerNumber, 0);
			calculateTotalScores(playerNumber);
			totalScore = categoryScores[playerNumber][TOTAL];
			display.updateScorecard(TOTAL, playerNumber, totalScore);
		}
	}
	
	/*sets the score in the categoryScores matrix for each player 
	based on the scoring category they chose after rolling the dice*/ 
	private void setCategoryScore(int playerNumber, int category) {
		int score = 0;
		if(category == ONES) {
			for(int i = 0; i < N_DICE; i++) {
				 if(dieResults[i] == 1) {
					 score = score + 1;
				 }
			 }
		}
                
                else if (category == TWOS){
                        for(int i = 0; i < N_DICE; i++) {
				 if(dieResults[i] == 2) {
					 score = score + 2;
				 }
			 }
                }
                
                else if (category == THREES){
                        for(int i = 0; i < N_DICE; i++) {
				 if(dieResults[i] == 3) {
					 score = score + 3;
				 }
			 }
                }
                
                else if (category == FOURS){
                        for(int i = 0; i < N_DICE; i++) {
				 if(dieResults[i] == 4) {
					 score = score + 4;
				 }
			 }
                }
                
                else if (category == FIVES){
                        for(int i = 0; i < N_DICE; i++) {
				 if(dieResults[i] == 5) {
					 score = score + 5;
				 }
			 }
                }
                
                else if (category == SIXES){
                        for(int i = 0; i < N_DICE; i++) {
				 if(dieResults[i] == 6) {
					 score = score + 6;
				 }
			 }
                }
		else if(category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == CHANCE) {
			for(int i = 0; i<N_DICE; i++) {
				score += dieResults[i];
			}
		}
		else if(category == FULL_HOUSE) {
			score = 25;
		}
		else if(category == SMALL_STRAIGHT) {
			score = 30;
		}
		else if(category == LARGE_STRAIGHT) {
			score = 40;
		}
		else if(category == YAHTZEE) {
			score = 50;
		}
		categoryScores[playerNumber][category] = score;
	}
	
	
	/*sets the total scores for each player */
	private void calculateTotalScores(int playerNumber) {
		int upperScore = 0;
		int lowerScore = 0;
		int totalScore = 0;
		for(int i = ONES; i <= SIXES; i++) {
			upperScore += categoryScores[playerNumber][i];
			}
		for(int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			lowerScore += categoryScores[playerNumber][i];
			}
		totalScore = upperScore + lowerScore; 
		categoryScores[playerNumber][UPPER_SCORE] = upperScore; 
		categoryScores[playerNumber][LOWER_SCORE] = lowerScore;
		categoryScores[playerNumber][TOTAL] = totalScore; 
	}
	
	/* Pre-condition: All players have completed the game. 
	 * Calculates and displays the Upper Score, Upper Bonus, and LowerScore */
	private void calculateResults() {
		for(int i = 1; i <= nPlayers; i++) {
			display.updateScorecard(UPPER_SCORE, i, categoryScores[i][UPPER_SCORE]);
			display.updateScorecard(LOWER_SCORE, i, categoryScores[i][LOWER_SCORE]);
			if(categoryScores[i][UPPER_SCORE] >= 63) {
				categoryScores[i][UPPER_BONUS] = 35;
			}
			display.updateScorecard(UPPER_BONUS, i, categoryScores[i][UPPER_BONUS]);
			categoryScores[i][TOTAL] = categoryScores[i][TOTAL] + categoryScores[i][UPPER_BONUS];
			display.updateScorecard(TOTAL, i, categoryScores[i][TOTAL]);
		}
	}
	
	/* Pre-condition: The game has ended, and all the final scores have been added up. 
	 * Calculates which player has the highest score and what the highest score is 
	 * and prints that information in a message at the very end of the game.*/
	private void calculateWinner() {
		int winningScore = 0;
		int winningPlayerNumber = 0;
		for(int i = 1; i<=nPlayers; i++) {
			int x = categoryScores[i][TOTAL];
			if( x > winningScore) {
				winningScore = x;
				winningPlayerNumber = i - 1;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winningPlayerNumber] + ", you're the winner with a total score of " + winningScore + "!");
                hiScore(winningScore, playerNames[winningPlayerNumber]);
                dispHiScore();
	}
        
        /**This implements the game's high score system**/
        private void hiScore(int score, String name){
                if (hiscore.isEmpty()){
                    hiscore.add(1, name + "   -   " + score);
                    numscores.add(1, score);
                 }
                else{
                    for(int a = 10; a > 0; a--){
                        int compare = (Integer) numscores.get(a);
                        if (score>compare){
                            int temp = compare;
                            if(a==10){
                                numscores.remove(a);
                                numscores.add(a, score);
                                hiscore.add(a, name + "   -   " + score);
                            }
                            else{
                            numscores.remove(a);
                            numscores.add(a+1, compare);
                            numscores.add(a, score);
                            hiscore.add(a, name + "   -   " + score);
                            }
                        }
                        else 
                        {
                            break;
                        }
                    }
                }
        }
        
        private void dispHiScore(){
            waitForClick();
            removeAll();
            for(int a=0;a<10;a++){
                println(hiscore.get(a));
            }
        }
        
	/* Pre-condition: The player has finished rolling the dice and selects a category. 
	 * This method returns true if the selected category matches 
	 * to the actual category correctly, and false if it does not match. */
	private boolean checkCategory(int[] dice, int category) {
		boolean categoryMatch = false;
		if(category >= ONES && category <= SIXES || category == CHANCE) {
			categoryMatch = true;
		}
		else {
			
			//creates an array for each possible dice value (1-6)
			ArrayList <Integer> ones = new ArrayList<Integer>();  
			ArrayList <Integer> twos = new ArrayList<Integer>(); 
			ArrayList <Integer> threes = new ArrayList<Integer>(); 
			ArrayList <Integer> fours = new ArrayList<Integer>(); 
			ArrayList <Integer> fives = new ArrayList<Integer>(); 
			ArrayList <Integer> sixes = new ArrayList<Integer>();
			
			/*goes through each rolled die and puts 1 as a place-holder into the appropriate ArrayList
			* e.g. if the first die value is 1, then 1 is added to the ones ArrayList or
			* if the second die value is 5, then 1 is added to the fives ArrayList*/
			for(int i = 0; i < N_DICE; i++) {
				if(dice[i] == 1) {
					ones.add(1);
				}
				else if(dice[i] == 2) {
					twos.add(1);
				}
				else if(dice[i] == 3) {
					threes.add(1);
				}
				else if(dice[i] == 4) {
					fours.add(1);
				}
				else if(dice[i] == 5) {
					fives.add(1);
				}
				else if(dice[i] == 6) {
					sixes.add(1);
				}
			}
			if(category == THREE_OF_A_KIND) {
				if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3 || sixes.size() >= 3) {
					categoryMatch = true;
				}
			}	
			else if(category == FOUR_OF_A_KIND) { 
				if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4 || sixes.size() >= 4) {
					categoryMatch = true;
				}
			}
			else if(category == YAHTZEE) {
				if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size() == 5) {
					categoryMatch = true;
				}
			}
			else if(category == FULL_HOUSE) {
				if(ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3 || sixes.size() == 3) {
					if(ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2) {
						categoryMatch = true;
					}
				}
			}	
			else if(category == LARGE_STRAIGHT) { 
				if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
					categoryMatch = true;
				}
				else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1) {
					categoryMatch = true;
				}
			}
			else if(category == SMALL_STRAIGHT) { 
				if(ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1) {
					categoryMatch = true;
				}
				else if(twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1) {
					categoryMatch = true;
				}
				else if(threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1 && sixes.size() >= 1) {
					categoryMatch = true;
				}
			}
		}
		return categoryMatch;
	}

    
    
	
}
