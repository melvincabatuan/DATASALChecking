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
public class Hailstone extends ConsoleProgram {
    public void run(){
        
        println("HAILSTONE SEQUENCE.");
        double first = readDouble("Enter number: ");
        int i= 0;
        do{
        if ((first%2)==0){
            double half = first/2;
           
            println(first+" is even so we take half : "+half);
             first=half;
             i++;
        }
        else{
        double odd = (3*first)+1;
        
            println(first+" is odd so we make 3n+1 : "+odd);
            first=odd;
            i++;
        }
        
        }while(first!=1);
        println("The process took "+i+" steps to run ");
        


    /**
     * @param args the command line arguments
     */
    }
    public static void main(String[] args) {
        new Hailstone().start(args);

        
        // TODO code application logic here
    }
    
}

