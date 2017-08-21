/**
 * Created by Student on 6/30/2016.
 */
package datasal.Narag.myInterface;

public interface Stack<E>{
    
    public void initializeStack();
    public boolean isFull();
    public boolean isEmpty();
    public E top();
    public void push(E obj);
    public void pop();

}