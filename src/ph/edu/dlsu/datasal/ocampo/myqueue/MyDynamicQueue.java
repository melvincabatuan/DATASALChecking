/*
 * Dynamic Queue - Linked List based
 */

package ph.edu.dlsu.datasal.ocampo.myqueue;
import ph.edu.dlsu.datasal.ocampo.myexception.*;
import java.util.*;

public class MyDynamicQueue<E> {
    private LinkedList<E> data;
    
    // Constructor
    public MyDynamicQueue() {
        data = new LinkedList<>();
    }
    
    /**
     * Checks if the queue is empty.
     * @return True if the queue is empty and false otherwise.
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }
    
    /**
     * Gives the number of elements in the queue.
     * @return The number of elements in the queue.
     */
    public int size() {
        return data.size();
    }
    
    /**
     * Adds an item to the rear of the queue.
     * @param element The item to be added to the queue.
     */
    public void enQueue(E element) {
        data.add(0, element);
    }
    
    /**
     * Gets the front element of the queue without deleting it.
     * @return The front element of the queue.
     * @throws QueueEmptyException If the queue is empty.
     */
    public E getFront() throws QueueEmptyException {
        E item = null;
        
        if(!isEmpty()) item = data.get(data.size()-1);
        else throw new QueueEmptyException("Queue Empty");
        
        return item;
    }
    
    /**
     * Removes the front element of the queue.
     * @return The front element of the queue.
     * @throws QueueEmptyException If the queue is empty.
     */
    public E deQueue() throws QueueEmptyException {
        E item = null;
        if(!isEmpty()) {
            item = data.get(data.size()-1);
            data.remove(data.size()-1);
        }
        else throw new QueueEmptyException("Queue Empty");
        
        return item;
    }
    
    /**
     * Removes all items from the queue. The queue is empty after this
     * method returns.
     * @throws QueueEmptyException If the queue is empty.
     */
    public void deQueueAll() throws QueueEmptyException {
        if(!isEmpty()) data.clear();
        else throw new QueueEmptyException("Queue Empty");
    }
    /**
     * Gives the string representation of the queue.
     * <br>
     * The queue elements are lined up with the leftmost element the rear and
     * the rightmost element the front.
     * @return 
     */
    public String toString() {
        String s = "";
        for(int i=0; i<size(); i++) 
            s += String.valueOf(data.get(i)) + " ";
        
        return s;
    }
    
    /**
     * Gives the dynamic list representation of the queue
     * @return The linked list representation of the queue.
     */
    public LinkedList<E> toDynamicList() {
        return data;
    }
    
    /**
     * Checks if the queue contains the element specified.
     * @param element The element to be checked.
     * @return True if the queue contains the specified element. False otherwise.
     */
    public boolean contains(E element) {
        return data.contains(element);
    }
    
    /**
     * Returns the position of the element.
     * <br>
     * The indexing is one based.
     * @param element The element to determine the position.
     * @return The positional index of the element in the queue.
     */
    public int positionOf(E element) {
        return data.indexOf(element)+1;
    }
    
    public boolean equals(MyDynamicQueue<E> anotherQueue) {
        return data.equals(anotherQueue.data);
    }
    
    public LinkedList<E> intersection(MyDynamicQueue<E> anotherQueue) {
        LinkedList<E> output = new LinkedList();
        for(int i=0; i<anotherQueue.size(); i++) {
            if(contains(anotherQueue.data.get(i))) output.add(anotherQueue.data.get(i));
        }
        return output;
    }
}
