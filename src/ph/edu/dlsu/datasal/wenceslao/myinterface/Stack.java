package ph.edu.dlsu.datasal.wenceslao.myinterface;

public interface Stack<E> {
    public void  initializeStack();
    
    public boolean  isFull();
    
    public boolean  isEmpty();
    
    public  E  top();
    
    public void  push( E  obj);
    
    public void  pop();
}
