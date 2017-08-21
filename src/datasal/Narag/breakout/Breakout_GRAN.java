package datasal.Narag.breakout;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import datasal.Narag.myarraylist.ListCanvas;
import datasal.Narag.myarraylist.MyArrayList;



public class Breakout_GRAN extends GraphicsProgram {
    
         private MyArrayList<String> hiScore = new MyArrayList<String>();
         
        
         
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH-400;
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
        
        /**velocity**/
        private double vy, vx;
        
        /**RNG be praised**/
        private RandomGenerator rng = RandomGenerator.getInstance();
        
         /**number of bricks**/
        int counter=100;
        
        int destroyed=0;
    
        private JFrame frame;
        
        /**canvas**/
        private ListCanvas leaderboard;
        
        int score1, score2, score3, score4, score5, score6, score7, score8, score9, score10;
        String scorename;
    
    public void init()
    {
        
        hiScore.createList();
        hiScore.add(1, ""+score1);
        hiScore.add(2, ""+score2);
        hiScore.add(3, ""+score3);
        hiScore.add(4, ""+score4);
        hiScore.add(5, ""+score5);
        hiScore.add(6, ""+score6);
        hiScore.add(7, ""+score7);
        hiScore.add(8, ""+score8);
        hiScore.add(9, ""+score9);
        hiScore.add(10, ""+score10);
        leaderboard = new ListCanvas();
        leaderboard.setBackground(Color.black);
        add(leaderboard);
    }
    
