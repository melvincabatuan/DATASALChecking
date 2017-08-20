/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.breakout;
/**
 * Ang, Ryan Jasper V.
 * 11428422
 * LBYCP12-EQ1
 * 2017-05-24
 */
import acm.graphics.*;
import acm.program.*;
import acm.util.*;


import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;



public class BreakoutAng extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 200;
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
	private static final int BALL_RADIUS = 20;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
        
        /**Ball velocity*/	
	private double vx, vy;
	
/**Random number generator for vx*/	
	private RandomGenerator rgen = RandomGenerator.getInstance();
        
        /** Animation delay or paust time between ball moves */	
	private static final int DELAY = 8;
        
        private int brickCounter = 100;
        
        int liveScore = 0;
        
    
    // MediaTools class and its AudioClip method add a sound effect for ball collisions
    /*AudioClip bounceClip = MediaTools.loadAudioClip("bounce.wav");*/

        public static void main(String args[]){
    new BreakoutAng().start();
    }

        
	public void run() {
		/* You fill this in, along with any subsidiary methods */
                highscore.createList();
                drawBrickWall();	
                for(int i=0; i < NTURNS; i++) {
			
                        drawPaddle();
                        drawBall();
                        drawSB();
			playGame();
                        
			if(brickCounter == 0) {
				ball.setVisible(false);
				printWinner();
                                LB();
				break;
			}
			if(brickCounter > 0) {
				//removeAll();
                                remove(ball);
                                remove(paddle);
			}
		}
		if(brickCounter > 0) {
			printGameOver();
                        LB();
		}
	}
              
	

        private void drawBrickWall() {
for (int i = 0; i < NBRICK_ROWS; i++)
for (int j = 0; j < NBRICKS_PER_ROW; j++)
drawBrick(i,j);
}
   
int rowe;
        
private void drawBrick(int row, int col){
double x, y; // brick location
GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
x = computeXOffset() + col * (BRICK_WIDTH +
BRICK_SEP);
y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT +
BRICK_SEP);
brick.setFilled(true);
switch (row) {
case 0:
case 1:
brick.setFillColor(Color.RED);
break;
case 2:
case 3:
brick.setFillColor(Color.ORANGE);
break;
case 4:
case 5:
brick.setFillColor(Color.YELLOW);
break;
case 6:
case 7:
brick.setFillColor(Color.GREEN);
break;
case 8:
case 9:
brick.setFillColor(Color.CYAN);
}
add(brick, x, y);
rowe = row;
}

private double computeXOffset() {
return (0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) *
BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW));
}
    
private GRect paddle;
    
    
private void drawPaddle() {
    double x = getWidth()/2 - PADDLE_WIDTH/2;     
    double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
    paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add (paddle);
		addMouseListeners();
}
    
