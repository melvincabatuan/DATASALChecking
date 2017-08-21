package ph.edu.dlsu.datasal.santos.artistry;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		putAllBricks();
	}
	private void putAllBricks(){
            GRect rect0 = new GRect(0, 0, 1000, 1000);
            rect0.setFilled(true);
            rect0.setColor(Color.ORANGE);
            add(rect0);
            GOval oval1 = new GOval (0, 0, 50, 30);
            oval1.setFilled(true);
            oval1.setFillColor(Color.RED);
            add (oval1);
            GRect rect1 = new GRect(400, 50, 100, 600);
            rect1.setFilled(true);
            rect1.setColor(Color.GREEN);
            add(rect1);
            GRect rect2 = new GRect(4, 50, 500, 500);
            rect2.setFilled(true);
            rect2.setColor(Color.BLUE);
            add(rect2);
            GRect rect3 = new GRect(400, 10, 634, 23);
            rect3.setFilled(true);
            rect3.setColor(Color.RED);
            add(rect3);
            GOval oval2 = new GOval(150, 353, 100, 243);
            oval2.setFilled(true);
            oval2.setColor(Color.GREEN);
            add(oval2);
            double size = 200;
            double x = (getWidth() - size) / 2;
            double y = (getHeight() - size) / 2;
            GArc arc = new GArc(x, y, size, size, 45, 270);
            arc.setFilled(true);
            arc.setFillColor(Color.YELLOW);
            add(arc);
            GOval oval3 = new GOval (400, 220, 33, 33);
            oval3.setFilled(true);
            oval3.setFillColor(Color.GREEN);
            add (oval3);
            GOval oval4 = new GOval (450, 220, 33, 33);
            oval4.setFilled(true);
            oval4.setFillColor(Color.RED);
            add (oval4);
            GOval oval5 = new GOval (500, 220, 33, 33);
            oval5.setFilled(true);
            oval5.setFillColor(Color.YELLOW);
            add (oval5);
            GOval oval6 = new GOval (550, 220, 33, 33);
            oval6.setFilled(true);
            oval6.setFillColor(Color.BLUE);
            add (oval6);
            add (new GLabel("Artistry by Cellix Mark Santos"), 600, 470);
	}
        public static void main(String[] args) {
        new Pyramid().start(args);
    }
}