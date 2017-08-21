package ph.edu.dlsu.velasco.breakout;

/*
 * File: Breakout.java
 * -------------------
 * Name: Neil Oliver Velasco
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.sound.sampled.*;
import ph.edu.dlsu.velasco.myarraylist.*;
import ph.edu.dlsu.velasco.myinterface.*;
import ph.edu.dlsu.velasco.myexception.*;

public class Breakout extends GraphicsProgram {

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 620;

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
    private int NTURNS = 3;

    private int LIVES = NTURNS - 1;

    private final int DELAY = 10;

    private int GameScore = 0;

    private double computeXOffset() {
        return 0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW);
    }

    private GRect bricks = new GRect(BRICK_WIDTH, BRICK_HEIGHT);

    private void drawBrick(int row, int col) {
        double x, y;
        x = computeXOffset() + col * (BRICK_WIDTH + BRICK_SEP);
        y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
        GRect bricks = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        bricks.setFilled(true);
        setBrickColor(bricks, row);
        add(bricks, x, y);
    }

    private void BrickWall() {
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                drawBrick(i, j);
            }
        }
    }

    private int BrickCount = 100;

    private void setBrickColor(GRect brick, int row) {
        switch (row) {
            case 0:
            case 1:
                brick.setFillColor(Color.RED);
                brick.setColor(Color.RED);
                break;
            case 2:
            case 3:
                brick.setFillColor(Color.ORANGE);
                brick.setColor(Color.ORANGE);
                break;
            case 4:
            case 5:
                brick.setFillColor(Color.YELLOW);
                brick.setColor(Color.YELLOW);
                break;
            case 6:
            case 7:
                brick.setFillColor(Color.GREEN);
                brick.setColor(Color.GREEN);
                break;
            default:
                brick.setFillColor(Color.CYAN);
                brick.setColor(Color.CYAN);
                break;
        }
    }

    private final GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);

    private void drawPaddle() {
        paddle.setFilled(true);
        add(paddle, 0.5 * (WIDTH - PADDLE_WIDTH), HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
    }

    private boolean isMouseInside(int x) {
        return ((x > 0.5 * PADDLE_WIDTH) && (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
    }

    public void mouseMoved(MouseEvent me) {
        if (isMouseInside(me.getX())) {
            paddle.setLocation(me.getX() - 0.5 * PADDLE_WIDTH,
                    paddle.getY());
        }
    }

    private final GOval ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);

    private void drawBall() {
        ball.setFilled(true);
        add(ball, 0.5 * WIDTH - BALL_RADIUS, .5 * HEIGHT - BALL_RADIUS);
    }

    private final RandomGenerator rgen = RandomGenerator.getInstance();

    private double vx, vy = 3;

    private boolean isBallInsideX(double x) {
        return ((x > 0.5 * BALL_RADIUS) && (x < WIDTH - 2 * BALL_RADIUS));
    }

    private boolean isBallInsideY(double y) {
        return ((y > 0.5 * BALL_RADIUS));
    }

    private boolean isBallDead(double y) {
        return (y > 600 + BALL_RADIUS);
    }

    private GObject getCollidingObject() {
        if ((getElementAt(ball.getX(), ball.getY())) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
        } else if (getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2)) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
        } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2)) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
        } else {
            return null;
        }
    }

    GLabel label = new GLabel("GAME OVER");
    GLabel tryagain = new GLabel("Click again to continue.");
    GLabel lives = new GLabel("You have " + 2 + " lives left.");
    GLabel brickC = new GLabel("Brick Count: ");
    GLabel FScore = new GLabel("Your Final Score is: ");

    private void drawLabel() {
        label.setFont("MONTSERRAT-48");
        tryagain.setFont("MONTSERRAT-24");
        lives.setFont("MONTSERRAT-24");
        label.setColor(Color.RED);
        tryagain.setColor(Color.RED);
        lives.setColor(Color.RED);
        add(label, 55, 300);
        add(tryagain, 60, 350);
        add(lives, 80, 375);
    }

    private void GameOver() {
        removeLabel();
        label.setFont("MONTSERRAT-48");
        add(label, 55, 300);
        FScore.setLabel("Your Final Score is: " + GameScore);
        FScore.setFont("MONTSERRAT-20");
        add(FScore, 20, 325);
    }

    private void removeLabel() {
        remove(label);
        remove(lives);
        remove(tryagain);
        remove(Score);
        remove(brickC);
        remove(FScore);
    }

    private boolean hitSide(double x, double y, double brickX, double brickY) {
        if (y <= brickY - (BRICK_HEIGHT / 2)) {
            return true;
        }
        if (y >= brickY + (BRICK_HEIGHT / 2)) {
            return true;
        }
        if (x < brickX) {
            return false;
        }
        if (x > brickX) {
            return false;
        } else {
            return true;
        }
    }

    private boolean hitSidePaddle(double x, double y, double paddleX, double paddleY) {
        if (y <= paddleY - (PADDLE_HEIGHT / 2)) {
            return true;
        }
        if (y >= paddleY + (PADDLE_HEIGHT / 2)) {
            return true;
        }
        if (x < paddleX) {
            return false;
        }
        if (x > paddleX + PADDLE_WIDTH) {
            return false;
        } else {
            return true;
        }
    }

    private void YouWin() {
        GLabel Win = new GLabel("Congratulations!");
        Win.setFont("MONTSERRAT-30");
        Win.setColor(Color.GREEN);
        add(Win, 40, 300);
    }

    private void CheckColor(GObject collider) {
        if (collider.getColor() == Color.CYAN) {
            GameScore = GameScore + 20;
        } else if (collider.getColor() == Color.GREEN) {
            GameScore = GameScore + 40;
        } else if (collider.getColor() == Color.YELLOW) {
            GameScore = GameScore + 60;
        } else if (collider.getColor() == Color.ORANGE) {
            GameScore = GameScore + 80;
        } else if (collider.getColor() == Color.RED) {
            GameScore = GameScore + 100;
        }
    }

    GLabel Score = new GLabel("Score: " + GameScore);

    private void moveBall() {
        waitForClick();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        while (true) {
            ball.move(vx, vy);
            pause(DELAY);
            Score.setFont("MONTSERRAT-10");

            if (isBallInsideX(ball.getX()) == false) {
                vx = -vx;
            } else if (isBallInsideY(ball.getY()) == false) {
                vy = -vy;
            } else if (isBallDead(ball.getY()) == true) {
                drawLabel();
                NTURNS--;
                waitForClick();
                if (NTURNS > 0) {
                    lives.setLabel("You have " + (NTURNS - 1) + " life left.");
                    drawBall();
                    removeLabel();
                    run();
                } else {
                    remove(lives);
                    lives.setLabel("You have " + 2 + " lives left.");
                    removeAll();
                    GameOver();
                    waitForClick();
                    run();
                }
            }
            GObject collider = getCollidingObject();
            if (collider == paddle) {
                soundEffect();
                if (hitSidePaddle(ball.getX(), ball.getY(), collider.getX(), collider.getY()) == true) {
                    vy = -vy;
                }
                if (hitSidePaddle(ball.getX(), ball.getY(), collider.getX(), collider.getY()) == false) {
                    vx = -vx;
                }
            } else if (collider == null) {
            } else if (collider == Score || collider == brickC) {
            } else {
                soundEffect();
                if (hitSide(ball.getX(), ball.getY(), collider.getX(), collider.getY()) == true) {
                    vy = -vy;
                }
                if (hitSide(ball.getX(), ball.getY(), collider.getX(), collider.getY()) == false) {
                    vx = -vx;
                }
                CheckColor(collider);
                remove(Score);
                remove(brickC);
                Score.setLabel("Score: " + GameScore);
                BrickCount--;
                brickC.setLabel("Brick Count: " + BrickCount);
                if (BrickCount % 10 == 0) {
                    vy = vy + 1;
                } else if (BrickCount == 0) {
                    removeAll();
                    YouWin();
                }
                remove(collider);
            }
            add(Score, 200, 10);
            add(brickC, 100, 10);
        }//end while   
    }

    private int REPEATS = 0;

    public void displayList(MyList list) {
        removeAll();
        int offset = 3;
        for (int i = 1; i <= 5; i++) {
            GLabel item = new GLabel("" + i + ". " + list.get(i));
            item.setFont("MONTSERRAT-32-BOLD");
            item.setColor(Color.RED);
            add(item, (getWidth() - item.getWidth()) / 2, getWidth() / 10 + i * (item.getHeight() + offset));
        }
        GLabel HighScores = new GLabel("High Scores:");
        HighScores.setFont("MONTSERRAT-12");
        add(HighScores, 150, 10);
        GLabel TryAgain = new GLabel("Click to retry.");
        TryAgain.setFont("MONTSERRAT-20");
        add(TryAgain, 150, 400);
        waitForClick();
        removeAll();
        NTURNS = 3;
        REPEATS++;
        GameScore = 0;
        init();
        run();
    }

    int[] ScoreArray = new int[10];

    private void ListHighScore() {
        MyList<String> Scores = new MyList<>();
        Scores.createList();
        int i;
        int temp = 0;
        if (REPEATS == 0) {
            for (i = 0; i < 10; i++) {
                ScoreArray[i] = 0;
            }
        }
        if (REPEATS > 9) {
            REPEATS = 9;
        }
        ScoreArray[9] = GameScore;
        for (i = 0; i < ScoreArray.length; i++) {
            for (int j = 1; j < (ScoreArray.length - i); j++) {
                if (ScoreArray[j] > ScoreArray[j - 1]) {
                    //swapping
                    temp = ScoreArray[j - 1];
                    ScoreArray[j - 1] = ScoreArray[j];
                    ScoreArray[j] = temp;
                }
            }
        }
        for (i = 1; i <= 5; i++) {
            Scores.add(i, ""+ScoreArray[i - 1]);
        }
        displayList(Scores);
        removeLabel();
    }

    private void soundEffect() {
        File Sound = new File("bounce.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {
        }
    }

    /* Method: init() */
    /**
     * Sets up the Breakout program.
     */ //starting drawings
    public void init() {
        BrickWall();
        drawPaddle();
        addMouseListeners();
        drawBall();
        vy = 3;
        BrickCount = 100;
    }

    private boolean recursiveShield = true;
    /* Method: run() */
    /**
     * Runs the Breakout program.
     */ //execute
    public void run() {
        if(recursiveShield){
            init();
            recursiveShield = false;
        }
        while (NTURNS > 0) {
            moveBall();
        }
        ListHighScore();
    }
    
}
