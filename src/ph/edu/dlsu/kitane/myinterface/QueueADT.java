/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.myinterface;

/**
 *
 * @author ChristophJohnEric
 */
public interface QueueADT<E>{
    
    public void createQueue();
    //precondition: none
    //postcondition: create an empty queue
    
    public boolean isEmptyQueue();
    //precondition: none
    //postcondition: Determines if QueueList is empty
    
    public boolean isFullQueue();
    //precondition: none
    //postcondition: Determines if QueueList is full
    
    public E front();
    //precondition: Queue exists and it isn't empty
    //postcondition: Determines the first element in the queue
    //If queue is empty, throw QueueEmptyException
    
    public E back();
    //precondition: Queue exists and it isn't empty
    //postcondition: Determines the last element in the queue
    //If queue is empty, throw QueueEmtyException
    
    public void addQueue(E item);
    //precondition: Queue exists and it isn't full
    //postcondition: item is added if Queue isn't full
    //If Queue is full, throw QueueFullException
    
    public void deleteQueue();
    //precondition: Queue exists and it isn't empty
    //postcondition: first element is removed from QueueList if Queue is not empty
    //If Queue is empty, throw QueueEmptyException
    
    public int size();
    //precondition: none
    //postcondition: Determines the size and returns it.
    
    
}
