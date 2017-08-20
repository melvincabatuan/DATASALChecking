/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.artistry;
import acm.graphics.*;
import acm.program.*;


public class Target extends GraphicsProgram
{
    public void run()
    {

        int height = BRICKS_IN_BASE * BRICK_HEIGHT;
        double pyraheight = (getHeight() - height);
        for (int i=BRICKS_IN_BASE ; i >= 1; i--)
        {
        int pyrawidth = BRICK_WIDTH * i;
        double vertilayer=pyraheight+((i-1)*BRICK_HEIGHT);
        double horizonlayer=(getWidth() - pyrawidth)/2;
            

            // For each brick in the layer...
            for(int a=0 ; a<i ; a++)
            {
                GRect square = new GRect(horizonlayer+ a*BRICK_WIDTH, vertilayer, BRICK_WIDTH, BRICK_HEIGHT);
                add(square);
            }
        }
    }
        public static void main(String[] args) {
        new Target().start(args);

        
        // TODO code application logic here
    }
    private static final int BRICK_WIDTH = 30;
    private static final int BRICK_HEIGHT = 12;
    private static final int BRICKS_IN_BASE = 14;
}

