package ph.edu.dlsu.velasco.hangman;

/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;
import java.awt.Color;


import java.util.Scanner;

public class Hangman extends ConsoleProgram {

    private HangmanLexicon Lexicon = new HangmanLexicon();
    private HangmanCanvas Canvas = new HangmanCanvas();
    
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;
            
    private int wordIndex;
    private final RandomGenerator rgen = RandomGenerator.getInstance();
    private char[] CharArray = new char[20];
    private char[] DashArray = new char[20];
    private String[] WordArray = new String[20];
    private String Word;
    public int WordCount = Lexicon.getWordCount();
    private int WordLength;
    private String Input;
    private char[] InputArray = new char[10];
    Scanner s = new Scanner(System.in);
    private int count;
    private int[] ifMultiple = new int[20];
    private int j=0;
    private char[] Alphabet = new char[30];
    private int c=0;
    private int lives=8;
    private String ENTER;
    
    public void setWord(){
        wordIndex = rgen.nextInt(0,WordCount-1);
        Word = Lexicon.getWord(wordIndex);
        WordLength = Word.length();
        CharArray = Word.toCharArray();
    }
    
    public void WordInit(){
        for(int i=0; i<WordLength; i++){
            DashArray[i]='-';
            print(" "+DashArray[i]);
        }
        println("");
    }
    
    public void AskForInput(){
        Input = readLine("Input your guess: ");
        if (Input.isEmpty()){
            //do nothing
        }
        else{
            Input = Input.toUpperCase();
            InputArray = Input.toCharArray();
        }
    }
    
    public boolean ValidateInput(){
        for(int i=0; i<WordLength; i++){
            if(CharArray[i]==InputArray[0] && RepeatedInput()==false){
                return true;
            }
        }
        return false;
    }
    
    public void ReturnIndex(){
        int i;
        j=0;
        for(i=0;i<WordLength;i++){
            if(CharArray[i]==InputArray[0]){
                CharArray[i]=InputArray[0];
                ifMultiple[j]=i;
                j++;
            }
        }
    }

    public void Counter(){
        count=0;
        for (int i=0; i<=WordLength; i++){
            if (DashArray[i]=='-'){
                count++;
            }
        }
    }
    
    public boolean PressEnter(){
        ENTER = readLine("");
        return ENTER.isEmpty();
    }
    
    public boolean RepeatedInput(){
        for(int i=0; i<WordLength; i++){
            if(DashArray[i]==InputArray[0]){
                return true;
            }
            else if(Alphabet[i]==InputArray[0]){
                return true;
            }
        }
        return false;
    }
    public void Retry(){
        Canvas.reset();
        lives = 8;
        count = WordCount;
        c=0;
        Alphabet = new char[30];
    }

    public void GameAI(){
        Lexicon.retrieveWord();
        setWord();
        println("WELCOME TO HANGMAN!!");
        println("Your word looks like this: ");
        WordInit();
        Counter();
        Canvas.displayWord(DashArray, WordLength);
        println("You have "+lives+" tries left.");
        while(count!=0 && lives!=0){
            AskForInput();
            if(ValidateInput()){
                println("Your word is now: ");
                ReturnIndex();
                for(int i=0; i<j; i++){
                    DashArray[ifMultiple[i]]=InputArray[0];
                    count--;
                }
                for (int i=0; i<WordLength; i++){
                    print(" "+DashArray[i]);
                }
                println("");
                Canvas.displayWord(DashArray, WordLength);
            }
            else if(RepeatedInput()==true){
                println("You have already guessed that letter!");
                lives--;
                println("You have "+lives+" tries left.");
                Canvas.IncorrectLetter(lives);
            }
            else{
                println("There is no " + Input + " in this word.");
                Alphabet[c]=InputArray[0];
                c++;
                lives--;
                println("You have "+lives+" tries left.");
                Canvas.noteIncorrectGuess(Alphabet, WordLength);
                Canvas.IncorrectLetter(lives);
            //else draw Hangman Picture
            }
        }
        if(count==0){
            println("CONGRATULATIONS! The word is "+Word);
            Canvas.congrats();
        }
        else if(lives==0)
            println("GAME OVER! The word is "+Word);
            Canvas.GameOver(Word);
        println("RETRY? Press enter to try again");
        if(PressEnter()){
            Retry();
            GameAI();
        }    
    }
    
    public void init(){
        add(Canvas);
        Canvas.reset();
        Color c = new Color(204,229,225);
        Canvas.setBackground(c);
    }
    
    private boolean recursiveShield = true;
    
    public void run() {
        if(recursiveShield){
            init();
            recursiveShield = false;
        }
        GameAI();
    }
    
    public static void main(String[] args){
        new Hangman().start(args);
    }

    private char readChar(String enter_your_guess_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CharSequence readCharSequence(String enter_your_guess_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
