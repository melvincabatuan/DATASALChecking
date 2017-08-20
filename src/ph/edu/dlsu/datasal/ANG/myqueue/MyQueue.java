/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.myqueue;

import java.util.Collection;
import ph.edu.dlsu.datasal.ang.myexception.QueueIndexOutOfBoundsException;
import ph.edu.dlsu.datasal.ang.myinterface.Queue;


public class MyQueue<E> implements Queue<E> {
    private E[] data; 
    private int rear; 
    private int front; 
    private int count; 
    private int MAX = 4500;

    public void createQueue(){
        data = (E[])new Object[MAX];
        rear = -1;
        front = 0;
    } 

    public boolean isEmpty() {
        return(count==0);
    }

    public boolean isFull() {
        return(count==MAX);
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

    public void dequeue() throws QueueIndexOutOfBoundsException {
        if(!isEmpty()){
            front = (front+1)%MAX;
            count--; 
        }
        else
            throw new QueueIndexOutOfBoundsException("Queue is Empty"); 
        }    

    public E getFront(){
        return data[front];
    }

    public E getRear(){
        return data[rear];
    }

    public int size(){
        return count;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

