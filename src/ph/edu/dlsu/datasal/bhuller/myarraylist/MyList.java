/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */

import acm.program.*;
import acm.util.*;
import java.util.Arrays;



public class MyList<E> {

     /// private data fields
     private final int MAX_LIST = 20;     // max length of list
     private E[] items; 
      
     private int NumItems;                  // current size of list
     private boolean val;
     
     public MyList(){
         createList();
     }
     
     /// list items are already allocated above with T items[MAX_LIST]
     @SuppressWarnings("unchecked")
     public void createList(){
           items = (E[])new Object[MAX_LIST]; 
           NumItems = 0;
     }

     public boolean equals(MyList<E> cc) {
        int a=0;
        if(NumItems!=cc.size()){
            return false;
        }
        for(int i=0;i<NumItems;i++){
            for(int j =0;j<cc.size();j++){
                if(get(i+1)==cc.get(j+1)){
                    a++;
                }
            }     
        }
        if(a==NumItems){
        return true;
        }
        else
            return false;
    }

     public int[] mergeSort(int[] dat) {
        if (dat == null || dat.length == 0) {
            System.out.println("Empty");
        } else if (dat.length == 1) {
            return dat;
        }

        int mid = (int) Math.floor(dat.length / 2);
        int[] lowerHalf = Arrays.copyOf(dat, mid);  
        int[] upperHalf = Arrays.copyOfRange(dat, mid, dat.length); 

        lowerHalf = mergeSort(lowerHalf);
        upperHalf = mergeSort(upperHalf);

        return merge(lowerHalf, upperHalf);
    }

    private int[] merge(int[] lowerHalf, int[] upperHalf) {
        int[] result = new int[lowerHalf.length + upperHalf.length];
        int i, j, k;
        i = j = k = 0;
        while (i < lowerHalf.length && j < upperHalf.length) {
            if (lowerHalf[i] < upperHalf[j]) {
                result[k++] = lowerHalf[i++];
            } else {
                result[k++] = upperHalf[j++];
            }
        }
          
        while (i < lowerHalf.length) {
            result[k++] = lowerHalf[i++];
        }
        while (j < upperHalf.length) {
            result[k++] = upperHalf[j++];
        }
        return result;
    }
     
    
    public void sort(MyList<Integer> arr){
        int siz =arr.size();
        int[] neew = new int[siz];
       
          for(int i=1;i<=arr.size();i++){
          neew[i-1]=arr.get(i);
    }
         mergeSort(neew);
         for(int i=0;i<=neew.length;i++){
            arr.add(i+1, neew[i]);
         }
    }       
     
     
     public MyList<E> intersection(MyList<E> cc){
        MyList<E> result = new MyList<E>();
        result.createList();
        int c=1;
            for(int j=0;j<cc.size();j++){
                if(contains(cc.get(j+1))){
                    result.add(c, cc.get(j));
                    c++;
                }
            }
        return result;
    }
    
     
    public boolean containsAll(MyList<E> cc){
        int a=0;
        if(cc.size()>NumItems){
            return false;
        }
        for(int i=1;i<=cc.size();i++){
                if(contains(cc.get(i))){
                    a++;
                }     
    }
        if(a>=cc.size()){
        return true;
        }
        else
            return false;
    }

     
    public void addAll(MyList<E> cc){
        if(cc.size()<=items.length){
        for (int i=0; i<cc.size(); i++){
            items[i] = cc.get(i+1);
        }
        }
    }
    
     public void removeAll(MyList<E> cc){
         if(cc.size()<=items.length)
         for (int i=1; i<cc.size(); i++){
             if(items[i-1]==cc.get(i)){
                 items[i-1]=null;
             }
         }
     }

     public boolean contains(E item){
         val =false;
         for (int i=0; i<items.length; i++){
             if (items[i]==item){
                 val = true;
             }
         }
         return val;
     }
     
     public void clear(){
         for (int i=0; i<items.length; i++){
             items[i] = null; 
             }
         NumItems = 0;
     }
     
     public void add(E item){
         if(NumItems<MAX_LIST){
          int j = NumItems;
                    items[j] = item;
                    NumItems++;
     }
     }
     
     public void set(int its, E item) throws ListIndexOutOfBoundsException{
         if ( its >= 0 && its <= NumItems-1){
              items[its-1] = item;
         }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
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

    
}