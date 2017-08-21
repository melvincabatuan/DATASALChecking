/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */
package ph.edu.dlsu.kitane.hangman;
import ph.edu.dlsu.kitane.linkedlist.MyLinkedList;
import acm.graphics.*;
import java.awt.Color;
import java.util.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
                removeAll();
                guess=0;
                letters.createList();
                if(letters.isEmpty()!=true){
                    for(int i=1;i<=8;i++){
                        letters.remove(i);
                    }
                }
                
                //Scaffold
                scaffold = new GRect(10,SCAFFOLD_HEIGHT);
                scaffold.setFilled(true);
                scaffold.setLocation(APPLICATION_WIDTH/4,SCAFFOLD_HEIGHT/10);
                add(scaffold);
                
                //Beam
                beam = new GRect(BEAM_LENGTH,10);
                beam.setFilled(true);
                beam.setLocation(APPLICATION_WIDTH/4,SCAFFOLD_HEIGHT/10);
                add(beam);
                
                //ROPE LENGTH
                rope = new GRect(0,ROPE_LENGTH);
                rope.setFilled(true);
                rope.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH,(SCAFFOLD_HEIGHT/10)+10);
                add(rope);
                
                //Head
                head = new GOval(HEAD_RADIUS,HEAD_RADIUS);
                head.setLocation(((APPLICATION_WIDTH/4)+BEAM_LENGTH-HEAD_RADIUS/2),(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH);
                
                
                //Body
                body = new GRect(0,BODY_LENGTH);
                body.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS);
                
                
                //Arms
                uArmR = new GRect(UPPER_ARM_LENGTH,0);
                uArmR.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
                
                uArmL = new GRect(UPPER_ARM_LENGTH,0);
                uArmL.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH-UPPER_ARM_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
                
                lArmR = new GRect(0,LOWER_ARM_LENGTH);
                lArmR.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH+UPPER_ARM_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
                
                lArmL = new GRect(0,LOWER_ARM_LENGTH);
                lArmL.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH-UPPER_ARM_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
                
                
                //Legs
                hipR = new GRect(HIP_WIDTH,0);
                hipR.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+BODY_LENGTH);
                
                hipL = new GRect(HIP_WIDTH,0);
                hipL.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH-HIP_WIDTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+BODY_LENGTH);
                
                legR = new GRect(0,LEG_LENGTH);
                legR.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH+HIP_WIDTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+BODY_LENGTH);
                
                legL = new GRect(0,LEG_LENGTH);
                legL.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH-HIP_WIDTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+BODY_LENGTH);
                
                
                //Feet
                rFoot = new GRect(FOOT_LENGTH,0);
                rFoot.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH+HIP_WIDTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
                
                lFoot = new GRect(FOOT_LENGTH,0);
                lFoot.setLocation((APPLICATION_WIDTH/4)+BEAM_LENGTH-HIP_WIDTH-FOOT_LENGTH,(SCAFFOLD_HEIGHT/10)+10+ROPE_LENGTH+HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
                
                //Wrong Letters
                GLabel wrongLetters = new GLabel("Wrong Letters: ",APPLICATION_WIDTH/3-110,432);
                wrongLetters.setFont("Arial-14-Bold");
                wrongLetters.setColor(Color.magenta);
                add(wrongLetters);
                
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
                remove(wrong);
                str.setLabel(word);
                str.setColor(Color.blue);
                str.setFont("Arial-Bold-24");
                add(str);
	}
        private void displayList(MyLinkedList list) {
		for(int i = 1; i <= list.size(); i++){
                   GLabel item = new GLabel(list.get(i)+" ");
                   item.setFont("Arial-14-Bold");
                   item.setColor(Color.magenta);
                   add(item, APPLICATION_WIDTH/3 + i * (16),APPLICATION_HEIGHT-100+str.getHeight()+wrong.getHeight()+item.getHeight());
                }
                
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
                wrong.setColor(Color.black);
                wrong.setFont("Arial-14");
                wrong.setLabel("The word does not contain the letter "+letter);
                wrong.setLocation(APPLICATION_WIDTH/4,APPLICATION_HEIGHT-100+str.getHeight());
                add(wrong);
                guess++;
                letters.add(guess, ""+letter);
                displayList(letters);
                if(guess==1){
                    add(head);
                }else if(guess==2){
                    add(body);
                }else if(guess==3){
                    add(uArmR);
                    add(lArmR);
                }else if(guess==4){
                    add(uArmL);
                    add(lArmL);
                }else if(guess==5){
                    add(hipR);
                    add(legR);
                }else if(guess==6){
                    add(hipL);
                    add(legL);
                }else if(guess==7){
                    add(rFoot);
                }else if(guess==8){
                    add(lFoot);
                }
	}

/**
 * Prints out Game Over in the screen.
 */        
        public void gameOver(){
            wrong.setColor(Color.RED);
            wrong.setFont("Arial-Bold-30");
            wrong.setLabel("GAMEOVER!");
            wrong.setLocation(APPLICATION_WIDTH/4,APPLICATION_HEIGHT-100+str.getHeight());
            add(wrong);
        }
        public void congrats(){
            wrong.setColor(Color.RED);
            wrong.setFont("Arial-Bold-26");
            wrong.setLabel("CONGRATULATIONS!");
            wrong.setLocation(APPLICATION_WIDTH/4,APPLICATION_HEIGHT-100+str.getHeight());
            add(wrong);
        }

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 260;
	private static final int BEAM_LENGTH = 100;
	private static final int ROPE_LENGTH = 25;
	private static final int HEAD_RADIUS = 52;
	private static final int BODY_LENGTH = 100;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 60;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 70;
	private static final int FOOT_LENGTH = 28;
        
        //Canvas Size
        private static final int APPLICATION_WIDTH = 377;
        private static final int APPLICATION_HEIGHT = 469;
        public static final int WIDTH = APPLICATION_WIDTH;
        public static final int HEIGHT = APPLICATION_HEIGHT;
        private int guess = 0;
        
        //Hangman Noose
        private GRect scaffold;
        private GRect beam;
        private GRect rope;
        
        //Human Parts
        private GOval head;
        private GRect body;
        private GRect uArmR;
        private GRect uArmL;
        private GRect lArmR;
        private GRect lArmL;
        private GRect hipR;
        private GRect hipL;
        private GRect legR;
        private GRect legL;
        private GRect rFoot;
        private GRect lFoot;
        
        //Words
        private GLabel str = new GLabel("",APPLICATION_WIDTH/4,APPLICATION_HEIGHT-100);
        private GLabel wrong = new GLabel("",APPLICATION_WIDTH/4,APPLICATION_HEIGHT-100+str.getHeight());
        private MyLinkedList<String> letters = new MyLinkedList<String>();
}