         @Override
    public void run(){
                //removeAll();
                destroyed = 0;
                counter = 100;
                scoreboard = new GLabel("Score: " + destroyed,0, 10);
                buildWall();
                for(int i=0; i < NTURNS; i++) {
                        
                        paddle();
                        ball();
			StartGame();
			if(counter==0) {
				ball.setVisible(false);
				Winscreen();
                                scorename = JOptionPane.showInputDialog("Input name");
                                highScore();
                                waitForClick();
                                run();
			}
			if(counter > 0) {
				remove(pdl);     
                                remove(ball);
			}
		}
		if(counter > 0) {
			GOscreen();
                        scorename = JOptionPane.showInputDialog("Input name");
                        highScore();
                        waitForClick();
                        run();
		}
            }
    
    
 //G A M E   S E C T I O N       
    private double XLoc() {
        return 0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW);
                                }    
    
    private GRect brick;
        
    //D R A W  B R I C K S
    private void drawWall(int row, int cols){
        double x, y;        
        
        brick = new GRect (BRICK_WIDTH, BRICK_HEIGHT);
        JComponent brickC = new JComponent() {};
        
        x = XLoc() + cols * (BRICK_WIDTH + BRICK_SEP);
        y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
        brick.setFilled(true);
        //B R I C K  C O L O R
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
        //B R I C K  C O L O R  E N D
               add (brick, x, y);
               
    }
    //B U I L D   W A L L
    
    private void buildWall(){
        for (int a = 0; a<NBRICKS_PER_ROW; a++)
            for (int b = 0; b<NBRICKS_PER_ROW; b++)
                drawWall(a, b);
    }
    
    //P A D D L E
    private GRect pdl;
    
    private void paddle(){
        pdl = new GRect(0.5*(WIDTH - PADDLE_WIDTH), HEIGHT -PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        pdl.setFilled(true);
        add(pdl);
        addMouseListeners();
    }
    
    //B A L L 
    private GOval ball;
    
    private void ball(){
        ball = new GOval(BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        add(ball, 0.5*(WIDTH - BALL_RADIUS), 400 );
    }
    
    //actual game            
    private void StartGame() {
		waitForClick();
		getBallVelocity();
		while (true) {
			moveBall();
			if (ball.getY() >= getHeight()) {
				break;
			}
                        
			if(counter == 0) {
				break;
			}
		}
	}
    
    //checks if mouse in bounds
    private boolean mousebounds(int g){
        
        return ((g > 0.5 * PADDLE_WIDTH)) && ((g < (WIDTH - (0.5 * PADDLE_WIDTH))));
    }
    
    //moves the paddle
    public void mouseMoved(MouseEvent me){
        
        if (mousebounds(me.getX()))
            {
            pdl.setLocation(me.getX()-0.5 * PADDLE_WIDTH, pdl.getY());
            }
        }
    
    //gets the ball's velocity
    private void getBallVelocity() {
		vy = 3.0;
		vx = rng.nextDouble(1.0, 3.0);
		if (rng.nextBoolean(0.5)) {
			vx = -vx;
		}
		
	}
    
    private GObject collider;
    //moves the ball
    private void moveBall() {
		ball.move(vx, vy);
                
		//check for walls
		if ((ball.getX() - vx <= 0 && vx < 0 )|| (ball.getX() + vx >= (getWidth() - BALL_RADIUS*2) && vx>0) || ball.getX()<=400 && ball.getX()>390) {
			vx = -vx;
		}
		
		if ((ball.getY() - vy <= 0 && vy < 0 )) {
			vy = -vy;
		}
            //checks for collision
            collider = getCollidingObject();
            if (collider == pdl)
                {
                        vy = -vy;	
                }
                
            else if(collider !=null && collider != pdl && collider!=scoreboard) 
                {
                        scoring();
			remove(collider); 
			destroyed = destroyed + 1;
                        counter = counter - 1;
			vy = -vy;
		}
            pause (15);
                }
                
    //collisions and stuff        
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
		
		else
	         return null;
                }
             
    //when you get rekt
    private void GOscreen() {
		GLabel GO = new GLabel ("Game Over", (getWidth()/2)-200, getHeight()/2);
		GO.move(-GO.getWidth()/2, -GO.getHeight());
		GO.setColor(Color.RED);
		add (GO);
                waitForClick();
                remove(GO);
	}
	
    //when you win
    private void Winscreen() {
		GLabel Winner = new GLabel ("Winner!!", (getWidth()/2)-200, getHeight()/2);
		Winner.move(-Winner.getWidth()/2, -Winner.getHeight());
		Winner.setColor(Color.RED);
		add (Winner);
                waitForClick();
                remove(Winner);
	}
    
    //score
    private GLabel scoreboard;
    
    private void scoring(){
        remove(scoreboard);
        if (collider!=null && collider !=pdl)
                        {
                           scoreboard.setLabel("Score: " + (destroyed+1));
                        }
        add(scoreboard);
    }

    
    
    private void highScore(){
        if (destroyed>score1){
            hiScore.remove(10);
            hiScore.add(1, scorename + " - " +destroyed);
            score1=destroyed;
            leaderboard.displayList(hiScore);
        }
        
        else if (destroyed>score2){
            hiScore.remove(10);
            hiScore.add(2, scorename + " - "+destroyed);
            score2=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score3){
            hiScore.remove(10);
            hiScore.add(3, scorename + " - "+destroyed);
            score3=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score4){
            hiScore.remove(10);
            hiScore.add(4, scorename + " - "+destroyed);
            score4=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score5){
            hiScore.remove(10);
            hiScore.add(5, scorename + " - "+destroyed);
            score5=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score6){
            hiScore.remove(10);
            hiScore.add(6, scorename + " - "+destroyed);
            score6=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score7){
            hiScore.remove(10);
            hiScore.add(7, scorename + " - "+destroyed);
            score7=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score8){
            hiScore.remove(10);
            hiScore.add(8, scorename + " - " +destroyed);
            score8=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score9){
            hiScore.remove(10);
            hiScore.add(9, scorename + " - "+destroyed);
            score9=destroyed;
            leaderboard.displayList(hiScore);
        }
        else if (destroyed>score10){
            hiScore.remove(10);
            hiScore.add(10, scorename + " - "+destroyed);
            score10=destroyed;
            leaderboard.displayList(hiScore);
        }
        else
        {
            leaderboard.displayList(hiScore);
        }
    }
    
    
    public void runBreakout()
        {
            GCanvas frame;
            new Breakout_GRAN().start();
        }

   
}  




