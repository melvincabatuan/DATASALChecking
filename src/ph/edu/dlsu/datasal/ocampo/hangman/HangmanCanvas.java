/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

package ph.edu.dlsu.datasal.ocampo.hangman;
import acm.graphics.*;
import java.awt.Color;

public class HangmanCanvas extends GCanvas {
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
        
        // Object (fields)
        private GLabel blankWord = new GLabel("");
        private GLabel incorrectLetters = new GLabel("");
        private String iLetterString = "";
        //private GLine scaffold = new GLine(0, 0, 0, SCAFFOLD_HEIGHT);
        private GRect scaffold = new GRect(5, SCAFFOLD_HEIGHT);
        // private GLine beam = new GLine(0, 0, BEAM_LENGTH, 0);
        private GRect beam = new GRect(BEAM_LENGTH, 5);
        private GLine rope = new GLine(0, 0, 0, ROPE_LENGTH);
        private GOval head = new GOval(2*HEAD_RADIUS, 2*HEAD_RADIUS);
        private GLine body = new GLine(0, 0, 0, BODY_LENGTH);
        private GLine leftArm = new GLine(0, 0, UPPER_ARM_LENGTH, 0);
        private GLine rightArm = new GLine(0, 0, UPPER_ARM_LENGTH, 0);
        private GLine leftLowArm = new GLine(0, 0, 0, LOWER_ARM_LENGTH);
        private GLine rightLowArm = new GLine(0, 0, 0, LOWER_ARM_LENGTH);
        private GLine lhip = new GLine(0, 0, HIP_WIDTH, 0);
        private GLine rhip = new GLine(0, 0, HIP_WIDTH, 0);
        private GLine lLeg = new GLine(0, 0, 0, LEG_LENGTH);
        private GLine rLeg = new GLine(0, 0, 0, LEG_LENGTH);
        private GLine lFoot = new GLine(0, 0, FOOT_LENGTH, 0);
        private GLine rFoot = new GLine(0, 0, FOOT_LENGTH, 0);
        
        private int incorrectGuesses = 0;
        
        // Get Center methods
        private double getXCenter(GObject obj) {
            return (getWidth()-obj.getWidth())/2;
        }
        
        private double getYCenter(GObject obj) {
            return (getHeight()-obj.getHeight())/2;
        }
        
        public void reset() {
            removeAll();
            incorrectGuesses = 0;
            iLetterString = "";
            beam.setFilled(true);
            scaffold.setFilled(true);
            add(scaffold, getWidth()/2-beam.getWidth(), (getHeight()-SCAFFOLD_HEIGHT)/2-ROPE_LENGTH);
            add(beam, getWidth()/2-BEAM_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-ROPE_LENGTH);
            add(rope, getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-ROPE_LENGTH);
            blankWord.setFont("SansSerif-25");
            blankWord.setColor(Color.BLACK);
            add(incorrectLetters, getXCenter(incorrectLetters), getHeight()-incorrectLetters.getHeight());
            add(blankWord, getXCenter(blankWord), getHeight()-incorrectLetters.getHeight()-blankWord.getHeight());
        }
        
        private boolean containsBlank(String word) {
            boolean res = false;
            for(int i=0; i<word.length(); i++) {
                if(word.charAt(i)=='-') {
                    res=true; break;
                }
            }
            return res;
        }
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
        
	public void displayWord(String word, int guess) {
            remove(blankWord);
            blankWord.setLabel(word);
            if(!containsBlank(word)) blankWord.setColor(Color.GREEN);
            else if(guess==0) blankWord.setColor(Color.RED);
            add(blankWord, getXCenter(blankWord), getHeight()-incorrectLetters.getHeight()-blankWord.getHeight());
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
            // Draw body parts
            double armY=0, hipY=0, footY=0;
            incorrectGuesses++;
            switch(incorrectGuesses) {
                case 1: add(head, getXCenter(head), (getHeight()-SCAFFOLD_HEIGHT)/2); break;
                case 2: 
                    add(body, getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS); break;
                case 3:
                    armY = (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD;
                    add(leftArm, getWidth()/2-UPPER_ARM_LENGTH, armY); 
                    add(leftLowArm, getWidth()/2-UPPER_ARM_LENGTH, armY); break;
                case 4:
                    armY = (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD;
                    add(rightArm, getWidth()/2, armY); 
                    add(rightLowArm, getWidth()/2+UPPER_ARM_LENGTH, armY); break;
                case 5: 
                    hipY = (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS+BODY_LENGTH;
                    add(lhip, getWidth()/2-HIP_WIDTH, hipY); 
                    add(lLeg, getWidth()/2-HIP_WIDTH, hipY); break;
                case 6:
                    hipY = (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS+BODY_LENGTH;
                    add(rhip, getWidth()/2, hipY);
                    add(rLeg, getWidth()/2+HIP_WIDTH, hipY); break;
                case 7: 
                    footY = (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH;
                    add(lFoot, getWidth()/2-HIP_WIDTH-FOOT_LENGTH, footY); break;
                case 8:
                    footY = (getHeight()-SCAFFOLD_HEIGHT)/2+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH;
                    add(rFoot, getHeight()/2+HIP_WIDTH+FOOT_LENGTH, footY); break;
            }
            
            remove(incorrectLetters);
            iLetterString+=Character.toString(Character.toUpperCase(letter))+", ";
            incorrectLetters.setLabel(iLetterString);
            add(incorrectLetters, getXCenter(incorrectLetters), getHeight()-incorrectLetters.getHeight());
	}
}
