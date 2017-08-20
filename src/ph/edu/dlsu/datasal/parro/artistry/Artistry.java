package ph.edu.dlsu.datasal.parro.artistry;

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class Artistry extends GraphicsProgram{
   public void run(GCanvas canvas) {
       GRect rect = new GRect(100, 70, 500, 300);
       rect.setFilled(true);
       rect.setFillColor(Color.lightGray);
       canvas.add(rect);

       GRect rect1 = new GRect(120, 90, 460, 260);
       rect1.setFilled(true);
       rect1.setFillColor(Color.orange);
       canvas.add(rect1);

       GRect rect2 = new GRect(120, 320, 460, 29.5);
       rect2.setFilled(true);
       rect2.setFillColor(Color.blue);
       canvas.add(rect2);

       GArc arc = new GArc(300, 278, 100, 85, 0, 180);
       arc.setFilled(true);
       arc.setFillColor(Color.red);
       canvas.add(arc);
	
	GLabel label = new GLabel("Artistry by Manuel Parro", 450, 345);
        label.setColor(Color.white);
        canvas.add(label);
   }
   
}


