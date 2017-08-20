/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

package ph.edu.dlsu.datasal.parro.hangman;

import acm.graphics.*;
import java.awt.Color;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		drawScaffolding();
		result = "";
		createGLabels();
		wrongGuess = 0;
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		drawHiddenWord.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		wrongGuess++;
		drawBodyPart();
		updateWrongGuess(letter);
	}
	
	// tells program to draw a segment (uses switch statement)
	private void drawBodyPart() {
		switch (wrongGuess) {
		case 1: drawHead();
		break;
		case 2: drawBody();
		break;
		case 3: drawLeftArm();
		break;
		case 4: drawRightArm();
		break;
		case 5: drawLeftLeg();
		break;
		case 6: drawRightLeg();
		break;
		case 7: drawLeftFoot();
		break;
		case 8: drawRightFoot();
		break;
		}
	}
	
	// draws the head
	private void drawHead() {
		ropeY = y - SCAFFOLD_HEIGHT + ROPE_LENGTH;
		ropeX = x + BEAM_LENGTH;
		GOval head = new GOval(HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
		head.move(ropeX - HEAD_RADIUS, ropeY);
	}
	
	// draws the body
	private void drawBody() {
		bodyX = x + BEAM_LENGTH;
		bodyY = (y - SCAFFOLD_HEIGHT + ROPE_LENGTH) + HEAD_RADIUS * 2 + 1;
		GLine body = new GLine(bodyX, bodyY, bodyX, bodyY + BODY_LENGTH);
		add(body);
	}
	
	//draws the left arm
	private void drawLeftArm() {
		
		GLine leftArm = new GLine (bodyX, bodyY + ARM_OFFSET_FROM_HEAD, bodyX - UPPER_ARM_LENGTH, bodyY + ARM_OFFSET_FROM_HEAD);
		GLine leftHand = new GLine (bodyX - UPPER_ARM_LENGTH, bodyY + ARM_OFFSET_FROM_HEAD, bodyX - UPPER_ARM_LENGTH, bodyY + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(leftArm);
		add(leftHand);
	}
	
	//draws the right arm
	private void drawRightArm() {
		GLine rightArm = new GLine (bodyX, bodyY + ARM_OFFSET_FROM_HEAD, bodyX + UPPER_ARM_LENGTH, bodyY + ARM_OFFSET_FROM_HEAD);
		GLine rightHand = new GLine (bodyX + UPPER_ARM_LENGTH, bodyY + ARM_OFFSET_FROM_HEAD, bodyX + UPPER_ARM_LENGTH, bodyY + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(rightArm);
		add(rightHand);
	}
	
	//draws the left leg
	private void drawLeftLeg() {
		GLine leftThigh = new GLine(bodyX, bodyY + BODY_LENGTH, bodyX - (HIP_WIDTH), bodyY + BODY_LENGTH);
		GLine leftCalf = new GLine(bodyX - (HIP_WIDTH), bodyY + BODY_LENGTH, bodyX - (HIP_WIDTH), bodyY + BODY_LENGTH + LEG_LENGTH);
		add(leftThigh);
		add(leftCalf);
	}
	
	//draws the right leg
	private void drawRightLeg() {
		GLine rightThigh = new GLine(bodyX, bodyY + BODY_LENGTH, bodyX + (HIP_WIDTH), bodyY + BODY_LENGTH);
		GLine rightCalf = new GLine(bodyX + (HIP_WIDTH), bodyY + BODY_LENGTH, bodyX + (HIP_WIDTH), bodyY + BODY_LENGTH + LEG_LENGTH);
		add(rightThigh);
		add(rightCalf);
	}
	
	//draws the left foot
	private void drawLeftFoot() {
		GLine foot = new GLine(bodyX - (HIP_WIDTH), bodyY + BODY_LENGTH + LEG_LENGTH, bodyX - (HIP_WIDTH) - FOOT_LENGTH, bodyY + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}
	
	//draws the right foot
	private void drawRightFoot() {
		GLine foot = new GLine(bodyX + (HIP_WIDTH), bodyY + BODY_LENGTH + LEG_LENGTH, bodyX + (HIP_WIDTH) + FOOT_LENGTH, bodyY + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}
	
	//draws the scaffolding
	private void drawScaffolding() {
		canvasWidth = getWidth();
		canvasHeight = getHeight();
		x = canvasWidth / 2 - (BEAM_LENGTH / 2) - 30;
		y = canvasHeight / 2 + SCAFFOLD_HEIGHT /  2 - 20;
		GLine vertLine = new GLine (x, y, x, y - SCAFFOLD_HEIGHT);
		GLine beam = new GLine (x, y - SCAFFOLD_HEIGHT, x + BEAM_LENGTH, y - SCAFFOLD_HEIGHT);
		GLine rope = new GLine (x + BEAM_LENGTH, y - SCAFFOLD_HEIGHT, x + BEAM_LENGTH, y - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(vertLine);
		add(beam);
		add(rope);
	}
	
	//updates the graphical representation of wrong guesses so far
	private void updateWrongGuess(char guess) {
		result = result + " " + guess;
		drawWrongGuess.setLabel(""+result+"");
	}
	
	// creates GLabels for graphical representations of correct and incorrect guesses
	private void createGLabels() {
		int y = canvasHeight / 2 + SCAFFOLD_HEIGHT /  2;
		drawHiddenWord = new GLabel ("", (canvasWidth / 4), y + (canvasHeight - y) / 2);           
		drawWrongGuess = new GLabel("", ((canvasWidth / 4) * 3), y + (canvasHeight - y) / 2);
                printWrongLetters = new GLabel("Wrong Letters: ", ((canvasWidth / 4) * 3), y + (((canvasHeight - y) / 2) - 20 ));
                drawWrongGuess.setColor(Color.RED);
                add(printWrongLetters);
		add(drawHiddenWord);
		add(drawWrongGuess);
		drawHiddenWord.setFont("drawHiddenWord.getFont()-16");
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
	private int canvasWidth;
	private int canvasHeight;
	private GLabel drawHiddenWord; // graphical representation of the word guesses so far
	private GLabel drawWrongGuess; // graphical representation of wrong guesses so far (GLabel set to display "+result+"
        private GLabel printWrongLetters;
	private String result = ""; // String containing the wrong guesses (determines drawWrongGuess)
	private int wrongGuess = 0; // records the number of wrong guesses so far
	private int x; // scaffolding reference x point
	private int y; // scaffolding reference y point
	private int ropeX; // starting rope x coordinate
	private int ropeY; // starting rope y coordinate
	private int bodyX; // starting body x coordinate
	private int bodyY; // starting body y coordinate

}
