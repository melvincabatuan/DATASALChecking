package ph.edu.dlsu.datasal.santos.hangman;

import acm.program.*;
import acm.util.*;


public class MyList<E> implements List<E>{

     /// private data fields
     private final int MAX_LIST = 10;     // max length of list
     private E[] items;                     // array of list items
     private int NumItems;                  // current size of list

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
           if (NumItems > 0){
               return false;
           }
           else {
               return true;
           }
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

}
