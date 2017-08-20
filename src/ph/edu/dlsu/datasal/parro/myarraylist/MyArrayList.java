package ph.edu.dlsu.datasal.parro.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import ph.edu.dlsu.datasal.parro.myexception.ListFullException;
import ph.edu.dlsu.datasal.parro.myexception.ListIndexOutOfBoundsException;
import ph.edu.dlsu.datasal.parro.myinterface.List;
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
          if(items == null){
              createList();
          }
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

    public boolean containsAll(List<E> c) {
        int x = 1;
         for (int listIndex = 1; listIndex < c.size(); listIndex++){
             for(int numIndex = 0; numIndex < NumItems; numIndex++){
                 if (items[numIndex] == c.get(listIndex)){
                     x = 1;
                     break;
                 }
                 else{
                     x = 0;
                 }
             }
         }
         if (x != 0){
             return true;
         }
         else return false;
    }

    public void addAll(List<E> c) {
        for(int a = 0; a < c.size(); a++){
           items[NumItems] = (E) c.get(a+1);
           NumItems++;
        }
    }

    public void removeAll(List<E> c) {
        for (int listIndex = 1; listIndex < c.size(); listIndex++){
             for(int numIndex = 0; numIndex < NumItems; numIndex++){
                 if (items[numIndex] == c.get(listIndex)){
                     for(int a = numIndex; a<NumItems; a++){
                         items[a] = items[a+1];
                     }
                     break;
                 }
                 
             }
         }
    }

    public void clear() {
        for (int i = 0; i < NumItems; i++){
            items[i] = null;
        }
        NumItems = 0;
    }

    public boolean equals(Object o){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void sort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void set(int index, E item) throws ListIndexOutOfBoundsException {
        if(NumItems != MAX_LIST){
            items[index]=item;
        }
    }

    public void add(E e) {
        if(NumItems != MAX_LIST && NumItems != 0){
            items[NumItems] = e;
            NumItems++;
        }
    }

    public void intersection(List<E> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public boolean intersection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public void intersection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}