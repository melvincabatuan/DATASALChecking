/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.santos.mergesort;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author cobalt mkc
 */
public class MergeSortDemo {

    public static final boolean VERBOSE = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MergeSortDemo demo = new MergeSortDemo();
        Scanner input = new Scanner(System.in);

        System.out.println("How many integers are you sorting?");
        int n = input.nextInt();

        /// Input array
        final int array[] = new int[n];

        /// Input numbers to be sorted
        System.out.println("Enter " + n + " numbers");

        for (int i = 0; i < n; i++) {
            System.out.print(">> ");
            array[i] = input.nextInt();
        }

        System.out.println("Input array:");
        demo.display(array);
        System.out.println("Sorted array:");
        demo.display(demo.mergeSort(array));

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

    public static void display(final int[] array) {
        System.out.printf("{%d", array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.printf(", %d", array[i]);
        }
        System.out.println("}");
    }

}
