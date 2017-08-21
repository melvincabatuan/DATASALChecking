package ph.edu.dlsu.datasal.santos.myinterface;

public interface Stack<E>{
    public void createstack();
    
    public void push(E element);
    
    public void pop();
    
    public E top();
    
    public int size();
    
    public boolean isEmpty(); 
    
    public boolean isFull(); 
}
