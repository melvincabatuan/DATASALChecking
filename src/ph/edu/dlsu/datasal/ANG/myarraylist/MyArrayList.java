package ph.edu.dlsu.datasal.ang.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import ph.edu.dlsu.datasal.ang.myexception.ListFullException;
import ph.edu.dlsu.datasal.ang.myexception.ListIndexOutOfBoundsException;
import ph.edu.dlsu.datasal.ang.myinterface.List;
import acm.program.*;
import acm.util.*;
import java.util.Collection;


public class MyArrayList<E> implements List<E>{

     /// private data fields
     private final int MAX_LIST = 100;     // max length of list
     private E[] items;                     // array of list items
     private int NumItems;                  // current size of list
     
     public MyArrayList(){
         createList();
     }

     /// list items are already allocated above with T items[MAX_LIST]
     @SuppressWarnings("unchecked")
     public void createList(){
           items = (E[])new Object[MAX_LIST]; 
           NumItems = 0;
     }

     public void add(int index, E item) throws ListIndexOutOfBoundsException, ListFullException{
          if ( index > 0 && index <= NumItems + 1){
               if (NumItems == MAX_LIST){
                    throw new ListFullException("ERROR: List Already Full"); 
               }  
               else { // insert the element
                    int j = NumItems;
                    while(j >= index){
                        items[j] = items[j - 1];
                        j--; 
                    }
                    items[index-1] = item; 
                    NumItems++;
               }
          }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
     } 

     public void remove(int index) throws ListIndexOutOfBoundsException{
          if ( index > 0 && index <= NumItems){
                    for(int i = index; i < NumItems; i++){
                        items[i-1] = items[i];
                    }
                    NumItems--;
              }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    } 

    public boolean isEmpty(){
           return NumItems == 0;          
    }

    public E get(int index) throws ListIndexOutOfBoundsException{
         if ( index > 0 && index <= NumItems){
             return items[index-1];
         }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    }

    public int size(){
           return NumItems;
    }

    public boolean contains(Object o) {
        for(int i = 0; i < NumItems; i++){
            if(o == items[i]){
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
   
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean equals(Object o){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void sort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void set(int index, E item) throws ListIndexOutOfBoundsException {
        if(NumItems!=MAX_LIST){
            items[index]=item;
        }
    }

    public void add(E e) {
        if(NumItems!=MAX_LIST&&NumItems!=0){
            items[NumItems] = e;
            NumItems++;
        }
    }

    public void intersection(List<E> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void intersection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}