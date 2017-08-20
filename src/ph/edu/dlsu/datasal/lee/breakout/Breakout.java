package ph.edu.dlsu.datasal.lee.breakout;

/*
 * File: Breakout.java
 * -------------------
 * Name:Lee Inicca
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.io.IODialog;
import acm.program.*;
import acm.util.*;


import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import ph.edu.dlsu.datasal.lee.myarraylist.*;



public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	public static final int WIDTH = APPLICATION_WIDTH;
	public static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	public static final int PADDLE_WIDTH = 60;
	public static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	public static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	public static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	public static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	public static final int BRICK_SEP = 4;

	/** Width of a brick */
	public static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	public static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	public static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	public static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	public static int NTURNS = 3;
        
        public static final int PAUSE_TIME = 20;
        
        int counter=0;
        
        double dx=3, dy=4;
        
        GRect paddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
        String name;

        
        int index=0;
        int check=0;
        /* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
            NTURNS = 3;
            counter=0;
            life.setLabel("Lives:"+ NTURNS);
            score.setLabel("Score:" + counter);
            drawBall();
            drawBrickWall();
            drawPaddle();
            addMouseListeners();
            run();
	}
        
        
        public void drawBrickWall() {
            for(int i=0;i<NBRICK_ROWS;i++)
            for(int j=0;j<NBRICKS_PER_ROW;j++)
            drawBrick(i,j);
        }
            
        public void drawBrick(int row, int col){
            double x, y;
            GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
            x = computeXOffset()+col*(BRICK_WIDTH+BRICK_SEP);
            y = BRICK_Y_OFFSET+row*(BRICK_HEIGHT+BRICK_SEP);
            brick.setFilled(true); 
            setBrickColor(brick, row);
            add(brick, x, y);
        }
        
        public double computeXOffset() {
            return (WIDTH-(NBRICKS_PER_ROW-1)*BRICK_SEP-BRICK_WIDTH*NBRICKS_PER_ROW);
        }
        
        public double computeYOffset() {
            return (HEIGHT-(NBRICK_ROWS-1)*BRICK_SEP-BRICK_WIDTH*NBRICKS_PER_ROW);
        }
        
        public void setBrickColor(GRect brick, int row) {
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
            
        public void drawPaddle(){
            paddle.setFilled(true);
            add( paddle, 0.5*(WIDTH-PADDLE_WIDTH),HEIGHT-PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
        
        public boolean isMouseXInsideWindow(int x){
            return((x >0.5*PADDLE_WIDTH)&& (x<(WIDTH-0.5*PADDLE_WIDTH)));
        }
        
        public void mouseMoved(MouseEvent me){
            if (isMouseXInsideWindow(me.getX()))
            paddle.setLocation(me.getX()-0.5*PADDLE_WIDTH,paddle.getY());
        }
        
        public GOval ball = new GOval(2*BALL_RADIUS,2*BALL_RADIUS);
        
        public void drawBall(){
            ball.setFilled(true);
            add(ball, 0.5*WIDTH - BALL_RADIUS, .5*HEIGHT -BALL_RADIUS);
        }
        
        public void moveBall(){
            double bx = ball.getX();
            double by = ball.getY();
            if (bx>=APPLICATION_WIDTH-BALL_RADIUS*2||bx<=0) dx = -dx;
            else if (by<=0) dy = -dy;
            else if (by>=APPLICATION_HEIGHT-BALL_RADIUS*2) relife();
            ball.move(dx, dy);
        }
        
        public GObject collision(){
            double bx = ball.getX();
            double by = ball.getY();
            if(null!=getElementAt(bx,by)) return getElementAt(bx,by);
            else if(null!=getElementAt(bx+BALL_RADIUS*2,by)) return getElementAt(bx+BALL_RADIUS*2,by);
            else if(null!=getElementAt(bx,by+BALL_RADIUS*2)) return getElementAt(bx,by+BALL_RADIUS*2);
            else if(null!=getElementAt(bx+BALL_RADIUS*2,by+BALL_RADIUS*2)) return getElementAt(bx+BALL_RADIUS*2,by+BALL_RADIUS*2);
            else return null;
        }
                
        public void Hit(){
            GObject collider= collision();
            if(collider==paddle){
                dy=-dy;
            }
            else if(collider==score);
            else if(collider==null);
            else{
                remove(collider);
                dy=-dy; 
                score();
            }
        }
        
        GLabel score= new GLabel("Score:");
        GLabel life= new GLabel("Lives"+ NTURNS);
        
        public void display(){
            life.setFont("Serif-Bold-24");
            life.setLabel("Lives:"+ NTURNS);
            score.setFont("Serif-Bold-24");
            score.setLabel("Score:" + counter);
            add(life,162,24);
            add(score,12,24);
        }
        public int score(){ 
            counter=counter+points();
            return counter;
        }
        
        public int points(){
            double by = ball.getY();
            int points = 0;
            if(by<=90) points=50;
            else if(by>90&&by<=114) points=30;
            else if(by>114&&by<=138) points=20;
            else if(by>138&&by<=162) points=10;
            else if(by>162) points=5;
            return points;
        }
       
        public void relife(){
            NTURNS--;
            drawBall();
        }
          
        public void gameover(){
            displayList(list);
            GLabel game= new GLabel("GAMEOVER");
            game.setFont("Serif-Bold-50");
            add(game,50,100);
            GLabel restart= new GLabel("to restart, click again");
            restart.setFont("Serif-Bold-14");
            add(restart,130,180);
            waitForClick();
            removeAll();
            init(); 
        }
       
        MyList<String> namelist  = new MyList<>(); 
        MyList<Integer> list  = new MyList<>();  
        public void sort(){
            int temp;
            String nametemp;
            for(int i=list.size();i>=1;i--){
                for(int j=1;j<i;j++){
                    temp=list.get(j);
                    nametemp=namelist.get(j);
                    if(list.get(j)<list.get(j+1)){
                        list.remove(j);
                        namelist.remove(j);
                        list.add(j+1, temp);
                        namelist.add(j+1, nametemp);
                    }
                }
                if(list.size()==6) list.remove(6);
                if(namelist.size()==6) namelist.remove(6);
            }
        }

        public void input(){
            IODialog dialog = getDialog();
            name = dialog.readLine("Enter name:");
            namelist.add(1, name);
        }
        
        public void displayList(MyList list) {           
                removeAll();
                list.add(1,counter);
                input();
                sort();
                int offset = 200;
                GLabel HS = new GLabel("HIGHSCORE");
                HS.setFont("Serif-Bold-32");
                add(HS,(WIDTH-HS.getWidth())/2,250);
		for(int i=1;i<=list.size();i++){
                   GLabel item = new GLabel(namelist.get(i)+"    "+list.get(i));
                   item.setFont("Serif-Bold-32");
                   add(item,(WIDTH-item.getWidth())/2,WIDTH/10+i*(item.getHeight())+offset); 
                }
	}
       
	public void run() {
            if(check!=1){
                check=1;
                init();
            }
            waitForClick();
            boolean x=true;
            while (x==true) {
                display();
                Hit();
                moveBall();
                if(NTURNS==0) x=false;
                pause(PAUSE_TIME);
            }  
            gameover();
        }  
}
