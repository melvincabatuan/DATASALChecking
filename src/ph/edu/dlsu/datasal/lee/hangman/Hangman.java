package ph.edu.dlsu.datasal.lee.hangman;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hangman extends ConsoleProgram {
    private HangmanCanvas canvas = new HangmanCanvas();
    
    public void init() {
        add(canvas);
    }
    
    public static void main(String[] args ) {
            new Hangman().start(args);
        }

    
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon HL = new HangmanLexicon();
    private char[] I = new char[30];
    private char out;
    private int count=0, prev=0, lives=8;
    private boolean check=false;
    private char[] output = new char[30];
   
    public void run() {
        try {
            HL.List();
        } catch (IOException ex) {
            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
        }
        int Wnum= rgen.nextInt(0,HL.getWordCount()-1);
        String word= HL.getWord(Wnum);
        //println(""+word);
        println("Welcome to Hangman");
        print("The word now looks like this: ");
        for(int i =word.length();i>0;i--){
            print("-");
        }
        println("");
        char[] L =word.toCharArray();
        
        for(int i=0;i<=26;i++){
            check = false;
            prev=count;
            count=0;
            while(check == false){
                String input = readLine("You entered: ");
                I[i] = input.charAt(0);
                I[i] = Character.toUpperCase(I[i]);
                if(input.length()>1) println("INVALID: Enter only one character.");
                else{
                    check=true;
                }
            }
            print("The word now looks like this: ");
            for(int j=0;j<word.length();j++){
                for(int k=0;k<=i;k++){
                    if(I[k]==L[j]){
                        out=I[k];
                        count++;
                        break;
                    }
                    else out=0;
                }
                if(out!=0){
                    output[j]=out;
                }
                else 
                    output[j]='-';
            }
            canvas.reset();
            String Foutput= new String(output);
            println(""+ Foutput);
            if(count<=prev){
                canvas.noteIncorrectGuess(I[i]);
                lives--;
            }
            else canvas.noteIncorrectGuess(' ');
            canvas.displayWord(Foutput,8-lives);
            if(count== word.length()||lives==0) break;
        } 
        println("----------------------------");
        if(lives!=0) println("Congratulions! YOU WIN");
        else println("GAMEOVER");
    }
}





