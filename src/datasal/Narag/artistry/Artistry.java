/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasal.Narag.artistry;

/**
 *
 * @author Gershom Narag      『Heaven's Door』
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

public class Artistry extends GraphicsProgram{
        
       
    public void run(GCanvas canvas){
        GRect body = new GRect (1640/2, 500, 100, 40);
        GOval head = new GOval (40, 40);
        GLine tail = new GLine (20, 20, 100, 50);
        GLine neck = new GLine (70, 20, 40, 40);
        GRect leg1 = new GRect (40, 10);
        GRect leg2 = new GRect (30, 10);
        
        body.setFilled(true);
        head.setFilled(true);
        leg1.setFilled(true);
        leg2.setFilled(true);
        
        body.setFillColor(Color.red);
        head.setFillColor(Color.yellow);
        leg1.setFillColor(Color.yellow);
        leg2.setFillColor(Color.yellow);
        
        canvas.add(body);
        canvas.add(head);
        canvas.add(tail);
        canvas.add(neck);
        canvas.add(leg1);
        canvas.add(leg2);
    }
    
    
   
}
