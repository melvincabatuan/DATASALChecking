/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp;
import acm.program.ConsoleProgram;
/**
 *
 * @author student
 */
public class mergesort extends ConsoleProgram{
    int array[] = new int[100];
    public int upperhalf[] = new int[100];
    public int lowerhalf[] = new int[100];
    public int size = readInt("input number of integers to sort");
    public void run(){
        for(int i = 0;i!=size;i++){
            array[i]=readInt("input integer no."+i );
        }  
    }
    public void mergesort(int array[]){
//        int half = (int) Math.floor(size/2);
//        int[] lowerhalf[] =  
//        mergesort(lowerhalf[]);
//        mergesort(upperhalf[]);
    }
}
