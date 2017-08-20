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
public class Add2Integers extends ConsoleProgram {

    public void run() {
        println("This program adds two integers.");
        double n1 = readDouble("Enter n1: ");
        double n2 = readDouble("Enter n2: ");
        double total = n1 + n2;
        println("The total is " + total + ".");
    }
    
//    public static void main(String [] args) {
//        new Add2Integers().start(args);
//    }
    
}
