package ph.edu.dlsu.datasal.santos.breakout;

/**
 * Cellix Mark T. Santos - 11416718 LBYCP12-EQ1 May 24, 2017
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Breakout extends GraphicsProgram {

    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_Y_OFFSET = 30;
    private static final int NBRICKS_PER_ROW = 10;
    private static final int NBRICK_ROWS = 10;
    private static final int BRICK_SEP = 4;
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    private static final int BRICK_HEIGHT = 8;
    private static final int BALL_RADIUS = 5;
    private static final int BRICK_Y_OFFSET = 70;
    private int NTURNS;
    private double vx, vy;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private static final int DELAY = 13;
    private static final double BRICK_X_OFFSET = 0.5 * (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW);
    private int brickCounter;
    private MyList<String> leaderboard = new MyList<String>();

    public static void main(String[] args) {
        new Breakout().start(args);
    }

    public void run() {
        leaderboard.createList();
        for (int i = 0; i > -1; i++) {
            startgame();
        }
    }

    private void startgame() {
        removeAll();
        NTURNS = 3;
        brickCounter = 100;
        points = 0;
        putAllBricks();
        paddle();
        onegame();
    }

    private GLabel lifeLabel = new GLabel("");

    private int maxlives = 3;

    private void lifeleft(int i) {
        int livesleft = maxlives - i;
        lifeLabel.setLabel("Lives: " + livesleft);
        remove(lifeLabel);
        add(lifeLabel, 0, 10);
    }

    private int turn;

    private void onegame() {
        add(lifeLabel);
        add(scoreLabel);
        showscore();
        turn = 0;
        for (int i = 0; i < NTURNS; i++) {
            lifeleft(i);
            theBall();
            playGame();
        }
        remove(lifeLabel);
        lifeLabel.setLabel("Lives: 0");
        add(lifeLabel, 0, 10);
        if (turn == 3) {
            gameover();
        }
    }

    private GLabel end = new GLabel("Game Over!");
    private double w = end.getWidth();
    private double h = end.getAscent();

    private void gameover() {
        removeAll();
        GLabel finalscore = new GLabel("Your Score: " + points);
        add(end, (WIDTH - w) / 2, (HEIGHT - h) / 2);
        double w2 = finalscore.getWidth();
        double h2 = finalscore.getAscent();
        add(finalscore, (WIDTH - w2) / 2, ((HEIGHT - h2) / 3) + (HEIGHT / 4));
        waitForClick();
        checkscores();
    }

    private int first = 0;
    private int second = 0;
    private int third = 0;
    private int counter = 0;

    private void checkscores() {
        if (points >= first && points != 0) {
            removeAll();
            GLabel win = new GLabel("Congratulations! You got the High Score!");
            add(win, 100, (HEIGHT - h) / 2);
            if (counter < 3) {
                counter++;
            }
            String nam = JOptionPane.showInputDialog("Enter name: ");
            if (leaderboard.size() == 3) {
                leaderboard.remove(3);
            }
            leaderboard.add(1, nam + "   :  " + points);
            sortscores();
            showscores();
            waitForClick();
        } else if (points >= second && points != 0) {
            removeAll();
            GLabel top = new GLabel("You made it to second place!");
            add(top, 100, (HEIGHT - h) / 2);
            if (counter < 3) {
                counter++;
            }
            String nam = JOptionPane.showInputDialog("Enter player name: ");
            if (leaderboard.size() == 3) {
                leaderboard.remove(3);
            }
            leaderboard.add(2, nam + "   :  " + points);
            sortscores();
            showscores();
            waitForClick();
        } else if (points >= third && points != 0) {
            removeAll();
            GLabel top = new GLabel("You made it to third place!");
            add(top, 100, (HEIGHT - h) / 2);
            if (counter < 3) {
                counter++;
            }
            String nam = JOptionPane.showInputDialog("Enter player name: ");
            if (leaderboard.size() == 3) {
                leaderboard.remove(3);
            }
            leaderboard.add(3, nam + "   :  " + points);
            sortscores();
            showscores();
            waitForClick();
        } else {
            removeAll();
            GLabel nope = new GLabel("You did not make it to the top 3 better luck next time");
            add(nope, 50, (HEIGHT - h) / 2);
            waitForClick();
            showscores();
            waitForClick();
        }
    }

    private void winner() {
        removeAll();
        NTURNS = 0;
        GLabel win = new GLabel("Congratulations! You WON!");
        add(win, 100, (HEIGHT - h) / 2);
        GLabel finalscore = new GLabel("Your Score: " + points);
        double w2 = finalscore.getWidth();
        double h2 = finalscore.getAscent();
        add(finalscore, (WIDTH - w2) / 2, ((HEIGHT - h2) / 3) + (HEIGHT / 4));
        waitForClick();
        checkscores();
    }

    private void sortscores() {
        if (points >= first) {
            third = second;
            second = first;
            first = points;
        } else if (points >= second) {
            third = second;
            second = points;
        } else if (points >= third) {
            third = points;
        }
    }

    private void showscores() {
        removeAll();
        GLabel hslist = new GLabel("High score list");
        add(hslist, 150, (HEIGHT - h) / 3);
        for (int i = 1; i <= counter; i++) {
            GLabel item = new GLabel("#" + i + " ) " + leaderboard.get(i));
            add(item, (getWidth() - item.getWidth()) / 2, 200 + (i * (item.getHeight() + 3)));
        }
    }

    private void putAllBricks() {
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int brickNum = 0; brickNum < NBRICKS_PER_ROW; brickNum++) {
                double x = BRICK_X_OFFSET + brickNum * (BRICK_WIDTH + BRICK_SEP);
                double y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
                GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                if (row == 0 || row == 1) {
                    brick.setColor(Color.RED);
                    brick.setFilled(true);
                } else if (row == 2 || row == 3) {
                    brick.setColor(Color.YELLOW);
                    brick.setFilled(true);
                } else if (row == 4 || row == 5) {
                    brick.setColor(Color.ORANGE);
                    brick.setFilled(true);
                } else if (row == 6 || row == 7) {
                    brick.setColor(Color.GREEN);
                    brick.setFilled(true);
                } else if (row == 8 || row == 9) {
                    brick.setColor(Color.CYAN);
                    brick.setFilled(true);
                }
                add(brick);
            }
        }
    }

    private GRect pad;

    private void paddle() {
        double x = getWidth() / 2 - PADDLE_WIDTH / 2;
        double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        pad = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        pad.setColor(Color.PINK);
        pad.setFilled(true);
        add(pad);
        addMouseListeners();
    }

    private boolean isMouseXInsideWindow(int x) {
        return ((x > 0.5 * PADDLE_WIDTH) && (x < (WIDTH - 0.5 * PADDLE_WIDTH)));
    }

    public void mouseMoved(MouseEvent me) {
        if (isMouseXInsideWindow(me.getX())) {
            pad.setLocation(me.getX() - 0.5 * PADDLE_WIDTH,
                    pad.getY());
        }
    }

    private GOval ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);

    private void theBall() {
        ball.setColor(Color.MAGENTA);
        ball.setFilled(true);
        add(ball, 0.5 * WIDTH - BALL_RADIUS, .5 * HEIGHT - BALL_RADIUS);
    }

    private void playGame() {
        waitForClick();
        getBallVelocity();
        while (true) {
            showscore();
            moveBall();
            if (ball.getY() >= getHeight()) {
                turn += 1;
                break;
            }
            if (brickCounter == 0) {
                winner();
                break;
            }
        }
    }

    private void getBallVelocity() {
        vy = 4.0;
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
    }

    private void moveBall() {
        ball.move(vx, vy);
        if ((ball.getX() - vx <= 0 && vx < 0) || (ball.getX() + vx >= (getWidth() - BALL_RADIUS * 2) && vx > 0)) {
            vx = -vx;
        }
        if ((ball.getY() - vy <= 0 && vy < 0)) {
            vy = -vy;
        }
        GObject collider = getCollidingObject();
        if (collider == pad) {
            if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 && ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 + 4) {
                vy = -vy;
            }
        } else if ((collider != null) && (collider != scoreLabel)) {
            brickpoints(collider.getColor());
            remove(collider);
            brickCounter--;
            vy = -vy;
        }
        pause(DELAY);
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

    private int rP = 13;
    private int yP = 10;
    private int oP = 7;
    private int gP = 5;
    private int cP = 3;

    private void brickpoints(Color col) {
        if (col == Color.RED) {
            points += rP;
        } else if (col == Color.YELLOW) {
            points += yP;
        } else if (col == Color.ORANGE) {
            points += oP;
        } else if (col == Color.GREEN) {
            points += gP;
        } else if (col == Color.CYAN) {
            points += cP;
        }
    }

    private GLabel scoreLabel = new GLabel("");

    private void showscore() {
        scoreLabel.setLabel("Score: " + points);
        remove(scoreLabel);
        add(scoreLabel, 300, 10);
    }

    private int points;

}
