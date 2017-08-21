package ph.edu.dlsu.datasal.santos.calculator;

public class MyStack1<E> implements Stack<E>{
    private final int size=13;
    private E[] items;
    private int numitems;
    public int tos;
    
    public void createstack(){
        items = (E[])new Object[size​];
        numitems=0;
        tos = -1;
    }
    
    public boolean isEmpty() {
        return tos==-1;
    }
    
    public boolean isFull(){
        return tos==size-1;
    }
    
    public void push (E element) {
        if(isFull()){
            System.out.println("Stack is full");
        }
        else {
            tos=tos+1;
            items[tos]= element;
        }
    }
    
    public void pop(){
        if (isEmpty()) {
            System.out.println("Stack is empty");
            createstack();
        }
        else {
            tos=tos-1;
        }
    }
    
    public E top(){
        return items[tos];
    }

    public int size() {
        return tos+1;
    }

}

