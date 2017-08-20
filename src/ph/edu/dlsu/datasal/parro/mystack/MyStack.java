/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.parro.mystack;

import java.util.Collection;
import ph.edu.dlsu.datasal.parro.myinterface.Stack;

/**
 *
 * @author renzl
 */
public class MyStack<E> implements Stack<E> {
    private final int  size = 11;
    private E[] items; 
    private int  numItems; 
    public int  topOfStack;
    
    public MyStack(){
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

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

