/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */
package ph.edu.dlsu.datasal.parro.hangman;

import ph.edu.dlsu.datasal.parro.mylinkedlist.MyLinkedList;
import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import javax.swing.*;

public class Hangman extends ConsoleProgram {
	
	private final static int N_GUESS = 8; // number of parts to draw to complete hangman drawing
	
	// runs the program
	public void run() {
		welcomeMessage();
		setup();
		playGame();
	}
	
	// initialises the HangmanCanvas
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvasWidth = canvas.getWidth();
		canvasHeight = canvas.getHeight();
	}
	
	// sets up the game by choosing a word and creating its hidden version
	private void setup() {
		pickWord();
		drawHiddenWord();
		canvas.reset();
	}
	
	// simple welcome message
	private void welcomeMessage() {
		println("Welcome to Hangman. ");
	}
	
	// picks a word at random from the lexicon
	private void pickWord() {
		int index = rgen.nextInt(0, lexicon.getWordCount() - 1);
		word = lexicon.getWord(index);
	}
	
	// creates String of dashes same length as chosen word
	private void drawHiddenWord() {
		for (int i = 0; i < word.length(); i++) {
			hiddenWord = hiddenWord + "-";
		}
	}
	
	// Plays the game of hangman. Assumes word has been chosen in setup. 
	private void playGame() {
		while (N_WRONG < N_GUESS && win == false) {
			showProgress();
			playerGuess();
			checkGuess();
			if (correctGuess == true) {
				printCorrectGuess();
				revealInstances();
			}
			if (correctGuess == false) {
				printWrongGuess();
				addBodyPart();
			}
			turns++;
			if (hiddenWord.equals(word) == true) {
				win = true;
			}
		}
		if (win == false) {
			printLost();
		}
		if (win == true) {
			printWinner();
		}
		playAgain();
	}

	// replace dashes in hiddenWord with any instances of user's guess
	private void revealInstances() {
		String result = ""; // creates new String 'result'
		
		/* Checks all letters in word one by one (until i = word length)
		 * at each index position i, checks to see if char is the same as guess (a correct instance)
		 * if it is - the user's guess is added to a new String 'result'
		 * if it isn't, this method adds to result a dash or previously revealed letter
		 * depending on what is present at that index in the hidden/partially hidden word hiddenWord
		 */
		for (int i=0; i < word.length(); i++) {
			if (word.charAt(i) == guess) {
				result += guess;
			} else {
				result += hiddenWord.charAt(i);
			}
		}
		hiddenWord = result; // once operation is complete, sets hiddenWord equal to result
		canvas.displayWord(hiddenWord);
	}
	
	// adds incorrect guess to a String containing all the incorrect guesses
	private void printWrongGuess() {
		wrongGuess = wrongGuess + " - " + guess;
		N_WRONG++;
		println("There are no "+guess+"'s in the word. ");
	}
	
	private void printCorrectGuess() {
		revealedLetters = revealedLetters + " - " + guess;
		println("Your guess is correct! ");
	}
	
	// logs that a new segment has been added to the hangman drawing
	private void addBodyPart() {
		segmentsDrawn++;
		canvas.noteIncorrectGuess(guess);
	}
	
	// checks guess and assigns correctGuess as either true or false
	private void checkGuess() {
		guess = userGuess.charAt(0);
		guess = Character.toUpperCase(guess);
		int anyInstance = word.indexOf(guess);
		if (anyInstance != -1) {
			correctGuess = true;
                        addScore(100);
		}
		if (anyInstance == -1) {
			correctGuess = false;
		}
	}
	
	// displays word progress and number of turns left
	private void showProgress() {
            println("\nscore: " + score);
            println("The word now looks like this: "+hiddenWord+"");
            int guessesLeft = N_GUESS - N_WRONG;
            println("You have "+guessesLeft+" guesses left");
	}
	
	// Asks user for guess and logs response. Forces guesses to adhere to game rules
	private void playerGuess() {
		boolean validGuess = false;
		while (validGuess == false) {
			userGuess = readLine("Your guess is: ");
			if (userGuess.length() > 1) {
				println("\nYou have entered more than one letter, please try again. ");
			}
			if (userGuess == null) {
				println("You did not register a response. Please try again. ");
			}
			if (userGuess.length() == 1) {
				validGuess = true;
			}
		}
	}
	
	// User lost the game. Sad times :(. Displays a message to that effect
    private void printLost() {
    	println("\nYou lost!");
    	println("The word was "+word+"");
    	println("Better luck next time!");
        checkScore();
    }
    
    // Woop woop! The user won! This message lets them know
    private void printWinner() {
    	println("\nCongratulations! You won! The correct word is "+word+"");
        checkScore();
    }
    
    // asks the user if they want to play again, y indicates yes, any other key ends the program and prompts them to close the window
    private void playAgain() {
    	String rematch = readLine("\nPress 'y' if you would like to play again. ");
    	char y = 'y';
    	if (rematch.indexOf(y) != -1) {
    		restart();
    		setup();
    		playGame();
    	} else {
    		exit();
    	}
    }
    
    // resets the parameters if the user chooses to play again
    private void restart() {
    	win = false;
        score = 0;
    	N_WRONG = 0;
    	turns = 0;
    	hiddenWord = "";
    	canvas.removeAll();
    	canvas.reset();
    }
    
    private void addScore(int value) {
        score += value;
    }
    
    // Linked list implementation starts here
    
    private  int counter = 0;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    
    private MyLinkedList<String> highscore = new MyLinkedList<String>();

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
	
	private RandomGenerator rgen = RandomGenerator.getInstance(); // random generator to choose word from lexicon at random
        private String word; // the randomly chosen word
        private int score = 0; 
	public String hiddenWord = ""; // hidden version (with dashes) of randomly chosen word
	private boolean win = false; // whether the user has won the game or not
	private boolean correctGuess; // whether the user's guess is correct or not
	private int turns; // number of turns taken in the game
	private String userGuess; // the user's guess in the form of a String
	private char guess; // the user's guess truncated to a char
	public String wrongGuess = ""; // record of all wrong guesses made by user
	private int N_WRONG; // number of wrong guesses
	private String revealedLetters = ""; // record of letters that have been revealed so far
	private int segmentsDrawn = 0; // record of segments of hangman drawing drawn so far
	private HangmanLexicon lexicon = new HangmanLexicon(); // creates an object of type HangmanLexicon
	private HangmanCanvas canvas; // creates an object of type HangmanCanvas
	public int canvasWidth; // canvas width (initialised elsewhere)
	public int canvasHeight; // canvas height (initialised elsewhere)

    public static void main(String args[]){
        new Hangman().start();
    }
}	