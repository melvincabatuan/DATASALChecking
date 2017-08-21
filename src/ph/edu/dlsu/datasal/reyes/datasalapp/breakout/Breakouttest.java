package ph.edu.dlsu.datasal.reyes.datasalapp.breakout;


import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakouttest extends GraphicsProgram {

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
        
        private final GRect ppaddle = drawPaddle();
        private final GRect bbrick = drawBrick(1, 1);
        private int ballspeed = 2;

	/* Method: init() */
	/** Sets up the Breakout program. */
          private void drawBrickWall(){
              for(int i=0; i<NBRICK_ROWS; i++)
                for(int j = 0; j<NBRICKS_PER_ROW;j++)
                    drawBrick(i,j);
            }
          private GRect drawBrick(int row, int col){
              double x, y;
              GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
              x = computeXOffset() + col *(BRICK_WIDTH+BRICK_SEP);
              y= BRICK_Y_OFFSET + row *(BRICK_HEIGHT + BRICK_SEP);
              brick.setFilled(true);
              setBrickColor(brick,row);
              add(brick, x , y);
            return brick;
          }
          private double computeXOffset(){
              return 0.5 * (WIDTH -(NBRICKS_PER_ROW -1)*BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW);
          }
          private void setBrickColor(GRect brick, int row){
              switch (row){
                  case 0:
                  case 1:
                      brick.setFillColor(Color.RED);
              }
               switch (row){
                  case 2:
                  case 3:
                      brick.setFillColor(Color.orange);
              }
                switch (row){
                  case 4:
                  case 5:
                      brick.setFillColor(Color.yellow);
              }
                 switch (row){
                  case 6:
                  case 7:
                      brick.setFillColor(Color.green);
              }
                  switch (row){
                  case 8:
                  case 9:
                      brick.setFillColor(Color.cyan);
              }
          }
          public GRect drawPaddle(){
              GRect paddle = new GRect (10,HEIGHT-PADDLE_Y_OFFSET,PADDLE_WIDTH, PADDLE_HEIGHT);
              paddle.setFilled(true);   
              return paddle;
          }
          private GOval ball = new GOval((WIDTH/2)-BALL_RADIUS,(HEIGHT/2)-BALL_RADIUS,2*BALL_RADIUS,2*BALL_RADIUS);       
          private boolean isMouseXInsideWindow(int x){
            return ((x > 0.5 * PADDLE_WIDTH)&& (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
          }
          public void mouseMoved(MouseEvent me){
              if (isMouseXInsideWindow(me.getX()))
               ppaddle.setLocation(me.getX()-0.5 * PADDLE_WIDTH,ppaddle.getY());   
          }
          private RandomGenerator rgen = RandomGenerator.getInstance();
          public void brickcollision(){
              
              //TOP
                if(getElementAt(ball.getX()+BALL_RADIUS,ball.getY()-1)!=null){
                  vy=-vy; 
                  remove(getElementAt(ball.getX()+BALL_RADIUS,ball.getY()-1));
                  score++;
                }
                //BOTTOM
                else if(getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+(2*BALL_RADIUS)+1)!=null){
                  vy=-vy; 
                  remove(getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+(2*BALL_RADIUS)));
                 if(getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+(2*BALL_RADIUS)+1)==ppaddle)
                 score++;
                }
                //LEFT
                else if(getElementAt(ball.getX()-1,ball.getY()+BALL_RADIUS)!=null){
                  vx=-vx;  
                  remove(getElementAt(ball.getX(),ball.getY()+BALL_RADIUS));
                  score++;
                }
                //RIGHT
                else if(getElementAt(ball.getX()+(2*BALL_RADIUS)+1,ball.getY()+BALL_RADIUS)!=null){
                  vx=-vx;
                  remove(getElementAt(ball.getX()+(2*BALL_RADIUS),ball.getY()+BALL_RADIUS));
                  score++;
                }
                //TOPRIGHT
                else if(getElementAt(ball.getX()+(2*BALL_RADIUS),ball.getY())!=null){
                  vx=-vx; 
                  remove(getElementAt(ball.getX()+(2*BALL_RADIUS),ball.getY()));
                  score++;
                }
                //BOTTOMRIGHT
                else if(getElementAt(ball.getX()+(2*BALL_RADIUS),ball.getY()+(2*BALL_RADIUS))!=null){
                  vx=-vx; 
                  remove(getElementAt(ball.getX()+(2*BALL_RADIUS),ball.getY()+(2*BALL_RADIUS)));
                score++;
                }
                //TOPLEFT
                else if(getElementAt(ball.getX(),ball.getY())!=null){
                  vx=-vx; 
                  remove(getElementAt(ball.getX(),ball.getY()));
                score++;
                }
                else if(getElementAt(ball.getX(),ball.getY()+(2*BALL_RADIUS))!=null){
                  vx=-vx; 
                  remove(getElementAt(ball.getX(),ball.getY()+(2*BALL_RADIUS)));
                score++;
                }
             
          }
          
	public void init() {
		/* You fill this in, along with any subsidiary methods */
          
          addMouseListeners();
          
          add (ball);
          ball.setFilled(true);
          drawBrickWall();
	}
         private  double vx = ballspeed;
         private  double vy = ballspeed;
         public int score = 0;
	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
            GLabel scores = new GLabel ("score"+ score);
            GLabel gameover = new GLabel ("GAME OVER",(APPLICATION_WIDTH/2)-50,APPLICATION_HEIGHT/2);
            add (scores,20,20);
         for(;;){
             
             scores.setLabel("score"+ score);
             
             add (ppaddle);
             ball.setLocation(ball.getX()+vx,ball.getY()+vy);
             pause(10);
             if(ball.getX()+(BALL_RADIUS*2)>WIDTH){
                 vx=-vx;
             }
             else if(ball.getX()<0){
                 vx=-vx;
             }
             if(ball.getY()+(BALL_RADIUS*4)>HEIGHT){
                 add(gameover);
                 break;
                 //vy=-vy;
             }
             else if(ball.getY()<0){
                 vy=-vy;
             }
           
            brickcollision();
         }
	}
        public static void main(String[] args) {
   new Breakouttest().start(args);   
}
}
