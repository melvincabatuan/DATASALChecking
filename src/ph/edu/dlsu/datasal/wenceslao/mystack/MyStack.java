package ph.edu.dlsu.datasal.wenceslao.mystack;

import ph.edu.dlsu.datasal.wenceslao.myinterface.Stack;

public class  MyStack<E> implements Stack<E>{  
    private final int size = 11;
    private E[] items;
    private int numItems;
    public int topOfStack;
    
    public MyStack(){
        initializeStack();
    }
    
    public void initializeStack(){  
        items = (E[]) new  Object[size];
        numItems = 0;
        topOfStack =- 1;
    }
 
    public void push(E obj){
        if(isFull()){
            System.out.println("STACK OVERFLOW");
        }
        else {
            topOfStack = topOfStack + 1;  
            items[topOfStack] = obj;
        } 
    }
    
    public void pop(){  
        if(isEmpty()){
            System.out.println("STACK IS EMPTY");
            initializeStack();
        }
        else {
           topOfStack = topOfStack - 1;
        }
    }
    
    public boolean isEmpty(){
        return topOfStack ==- 1; 
    }

    public boolean isFull(){
       return topOfStack == size - 1; 
    }

    public E top(){
        return items[topOfStack];
    }
}