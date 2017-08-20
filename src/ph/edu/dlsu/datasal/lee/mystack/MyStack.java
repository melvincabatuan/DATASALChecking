/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.lee.mystack;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import acm.program.*;
import acm.util.*;


public class MyStack<E>{

     /// private data fields
     private final int MAX_LIST =1000000;     // max length of list
     private E[] items;                     // array of list items
     private int NumItems;                  // current size of list

     /// list items are already allocated above with T items[MAX_LIST]
     @SuppressWarnings("unchecked")
     public MyStack(){
         createList();
     }
     
     public void createList(){
           items = (E[])new Object[MAX_LIST]; 
           NumItems = 0;
     }

     public void push(E item) {
         NumItems++; 
         items[NumItems-1]=item;
     } 

     public E pop(){
        E result;
        result = items[NumItems-1];
        NumItems--;
        return result;
    } 

    public boolean isEmpty(){
           return NumItems == 0;          
    }
    public int size(){
        return NumItems;
    }
    public E top(){
        E item;
        item=items[NumItems-1];
        return item;
    } 
}