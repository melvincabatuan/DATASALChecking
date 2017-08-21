/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.velasco.mystack;

import java.util.Arrays;

/**
 *
 * @author Neil Velasco
 */
public class MyStack<E> {

    private int maxSize;
    private E[] stackArray;
    private int top;

    public MyStack(int s) {
        maxSize = s;
        stackArray = (E[]) new Object[maxSize];
        top = -1;
    }

    public void push(E item) {
        stackArray[++top] = item;
    }

    public void pop() {
        top--;
    }

    public E top() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public int size() {
        return top + 1;
    }

    public MyStack intersection(MyStack stack) {
        MyStack intersection = new MyStack(stack.size());
        for (int i = 0; i < stackArray.length; i++) {

        }
        return intersection;
    }

    public boolean contains(E item) {
        if (isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < stackArray.length; i++) {
                if (stackArray[i] == item) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        top = -1;
    }

    //mergeSort
    public void sort() {
        if (isEmpty()) {
            return;
        }
        int middle = (int) Math.floor((top + 1) / 2);
        E[] lowerHalf = Arrays.copyOf(stackArray, middle);
        E[] upperHalf = Arrays.copyOfRange(stackArray, middle, top + 1);

        lowerHalf = sort(lowerHalf);
        upperHalf = sort(upperHalf);

        E[] merged = merge(lowerHalf, upperHalf);
        stackArray = merged.clone();

        E[] tempArray = stackArray.clone();
        int index = stackArray.length;
        for (int i = 0; i < index; i++) {
            System.out.println(tempArray[i]);
            stackArray[i] = tempArray[index - (i + 1)];
        }
        for (int i = 0; i < stackArray.length; i++) {
            System.out.println(stackArray[i]);
        }
    }

    private E[] sort(E[] array) {
        if (array.length == 1) {
            return array;
        }
        int middle = (int) Math.floor(array.length / 2);
        E[] lowerHalf = Arrays.copyOf(array, middle);
        E[] upperHalf = Arrays.copyOfRange(array, middle, array.length);

        lowerHalf = sort(lowerHalf);
        upperHalf = sort(upperHalf);

        E[] merged = merge(lowerHalf, upperHalf);
        return merged;
    }

    private E[] merge(E[] lower, E[] upper) {
        int i;
        int j;
        int k;
        i = j = k = 0;
        int max = upper.length + lower.length;
        E[] merged = (E[]) new Object[max];

        while (i < lower.length && j < upper.length) {

            if ((Integer) lower[i] < (Integer) upper[j]) {
                merged[k++] = lower[i++];
            } else {
                merged[k++] = upper[j++];
            }

        }
        while (i < lower.length) {
            merged[k++] = lower[i++];
        }
        while (j < upper.length) {
            merged[k++] = upper[j++];
        }
        return merged;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(5);
        stack.push(1);
        stack.push(3);
        stack.push(12);
        stack.push(9);
        stack.push(10);
        System.out.println("Current size: " + stack.size());

        stack.sort();

    }
}
