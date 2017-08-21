/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasal.Narag.myQueue;

import datasal.Narag.myExceptions.QueueIndexOutOfBoundsException;
import datasal.Narag.myInterface.Queue;

/**
 *
 * @author renzl
 */

public class MyQueue<E> implements Queue<E> { 
    private E[] data; 
    private int rear; 
    private int front; 
    private int count; 
    private int MAX = 4514;
    
    public MyQueue(){
        createQueue();
    }

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
}

