/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.breakout;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import ph.edu.dlsu.kitane.listdemo.MyArrayList;
import ph.edu.dlsu.kitane.listdemo.MyArrayListInteger;
//@author Christoph Kitane
public class Breakout extends GraphicsProgram implements Runnable{

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    public static final int WIDTH = APPLICATION_WIDTH;
    public static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 50;

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
    private static final GOval ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
    private static final GRect paddle = new GRect((WIDTH / 2) - (PADDLE_WIDTH / 2), HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
    private static final int TIME_DELAY = 10;
    private GRect brick;
    private int r;
    private int c;
    private File bounceClip = new File("bounce.wav");
    private GLabel scoreLabel;
    private GLabel brickCounter;
    private int sc1 = 0, sc2 = 0, sc3 = 0, sc4 = 0, sc5 = 0;
    //Creating List
    private MyArrayList<String> score = new MyArrayList<>();
    private GLabel topscorer;
    private int oldsc = 0, check =0;

    private void topscore() {
        topscorer = new GLabel("Top Scorer: Player - " + oldsc, BRICK_SEP, BRICK_Y_OFFSET - 40);

        if (oldsc < sc1) {
            topscorer.setLabel("Top Score: Player - " + sc1);
        }
        add(topscorer);
    }

    public void displayList(MyArrayList<String> list) {
        int offset = 3;
        for (int i = 1; i <= list.size(); i++) {
            GLabel item = new GLabel("" + i + ". " + list.get(i));
            item.setFont("Arial-18-italic");
            item.setColor(Color.ORANGE);
            add(item, (WIDTH * 0.5 - item.getWidth() * 0.5), BRICK_Y_OFFSET - 40 + i * (item.getHeight() + offset));
        }
    }

    private void scorelist() {

        score.createList();
        GLabel title = new GLabel("TOP SCORE:");
        title.setFont("Arial-*-20");
        title.setColor(Color.ORANGE);
        add(title, WIDTH * 0.5 - title.getWidth() * 0.5, BRICK_Y_OFFSET - 40);
        score.add(1, "" + sc1);
        score.add(2, "" + sc2);
        score.add(3, "" + sc3);
        score.add(4, "" + sc4);
        score.add(5, "" + sc5);
        if (oldsc > sc1) {
            score.add(1, "" + oldsc);
            sc1 = oldsc;
            score.remove(6);
            displayList(score);
        } else if (oldsc > sc2 && oldsc < sc1) {
            score.add(2, "" + oldsc);
            sc2 = oldsc;
            score.remove(6);
            displayList(score);
        } else if (oldsc > sc3 && oldsc < sc2) {
            score.add(3, "" + oldsc);
            sc3 = oldsc;
            score.remove(6);
            displayList(score);
        } else if (oldsc > sc4 && oldsc < sc3) {
            score.add(4, "" + oldsc);
            sc4 = oldsc;
            score.remove(6);
            displayList(score);
        } else if (oldsc > sc5 && oldsc < sc4) {
            score.add(5, "" + sc5);
            sc5 = oldsc;
            score.remove(6);
            displayList(score);
        } else {
            displayList(score);
        }

    }

    /* Method: init() */
    /**
     * Sets up the Breakout program.
     */
    public void init() {
        /* You fill this in, along with any subsidiary methods */
        BrickWallSetup();
        PaddleSetup();
        addMouseListeners();
        BallSetup();
        scoreLabel();
        run();
    }
    //Creates the Grid of Bricks

    private void BrickWallSetup() {
        for (r = 0; r < NBRICK_ROWS; r++) {
            for (c = 0; c < NBRICKS_PER_ROW; c++) {
                BrickSetup(r, c);
            }
        }
    }

    //Creates the properties of the bricks
    private void BrickSetup(int row, int col) {
        double x, y;
        brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        x = computeXOffset() + col * (BRICK_WIDTH + BRICK_SEP);
        y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
        brick.setFilled(true);
        if (row < 2) {
            brick.setColor(Color.RED);
        }
        if (row == 2 || row == 3) {
            brick.setColor(Color.ORANGE);
        }
        if (row == 4 || row == 5) {
            brick.setColor(Color.YELLOW);
        }
        if (row == 6 || row == 7) {
            brick.setColor(Color.GREEN);
        }
        if (row == 8 || row == 9) {
            brick.setColor(Color.CYAN);
        }
        add(brick, x, y);
    }

    private double computeXOffset() {
        return 0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW);
    }

    //colors of the bricks
    private void PaddleSetup() {

        paddle.setFilled(true);
        add(paddle);

    }

    private void BallSetup() {
        ball.setFilled(true);
        add(ball, 0.5 * WIDTH - BALL_RADIUS, 0.5 * HEIGHT - BALL_RADIUS);
    }

