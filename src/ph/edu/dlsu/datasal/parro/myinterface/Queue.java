/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.parro.myinterface;

import java.util.Collection;

/**
 *
 * @author renzl
 */
public interface Queue<E> {
    
    public void createQueue();
    
    public boolean isEmpty(); 
    
    public boolean isFull(); 
    
    public void enqueue(E item); 
    
    public void dequeue();
    
    public E getFront(); 
    
    public E getRear(); 
    
    public int size();
    
    public boolean contains(Object o);

    public boolean containsAll(Collection<E> c);

    public boolean addAll(Collection<? extends E> c);

    public boolean removeAll(Collection<?> c);

    public void clear();

    public boolean equals(Object o);
}
