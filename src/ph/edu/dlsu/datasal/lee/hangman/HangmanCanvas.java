/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

package ph.edu.dlsu.datasal.lee.hangman;

import acm.graphics.*;
import java.awt.Color;


public class HangmanCanvas extends GCanvas {
    
public String output="";
public int counter=0;
public GRect scaffold1= new GRect(70,70,20,360);
public GRect scaffold2= new GRect(90,70,200,20);
public GRect scaffold3= new GRect(60,430,40,20);
public GRect scaffold4= new GRect(190,90,20,20);
public GOval head = new GOval(160,100,80,80);
public GOval body = new GOval(160,180,80,120);
public GRect arm1 = new GRect(160,230,15,90);
public GRect arm2 = new GRect(226,230,15,90);
public GRect leg1 = new GRect(175,280,23,130);
public GRect leg2 = new GRect(203,280,23,130);
public GRect foot1 = new GRect(158,410,40,20);
public GRect foot2 = new GRect(203,410,40,20);





/** Resets the display so that only the scaffold appears */
	public void reset() {
            removeAll();
            Fills();
            add(scaffold1);
            add(scaffold2);
            add(scaffold3);
            add(scaffold4);
	}
        
        private void Fills(){
            scaffold1.setFilled(true);
            scaffold2.setFilled(true);            
            scaffold3.setFilled(true);
            scaffold4.setFilled(true);
            scaffold1.setFillColor(Color.RED);
            scaffold2.setFillColor(Color.RED);
            scaffold3.setFillColor(Color.RED);
            scaffold4.setFillColor(Color.RED);
            head.setFilled(true);
            head.setFillColor(Color.BLACK);
            body.setFilled(true);
            body.setFillColor(Color.BLACK);
            arm1.setFilled(true);
            arm1.setFillColor(Color.BLACK);
            arm2.setFilled(true);
            arm2.setFillColor(Color.BLACK);
            leg1.setColor(Color.BLACK);
            leg1.setFilled(true);
            leg1.setFillColor(Color.BLACK); 
            leg2.setColor(Color.BLACK);
            leg2.setFilled(true);
            leg2.setFillColor(Color.BLACK);
            foot1.setFilled(true);
            foot1.setFillColor(Color.BLACK);
            foot2.setFilled(true);
            foot2.setFillColor(Color.BLACK);
        }

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word, int count) {
            GLabel answer = new GLabel(""+word);
            answer.setFont("Calibri-Bold-32");
            add(answer,100,480);
            switch(count){
                case 1: 
                    add(head);
                    break;
                case 2: 
                    add(body);
                    add(head);
                    break;
                case 3:    
                    add(body);
                    add(head);
                    add(arm1);
                    break;
                case 4:
                    add(body);
                    add(head);
                    add(arm1);
                    add(arm2);
                    break;
                case 5:
                    add(body);
                    add(head);
                    add(arm1);
                    add(arm2);
                    add(leg1);
                    break;
                case 6:
                    add(body);
                    add(head);
                    add(arm1);
                    add(arm2);
                    add(leg1);
                    add(leg2);
                    break;
                case 7:
                    add(body);
                    add(head);
                    add(arm1);
                    add(arm2);
                    add(leg1);
                    add(leg2);
                    add(foot1);
                    break;
                case 8:
                    add(body);
                    add(head);
                    add(arm1);
                    add(arm2);
                    add(leg1);
                    add(leg2);
                    add(foot1);
                    add(foot2);
                    break;    
            }
        }

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
            if(letter!=' ') output =new String(""+output+letter);
            else output= new String(""+output);
            GLabel answer = new GLabel(""+output);
            answer.setFont("Calibri-Bold-30");
            add(answer,100,30);  
        }

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
