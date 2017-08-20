/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */
package ph.edu.dlsu.datasal.DELA_PENA.hangman;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyLinkedList.*;

import java.awt.*;

public class HangmanDP extends ConsoleProgram {
    
    private HangmanCanvas canvas;
    
    public HangmanLexicon Lexicon = new HangmanLexicon();
    //private MyLinkedList<String> CORRECT_GUESSES = new MyLinkedList<String>();
    private MyLinkedList<String> WORD_LETTERS = new MyLinkedList<String>();
    private MyLinkedList<String> CURRENT_WORD = new MyLinkedList<String>();
    private MyLinkedList<String> PAST_GUESSES = new MyLinkedList<String>();
    
    private final RandomGenerator rgen=RandomGenerator.getInstance();
    private String WORD_TO_GUESS;
    private String PRINT_WORD="";
    private int num_correct;
    private int GUESSES_LEFT;
    private String MY_GUESS;
    
    public void init(){
        //println("Loading... \n");
        
        canvas = new HangmanCanvas();
        add(canvas);
        
        num_correct=0;
        GUESSES_LEFT = 8;
        
        Lexicon.createLexicon();
        WORD_LETTERS.createList();
        CURRENT_WORD.createList();
        PAST_GUESSES.createList();
        
        WORD_TO_GUESS = Lexicon.getWord(rgen.nextInt(1, Lexicon.getWordCount()));
        createWordLetterList();
        createCurrentWord();
        canvas.reset();
        canvas.displayWord(printWord(CURRENT_WORD), printWord(PAST_GUESSES));
    }
    
    
    public void run() {
        println("WELCOME TO SUSPENDED DUDE by Paolo TM");
        //String_Temp = new String(Lexicon.getWord(5));
        
        //while(num_correct<WORD_TO_GUESS.length()){
        while(true){    
        //}
        while(num_correct<WORD_TO_GUESS.length() && GUESSES_LEFT>0){
            println("\nThe word now looks like this: " + printWord(CURRENT_WORD));
            println("You have " + GUESSES_LEFT + " guesses left");
            MY_GUESS = readLine("Your guess: ");
            MY_GUESS = MY_GUESS.toUpperCase();
            if(MY_GUESS.length()>1 || MY_GUESS.equals("")){
                println("INVALID INPUT. only enter one character");
                continue;
            }
            else{
                if(checkIfAlreadyGuessed(MY_GUESS)&&PAST_GUESSES.size()>0){
                    println("You already tried that letter, try another one");
                    continue;
                }
                else if(checkLetterInWord(MY_GUESS)){
                    println("That guess is correct");
                }
                else {
                    println("There are no "+MY_GUESS+"'s in the word");
                    GUESSES_LEFT--;
                    canvas.noteIncorrectGuess(GUESSES_LEFT);
                }   
                PAST_GUESSES.add(PAST_GUESSES.size()+1, MY_GUESS);
            }
            canvas.displayWord(printWord(CURRENT_WORD), printWord(PAST_GUESSES));  
        }
        
        if(num_correct == WORD_TO_GUESS.length()){
            println("YOU GUESSED THE WORD: " + printWord(CURRENT_WORD));
            println("Suspended dude lives another day");
        }
        else if(GUESSES_LEFT == 0){
            println("\nYou failed to guess: "+WORD_TO_GUESS + "\nYou let him die");
        }
        
        readLine("\n\nPress enter to Continue...");
        resetGame();
        }
    }
    
    public void resetGame(){
        canvas.reset();
        num_correct=0;
        GUESSES_LEFT = 8;
        for(int i=WORD_LETTERS.size(); i>=1; i--){
            WORD_LETTERS.remove(i);
        }
        for(int i=CURRENT_WORD.size(); i>=1; i--){
            CURRENT_WORD.remove(i);
        }
        for(int i=PAST_GUESSES.size(); i>=1; i--){
            PAST_GUESSES.remove(i);
        }
        for(int i=0; i<30; i++){
            println("");
        }
        WORD_TO_GUESS = Lexicon.getWord(rgen.nextInt(1, Lexicon.getWordCount()-1));
        createWordLetterList();
        createCurrentWord();
        canvas.displayWord(printWord(CURRENT_WORD), printWord(PAST_GUESSES)); 
        
    }
    
    public boolean checkIfAlreadyGuessed(String guess){
        boolean pastguess=false;
        if(PAST_GUESSES.size()!=0){
            for(int i=1; i<=PAST_GUESSES.size(); i++){
                if(guess.equals(PAST_GUESSES.get(i))){
                    pastguess=true;
                    break;
                }
            }
        }
        return pastguess;
    }
    
    public boolean checkLetterInWord(String guess){
        boolean letterinword=false;
            for(int i=1; i<=WORD_TO_GUESS.length(); i++){
                if(guess.equals(WORD_LETTERS.get(i))){
                    letterinword=true;
                    CURRENT_WORD.remove(i);
                    CURRENT_WORD.add(i, guess);
                    
                    num_correct++;
                }
            }
        return letterinword;
    }
    
    public void createWordLetterList(){ //creates a list with each correct letter
        
        for(int i=0; i<WORD_TO_GUESS.length(); i++){
            WORD_LETTERS.add(i+1, WORD_TO_GUESS.substring(i, i+1));
        }
    }
    
    public void createCurrentWord(){
        for(int i=1; i<=WORD_TO_GUESS.length(); i++){
            CURRENT_WORD.add(i, "-");
        }
    }
    public String printWord(MyLinkedList<String> WORD){
        String PRINTME="";
        for(int i=1; i<=WORD.size(); i++){
            PRINTME = PRINTME.concat(WORD.get(i));
        }
        
        return PRINTME;
    }
    
    public static void main(String[] args){
        new HangmanDP().start(args);
    }
}