package ph.edu.dlsu.datasal.wenceslao.hangman;

import ph.edu.dlsu.datasal.wenceslao.mylinkedlist.*;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import javax.swing.*;

// Author: Luis Paolo D. Wenceslao

public class Hangman_LPDW extends ConsoleProgram{

    private HangmanCanvas canvas;
    private HangmanLexicon hangmanWords;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    private int guessCount = 8;
    private int Guesses;
    
    private String hiddenWord;

    private String word = RandomWord();

    private char cha;

    private String incorrectLetters = "";
    
    private int score = 0;
    
    public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    public void run(){
        Guesses = guessCount;
        score = 0;
        setupGame();
        playGame();
    }
    
    private void restart(){
        canvas.removeAll();
        add(canvas);
        incorrectLetters = "";
        Guesses = guessCount;
        word = RandomWord();
        run();
    }
    
    private void select(){
        int option;
        println("Press [1] to restart game.");
        println("Press [2] to exit.");
        option = readInt("Input choice: ");
        println("");
        println("");
        switch(option){
            case 1:
                restart();
                break;
            case 2:
                exit();
            default:
                println("Invalid option");
                println("");
                select();
                break;
        }
    }
    
    private void setupGame(){
    	canvas.reset();
    	hiddenWord = Dashes();
    	canvas.displayWord(hiddenWord);
        // WELCOME SCREEN //
        println("===============================");
    	println("       Welcome to HANGMAN!     ");
        println("===============================");
    	println("Your word now looks like this: " + hiddenWord);
    	println("You have " + Guesses + " number of guesses left.");
        println("Score: " + score);
        println("");
    }
    
    private String Dashes(){
    	String res = "";
    	for(int i=0; i<word.length(); i++){
            res += "-";
    	}
    	return res;
    }
    
    private String RandomWord(){
    	hangmanWords = new HangmanLexicon();
    	return(hangmanWords.getWord(rgen.nextInt(0, hangmanWords.getWordCount() - 1)));
    }
    
    private void playGame(){
    	while(Guesses > 0){
            String guess = readLine("Your guess: ");
            while(true){
                if(guess.length() > 1){
                println("You can guess only one letter at a time!");
                guess = readLine("Try again: ");
            }
                if(guess.length() == 1){
                    break;
                }
            }
            cha = guess.charAt(0);
            cha = Character.toUpperCase(cha);
            letterCheck();
            if(hiddenWord.equals(word)){
                // WIN SCREEN //
                println("===============================");
                println("You guessed the word: " +  word);
                println("YOU WIN!");
                println("YOUR SCORE: " + score);
                println("===============================");
                println("");
                checkScore();
                println("");
                select();
                break;
            }
            println("");
            println("The word now looks like this: " + hiddenWord);
            println("You have " + Guesses + " number of guesses left.");
            println("Score: " + score);
            println("");
    	}
    	if(Guesses == 0){
            // LOSE SCREEN //
            println("===============================");
            println("You're completely hung.");
            println("The hidden word was: " + word);
            println("YOU LOSE!");
            println("YOUR SCORE: " + score);
            println("===============================");
            println("");
            checkScore();
            println("");
            select();
    	}
    }
    
    private void letterCheck(){
    	if(word.indexOf(cha) == -1){
            println("There are no " + cha + "'s in the word.");
            Guesses--;
            incorrectLetters += cha;
            canvas.noteIncorrectGuess(incorrectLetters);
//            subtractScore(2);
    	}
    	if(word.indexOf(cha) != -1){
            println("That guess is correct.");
            for(int i = 0; i < word.length(); i++){
                if(cha == word.charAt(i)){
                    if(i == 0){
                        hiddenWord = cha + hiddenWord.substring(1);
                    }
                    if(i > 0){
                        hiddenWord = hiddenWord.substring(0, i) + cha + hiddenWord.substring(i+1);
                    }
                }
            }
            canvas.displayWord(hiddenWord);
            addScore(10);
    	}
    	
    }
    
    private void addScore(int value) {
        score += value;
    }
    
//    private void subtractScore(int value) {
//        score -= value;
//    }
    
    private  int counter = 0;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    
    private MyLinkedList<String> highscore = new MyLinkedList<String>();

    private void Leaderboard() {
        println("===============================");
        println("LEADERBOARD: ");
        for(int i = 1; i <= counter; i++){
            println("" + i + ". " + highscore.get(i));
        }
        println("===============================");
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
            Leaderboard();
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
            Leaderboard();
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
            Leaderboard();
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
    
//    public static void main(String[] args) {
//        new Hangman_LPDW().start(args);
//    }
}