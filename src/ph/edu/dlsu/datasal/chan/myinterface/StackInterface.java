package ph.edu.dlsu.datasal.chan.myinterface;


import ph.edu.dlsu.datasal.chan.myexception.StackFullException;
import ph.edu.dlsu.datasal.chan.myexception.StackEmptyException;


/*@author Patrick*/
public interface StackInterface<E> {
    //methods
    public void push(E j) throws StackFullException;
    public void pop() throws StackEmptyException;
    public E top()  throws StackEmptyException;
    public boolean isEmpty();
    public boolean isFull();
    //resize()
}