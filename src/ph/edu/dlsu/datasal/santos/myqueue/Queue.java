package ph.edu.dlsu.datasal.santos.myqueue;

public interface Queue<E>{
    public void createqueue();
    public void enqueue(E element);
    public void dequeue();
    public E front();
    public E rear();
    public E item(int i);
    public boolean isEmpty(); 
    public boolean isFull(); 
    public int size();
}
