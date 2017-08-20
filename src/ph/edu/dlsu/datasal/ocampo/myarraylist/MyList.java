/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */

package ph.edu.dlsu.datasal.ocampo.myarraylist;
import ph.edu.dlsu.datasal.ocampo.myexception.*;
import ph.edu.dlsu.datasal.ocampo.myinterface.*;

public class MyList<E> implements List<E>{

     /// private data fields
     private final int MAX_LIST = 10;     // max length of list
     private E[] items;                     // array of list items
     private int NumItems;                  // current size of list
     
     public MyList(){
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
               if (NumItems == MAX_LIST) {
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

    public boolean contains(E element) {
        boolean y=false;
        for(int i=0; i<NumItems; i++) {
            if(items[i]==element) {
                y=true; break;
            }
        }
        return y;
    }
    
    public int indexOf(E element) {
        int i=0;
        while(i<NumItems && element!=items[i]) {
            i++;
        }
        return (i<NumItems)?i:(-1);
    }
    
    public void addAll(MyList<E> list) {
        for(int i=1; i<=list.size(); i++) {
            add(list.size()+1, list.get(i));
        }
    }
    
    public void removeAll(MyList<E> list) {
        for(int i=1; i<=list.size(); i++) {
            remove(indexOf(list.get(i)));
        }
    }
    
    public boolean equals(MyList<E> list) {
        boolean y=true;
        for(int i=1; i<=list.size(); i++) {
            if(list.get(i)!=items[i-1]) {
                y=false; break;
            }
        }
        return y;
    }
    
    public MyList<E> intersection(MyList<E> list) {
        MyList<E> intList = new MyList();
        intList.createList();
        for(int i=0; i<list.size(); i++) {
            if(contains(list.get(i))) intList.add(intList.size(), list.get(i));
        }
        return intList;
    }
    
    public void sort() {
        
    }
    
    private MyList<E> subList(int startIndex, int endIndex) {
        MyList<E> out = new MyList();
        int k=0;
        for(int i=startIndex; i<=endIndex; i++) {
            out.items[k] = items[i-1]; k++;
        }
        return out;
    }
}
