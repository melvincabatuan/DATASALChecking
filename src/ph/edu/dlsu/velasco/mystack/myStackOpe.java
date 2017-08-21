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
public class myStackOpe {

    private int maxSize;
    private char[] stackArray;
    private int top;

    public myStackOpe(int s) {
        maxSize = s;
        stackArray = new char[maxSize];
        top = -1;
    }

    public void push(char item) {
        stackArray[++top] = item;
    }

    public void pop() {
        top--;
    }

    public char top() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
    
    public int size(){
        return top+1;
    }
    

    public boolean contains(char item) {
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

}
