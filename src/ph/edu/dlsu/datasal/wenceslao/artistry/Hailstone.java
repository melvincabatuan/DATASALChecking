/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.artistry;

import acm.program.*;

/**
 *
 * @author Luis Paolo D. Wenceslao
 */
public class Hailstone extends ConsoleProgram {
	public void run() {
		println ("Enter a number to start the Hailstone sequence: "); 
                int n = readInt();
		int n2 = n;
		int j = 1;
		
		while (n != 1) {
			n2 = n;
			if (n %2 ==0) {
				n2 /= 2;
				println (n + " is even, so I take half: " + n2);
				n = n2;
			} else {
				n2 = (3*n + 1);
				println (n + " is odd, so I make 3n+1: " + n2);
				n = n2;
			}
			j++;
		}
		println ("This process took " + j + " steps to reach 1.");
	}
        
//        public static void main(String [] args) {
//        new Hailstone().start(args);
//    }
}
