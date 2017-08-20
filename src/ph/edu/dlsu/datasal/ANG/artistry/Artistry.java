/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.artistry;

/**
 * Ang, Ryan Jasper V.
 * 11428422
 * LBYCP12-EQ1
 * 2017-05-23
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class Artistry extends GraphicsProgram {

    public void run() {
        GRect rect = new GRect( 150, 150);
        double x = (getWidth() - rect.getWidth()) / 2;
        double y = (getHeight() - rect.getHeight()) / 2;
        rect.setFilled(true);
        rect.setColor(Color.BLUE);
        add(rect, x, y);

        GOval oval = new GOval( 72+72, 72+72);

        double a = (getWidth() - oval.getWidth()) / 2;
        double b = (getHeight() - oval.getHeight()) / 2;
        add(oval, a, b);
        oval.setFilled(true);
        oval.setColor(Color.RED);

        GOval oval2 = new GOval( 62*2, 62*2);

        add(oval2, x, y);
        oval2.setFilled(true);
        oval2.setColor(Color.ORANGE);

        GOval oval3 = new GOval( 52*2, 52*2);

        add(oval3, x, y);
        oval3.setFilled(true);
        oval3.setColor(Color.YELLOW);

        GLine line = new GLine(15,15,15, 200);
        line.setColor(Color.RED);
        add(line);

        GLine line3 = new GLine(15,15,200, 15);
        line3.setColor(Color.RED);
        add(line3);

        GLine line2 = new GLine(20,20,20, 205);
        line2.setColor(Color.BLACK);
        add(line2);

        GLine line4 = new GLine(20,20,205, 20);
        line4.setColor(Color.BLACK);
        add(line4);

        add(new GLabel("Artistry by Ryan Ang"), 360, 380);
    }


public static void main(String[] args) {
        new Artistry().start();
}
}

