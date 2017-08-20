package ph.edu.dlsu.datasal.lee.yahtzee;
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
 
import ph.edu.dlsu.datasal.lee.myarraylist.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
        int[] up=new int[4];
        int[] low=new int[4];
        int[][] list=new int[4][16];
        int x;
    	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
        private int[][] Dice= new int[4][5];
        private int[] score=new int[4];
        private int[] fscore= new int[4];
       

        
        public void run() {
		setupPlayers();
		initDisplay();
		playGame();
                highscore();
	}
        
         public static void main(String[] args) {
            new Yahtzee().start(args);
        }
    
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));          
		}
	}
        
        private int chooseNumberOfPlayers() {
		IODialog dialog = getDialog();
		while (true) {
			int result = dialog.readInt("Enter number of players");
			if (result > 0 && result <= MAX_PLAYERS) return result;
                        else dialog.println("Please enter a valid number of players.");
		}
	}
	
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	private void playGame() {
            while(true){
                for(int round=0;round<13;round++){
                    for(int i=0;i<nPlayers;i++){
                        for(int j=0;j<3;j++){
                            display.printMessage(playerNames[i] + "'s turn, Roll the Dice");
                            if(j==0){
                                display.waitForPlayerToClickRoll(i);
                                for(int k=0;k<=N_DICE-1;k++){
                                    Dice[i][k] = rgen.nextInt(1,6);   
                                }
                            }
                            
                            else{
                                display.waitForPlayerToSelectDice();  
                                for(int k=0;k<=N_DICE-1;k++){
                                    if(display.isDieSelected(k)){
                                        Dice[i][k] = rgen.nextInt(1,6);   
                                    }
                                }
                            }
                            display.displayDice(Dice[i]);
                        }   
                        boolean s=true;
                        while(s==true){
                            display.printMessage(playerNames[i] + "'s turn, Select a Category");
                            x =display.waitForPlayerToSelectCategory();
                            if(list[i][x]==0){
                                list[i][x]=1;
                                s=false;
                            }
                        }
                        int total= score(x, Dice[i]);
                        display.updateScorecard(x, i, total);
                        if(x<6){
                            up[i]= up[i]+total;
                            display.updateScorecard(6, i, up[i]);
                            if(up[i]>=63){
                                display.updateScorecard(7, i, 35);
                            }
                        }
                        else if(x>7){
                            low[i]= low[i] +total;
                            display.updateScorecard(15, i, low[i]);
                        }
                        if(up[i]>=63){
                                display.updateScorecard(16, i, up[i]+low[i]+35);
                                fscore[i]=up[i]+low[i]+35;
                        }
                        else{ 
                            display.updateScorecard(16, i, up[i]+low[i]);
                            fscore[i]=up[i]+low[i];
                        }
                    }                
                }  
                highscore();            
            }
        }
        
        public int score(int x, int[] dice){
            int total=0, prev=0, cur=0, count=0, next=0;
            if(x<6){
               for(int i=0;i<5;i++){
                    if(x==0){
                        if(dice[i]==1) total+=1; 
                    }
                    if(x==1){
                        if(dice[i]==2) total+=2; 
                    }
                    if(x==2){
                        if(dice[i]==3) total+=3; 
                    }
                    if(x==3){
                        if(dice[i]==4) total+=4; 
                    }
                    if(x==4){
                        if(dice[i]==5) total+=5; 
                    }
                    if(x==5){
                        if(dice[i]==6) total+=6; 
                    }
               }
            }

            for(int j=0;j<5;j++){
                cur=0;
                for(int k=0;k<5;k++){
                    if(dice[j]==dice[k]) cur++;   
                }
                if(prev<cur){
                   next=prev;
                   prev=cur;
                }
                else if(prev!=cur) next=cur;
            }
            
            if(x==8){
                if(prev>2){
                    for(int l=0;l<5;l++){
                        total=total+dice[l];
                    }
                }
            }
            
            if(x==9){
                if(prev>3){
                    for(int l=0;l<5;l++){
                        total=total+dice[l];
                    }
                }
            }
            
            if(x==14){
                for(int l=0;l<5;l++){
                    total=total+dice[l];
                }
            } 
            if(x==10){
                if((prev==3&&next==2)||(prev==2&&next==3)) total=25;
            }   
            for(int j=0;j<5;j++){
                for(int k=j+1;k<5;k++){
                    if((dice[j]==dice[k])) dice[k]=10;
                    }
                }
            for(int j=0;j<5;j++){
                for(int k=j+1;k<5;k++){
                    if((dice[j]==dice[k]+1)||(dice[j]==dice[k]-1)){
                        count++;
                    }
                }
            }
            if(x==11&&count>2) total=30;
            if(x==12&&count>3) total=40;
            if(x==13&&prev>4) total=50;
           
            return total;
        }

        MyList<Integer> HS  = new MyList<>();  
        MyList<String> NL  = new MyList<>();  
        public void highscore(){
            HS.createList();
            NL.createList();
            String text;
            for(int i=0;i<nPlayers;i++){
                HS.add(1,fscore[i]);   
                NL.add(1,playerNames[i]);
                sort();
            }
            IODialog dialog = getDialog();
            text=("Highscore\n");
            for(int j=1;j<=HS.size();j++){
                text= text + ((j)+"  "+NL.get(j)+"  "+HS.get(j)+"\n"); 
            }
            dialog.println(""+text);
        }
        
        
        private void sort(){
            int temp;
            String nametemp;
            for(int i=HS.size();i>=1;i--){
                for(int j=1;j<i;j++){
                    temp=HS.get(j);
                    nametemp=NL.get(j);
                    if(HS.get(j)<HS.get(j+1)){
                        HS.remove(j);
                        NL.remove(j);
                        HS.add(j+1, temp);
                        NL.add(j+1, nametemp);
                    }
                }
            }
        }
         private RandomGenerator rgen = RandomGenerator.getInstance();
}