package ph.edu.dlsu.datasal.parro.mergesort;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    
    public static final boolean VERBOSE = true;
    
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
        // Add left-over values to result      
        while (i < lowerHalf.length) {
            result[k++] = lowerHalf[i++];
        }
        while (j < upperHalf.length) {
            result[k++] = upperHalf[j++];
        }

        return result;
    }
     
    public int[] mergeSort(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalStateException("Error: Array is empty.");
        } else if (data.length == 1) {
            return data;
        }

        int half = (int) Math.floor(data.length / 2);
        int[] lowerHalf = Arrays.copyOf(data, half);  // copies 'half' number of elements
        int[] upperHalf = Arrays.copyOfRange(data, half, data.length); // copies from 'half' until length-1

        if (VERBOSE) {
            System.out.print("lowerHalf: ");
            display(lowerHalf);
            System.out.print("upperHalf: ");
            display(upperHalf);
            System.out.println("");
        }

        lowerHalf = mergeSort(lowerHalf);
        upperHalf = mergeSort(upperHalf);

        if (VERBOSE) {
            System.out.print("lowerHalf: ");
            display(lowerHalf);
            System.out.print("upperHalf: ");
            display(upperHalf);
            System.out.println("");
        }

        return merge(lowerHalf, upperHalf);
    }

    public static void display(final int[] array) {
        System.out.printf("{%d", array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.printf(", %d", array[i]);
        }
        System.out.println("}");
    }
}
