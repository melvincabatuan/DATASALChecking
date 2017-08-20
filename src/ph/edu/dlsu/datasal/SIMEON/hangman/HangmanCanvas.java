/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.hangman;

/**
 *
 * @author Tiber
 */
import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
            removeAll();
            GLine gallows = new GLine(X,Y,X,Y-SCAFFOLD_HEIGHT);
            GLine beam = new GLine(X,Y-SCAFFOLD_HEIGHT,X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT);
            GLine rope = new GLine(X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT,X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH);
            add(gallows);
            add(beam);
            add(rope);
		/* You fill this in */
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
            if(text != null)
                remove(text);
            text = new GLabel(word, X+25, Y+25);
            text.setFont("COMIC SANS MS-30");
            add(text);
		/* You fill this in */
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
            lifes--;
            switch(lifes){
                case 8:
                    break;
                case 7:
                    GOval head = new GOval(X-HEAD_RADIUS+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH,HEAD_RADIUS*2,HEAD_RADIUS*2);
                    add(head);
                    break;
                case 6:
                    GLine body = new GLine(X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2,X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
                    add(body);
                    break;
                case 5:
                    GLine leftarm = new GLine(X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2,X+BEAM_LENGTH-72,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2);
                    GLine lefthand= new GLine(X+BEAM_LENGTH-72,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2,X+BEAM_LENGTH-72,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2+44);
                    add(leftarm); 
                    add(lefthand);
                    break;
                case 4:
                    GLine rightarm = new GLine(X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2,X+BEAM_LENGTH+72,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2);
                    GLine righthand= new GLine(X+BEAM_LENGTH+72,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2,X+BEAM_LENGTH+72,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+HEAD_RADIUS*2+44);
                    add(rightarm); 
                    add(righthand);
                    break;
                case 3:
                    GLine lefthip = new GLine(X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH,X+BEAM_LENGTH-HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
                    add(lefthip);
                    GLine leftleg = new GLine(X+BEAM_LENGTH-HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH,X+BEAM_LENGTH-HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
                    add(leftleg);
                    break;
                case 2:   
                    GLine righthip = new GLine(X+BEAM_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH,X+BEAM_LENGTH+HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
                    add(righthip);
                    GLine rightleg = new GLine(X+BEAM_LENGTH+HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH,X+BEAM_LENGTH+HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
                    add(rightleg);
                    break;
                case 1:
                    GLine rightfoot = new GLine(X+BEAM_LENGTH+HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH,X+BEAM_LENGTH+HIP_WIDTH+FOOT_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
                    add(rightfoot);
                    break;
                case 0:
                    GLine leftfoot = new GLine(X+BEAM_LENGTH-HIP_WIDTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH,X+BEAM_LENGTH-HIP_WIDTH-FOOT_LENGTH,Y-SCAFFOLD_HEIGHT+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
                    add(leftfoot);
                    break;
                default: 
                        break;    
                    
            }

             wrong=wrong+letter;
             sux = new GLabel(wrong,X+25, Y+50);
             sux.setFont("COMIC SANS MS-25");
             add(sux);
		/* You fill this in */
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
        private static final int HEIGHT = 400;
        private static final int WIDTH = 400;
        private double X = WIDTH/2 - BEAM_LENGTH;
        private double Y = HEIGHT/2 + SCAFFOLD_HEIGHT/2;
        private GLabel text = null;
        private String wrong = "";
        private GLabel sux = null;
        private int lifes=8;
}
