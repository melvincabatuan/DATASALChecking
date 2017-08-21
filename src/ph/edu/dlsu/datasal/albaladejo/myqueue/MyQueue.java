/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.albaladejo.myqueue;

/**
 *
 * @author student
 * @param <E>
 */
public abstract class MyQueue<E> {

    private int QUEUEsize = 100;
    public int front;
    public int rear;
    private E[] items;
    private int numItems;

    public int size() {
        return numItems;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean enqueue(E element) {
//         if(isFull()){
//           System.out.println("QUEUE IS FULL.");
//        }
//        else{
//            rear = (rear+1)%QUEUEsize;
//            items[rear] = element;
//            numItems++;
//        }
        throw new UnsupportedOperationException("Missing return statement.");
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("QUEUE IS EMPTY.");
        } else {
            for (int i = 0; i < numItems; i++) {
                items[i] = items[i + 1];
            }
            rear = rear - 1;
            numItems--;
        }
    }

    public boolean containsAll(Object c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addAll(Object c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeAll(Object c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sort(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isFull() {
        return numItems == QUEUEsize;
    }

}
