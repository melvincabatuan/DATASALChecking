/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */
package ph.edu.dlsu.datasal.DELA_PENA.hangman;
import acm.graphics.*;
import java.awt.*;

public class HangmanCanvas extends GCanvas {
    private int WIDTH;
    private int HEIGHT;
    
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
        private static final int WORD_FONT_SIZE = 20;
        private static final int WORD_OFFSET = 5;
        
        private GLabel WORD = new GLabel("Dank memes");
        private GLabel GUESS = new GLabel("XD");
        private GLine LINE;
        private GOval HEAD = new GOval(0,0,0,0);
        
        
        
        
/** Resets the display so that only the scaffold appears */
	public void reset() {
            removeAll();
            WIDTH = getWidth();
            HEIGHT = getHeight();
            //construct stand
            LINE = new GLine((WIDTH/2)-BEAM_LENGTH, (HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2, (WIDTH/2)-BEAM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+SCAFFOLD_HEIGHT);
            add(LINE);
            LINE = new GLine((WIDTH/2)-BEAM_LENGTH, (HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2, (WIDTH/2), (HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2);
            add(LINE);
            LINE = new GLine((WIDTH/2), (HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2, (WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH);
            add(LINE);
           
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word, String Guess) {
            remove(WORD);
            remove(GUESS);
            WORD.setLabel("Your Word: "+word);
            WORD.setFont("Comic Sans MS-"+WORD_FONT_SIZE);
            WORD.setLocation(WORD_OFFSET, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+SCAFFOLD_HEIGHT+WORD_FONT_SIZE+WORD_OFFSET);
            add(WORD);
            GUESS.setLabel("Guesses: " + Guess);
            GUESS.setFont("Comic Sans MS-"+WORD_FONT_SIZE);
            GUESS.setLocation(WORD_OFFSET, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+SCAFFOLD_HEIGHT+(WORD_FONT_SIZE*2)+WORD_OFFSET);
            add(GUESS);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(int Guess_left) {
            switch(Guess_left){
                case 7: //head
                    HEAD = new GOval((WIDTH/2)-HEAD_RADIUS, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2);
                    add(HEAD);
                    break;
                case 6: //body
                    LINE = new GLine((WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2), (WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH);
                    add(LINE);
                    break;
                case 5: //left arm
                    LINE = new GLine((WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,(WIDTH/2)-UPPER_ARM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD);
                    add(LINE);
                    LINE = new GLine((WIDTH/2)-UPPER_ARM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,(WIDTH/2)-UPPER_ARM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
                    add(LINE);
                    break;
                case 4: //right arm
                    LINE = new GLine((WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,(WIDTH/2)+UPPER_ARM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD);
                    add(LINE);
                    LINE = new GLine((WIDTH/2)+UPPER_ARM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,(WIDTH/2)+UPPER_ARM_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
                    add(LINE);
                    break;
                     
                case 3: //left leg
                    LINE = new GLine((WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH, (WIDTH/2)-(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH);
                    add(LINE);
                    LINE = new GLine((WIDTH/2)-(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH, (WIDTH/2)-(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
                    add(LINE);
                    break;
                    
                case 2: //left foot
                    LINE = new GLine((WIDTH/2)-(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH, (WIDTH/2)-(HIP_WIDTH/2)-FOOT_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
                    add(LINE);
                    
                case 1: //right leg
                    LINE = new GLine((WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH, (WIDTH/2)+(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH);
                    add(LINE);
                    LINE = new GLine((WIDTH/2)+(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH, (WIDTH/2)+(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
                    add(LINE);
                    break;
                
                case 0: //right foot
                    LINE = new GLine((WIDTH/2)+(HIP_WIDTH/2), ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH, (WIDTH/2)+(HIP_WIDTH/2)+FOOT_LENGTH, ((HEIGHT-SCAFFOLD_HEIGHT-(WORD_FONT_SIZE*2)-(WORD_OFFSET*2))/2)+ROPE_LENGTH+(HEAD_RADIUS*2)+BODY_LENGTH+LEG_LENGTH);
                    add(LINE);
            }
	}

/* Constants for the simple version of the picture (in pixels) */
	

}
