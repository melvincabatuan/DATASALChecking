package ph.edu.dlsu.datasal.go.myqueue;

import java.util.Collection;
import ph.edu.dlsu.datasal.go.datasalapp.MyCollection;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class myQueueMar <E> implements MyCollection<E> {

    private int QUEUEsize=10;
    public int front;
    public int rear;
    private E[] items;
    private int numItems;
    
    public myQueueMar(){
        create();
    }
    

    public void create() {
    items = ( E[]) new Object[QUEUEsize];
    numItems =0;
    front = 0;
    rear = -1;
    }

    public void add(E element) {
        if(isFull()){
           System.out.println("Queue full");
        }
        else{
            rear = (rear+1)%QUEUEsize;
            items[rear] = element;
            numItems++;
        }
    }

    public void dequeue() {
        if(isEmpty()){
            System.out.println("Queue empty");
        }
        else{
            for(int i=0; i<numItems; i++){
                items[i] = items[i+1];
            }
            rear = rear-1;
            numItems--;
        }
    }

    public E top(){
        return items[rear];
    }

    public E first() {
        return items[front];
    }

    public int size() {
        return numItems;
    }

    public boolean isEmpty() {
        return numItems==0;
    }

    public boolean isFull() {
        return numItems==QUEUEsize;
    }
    
    public E get(int i){
        return items[i];
    }

    public E contains(int i) {
        return items[i];
    }


    public boolean remove(Object o) {
        if(isEmpty()){
            System.out.println("Queue empty");
        }
        else{
            for(int i=0; i<numItems; i++){
                items[i] = items[i+1];
            }
            rear = rear-1;
            numItems--;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(int i=0;i>items.length;i++){
            items[i]=items[i+1];
        }
        return false;
    }

    @Override
    public void clear() {
       for (int i=0; i<items.length; i++){
             items[i] = null; 
             }
    }

    @Override
    public void sort(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
