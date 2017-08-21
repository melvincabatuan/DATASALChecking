/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp.queue;

import ph.edu.dlsu.datasal.reyes.datasalapp.myexceptions.*;
public class queue <E>{
    
    private int max = 100;
    private E queue[] = (E[]) new Object[max];
    private int size = 0;
    private int front = 0;
    private int back = 1;
    private int contains= 0;
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        if(size==0) return true;
        else return false;
    }
    public void enqueue(E object) throws ListFullException{
        if(size!=100){
            queue[back] = object;
            size++;
            back++;
        }
        else if(size==100)throw new ListFullException("cant queue: Queue full");
    }
    public E dequeue()throws ListEmptyException{
        if(!isEmpty()){
            front++;
            size--;
            return(queue[back-1]);
        }
        else throw new ListEmptyException("cant dqueue: Queue empty");
    }
    public boolean contains(E object){
        for(int i = 0;i!=size-1;i++){
            if(object==queue[i]){
                 contains=1;
                 break;
            }
            else contains = 0;
        }
        if (contains ==1) return true;
        else return false;
    }
}
