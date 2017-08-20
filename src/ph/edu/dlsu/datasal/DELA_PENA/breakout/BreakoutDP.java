/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */
package ph.edu.dlsu.datasal.DELA_PENA.breakout;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyArrayList.*;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BreakoutDP extends GraphicsProgram implements Runnable{

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 700;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH1 = APPLICATION_WIDTH;
	private static final int HEIGHT1 = APPLICATION_HEIGHT-100;

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
	  (WIDTH1 - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
        //private GRect bricks[NBRICK_ROWS][NBRICKS_PER_ROW];
	/* Method: init() */
	/** Sets up the Breakout program. */
        
        
        private final GOval ball=setupball();
        private final GRect paddle = setuppaddle();
        private final int DELAY = 10;
        int vx, vy;
        
        private final GLabel ScoreLabel = new GLabel("Score: ", 50, 50);
        int score;
        
        //score given per collored brick
        private final int Red_Score=10;
        private final int Orange_Score=20;
        private final int Yellow_Score=50;
        private final int Green_Score=100;
        private final int Cyan_Score=200;
        
        private final MyArrayList<Integer> HighScores = new MyArrayList<>();
        private final int MAX_HIGHSCORES=5;
        private final int ScoresYOffset = 5;
        private final int ScoresFontSize=10;
        
        private final RandomGenerator rgen=RandomGenerator.getInstance();
        
	public void init() {
           vx=rgen.nextInt(-4, 4);
           if (vx<0)
               vy=5+vx;
           else
               vy=5-vx;
           
           score=0;
           setupbricks();
           add(ball);
           ball.setLocation((WIDTH1/2)-BALL_RADIUS, (HEIGHT1/2)-BALL_RADIUS);
           add(paddle);
           addMouseListeners();
           ScoreLabel.setLabel("Score: " + score);
           ScoreLabel.setFont("Comic Sans MS-20");
           ScoreLabel.setLocation(20, paddle.getY()+PADDLE_HEIGHT+20+ScoresFontSize);
           add(ScoreLabel);
           
           
	}

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
           HighScores.createList();
           int exit=0;
           while(exit!=1){
           while(GameOver()!=1){
               wallcollision();
               AddScore(ObjectCollision());
               
               
               ball.setLocation(ball.getX()+vx, ball.getY()+vy);
               pause(DELAY);
           }
           PlaceHighScore();
           PrintHighScores();
           pause(3000);
           ResetGame();
          
           }
	}
        
        public void PlaceHighScore(){
            if(HighScores.isEmpty() || score > HighScores.get(1))
               HighScores.add(1, score);
            else{
                for(int i=2; i<=HighScores.size(); i++){
                    if(score<=HighScores.get(i-1) && score > HighScores.get(i)){
                        if(i==10)
                            HighScores.remove(10);
                        HighScores.add(i, score);
                        break;
                    }
                }
            }
                
            
                
               
        }
        
        public void PrintHighScores(){
            int y;
            
            GLabel tempHScore;
            int list;
            if(HighScores.size()>=MAX_HIGHSCORES)
                list=MAX_HIGHSCORES;
            else
                list = HighScores.size();
                
            y=(HEIGHT1/2)-((list/2)*ScoresFontSize);
            for(int i=1; i<=list; i++){
                tempHScore=new GLabel(i+". "+HighScores.get(i));
                tempHScore.setFont("Comic Sans MS-bold-"+ScoresFontSize);
                add(tempHScore, (WIDTH1-tempHScore.getWidth())/2, y);
                y+=ScoresFontSize+ScoresYOffset;
            }
        }
        
        //removes all elements and reinitializes the game
        public void ResetGame(){
            for(int h = 0; h<=APPLICATION_HEIGHT; h++){
                for(int w = 0; w<=APPLICATION_WIDTH; w++){
                    if(getElementAt(w,h)!=null)
                        remove(getElementAt(w,h));
                }
            }
            init();
        }
        
        public void AddScore(GObject collider){
             if(collider != null){
                 if(collider.getColor()==Color.red)
                     score+=Red_Score;
                 else if (collider.getColor()==Color.orange)
                     score+=Orange_Score;
                 else if (collider.getColor()==Color.yellow)
                     score+=Yellow_Score;
                 else if (collider.getColor()==Color.green)
                     score+=Green_Score;
                 else if (collider.getColor()==Color.cyan)
                     score+=Cyan_Score;
                 
                 ScoreLabel.setLabel("Score: "+score);
             }
            
        }
       
        public GObject ObjectCollision(){ //returns object collided with
            GObject collider;
            //bottom of ball
            if((collider=getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS+1))!=null){
                if(collider != paddle){
                 remove(collider);   
                }
                vy*=-1;
            }
            //left side of ball
            else if((collider=getElementAt(ball.getX()-1, ball.getY()+BALL_RADIUS))!=null){
                remove(collider);
                vx*=-1;
            }
            //right side of ball
            else if((collider=getElementAt(ball.getX()+2*BALL_RADIUS+1, ball.getY()+BALL_RADIUS))!=null){
                remove(collider);
                vx*=-1;
            }
            //top of ball
            else if((collider=getElementAt(ball.getX()+BALL_RADIUS, ball.getY()-1))!=null){
                remove(collider);
                vy*=-1;
            }
            //upperleft corner
            else if((collider=getElementAt(ball.getX(), ball.getY()))!=null){
                if(collider==getElementAt(ball.getX()-5, ball.getY())){
                    if(collider!=paddle)
                        remove(collider);
                    vx*=-1;
                }
                else if (collider==getElementAt(ball.getX(), ball.getY()-5)){
                    if(collider!=paddle)
                        remove(collider);
                    vy*=-1;
                }
            }
            
            //upperright corner
            else if((collider=getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()))!=null){
                if(collider==getElementAt(ball.getX()+2*BALL_RADIUS+1, ball.getY())){
                    if(collider!=paddle)
                        remove(collider);
                    vx*=-1;
                }
                else if (collider==getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()-1)){
                    if(collider!=paddle)
                        remove(collider);
                    vy*=-1;
                }
            }
            
            //lowerleft corner
            else if((collider=getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS))!=null){
                if(collider==getElementAt(ball.getX()-1, ball.getY()+2*BALL_RADIUS)){
                    if(collider!=paddle)
                        remove(collider);
                    vx*=-1;
                }
                else if (collider==getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS+1)){
                    if(collider!=paddle)
                        remove(collider);
                    vy*=-1;
                }
            }
            //lowerright corner
            else if((collider=getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS))!=null){
                if(collider==getElementAt(ball.getX()+2*BALL_RADIUS+1, ball.getY()+2*BALL_RADIUS)){
                    if(collider!=paddle)
                        remove(collider);
                    vx*=-1;
                }
                else if (collider==getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS+1)){
                    if(collider!=paddle)
                        remove(collider);
                    vy*=-1;
                }
            }
            
            if(collider == paddle){
                collider=null;
                vx=rgen.nextInt(-4, 4);
                if (vx<0)
                    vy=(5+vx)*(-1);
                else
                    vy=(5-vx)*(-1);
            }
            return collider;
        }
        public int GameOver(){
            int i=0;
            if((ball.getY()+(BALL_RADIUS*2))>=paddle.getY()+PADDLE_HEIGHT){
                i=1;
                GLabel gameover = new GLabel("GAME OVER");
                gameover.setFont("Comic Sans MS-"+ScoresFontSize);
                add(gameover , (WIDTH1/2)-gameover.getWidth()/2, ((BRICK_Y_OFFSET-ScoresFontSize)/2)+ScoresFontSize);
            }
            return i;
        }
        
        public void wallcollision(){
            if((ball.getX()+(BALL_RADIUS*2))>=WIDTH1)
                   vx*=-1;
               
               if((ball.getY()+(BALL_RADIUS*4))>=HEIGHT1)
                   vy*=-1;
               
               if(ball.getX()<=0)
                   vx*=-1;
               
               if(ball.getY()<=0)
                   vy*=-1;
        }
        
        public GOval setupball(){
            GOval ball = new GOval((WIDTH1/2)-BALL_RADIUS, (HEIGHT1/2)-BALL_RADIUS, BALL_RADIUS*2, BALL_RADIUS*2);
            ball.setFilled(true);
            
            return ball;
        }
        public GRect setuppaddle(){
            GRect paddle = new GRect((WIDTH1/2)-(PADDLE_WIDTH/2), HEIGHT1-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
            paddle.setFilled(true);
            paddle.setFillColor(Color.black);
            
            return paddle;
        }
        public void setupbricks(){
            int x, y=BRICK_Y_OFFSET;
            GRect brick;
            for(int i=NBRICK_ROWS; i>=1; i--){
                x=0;
                for(int j=NBRICKS_PER_ROW; j>=1; j--){
                    brick=new GRect(x,y,BRICK_WIDTH, BRICK_HEIGHT );
                    switch(i){
                        case 10:
                        case 9:
                            brick.setFilled(true);
                            brick.setColor(Color.cyan);
                            brick.setFillColor(Color.cyan);
                            break;
                          
                        case 8:
                        case 7:
                            brick.setFilled(true);
                            brick.setColor(Color.green);
                            brick.setFillColor(Color.green);
                            break;
                            
                        case 6:
                        case 5:
                            brick.setFilled(true);
                            brick.setColor(Color.yellow);
                            brick.setFillColor(Color.yellow);
                            break;
                            
                            
                        case 4:
                        case 3:
                            brick.setFilled(true);
                            brick.setColor(Color.orange);
                            brick.setFillColor(Color.orange);
                            break;
                            
                        case 2:
                        case 1:
                            brick.setFilled(true);
                            brick.setColor(Color.red);
                            brick.setFillColor(Color.red);
                            break;
                    }
                    add(brick);
                    x+=BRICK_WIDTH+BRICK_SEP;
                }
                y+=BRICK_HEIGHT+BRICK_SEP;
            }
        }
        
        public void mouseMoved(MouseEvent me){
            if(IsMouseXinWindow(me.getX())){
                paddle.setLocation(me.getX()-(PADDLE_WIDTH/2), paddle.getY());
            }
        }
        
        public boolean IsMouseXinWindow(double x){
            return ((x>(PADDLE_WIDTH/2))&&(x<(WIDTH1-(PADDLE_WIDTH/2))));
        }
       
        
        
        /*public static void main(String[] args) {
             new BreakoutDP().start(args);
        }*/
       
}
