/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.artistry;
import acm.graphics.*;
import acm.program.*;
/**
 *
 * @author student
 */
public class HelloLBYCP12 extends ConsoleProgram {
    public void run(){
       
       println("This program applies the Pyhtagorean Theorem.");
        double a = readDouble("Enter n1: ");
        double b = readDouble("Enter n2: ");
        double c = Math.sqrt((a*a) + (b*b)) ;
       println("The answer is: "+ c);
    }
// i did it 
    // field/state
    
    static int age=10;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new HelloLBYCP12().start(args);
 
        
        // TODO code application logic here
    }
    
}
