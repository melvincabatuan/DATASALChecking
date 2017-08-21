/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp.stack;

import ph.edu.dlsu.datasal.reyes.datasalapp.myexceptions.*;

public class mystack <E>{
    private int max = 100;
    public E[] Stack = (E[])new Object[max];
    private int size = 0;
    public int contains = 0;
    public boolean full(){
        if(size==max) return true;
        else return false;               
    }
     public boolean isEmpty(){
        if(size==0) return true;
        else return false;               
    }
    public E top() throws ListEmptyException{
       if(!isEmpty()){
            E top = Stack[size-1];
            return top;
       }
       else throw new ListEmptyException("cant get top: Stack empty");
    }
    public void push(E object) throws ListFullException{
        if(full()){
            Stack[size] = object;
            size++;
        }
        else throw new ListEmptyException("cant push: Stack full");
    }
    public void pop()throws ListEmptyException{
       if(!isEmpty())size--;
       else throw new ListEmptyException("cant pop: Stack empty");
    }
    public int size(){
        return size;
    }
    public boolean contains(E object){
        for(int i = 0;i!=size-1;i++){
            if(object==Stack[i]){
                 contains=1;
                 break;
            }
            else contains = 0;
        }
        if (contains ==1) return true;
        else return false;
    }
} 
