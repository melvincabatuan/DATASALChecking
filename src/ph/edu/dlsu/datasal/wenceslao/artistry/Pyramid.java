/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.artistry;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/**
 *
 * @author Luis Paolo D. Wenceslao
 */
public class Pyramid extends GraphicsProgram {

	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		putAllBricks();
	}
	private void putAllBricks()
	{
		for( int row = 0; row < BRICKS_IN_BASE; row++ )
		{
			int bricksInRow = BRICKS_IN_BASE - row;
			
			for( int brickNum = 0; brickNum < bricksInRow; brickNum++ )
			{
				
				int x = ( getWidth()/2 ) - (BRICK_WIDTH * bricksInRow) / 2 + brickNum * BRICK_WIDTH;
				int y = getHeight() - BRICK_HEIGHT * (row+1);
				GRect brick = new GRect( x , y , BRICK_WIDTH , BRICK_HEIGHT );
				add(brick);
			}
		}
	}

//    public static void main(String [] args) {
//        new Pyramid().start(args);
//    }
}