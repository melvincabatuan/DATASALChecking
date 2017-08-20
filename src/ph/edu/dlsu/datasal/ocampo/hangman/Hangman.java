/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */
package ph.edu.dlsu.datasal.ocampo.hangman;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import ph.edu.dlsu.datasal.ocampo.mylinkedlist.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Hangman extends ConsoleProgram {
    
    private String word="";
    private HangmanLexicon data = new HangmanLexicon();
    private RandomGenerator rgen = new RandomGenerator();
    private int guessCount = 8;
    private char guessLetter;
    private String blank;
    private int remain;
    private String usedLetters;
    private HangmanCanvas canvas;
    

    private String replaceChar(String in, int index, char ch) {
        String str=in;
        if(index==0) str = Character.toString(ch)+in.substring(1);
        else {
            str = in.substring(0, index)+Character.toString(ch);
            if(index<in.length()-1) str+=in.substring(index+1);
        }
        return str;
    }
    
    // Check if its in word
    public boolean isInWord() {
        boolean res=false;
        for(int i=0; i<word.length(); i++) {
            if(Character.toUpperCase(guessLetter)==word.charAt(i)) {
                res=true;
                break;
            }
        }
        return res;
    }
    
    // Update the blank word
    private void updateWord() {
        for(int i=0; i<word.length(); i++) {
            if(Character.toUpperCase(guessLetter)==word.charAt(i)) {
                blank = replaceChar(blank, i, Character.toUpperCase(guessLetter));
                remain--;
            }
        }
    }
    
    // Check if letter has been guessed
    public boolean isGuessed(char letter) {
        boolean res=false;
        
        for(int i=0; i<usedLetters.length(); i++) {
            if(letter==usedLetters.charAt(i)) {
                res=true; break;
            }
        }
        
        return res;
    }
    
    private void setUp() {
        data.loadList();
        canvas = new HangmanCanvas();
        setUpGame();
        add(canvas);
    }
    
    // Setup game
    private void setUpGame() {
        word = data.getWord(rgen.nextInt(1, data.getWordCount()));
        blank = "";
        guessCount = 8;
        remain = word.length();
        usedLetters="";
        for(int i=0; i<word.length(); i++)
            blank += "-";
    }
    
    public void run() {
        setUp();
        while(true) {
            canvas.reset();
            println("Welcome to Hangman!");
            // Gameplay
            while(guessCount>0 && remain>0) {
                println("The word now looks like this: "+blank);
                if(guessCount==1) 
                    println("You have "+guessCount+" guess left");
                else
                    println("You have "+guessCount+" guesses left");
                
                canvas.displayWord(blank, guessCount);
                String g = readLine("Your guess: ");
                
                // Checking
                if(g.length()==1) {
                    guessLetter = Character.toUpperCase(g.charAt(0));
                    if(isGuessed(guessLetter)) 
                        println("Letter already guessed.");
                    else {
                        if(isInWord()) {
                            println("That guess is correct!");
                            updateWord();
                        }
                        else {
                            println("There are no "+guessLetter+"\'s in the word.");
                            canvas.noteIncorrectGuess(guessLetter);
                            guessCount--;
                        }
                        usedLetters+=g.toUpperCase();
                    }  
                }
                else println("Invalid input");
                
                // Win or lose
                if(guessCount==0) {
                    println("You're completely hung.");
                    println("The word was "+word);
                    println("You lose.");
                    canvas.displayWord(word, guessCount);
                }
                else if(remain==0) {
                    println("You guessed the word: "+word);
                    canvas.displayWord(blank, guessCount);
                    println("You win.");
                }
            }
            
            pause(5000);
            setUpGame();
        }
    }
    
}
