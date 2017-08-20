package ph.edu.dlsu.datasal.parro.breakout;

import ph.edu.dlsu.datasal.parro.myarraylist.MyArrayList;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH
            = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /* Method: init() */
    /**
     * Sets up the Breakout program.
     */
    private void setup() {
        drawBrickWall();
        drawPaddle();
        drawBall();
        setupScore();
    }

    /* Method: run() */
    /**
     * Runs the Breakout program.
     */
    public void run() {
        highscore.createList();
        setup();
        lives = 3;
	for(int i=0; i < lives; i++) {
			playGame();
			if(brickCount == 0) {
				ball.setVisible(false);
				printWinner();
				break;
			}
			if(brickCount > 0) {
				removeAll();
			}
		}
		if(brickCount > 0) {
			printGameOver();    
		}
    }

    //private instance variables
    private GRect brick;
    private GRect paddle;
    private GOval ball;
    private int brickCount = NBRICK_ROWS * NBRICKS_PER_ROW;
    private double vx, vy;
    private final RandomGenerator rgen = RandomGenerator.getInstance();
    private GLabel scoreLabel;
    private int score;
    private int lives = NTURNS;

    private double computeXOffset() {
        return 0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW);
    }

    private void drawBrickWall() {
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICK_ROWS; j++) {
                drawBrick(i, j);
            }
        }
    }

    private void drawBrick(int row, int col) {
        double x, y; //location of Bricks
        brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        x = computeXOffset() + col * (BRICK_WIDTH + BRICK_SEP);
        y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
        brick.setFilled(true);
        setBrickColor(brick, row);
        add(brick, x, y);
    }

    private void setBrickColor(GRect brick, int row) {
        switch (row) {
            case 0:
            case 1:
                brick.setColor(Color.RED);
                brick.setFillColor(Color.RED);
                break;
            case 2:
            case 3:
                brick.setColor(Color.ORANGE);
                brick.setFillColor(Color.ORANGE);
                break;
            case 4:
            case 5:
                brick.setColor(Color.YELLOW);
                brick.setFillColor(Color.YELLOW);
                break;
            case 6:
            case 7:
                brick.setColor(Color.GREEN);
                brick.setFillColor(Color.GREEN);
                break;
            case 8:
            case 9:
                brick.setColor(Color.CYAN);
                brick.setFillColor(Color.CYAN);
        }
    }

    private void drawPaddle() {
        paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle, 0.5 * (WIDTH - PADDLE_WIDTH), HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        addMouseListeners();
    }

    public void mouseMoved(MouseEvent e) {
        if ((e.getX() < getWidth() - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) {
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
    }

    private void drawBall() {
        double x = getWidth() / 2 - BALL_RADIUS ;
        double y = getHeight() / 2 - BALL_RADIUS;
        ball = new GOval(x, y, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        add(ball);
    }

    private void playGame() {
        
        GLabel readyMessage = new GLabel("You have " + lives + " " + "tries left. Click to begin!");
        if(lives != 0){
        add(readyMessage, WIDTH / 2 - readyMessage.getWidth() / 2, HEIGHT * 0.75 - readyMessage.getAscent());
        waitForClick();
        }
        
        remove(readyMessage);
        getBallVelocity();
        
        while (brickCount > 0  &&  lives > 0) { //plays the game as long there are bricks and lives left
            moveBall();
	}
        
        if (brickCount <= 0){
            removeAll();
            printWinner();
        }
        else {
            removeAll();
            printGameOver();
        }
    }
    
    private void getBallVelocity() {
        vy = 3.0;
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
    }
    
    private void moveBall() {
        ball.move(vx, vy);

        if ( ball.getX() >= WIDTH - BALL_RADIUS) //right wall
            vx = -vx;
        if ( ball.getX() <= 0) //left wall
            vx = -vx;
        if ( ball.getY() >= HEIGHT - BALL_RADIUS) { //bottom wall
            lives--;
            drawBall();
            playGame();
        }
        if (ball.getY() <= 0) //top wall
            vy = -vy;        

        //check for other objects
        GObject collider = getCollidingObject();
        if (collider == paddle) {
            vx = getNewVx(vx);
            if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 && ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 + 4) {
                vy = -vy;
            }
        }
        
        else if (collider instanceof GRect) {
            //playSound();
            if (collider.getColor() == Color.CYAN) {
                addScore(1);
            } else if (collider.getColor() == Color.GREEN) {
                addScore(5);
            } else if (collider.getColor() == Color.YELLOW) {
                addScore(10);
            } else if (collider.getColor() == Color.ORANGE) {
                addScore(20);
            } else if (collider.getColor() == Color.RED) {
                addScore(25);
            }
            
            remove(collider);
            brickCount--;
            vy = -vy;
            
        }
        else if (collider instanceof GLabel) {

        }
        pause(5);
    }

    private void setupScore() {
        score = 0;
        scoreLabel = new GLabel("Score: " + score + " ");
        scoreLabel.setFont("SansSerif-12");
        scoreLabel.setLocation(5, 15); 
        add(scoreLabel);
    }

    private void addScore(int value) {
        score += value;
        scoreLabel.setLabel("Score: " + score + " ");
        scoreLabel.setLocation(5, 15);
    }
    
    private GObject getCollidingObject() {

        GObject UpperLeft = getElementAt(ball.getX(), ball.getY());
        GObject LowerLeft = getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2));
        GObject UpperRight = getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY());
        GObject LowerRight = getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2));

        if (UpperLeft != null) {
            return UpperLeft;
        } else if (LowerLeft != null) {
            return LowerLeft;
        } else if (UpperRight != null) {
            return UpperRight;
        } else if (LowerRight != null) {
            return LowerRight;
        } else {
            return null;
        }
    }
    
    private double getNewVx(double vx){
        GPoint UpperLeft = new GPoint(paddle.getX(), paddle.getY());
        GPoint UpperRight = new GPoint(paddle.getX() + PADDLE_WIDTH, paddle.getY());
        GPoint LowerLeft = new GPoint(paddle.getX(), paddle.getY() + PADDLE_HEIGHT);
        GPoint LowerRight = new GPoint(paddle.getX() + PADDLE_WIDTH, paddle.getY());

        if(ball.contains(UpperLeft)) return Math.abs(vx) * -1;
        if(ball.contains(UpperRight)) return Math.abs(vx);
        if(ball.contains(LowerLeft)) return  Math.abs(vx) * -1;
        if(ball.contains(LowerRight)) return Math.abs(vx);

        else return vx;
    }

    private void printGameOver() {
        GLabel gameOver = new GLabel("Game Over", getWidth() / 2, getHeight() / 2);
        gameOver.move(-gameOver.getWidth() / 2, -gameOver.getHeight());
        gameOver.setColor(Color.RED);
        add(gameOver);
        GLabel showscore = new GLabel("Score: " + score);
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
        GLabel showscore = new GLabel("Score: " + score);
        showscore.setColor(Color.RED);
        add(showscore, (getWidth()-showscore.getWidth())/2, (getHeight()-Winner.getHeight() + showscore.getHeight())/2);
        checkScore();
    }
    
    private void restart(){
        removeAll();
        lives = 3;
        setup();
        playGame();
    } 

    //MyList implementation starts here
    
    private  int counter = 0;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    
    private MyArrayList<String> highscore = new MyArrayList<String>();

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
        if(score >= first && score != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(1, player + " - " + score);
            sort();
            showHighScore();
            waitForClick();
        }
        
        else if(score >= second && score != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(2, player + " - " + score);
            sort();
            showHighScore();
            waitForClick();
        }
        
        else if(score >= third && score != 0){
            if (counter < 3){
                counter++;
            }
            String player = JOptionPane.showInputDialog("Enter player name: ");
            if(highscore.size() == 3){
                highscore.remove(3);
            }
            highscore.add(3, player + " - " + score);
            sort();
            showHighScore();
            waitForClick();
        }
    }
    
    private void sort(){
        if(score >= first){
            third = second;
            second = first;
            first = score;
        }
            
        else if(score >= second){
            third = second;
            second = score;
        }
        
        else if(score >= third){
            third = score;
        }
    }    
}