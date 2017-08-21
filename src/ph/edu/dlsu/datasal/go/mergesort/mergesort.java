/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.go.mergesort;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author student
 */
public class mergesort {
    
    public static void main(String[] args){
        mergesort merge = new mergesort();
        Scanner input = new Scanner(System.in);
        int nums =input.nextInt();
        final int array[]=new int[nums];
        for(int i=0;i<nums;i++){
            array[i]= input.nextInt();
        }
    }
    public int[] mergesort(int[] data){
        if(data==null&&data.length==0){
            
        }else if(data.length==1)
            return data;
        
        int halfNum = (int)Math.floor(data.length/2);
        int[] lower=Arrays.copyOf(data, halfNum);
        int[] upper=Arrays.copyOfRange(data, halfNum,data.length);
        lower = mergesort(lower);
        upper = mergesort(upper);
        return merge(lower, upper);
    }
    
    private int[] merge(int[] lower, int[] upper){
        int[] result = new int[lower.length + upper.length];
        int i=0, j=0, k=0;
        while (i < lower.length && j < upper.length) {
            if (lower[i] < upper[j]) {
                result[k++] = lower[i++];
            }else 
                result[k++] = upper[j++];
        }
        while (i < lower.length) {
            result[k++] = lower[i++];
        }
        while (j < upper.length) {
            result[k++] = upper[j++];
        }
        return result;
    }
}   
   
