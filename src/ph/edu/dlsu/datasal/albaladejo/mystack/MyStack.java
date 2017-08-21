/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.albaladejo.mystack;

/**
 *
 * @author student
 */
public class MyStack<E> {

    private E[] myStack;
    private int MAX_SIZE = 10;
    private int TOS = -1;

    public int size() {
        return TOS + 1;
    }

    public boolean isEmpty() {
        return (TOS == -1);
    }

    public int contains(E item) {
//        if(item!=TOS){
//            pop();
//            TOS--;
//        }
//        else
//            return TOS;
        throw new UnsupportedOperationException("Bad operand.");
    }

    public void push(E item) {
        if (isFull()) {
            System.out.println("Stack is full");
        } else {
            TOS = TOS + 1;
            myStack[TOS] = item;
        }
    }

    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            TOS = TOS - 1;
        }
        return (myStack[TOS - 1]);
    }

    public boolean containsAll(Object c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addAll(Object c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {

    }

    public void sort(Object o) {

    }

    public void removeAll() {
        for (int i = 0; i >= TOS; i++) {
            pop();
        }
    }

    public boolean isFull() {
        return (TOS == MAX_SIZE);
    }

}
