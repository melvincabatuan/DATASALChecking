package ph.edu.datasal.SIMEON.myqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */


import acm.program.*;
import acm.util.*;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListFullException;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListIndexOutOfBoundsException;


public class Queue<E>{

     /// private data fields
     private final int MAX_LIST = 10000;     // max length of list
     private int frontqueue=0;
     private int rearqueue=0;// array of list items
     private int NumItems= 0;
     private E[] items= (E[])new Object[MAX_LIST]; ;  // current size of list


     public void createList(){
           items = (E[])new Object[MAX_LIST]; 
           NumItems = 0;
           frontqueue = 0;
           rearqueue=0;
     }
      
     public void enqueue(E item) throws ListIndexOutOfBoundsException, ListFullException{
          if ( rearqueue!=MAX_LIST){
               items[rearqueue]=item;
               rearqueue++;
               NumItems++;
               }            
          else
            throw new ListIndexOutOfBoundsException("ERROR: Overflow");
     } 

     public void dequeue() throws ListIndexOutOfBoundsException{
          if ( frontqueue!=0){
                    remove(1);
                   
              }
          else
            throw new ListIndexOutOfBoundsException("ERROR: Underflow");
    } 

    public boolean isEmpty(){
           return frontqueue == 0 && rearqueue == 0;          
    }
    public void clear(){   
        while(!isEmpty()){
            remove(1);
        }
    }

    public E peek() throws ListIndexOutOfBoundsException{     
             return items[frontqueue];
       
    }

    public int size(){
           return NumItems;
    }
    public E get(int index) throws ListIndexOutOfBoundsException{
         if ( index > 0 && index <= NumItems){
             return items[index-1];
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
                    rearqueue--;
              }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    } 

        public void removeAll(Queue<E> queue){
           for(int i=1;i<=NumItems;i++){
            for(int j =1;j<=queue.size();j++){
                if(get(i)==queue.get(j)){
                    remove(i);
                }
            }     
    }
    }
        public boolean containsAll(Queue<E> queue){
        int b=0;
        if(queue.size()>NumItems){
            return false;
        }
        for(int j =1;j<=queue.size();j++){

            if(contains(queue.get(j))){
                    b++;
                }
                
    }
        if(b>=queue.size()){
        return true;
        }
        else
            return false;
    }
    public boolean equals(Queue<E> queue) {
        int b=0;
        if(NumItems!=queue.size()){
            return false;
        }
        for(int i=1;i<=NumItems;i++){

           if(contains(queue.get(i))){
                    b++;
                }
            }     
    
        if(b==NumItems){
        return true;
        }
        else
            return false;
    }

    public boolean contains(E item){
        for(int i=0;i<NumItems;i++){
            if(items[i]==item){
                return true;
            }
        }
        return false;
    }
    public void addAll(Queue<E> queue){
        for(int i=1; i<=queue.size();i++){
            enqueue(queue.get(i));
            
        }
    }

       public void sort(Queue<Integer> queue){
        int[] temp = new int[queue.size()];
       
          for(int i=0;i<queue.size();i++){
          temp[i]=queue.get(i+1);
    }
         temp=BubbleSort(temp);
         queue.clear();
         for(int i=1;i<=temp.length;i++){
             
            queue.enqueue(temp[i-1]);
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




}