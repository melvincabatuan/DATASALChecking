package ph.edu.dlsu.datasal.santos.myqueue;

import ph.edu.dlsu.datasal.santos.namesurfer.*;


public class MyQueue<E> implements Queue<E>{
    private final int size=4514;
    private int front;
    private int rear;
    private E[] item;
    private int numitems;
    
    public MyQueue(){
        createqueue();
    }
    
    public void createqueue(){
        item = (E[])new Object[size​];
        numitems=0;
        front =0;
        rear=-1;
    }
    
    public void enqueue(E element)throws QueueOutOfBoundsException{
        if(isFull()){
            System.out.println("Q is full");
        }
        else {
            rear=(rear+1)%size;
            item[rear]= element;
            numitems++;
        }
    }
    
    public void dequeue(){
        if(isEmpty()){
            System.out.println("Q is empty");
        }
        else{
            for(int i=0; i<numitems; i++){
                item[i]=item[i+1];
            }
           numitems--;
           rear=(rear-1)%size;
           // front++;
        }
    }
    
    public E front(){
        return item[front];
    }
    
    public E rear(){
        return item[rear];
    }
    
    public E item(int i){
        return item[i];
    }
    
    public boolean isEmpty(){
        return numitems==0;
    } 
    
    public boolean isFull(){
        return numitems==size;
    }
    
    public int size(){
        return numitems;
    }
}