private boolean isMouseXInsideWindow(int x){
return ((x > 0.5 * PADDLE_WIDTH)
&& (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
}



private GOval ball = new GOval(2*BALL_RADIUS,
2*BALL_RADIUS);

private void drawBall(){
ball.setFilled(true);
add(ball, 0.5*WIDTH - BALL_RADIUS , .5*HEIGHT -
BALL_RADIUS );
}

public void mouseMoved(MouseEvent me){
if (isMouseXInsideWindow(me.getX()))
paddle.setLocation(me.getX()-0.5 * PADDLE_WIDTH,
paddle.getY());
}

private void playGame() {
		waitForClick();
		getBallVelocity();
		while (true) {
			moveBall();
			if (ball.getY() >= getHeight()) {
				break;
			}
			if(brickCounter == 0) {
				break;
			}
		}
	}
	
	
	
	private void getBallVelocity() {
		vy = 5.0;
		vx = rgen.nextDouble(2.0, 4.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx; 
		}
		
	}
        
        private void moveBall() {
		ball.move(vx, vy);
		
		if ((ball.getX() - vx <= 0 && vx < 0 )|| (ball.getX() + vx >= (getWidth() - BALL_RADIUS*2) && vx>0)) {
			vx = -vx;
		}

		if ((ball.getY() - vy <= 0 && vy < 0 )) {
			vy = -vy;
		}

		GObject collider = getCollidingObject();
		if (collider == paddle) {
		
			
			if(ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2 && ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2 + 4) {
				vy = -vy;	
			}
		}

		else if (collider != null) {
			remove(collider);
                        retrieveScore();
			brickCounter--;
			vy = -vy;
		}
		pause (DELAY);
	}
	/*SCORING SYSTEM*/
        	/*SCORING SYSTEM*/
        	/*SCORING SYSTEM*/
        	/*SCORING SYSTEM*/
        	/*SCORING SYSTEM*/	/*SCORING SYSTEM*/
        	/*SCORING SYSTEM*/
        
        GLabel scoreBoard = new GLabel ("Score: " + liveScore, 15, 15);
        
	private void retrieveScore() {
         
             
            //GLabel scoreBoard = new GLabel ("Score: " + liveScore, 15, 15);
            //remove(scoreBoard);
            scoreBoard.setLabel("Score: " + liveScore);
            switch (rowe) {
case 0:
case 1:
liveScore += 50;
break;

case 2:
case 3:
liveScore += 40;
break;

case 4:
case 5:
liveScore += 30;
break;

case 6:
case 7:
liveScore += 20;
break;

case 8:
case 9:
liveScore += 10;
break;
}
           
            
            
        }
        
        public void drawSB(){
            add(scoreBoard);
            
            
        }
            
        
        
	private GObject getCollidingObject() {
		
		if((getElementAt(ball.getX(), ball.getY())) != null) {
	         return getElementAt(ball.getX(), ball.getY());
	      }
		else if (getElementAt( (ball.getX() + BALL_RADIUS*2), ball.getY()) != null ){
	         return getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY());
	      }
		else if(getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS*2)) != null ){
	         return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2);
	      }
		else if(getElementAt((ball.getX() + BALL_RADIUS*2), (ball.getY() + BALL_RADIUS*2)) != null ){
	         return getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY() + BALL_RADIUS*2);
	      }
		
		else{
	         return null;
	      }
		
	}
	
	 private void printGameOver() {
        GLabel gameOver = new GLabel("Game Over", getWidth() / 2, getHeight() / 2);
        gameOver.move(-gameOver.getWidth() / 2, -gameOver.getHeight());
        gameOver.setColor(Color.RED);
        add(gameOver);
        GLabel showscore = new GLabel("Score: " + liveScore);
        showscore.setColor(Color.RED);
        add(showscore, (getWidth()-showscore.getWidth())/2, (getHeight()-gameOver.getHeight() + showscore.getHeight())/2);
        checkScore();
        restart();
    }

    private void printWinner() {
        GLabel Winner = new GLabel("Winner!!", getWidth() / 2, getHeight() / 2);
        Winner.move(-Winner.getWidth() / 2, -Winner.getHeight());
        Winner.setColor(Color.RED);
        add(Winner);
        GLabel showscore = new GLabel("Score: " + liveScore);
        showscore.setColor(Color.RED);
        add(showscore, (getWidth()-showscore.getWidth())/2, (getHeight()-Winner.getHeight() + showscore.getHeight())/2);
        checkScore();
    }
    
    private void restart(){
        liveScore=0;
        removeAll();
        run();
        
    }

 
int hiScores = 0;
         public void LB(){
             int hi = liveScore;
             
             //for(int i=0; i < 3; i++){
                 if(hi>=hiScores){
                     hiScores=hi;
                 //}
              
                    
             }
              drawLB();
         }
         
         public void drawLB(){
             GLabel hiscore = new GLabel ("Highscore", 160, 250);
		hiscore.setLabel("Highscore: " + hiScores);
             
		add (hiscore);
             
         }
        
       



//LIST ADT IMPLEMENTATION

private  int counter = 0;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    
    private ph.edu.dlsu.datasal.ang.myarraylist.MyArrayList<String> highscore = new ph.edu.dlsu.datasal.ang.myarraylist.MyArrayList<String>();

    private void showHighScore() {
        GLabel Highscore = new GLabel("HIGHSCORE:");
        Highscore.setLocation(160, 325);
        add(Highscore);
            for(int i = 1; i <= counter; i++){
                       GLabel item = new GLabel(""+ i +". " + highscore.get(i));
                       add(item, (getWidth() - item.getWidth())/2, 335 + (i * (item.getHeight() + 3))); 
                    }
    }
    
    private void checkScore(){
        if(liveScore >= first && liveScore != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(1, player + " - " + liveScore);
            sort();
            showHighScore();
            waitForClick();
        }
        
        else if(liveScore >= second && liveScore != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(2, player + " - " + liveScore);
            sort();
            showHighScore();
            waitForClick();
        }
        
        else if(liveScore >= third && liveScore != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(3, player + " - " + liveScore);
            sort();
            showHighScore();
            waitForClick();
        }
    }
    
    private void sort(){
        if(liveScore >= first){
            third = second;
            second = first;
            first = liveScore;
        }
            
        else if(liveScore >= second){
            third = second;
            second = liveScore;
        }
        
        else if(liveScore >= third){
            third = liveScore;
        }
    }
       
}