package ph.edu.dlsu.datasal.wenceslao.myqueue;

import ph.edu.dlsu.datasal.wenceslao.myinterface.Queue;
import ph.edu.dlsu.datasal.wenceslao.myexception.QueueIndexOutOfBoundsException;

public class MyQueue<E> implements Queue<E> {
    
    private E[] data; 
    private int rear; 
    private int front; 
    private int count; 
    private int MAX = 1000;
    
    public MyQueue(){
        createQueue();
    }
    
    public void createQueue(){
        data = (E[])new Object[MAX];
        rear = -1;
        front = 0;
    }
    
    public void enqueue(E item) throws QueueIndexOutOfBoundsException{
        if(!isFull()){
            rear = (rear+1)%MAX; 
            data[rear] = item; 
            count++;
        }
        else
            throw new QueueIndexOutOfBoundsException("Queue is Full");
    }
    
    public Object dequeue() throws QueueIndexOutOfBoundsException{
        if(!isEmpty()){
            front = (front+1)%MAX;
            count--; 
        }
        else
            throw new QueueIndexOutOfBoundsException("Queue is Empty"); 
        return null;
    }
    
    public boolean isEmpty(){
        return (count==0);
    }
    
    public boolean isFull(){
        return (count==MAX);
    }
    
    public int size(){
        return count;
    }
    
    public E getFront(){
        return data[front];
    }

    public E getRear(){
        return data[rear];
    }
}
