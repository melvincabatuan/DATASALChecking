/**
 * Created by Student on 6/30/2016.
 */
package datasal.Narag.myStack;

import datasal.Narag.myInterface.Stack;

public class myStack<E> implements Stack<E> {
    private final int size = 11;
    private E[] items;
    private int numItems;
    public int topOfStack;
    
    public myStack(){
        initializeStack();
    }


    public void initializeStack(){
        items = (E[])new Object[size];
        numItems=0;
        topOfStack=-1;


    }

    public void push(E obj){
        if(isFull()){
            System.out.println("STACK OVERFLOW");
        }
        else{
            topOfStack=topOfStack+1;
            items[topOfStack]=obj;

        }
    }

    public void pop(){
        if(isEmpty()){
            System.out.println("STACK IS EMPTY");
            initializeStack();

        }
        else{
            topOfStack=topOfStack-1;
        }
    }

    public boolean isEmpty(){
        return topOfStack==-1;
    }

    public boolean isFull(){
        return topOfStack==size-1;
    }

    public E top(){
        return items[topOfStack];
    }

    public boolean contains(Object E){
        int TOS = topOfStack;
        int flag = 0;
        while(TOS!=-1){
            if (items[TOS]==E){
                flag=1;
                TOS--;
            }
            
            else {
                TOS--;
            }
        }
        if (flag!=0){
            return true;
        }
        else return false;
    }
    
    public void clear(){
        while (topOfStack!=-1){
            topOfStack--;
        }
    }

}