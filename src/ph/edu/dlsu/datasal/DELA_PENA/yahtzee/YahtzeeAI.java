package ph.edu.dlsu.datasal.DELA_PENA.yahtzee;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class YahtzeeAI extends ConsoleProgram implements YahtzeeConstants{
    
    private float[] possiblescore = new float[N_CATEGORIES];
    private boolean[] alreadypicked = new boolean[N_CATEGORIES];
    private boolean[][] reRoll = new boolean[N_CATEGORIES][N_DICE];
    private boolean RetRoll[] = new boolean[N_DICE];
    
    public int PickCategory(int dice[]){
        resetScores();
        for(int i=ONES; i<=CHANCE; i++){
            possiblescore[i] = CategoryScore(i, dice);
        }
        possiblescore[UPPER_SCORE] = -1;
        possiblescore[UPPER_BONUS] = -1;
        possiblescore[LOWER_SCORE] = -1;
        possiblescore[TOTAL] = -1;
        int CategoryHigh=0;
        while(alreadypicked[CategoryHigh]){
            CategoryHigh++;
        }
        for(int i=ONES; i<=CHANCE; i++){
            if(possiblescore[i]>possiblescore[CategoryHigh] && !alreadypicked[i])
                CategoryHigh=i;
        }
        alreadypicked[CategoryHigh]=true;
        return CategoryHigh;
    }
    public int CategoryScore(int category, int dice[]){
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
                        if(MatchingDice(d, dice)>=3){
                            score += (d*3);
                        }
                    }
                    break;
                    
                case 9: //four of a kind
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d, dice)>=4){
                            score += (d*4);
                        }
                    }
                    break;
                    
                case 10: //FULL HOUSE
                    boolean triple = false;
                    boolean duo = false;
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d, dice)==3){
                            triple=true;
                        }
                        if(MatchingDice(d, dice)==2){
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
                                if(dice[m]==j && !alreadycheckeddice[m]){
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
                        if(MatchingDice(d, dice)>=5){
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
    
    public boolean[] DicetoReroll(int dice[]){
        ComputePossibleCategories(dice);
        int CategoryHigh=0;
        while(alreadypicked[CategoryHigh]){
            CategoryHigh++;
        }
        for(int i=14; i>=0; i--){
            if(possiblescore[i]>possiblescore[CategoryHigh])
                CategoryHigh=i;
        }
        for(int i=0; i<N_DICE; i++){
            RetRoll[i]=reRoll[CategoryHigh][i];
        }
        return RetRoll;
    }
    
    public void init(){
        reset();
    }
    
    
    public void ComputePossibleCategories(int dice[]){
        
        resetScores();
        for(int category=0; category<N_CATEGORIES; category++){
            
            int missdice=0;
            switch(category){
                case 0:
                    missdice=0;
                    for(int i=0; i<N_DICE; i++){
                        if(dice[i] != 1){
                            missdice++;
                            reRoll[category][i]=true;
                        }
                    }
                    possiblescore[category] = 5/(1+missdice);
                    break;
                case 1:
                    missdice=0;
                    for(int i=0; i<N_DICE; i++){
                        if(dice[i] != 2){
                            missdice++;
                            reRoll[category][i]=true;
                        }
                    }
                    possiblescore[category] = (2*5)/(1+missdice);
                    break;
                case 2:
                    missdice=0;
                    for(int i=0; i<N_DICE; i++){
                        if(dice[i] != 3){
                            missdice++;
                            reRoll[category][i]=true;
                        }
                    }
                    possiblescore[category] = (3*5)/(1+missdice);
                    break;
                case 3:
                    missdice=0;
                    for(int i=0; i<N_DICE; i++){
                        if(dice[i] != 4){
                            missdice++;
                            reRoll[category][i]=true;
                        }
                    }
                    possiblescore[category] = (4*5)/(1+missdice);
                    break;
                case 4:
                    missdice=0;
                    for(int i=0; i<N_DICE; i++){
                        if(dice[i] != 5){
                            missdice++;
                            reRoll[category][i]=true;
                        }
                    }
                    possiblescore[category] = (5*5)/(1+missdice);
                    break;
                case 5:
                    missdice=0;
                    for(int i=0; i<N_DICE; i++){
                        if(dice[i] != 6){
                            missdice++;
                            reRoll[category][i]=true;
                        }
                    }
                    possiblescore[category] = (6*5)/(1+missdice);
                    break;
                case 8: //Three of a kind
                    int threehigh=0;
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d, dice)>MatchingDice(threehigh, dice)){
                            threehigh=d;
                        }
                    }
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]!=threehigh){
                            reRoll[category][d]=true;
                            missdice++;
                        }
                    }
                    if(missdice>3)
                        missdice=1;
                    possiblescore[category] = (threehigh*3)/(1+missdice);
                    break;
                
                case 9: //four of a kind
                    int fourhigh=0;
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d, dice)>MatchingDice(fourhigh, dice)){
                            fourhigh=d;
                        }
                    }
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]!=fourhigh){
                            reRoll[category][d]=true;
                            missdice++;
                        }
                    }
                    if(missdice>2)
                        missdice=1;
                    possiblescore[category] = (fourhigh*4)/(1+missdice);
                    break;
                
                case 10: //FULL HOUSE
                    int trio=0;
                    int nTrio=0;
                    int duo=0;
                    int nDuo=0;
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d, dice)>MatchingDice(trio, dice)){
                            trio=d;
                            nTrio=MatchingDice(d, dice);
                        }
                    }
                    for(int d=1; d<=6; d++){
                        if(MatchingDice(d, dice)>MatchingDice(trio, dice) && d!=trio){
                            trio=d;
                            nDuo=MatchingDice(d, dice);
                        }
                    }
                    for(int d=0; d<N_DICE; d++){
                        if(dice[d]==trio && nTrio>3){
                            reRoll[category][d]=true;
                            nTrio--;
                        }
                        else if(dice[d]==duo && nDuo>2){
                            reRoll[category][d]=true;
                            nDuo--;
                        }
                        else{
                            reRoll[category][d] = true;
                        }
                    }
                    missdice = 5 - nTrio - nDuo;
                    possiblescore[category] = FULL_HOUSE_SCORE/(1+missdice);
                    break;
                    
                case 11: //small straight;
                    
                    break;
            }
            possiblescore[UPPER_SCORE] = -1;
            possiblescore[UPPER_BONUS] = -1;
            possiblescore[LOWER_SCORE] = -1;
            possiblescore[TOTAL] = -1;
        }
        
        
        
        
    }
    public void resetScores(){
        for(int i=0; i<N_CATEGORIES; i++){
            possiblescore[i] = 0;
        }
        possiblescore[UPPER_SCORE] = -1;
        possiblescore[UPPER_BONUS] = -1;
        possiblescore[LOWER_SCORE] = -1;
        possiblescore[TOTAL] = -1;
        for(int i=0; i<N_CATEGORIES; i++){
            for(int j=0; j<N_DICE; j++){
                reRoll[i][j] = false;
            }
        }
        for(int i=0; i<N_DICE; i++){
            RetRoll[i] = false;
        }
    }
    
    public void reset(){
        for(int i=0; i<N_CATEGORIES; i++){
            possiblescore[i] = 0;
        }
        possiblescore[UPPER_SCORE] = -1;
        possiblescore[UPPER_BONUS] = -1;
        possiblescore[LOWER_SCORE] = -1;
        possiblescore[TOTAL] = -1;
        for(int i=0; i<N_CATEGORIES; i++){
            alreadypicked[i] = false;
        }
        for(int i=0; i<N_CATEGORIES; i++){
            for(int j=0; j<N_DICE; j++){
                reRoll[i][j] = false;
            }
        }
        for(int i=0; i<N_DICE; i++){
            RetRoll[i] = false;
        }
    }
    private int MatchingDice(int diceValue, int dice[]){
            int matches = 0;
            for(int d=0; d<N_DICE; d++){
                if(dice[d]==diceValue)
                    matches++;
            }
            return matches;
        }
}
