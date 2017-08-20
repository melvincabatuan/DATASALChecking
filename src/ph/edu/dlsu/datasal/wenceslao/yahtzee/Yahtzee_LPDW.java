package ph.edu.dlsu.datasal.wenceslao.yahtzee;

import acm.graphics.*;
import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.awt.*;

import ph.edu.dlsu.datasal.wenceslao.mylinkedlist.*;

// Author: Luis Paolo D. Wenceslao

public class Yahtzee_LPDW extends GraphicsProgram implements YahtzeeConstants {
    
    private int nPlayers; //number of players
    private String[] playerNames; //an array of Player names
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator(); //random number generator
    private int[] dieResults = new int [N_DICE]; //stores the most recently rolled dice numbers
    private int[][] categoryScores; //stores the score for each category for each player
    private int category; //selected category
    private int[][] selectedCategories; //stores the already selected categories
    
    public void run() {
        // Sets up players //
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
        for (int i = 1; i <= nPlayers; i++) {
            playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
        }
        // Initializes display //
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
        if(category == ONES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 1)
                    score += 1;
            }
        }
        else if(category == TWOS) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 2)
                    score += 2;
            }
        }
        else if(category == THREES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 3)
                    score += 3;
            }
        }
        else if(category == FOURS) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 4)
                    score += 4;
            }
        }
        else if(category == FIVES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 5)
                    score += 5;
            }
        }
        else if(category == SIXES) {
            for(int i = 0; i < N_DICE; i++) {
                if(dieResults[i] == 6)
                    score += 6;
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
        display.printMessage("Congratulations, " + playerNames[winningPlayerNumber] + ", you're the winner with a total score of " + winningScore + "! Click to Continue.");
        Leaderboard();
    }
    
    
    // LEADERBOARD //
    private MyLinkedList<Integer> LinkScore = new MyLinkedList<Integer>();
    
    private void Leaderboard(){
        sort();
        waitForClick();
        removeAll();
        GLabel HS = new GLabel("LEADERBOARD: TOP 3");
        HS.setColor(Color.white);
        add(HS, (getWidth() - HS.getWidth())/2, 100);
        for(int a=0; a<3; a++){
            if(names[a]!=null){
                GLabel HS1 = new GLabel((a+1) + ". " + names[a] + " - " + LinkScore.get(a+1));
                HS1.setColor(Color.white);
                add(HS1, (getWidth()-HS1.getWidth())/2 , 150+50*a);
            }
        }
        GLabel again =  new  GLabel( "Click anywhere to Start new Game" ); 
        again.setColor(Color.white);
        add(again, (getWidth()-again.getWidth())/2 , getHeight()- 30 ); 
        waitForClick();
        removeAll();
        run();
    }
    
    public int []  highScores =  new int [ 10 ];
    public  String[]  names  =  new  String[ 10 ];
    private void  sort(){
        for (int c= 0; c<nPlayers; c++){  
            highScores[c+3] = categoryScores[c][TOTAL];
            names[c+3] = playerNames[c];
        }
        for ( int  a= 1 ;a<= 10 ;a++){  
            for ( int  b =  0 ; b< 10 -a;b++)
                if ( highScores [b] <  highScores [b+ 1 ]) {  
                    int  temp1 =  highScores [b];  
                    highScores [b] =  highScores [b+ 1 ];  
                    highScores [b+ 1 ] = temp1;
                    String temp2 =  names [b];
                    names [b] =  names [b+ 1 ];  
                    names [b+ 1 ] = temp2;
                }
            }
        for ( int  d= 1 ; d<= 3 ; d++){
            LinkScore .add(d, highScores [d- 1 ]);
        }
    }
    // END OF LEADERBOARD //

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
                switch (dice[i]) {
                    case 1:
                        ones.add(1);
                        break;
                    case 2:
                        twos.add(1);
                        break;
                    case 3:
                        threes.add(1);
                        break;
                    case 4:
                        fours.add(1);
                        break;
                    case 5:
                        fives.add(1);
                        break;
                    case 6:
                        sixes.add(1);
                        break;
                    default:
                        break;
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
    
//    public static void main(String[] args) {
//        new Yahtzee_LPDW().start(args);
//    }
}