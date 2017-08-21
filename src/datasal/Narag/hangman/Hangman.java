/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */
package datasal.Narag.hangman;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import javax.swing.JOptionPane;
import datasal.Narag.myLinkedList.MyLinkedList;

public class Hangman extends ConsoleProgram {
    
    //variables
    private MyLinkedList<String> scoreboard = new MyLinkedList<String>();
	 
    private HangmanLexicon lexicon; 
    
    private char input; //user's guess
    
    private String word; 
    
    private RandomGenerator rng = RandomGenerator.getInstance(); 
    
    private int guesses = remaining; //guesses remaining
    
    private String wordString = ""; 
    
    private HangmanCanvas canvas; 
    
    private static final int remaining = 8;
    
    public int score = 2000;
    
    public int score1, score2, score3, score4, score5, score6, score7, score8, score9, score10;
    
    public String scorename;
    
 
    
    public void init() { 
                scoreboard.createList();
                scoreboard.add(1, ""+score1);
                scoreboard.add(2, ""+score2);
                scoreboard.add(3, ""+score3);
                scoreboard.add(4, ""+score4);
                scoreboard.add(5, ""+score5);
                scoreboard.add(6, ""+score6);
                scoreboard.add(7, ""+score7);
                scoreboard.add(8, ""+score8);
                scoreboard.add(9, ""+score9);
                scoreboard.add(10, ""+score10);
		 canvas = new HangmanCanvas();
		 canvas.reset();
                 
                 lexicon = new HangmanLexicon();
		 add(canvas); 
                 println("H A N G M A N");
                 
    	//loop
                
	} 
        
	
    public void run() {
	play();
                }
    
    private void play() {
        
        while (guesses > 0 && !isWordComplete()) {
            
        int wordCount = lexicon.getWordCount() - 1;
    	word = lexicon.getWord(rng.nextInt(0, wordCount));
    	
        
        wordString = "";
    	for (int i = 0; i < word.length(); i++) {
    		wordString = wordString + "-";
    	}	

		canvas.displayWord(wordString);
    		println("The word now looks like this: " + wordString);
    		println("You have " + guesses + " guesses left.");
    		String userInput = readLine("Your guess: ");
    		
    		if (!isError(userInput)) {
			input = userInput.charAt(0);
                        input = Character.toUpperCase(input);
    			if (isInWord(input)) {
    				println("Word contains "+input);
    	    		wordString = buildWordString();
    				canvas.displayWord(wordString);
    				
    			} else {
    				println("There are no " + input + "'s in the word.");
                                score = score - 100;
    				guesses--;
    				canvas.noteIncorrectGuess(input);
    			}
    			
    		} else {
    			println("That is not a valid input. Try again.");
    		}
    	}
        
        if (guesses > 0 && isWordComplete()) {
                score = score + (guesses * 100);
    		win();
    	} else {
    		lose();
    	}
    	
    }
    
    
    private boolean isWordComplete() {
    	int index = wordString.indexOf('-');
    	if (index == -1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private String buildWordString() {
    	
		int startCount = 0;
                int index = -1;
    	
		while (startCount == (index + 1)) {
			
    		String wordsubstr = word.substring(startCount, word.length());
    		index = wordsubstr.indexOf(input);
    		if (index != -1) {
    			index = index + startCount;
    			wordString = wordString.substring(0, index)
    						+ input
    						+ wordString.substring(index+1, word.length());
    			startCount = index + 1;
    		}
		
		}
    	
    	return wordString;
    }
    
    private boolean isError(String userInput) {
    	if (userInput.length() > 0) {
    		return false;
    	} else {
    		return true;	
    	}
    	
    	
    }
    //checks if in word
    private boolean isInWord(char userGuess) {
    	for (int i = 0; i < word.length(); i++) {
    		if (userGuess == word.charAt(i)) return true;
    	}    	
    	return false;
    }
    
    private void win() {
    	println("Correct! The word is " + word + ". You have saved the man from certain doom!");
        println("\nYour score is " + score +" (+" + (guesses * 100) + " from remaining guesses)");
        scorename = JOptionPane.showInputDialog("Input name");
        scoring(score);
        println(scoreboard.get(1));
        println(scoreboard.get(2));
        println(scoreboard.get(3));
        println(scoreboard.get(4));
        println(scoreboard.get(5));
        println(scoreboard.get(6));
        println(scoreboard.get(7));
        println(scoreboard.get(8));
        println(scoreboard.get(9));
        println(scoreboard.get(10));
        println("\nPlay again? 1 - Yes;2 - No");
        int choice = readInt();
        playagain(choice);
    }
    
    private void lose() {
    	println("You have failed to guess the word, "+word+". The man is now a few inches taller.");
        println("");
        int choice;
        println("\nPlay again? 1 - Yes;2 - No");
        choice = readInt();
        playagain(choice);
    }
    
    private void scoring(int ns){
        if (ns>score1){
            scoreboard.add(1, scorename + " - " +ns);
            score1=ns;
        }
        
        else if (ns>score2){
            scoreboard.add(2, scorename + " - "+ns);
            score2=ns;
        }
        else if (ns>score3){
            scoreboard.add(3, scorename + " - "+ns);
            score3=ns;
        }
        else if (ns>score4){
            scoreboard.add(4, ns + " - "+ns);
            score4=ns;
        }
        else if (ns>score5){
            scoreboard.add(5, scorename + " - "+ns);
            score5=ns;
        }
        else if (ns>score6){
            scoreboard.add(6, scorename + " - "+ns);
            score6=ns;
        }
        else if (ns>score7){
            scoreboard.add(7, scorename + " - "+ns);
            score7=ns;
        }
        else if (ns>score8){
            scoreboard.add(8, scorename + " - " +ns);
            score8=ns;
        }
        else if (ns>score9){
            scoreboard.add(9, scorename + " - "+ns);
            score9=ns;
        }
        else if (ns>score10){
            scoreboard.add(10, scorename + " - "+ns);
            score10=ns;
        }
        else
        {
            
        }
        
    }
    private void playagain(int choice){
        switch (choice)
        {
            case 1:
                canvas.removeAll();
                canvas.reset();
                guesses = 8;
                run();
                break;
            case 2:
                exit();
                break;
        }
    }
    
    public void runHangman()
        {
            this.start();
        }

}	