package ph.edu.dlsu.datasal.santos.yahtzee;

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator();
    private int[] dieResults = new int [N_DICE];
    private int[][] categoryScores;
    private int category; 
    private int[][] selectedCategories; 
    private MyList<String> leaderboard = new MyList<String>();
    
    public void run() {
        leaderboard.createList();
        for(int i=0; i>-1; i++){
                IODialog dialog = getDialog();
                nPlayers = dialog.readInt("Enter number of players");
                while(true) {
                    if(nPlayers <= MAX_PLAYERS) 
                        break;
                    nPlayers = dialog.readInt("You can only enter up to " + MAX_PLAYERS +" number of players. Enter number of players");
                }
                playerNames = new String[nPlayers];
                categoryScores =  new int [nPlayers + 1][N_CATEGORIES+1];
                selectedCategories = new int[nPlayers+1][N_CATEGORIES+1];
                for (int j = 1; j <= nPlayers; j++) {
                    playerNames[j - 1] = dialog.readLine("Enter name for player " + j);
                }
                display = new YahtzeeDisplay(getGCanvas(), playerNames);
                playGame();
            }
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

    private void selectCategory(int playerNumber) {
        display.printMessage("Select a category for this roll");
        while(true) {
            category = display.waitForPlayerToSelectCategory();
            if(selectedCategories[playerNumber][category] == 0) {
                calculateCategoryScore(playerNumber);
                break;
            }
            display.printMessage("You have already selected this category. Please select another one.");
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

    private void setCategoryScore(int playerNumber, int category) {
        int score = 0; 
        if(category == ONES){
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 1) {
                        score += 1;
                }
            }
        }
        else if(category == TWOS) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 2) {
                        score += 2;
                }
            }
        }
        else if(category == THREES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 3) {
                        score += 3;
                }
            }
        }
        else if(category == FOURS) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 4) {
                        score += 4;
                }
            }
        }
        else if(category == FIVES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 5) {
                        score += 5;
                }
            }
        }
        else if(category == SIXES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 6) {
                        score += 6;
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
    }

    private boolean checkCategory(int[] dice, int category) {
        boolean categoryMatch = false;
        if(category >= ONES && category <= SIXES || category == CHANCE) {
            categoryMatch = true;
        }
        else {
            ArrayList <Integer> ones = new ArrayList<Integer>();  
            ArrayList <Integer> twos = new ArrayList<Integer>(); 
            ArrayList <Integer> threes = new ArrayList<Integer>(); 
            ArrayList <Integer> fours = new ArrayList<Integer>(); 
            ArrayList <Integer> fives = new ArrayList<Integer>(); 
            ArrayList <Integer> sixes = new ArrayList<Integer>();

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
    
    public static void main(String[] args) {
        new Yahtzee().start(args);
    }
}