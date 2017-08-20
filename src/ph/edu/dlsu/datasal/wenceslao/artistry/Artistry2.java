/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.artistry;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

/**
 *
 * @author Luis Paolo D. Wenceslao
 */
public class Artistry2 extends GraphicsProgram {
	
	private static final int NUM_STRINGS = 100;
	
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 600;
	
	public void run() {
		double space = getHeight() / NUM_STRINGS;
		for(int i = 0; i < NUM_STRINGS; i++) {
			double delta = i * space;
			GLine lines = new GLine(0, delta, delta, getHeight());
                        add(lines);
		}
	}
//        public static void main(String [] args) {
//            new Artistry2().start(args);
//        }
}