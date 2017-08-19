/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * File: Artistry.java
 * Name: Maverick C. Rivera
 * 
 */
package ph.edu.dlsu.rivera.breakout;
import acm.util.RandomGenerator;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import java.awt.Color;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;





public class Breakout extends GraphicsProgram{
GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
private GOval ball = new GOval(2*BALL_RADIUS,
2*BALL_RADIUS);
private ArrayList<Integer> HS = new ArrayList<>();
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
private static int NTURNS = 3;
private static Integer SCORE = 0;
GLabel score = new GLabel("Score: " + SCORE, APPLICATION_WIDTH-100 ,30 );
GLabel lives = new GLabel("Lives: " + NTURNS, 20 ,30 );
GLabel pWhenReady = new GLabel("Press When Ready!" , 50 ,300 );
private boolean isRunning=true;
private RandomGenerator rgen = RandomGenerator.getInstance();
public Breakout(){
    
}
public void init() {
/* You fill this in, along with any subsidiary methods */
NTURNS=3;
SCORE=0;
lives.setLabel("Lives: " + NTURNS);
lives.setFont("Serif-bold-20");
lives.setColor(Color.RED);
drawScore();
drawBrickWall();
drawPaddle();
addMouseListeners();
drawBall();
run();

}

public void Sound() throws FileNotFoundException, IOException{
    // open the sound file as a Java input stream
    String gongFile = "A:\\mave documents\\term3 2016- 2017\\LBYCP12\\breakout\\JavaApplication10\\src\\bounce.au";
    InputStream in = new FileInputStream(gongFile);
    
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
}
public void ballUpdate() throws IOException{
    double dx=1,dy=-1;
    double x, y;
    GObject temp;
   
   add(lives);
    while(NTURNS>0){
    while(isRunning){
    x = ball.getX();
    y= ball.getY();
         ball.move(dx,dy);
         pause(3);
    if(x<0){
       dx =1;
   }
     if(x>WIDTH-2*BALL_RADIUS){
       dx =-1;
   }
     if(y<=0){
         dy=1;
     }
     if(ball.getX()+BALL_RADIUS*2>paddle.getX() && ball.getX()<PADDLE_WIDTH + paddle.getX() && y>=HEIGHT-2*BALL_RADIUS-PADDLE_Y_OFFSET){
         dy=-1;
         if(dx>0){
             dx=rgen.nextDouble(1, 2);
         }else{
             dx=-rgen.nextDouble(1, 2);
         }
     }else if(y>=HEIGHT-2*BALL_RADIUS){
         
//         removeAllComponents();
         
         break;
     }
     
       //removing object 
     
     
        if(getElementAt(x+BALL_RADIUS, y)!=null &&
                getElementAt(x+BALL_RADIUS, y) !=ball &&
                getElementAt(x+BALL_RADIUS, y) !=paddle){
            temp   =  getElementAt(x+BALL_RADIUS, y);
            
            determineScore(y);
            remove(temp);
            Sound();
            dx=-dx;
            dy=-dy;
            
            
            
            score.setLabel("Score: " + SCORE);
            
                    
        }
        if(getElementAt(x+BALL_RADIUS, y+2*BALL_RADIUS)!=null &&
                getElementAt(x+BALL_RADIUS, y+2*BALL_RADIUS) !=ball &&
                getElementAt(x+BALL_RADIUS, y+2*BALL_RADIUS) !=paddle){
            temp   =  getElementAt(x+BALL_RADIUS, y+2*BALL_RADIUS);
            determineScore(y+2*BALL_RADIUS);
            remove(temp);
            Sound();
            dx=-dx;
            dy=-dy;
          
            
            score.setLabel("Score: " + SCORE);
            
                    
        }
        if(getElementAt(x, y+BALL_RADIUS)!=null &&
                getElementAt(x, y+BALL_RADIUS) !=ball &&
                getElementAt(x, y+BALL_RADIUS) !=paddle){
            temp   =  getElementAt(x, y+BALL_RADIUS);
            determineScore(y+BALL_RADIUS);
            remove(temp);
            Sound();
            dx=-dx;
            dy=-dy;
      
            
            score.setLabel("Score: " + SCORE);
            
                    
        }
        if(getElementAt(x+2*BALL_RADIUS, y+BALL_RADIUS)!=null &&
                getElementAt(x+2*BALL_RADIUS, y+BALL_RADIUS) !=ball &&
                getElementAt(x+2*BALL_RADIUS, y+BALL_RADIUS) !=paddle){
            temp   =  getElementAt(x+2*BALL_RADIUS, y+BALL_RADIUS);
            determineScore(y+BALL_RADIUS);
            remove(temp);
            Sound();
            dx=-dx;
            dy=-dy;
            
            
            score.setLabel("Score: " + SCORE);
            
                    
        }
        
        
    }
    if(NTURNS>1){
    pressWhenReady();
    waitForClick();
        remove(pWhenReady);
        ball.setLocation(0.5*WIDTH - BALL_RADIUS , .5*HEIGHT -
BALL_RADIUS);
        dx=1;
        dy=-1;}
        
    NTURNS--;
    lives.setLabel("Lives: " + NTURNS);
    }
    remove(ball);
    displayDeath();
    HS.add(1, SCORE);
    showHighScores();
   waitForClick();
   removeAll();
   init();
   
}

public void determineScore(double y){
    double row =  (y - BRICK_Y_OFFSET)/ (BRICK_HEIGHT +
BRICK_SEP)+2;
    switch((int)row/2){
        case 1: SCORE+=5;break;
        case 2: SCORE+=4;break;
        case 3: SCORE+=3;break;
        case 4: SCORE+=2;break;
        case 5:SCORE++;break;
    }
}
public void run() {
    try {
        ballUpdate();
        /* You fill this in, along with any subsidiary methods */
    } catch (IOException ex) {
        Logger.getLogger(Breakout.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
private boolean isMouseXInsideWindow(double x){
return ((x > 0.5 * PADDLE_WIDTH)
&& (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
}

public void mouseMoved(MouseEvent me){
if (isMouseXInsideWindow(me.getX()))
paddle.setLocation(me.getX()-0.5 * PADDLE_WIDTH,
paddle.getY());
} 

private void drawBrickWall() {
for (int i = 0; i < NBRICK_ROWS; i++)
for (int j = 0; j < NBRICKS_PER_ROW; j++)
drawBrick(i,j);
}

private void drawBrick(int row, int col){
double x, y; // brick location
GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
x = computeXOffset() + col * (BRICK_WIDTH +
BRICK_SEP);
y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT +
BRICK_SEP);
setBrickColor(brick, row);
brick.setFilled(true);
add(brick, x, y);
}
private void setBrickColor(GRect brick, int row) {
switch (row) {
case 0:
brick.setFillColor(Color.RED);break;
case 1:
brick.setFillColor(Color.RED);break;
   
case 2:
brick.setFillColor(Color.ORANGE);break;
case 3:
brick.setFillColor(Color.ORANGE);break;
case 4:
brick.setFillColor(Color.YELLOW);break;
case 5:
brick.setFillColor(Color.YELLOW);break;
case 6:
brick.setFillColor(Color.GREEN);break;
case 7:
brick.setFillColor(Color.GREEN);break;
case 8:
brick.setFillColor(Color.CYAN);break;
case 9:
brick.setFillColor(Color.CYAN);break;
}
}
private double computeXOffset() {

    return 0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP -(BRICK_WIDTH * NBRICKS_PER_ROW));
}





private void drawBall(){
ball.setFilled(true);
add(ball, 0.5*WIDTH - BALL_RADIUS , .5*HEIGHT -
BALL_RADIUS );
ball.sendToBack();
}

private void pressWhenReady(){
    
    pWhenReady.setFont("Serif-bold-36");
         pWhenReady.setColor(Color.RED);
         add(pWhenReady);
}
private void drawPaddle() {

paddle.setFilled(true);
add( paddle, 0.5*(WIDTH - PADDLE_WIDTH), HEIGHT -
PADDLE_Y_OFFSET - PADDLE_HEIGHT);
}

    public static void main(String[] args ) {
        
            new Breakout().start(args);
        }

    private void displayDeath() {
        GLabel dead = new GLabel("DEAD", WIDTH/2-50 ,HEIGHT/2-35);
        GLabel click2playagain = new GLabel("Click to play again", WIDTH/2 -70 ,HEIGHT/2);
        click2playagain.setFont("Serif-bold-18");
         click2playagain.setColor(Color.RED);
         dead.setFont("Serif-bold-36");
         dead.setColor(Color.RED);
         add(dead);
         add(click2playagain);
    }

    private void drawScore() {
         score.setFont("Serif-bold-20");
         score.setColor(Color.RED);
         add(score);
       }

     private void showHighScores() {
        //sortthelist
        for(int i=1;i<HS.size();i++){
            for(int j=1;j<=HS.size()-i;j++){
                if(HS.get(j+1)>HS.get(j)){
                  Integer temp = HS.get(j);
                   HS.remove(j);
                   HS.add(j+1,temp);
                 
                }
            }
        }
        
        if(HS.size()==6){
            HS.remove(6);
        }
        displayList(HS);
    }
    public void displayList(ArrayList list) {
                int offset = 300;
                GLabel heading = new GLabel("Highscores");
                heading.setFont("Century Schoolbook L-20-italic");
                heading.setColor(Color.RED);
                
                add(heading, WIDTH/2-heading.getWidth()/2 ,HEIGHT/2+ 30);
                
		for(int i = 1; i <= list.size(); i++){
                   GLabel item = new GLabel(""+ i +". " + list.get(i));
                   item.setFont("Century Schoolbook L-20-italic");
                   item.setColor(Color.RED);
                   add(item, (getWidth() - item.getWidth())/2, getWidth()/10 + i * (item.getHeight() + 3)+offset); 
                }
	}
}
