/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.myinterface;

import java.util.Collection;


public interface Stack<E>{
    
    public void initializeStack();
    
    public boolean isFull(); 
    
    public boolean isEmpty(); 
    
    public E top();
    
    public void push(E obj);

    public void pop();
    
    public boolean contains(Object o);

    public boolean containsAll(Collection<E> c);

    public boolean addAll(Collection<? extends E> c);

    public boolean removeAll(Collection<?> c);

    public void clear();

    public boolean equals(Object o);

}

