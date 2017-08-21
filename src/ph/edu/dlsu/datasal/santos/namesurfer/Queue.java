package ph.edu.dlsu.datasal.santos.namesurfer;

public interface Queue<E>{
    public void createq();
    public void nq(E element);
    public void dq();
    public E top();
    public E bottom();
    public E item(int i);
    public boolean isEmpty(); 
    public boolean isFull(); 
    public int size();
}
