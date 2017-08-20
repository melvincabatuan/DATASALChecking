/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import ph.edu.dlsu.datasal.wenceslao.myexception.ListIndexOutOfBoundsException;
import ph.edu.dlsu.datasal.wenceslao.myexception.ListFullException;
import ph.edu.dlsu.datasal.wenceslao.myinterface.List;
import acm.program.*;
import acm.util.*;
import java.util.*;


public class MyArrayList<E> implements List<E>{

     /// private data fields
     private final int MAX_LIST = 10;     // max length of list
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
            if(o==items[i])
                return true;
        }
         return false;
    }
    
    public void clear(){
        while(NumItems != 0){
            this.remove(1);
        }
    }
    
    public void add(E item){
        items[NumItems] = item;
        NumItems++;
    }
    
    public void set(int x, E item) throws ListIndexOutOfBoundsException{
        if(x >= 0 && x <= NumItems-1)
            items[x-1] = item;
        else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    }

    public boolean containsAll(List<E> record) {
        int y = 1;
        for (int index = 1; index < record.size(); index++) {
            for (int indexNum = 0; indexNum < NumItems; indexNum++) {
                if (items[indexNum] == record.get(index)) {
                    y = 1;
                    break;
                }
                else{
                    y = 0;
                }
            }
        }
        if (y != 0) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    public void addAll(List<E> record) {
        for (int a = 0; a < record.size(); a++) {
            items[NumItems] = (E) record.get(a + 1);
            NumItems++;
        }
    }

    public void removeAll(List<E> record) {
        for (int index = 1; index < record.size(); index++) {
            for (int indexNum = 0; indexNum < NumItems; indexNum++) {
                if (items[indexNum] == record.get(index)) {
                    for (int a = indexNum; a < NumItems; a++) {
                        items[a] = items[a + 1];
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void sort(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}