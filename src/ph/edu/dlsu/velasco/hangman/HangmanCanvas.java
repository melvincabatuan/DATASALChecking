package ph.edu.dlsu.velasco.hangman;
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

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
        
        GRect Scaffold = new GRect(5,SCAFFOLD_HEIGHT);
        GRect Beam = new GRect(BEAM_LENGTH,5);
        GRect Rope = new GRect(5,ROPE_LENGTH);
        GOval HeadOut = new GOval(2*HEAD_RADIUS,2*HEAD_RADIUS);
        GRect Body = new GRect(6,BODY_LENGTH);
        GRect LeftArm1 = new GRect(UPPER_ARM_LENGTH,4);
        GRect LeftArm2 = new GRect(4,LOWER_ARM_LENGTH);
        GRect RightArm1 = new GRect (UPPER_ARM_LENGTH,4);
        GRect RightArm2 = new GRect(4,LOWER_ARM_LENGTH);
        GRect LeftHip = new GRect (HIP_WIDTH,4);
        GRect LeftLeg = new GRect (4,LEG_LENGTH);
        GRect LeftFoot = new GRect (FOOT_LENGTH,4);
        GRect RightHip = new GRect (HIP_WIDTH,4);
        GRect RightLeg = new GRect (4,LEG_LENGTH);
        GRect RightFoot = new GRect (FOOT_LENGTH,4);
        
/** Resets the display so that only the scaffold appears */
	public void reset() {
            removeAll();
            Scaffold.setFilled(true);
            add(Scaffold,75,50);
            Beam.setFilled(true);
            add(Beam,75,50);
            Rope.setFilled(true);
            add(Rope,75+BEAM_LENGTH,50);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
        
	public void displayWord(char[] word, int length){
            for (int i=0; i<length; i++){
                if(getElementAt(100+(20*i),475) != null ){
                    remove(getElementAt(100+(20*i),475));
                }
            }
            for (int i=0; i<length; i++){
                GLabel Word = new GLabel("");
                Word.setLabel(""+ word[i]);
                Word.setFont("MONTSERRAT-25");
                add(Word,75+(20*i),475);
            }
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char[] letter, int length) {
            for(int j=0; j<length; j++){
                GLabel Incor = new GLabel(""+letter[j]);
                Incor.setColor(Color.MAGENTA);
                Incor.setFont("MONTSERRAT-20");
                add(Incor,90+(18*j),550);
            }
	}
        
        public void BlurEffect(){
            
        }
        
        public void congrats(){
            GLabel cong = new GLabel("Congratulations!");
            cong.setFont("MONTSERRAT-40");
            cong.setColor(Color.RED);
            add(cong,25,200);
        }
        
        public void GameOver(String word){
            GLabel gamo = new GLabel("Game Over!");
            gamo.setFont("MONTSERRAT-40");
            gamo.setColor(Color.RED);
            add(gamo,75,200);
            GLabel correct = new GLabel(word);
            correct.setFont("MONTSERRAT-20");
            correct.setColor(Color.RED);
            add(correct,125,500);
        }
        
        public void IncorrectLetter(int lives){
            switch(lives){
                case 7: 
                    HeadOut.setFilled(true);
                    add(HeadOut,(Rope.getX()-HEAD_RADIUS+2.5),Rope.getY()+ROPE_LENGTH);
                    break;
                case 6:
                    Body.setFilled(true);
                    add(Body,HeadOut.getX()+HEAD_RADIUS-3,HeadOut.getY()+(2*HEAD_RADIUS));
                    break;
                case 5:
                    LeftArm1.setFilled(true);
                    add(LeftArm1,Body.getX()-UPPER_ARM_LENGTH,HeadOut.getY()+(2*HEAD_RADIUS)+ARM_OFFSET_FROM_HEAD);
                    break;
                case 4:
                    RightArm1.setFilled(true);
                    add(RightArm1,Body.getX()+6,HeadOut.getY()+(2*HEAD_RADIUS)+ARM_OFFSET_FROM_HEAD);
                    break;
                case 3:
                    LeftArm2.setFilled(true);
                    add(LeftArm2,LeftArm1.getX(),LeftArm1.getY()+4);
                    break;
                case 2:
                    RightArm2.setFilled(true);
                    add(RightArm2,RightArm1.getX()+UPPER_ARM_LENGTH-4,RightArm1.getY()+4);
                    break;
                case 1:
                    LeftHip.setFilled(true);
                    LeftLeg.setFilled(true);
                    LeftFoot.setFilled(true);
                    add(LeftHip,Body.getX()-HIP_WIDTH+3,Body.getY()+BODY_LENGTH);
                    add(LeftLeg,LeftHip.getX(),LeftHip.getY()+4);
                    add(LeftFoot,LeftLeg.getX()-FOOT_LENGTH,LeftLeg.getY()+LEG_LENGTH-4);
                    break;
                case 0:
                    RightHip.setFilled(true);
                    RightLeg.setFilled(true);
                    RightFoot.setFilled(true);
                    add(RightHip,Body.getX()+3,Body.getY()+BODY_LENGTH);
                    add(RightLeg,RightHip.getX()+HIP_WIDTH-4,RightHip.getY()+4);
                    add(RightFoot,RightLeg.getX()+4,RightLeg.getY()+LEG_LENGTH-4);
                    break;
            }
        }


}