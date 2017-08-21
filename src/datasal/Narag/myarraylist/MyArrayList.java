/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasal.Narag.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import datasal.Narag.myInterface.List;
import datasal.Narag.myExceptions.ListFullException;
import datasal.Narag.myExceptions.ListIndexOutOfBoundsException;
import acm.program.*;
import acm.util.*;


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

    public boolean contains(Object o){
            int flag = 0;
            for(int index = 0; index < NumItems; index++){
                if (o==items[index]){
                    flag = 1;
                }
            }
            if (flag==1){
                return true;
            }
            else{
                return false;
            }
    
    }
    
    public void clear(){
            while(NumItems!=0){
                this.remove(1);
            }
    }
    
    public boolean equals(MyArrayList list){
        int flag = 1;
        if (NumItems!=list.size()){
            return false;
        }
        
        else    {
            for(int index = 0; index < NumItems; index ++){
                if((items[index])!=(list.get(index+1))){
                   flag=0;
                }
            }
        }
        if (flag==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void addAll(MyArrayList list1){
        
        for(int a = 0; a < list1.size(); a++){
           items[NumItems] = (E) list1.get(a+1);
           NumItems++;
        }
    }
    
    public MyArrayList<String> intersection(MyArrayList<String> list1){
         MyArrayList<String> equiv = new MyArrayList<>();
         equiv.createList();
         
         if (list1==this){
          for(int a = 1; a<NumItems; a++){
              equiv.add(a, (String)this.get(a));
          }
          
         }
         else{
         for(int numIndex = 0; numIndex < NumItems; numIndex++){
             
             for(int listIndex = 1; listIndex < list1.size(); listIndex++){
                 if (((String)items[numIndex]).equals((String)list1.get(listIndex))){
                     equiv.add(listIndex, list1.get(listIndex));
                 }
             }
         }
         }
         return equiv;
        
    }
    
    
    public boolean containsAll(MyArrayList list){
         int flag = 1;
         for (int listIndex = 1; listIndex < list.size(); listIndex++){
             for(int numIndex = 0; numIndex < NumItems; numIndex++){
                 if (items[numIndex] == list.get(listIndex)){
                     flag = 1;
                     break;
                 }
                 flag = 0;
             }
         }
         if (flag!=0){
             return true;
         }
         else return false;
    }
    
    public void removeAll(MyArrayList list){
        for (int listIndex = 1; listIndex < list.size(); listIndex++){
             for(int numIndex = 0; numIndex < NumItems; numIndex++){
                 if (items[numIndex] == list.get(listIndex)){
                     for(int a = numIndex; a<NumItems; a++){
                         items[a] = items[a+1];
                     }
                     break;
                 }
                 
             }
         }
    }
    
    public void sort(){
        int swaps;
        E temp;
        do{
            swaps = 0;
            for(int a=0;a<NumItems-1;a++){
               int first = ((Integer) items[a]);
               int second = ((Integer) items[a+1]);
               if(first>second){
                   temp = items[a+1];
                   items[a+1] = items[a];
                   items[a] = temp;
                   swaps++;
               }
                
            }
        }
        while (swaps != 0);
        
    }
}