    //This Checks if the mouse is inside the window.
    private boolean isMouseXInsideWindow(int x) {
        return ((x > 0.5 * PADDLE_WIDTH) && (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
    }

    //Sets the Paddle to the x-position of the Mouse.
    public void mouseMoved(MouseEvent me) {
        if (isMouseXInsideWindow(me.getX())) {
            paddle.setLocation(me.getX() - 0.5 * PADDLE_WIDTH, paddle.getY());
        }
    }

    private boolean isMouseOnRestart(int x, int y) {
        return ((x > restart.getX()) && x < (restart.getX() + restart.getWidth()) && y > (restart.getY() - restart.getHeight() * 0.5) && y < (restart.getY() + restart.getHeight() * 0.5));
    }

    public void mouseClicked(MouseEvent me) {
        if (isMouseOnRestart(me.getX(), me.getY()) && (brickCount == 0 || ball.getY() > HEIGHT)) {
            ball.setVisible(true);
            removeAll();
            BrickWallSetup();
            PaddleSetup();
            addMouseListeners();
            BallSetup();
            scoreLabel();
            topscore();
            sc = 0;
            brickCount = 100;
        }
    }

    private void scoreLabel() {
        scoreLabel = new GLabel("Score: 0");
        scoreLabel.setFont("Arial-Italic-10");
        add(scoreLabel, paddle.getX() + scoreLabel.getWidth() / 2, HEIGHT - PADDLE_Y_OFFSET + PADDLE_HEIGHT);
        brickCounter = new GLabel("Brick Count: 100");
        brickCounter.setFont("Arial-Italic-10");
        add(brickCounter, paddle.getX() - brickCounter.getWidth() - scoreLabel.getWidth(), HEIGHT - PADDLE_Y_OFFSET + PADDLE_HEIGHT);
    }

    //PLAYING PART OF THE GAME
    private double vx, vy;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private int brickCount = 100;
    private int sc = 0;
    private GObject collider;
    private GLabel restart;

    private void Ballmotion() {
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        vy = 3.0;

        while (true) {
            ball.move(vx, vy);
            pause(TIME_DELAY);
            collider = collisions();

            if (collider == paddle) {
                vy = -vy;
            } else if (collider != null && collider != paddle && collider != brickCounter && collider != scoreLabel && collider != topscorer) {
                sFX();
                score();
                vy = -vy;
                brickCount--;
                brickCounter.setLabel("Brick Count: " + brickCount);
                remove(collider);
            }
            checkWall();
            if (ball.getY() >= HEIGHT || brickCount == 0) {
                break;
            }
        }
    }

    //Collistion of Walls
    private void checkWall() {
        if (ball.getX() <= 0) {
            vx = -vx;
        } else if (((ball.getX()) + 2 * BALL_RADIUS) >= WIDTH) {
            vx = -vx;
        } else if (ball.getY() <= 0) {
            vy = -vy;
        }
    }

    private GObject collisions() {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
        } else if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
            return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
        }
        return null;
    }

    /* Method: run() */
    /**
     * Runs the Breakout program.
     */
    public void run() {
        System.out.println("Breakout is called.");
        if(check!=1){
            check =1;
            init();
        }
        do {
            waitForClick();
            topscore();
            while (true) {
                Ballmotion();
                if (ball.getY() >= HEIGHT) {
                    break;
                } else if (brickCount == 0) {
                    break;
                }
            }
            removeAll();
            if (brickCount == 0) {
                ball.setVisible(false);
                printWinner();

            } else if (brickCount > 0) {
                printGameOver();
            }

            restart = new GLabel("RESTART");
            restart.setFont("Arial-*-16");
            add(restart, WIDTH * 0.5 - restart.getWidth() * 0.5, HEIGHT * 0.5 + BRICK_Y_OFFSET);

            oldsc = sc;
            scorelist();
        } while (true);
    }

    private void score() {
        if (collider != null && collider != paddle) {
            if (collider.getColor() == Color.CYAN) {
                sc += 2;
                scoreLabel.setLabel("Score: " + sc);
            }
            if (collider.getColor() == Color.GREEN) {
                sc += 4;
                scoreLabel.setLabel("Score: " + sc);
            }
            if (collider.getColor() == Color.YELLOW) {
                sc += 6;
                scoreLabel.setLabel("Score: " + sc);
            }
            if (collider.getColor() == Color.ORANGE) {
                sc += 8;
                scoreLabel.setLabel("Score: " + sc);
            }
            if (collider.getColor() == Color.RED) {
                sc += 10;
                scoreLabel.setLabel("Score: " + sc);
            }
        }
    }

    private void sFX() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(bounceClip));
            clip.start();
        } catch (Exception e) {
        }
    }

    private void printGameOver() {
        GLabel gameOver = new GLabel("Game Over");
        gameOver.setColor(Color.RED);
        gameOver.setFont("Arial-Bold-24");
        add(gameOver, (WIDTH * 0.5 - gameOver.getWidth() * 0.5), (HEIGHT * 0.5 - gameOver.getHeight() * 0.5));
    }

    private void printWinner() {
        GLabel Winner = new GLabel("Winner!!", getWidth() / 2, getHeight() / 2);
        Winner.setFont("Arial-Bold-24");
        Winner.setColor(Color.RED);
        add(Winner, (WIDTH * 0.5 - Winner.getWidth() * 0.5), (HEIGHT * 0.5 - Winner.getHeight() * 0.5));
    }

    /*public static void main(String[] args) {
        new Breakout().start(args);
    }*/
}
