/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.myarraylist;

import java.util.Arrays;

/**
 *
 * @author student
 */
public class MergeSort {
     public static final boolean VERBOSE = true;
     
    public int[] mergeSort(int[] dat) {
        if (dat == null || dat.length == 0) {
            System.out.println("Empty");
        } else if (dat.length == 1) {
            return dat;
        }

        int mid = (int) Math.floor(dat.length / 2);
        int[] lowerHalf = Arrays.copyOf(dat, mid);  
        int[] upperHalf = Arrays.copyOfRange(dat, mid, dat.length); 

        if (VERBOSE) {
            System.out.print("lower: ");
            display(lowerHalf);
            System.out.print("upper: ");
            display(upperHalf);
            System.out.println("");
        }

        lowerHalf = mergeSort(lowerHalf);
        upperHalf = mergeSort(upperHalf);

        if (VERBOSE) {
            System.out.print("lower: ");
            display(lowerHalf);
            System.out.print("upper: ");
            display(upperHalf);
            System.out.println("");
        }
        return merge(lowerHalf, upperHalf);
    }

    private int[] merge(int[] lowerHalf, int[] upperHalf) {
        int[] result = new int[lowerHalf.length + upperHalf.length];
        int i, j, k;
        i = j = k = 0;
        while (i < lowerHalf.length && j < upperHalf.length) {
            if (lowerHalf[i] < upperHalf[j]) {
                result[k++] = lowerHalf[i++];
            } else {
                result[k++] = upperHalf[j++];
            }
        }
          
        while (i < lowerHalf.length) {
            result[k++] = lowerHalf[i++];
        }
        while (j < upperHalf.length) {
            result[k++] = upperHalf[j++];
        }
        return result;
    }

    public static void display(final int[] array) {
        System.out.printf("{%d", array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.printf(", %d", array[i]);
        }
        System.out.println("}");
    }
}
