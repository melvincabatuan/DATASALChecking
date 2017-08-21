package ph.edu.dlsu.velasco.yahtzee;

/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 * Author: NEIL OLIVER VELASCO
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import ph.edu.dlsu.velasco.myarraylist.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    
    private boolean recursiveShield = true;
    
    public void run() {
        setupPlayers();
        if(recursiveShield){
            initDisplay();
            recursiveShield = false;
        }
	//playGame();
        AutoPlay();
    }
	
/**
 * Prompts the user for information about the number of players, then sets up the
 * players array and number of players.
 */
    private void setupPlayers() {
	nPlayers = chooseNumberOfPlayers();	
		
	/* Set up the players array by reading names for each player. */
	playerNames = new String[nPlayers];
	for (int i = 0; i < nPlayers; i++) {
            /* IODialog is a class that allows us to prompt the user for information as a
             * series of dialog boxes.  We will use this here to read player names.
             */
            IODialog dialog = getDialog();
            playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
	}
    }
	
/**
 * Prompts the user for a number of players in this game, reprompting until the user
 * enters a valid number.
 * 
 * @return The number of players in this game.
 */
        
    private int chooseNumberOfPlayers() {
	/* See setupPlayers() for more details on how IODialog works. */
	IODialog dialog = getDialog();
		
	while (true) {
            /* Prompt the user for a number of players. */
            int result = dialog.readInt("Enter number of players");
			
            /* If the result is valid, return it. */
            if (result > 0 && result <= MAX_PLAYERS)
		return result;
			
            dialog.println("Please enter a valid number of players.");
		}
    }
	
