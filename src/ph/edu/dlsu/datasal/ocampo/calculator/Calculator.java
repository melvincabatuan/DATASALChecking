package ph.edu.dlsu.datasal.ocampo.calculator;
import java.awt.*;
import java.util.*;
import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;

public class Calculator extends GraphicsProgram {

    /** Calculator GUI parameters */
    private static final int BUTTON_SIZE = 60;
    private static final int OFFSET_GAP = 5;
    private static final int DISPLAY_HEIGHT = 30;
    
    public static final int CALCULATOR_WIDTH = 4*BUTTON_SIZE + OFFSET_GAP;
    public static final int CALCULATOR_HEIGHT = 5*BUTTON_SIZE + DISPLAY_HEIGHT + OFFSET_GAP;
    
    private GRect[][] numericalKeypad = new GRect[4][3];
    private GRect[] operatorKeypad = new GRect[4];
    private GRect equalsButton = new GRect(3*BUTTON_SIZE, BUTTON_SIZE);
    
    /** Calculator display fields */
    private String calcDisplayString = "";
    private GLabel calcDisplay = new GLabel("0");
    
    /** Calculator input fields */
    private char[][] numericArray = { {'1', '2', '3'},
        {'4', '5', '6'},
        {'7', '8', '9'},
        {'0', '.', 'C'},
    };
    private char[] operatorArray = {'+', '-', '*', '/'};
    
    private int[] clickPos = new int[2];    // Click pos
    
    /** Calculator Function parameters */
    private String inString = "";
    private String ans = "";
    private CalculatorIO calc = new CalculatorIO("0");
    private boolean inputMode = true;
    
    /** Calculator display methods */
    private void displayInCalculator(String str) {
        if(str.equals("")) calcDisplay.setLabel("0");
        else calcDisplay.setLabel(str);
    }
    
    /** Other methods */
    private char getNumericValue(int r, int c) {
        return numericArray[r][c];
    }
    public char getOperatorValue(int index) {
        return operatorArray[index];
    }
    
    // Placing objB in the center of objA
    private double getXCenterIn(GObject objA, GObject objB) {
        return Math.abs(objA.getWidth()-objB.getWidth())/2;
    }
    
    private double getYCenterIn(GObject objA, GObject objB) {
        return Math.abs(objA.getHeight()-objB.getHeight())/2;
    }
    
    public void drawNumericalKeypad() {
        int i=0, j=0;
        
        for(i=0; i<4; i++) {
            for(j=0; j<3; j++) {
                numericalKeypad[i][j] = new GRect(BUTTON_SIZE, BUTTON_SIZE);
                add(numericalKeypad[i][j], BUTTON_SIZE*j, DISPLAY_HEIGHT+BUTTON_SIZE*i);
                GLabel label = new GLabel(Character.toString(numericArray[i][j]));
                add(label, 
                        j*BUTTON_SIZE + getXCenterIn(numericalKeypad[i][j], label), 
                        DISPLAY_HEIGHT + i*BUTTON_SIZE + getYCenterIn(numericalKeypad[i][j], label));
            }
        }
    }
    
    public void drawOperatorKeypad() {
        for(int i=0; i<4; i++) {
            operatorKeypad[i] = new GRect(BUTTON_SIZE, BUTTON_SIZE);
            add(operatorKeypad[i], 3*BUTTON_SIZE+OFFSET_GAP, DISPLAY_HEIGHT+BUTTON_SIZE*i);
            GLabel operator = new GLabel(Character.toString(operatorArray[i]));
            add(operator, 
                    3*BUTTON_SIZE + OFFSET_GAP + getXCenterIn(operatorKeypad[i], operator), 
                    DISPLAY_HEIGHT + i*BUTTON_SIZE + getYCenterIn(operatorKeypad[i], operator));
        }
        GLabel equals = new GLabel("=");
        add(equals, getXCenterIn(equalsButton, equals), DISPLAY_HEIGHT+4*BUTTON_SIZE+OFFSET_GAP+getYCenterIn(equalsButton, equals));
    }
    
    /** Calculator function methods */
    private void cPressed() {
        calc.clearErrors();
        ans = "";
        inString = ans;
        displayInCalculator(ans);
        inputMode = true;
    }
    
