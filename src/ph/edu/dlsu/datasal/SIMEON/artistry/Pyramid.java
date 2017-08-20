/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.artistry;
import acm.graphics.*;
import acm.program.*;
import java.awt.Color;
/**
 *
 * @author jiggy
 */
public class Pyramid extends GraphicsProgram {
    
    
         public void run() {
     
      GOval oval = new GOval(150, 50, 100, 100 );  
      oval.setFilled(true);
      add(oval);
      oval.setColor(Color.RED);
      
      GOval oval1 = new GOval(165, 65, 70, 70 );  
      oval1.setFilled(true);
      add(oval1);
      oval1.setColor(Color.WHITE);
      
      GOval oval2 = new GOval(185, 83, 30, 30 );  
      oval2.setFilled(true);
      add(oval2);
      oval2.setColor(Color.RED);

   
            }
    
        public static void main(String[] args) {
        new Pyramid().start(args);

        
        // TODO code application logic here
    }
    
}
