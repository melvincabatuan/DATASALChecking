/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.rivera.yahtzee;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.Color;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rivera
 */
public class Yahtzee extends GraphicsProgram implements YahtzeeConstants{
    private MyLinkedList<String> names = new MyLinkedList();
    private MyLinkedList<Integer> scores = new MyLinkedList();
        private boolean[][] scored ;
        private boolean valid_category = false;
        private int category,turn_score,total_score;
        
        private int[] yahtzee_counter;
        private int[] dice = new int[5];
        private int[] freq = new int[6];
        private int[] playerScores,upper_score,lower_scores;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
        private RandomGenerator rgen = RandomGenerator.getInstance(); 
        private String winner;
        IODialog dialog;
        private int ai_turn_score=0,max_score;
        boolean moveIsNotMadeYet=true;
    public GImage cup = new GImage("cup.jpg");
    public GImage one;
    public GImage[] dicePicture = new GImage[5];
    double[] dicex = new double[5];//--5
        double[] dicey = new double[5];//-10
        double[] mult=new double[5];//0.97
    private GCanvas canvas=getGCanvas();
   double dx =1,dy=-1;
public boolean[] selected= {false,false,false,false,false};
    public Yahtzee(){
        
    }
    public void init(){
        setSize(1000, 1000);
        for(int i=0;i<5;i++){
            one = new GImage("y1.jpg");
            dicePicture[i]=one;
        }
                removeAll();
		setupPlayers();
		initDisplay();
//                addKeyListeners();
        try {
            playGame();
        } catch (IOException ex) {
            Logger.getLogger(Yahtzee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void aiTurn(int player){
        int best_category=0;
        display.printMessage(playerNames[player]+"'s turn.");
        pause(500);
        display.printMessage(playerNames[player]+"is rolling the dice.");
        roll();
        //waitForClick();
        display.printMessage(playerNames[player]+"is thinking.");
        pause(1000);
//      //waitForClick();
        evaluate(player);
//        //pause(2000);
        display.printMessage(playerNames[player]+"is thinking.");
        pause(500);
        evaluate(player);
        //pause(2000);
        int best_categ = aiSubmit(player);
        display.updateScorecard(best_categ, player, max_score);
        playerScores[player]+=max_score;
        if(best_categ>7){
            lower_scores[player]+=max_score;
        }else{
            upper_score[player]+=max_score;
        }
        display.updateScorecard(6, player, upper_score[player]);
        display.updateScorecard(15, player, lower_scores[player]);
        display.updateScorecard(16, player,playerScores[player]);
        scored[player][best_categ]=true;
        ai_turn_score=0;
        max_score=0;
        pause(5000);
        
    }
    public int aiSubmit(int player){
        max_score=0;
        ai_turn_score=0;
        int category_best = 0;
        
        while(scored[player][category_best]){
            category_best++;
        }
         
        initArrays(freq);
        checkFreq(1);
        
        //ones to sixes
        for(int i=0;i<6;i++){
            
            if(!scored[player][i]){
                
                 ai_turn_score=freq[i]*(i+1);
                
                if(ai_turn_score>max_score){
                    max_score=ai_turn_score;
                    category_best=i;
                }
            }
            ai_turn_score=0;
        }
        
        if(!scored[player][12]){
            if(largeStraight()){
                ai_turn_score=40;
            }else{
                ai_turn_score=0;
            }
            if(ai_turn_score>=max_score){
                max_score=ai_turn_score;
                category_best=12;
            }
        }
        
        if(!scored[player][11]){
            if(smallStraight()){
                ai_turn_score=30;
            }else{
                ai_turn_score=0;
            }
            if(ai_turn_score>=max_score){
                max_score=ai_turn_score;
                category_best=11;
            }
        }
        
        if(!scored[player][10]){
            if(full_house()){
                ai_turn_score=25;
            }else{
                ai_turn_score=0;
            }
            if(ai_turn_score>=max_score){
                max_score=ai_turn_score;
                category_best=10;
            }
        }
        ///four of a kind
        if(!scored[player][9]){
            if(checkFreq(4)){
                max_score=totalDice();
                category_best=9;
            }else{
                ai_turn_score=0;
            }
            if(ai_turn_score>=max_score){
                max_score=ai_turn_score;
                category_best = 9;
            }
        }
        
        
        //three of a kind 
        if(!scored[player][8]){
            if(checkFreq(3)){
                ai_turn_score=totalDice();
                category_best=8;
            }else{
                ai_turn_score=0;
            }
            if(ai_turn_score>=max_score){
                max_score=ai_turn_score;
                category_best = 8;
            }
        }
        
        //yatzhee
        if(!scored[player][13]){
            if(dice[0]==dice[1] && dice[1]==dice[2] && dice[2]==dice[3] && dice[3]==dice[4]){
                ai_turn_score=50;
                System.out.println("yahtzee");
            }else{
                ai_turn_score=0;
            }
            if(ai_turn_score>=max_score){
                max_score=ai_turn_score;
                category_best=13;
            }
        }
        //chance
        if(!scored[player][14]){
            ai_turn_score=totalDice();
            if(ai_turn_score>20){
                max_score=ai_turn_score;
                category_best=14;
            }
            
        }
        
        
        
        
        return category_best;
    }
    public void evaluate(int player){
        System.out.println("Evaluating");
        //yatzhee
        if(checkFreq(4)){
            System.out.println("4");

            int dice_number=0,dice_value=0;
        
            for(int i=0;i<6;i++){
                if(freq[i]==1){
                    dice_value=i;
                }
            }
            for(int i=0;i<5;i++){
                if(dice_value==dice[i]){
                    dice_number=i;
                }
            }
            dice[dice_number]=rgen.nextInt(1,6);
             display.displayDice(dice);
            display.printMessage(playerNames[player]+" has rerolled dice "+(dice_number+1));
           
            pause(1000);
         }
        else if(checkFreq(3)){
            int dice_repeated=0;
            int d1=-1,d2=-1;
            for(int i=0;i<6;i++){
                if(freq[i]==3){
                dice_repeated=i+1;
                }
            }
            for(int i=0;i<5;i++){
                if(dice[i]!=dice_repeated && d1==-1){
                    d1=i;
                }else if(dice[i]!=dice_repeated && d1!=-1){
                    d2=i;
                }
            }
            dice[d1]=rgen.nextInt(1, 6);
            dice[d2]=rgen.nextInt(1, 6);
            display.printMessage(playerNames[player]+" has rerolled dice "+ (d1+1) + " and " +(d2+1));
            display.displayDice(dice);
            pause(1000);
         
        }
    }
    public void animate(){
        canvas.add(cup);
        cup.setLocation(getWidth()-cup.getWidth()+50, getHeight()-cup.getHeight()+50);
        one.sendToFront();
        one.setVisible(true);
        cup.setVisible(true);
        for(int i = 0;i<4;i++){
            for(int j=0;j<20;j++){
                cup.move(dx, dy);
                pause(5);
            }
            dx=-dx;dy=-dy;
        }
        //setting images, adding
        for(int i=0;i<5;i++){
            dicePicture[i].setImage("y"+dice[i]+".jpg");
            dicePicture[i].setLocation(getWidth()-one.getWidth(), getHeight()-one.getHeight());
            
            dicePicture[i].setVisible(true);
            dicePicture[i].sendToFront();
            add(dicePicture[i]);
            dicex[i]=i-8;
            dicey[i]=i-10;
            mult[i]=0.97;
        }
         cup.sendToFront();
        for(int i=0;i<100;i++){
            for(int j=0;j<5;j++){
            dicePicture[j].move(dicex[j], dicey[j]);
            dicey[j]*=mult[j];
            if(dicey[j]>-1){
                break;
            }
            pause(1);
            }
        }
    }
    private void roll(){
       for(int i = 0; i < 5;i++){
            dice[i]=rgen.nextInt(1, 6);
            
        }
       display.displayDice(dice);
//        animate();
        
        
    }
     private void reroll() {
         for(int i = 0;i<4;i++){
            for(int j=0;j<20;j++){
                cup.move(dx, dy);
                pause(5);
            }
            dx=-dx;dy=-dy;
            
        }
        for(int i = 0; i < 5;i++){
            if(display.isDieSelected(i)){
                selected[i]=display.isDieSelected(i);
                dice[i]=rgen.nextInt(1, 6);
                dicePicture[i].setImage(dice[i]+".jpg");
            }
        }
        
        display.displayDice(dice);
        }
    
    
    
        
	public void run() {
            
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
     * @param e
	 */
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyChar() == 'j'){
                display.printMessage("Cheat active!");
                for(int i=0;i<5;i++){
                    dice[i]=dialog.readInt("Enter dice "+(i+1)+" value");
                }
                
                display.displayDice(dice);
            }
            if(e.getKeyChar() == 'q'){
                animate();
                display.printMessage("animated");
            }
            
        }
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
		 dialog = getDialog();
		int result;
		while (true) {
			/* Prompt the user for a number of players. */
			 result = dialog.readInt("Enter number of players");
                         yahtzee_counter = new int[result];
			playerScores = new int[result];
                        upper_score = new int[result];
                        lower_scores = new int[result];
                        scored = new boolean[result][17];
                        initArrays(playerScores);
                        initArrays(upper_score);
                        initArrays(freq);
                        initArrays(dice);
                        initArrays(lower_scores);
                        initArrays(scored);
                        initArrays(yahtzee_counter);
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
	private void playGame() throws IOException {
		/* You fill this in! */
                //for(int i=0;i<N_SCORING_CATEGORIES;i++){
                    for(int i=0;i<13;i++){
                    for(int j = 0; j < nPlayers;j++){
                        if(playerNames[j].contains("ai")){
                            aiTurn(j);
                        }else{
                        display.printMessage(playerNames[j]+"'s turn! Click \"Roll Dice\" button to roll the dice.");
                        display.waitForPlayerToClickRoll(j);
                        roll();
                        display.displayDice(dice);
                        display.printMessage("You can still reroll any dice 2 more times.");
                        display.waitForPlayerToSelectDice();
                        reroll();
                        display.printMessage("You can still reroll any dice 1 more time.");
                        display.waitForPlayerToSelectDice();
                        reroll();
                        display.printMessage("Click the category you want the dice to be scored.");
                        category = display.waitForPlayerToSelectCategory();
                        
                        while(moveIsNotMadeYet){
                        if(checkIfScored(category,j)){
                            display.printMessage("Please select another category. The category have been chose already.");
                            category = display.waitForPlayerToSelectCategory();
                            
                        }else{
                        selectCategory(category,j);
                        moveIsNotMadeYet=false;
                        }}
                        moveIsNotMadeYet=true;
                        }
                    }
                }
                upperBonus();
                
                winner = result();
                display.printMessage("The winner is "+ winner + "! Congratulations!");
                
                saveScore();
                
                
	}
		
	
      

    

   

    private void selectCategory(int category,int nPlayer) {
        switch(category){
            case 0:
                for(int i = 0; i < 5;i++){
                    if(dice[i]==1){
                        turn_score++;
                                
                    }
                }
                upper_score[nPlayer]+=turn_score;
                break;
            case 1: 
                for(int i = 0; i < 5;i++){
                    if(dice[i]==2){
                        turn_score+=2;
                                
                    }
                }
                upper_score[nPlayer]+=turn_score;
                break;
            case 2: 
                for(int i = 0; i < 5;i++){
                    if(dice[i]==3){
                        turn_score+=3;
                                
                    }
                }
                upper_score[nPlayer]+=turn_score;
                break;
            case 3: 
                for(int i = 0; i < 5;i++){
                    if(dice[i]==4){
                        turn_score+=4;
                                
                    }
                }
                upper_score[nPlayer]+=turn_score;
                break;
            case 4: 
                for(int i = 0; i < 5;i++){
                    if(dice[i]==5){
                        turn_score+=5;
                                
                    }
                }
                upper_score[nPlayer]+=turn_score;
                break;
            case 5: 
                for(int i = 0; i < 5;i++){
                    if(dice[i]==6){
                        turn_score+=6;
                                
                    }
                }
                upper_score[nPlayer]+=turn_score;
                break;
            case 6:
            case 7: category=display.waitForPlayerToSelectCategory();break;
            case 8: if(checkFreq(3)){
                addDice();
            }else{
                turn_score=0;
                        
            }
            break;
            case 9: if(checkFreq(4)){
                addDice();
            }else{
                turn_score=0;
                        
            }
            break;
            case 10: 
                if(full_house()){
                turn_score=25;
            }else{
                turn_score=0;
                        
            }
                break;
            case 11: 
                
                if(smallStraight()){
                    turn_score=30;
                }else{
                    turn_score=0;
                            
                }
                break;
            case 12:
                
                if(largeStraight()){
                    turn_score=40;
                }else{
                    turn_score=0;
                            
                }
                break;
            case 13:if(checkFreq(5)){
                turn_score=50;
                if(yahtzee_counter[nPlayer]>0){
                    lower_scores[nPlayer]+=100;
                    
                    display.updateScorecard(13, nPlayer, lower_scores[nPlayer]);
                    display.printMessage("Another yatzhee! +100 to total score");
                    pause(1000);
                }
                yahtzee_counter[nPlayer]++;
            }else{
                turn_score=0;
                        
            }
            break;
            case 14: 
                addDice();
                break;
            case 15: 
                break;
            case 16:
                
        }
        playerScores[nPlayer]+=turn_score;
        if(upper_score[nPlayer]!=0){
           display.updateScorecard(6, nPlayer,upper_score[nPlayer]); 
        }
        if(category>7){
            lower_scores[nPlayer]+=turn_score;
        }
        display.updateScorecard(15, nPlayer, lower_scores[nPlayer]);
        display.updateScorecard(category, nPlayer,turn_score);
        display.updateScorecard(16, nPlayer,playerScores[nPlayer]);
        scored[nPlayer][category]=true;
        turn_score=0;
        initArrays(freq);
    }

    private boolean checkFreq(int count) {
        initArrays(freq);
        
        for(int i = 0; i < 5;i++){
            freq[dice[i]-1]++;
        }
        
        for(int i = 0; i < 6;i++){
            if (freq[i]>=count){
                return true;
            }
        }
        
        
            return false;
            
    }
    
    private void initArrays(int[] array){
        for(int i = 0; i < array.length;i++){
            array[i]=0;
        }
    }

    private int addDice() {
        
        for(int i = 0; i < 5;i++){
            turn_score+=dice[i];
        }
        return turn_score;
        }
    private int totalDice(){
        return dice[0]+dice[1]+dice[2]+dice[3]+dice[4];
    }

    private boolean fourZeroes() {
        int zero_count=0;
        for(int i = 0; i < 6;i++){
                if(freq[i]==0){
                    zero_count++;
                }
        }
                if(zero_count==4){
            return true;
        }else{
            return false;
        }
                }

    private String result() {
        int highest=0;
        for(int i=0; i<nPlayers;i++){
            if(playerScores[i]>playerScores[highest]){
                highest=i;
                
            }
        }
            return playerNames[highest];
    
    }

    private boolean largeStraight() {
        checkFreq(2);
        int counter=0;
        for(int i=0;i<6;i++){
            if(freq[i]>0){
                counter++;
            }else{
                counter=0;
            }
        }
        
        if(counter==5){
            return true;
        }else{
            return false;
        }
        
        }

    private boolean smallStraight() {
        checkFreq(2);
        int counter=0;
        for(int i=0;i<6;i++){
            if(freq[i]>0){
                counter++;
            }else if(counter==4){
            break;}else{
                counter=0;
            }
        }
        
        if(counter>=4){
            return true;
        }else{
            return false;
        }
    }

    private void upperBonus() {
        for(int i = 0; i < nPlayers ; i++){
            if(upper_score[i]>=63){
                display.updateScorecard(7, i, 35);
                playerScores[i]+=35;
            }else{
                display.updateScorecard(7, i, 0);
            }
        }
    }
    
    private void saveScore() throws IOException {
        for(int i=0;i<nPlayers;i++){
            int j=1;
            if(scores.isEmpty()){
                names.add(1, playerNames[i]);
                scores.add(1, playerScores[i]);
            }else{
                while(playerScores[j]<scores.get(j)){
                    j++;
                }
                scores.add(j, playerScores[i]);
                names.add(j, playerNames[i]);
            }
            
            
        }
        displayScore(scores, names,getGCanvas());
        waitForClick();
        playagain();
        }
    private void playagain() throws IOException{
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Yahtzee", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            
            run();
            
            
         
          
        }
        else {
          
           
           System.exit(0);
        }
    }
    private void displayScore(MyLinkedList score, MyLinkedList names, GCanvas canvas) {
        
       int offset = 30;
        canvas.removeAll();
        
                GLabel heading = new GLabel("Highscores");
                heading.setFont("Century Schoolbook L-30-italic");
                heading.setColor(Color.RED);
                
                canvas.add(heading, getWidth()/2-heading.getWidth()/2 ,HEIGHT/2+ 30);
                
		for(int i = 1; i <= score.size(); i++){
                   GLabel item = new GLabel(""+scores.get(i));
                   GLabel item2 = new GLabel(""+ i +". " + names.get(i));
                   item.setFont("Century Schoolbook L-25");
                   item.setColor(Color.RED);
                   item2.setFont("Century Schoolbook L-25");
                   item2.setColor(Color.BLACK);
                   canvas.add(item2, (getWidth() - item.getWidth()-item2.getWidth()-30)/2, getWidth()/10 + i * (item.getHeight() + 3)+offset); 
                
                   canvas.add(item, item2.getX()+item2.getWidth()+30, getWidth()/10 + i * (item.getHeight() + 3)+offset); 
                
                   }
    }    
    
    private boolean checkIfScored(int category, int j){
        return scored[j][category];
    }
    

    private void initArrays(boolean[][] scored) {
        for(int i = 0;i< scored.length;i++){
            for(int j = 0 ; j<17;j++){
            scored[i][j]=false;
            }
        }
    }
    
    
    
    public static void main(String[] args){
        new Yahtzee().start(args);
    }

    private boolean full_house() {
        checkFreq(1);
        boolean twice=false, thrice=false;
        for(int i=0;i<6;i++){
            if (freq[i]==2){
                twice = true;
            }
            if (freq[i]==3){
                thrice = true;
            }
        }
        return twice && thrice;
    }
}
