/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.mygraph;

import ph.edu.dlsu.kitane.myexception.QueueFullException;
import ph.edu.dlsu.kitane.myexception.QueueEmptyException;
import ph.edu.dlsu.kitane.myinterface.QueueADT;

/**
 *
 * @author ChristophJohnEric
 */
public class myQueueList<E> implements QueueADT<E> {

    private final int MAX_QUEUE = 4;
    private E[] items;
    private int numItems;
    private int qFront;
    private int qRear;

    /*Constructor: creatQueue()*/
    /**
     * Creates a new QueueList and initializes the QueueList.
     */

    public myQueueList() {
        createQueue();
    }

    public void createQueue() {
        items = (E[]) new Object[MAX_QUEUE];
        numItems = 0;
        qFront = -1;
        qRear = -1;
    }

    /*Method: isEmptyQueue*/
    /**
     * Checks the QueueList it is empty.
     *
     * @return isEmptyQueue checks if numItems==0.
     */
    public boolean isEmptyQueue() {
        return numItems == 0;
    }

    /*Method: isFullQueue*/
    /**
     * Checks the QueueList if it is full.
     *
     * @return isFullQueue checks if numItems==MAX_QUEUE.
     */
    public boolean isFullQueue() {
        return numItems == MAX_QUEUE;
    }

    /*Method: front()*/
    /**
     * Determines the first element in the QueueList.
     *
     * @return it will return items[qFront+1].
     */
    public E front() {
        if (isEmptyQueue()) {
            throw new QueueEmptyException("No front, Queue is empty.");
        } else {
            return items[qFront];
        }
    }

    /*Method: back()*/
    /**
     * Determines the last element in the QueueList.
     *
     * @return it will return items[qRear].
     */
    public E back() {
        if (isEmptyQueue()) {
            throw new QueueEmptyException("No front, Queue is empty.");
        } else {
            return items[qRear];
        }
    }

    /*Method addQueue(E item)*/
    /**
     * Adds item to the QueueList. If QueueList is full, addQueue(E item) and
     * throws QueueFullEsception.
     */
    public void addQueue(E item) {
        if (!isFullQueue()) {
            if (qFront == -1) {
                qFront = 0;
            }
            qRear = (qRear + 1) % MAX_QUEUE;
            items[qRear] = item;
            numItems++;
        } else {
            throw new QueueFullException("Queue is Full!");
        }
    }

    /*Method: deleteQueue()*/
    /**
     * Deletes the first element in the QueueList. If the QueueList is empty,
     * deleteQueueList will terminate and throws QueueEmptyException.
     */
    public void deleteQueue() {
        if (!isEmptyQueue()) {
            if (qFront == qRear) {
                qFront = -1;
                qRear = -1;
            }
            qFront = (qFront + 1) % MAX_QUEUE;
            numItems--;
        } else {
            throw new QueueEmptyException("Queue is Empty!");
        }
    }

    /*Method: size()*/
    /**
     * Returns the size of the QueueList.
     */
    public int size() {
        return numItems;
    }
}
