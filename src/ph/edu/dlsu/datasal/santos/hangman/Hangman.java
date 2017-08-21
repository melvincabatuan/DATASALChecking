package ph.edu.dlsu.datasal.santos.hangman;

/**
 *
 * @author Cellix
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Hangman extends ConsoleProgram {
    
    private HangmanLexicon lexicon; 
    private String word; 
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private static final int N_GUESS = 8;
    private int guesses;
    private String wordString = "";
    private char userGuess;
    private HangmanCanvas canvas;
    private int score;
    
    private MyLinkedList<String> leaderboard = new MyLinkedList<String>();

    public void init() { 
        canvas = new HangmanCanvas();
        canvas.reset();
        add(canvas); 
	} 
    
    public void run() {
        leaderboard.createList();
    	lexicon = new HangmanLexicon();
        println("Welcome to Hangman!");
        for(int i=0; i>-1;i++){
            canvas.reset();
            score=0;
            game();
        }
    	}
    
    private void game (){
        guesses = N_GUESS;
        int wordCount = lexicon.getWordCount() - 1;
    	word = lexicon.getWord(rgen.nextInt(0, wordCount));
    	/* Main function */
    	playRound();
    	
    	if (guesses > 0) {
    		gameWin();
    	} 
        else {
    		gameLose();
    	}
    	
	
    }
    
    private void playRound() {    	
    	wordString = "";
    	for (int i = 0; i < word.length(); i++) {
    		wordString = wordString + "-";
    	}	
	canvas.displayWord(wordString);   	
    	while (guesses > 0 && !isWordComplete()) {
    		println("\nThe word looks like this: " + wordString);
    		println(guesses + " attempts left.");
    		String userInput = readLine("Your guess: ");
    		if (!isError(userInput)) {
			userGuess = userInput.charAt(0);
                	userGuess = Character.toUpperCase(userGuess);
    			if (isInWord(userGuess)) {
    				println("You got a correct letter");
                                wordString = buildWordString();
    				canvas.displayWord(wordString);
    				score++;
    			} 
                        else {
    				println("There are no " + userGuess + "'s in the word.");
    				guesses--;
    				canvas.noteIncorrectGuess(userGuess);
    			}
    			
    		} 
                else {
    			println("Invalid input! Try again.");
    		}
    	}
    }
    
    
    private boolean isWordComplete() {
    	int index = wordString.indexOf('-');
    	if (index == -1) {
    		return true;
    	} 
        else {
    		return false;
    	}
    }
    
    private String buildWordString() {
	int startCount = 0;
    	int index = -1;
		while (startCount == (index + 1)) {	
                    String wordsubstr = word.substring(startCount, word.length());
                    index = wordsubstr.indexOf(userGuess);
                    if (index != -1) {
                            index = index + startCount;
                            wordString = wordString.substring(0, index)
                            + userGuess
                            + wordString.substring(index+1, word.length());
                            startCount = index + 1;
                    }
		}
    	return wordString;
    }
    
    private boolean isError(String userInput) {
    	// Check if user input is valid (e.g. single character)
    	if (userInput.length() > 0) {
    		return false;
    	} else {
    		return true;	
    	}
    }
    
    private boolean isInWord(char userGuess) {
    	// Check if the character is in the word
    	for (int i = 0; i < word.length(); i++) {
    		if (userGuess == word.charAt(i)) return true;
    	}    	
    	return false;
    }
    
    private void gameWin() {
    	println("You win!");
        game();
    }
    
    private void gameLose() {
    	println("You lose!");
        checkscores();
    }
    
    private void showscores(){
            println("\n\nHigh score list");
            for(int i=1 ;i<=leaderboard.size();i++){
                println(leaderboard.get(i));
            }
        }
    
    public static void main(String[] args) {
        new Hangman().start(args);  
        }
  
    private int first=0;
    private int second=0;
    private int third=0;
    private int counter=0;
    
    private void checkscores(){
            if(score>=first&&score!=0){
                println("Congratulations! You got the High Score!");
                if(counter<3){
                    counter++;
                }
                String nam = JOptionPane.showInputDialog("Enter name: ");
                if(leaderboard.size()==3){
                    leaderboard.remove(3); 
                }
                leaderboard.add(1, nam +"   :  "+ score); 
                sortscores();
                showscores();
            }
            else if(score>=second&&score!=0){
                println("You made it to second place!");
                if(counter<3){
                    counter++;
                }
                String nam = JOptionPane.showInputDialog("Enter player name: ");
                if(leaderboard.size()==3){
                    leaderboard.remove(3); 
                }
                leaderboard.add(2, nam +"   :  "+ score); 
                sortscores();
                showscores();
            }
            else if(score>=third&&score!=0){
                println("You made it to third place!");
                if(counter<3){
                    counter++;
                }
                String nam = JOptionPane.showInputDialog("Enter player name: ");
                if(leaderboard.size()==3){
                    leaderboard.remove(3); 
                }
                leaderboard.add(3, nam +"   :  "+ score); 
                sortscores();
                showscores();
            }
            else{
                removeAll();
                println("You did not make it to the top 3 better luck next time");
                showscores();
            }
        }    
    
    
    
    private void sortscores(){
            if(score>=first){
                third=second;
                second=first;
                first=score;
            }
            else if(score>=second){
                third=second;
                second=score;
            }
            else if(score>=third){
                third=score;
            }
        }
    
}