/**
 * Sets up the YahtzeeDisplay associated with this game.
 */
    private void initDisplay() {
	display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

/**
 * Actually plays a game of Yahtzee.  This is where you should begin writing your
 * implementation.
 */
    
    private void CheatCode(){
        switch(cheatCount){
            case 13:{ //Yahtzee
                for(int i = 0; i<5; i++){
                    dieResults[i]=6;
                }
                break;
            }
            case 2:{ //FourOfAKind
                for(int i=0; i<4; i++){
                    dieResults[i]=6;
                }
                dieResults[4]=5;
                break;
            }
            case 3:{ //FullHouse
                for(int i=0; i<3; i++){
                    dieResults[i]=6;
                }
                for(int i=3; i<5; i++){
                    dieResults[i]=5;
                }
                break;
            }
            case 4:{ //LargeStraight
                for(int i=0; i<5; i++){
                    dieResults[i]=i+1;
                }
                break;
            }
            case 5:{ //SmallStraight
                for(int i=0; i<4; i++){
                    dieResults[i]=i+1;
                }
                dieResults[4]=1;
                break;
            }
            case 6:{ //Ones
                for(int i=0; i<5; i++){
                    dieResults[i]=1;
                }
                break;
            }
            case 7:{ //Twos
                for(int i=0; i<5; i++){
                    dieResults[i]=2;
                }
                break;
            }
            case 8:{ //Threes
                for(int i=0; i<5; i++){
                    dieResults[i]=3;
                }
                break;
            }
            case 9:{ //Fours
                for(int i=0; i<5; i++){
                    dieResults[i]=4;
                }
                break;
            }
            case 10:{ //Fives
                for(int i=0; i<5; i++){
                    dieResults[i]=5;
                }
                break;
            }
            case 11:{ //Sixes
                for(int i=0; i<5; i++){
                    dieResults[i]=6;
                }
                break;
            }
            case 12:{ //ThreeOfAKind
                for(int i=0; i<3; i++){
                    dieResults[i]=6;
                }
                for(int i=3; i<5; i++){
                    dieResults[i]=i;
                }
                break;
            }
            case 1:{ //chance
                for(int i=0; i<5; i++){
                    int dieRoll = rgen.nextInt(1,6);
                    dieResults[i] = dieRoll;
                }
                break;
            }
        }
    }
    
    
    private void AutoPlay(){
        scores = new int[N_CATEGORIES][nPlayers];
        marked = new int[N_CATEGORIES][nPlayers];
        
        for (int i = 0; i < N_SCORING_CATEGORIES; i++){
            for (int j = 0; j < nPlayers; j++){
                firstrollDiceAuto(j);
                //RerollSecond();
                //RerollThird();
                calculateTotalScores(j);
            }
            cheatCount++;
        }
	showWinner();
    }
    
    private void firstrollDiceAuto(int pNum){
        CheatCode();
        /*
        for(int i = 0; i < N_DICE; i++) {
            int dieRoll = rgen.nextInt(1,6);
            dieResults[i] = dieRoll;
	}
        */
	display.printMessage(playerNames[pNum] + "'s turn! Click the " + "\"Roll Dice\" " + "button to roll the dice.");
	display.displayDice(dieResults);
        selectCategoryAuto(pNum);
    }
    
    private int SelectCategoryAI(int[] dieRes, int pNum){
        int score=0;
        int high=0;
        int index=0;
        for(int i=0; i<6; i++){
            if(markScored(pNum,i)){
                if(findCategory (dieRes, i)){
                    score = calculateScore (i);
                    if(high < score){
                        high = score;
                        index = i;
                    }
                }
            }
        }
        for(int i=8; i<15; i++){
            if(markScored(pNum,i)){
                if(findCategory (dieRes, i)){
                    score = calculateScore (i);
                    if(high < score){
                        high = score;
                        index = i;
                    }
                }
            }
        }
        return index;
    }
    
    private void selectCategoryAuto(int pNum) {
        while(true) {
            display.printMessage("Select a category");
            category = SelectCategoryAI(dieResults, pNum);

            if (markScored(pNum, category)){
                if (findCategory(dieResults, category)){
                    scores[category][pNum] = calculateScore(category);
                    display.updateScorecard(category, pNum, scores[category][pNum]);
                    calculateTotalScores(pNum);
                    display.updateScorecard(UPPER_SCORE, pNum, scores[UPPER_SCORE][pNum]);
                    display.updateScorecard(LOWER_SCORE, pNum, scores[LOWER_SCORE][pNum]);
                    display.updateScorecard(TOTAL, pNum, scores[TOTAL][pNum]);
                    display.updateScorecard(UPPER_BONUS, pNum, scores[UPPER_BONUS][pNum]);
                }
                else {
                    display.updateScorecard(category, pNum, 0);
                }
                marked[category][pNum] = 1;
                break;
            }
	display.printMessage("You already scored this category. Try another.");
        }	
    }
    
    
    private void playGame() {
        scores = new int[N_CATEGORIES][nPlayers];
        
        marked = new int[N_CATEGORIES][nPlayers];
        
        for (int i = 0; i < N_SCORING_CATEGORIES; i++){
            for (int j = 0; j < nPlayers; j++){
                firstrollDice(j);
                //RerollSecond();
                //RerollThird();
                selectCategory(j);
                calculateTotalScores(j);
            }
            cheatCount++;
        }
	showWinner();
     }
        
    private void firstrollDice(int pNum){
        CheatCode();
        /*
        for(int i = 0; i < N_DICE; i++) {
            int dieRoll = rgen.nextInt(1,6);
            dieResults[i] = dieRoll;
	}
        */
	display.printMessage(playerNames[pNum] + "'s turn! Click the " + "\"Roll Dice\" " + "button to roll the dice.");
	display.waitForPlayerToClickRoll(pNum);
	display.displayDice(dieResults);
    }
        
    private void RerollSecond() {
        
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
    
    private void RerollThird() {
        
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
        
    /*private void selectCategoryOpt(int pNum) {
        while(true) {
            display.printMessage("Select a category for this roll.");
            category = display.waitForPlayerToSelectCategory();
            if(selectedCategories[pNum][category] == 0) {
                calculateScore(pNum);
            }
        }	
    }*/
        
    private void selectCategory(int pNum) {
        while(true) {
            display.printMessage("Select a category");
            category = display.waitForPlayerToSelectCategory();
            if (markScored(pNum, category)){
                if (findCategory(dieResults, category)){
                    scores[category][pNum] = calculateScore(category);
                    display.updateScorecard(category, pNum, scores[category][pNum]);
                    calculateTotalScores(pNum);
                    display.updateScorecard(UPPER_SCORE, pNum, scores[UPPER_SCORE][pNum]);
                    display.updateScorecard(LOWER_SCORE, pNum, scores[LOWER_SCORE][pNum]);
                    display.updateScorecard(TOTAL, pNum, scores[TOTAL][pNum]);
                    display.updateScorecard(UPPER_BONUS, pNum, scores[UPPER_BONUS][pNum]);
                } 
                else {
                    display.updateScorecard(category, pNum, 0);
                }
                marked[category][pNum] = 1;
                break;
            }
	display.printMessage("You already scored this category. Try another.");
        }	
    }
    
    
    private boolean markScored(int pNum, int category){
        return (marked[category][pNum] == 0);
    }
        
    
    private int calculateScore(int category){
	int result = 0;

	switch (category){
            case ONES: return diceValue(1);
            case TWOS: return diceValue(2);
            case THREES: return diceValue(3);
            case FOURS: return diceValue(4);
            case FIVES: return diceValue(5);
            case SIXES: return diceValue(6);

            case THREE_OF_A_KIND: case FOUR_OF_A_KIND: case CHANCE:
		for (int i = 0; i < N_DICE; i++){
                    result += dieResults[i];
		}
            return result;

            case FULL_HOUSE: return 25;
            case SMALL_STRAIGHT: return 30;
            case LARGE_STRAIGHT: return 40;
            case YAHTZEE: return 50;
        }
        return 0;
    }
    
    
    private int diceValue(int dice){
		int result = 0;
		for (int i = 0; i < N_DICE; i++){
			if (dieResults[i] == dice){
				result += dice;
			}
		}
		return result;
    }
    
    
    private void calculateTotalScores(int pNum){
        int upperScore = 0;
	int lowerScore = 0;
	int totalScore = 0;
        int upperBonus = 0;
	for(int i = ONES; i <= SIXES; i++) {
            upperScore += scores[i][pNum];
	}
        if(upperScore>=35){
            scores[UPPER_BONUS][pNum] = 35;
            upperBonus = 35;
        }
	for(int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
            lowerScore += scores[i][pNum];
	}
	totalScore = upperScore + lowerScore + upperBonus; 
	scores[UPPER_SCORE][pNum] = upperScore; 
	scores[LOWER_SCORE][pNum] = lowerScore;
	scores[TOTAL][pNum] = totalScore; 
    }
    
   
    private boolean findCategory (int[] dieRes, int category){
        boolean checker = false;
            
        if (category >= ONES && category <= SIXES || category == CHANCE){
            checker = true;
        }
            
        else{
                
            MyList<Integer> ones = new MyList<>();
            MyList<Integer> twos = new MyList<>();
            MyList<Integer> threes = new MyList<>();
            MyList<Integer> fours = new MyList<>();
            MyList<Integer> fives = new MyList<>();
            MyList<Integer> sixes = new MyList<>();
            int j=1; int k=1; int x=1; int y=1; int z=1; int f=1;
            
            ones.createList();
            twos.createList();
            threes.createList();
            fours.createList();
            fives.createList();
            sixes.createList();
            
            for (int i=0; i < N_DICE; i++){
                switch(dieRes[i]){
                    case 1:{
                        ones.add(j,1);
                        j++;
                        break;
                    }
                    case 2:{
                        twos.add(k,1);
                        k++;
                        break;
                    }
                    case 3:{
                        threes.add(x,1);
                        x++;
                        break;
                    }
                    case 4:{
                        fours.add(y,1);
                        y++;
                        break;
                    }
                    case 5:{
                        fives.add(z,1);
                        z++;
                        break;
                    }
                    case 6:{
                        sixes.add(f,1);
                        f++;
                        break;
                    }
                }//end switch
            }//end for
                
            switch(category){
                case THREE_OF_A_KIND:{
                    if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3 || sixes.size() >= 3) {
                        checker = true;
                    }
                    break;
                }
                case FOUR_OF_A_KIND:{
                    if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4 || sixes.size() >= 4) {
			checker = true;
                    }
                    break;
                }
                case YAHTZEE:{
                    if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size() == 5) {
			checker = true;
                    }
                    break;
                }
                case FULL_HOUSE:{
                    if(ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3 || sixes.size() == 3) {
			if(ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2) {
                            checker = true;
			}
                    }
                }
                case LARGE_STRAIGHT:{
                    if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
			checker = true;
                    }
                    else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1) {
			checker = true;
                    }
                }
                case SMALL_STRAIGHT:{
                    if(ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1) {
                        checker = true;
                    }
                    else if(twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1) {
			checker = true;
                    }
                    else if(threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1 && sixes.size() >= 1) {
			checker = true;
                    }
                }
                
            }//end switch
        }//end else
            
        return checker;
    }
    
    private void showWinner(){
        HighScore.createList();
        
	int winner = 0;
	int winnerScore = 0;

	for (int player = 0; player < nPlayers; player++){
            int totalScore = scores[TOTAL][player];
            if (totalScore > winnerScore){
		winnerScore = totalScore;
		winner = player;
            }
        }
	display.printMessage("Congratulations, " + playerNames[winner] + ", you're the winner with a total score of " + winnerScore);
        
        //HighScore.add(WinnerCount," "+playerNames[winner]+" - "+ winnerScore);
        WinnerCount++;
        
        displayWinners();
    }
     
    private void displayWinners(){
        //String Highers = HighScore.get(1);
        //display.printMessage("High Score:" + Highers);
    }
        
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
        private int[] dieResults = new int[N_DICE];
        private RandomGenerator rgen = RandomGenerator.getInstance();
        private int category=-1;
        private int[][] scores;
        private int[][] marked;
        private int cheatCount=1;
        private int[] saveIndex = new int[TOTAL];
        private MyList<String> HighScore = new MyList<>();
        private int WinnerCount=1;
        
        public static void main(String[] args){
            new Yahtzee().start(args);
        }
}


