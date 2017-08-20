/*
 * File: Breakout.java
 * -------------------
 * Name: Vladimir II Christian R. Ocampo
 * Section Leader: ---
 * 
 * This file will eventually implement the game of Breakout.
 */
package ph.edu.dlsu.datasal.ocampo.breakout;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;
import ph.edu.dlsu.datasal.ocampo.myarraylist.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
        
        /** Objects */
        private GRect brick;
        private GOval ball = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS);
        private GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        private GLabel caption = new GLabel("Click to start");
        private GLabel scoreLabel = new GLabel("0");
        private MyList<String> topList = new MyList<String>();
        private MyList<Integer> scoreList = new MyList<Integer>();
        private GLabel listElement;
        private GLabel ans = new GLabel(null);

        // Game parameters
        private RandomGenerator rgen = RandomGenerator.getInstance();
        private double vx;
        private double vy;
        private final double v = Math.sqrt(2)*3.0;
        private int score = 0;
        private int turns = NTURNS;
        private String name;

        private double getXCenter(GObject obj) {
            return (WIDTH-obj.getWidth())/2;
        }
        
        private double getYCenter(GObject obj) {
            return (HEIGHT-obj.getHeight())/2;
        }
        
        public double getWindowWidth() {
            return WIDTH;
        }
        
        public double getWindowHeight() {
            return HEIGHT;
        }
        
        /** Bricks bricks bricks */
        private double computeXOffset() {
            return (WIDTH - NBRICKS_PER_ROW*BRICK_WIDTH - (NBRICKS_PER_ROW-1)*BRICK_SEP)/2;
        }
        
        private void drawBrick(int row, int col) { 
            brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
            brick.setFilled(true);
            setColor(brick, row);
            if(getElementAt(computeXOffset()+col*(BRICK_WIDTH+BRICK_SEP), BRICK_Y_OFFSET+row*(BRICK_HEIGHT+BRICK_SEP))==null)
                add(brick, computeXOffset()+col*(BRICK_WIDTH+BRICK_SEP), BRICK_Y_OFFSET+row*(BRICK_HEIGHT+BRICK_SEP));
        }
        
        private void drawBrickWall() {
            for(int i=0; i<NBRICK_ROWS; i++) {
                for(int j=0; j<NBRICKS_PER_ROW; j++) {
                    drawBrick(i, j);
                }
            }
        }
        
        private void setColor(GObject obj, int row) {
            switch(row) {
                case 0:
                case 1:
                    obj.setColor(Color.RED); break;
                case 2:
                case 3:
                    obj.setColor(Color.ORANGE); break;
                case 4:
                case 5:
                    obj.setColor(Color.YELLOW); break;
                case 6:
                case 7:
                    obj.setColor(Color.GREEN); break;
                case 8:
                case 9:
                    obj.setColor(Color.CYAN); break;
            }
        }
        
        /** Paddle paddle */
        private void drawPaddle() {
            paddle.setFilled(true);
            add(paddle, (WIDTH-PADDLE_WIDTH)/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_WIDTH);
        }
        
        private boolean isGridEmpty() {
            boolean res = true;
            
            for(int i=0; i<NBRICK_ROWS; i++)
            {
                for(int j=0; j<NBRICKS_PER_ROW; j++)
                {
                    if(getElementAt(computeXOffset()+j*(BRICK_WIDTH+BRICK_SEP), BRICK_Y_OFFSET+i*(BRICK_HEIGHT+BRICK_SEP))!=null) {
                        res = false; break;
                    }
                }
            }
            return res;
        }
        
        // Paddle in window
        private boolean isMouseinWindow(int x) {
            return (x>PADDLE_WIDTH/2 && x<WIDTH-PADDLE_WIDTH/2);
        }
        
        // Play sound
        private void playSound(String filename) {
            // File - InputStream - AudioStream - AudioPlayer.player.start(AudioStream name)
            File soundfile = new File(filename); // input file
            try {
                InputStream filestream = new FileInputStream(soundfile.getPath());
                try {
                    // Let the computer retreive its path
                    AudioStream audio = new AudioStream(filestream);
                    // Start playing the audio file
                    AudioPlayer.player.start(audio);
                            } catch (IOException ex) {
                    // Logger.getLogger(Breakout.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                // Logger.getLogger(Breakout.class.getName()).log(Level.SEVERE, null, ex);
                println("Audio File not found");
            }
        }
        
        // Captions
        private void drawLabels() {
            caption.setLabel("Click to start");
            caption.setColor(Color.RED);
            caption.setFont("SansSerif-30");
            add(caption, getXCenter(caption), getYCenter(caption));
            scoreLabel.setFont("SansSerif-15");
            scoreLabel.setColor(Color.BLUE);
            scoreLabel.setLabel(Integer.toString(score));
            add(scoreLabel, 0, scoreLabel.getHeight());
        }
        
        // List functions
        // --------------
        public void listDisplay() {
            removeAll();
            for(int i=1; i<=scoreList.size(); i++) {
                listElement = new GLabel(i+": "+topList.get(i)+" - "+Integer.toString(scoreList.get(i)));
                listElement.setFont("SansSerif-20");
                listElement.setColor(Color.BLUE);
                add(listElement, getXCenter(listElement), i*listElement.getHeight());
            }
        }
        
        private int getListPos() {
            int i=1;
            for(i=1; i<=scoreList.size(); i++) {
                if(scoreList.get(i)<score) break;
            }
            return i;
        }
        
        public void lister() {
            if(topList.isEmpty()) {
                topList.add(1, name);
            }
            else {
                if(topList.size()==5) topList.remove(getListPos());
                topList.add(getListPos(), name);
            }
            if(scoreList.isEmpty()) scoreList.add(1, score);
            else {
                if(scoreList.size()==5) scoreList.remove(getListPos());
                scoreList.add(getListPos(), score);
            }
        }
        
        
        private void resetGame() {
            if(turns==0 || isGridEmpty()) {
                drawBrickWall();
                drawPaddle();
                turns = NTURNS;
                score = 0;
            }
            vx = rgen.nextDouble(1.0, 3.0);
            vy = Math.sqrt(v*v-vx*vx);

            // Draw ball;
            ball.setFilled(true);
            add(ball, getXCenter(ball), getYCenter(ball));
            
            drawLabels();
        }
        
	/* Method: init() */
	/** Sets up the Breakout program. */
        @Override
	public void init() {
            resetGame();
            addMouseListeners();
	}
        
	/* Method: run() */
	/** Runs the Breakout program. */
        @Override
	public void run() {
            scoreList.createList();
            topList.createList();
            
            while(true) {
                resetGame();
                waitForClick();
                remove(caption);
                
                // Gameplay
                while(true) {
                    // Display
                    scoreLabel.setLabel(Integer.toString(score));
                    // Ball bounce of walls
                    if(ball.getX()+2*BALL_RADIUS > WIDTH || ball.getX() < 0) vx = -vx;
                    if(ball.getY() < 0) vy = -vy;
                    if(ball.getY()+2*BALL_RADIUS > HEIGHT) {
                        turns--;
                        if(turns>0) {
                            if(turns>1) caption.setLabel(turns+" turns left");
                            else caption.setLabel(turns+" turn left");
                        }
                        else caption.setLabel("Game over");
                        add(caption, getXCenter(caption), getYCenter(caption));
                        break;
                    }
                    
                    // Ball hits OBJECTS
                    GObject cobj = getObjectCollided();
                    
                    if(cobj!=null) {
                        // ball.getY()+2*BALL_RADIUS < paddle.getY()-1
                        if(cobj!=paddle) {
                            if(cobj!=scoreLabel) {
                                playSound("bounce.wav");
                                if(cobj.getColor()==Color.RED) score+=50.0;
                                else if(cobj.getColor()==Color.ORANGE) score+=40;
                                else if(cobj.getColor()==Color.YELLOW) score+=30;
                                else if(cobj.getColor()==Color.GREEN) score+=20;
                                else if(cobj.getColor()==Color.CYAN) score+=10;
                                remove(cobj);
                                // Horizontal bounce across bricks
                                if(getElementAt(ball.getX()-1, ball.getY()+BALL_RADIUS)!=null 
                                        || getElementAt(ball.getX()+2*BALL_RADIUS+1, ball.getY()+BALL_RADIUS)!=null) {
                                    if(vx>0) vx = -rgen.nextDouble(1.0, 3.0);
                                    else if(vx<0) vx = rgen.nextDouble(1.0, 3.0);
                                }
                                else {
                                    if(vx>0) vx = rgen.nextDouble(1.0, 3.0);
                                    else if(vx<0) vx = -rgen.nextDouble(1.0, 3.0);
                                }
                                // Vertical bounce
                                if(vy>0) vy = -Math.sqrt(v*v-vx*vx);
                                else if(vy<0) vy = Math.sqrt(v*v-vx*vx);
                            }
                        }
                        else {
                            vy = -Math.sqrt(v*v-vx*vx);
                        }
                    }
                    

                    ball.move(vx, vy);
                    //paddle.move(vx, 0.0);
                    
                    if(isGridEmpty()) {
                       caption.setLabel("You win!");
                       add(caption, getXCenter(caption), getYCenter(caption));
                       break;
                    }

                    pause(8.0);
                }

                waitForClick();
                if(turns==0 || isGridEmpty()) {
                    if(scoreList.size()<=5) lister();
                    else {
                        if(getListPos()<=5) lister();
                    }
                    listDisplay();
                    waitForClick();
                    removeAll();
                }
            }
                    
	}
        
        private GObject getObjectCollided() {
            GObject coll=null;
            
            if(getElementAt(ball.getX(), ball.getY())!=null)
                coll = getElementAt(ball.getX(), ball.getY());
            if(getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY())!=null)
                coll = getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY());
            if(getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS)!=null)
                coll = getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS);
            if(getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=null)
                coll = getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS);
            
            return coll;
        }
        
        
        public void mouseMoved(MouseEvent e) {
            
            if(isMouseinWindow(e.getX())) {
                paddle.setLocation(e.getX()-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_WIDTH);
            }
        }
        
}
