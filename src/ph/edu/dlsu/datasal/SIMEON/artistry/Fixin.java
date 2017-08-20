/* 
 * File: FixingBrokenJava.java
 * Name:
 * Section Leader:
 * 
 * This program does not work as intended.  It contains both
 * compile-time errors (errors that prevent the compiler from even
 * running the program) and run-time errors (errors where the
 * program does not function as intended).  Your job is to fix
 * this program so that it works correctly.  Note that it is *not*
 * sufficient to simply fix the compiler errors; you will need to
 * update the logic as well.
 * 
 * This program attempts to read a positive integer from the user,
 * then check whether that integer is prime (whether its only
 * divisors are 1 and itself).  If so, it prints a message saying
 * that the number is prime; otherwise it says that the number is
 * composite.
 */
package ph.edu.dlsu.datasal.SIMEON.artistry;
import acm.graphics.*;
import acm.program.*;
import java.lang.Math.*;
import java.util.*;
public class Fixin extends ConsoleProgram {
	/* Reads a number from the user and reports whether or not it
	 * is prime.
	 */

    private int[] matrix = new int[4000];

    private int numItems=0;
    private int[] upper = new int[4000];
    private int[] lower = new int[4000];
    private int[] answer = new int[4000];
    private int i=0;
    private int a;

    
    public void run(){
    println("Enter numbers to be sorted");
    println("Enter 0 to start sorting (Note: 0 is not included in array to be sorted)");
    do{
    matrix[i]=readInt();
    a=matrix[i];
    if(a==0){
        break;
    }
    i++;
    numItems++;

    }while(a!=0); 
    answer = MergeSort(matrix);
    println("The sorted answer is: ");
    for(int i=0;i<=numItems;i++){
    System.out.printf(answer[i]+", ");
            }
        
    }
    
    private int[] MergeSort(int[] data){
        int x=0;

        if(data.length==1){
            return data;
        }
        else{
            
       int half = (int) Math.floor(data.length/2);
        int[] lower = Arrays.copyOf(data, half);  // copies 'half' number of elements
        int[] upper = Arrays.copyOfRange(data, half, data.length); // copies from 'half' until length-1
            
        }
        lower=MergeSort(lower);
        upper=MergeSort(upper);
        return merge(upper,lower);
    }
    private int[] merge(int[] upper, int[] lower){
        int[] result = new int[40];
        int i, j, k;
        i = j = k = 0;
        while (i < lower.length && j < upper.length) {
            if (lower[i] < upper[j]) {
                result[k++] = lower[i++];
            } else {
                result[k++] = upper[j++];
            }
        }      
        while (i < lower.length) {
            result[k++] = lower[i++];
        }
        while (j < upper.length) {
            result[k++] = upper[j++];
        }

        return result;
    }

	
	/**
	 * Given a positive integer, returns whether that integer is
	 * prime.
	 * 
	 * @param value The value to test.
	 * @return Whether or not it is prime.
	 */
	private boolean isPrime(int value) {
		/* Try all possible divisors of the number.  If any of them
		 * cleanly divide the number, we return that the number is
		 * composite.
		 */
		for (int divisor = 2; divisor < value; divisor++) {
			if (value % divisor == 0) {
				return false;

			}
		}
                return true;
	}
	
	/**
	 * Reads a positive integer from the user and returns it.
	 * 
	 * @return A positive integer entered by the user.
	 */
	private int readPositiveInt() {
		/* Get an initial value. */
		int value = readInt("Enter a positive integer: ");
		
		/* If the value was nonpositive, reprompt the user. */
		while (value <= 0) {
			println("Please enter a positive integer.");
			value = readInt("Enter a positive integer: ");
		}
		
		return value;
	}
public static void main(String[] args) {
   new Fixin().start(args);   // AddIntegers is the class name
}
}

