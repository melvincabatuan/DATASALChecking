package ph.edu.dlsu.datasal.SIMEON.mystack;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import ph.edu.dlsu.datasal.SIMEON.myinterface.MyStackInterface;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListFullException;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListIndexOutOfBoundsException;
import acm.program.*;
import acm.util.*;


public class Stack<E> implements MyStackInterface<E>{

     /// private data fields
     private final int MAX_LIST = 2000;     // max length of list
     private int tos=0;                     // array of list items
     private int NumItems=0;
     private E[] items = (E[])new Object[MAX_LIST]; ;  // current size of list


     public void createList(){
           items = (E[])new Object[MAX_LIST]; 
           NumItems = 0;
     }
    public E get(int index) throws ListIndexOutOfBoundsException{
         if ( index > 0 && index <= NumItems){
             return items[index-1];
         }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    }
     public void push(E item) throws ListIndexOutOfBoundsException, ListFullException{
          if ( tos!=MAX_LIST){
               items[tos]=item;
               tos++;
               NumItems++;
               }            
          else
            throw new ListIndexOutOfBoundsException("ERROR: Overflow");
     } 

     public void pop() throws ListIndexOutOfBoundsException{
          if ( tos!=0){
                    NumItems--;
                    tos--;
              }
          else
            throw new ListIndexOutOfBoundsException("ERROR: Underflow");
    } 

    public boolean isEmpty(){
           return NumItems == 0;          
    }

    public E top() throws ListIndexOutOfBoundsException{
         
             return items[tos-1];
         

    }
    public void remove(int index) throws ListIndexOutOfBoundsException{
          if ( index > 0 && index <= NumItems){
                    for(int i = index; i < NumItems; i++){
                        items[i-1] = items[i];
                    }
                    NumItems--;
                    tos--;
              }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    } 

            public void removeAll(Stack<E> stock){
           for(int i=1;i<=NumItems;i++){
            for(int j =1;j<=stock.size();j++){
                if(get(i)==stock.get(j)){
                    remove(i);
                }
            }     
    }
    }
    public boolean containsAll(Stack<E> stock){
        int b=0;
        if(stock.size()>size()){
            return false;
        }
        for(int i=1;i<=stock.size();i++){
            
                if(contains(stock.get(i))){
                    b++;
                }
                 
    }
        if(b>=stock.size()){
        return true;
        }
        else
            return false;
    }
    public boolean equals(Stack<E> stock) {
        int b=0;
        if(NumItems!=stock.size()){
            return false;
        }
        for(int i=1;i<=NumItems;i++){
            for(int j =1;j<=stock.size();j++){
                if(get(i)==stock.get(j)){
                    b++;
                }
            }     
    }
        if(b==NumItems){
        return true;
        }
        else
            return false;
    }
    public boolean contains(E item){
        for(int i=0;i<NumItems;i++){
            if(items[i]==item){
                return true;
            }
        }
        return false;
    }
    public void addAll(Stack<E> stock){
        for(int i=1; i<=stock.size();i++){
            push(stock.get(i));
            
        }
    }

    public int size(){
           return NumItems;
    }
    
    public void clear(){
        if(!isEmpty()){
            for(int i=1;i<=NumItems;){
                pop();            
            }
        }
    }
    public Stack<E> intersection(Stack<E> stock){
        Stack<E> result = new Stack<E>();
        result.createList();
            for(int j=1;j<=stock.size();j++){
                if(contains(stock.get(j))){
                    result.push(stock.get(j));
                   
                }
            }
        
        return result;
    }
    public void sort(Stack<Integer> stock){
        int[] temp = new int[stock.size()];
       
          for(int i=0;i<stock.size();i++){
          temp[i]=stock.get(i+1);
    }
         temp=BubbleSort(temp);
         stock.clear();
         for(int i=1;i<=temp.length;i++){
             
            stock.push(temp[i-1]);
         }
         

    }       
    public int[] BubbleSort(int[] temp){
        int tempz;  

               for(int i = 0; i < temp.length - 1; i++){
                    for(int j = 0; j < temp.length - i - 1; j++){
                        if(temp[j] > temp[j+1])
                        { 
                           tempz = temp[j];
                           temp[j] = temp[j+1]; 
                           temp[j+1] = tempz;
                         }
                     
                    } 
                }
               return temp;
    }
    




}