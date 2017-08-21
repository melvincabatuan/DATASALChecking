package datasal.Narag.hangman;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {


//reset display
	public void reset() {
		
		// Reset guessed letters string
		letters.setLabel("");
		i = 0;
		hangmanGraphic = new GCompound();
		hangmanGraphic.setLocation(WIDTH/2, 0);
		drawScaffold();
		add(hangmanGraphic);
	}

	public void displayWord(String word) {
		WordDisplay.setLabel(word);
		add(WordDisplay);
		
	}

	public void noteIncorrectGuess(char letter) {
	
		StringBuilder sb = new StringBuilder();
		
		String str = letters.getLabel();
		
		str += sb.append(letter);
		
		letters.setLabel(str);		
		add(letters);
		
		addBodyPart();		
	}
		
	private void drawScaffold() {
		double x1 = 0;
		double y1 = HEIGHT;
		double x2 = 0;
		double y2 = y1 - SCAFFOLD_HEIGHT;
		double x3 = BEAM_LENGTH;
		double y3 = y2;
		double x4 = x3;
		double y4 = y3 + ROPE_LENGTH;
		
		GLine scaffoldMast = new GLine(x1, y1, x2, y2);
		GLine scaffoldTop = new GLine(x2, y3, x3, y3);
		GLine scaffoldRope = new GLine(x3, y3, x4, y4);
		hangmanGraphic.add(scaffoldMast);
		hangmanGraphic.add(scaffoldTop);
		hangmanGraphic.add(scaffoldRope);
	}
	
	private void addBodyPart() {
		switch (i) {
		case 0:	GOval head = new GOval(BEAM_LENGTH - (HEAD_RADIUS / 2), HEIGHT - SCAFFOLD_HEIGHT + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				hangmanGraphic.add(head);
				add(hangmanGraphic);
				break;
		case 1: GLine body = new GLine(getWidth()/2.1 + UPPER_ARM_LENGTH/2 - HEAD_RADIUS*2, getHeight()/2 - BODY_LENGTH , getWidth()/2.1 + UPPER_ARM_LENGTH/2 - HEAD_RADIUS*2, getHeight()/2 + 30);
                                hangmanGraphic.add(body);
				add(hangmanGraphic);
                                break;
                case 2: double armStartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS*0.6;
                        double armEndX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - UPPER_ARM_LENGTH;
                        double upperArmHeightY = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
                        double lowerArmEndY = upperArmHeightY + LOWER_ARM_LENGTH;
                        GLine upperLeftArm = new GLine (armStartX, upperArmHeightY, armEndX, upperArmHeightY);
                        add(upperLeftArm);
                        GLine lowerLeftArm = new GLine (armEndX, upperArmHeightY, armEndX, lowerArmEndY);
                        add(lowerLeftArm);
                        break;
                case 3: 
                        double rightArmstartX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS*0.6;
                        double rightArmendX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + UPPER_ARM_LENGTH;
                        double rightUpperArmY = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
                        double rightLowerArmY = rightUpperArmY + LOWER_ARM_LENGTH;
                        GLine upperRightArm = new GLine (rightArmstartX, rightUpperArmY, rightArmendX, rightUpperArmY);
                        add(upperRightArm);
                        GLine lowerRightArm = new GLine (rightArmendX, rightUpperArmY, rightArmendX, rightLowerArmY);
                        add(lowerRightArm);
                        break;
                case 4:
                        double hipStartX = (getWidth()/2)*0.92 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
                        double hipEndX = hipStartX - HIP_WIDTH;
                        double hipHeightY = (getHeight()/2)*1.14;
                        GLine leftHip = new GLine(hipStartX, hipHeightY, hipEndX, hipHeightY);
                        add(leftHip);
                        double leftLegY = hipHeightY + LEG_LENGTH;
                        GLine leftLeg = new GLine(hipEndX, hipHeightY, hipEndX, leftLegY);
                        add(leftLeg);
                        break;
                case 5:
                        double rhipStartX = getWidth()/2 * 0.92 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
                        double rhipEndX = rhipStartX + HIP_WIDTH;
                        double rhipHeightY = (getHeight()/2)*1.14;
                        double leftLegEndY = (getHeight()/2)*1.14 + LEG_LENGTH;
                        GLine rightHip = new GLine(rhipStartX, rhipHeightY, rhipEndX, rhipHeightY);
                        add(rightHip);
                        GLine rightLeg = new GLine(rhipEndX, rhipHeightY, rhipEndX, leftLegEndY);
                        add(rightLeg);
                        break;
                case 6:
                        double lfootStartX = (getWidth()/2)*0.92 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - HIP_WIDTH;
                        double lfootEndX = lfootStartX - FOOT_LENGTH;
                        double footHeight = (getHeight()/2)*1.14;
                        GLine leftFoot = new GLine (lfootStartX, footHeight+112, lfootEndX, footHeight+112);
                        add(leftFoot);
                    break;
                case 7:
                        double rfootStartX = (getWidth()/2)*0.92 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + HIP_WIDTH;
                        double rfootEndX = rfootStartX + FOOT_LENGTH;
                        double rootHeight = (getHeight()/2)*1.14;
                        GLine rtFoot = new GLine (rfootStartX, rootHeight+112, rfootEndX, rootHeight+112);
                        add(rtFoot);
                    break;
                case 8:
                    break;
		}
		i++;
	}
	
	private final int WIDTH = 200;
	private final int HEIGHT = 400;
		
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
	private static final int WORD_LABEL_X = 10;
	private final int WORD_LABEL_Y = HEIGHT - 10;

	private GCompound hangmanGraphic;
	private GLabel WordDisplay = new GLabel("", 50, 450); 
	private GLabel letters = new GLabel("", 50, 465);
	private int i = 0; 

}
