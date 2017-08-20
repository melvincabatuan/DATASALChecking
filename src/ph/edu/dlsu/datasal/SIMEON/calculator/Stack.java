package ph.edu.dlsu.datasal.SIMEON.calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


public class Stack<E> implements List<E>{

     /// private data fields
     private final int MAX_LIST = 200;     // max length of list
     private int tos=0;                     // array of list items
     private int NumItems;
     private E[] items;  // current size of list


     public void createList(){
           items = (E[])new Object[MAX_LIST]; 
           NumItems = 0;
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

    public int size(){
           return NumItems;
    }



}