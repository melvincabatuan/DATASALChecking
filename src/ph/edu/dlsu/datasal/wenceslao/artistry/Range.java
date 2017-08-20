/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.artistry;

import acm.graphics.*;
import acm.program.ConsoleProgram;

/**
 *
 * @author Luis Paolo D. Wenceslao
 */
public class Range extends ConsoleProgram {
    
    public void run() {
    
        println("Range Finder");
        int numa = readInt("? ");
        int min = numa;
        int max = numa;
        
        if(numa == 0)
            println("Invalid");
        else
        {
            while (numa != 0)
            {
                int numb = readInt("? ");
                if (numb > max)
                    max = numb;
                else if (numb == 0)
                    break;
                else if (numb < min)
                    min = numb;
            }
        }
        
        println("Minimum value: " + min);
        println("Maximum value: " + max);
    }
        
//    public static void main(String [] args) {
//        new Range().start(args);
//    }
    
}