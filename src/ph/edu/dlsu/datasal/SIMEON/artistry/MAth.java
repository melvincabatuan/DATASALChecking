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
public class MAth extends ConsoleProgram {
    public void run(){
        
        println("Keep entering numbers and enter 0 to end the program and I will display the least and greatest number you entered.");
        double first = readDouble("Enter number: ");
        double max = first;
        double min = first;
        if (first == 0){
            println("You enetered the sentinel number and no more answers shall be accepted.");
        }
        else{
           while(first!=0){
               double second = readDouble("Enter number: ");
               if(second>max){
                   max = second;
                   
               }
                if(second==0)
                   break;
               if(second<min){
                   min=second;
               }

           }
           
    }
        println("Greatest: "+max);
        println("Smallest: "+min);
    }
// i did it 
    // field/state
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MAth().start(args);

        
        // TODO code application logic here
    }
    
}
