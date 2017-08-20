/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

package ph.edu.dlsu.datasal.wenceslao.hangman;

import acm.graphics.*;
import java.awt.*;

// Author: Luis Paolo D. Wenceslao

public class HangmanCanvas extends GCanvas{
	
	/** Resets the display so that only the scaffold appears */
	public void reset() {
            drawScaffold();
	}
	
	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game. The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
            double x = getWidth()/4;
            double y = getHeight() - 2 * HEAD_RADIUS;
            GLabel hiddenWord = new GLabel(word);
            hiddenWord.setFont("Helvetica-26");
            if(getElementAt(x, y) != null){
                    remove(getElementAt(x, y));
            }
            add(hiddenWord, x, y);
	}
	
	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user. Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(String incorrectGuesses) {
            double x = getWidth()/4;
            double y = getHeight() - HEAD_RADIUS;
            GLabel incorrectLetters = new GLabel(incorrectGuesses);
            incorrectLetters.setFont("Helvetica-26");
            incorrectLetters.setColor(Color.RED);
            if(getElementAt(x, y) != null){
                remove(getElementAt(x, y));
            }
            add(incorrectLetters, x, y);
            if(incorrectGuesses.length() == 1){
                drawHead();
            }
            if(incorrectGuesses.length() == 2){
                drawBody();
            }
            if(incorrectGuesses.length() == 3){
                drawLeftArm();
            }
            if(incorrectGuesses.length() == 4){
                drawRightArm();
            }
            if(incorrectGuesses.length() == 5){
                drawLeftLeg();
            }
            if(incorrectGuesses.length() == 6){
                drawRightLeg();
            }
            if(incorrectGuesses.length() == 7){
                drawLeftFoot();
            }
            if(incorrectGuesses.length() == 8){
                drawRightFoot();
            }
		
	}
        
        private void drawScaffold(){
            double scaffoldTopX = getWidth()/2 - BEAM_LENGTH;
            double scaffoldTopY = getHeight()/2 - BODY_LENGTH - 2 * HEAD_RADIUS-ROPE_LENGTH;
            double scaffoldBottomY = scaffoldTopY + SCAFFOLD_HEIGHT;
            GLine scaffold = new GLine(scaffoldTopX, scaffoldTopY, scaffoldTopX, scaffoldBottomY);
            add(scaffold);
            double beamRightX = scaffoldTopX + BEAM_LENGTH;
            GLine beam = new GLine(scaffoldTopX, scaffoldTopY, beamRightX, scaffoldTopY);
            add(beam);
            double ropeBottomY = scaffoldTopY + ROPE_LENGTH;
            GLine rope = new GLine(beamRightX, scaffoldTopY, beamRightX, ropeBottomY);
            add(rope);
	}
	
	private void drawHead(){
            double x = getWidth()/2 - HEAD_RADIUS;
            double y = getHeight()/2 - BODY_LENGTH - 2 * HEAD_RADIUS;
            GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
            add(head, x, y);
	}
	
	private void drawBody(){
            double x = getWidth()/2;
            double bodyTopY = getHeight()/2 - BODY_LENGTH;
            double bodyBottomY = getHeight()/2;
            GLine body = new GLine(x, bodyTopY, x, bodyBottomY);
            add(body);
	}
	
	private void drawLeftArm(){
            double upperarmRightX = getWidth()/2;
            double upperarmLeftX = upperarmRightX - UPPER_ARM_LENGTH;
            double y = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
            GLine upperLeftArm = new GLine(upperarmLeftX, y, upperarmRightX, y);
            add(upperLeftArm);
            double lowerarmEndY = y + LOWER_ARM_LENGTH;
            GLine lowerLeftArm = new GLine(upperarmLeftX, y, upperarmLeftX, lowerarmEndY);
            add(lowerLeftArm);		
	}
	
	private void drawRightArm(){
            double upperarmLeftX = getWidth()/2;
            double upperarmRightX = upperarmLeftX + UPPER_ARM_LENGTH;
            double y = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;;
            GLine upperRightArm = new GLine(upperarmLeftX, y, upperarmRightX, y);
            add(upperRightArm);
            double lowerarmEndY = y + LOWER_ARM_LENGTH;
            GLine lowerRightArm = new GLine(upperarmRightX, y, upperarmRightX, lowerarmEndY);
            add(lowerRightArm);
	}
	
	private void drawLeftLeg(){
            double hipLeftX = getWidth()/2 - HIP_WIDTH;
            double hipRightX = getWidth()/2;
            double hipY = getHeight()/2;
            GLine leftHip = new GLine(hipLeftX, hipY, hipRightX, hipY);
            add(leftHip);
            double legBottomY = hipY + LEG_LENGTH;
            GLine leftLeg = new GLine(hipLeftX, hipY, hipLeftX, legBottomY);
            add(leftLeg);
	}
	
	private void drawRightLeg(){
            double hipLeftX = getWidth()/2;
            double hipRightX = hipLeftX + HIP_WIDTH;
            double hipY = getHeight()/2;
            GLine rightHip = new GLine(hipLeftX, hipY, hipRightX, hipY);
            add(rightHip);
            double legBottomY = hipY + LEG_LENGTH;
            GLine rightLeg = new GLine(hipRightX, hipY, hipRightX, legBottomY);
            add(rightLeg);
	}
	
	private void drawLeftFoot(){
            double rightX = getWidth()/2 - HIP_WIDTH;
            double leftX = rightX - FOOT_LENGTH;
            double footY = getHeight()/2 + LEG_LENGTH;
            GLine leftFoot = new GLine(leftX, footY, rightX, footY);
            add(leftFoot);
	}
	
	private void drawRightFoot(){
            double leftX = getWidth()/2 + HIP_WIDTH;
            double rightX = leftX + FOOT_LENGTH;
            double footY = getHeight()/2 + LEG_LENGTH;
            GLine rightFoot = new GLine(leftX, footY, rightX, footY);
            add(rightFoot);
	}
	
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28; //from bottom of head
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}