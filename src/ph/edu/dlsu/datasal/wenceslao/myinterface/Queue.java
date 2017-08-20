package ph.edu.dlsu.datasal.wenceslao.myinterface;

public interface Queue<E> {
    
    public void createQueue();
    
    public void enqueue(E item);
    
    public Object dequeue();
    
    public boolean isEmpty();
    
    public boolean isFull();
    
    public int size();
    
    public E getFront();
    
    public E getRear();

}
