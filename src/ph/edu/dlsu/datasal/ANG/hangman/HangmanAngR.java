/*
Ang, Ryan Jasper V.
11428422
2017-05-31
LBYCP12-EQ1
Experiment 03
*/

package ph.edu.dlsu.datasal.ang.hangman;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;



public class HangmanAngR extends ConsoleProgram {
    
    private HangmanCanvas canvas;

public void init() {
canvas = new HangmanCanvas();
add(canvas);

}
    
     public static void main(String args[]){
    new HangmanAngR().start();
    }

	private HangmanLexicon hangmanWords = new HangmanLexicon();
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** Tracks the number of guesses the player has */
	private int guessCounter = 8;
	
        int score = 0;
        //int livescore = score;
    
    public void run() {
    	canvas.drawSB1(score, hiscore);
        setUpGame();
    	canvas.reset();
        playGame();
        
	}
    
    /*Set up the game by displaying the welcome message, 
     *how many letters there are in the word, 
     *and how many guesses the user has
     */
    private void setUpGame() {
        //init();
    	println("Welcome to Hangman!");
    	println("The word now looks like this: " + hiddenWord);
    	println("You have " + guessCounter + " guesses left.");
    }
	
    //Generates a random word selected from the HangmanLexicon
    private String pickWord() {
    	int randomWord = rgen.nextInt(0, (hangmanWords.getWordCount() - 1)); 
    	String pickedWord = hangmanWords.getWord(randomWord);
    	return pickedWord;
    }
	
    //Shows how many letters there are in the word as part of game setup
	private String showNumberOfLetters() {
		String result = "";
		for(int i = 0; i< word.length(); i++) {
			result = result + "-";
		}
		return result;
		}
	String name;
        
        private void endGame(){
            //println("Enter your name: ");
          //  name = readLine("Enter your name: ");
           // MyLinkedList.add(hiscore, 1);
           checkScore();
            println("");
            println("Enter (1) to play again.");
            println("Enter (2) to exit.");
            int selection = readInt("Selection: ");
            switch (selection) {
                case 1: 
                    canvas.clear();
                    if(score>hiscore){
            hiscore =+ score;
            
        } 
                    score = 0;
                    word = pickWord();
                    hiddenWord = showNumberOfLetters();
                    guessCounter = 8;
                    canvas.displayWord(hiddenWord);
                    //FIND INCORRECT GUESSES LINK
                    //FIND INCORRECT GUESSES LINK
                    //FIND INCORRECT GUESSES LINK
                    str=null;
                    ch=0;
                    wrongList(ch);
                 
                    canvas.drawSB1(score, hiscore);
                    canvas.updateHS(score, hiscore);
        setUpGame();
    	canvas.reset();
        playGame();
        break;
        
                case 2: 
        exit();
                default: 
                    println("Out of Bounds.");
                    endGame();
            }
        }
        private int hiscore;
        
	private void playGame() {
/*            displayWord(pickedWord);
noteIncorrectGuess(String incorrectGuesses);*/

		while(guessCounter > 0) {
			String getChar = readLine("Your guess: ");
			while (true) {
				if(getChar.length() > 1) {
					getChar = readLine("You can only guess one letter at a time. Try again: ");
				}
				if(getChar.length() == 1) break;
			}
			ch = getChar.charAt(0);
			if(Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			letterCheck();
			if (hiddenWord.equals(word)) {
				println("You guessed the word: " + word);
				println("You win");
                                println("Word completion bonus: 50 points");
                                score += 50;
                                println("Final Score: " + score);
                                if(score>hiscore){
            hiscore =+ score;
            
        } 
                                canvas.updateHS(score, hiscore);
                                
                                /*
                                INSERT
                                HISCORE
                                TRIGGER
                                HERE
                                */
                                endGame();
				break;
			}
			println("The word now looks like this: " + hiddenWord);
			println("You have " + guessCounter + " guesses left.");
			
		}
		if (guessCounter == 0) {
			println("You're completely hung.");
			println("The word was:" + word);
			println("You lose.");
                        println("Final Score: " + score);
                        
                        endGame();
		}
                
	}
	
        
       
	//updates the hiddenWord if the character entered is correct 
	private void letterCheck() {
		//checks to see if the guessed letter is in the word
		if(word.indexOf(ch) == -1) {
			println("There are no " + ch + "'s in the word");
                        println("Score deducted by 5 points.");
                        println("\n");
			guessCounter--;
                        score -= 5;
                        
                        
                       
                        wrongList(ch);
                        
		}
		if(word.indexOf(ch) != -1) {
			println("The guess is correct.");
                        println("Score increased by 10 points.");
                        println("\n");
                        score += 10;
		}
		//goes through each of the letters in the word and checks if it matches the guessed letter, 
		//if it's a match, updates the hidden word to reveal the position of the guessed letter
		for(int i = 0; i < word.length(); i++) {
			if (ch == word.charAt(i)) {
				if (i > 0) {
					hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i + 1);
				}
				if(i == 0) {
					hiddenWord = ch + hiddenWord.substring(1);
				}
			}
		}
                /*if(score>hiscore){
            hiscore =+ score;
            
        } 
                */
                canvas.displayWord(hiddenWord);
                 canvas.updateHS(score, hiscore);
	}
    
	//This is the secret word
	private String word = pickWord();
	
	//This is the word being guessed
	private String hiddenWord = showNumberOfLetters();
	
	//This is the latest character entered by the user
	private char ch;
        
        private String str;
        
        private void wrongList(char letter){
   
           

            /*StringBuilder sb = new StringBuilder();
            sb.append(letter);
            str = sb.toString();*/
            
            str = str + Character.toString(letter);
            String charsToRemove = "null";
String stringToFilter = str;

String filtered = stripGarbage(stringToFilter);
               canvas.noteIncorrectGuess(filtered);
            
            
        }
      public String stripGarbage(String s) {  
    String good =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String result = "";
    for ( int i = 0; i < s.length(); i++ ) {
        if ( good.indexOf(s.charAt(i)) >= 0 )
           result += s.charAt(i);
        }
    return result;
    }
 
       private  int counter = 0;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    
    private ph.edu.dlsu.datasal.ang.mylinkedlist.MyLinkedList<String> highscore = new ph.edu.dlsu.datasal.ang.mylinkedlist.MyLinkedList<String>();

    private void showHighScore() {
        println("\nHIGHSCORES: ");
            for(int i = 1; i <= counter; i++){
                       println(""+ i +". " + highscore.get(i));
                    }
    }
    
    private void checkScore(){
        if(score >= first && score != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(1, player + " - " + score);
            sort();
            showHighScore();
        }
        
        else if(score >= second && score != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(2, player + " - " + score);
            sort();
            showHighScore();
        }
        
        else if(score >= third && score != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(3, player + " - " + score);
            sort();
            showHighScore();
        }
    }
    
    private void sort(){
        if(score >= first){
            third = second;
            second = first;
            first = score;
        }
            
        else if(score >= second){
            third = second;
            second = score;
        }
        
        else if(score >= third){
            third = score;
        }
    }
      
}