    /** Program run */
    public void setUp() {
        // Draw the calculator
        drawNumericalKeypad();
        drawOperatorKeypad();
        add(calcDisplay, 2, calcDisplay.getHeight());
        add(equalsButton, 0, DISPLAY_HEIGHT+4*BUTTON_SIZE+OFFSET_GAP);
        // Set Properties
        int i=0, j=0;
        for(i=0; i<4; i++) {
            for(j=0; j<3; j++) {
                numericalKeypad[i][j].setFillColor(Color.BLUE);
                operatorKeypad[i].setFillColor(Color.BLUE);
            }
        }
        equalsButton.setFillColor(Color.BLUE);
        
        addMouseListeners();
    }
    
    public void run() {
        setUp();
        while(true) {
            pause(20);
        }
    }
    
    /** Mouse events */
    private boolean numericButtonPressed(int x, int y) {
        return (x>0 && x<=3*BUTTON_SIZE &&
                y>DISPLAY_HEIGHT && y<CALCULATOR_HEIGHT-BUTTON_SIZE-OFFSET_GAP &&
                !isCPressed(x, y));
    }
    
    private boolean operatorButtonPressed(int x, int y) {
        return (x>3*BUTTON_SIZE &&  x<CALCULATOR_WIDTH &&
                y>DISPLAY_HEIGHT && y<CALCULATOR_HEIGHT-BUTTON_SIZE-OFFSET_GAP);
    }
    
    private boolean equalButtonPressed(int x, int y) {
        return (x>0 && x<3*BUTTON_SIZE &&
                y>CALCULATOR_HEIGHT-BUTTON_SIZE && y<CALCULATOR_HEIGHT);
    }
    
    private boolean isCPressed(int x, int y) {
        return (x>2*BUTTON_SIZE && x<3*BUTTON_SIZE &&
                y>DISPLAY_HEIGHT+3*BUTTON_SIZE && y<DISPLAY_HEIGHT+4*BUTTON_SIZE);
    }
    
    public void mousePressed(MouseEvent me) {
        int nX, nY;
        if(me.getY()>DISPLAY_HEIGHT && me.getY()<CALCULATOR_HEIGHT-BUTTON_SIZE-OFFSET_GAP) {
            if(me.getX()>0 && me.getX()<=3*BUTTON_SIZE) {
                // Numeric button press
                nX = me.getX()/BUTTON_SIZE;
                nY = (me.getY()-DISPLAY_HEIGHT)/BUTTON_SIZE;
                clickPos[0] = nX;
                clickPos[1] = nY;
                numericalKeypad[nY][nX].setFilled(true);
            }
            else if(me.getX()>3*BUTTON_SIZE && me.getX()<CALCULATOR_WIDTH) {
                // Operator button press
                int opPos = (me.getY()-DISPLAY_HEIGHT)/BUTTON_SIZE;
                operatorKeypad[opPos].setFilled(true);
                clickPos[0] = opPos;
                clickPos[1] = 0;
            }
        }
        else {
            // Equals button pressed
            if(me.getY()>CALCULATOR_HEIGHT-BUTTON_SIZE && me.getY()<CALCULATOR_HEIGHT && me.getX()>0 && me.getX()<3*BUTTON_SIZE)
                equalsButton.setFilled(true);
        }
    }
    
    public void mouseReleased(MouseEvent me) {
        for(int i=0; i<4; i++) {
            for(int j=0; j<3; j++) {
                operatorKeypad[i].setFilled(false);
                numericalKeypad[i][j].setFilled(false);
            }
        }
        equalsButton.setFilled(false);
    }
    
    public void mouseClicked(MouseEvent me) {
        if(numericButtonPressed(me.getX(), me.getY()) && !calc.isErrorIncurred()) {
            if(!inputMode) {
                inString = "";
                inputMode = true;
            }
            inString += Character.toString(getNumericValue(clickPos[1], clickPos[0]));
            displayInCalculator(inString);
        }
        else if(operatorButtonPressed(me.getX(), me.getY()) && !calc.isErrorIncurred()) {
            if(!inputMode) {
                inString = calc.isErrorIncurred()?"":ans;
                inputMode = true;
            }
            inString += Character.toString(getOperatorValue(clickPos[0]));
            displayInCalculator(inString);
        }
        else if(equalButtonPressed(me.getX(), me.getY())) {
            calc.setInput(inString);
            ans = calc.displayOutput();
            displayInCalculator(ans);
            inputMode = false;
        }
        else if(isCPressed(me.getX(), me.getY())) cPressed();
    }
    
    
} // end of class
