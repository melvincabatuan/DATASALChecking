package ph.edu.dlsu.datasal.SIMEON.breakout;

import ph.edu.dlsu.datasal.SIMEON.myarraylist.MyArrayList;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author jiggy
 */
public class Breakout extends GraphicsProgram{
        AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
        private MyArrayList<Integer> score = new MyArrayList<Integer>();
    	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;
        
        /** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;
        private GLabel scorez;
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
	private static final int NTURNS = 10;
        
        private static final double TIME_DELAY = 5;
        
	private GRect paddle;
        
        private double vx, vy;
        
        private int scores;
        
        private GRect brick;
        
            private void setBrickColor(GRect brick, int row) {
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
}
            private void drawPaddle(){
            paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
            paddle.setFilled(true);
            add(paddle,0.5*(WIDTH-PADDLE_WIDTH),HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
        }

        
        private GOval ball = new GOval (2*BALL_RADIUS,2*BALL_RADIUS);
        
        private void drawBall(){
            ball.setFilled(true);
            add(ball, 0.5*WIDTH - BALL_RADIUS, 0.5*HEIGHT - BALL_RADIUS);
        }
        
        private void drawBrickWall(){
                    for(int i=0; i<NBRICK_ROWS; i++)
                        for(int j=0; j<NBRICKS_PER_ROW; j++){
                            drawBrick(i,j);
                        }
                }
 
        private void drawBrick(int row, int col){
                    double x, y; //brick location
                    brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                    x = computeXOffset()+(col*(BRICK_WIDTH+BRICK_SEP));
                    y = BRICK_Y_OFFSET + (row*(BRICK_HEIGHT+BRICK_SEP));
                    brick.setFilled(true);
                    setBrickColor(brick, row);
                    add(brick,x,y);
                }   
                
        private double computeXOffset(){
                    return 0.5*(WIDTH-(NBRICKS_PER_ROW - 1)*BRICK_SEP-BRICK_WIDTH*NBRICKS_PER_ROW);
                }
        
        private boolean isMouseXInsideWindow(int x){
                return ((x > 0.5 * PADDLE_WIDTH) && (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
        }
        
        public void mouseMoved(MouseEvent me){
            if(isMouseXInsideWindow(me.getX()))
                paddle.setLocation(me.getX()-0.5*PADDLE_WIDTH,paddle.getY());

        }
        private void checkwalls(){
            if(ball.getX()<=0)
                vx=-vx;
            else if(ball.getX()+2*BALL_RADIUS>=WIDTH){
                vx=-vx;
            }
            else if(ball.getY()<=0){
                vy=-vy;
             }

        }
        private void moveBall(){
            vx = rgen.nextDouble(1.0,3.0);
            if(rgen.nextBoolean(0.5)) vx = -vx;
                vy = 3.0;
            while(true){
            ball.move(vx,vy);
            pause(TIME_DELAY);
            checkwalls();
            GObject collider = getCollidingObject();
               if(collider == paddle){
                   bounceClip.play();
                   vy=-vy;
               }
               else if(collider!=null){

                   remove(collider);
                   vy=-vy;
                   bounceClip.play();
                   brickCounter--;
                   scores++;
                   if(scorez!=null)
                       remove(scorez);
                   scorez = new GLabel("SCORE: "+scores );
                   add(scorez,20,35);
                   

                   
                   
                   
               }
               else if (ball.getY() >= getHeight()) {
				break;
			}
               else if(brickCounter==0)
                   break;
        }
        }
        
        private GObject getCollidingObject(){
            if( getElementAt(ball.getX(),ball.getY())!=null){
                return getElementAt (ball.getX(),ball.getY());
            }
            else if ( getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY())!=null){
                return getElementAt (ball.getX()+2*BALL_RADIUS,ball.getY());
        }
            else if ( getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS)!=null){
                return getElementAt (ball.getX(),ball.getY()+2*BALL_RADIUS);
        }
            else if ( getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS)!=null){
                return getElementAt (ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS);
            }
            return null;
            
        }

private int trun=NTURNS;
private void GOver() {
            int n=0;
            trun--;
		GLabel gameOver = new GLabel ("GAME OVER LOSER!!");
		gameOver.setColor(Color.RED);
		add (gameOver,getWidth()/2-gameOver.getWidth()/2, getHeight()/2-gameOver.getHeight());
                GLabel High = new GLabel ("HIGH SCORES");
                add(High, (getWidth()/2)-(High.getWidth()/2), (getHeight()/18));
                GLabel moana = new GLabel ("LOLOL");
                moana.setLabel("LIVES:"+trun);
                add(moana,20,20);
                if(score.size()==0){
                    score.add(1,scores);
                }
                else{
                for(int i=1; i<=score.size();i++ ){
                     if(scores>=score.get(i)){
                         if(score.size()==5)
                         score.remove(5);
                         score.add(i,scores);
                         n=1;
                         break;   
                     }    
                }
                if(n==0)
                score.add(score.size()+1,scores);
                        }
                if(score.size()<=5)
                    z = score.size();
                else
                    z=5;
                for(int i = 1; i <= z; i++){
                   GLabel item = new GLabel(""+ i +". " + score.get(i));
                   add(item, (getWidth() - item.getWidth())/2, getWidth()/10 + i * (item.getHeight())); 
                }
	}
        private int z;
        private void Wienner() {
            int n=0;
		GLabel Winner = new GLabel ("You're A WINNER!!");
		Winner.setColor(Color.BLUE);
		add (Winner,getWidth()/2-Winner.getWidth()/2, getHeight()/2-Winner.getHeight());
                GLabel High = new GLabel ("HIGH SCORES");
                add(High, (getWidth()/2)-(High.getWidth()/2), (getHeight()/18));
                                if(score.size()==0){
                    score.add(1,scores);
                }
                else{
                for(int i=1; i<=score.size();i++ ){
                     if(scores>=score.get(i)){
                         if(score.size()==5)
                             score.remove(5);
                         score.add(i,scores);
                         n=1;
                         break;        
                     }                 
                }
                if(n==0)
                score.add(score.size()+1,scores);
                        }
                for(int i = 1; i <= score.size(); i++){
                   GLabel item = new GLabel(""+ i +". " + score.get(i));
                   add(item, (getWidth() - item.getWidth())/2, getWidth()/10 + i * (item.getHeight())); 
                }
	}
        
private void playGame() {
                GLabel nemo = new GLabel ("LOLOL");
                nemo.setLabel("LIVES:"+trun);
                add(nemo,20,20);
                waitForClick();
                scores=0;
                brickCounter=100;
		while (true) {
			moveBall();
			if (ball.getY() >= getHeight()) {
				break;
			}
			if(brickCounter == 0) {
                                score.add(1, scores);
				break;
			}
		}
	}

	
	private int brickCounter;

        RandomGenerator rgen = RandomGenerator.getInstance();
	/* Method: init() */
	/** Sets up the Breakout program. */

                    public void run() {
                        score.createList();
                        for(int i=0; i < NTURNS; i++) {
                        addMouseListeners();
                        drawBrickWall();
                        drawPaddle();
                        drawBall();                       
			playGame();

			if(brickCounter == 0) {
				ball.setVisible(false);
				Wienner();
                                waitForClick();
				break;
			}
			if(brickCounter > 0) {
				removeAll();
			}
                        if(brickCounter > 0) {
			GOver();
                        waitForClick();
                        removeAll();
		}
		}

             
        
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Breakout().start(args);
    }
    
}
