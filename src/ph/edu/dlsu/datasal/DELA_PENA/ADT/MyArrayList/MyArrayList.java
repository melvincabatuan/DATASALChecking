/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyArrayList;
import java.lang.reflect.Array;
import java.util.Arrays;
import ph.edu.dlsu.datasal.DELA_PENA.MyException.*;
/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


public class MyArrayList<E> implements List<E>{

     /// private data fields
     private final int MAX_LIST = 50;     // max length of list
     private E[] items = (E[])new Object[MAX_LIST];;                     // array of list items
     private int NumItems=0;                  // current size of list
     
    

     /// list items are already allocated above with T items[MAX_LIST]
     @SuppressWarnings("unchecked")
     public void createList(){
           
     }
     
     public void add(E item) throws ListFullException{
         if(NumItems == MAX_LIST){
             throw new ListFullException("ERROR: List Already Full"); 
         }
         else{
             items[NumItems] = item;
             NumItems++;
         }
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
    
     public void set(int index, E item) throws ListIndexOutOfBoundsException, ListFullException{
          if ( index > 0 && index <= NumItems + 1){
               if (NumItems == MAX_LIST){
                    throw new ListFullException("ERROR: List Already Full"); 
               }  
               else { // insert the element
                    
                    items[index-1] = item; 
                    
               }
          }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
     }

     public E remove(int index) throws ListIndexOutOfBoundsException{
         E temp = null;
          if ( index > 0 && index <= NumItems){
                    temp = items[index-1];
                    for(int i = index; i < NumItems; i++){
                        items[i-1] = items[i];
                    }
                    NumItems--;
                    return temp;
              }
          
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
          
    } 
     
     public void remove(Object e){
         for(int i=0; i<NumItems; i++){
             if(items[i] == e){
                for(int j = i; j < NumItems; j++){
                    items[j] = items[j+1];
                }
                NumItems--;
                break;
             }
         }
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
        boolean contain = false;
        if(!isEmpty()){
            for(int i=0; i<NumItems; i++){
                if(items[i] == o){
                    contain = true;
                    break;
                }
            }
        }
        return contain;
    }
    
    public boolean containsAll(MyArrayList c){
        int numsame = 0;
        for(int i=1; i<=c.size(); i++){
            Object element = c.get(i);
            for(int j=0; j<NumItems; j++){
                if(items[j]==element){
                    numsame++;
                    break;
                }
            }
        }
        return numsame==c.size();
    }
    
    public void clear(){
        NumItems = 0;
    }
    
    public void addAll(MyArrayList c){
        for(int i=1; i<=c.size(); i++){
            this.add((E)c.get(i));
        }
    }
    
    public void removeAll(MyArrayList c){
        if(!isEmpty()){
            
            for(int i=1; i<=c.size(); i++){
                remove(c.get(i));
            }
        }
    }
    
    public void sort(){
        items = mergeSort(items);
    }
    
    private E[] mergeSort(E[] array){
        int arraylength = array.length;
        if(arraylength<=1)
            return array;
        else{
            int mid = Math.floorDiv(arraylength, 2);
            E[] A = Arrays.copyOfRange(array, 0, mid);
            E[] B = Arrays.copyOfRange(array, mid, arraylength);
            
            A = mergeSort(A);
            B = mergeSort(B);
            
            return merge(A, B);
        }
    }
    
    private E[] merge(E[] A, E[] B){
        E[] result = (E[]) new Object[A.length + B.length];
        int i=0, j=0, k=0;
        while(i<(A.length) && j<(B.length)){
            if((Integer)A[i]<(Integer)B[j]){
                result[k] = A[i];
                i++;
                k++;
            }
            else{
                result[k] = B[j];
                j++;
                k++;
            }
        }
        while(i<Array.getLength(A)){
            result[k] = A[i];
            i++;
            k++;
        }
        while(j<Array.getLength(B)){
            result[k] = B[j];
            j++;
            k++;
        }
        
        return result;
    }
    
    public MyArrayList intersection(MyArrayList c){
        MyArrayList intersect = new MyArrayList();
        for(int i=1; i<=c.size(); i++){
            Object element = c.get(i);
            for(int j=0; j<NumItems; j++){
                if(items[j]==element){
                    intersect.add(element);
                    break;
                }
            }
        }
        
        return intersect;
    }
    
    

}