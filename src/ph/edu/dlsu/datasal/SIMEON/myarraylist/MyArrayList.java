/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.myarraylist;

/*
 * File: MyArrayList.java
 * ------------------
 * This is the List ADT implementation
 */


import ph.edu.dlsu.datasal.SIMEON.myinterface.MyListInterface;
import acm.program.*;
import acm.util.*;
import java.util.Arrays;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListFullException;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListEmptyException;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListIndexOutOfBoundsException;

public class MyArrayList<E> implements MyListInterface<E>{

     /// private data fields
     private final int MAX_LIST = 50;     // max length of list
     private E[] items= (E[])new Object[MAX_LIST];                     // array of list items
     private int NumItems=0;                  // current size of list

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
    public boolean contains(E item){
        for(int i=0;i<NumItems;i++){
            if(items[i]==item){
                return true;
            }
        }
        return false;
    }
    public boolean equals(MyArrayList<E> list) {
        int b=0;
        if(NumItems!=list.size()){
            return false;
        }
        for(int i=1;i<=NumItems;i++){
            for(int j =1;j<=list.size();j++){
                if(get(i)==list.get(j)){
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

        
    public void sort(MyArrayList<Integer> list){
        int[] temp = new int[list.size()];
       
          for(int i=0;i<list.size();i++){
          temp[i]=list.get(i+1);
    }
         temp=BubbleSort(temp);
         list.clear();
         for(int i=1;i<=temp.length;i++){
             
            list.add(i, temp[i-1]);
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

    public MyArrayList<E> intersection(MyArrayList<E> list){
        MyArrayList<E> result = new MyArrayList<E>();
        result.createList();
        int z=1;

            for(int j=1;j<=list.size();j++){
                if(contains(list.get(j))){
                    result.add(z, list.get(j));
                    z++;
                }
            }
        
        return result;
    }

    public void clear(){
        if(!isEmpty()){
            for(int i=1;i<=NumItems;){
                remove(i);            
            }
        }
    }
    public void removeAll(MyArrayList<E> list){
           for(int i=1;i<=NumItems;i++){
            for(int j =1;j<=list.size();j++){
                if(get(i)==list.get(j)){
                    remove(i);
                }
            }     
    }
    }
    public void addAll(MyArrayList<E> list){
        for(int i=1; i<=list.size();i++){
            add((NumItems+1),list.get(i));
            
        }
    }
    public boolean containsAll(MyArrayList<E> list){
        int b=0;
        if(list.size()>size()){
            return false;
        }
        for(int i=1;i<=list.size();i++){
            
                if(contains(list.get(i))){
                    b++;
                }
                 
    }
        if(b>=list.size()){
        return true;
        }
        else
            return false;
    }

}
