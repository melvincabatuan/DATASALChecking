/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
package ph.edu.dlsu.datasal.SIMEON.artistry;
import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

public class ARTIST extends GraphicsProgram {
            
        public void init() {
      GOval oval = new GOval(287.5, 100, 50,50 );
      GOval oval2 = new GOval(250,50,50,50);
      GOval oval3 = new GOval(250, 150, 50,50 );
      GOval oval4 = new GOval(325,50,50,50);
      GOval oval5 = new GOval(325, 150, 50,50 );
      
      GOval oval6 = new GOval(287.5, 275, 50,50 );
      GOval oval7 = new GOval(250,225,50,50);
      GOval oval8 = new GOval(250, 325, 50,50 );
      GOval oval9 = new GOval(325,225,50,50);
      GOval oval10 = new GOval(325, 325, 50,50 );
      
      GOval oval11 = new GOval(487.5, 100, 50,50 );
      GOval oval12 = new GOval(450,50,50,50);
      GOval oval13 = new GOval(450, 150, 50,50 );
      GOval oval14 = new GOval(525,50,50,50);
      GOval oval15 = new GOval(525, 150, 50,50 );

      GOval oval16 = new GOval(487.5, 275, 50,50 );
      GOval oval17 = new GOval(450,225,50,50);
      GOval oval18 = new GOval(450, 325, 50,50 );
      GOval oval19 = new GOval(525,225,50,50);
      GOval oval20 = new GOval(525, 325, 50,50 );
      oval.setFilled(true);
      oval.setColor(Color.yellow);
      oval2.setFilled(true);
      oval2.setColor(Color.red);
      oval3.setFilled(true);
      oval3.setColor(Color.orange);
      oval4.setFilled(true);
      oval4.setColor(Color.green);
      oval5.setFilled(true);
      oval5.setColor(Color.blue);
      oval6.setFilled(true);
      oval6.setColor(Color.yellow);
      oval7.setFilled(true);
      oval7.setColor(Color.red);
      oval8.setFilled(true);
      oval8.setColor(Color.orange);
      oval9.setFilled(true);
      oval9.setColor(Color.green);
      oval10.setFilled(true);
      oval10.setColor(Color.blue);
      oval11.setFilled(true);
      oval11.setColor(Color.yellow);
      oval12.setFilled(true);
      oval12.setColor(Color.red);
      oval13.setFilled(true);
      oval13.setColor(Color.orange);
      oval14.setFilled(true);
      oval14.setColor(Color.green);
      oval15.setFilled(true);
      oval15.setColor(Color.blue);
      oval16.setFilled(true);
      oval20.setColor(Color.yellow);
      oval17.setFilled(true);
      oval19.setColor(Color.red);
      oval18.setFilled(true);
      oval18.setColor(Color.orange);
      oval19.setFilled(true);
      oval17.setColor(Color.green);
      oval20.setFilled(true);
      oval16.setColor(Color.blue);
      add(oval);
      add(oval2);
      add(oval3);
      add(oval4);
      add(oval5);
      add(oval6);
      add(oval7);
      add(oval8);
      add(oval9);
      add(oval10);
      add(oval11);
      add(oval12);
      add(oval13);
      add(oval14);
      add(oval15);
      add(oval16);
      add(oval17);
      add(oval18);
      add(oval19);
      add(oval20);
      

      

   
            }
            
        
        public static void main(String[] args) {
        new ARTIST().start(args);

        
        // TODO code application logic here
    }
        }
    

