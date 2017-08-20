/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.mystack;

/**
 *
 * @author Bhuller
 */
import ph.edu.dlsu.datasal.bhuller.myarraylist.ListIndexOutOfBoundsException;
public class MyStack<E>{

    private int maxsize = 10;
    private E[] value;
    private int numItems = 0;
    public int tostack;
    private boolean val;
    
    public MyStack() {
        value = (E[]) new Object [maxsize];
        tostack=-1;
    }

     public E get(int index) throws ListIndexOutOfBoundsException{
         if ( index > 0 && index <= numItems){
             return value[index-1];
         }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    }
   
    public E peek() {
        return value[tostack];  
    }
    
     public boolean contains(E item){
         val =false;
         for (int i=0; i<numItems; i++){
             if (value[i]==item){
                 val = true;
             }
         }
         return val;
     }
     
     public void clear(){
         for (int i=0; i<numItems; i++){
             value[i] = null; 
             }
     }
     
      public MyStack<E> intersection(MyStack<E> cc){
        MyStack<E> result = new MyStack<E>();
        int c=1;
            for(int j=0;j<cc.size();j++){
                if(contains(cc.get(j+1))){
                    result.push(cc.get(j));
                    c++;
                }
            }
        return result;
    }
    
     
    public boolean containsAll(MyStack<E> cc){
        int a=0;
        if(cc.size()>numItems){
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

     
    public void addAll(MyStack<E> cc){
        if(cc.size()<=numItems){
        for (int i=0; i<cc.size(); i++){
            value[i] = cc.get(i+1);
        }
        }
    }
    
     public void removeAll(MyStack<E> cc){
         if(cc.size()<=numItems)
         for (int i=1; i<cc.size(); i++){
             if(value[i-1]==cc.get(i)){
                 value[i-1]=null;
             }
         }
     }

      public boolean equals(MyStack<E> cc) {
        int a=0;
        if(numItems!=cc.size()){
            return false;
        }
        for(int i=0;i<numItems;i++){
            for(int j =0;j<cc.size();j++){
                if(get(i+1)==cc.get(j+1)){
                    a++;
                }
            }     
        }
        if(a==numItems){
        return true;
        }
        else
            return false;
    }
  
     

    public boolean full() {
     return (tostack == maxsize-1);
    }

    public int size(){
        return numItems;
    }
 
    public boolean empty() {
     return (tostack == -1);
    }


    public void push(E item) {
    if (full()){
        System.out.println("MAXIMUM SIZE REACHED");
    }
    else{
        tostack++;
        value [tostack]= item; 
    }
    }
  
    public E pop() {
       
        return (value [tostack--]); 
    }
}
    
    
    

