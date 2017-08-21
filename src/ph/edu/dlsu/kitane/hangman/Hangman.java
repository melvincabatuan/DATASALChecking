/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.hangman;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
/**
 *
 * @author Christoph Kitane
 */
public class Hangman extends ConsoleProgram{
    private HangmanCanvas canvas = new HangmanCanvas();
    private HangmanLexicon hangmanLexicon = new HangmanLexicon();
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private int lexiconIndex;
    
    //word and its properties
    private String word;
    private String wordString;
    private char[] wordChar;
    private int x,y;
    
    //Game Properties
    private int guess=8;
    private String tryLetter;
    private int dash;
    private String choice;
    private String yes = "Y";
    private String no = "N";
    private void encryptWord(){
        for(int i=0;i<word.length();i++){
            wordChar[i] ='-';
        }
        wordString = String.copyValueOf(wordChar);
        canvas.displayWord(wordString);
    }
    
    private char[] stringToChar(String word) {
        return wordChar = word.toCharArray();
        
    }
    private void window(){
        println("Welcome to Hangman!");
        encryptWord();
        println("The word you have to guess looks like this: ");
        for(int i=0;i<word.length();i++){
            print(wordChar[i]+" ");
        }
        println("\nYou have "+guess+" guesses left.");
        
    }
    
    private void validateWord(){
        guess=8;
        while(dash!=0){
            x=0;
            y=0;
            do{
                print("Write your Guess: ");
                tryLetter = readLine();
            }while(tryLetter.length()>1);         
            tryLetter = tryLetter.toUpperCase();
            char[] tryChar = tryLetter.toCharArray();
            for(int i=0;i<word.length();i++){
                if((word.charAt(i)==tryLetter.charAt(0))&&wordChar[i]!=tryChar[0]){
                   wordChar[i] = tryChar[0];
                    x++;
                    dash--;
                }else if(wordChar[i]==tryChar[0]){
                    y++;
                }
            }
            if(x>0){
                for(int i=0;i<word.length();i++){
                    print(wordChar[i]+" ");
                }
                wordString = String.copyValueOf(wordChar);
                canvas.displayWord(wordString);
                println("\n NICE! You have "+guess+" guesses left.");
            }else if(x==0&&y==0&&guess!=2){
                guess--;
                for(int i=0;i<word.length();i++){
                print(wordChar[i]+" ");
                }
                canvas.noteIncorrectGuess(tryChar[0]);
                println("\n The word does not contain "+tryLetter+" in it. "+"You have "+guess+" guesses left.");
            }else if(guess<=2){
                guess--;
                for(int i=0;i<word.length();i++){
                print(wordChar[i]+" ");
                }
                canvas.noteIncorrectGuess(tryChar[0]);
                println("\n The word does not contain "+tryLetter+" in it. "+"You have "+guess+" guess left.");
            }else if(y>0){
                println("\n You've already Guessed the letter "+tryLetter+", please guess another.");
                println("You have "+guess+" guesses left.");
            }
            if(guess==0){
                break;
            }
        
            print("\n");
        }
    }
    private void printGuessedCorrect(){
        println("CONGRATULATIONS! You got it right!");
        canvas.congrats();
    }
    private void printGuessedWrong(){
        canvas.displayWord(word);
        canvas.gameOver();
        println("GAME OVER! Better luck next time");
    }
    public void run(){
        System.out.println("Hangman is executed.");
        do{
            add(canvas);
            canvas.reset();
            lexiconIndex = rgen.nextInt(0, 121806);
            word = hangmanLexicon.getWord(lexiconIndex);
            dash = word.length();
            stringToChar(word);
            window();
            validateWord();
            if(dash==0){
                printGuessedCorrect();
            }else if(dash>0){
            printGuessedWrong();
            }
            do{
                print("Do you want to go again? <Y/N> ");
                choice = readLine();
            }while(choice.length()>1);
            print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }while(choice.toUpperCase().equals(yes));
        print("end");
    }
    
}
