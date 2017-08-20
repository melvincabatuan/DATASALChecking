package ph.edu.dlsu.datasal.lee.artistry;

/*
 * File: Artistry.java
 * Name: Lee Inicca 
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */

import acm.graphics.GLabel;
import acm.graphics.*;
import acm.program.*;
import java.awt.Color;
import java.util.ArrayList;


public class Artistry extends GraphicsProgram {
    
        public static final int APPLICATION_WIDTH = 200;
        public static final int APPLICATION_HEIGHT = 200;
        public ArrayList<GObject> collect =new ArrayList();
        
        @Override
	public void run() {
            
            GLabel name = new GLabel("Inicca Lee");
            name.setFont("Serif-Bold-24");
            add(name, 200,250);
            
            GRect face = new GRect(100,100);
            face.setFilled(true);
            face.setFillColor(Color.RED);
            add(face, 100, 100);
            
            
            GOval eye = new GOval(20,40);
            eye.setFilled(true);
            eye.setFillColor(Color.BLACK);
            add(eye, 120, 120);
            
            GOval eye2 = new GOval(20,40);
            eye2.setFilled(true);
            eye2.setFillColor(Color.BLACK);
            add(eye2, 160,120);
            
            GRect mouth = new GRect(40,15);
            mouth.setFilled(true);
            mouth.setFillColor(Color.WHITE);
            add(mouth, 130, 170);
            
            GLine teeth = new GLine(40,0,0,0);
            add(teeth, 170, 180);

            collect.add(name);
            collect.add(face);
            collect.add(eye);
            collect.add(eye2);
            collect.add(mouth);
            collect.add(teeth);

            
	}
}
