package ph.edu.dlsu.datasal.DELA_PENA.yahtzee;

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.io.*;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyArrayList.*;


public class YahtzeeDP extends GraphicsProgram implements YahtzeeConstants{
    	
	/* Private instance variables */
        RandomGenerator rgen = RandomGenerator.getInstance();
	private int nPlayers=0;
        private int nComputers=0;
	private String[] playerNames;
	private YahtzeeDisplay display;
        private int dice[] = new int[N_DICE];
        private int PlayerScorecard [][] = new int[MAX_PLAYERS][N_CATEGORIES];
        private boolean alreadyPicked[][] = new boolean[MAX_PLAYERS][N_CATEGORIES]; 
        private boolean cheatmode = false;
        private boolean newHScore = false;
        private MyArrayList<String> HScores = new MyArrayList<>();
        private MyArrayList<Integer> NEW_HScores = new MyArrayList<>();
        
        private final String ClrHScore_Key = "noonebetter";
        private final String CheatMode_Key = "iwannawin";
        private final String HSCORE_FILENAME = ".\\src\\YahtzeeHScores.txt";
        
        private final YahtzeeAI Computers[] = new YahtzeeAI[4];
        
        private final int AIpause = 0;
        
        
        
        public void init(){
            HScores.createList();
            NEW_HScores.createList();
            readHScores();
            //resetAI();
            //printHScores();
            for(int i=0;i<MAX_PLAYERS;i++){
                for(int j=0 ; j<N_CATEGORIES ; j++){
                    PlayerScorecard[i][j]=0;
                }
            }
            for(int i=0;i<MAX_PLAYERS;i++){
                for(int j=0 ; j<N_CATEGORIES ; j++){
                    alreadyPicked[i][j]=false;
                }
            }
            for(int i=0; i<4; i++){
                Computers[i] = new YahtzeeAI();
            }
            resetAI();
        }
        
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
                IODialog dialog = getDialog();
		/* Set up the players array by reading names for each player. */
                if(nPlayers<4){
                    do{
                        nComputers = dialog.readInt("Enter number of Computers");
                    }while(nComputers<0 || nComputers>4-nPlayers);
                }
		playerNames = new String[nPlayers+nComputers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
                        if(playerNames[i].equals(CheatMode_Key)){
                            cheatmode=true;
                            dialog.println("Cheat Mode Enabled");
                        }
                        else if(playerNames[i].equals(ClrHScore_Key))
                            clearHScores();
		}
                for(int i=nPlayers; i<(nPlayers+nComputers); i++){
                        playerNames[i] = "Comp "+(i-nPlayers+1);
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
			if (result >= 0 && result <= MAX_PLAYERS)
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
	private void playGame() {
            //this loop controls the 13 rounds of the game
            newHScore=false;
            //validateCategoryScore();
            for(int round = 1; round<=13 ;round++){
                //controls the turns of each player
                for(int playernum=0; playernum<nPlayers; playernum++){
                    display.printMessage(playerNames[playernum] + "'s turn! Click 'Roll Dice'");
                    display.waitForPlayerToClickRoll(playernum);
                    for(int d=0; d<N_DICE; d++){ //roll all dice
                        dice[d] = rgen.nextInt(1, 6);
                    }
                    display.displayDice(dice);
                    if(cheatmode){
                        CheatDiceVal();
                        display.displayDice(dice);
                    }
                    else{
                        int t=2;
                        while(t!=0){
                            display.printMessage("Select Dice to Reroll");
                            display.waitForPlayerToSelectDice();
                            for(int d=0; d<N_DICE; d++){
                                if(display.isDieSelected(d)){
                                    dice[d] = rgen.nextInt(1, 6);
                                }
                            }
                            display.displayDice(dice);
                            t--;
                        }
                    }
                    display.printMessage("Pick a category");
                    int category;
                    int score;
                    //do{
                        category = display.waitForPlayerToSelectCategory();
                        score = CategoryScore(category);
                        display.printMessage("You already picked that category...");
                    //} 
                    //while(alreadyPicked[playernum][category]);
                    
                    alreadyPicked[playernum][category] = true;
                    display.updateScorecard(category, playernum, score);
                    PlayerScorecard[playernum][category]=score;
                    
                    
                    
                    
                }//end of player's turn
                for(int computernum=0; computernum<nComputers; computernum++){
                    boolean reRoll[] = new boolean[5];
                    display.printMessage(playerNames[nPlayers+computernum] + "'s turn! Click 'Roll Dice'");
                    pause(AIpause*2);
                    for(int d=0; d<N_DICE; d++){ //roll all dice
                        dice[d] = rgen.nextInt(1, 6);
                    }
                    int t=2;
                    while(t!=0){
                        reRoll = Computers[computernum].DicetoReroll(dice);
                        for(int d=0; d<N_DICE; d++){
                            if(reRoll[d]){
                                dice[d] = rgen.nextInt(1, 6);
                            }
                        }
                        display.displayDice(dice);
                        pause(AIpause);
                        t--;
                    }
                    pause(AIpause);
                    int category = Computers[computernum].PickCategory(dice);
                    int score = CategoryScore(category);
                    alreadyPicked[nPlayers+computernum][category] = true;
                    display.updateScorecard(category, nPlayers+computernum, score);
                    PlayerScorecard[nPlayers+computernum][category]=score;
                    
                }
            }//end of game
            finalizeScore();
            updateHScores();
            printHScores();
            
            
	}
        
        public void CheatDiceVal(){
            IODialog dialog = getDialog();
            String diceval="";
            do{
                diceval = dialog.readLine("Enter Dice values\nex. 1-2-3-4-5");
            }while(!validDiceval(diceval));
            
            for(int i=0; i<N_DICE; i++){
                dice[i] = Integer.parseInt(diceval.substring((2*i), (2*i)+1));
            }
        }
        
        public boolean validDiceval(String diceval){
            boolean ok = true;
            for(int i=0; i<diceval.length(); i++){
                if(i%2==0){
                    char num=diceval.charAt(i);
                    ok=false;
                    if(num!='1' || num!='2' || num!='3' || num!='4' || num!='5' || num!='6'){
                        ok=true;
                        break;
                    }
                }
                else{
                    if(diceval.charAt(i)!='-'){
                        ok=false;
                        break;
                    }
                }
            }
            if(diceval.length()!=9 )
                ok=false;
            return ok;
        }
        
        public int CategoryScore(int category){
            int score=0;
            boolean[] alreadycheckeddice = new boolean[N_DICE];
            for(int i=0; i<N_DICE; i++){
                alreadycheckeddice[i] = false;
            }
            switch(category){
                case 0: //ONES  
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==1)
                            score += 1;
                    }
                    break;
                
                case 1: //TWOS
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==2)
                            score += 2;
                    }
                    break;
                
                case 2: //THREES
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==3)
                            score += 3;
                    }
                    break;
                
                case 3: //FOURS
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==4)
                            score += 4;
                    }
                    break;
                    
                case 4: //FIVES
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==5)
                            score += 5;
                    }
                    break;
                
                case 5: //SIXES
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==6)
                            score += 6;
                    }
                    break;
                    
                case 8: //Three of a kind
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d)>=3){
                            score += (d*3);
                        }
                    }
                    break;
                    
                case 9: //four of a kind
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d)>=4){
                            score += (d*4);
                        }
                    }
                    break;
                    
                case 10: //FULL HOUSE
                    boolean triple = false;
                    boolean duo = false;
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d)==3){
                            triple=true;
                        }
                        if(MatchingDice(d)==2){
                            duo=true;
                        }
                    }
                    if(triple && duo)
                        score=FULL_HOUSE_SCORE;
                    break;
                    
                case 11:
                    int sstraight=0;
                    for(int i=1; i<=3;i++){ //start dice
                        for(int j=i; j<i+4;j++){ //next in sequence
                            for(int m=0;m<N_DICE;m++){ //checking the dice
                                if(dice[m]==j && !alreadycheckeddice[m]){
                                    sstraight++;
                                    alreadycheckeddice[m]=true;
                                    break;
                                }
                            }
                        }
                        if(sstraight==4){
                            score = SMALL_STRAIGHT_SCORE;
                            break;
                        }
                        for(int m=0; m<N_DICE; m++){
                            alreadycheckeddice[m] = false;
                        }
                        sstraight=0;
                    }
                    break;
                    
                case 12:
                    int fstraight=0;
                    for(int i=1; i<=2;i++){
                        for(int j=i; j<i+5;j++){
                            for(int m=0;m<N_DICE;m++){ //checking the dice
                                if(dice[m]==j){
                                    fstraight++;
                                    alreadycheckeddice[m]=true;
                                    break;
                                }
                            }
                        }
                        if(fstraight==5){
                            score = LARGE_STRAIGHT_SCORE;
                            break;
                        }
                        for(int m=0; m<N_DICE; m++){
                            alreadycheckeddice[m] = false;
                        }
                        fstraight=0;
                    }
                    break;
                    
                case 13:
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d)>=5){
                            score = YAHTZEEEE;
                            break;
                        }
                    }
                    break;
                    
                case 14: //CHANCE
                    for(int d=0; d<N_DICE; d++){
                        score += dice[d];
                    }
                    break;
            }
            
            return score;
        }
        
        public int MatchingDice(int diceValue){
            int matches = 0;
            for(int d=0; d<N_DICE; d++){
                if(dice[d]==diceValue)
                    matches++;
            }
            return matches;
        }
        
        public void finalizeScore(){
            for(int playernum=0; playernum<nPlayers+nComputers; playernum++){
                for(int category=ONES; category<=SIXES ; category++){
                    PlayerScorecard[playernum][UPPER_SCORE] += PlayerScorecard[playernum][category];
                }
                if(PlayerScorecard[playernum][UPPER_SCORE]>=63)
                    PlayerScorecard[playernum][UPPER_BONUS] = 35;
                display.updateScorecard(UPPER_SCORE, playernum, PlayerScorecard[playernum][UPPER_SCORE]);
                display.updateScorecard(UPPER_BONUS, playernum, PlayerScorecard[playernum][UPPER_BONUS]);
                
                for(int category=THREE_OF_A_KIND; category<=CHANCE ; category++){
                    PlayerScorecard[playernum][LOWER_SCORE] += PlayerScorecard[playernum][category];
                }
                PlayerScorecard[playernum][TOTAL] = PlayerScorecard[playernum][LOWER_SCORE] + PlayerScorecard[playernum][UPPER_SCORE] + PlayerScorecard[playernum][UPPER_BONUS];
                display.updateScorecard(LOWER_SCORE, playernum, PlayerScorecard[playernum][LOWER_SCORE]);
                display.updateScorecard(TOTAL, playernum, PlayerScorecard[playernum][TOTAL]);
            }
        }
        
        public void readHScores(){
            String HSCORE_FILENAME = ".\\src\\YahtzeeHScores.txt";
            String CurrentLine = "";
            //WORDS.createList();
            try (BufferedReader br = new BufferedReader(new FileReader(HSCORE_FILENAME))) {	
                
		for (int i=0; i<10; i++) {
                    if((CurrentLine = br.readLine()) == null || CurrentLine.equals(""))
                        break;
                    HScores.add(HScores.size()+1, CurrentLine);
                    
		}

            } catch (IOException e) {
		e.printStackTrace();
            }
        }
        public void updateHScores(){
            
            for(int playernum=0; playernum<nPlayers; playernum++){
                if(!HScores.isEmpty()){
                    for(int j=1; j<=HScores.size(); j++){
                        int substr = 0;
                        int High = 0;
                        
                        while(!HScores.get(j).substring(substr, substr+3).equals(" - ")){
                            substr++;
                        }
                        High = Integer.parseInt(HScores.get(j).substring(0, substr));
                        
                        if(PlayerScorecard[playernum][TOTAL]>High){
                            if(HScores.size()==10)
                                HScores.remove(10);
                            println(HScores.get(j));
                            HScores.add(j, PlayerScorecard[playernum][TOTAL] + " - " + playerNames[playernum]);
                            newHScore = true;
                            NEW_HScores.add(NEW_HScores.size()+1, j);
                            //println(PlayerScorecard[playernum][TOTAL] + ">" + High + j);
                            //println(HScores.get(j));
                            //println(HScores.get(j+1)+"\n***");
                            break;
                        }
                        else if(j==HScores.size() && HScores.size()<10){
                            HScores.add(j+1, PlayerScorecard[playernum][TOTAL] + " - " + playerNames[playernum]);
                            NEW_HScores.add(NEW_HScores.size()+1, j+1);
                            newHScore = true;
                            break;
                        }
                        
                    }
                    
                }
                else{
                    HScores.add(1, (PlayerScorecard[playernum][TOTAL] + " - " + playerNames[playernum]));
                    NEW_HScores.add(NEW_HScores.size()+1, 1);
                    newHScore = true;
                }
            }
            
            try (BufferedWriter br = new BufferedWriter(new FileWriter(HSCORE_FILENAME))) {	
                br.write("");
		for (int i=1; i<=HScores.size(); i++) {
                    br.write(HScores.get(i)+"\n");
		}

            } catch (IOException e) {
		e.printStackTrace();
            }
        }
	public void printHScores(){
            String printHscores = "======HIGHSCORES======\n\n";
            IODialog dialog = getDialog();
            if(newHScore){
                printHscores += "*** NEW HIGHSCORE ***\n\n";
            }
            for(int i=1; i<=HScores.size(); i++){
                if(newHScore && !NEW_HScores.isEmpty()){
                    if(NEW_HScores.get(1)==i){
                        printHscores += "*";
                        NEW_HScores.remove(1);
                    }
                }
                printHscores += HScores.get(i) + "\n";
            }
            printHscores += "\n======================";
            dialog.println(printHscores);
        }
        
        public void clearHScores(){
            for(int i=HScores.size(); i>0; i--){
                HScores.remove(i);
            }
            try (BufferedWriter br = new BufferedWriter(new FileWriter(HSCORE_FILENAME))) {	
                br.write("");
		for (int i=1; i<=HScores.size(); i++) {
                    br.write(HScores.get(i)+"\n");
		}

            } catch (IOException e) {
		e.printStackTrace();
            }
            IODialog dialog = getDialog();
            dialog.println("High Scores Cleard");
        }
        
        public void clrNScores(){
            for(int i=NEW_HScores.size(); i>0; i--){
                NEW_HScores.remove(i);
            }
        }
        
        public void resetAI(){
            for(int i=1; i<2; i++){
                Computers[i].reset();
            }
        }
        
        public void validateCategoryScore(){
            int check = SMALL_STRAIGHT;
            for(int a=1; a<=6; a++){
                dice[0] = a; 
                for(int b=1; b<=6; b++){
                    dice[1] = b;
                    for(int c=1; c<=6; c++){
                        dice[2] = c;
                        for(int d=1; d<=6; d++){
                            dice[3] = d;
                            for(int e=1; e<=6; e++){
                                dice[4] = e;
                                println(a+""+b+""+c+""+d+""+e +" = "+CategoryScore(check));
                            }
                        }
                    }
                }
            }
        }
}
