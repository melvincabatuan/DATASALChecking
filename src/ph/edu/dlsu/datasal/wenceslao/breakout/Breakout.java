package ph.edu.dlsu.datasal.wenceslao.breakout;

/*
 * File: Breakout.java
 * -------------------
 * Name: Luis Paolo D. Wenceslao
 * 
 * This file will eventually implement the game of Breakout.
 */

import ph.edu.dlsu.datasal.wenceslao.myarraylist.MyArrayList;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;



public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 8;

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
	private static final int BALL_RADIUS = 5;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
        
        /** Ball velocity */	
	private double vx, vy;
	
        /** Random number generator for vx */	
        private RandomGenerator rgen = RandomGenerator.getInstance();

        /** Animation delay or pause time between ball moves */	
        private static final int DELAY = 10; 

	private int brickCounter = 100;
        
        
        
        
        
        /* Method: run() */
	/** Runs the Breakout program. */
        public void run() {
            tscore = 0;
            leaderboard.createList();
            setup();
            addMouseListeners();
            for(int i=0; i < NTURNS; i++) {
                cscore = 0;
                drawBall();
                displayScore();
                playGame();
                if(brickCounter == 0) {
                    removeAll();
                    WinScreen();
                    break;
                }
                if(brickCounter > 0) {
                    displayTurn(i);
                }
            }
            if(brickCounter > 0) {
                GameOverScreen();
            }
        }
        
        
        
        
        
        // SETUP //
        public void setup(){
            displayWelcome();
            waitForClick();
            removeAll();
            drawWall();            
            drawPaddle();
        }
        
        // RESTART //
        private void restart(){
        removeAll();
        tscore = 0;
        setup();
        addMouseListeners();
        for(int i=0; i < NTURNS; i++) {
            cscore = 0;
            drawBall();
            displayScore();
            playGame();
            if(brickCounter == 0) {
                removeAll();
                WinScreen();
                break;
            }
            if(brickCounter > 0) {
                displayTurn(i);
            }
        }
        if(brickCounter > 0) {
            GameOverScreen();
        }
        }
        
        // BRICKWALL //
        // WALL
        private void drawWall() {
        for (int i = 0; i < NBRICK_ROWS; i++)
            for (int j = 0; j < NBRICKS_PER_ROW; j++)
                drawBrick(i,j);
        }
        
        // BRICKS
        private void drawBrick(int row, int col){
            double x, y; // brick location
            GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
            x = computeXOffset() + col * (BRICK_WIDTH + BRICK_SEP);
            y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
       
        // BRICK COLORS
            brick.setFilled(true);
            if (row<=1) {
                brick.setColor(Color.RED);
            } 
            else if (row<=3) {
                brick.setColor(Color.ORANGE);
            } 
            else if (row<=5) {
                brick.setColor(Color.YELLOW);
            } 
            else if (row<=7) {
                brick.setColor(Color.GREEN);
            } 
            else {
                brick.setColor(Color.CYAN);
            }	
            add(brick, x, y);    
        }

        private double computeXOffset() {
            return (0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP - (BRICK_WIDTH * NBRICKS_PER_ROW)));
        }
        // END OF BRICK WALL //
        
        
        // PADDLE //
        private GRect paddle;
        
        private void drawPaddle() {
            paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
            paddle.setFilled(true);
            paddle.setColor(Color.BLACK);
            add( paddle, 0.5*(WIDTH - PADDLE_WIDTH), HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
            
        }
        // END OF PADDLE //
        
        
        // MOUSE ACTIVITY //
        private boolean isMouseXInsideWindow(int x){
            return ((x > 0.5 * PADDLE_WIDTH) && (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
        }
        
        public void mouseMoved(MouseEvent me){
            if (isMouseXInsideWindow(me.getX()))
            paddle.setLocation(me.getX()-0.5 * PADDLE_WIDTH, paddle.getY());
        }
        // END OF MOUSE ACTIVITY //
        
        
        // BALL //
        private GOval ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
        
        private void drawBall(){
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball, 0.5 * WIDTH - BALL_RADIUS, 0.5 * HEIGHT - BALL_RADIUS);
        }
        // END OF BALL //
        
        
        // GAME MECHANICS //
        // Starting the game
        private void playGame(){
            clickToStart();
            BallVelocity();
            while (true) {
                BallMovement();
                if (ball.getY() >= getHeight()) {
                        break;
                }
                if(brickCounter == 0) {
                        break;
                }
            }
        }
        
        // Starting message
        private void clickToStart() {
            GLabel startLabel = new GLabel ("Click to start", getWidth()/2, getHeight()/2);
            startLabel.move(-startLabel.getWidth()/2, -startLabel.getHeight());
            startLabel.setColor(Color.RED);
            add(startLabel);
            waitForClick();
            remove(startLabel);
	}
        
        // Speed of the Ball
        private void BallVelocity() {
            vy = 4.0;
            vx = rgen.nextDouble(1.0, 3.0);
            if (rgen.nextBoolean(0.5)) {
                    vx = -vx; 
            }
		
	}
        
        // Ball Movement across the Window
        private GObject collider;
        
        private void BallMovement() {
            ball.move(vx, vy);
            if ((ball.getX() - vx <= 0 && vx < 0 )|| (ball.getX() + vx >= (getWidth() - BALL_RADIUS * 2) && vx>0)) {
                vx = -vx;
            }
            if ((ball.getY() - vy <= 0 && vy < 0 )) {
                vy = -vy;
            }

            collider = getCollidingObject();
            if (collider == paddle) {
                {
                    vy = -vy;	
                }
            }
            else if (collider != null && collider != totalScore && collider != currentScore) {
                remove(collider);
                addScore(collider.getColor());
                displayScore();
                brickCounter--;
                vy = -vy;
            }
            pause (DELAY);
	}
        
        // Identifying the objects being hit or not hit
        private GObject getCollidingObject() {
            if((getElementAt(ball.getX(), ball.getY())) != null) {
	        return getElementAt(ball.getX(), ball.getY());
	    }
            else if (getElementAt( (ball.getX() + BALL_RADIUS * 2), ball.getY()) != null ){
	        return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
	    }
            else if(getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2)) != null ){
	        return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
	    }
            else if(getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2)) != null ){
	        return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
	    }
            else{
	        return null;
	    }
		
	}
        // END OF GAME MECHANICS //
        
        
        // MESSAGES //    
        private void displayWelcome() {
                removeAll();
                GLabel welcome = new GLabel("BREAKOUT");
                welcome.setFont("-BOLD-36");
		welcome.setColor(Color.RED);
		double w = welcome.getWidth();
		double h = welcome.getAscent();
		add(welcome, (WIDTH - w) / 2, (HEIGHT - h) / 2);
		
		GLabel click = new GLabel("Click to Play Game");
		double w2 = click.getWidth();
		double h2 = click.getAscent();
		add(click, (WIDTH - w2) / 2, ((HEIGHT - h2) / 2) + (HEIGHT / 4));
	}
        
        private void GameOverScreen() {
		GLabel gameOver = new GLabel ("GAME OVER", getWidth()/2, getHeight()/2);
                gameOver.setFont("-BOLD-36");
                gameOver.move(-gameOver.getWidth()/2, -gameOver.getHeight());
		gameOver.setColor(Color.RED);
		add(gameOver);
                
                GLabel TotalScore = new GLabel ("Total Score: " + tscore);
                TotalScore.setFont("-BOLD-30");
                TotalScore.setColor(Color.RED);
                double w2 = TotalScore.getWidth();
		double h2 = TotalScore.getAscent();
		add(TotalScore, (WIDTH - w2) / 2, ((HEIGHT - h2) / 2) + (HEIGHT / 8));
                
                ScoreCheck();
                removeAll();
                restart();
	}
	
	private void WinScreen() {
		GLabel Winner = new GLabel ("PLAYER WINS", getWidth()/2, getHeight()/2);
		Winner.setFont("-BOLD-36");
                Winner.move(-Winner.getWidth()/2, -Winner.getHeight());
		Winner.setColor(Color.RED);
		add(Winner);
                
                GLabel TotalScore = new GLabel ("Total Score: " + tscore);
                TotalScore.setFont("-BOLD-30");
                TotalScore.setColor(Color.RED);
                double w2 = TotalScore.getWidth();
		double h2 = TotalScore.getAscent();
		add(TotalScore, (WIDTH - w2) / 2, ((HEIGHT - h2) / 2) + (HEIGHT / 8));
                
                waitForClick();
                GLabel easter = new GLabel ("FATALITY!!!");
		easter.setFont("-BOLD-36");
		easter.setColor(Color.RED);
                double w3 = easter.getWidth();
		double h3 = easter.getAscent();
		add(easter, (WIDTH - w3) / 2, ((HEIGHT - h3) / 2) + (HEIGHT / 24));
                
                ScoreCheck();
                removeAll();
                restart();
	}
        
        private void displayTurn(int i) {
            int lives = NTURNS - (i + 1);
            if(lives == 0){
                removeAll();
            }
            else{
                GLabel turn = new GLabel("You have " + lives + " lives left!");
                turn.setColor(Color.RED);
                double w = turn.getWidth();
                double h = turn.getAscent();
                add(turn, (WIDTH - w) / 2, (HEIGHT - h) / 2);

                GLabel click = new GLabel("Click to Continue");
                click.setFont("-16");
                double w2 = click.getWidth();
                double h2 = click.getAscent();
                add(click, (WIDTH - w2) / 2, ((HEIGHT - h2) / 2) + (HEIGHT / 4));
                waitForClick();
                remove(turn);
                remove(click);
            }            
	}
        // END OF MESSAGES //
        
        
        // SCORES //
        private void addScore(Color col) {
		if(col == Color.RED) {
			tscore += redscore;
			cscore += redscore;
		}
		else if(col == Color.ORANGE) {
			tscore += orangescore;
			cscore += orangescore;
		}
		else if(col == Color.YELLOW) {
			tscore += yellowscore;
			cscore += yellowscore;
		}
		else if(col == Color.GREEN) {
			tscore += greenscore;
			cscore += greenscore;
		}
		else if(col == Color.CYAN) {
			tscore += cyanscore;
			cscore += cyanscore;
		}
	}
        
        private void displayScore() {
		totalScore.setColor(Color.BLUE);
		double w = totalScore.getWidth();
		double h = totalScore.getAscent();
		totalScore.setLabel("Total Score: " + tscore);
		remove(totalScore);
                add(totalScore, 305, 15);
		
		currentScore.setColor(Color.BLUE);
		double w2 = currentScore.getWidth();
		double h2 = currentScore.getAscent();
		currentScore.setLabel("Score: " + cscore);
		remove(currentScore);
                add(currentScore, 5, 15);
	}
        
        private int cscore;
	private int tscore;
	private int redscore = 10;
	private int orangescore = 8;
	private int yellowscore = 5;
	private int greenscore = 3;
	private int cyanscore = 1;
        
        private GLabel totalScore = new GLabel("");
	private GLabel currentScore = new GLabel("");
        
        
        // MYLIST IMPLEMENTATION //
        private  int counter = 0;
        private int a = 0;
        private int b = 0;
        private int c = 0;

        private MyArrayList<String> leaderboard = new MyArrayList<String>();

        private void HighScore() {
            removeAll();
            GLabel highscore = new GLabel("LEADERBOARD");
            highscore.setFont("-BOLD-36");
            highscore.setColor(Color.RED);
            highscore.setLocation(60, 300);
            add(highscore);
                for(int i = 1; i <= counter; i++){
                           GLabel item = new GLabel(""+ i +". " + leaderboard.get(i));
                           add(item, (getWidth() - item.getWidth())/2, 310 + (i * (item.getHeight() + 3))); 
                        }
            GLabel click = new GLabel ("Click to restart game or X to close game");
            double w = click.getWidth();
            double h = click.getAscent();
            add(click, (WIDTH - w) / 2, ((HEIGHT - h) / 2) + (HEIGHT / 2.5));
        }

        private void ScoreCheck(){
            if(tscore >= a && tscore != 0){
                if (counter < 3){
                    counter++;
                }
                String player = JOptionPane.showInputDialog("Enter player name: ");
                if(leaderboard.size() == 3){
                    leaderboard.remove(3);
                }
                leaderboard.add(1, player + " - " + tscore);
                sort();
                HighScore();
                waitForClick();
            }

            else if(tscore >= b && tscore != 0){
                if (counter < 3){
                    counter++;
                }
                String player = JOptionPane.showInputDialog("Enter player name: ");
                if(leaderboard.size() == 3){
                    leaderboard.remove(3);
                }
                leaderboard.add(2, player + " - " + tscore);
                sort();
                HighScore();
                waitForClick();
            }

            else if(tscore >= c && tscore != 0){
                if (counter < 3){
                    counter++;
                }
                String player = JOptionPane.showInputDialog("Enter player name: ");
                if(leaderboard.size() == 3){
                    leaderboard.remove(3);
                }
                leaderboard.add(3, player + " - " + tscore);
                sort();
                HighScore();
                waitForClick();
            }
        }

        private void sort(){
            if(tscore >= a){
                c = b;
                b = a;
                a = tscore;
            }

            else if(tscore >= b){
                c = b;
                b = tscore;
            }

            else if(tscore >= c){
                c = tscore;
            }
        }
        // END OF SCORES //
        
//        public static void main(String[] args){
//            new Breakout().start(args);
//        }
}