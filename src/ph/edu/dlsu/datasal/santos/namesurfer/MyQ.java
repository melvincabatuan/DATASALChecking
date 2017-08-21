package ph.edu.dlsu.datasal.santos.namesurfer;


public class MyQ<E> implements Queue<E>{
    private final int size=4514;
    private int front;
    private int rear;
    private E[] item;
    private int numitems;
    
    public void createq(){
        item = (E[])new Object[size​];
        numitems=0;
        front =0;
        rear=-1;
    }
    
    public void nq(E element)throws qoutof{
        if(isFull()){
            System.out.println("Q is full");
        }
        else {
            rear=(rear+1)%size;
            item[rear]= element;
            numitems++;
        }
    }
    
    public void dq(){
        if(isEmpty()){
            System.out.println("Q is empty");
        }
        else{
            for(int i=0; i<numitems; i++){
                item[i]=item[i+1];
            }
           numitems--;
           rear=(rear-1)%size;
           // front++;
        }
    }
    
    public E top(){
        return item[front];
    }
    
    public E bottom(){
        return item[rear];
    }
    
    public E item(int i){
        return item[i];
    }
    
    public boolean isEmpty(){
        return numitems==0;
    } 
    
    public boolean isFull(){
        return numitems==size;
    }
    
    public int size(){
        return numitems;
    }
}
