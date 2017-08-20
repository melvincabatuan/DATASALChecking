package ph.edu.dlsu.datasal.bhuller.myqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class Queue <E>{

    private int fsize=5000;
    public int fron;
    public int last;
    private E[] items;
    private int numItems;
    private boolean val;
    
    public Queue(){
        create();
    }
    
    public void create() {
    items = ( E[]) new Object[fsize];
    numItems =0;
    fron = 0;
    last = -1;
    }

   
    public void add(E elem) {
        if(isFull()){
           System.out.println("It is full");
        }
        else{
            last = (last+1);
            items[last] = elem;
            numItems++;
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
     }
     
      public Queue<E> intersection(Queue<E> cc){
        Queue<E> result = new Queue<E>();
        result.create();
        int c=1;
            for(int j=0;j<cc.size();j++){
                if(contains(cc.get(j+1))){
                    result.add(cc.get(j));
                    c++;
                }
            }
        return result;
    }
    
     
    public boolean containsAll(Queue<E> cc){
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

     
    public void addAll(Queue<E> cc){
        if(cc.size()<=items.length){
        for (int i=0; i<cc.size(); i++){
            items[i] = cc.get(i+1);
        }
        }
    }
    
     public void removeAll(Queue<E> cc){
         if(cc.size()<=items.length)
         for (int i=1; i<cc.size(); i++){
             if(items[i-1]==cc.get(i)){
                 items[i-1]=null;
             }
         }
     }

      public boolean equals(Queue<E> cc) {
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
  
    public void deque() {
        if(isEmpty()){
            System.out.println("It is empty");
        }
        else{
            for(int i=0; i<numItems; i++){
                items[i] = items[i+1];
            }
            last = last-1;
            numItems--;
        }
    }

    
    public E top() {
        return items[last];
    }

  
    public E first() {
        return items[fron];
    }

  
    public int size() {
        return numItems;
    }

   
    public boolean isEmpty() {
        return numItems==0;
    }

   
    public boolean isFull() {
        return numItems==fsize;
    }
    
    public E get(int i){
        return items[i];
    }
}
