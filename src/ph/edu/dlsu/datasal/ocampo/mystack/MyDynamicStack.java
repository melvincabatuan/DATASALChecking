
package ph.edu.dlsu.datasal.ocampo.mystack;
import java.util.*;
import ph.edu.dlsu.datasal.ocampo.myexception.*;

/**
 * This class is about dynamic stacks that change in size over time.
 * Dynamic stacks, unlike static stacks, do not have preset size limits,
 * rather, the limit is determined by the computers memory.
 * @param <E> The data type contained in the stack.
 */
public class MyDynamicStack<E> {
    
    private LinkedList<E> items;
    
    /**
     * Create a dynamic stack.
     */
    public MyDynamicStack() {
        items = new LinkedList<>();
    }
    
    /**
     * Checks if the stack is empty
     * @return True if the stack is empty, and false otherwise.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    /**
     * Gives the stack size or the number of elements in the stack.
     * @return The number of elements in the stack.
     */
    public int size() {
        return items.size();
    }
    
    /**
     * Checks if the stack contains the specified element, regardless on whether
     * it is on top of the stack or not.
     * @param element The element to be checked.
     * @return True if the stack contains the element.
     */
    public boolean contains(E element) {
        return items.contains(element);
    }
    
    /**
     * Adds an item to the stack.
     * @param element The item to be added to the stack.
     */
    public void push(E element) {
        items.add(0, element);
    }
    
    /**
     * Gets the top of the stack.
     * @return The item at the top of the stack or null when the item is not found.
     * @throws StackUnderflowException if the stack is empty.
     */
    public E top() throws StackUnderflowException {
        E item = null;
        if(!isEmpty()) item = items.get(0);
        else throw new StackUnderflowException("Stack Empty");
        
        return item;
    }
    
    /**
     * Removes the topmost item from the stack.
     * @throws StackUnderflowException if the stack is empty.
     */
    public void pop() throws StackUnderflowException {
        if(!isEmpty()) items.remove(0);
        else throw new StackUnderflowException("Stack Empty");
    }
    
    public boolean equals(MyDynamicStack<E> anotherStack) {
        return items.equals(anotherStack.items);
    }
    
    public LinkedList<E> intersection(MyDynamicStack<E> anotherStack) {
        LinkedList<E> output = new LinkedList();
        for(int i=0; i<anotherStack.size(); i++) {
            if(contains(anotherStack.items.get(i))) output.add(anotherStack.items.get(i));
        }
        return output;
    }
} // end of class
