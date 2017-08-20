/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.artistry;

import acm.program.*;
import static java.lang.Math.sqrt;

/**
 *
 * @author Luis Paolo D. Wenceslao
 */
public class Pythagorean extends ConsoleProgram {

    public void run() {
        
        println("Enter values to compute the Pythagorean Theorem: ");
        double x = readDouble("Enter a number for x: ");
        double y = readDouble("Enter a number for y: ");
        double z = sqrt((x*x) + (y*y));
        println("\nValue for z is " + z);
    }
    
//    public static void main(String [] args) {
//        new Pythagorean().start(args);
//    }
    
}
