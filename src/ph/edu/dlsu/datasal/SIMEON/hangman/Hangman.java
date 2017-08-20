/*
 * To change this license header, choose License Headers in Project Properties.
 * To change tk
his template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.hangman;
import ph.edu.dlsu.datasal.SIMEON.mylinkedlist.MyLinkedList;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Tiber
 */
public class Hangman extends ConsoleProgram {
    private MyLinkedList<String> words = new MyLinkedList<String>();
    RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon hayman = new HangmanLexicon();
    private HangmanCanvas canvas = new HangmanCanvas();
    public void init(){
       add(canvas);
       
    }
    String secretz;
    String hid = "";
    int rando;
    int lifes = 8;
    String gues;
    
    private boolean guess(String gues){
        if(gues.length()>1)
            return false;
        else if(Character.isDigit(gues.charAt(0)))
            return false;
        return true;
    }
    public void run(){
        
        rando= rgen.nextInt(1,hayman.getWordCount());
        secretz = hayman.getWord(rando);
        for(int i=0;i<secretz.length();i++){
            hid = hid+"-";
        }
        canvas.reset();
        canvas.displayWord(hid);
        println("Welcome to Hangman!!!!!!!");
        println(hayman.getWordCount());
        
        while(true){
        if(lifes==0){
        println("You're completely hung.");
        println("The word was: "+secretz);
        println("You lose.");
        break;
        }
        if (hid.equals(secretz)){
        println("You guessed the word "+hid);
        println("You win.");
        break;
        }
        
        println("The word now looks like this: "+hid);
        gues = readLine("Your guess: ");
        gues = gues.toUpperCase();
        while (guess(gues) == false){
        println("Invalid guess, try again.");
        gues = readLine("Your guess: ");
        gues = gues.toUpperCase(); 
        }
        if(secretz.indexOf(gues)==-1){
            lifes--;
            println("There are no " + gues+"'s in the word.");
            canvas.noteIncorrectGuess(gues.charAt(0));
            
        }
        else{
            println("That guess is correct");

            for(int i = 0; i<secretz.length();i++){
                if (secretz.charAt(i)==gues.charAt(0) && i == 0){
                    hid = gues + hid.substring(i+1);
                }
                else if( secretz.charAt(i)==gues.charAt(0) && i!=0){
                    hid = hid.substring(0,i) + gues +hid.substring(i+1);
            
                    }

                
                    
            }
            canvas.displayWord(hid);

 
            
        }



    }  
    }
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Hangman().start(args);
        // TODO code application logic here
    }
    
}
