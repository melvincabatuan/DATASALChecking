/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.go.mystack;

import java.util.Collection;
import ph.edu.dlsu.datasal.go.datasalapp.MyCollection;

/**
 *
 * @author intel
 */
public class MyStackMar<E> implements MyCollection<E> {
    private E[] myStack;
   private int MAX_SIZE=10;
   private int TOS= -1;
   
   public MyStackMar(){
        //myStack = new int[MAX_SIZE];
        myStack = (E[]) new Object [MAX_SIZE];
   }
   
   public void push(E item){
       if (!isEmpty()){
        System.out.println("MAXIMUM SIZE REACHED");
    }
    else{
        TOS++;
        myStack [TOS]= item; 
    }
    }
   
    public E pop(){
        return (myStack[TOS--]); 
    }
    
    public E peek(){
        return null;
        
    }
    public boolean isEmpty(){
        return(TOS==-1);
    }
    
    public boolean isFull(){
        return(TOS==MAX_SIZE);
    }

    @Override
    public int size() {
        return MAX_SIZE;
    }

    
    public E contains(Object o) {
        return myStack[TOS];
    }

   
    public void add(E e) {
        if(isFull()){
            System.out.println("Stack is full");
        }
        else{
            TOS=TOS+1;
            myStack[TOS]=e;
        }
    }
    
    public boolean remove(Object o) {
        if(isEmpty())
            System.out.println("Stack is empty");
        else{
            TOS=TOS-1;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(int i=0; i>TOS;i++){
            pop();
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i=0; i<myStack.length; i++){
             myStack[i] = null; 
             }
    }

    @Override
    public void sort(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E contains(int o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
