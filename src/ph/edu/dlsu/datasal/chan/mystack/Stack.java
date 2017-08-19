package ph.edu.dlsu.datasal.chan.mystack;

import java.util.Collection;
import ph.edu.dlsu.datasal.chan.myarraylist.MyLinkedList;


public class Stack<E> extends StackBasic<E>{
    //constructor
    public Stack(){
        super();
    }
    public Stack(int s) {
        super(s);
    }
    //additional methods
    public int size(){//1-based
        return top+1;
    }
    public void clear(){
        top=-1;
    }
    public void reverse(){
        E[] temp=(E[])new Object[stackArray.length];
        for(int i=0;i<size();i++){
            temp[size()-1-i]=stackArray[i];
        }
        stackArray=temp;
    }
    public Stack<E> copyOf(){
        Stack<E> temp=new Stack<>(maxSize);
        for(int i=0;i<size();i++){
            temp.push(stackArray[i]);
        }
        return temp;
    }
    public int getMaxItems(){//++
        return maxSize;
    }
    
    
    
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    
    public boolean contains(Object o){
        for(int i=0;i<=top;i++){
            E item=stackArray[i];
            if(item.equals(o)){
                return true;
            }
        }
        return false;
    }
    public boolean containsAll(Stack<E> c){
        Stack<E> temp=c.copyOf();
        while(!temp.isEmpty()){
            E item=temp.top();
            temp.pop();
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }
    public boolean addAll(Stack<E> c){
        Stack<E> temp=c.copyOf();
        while(!temp.isEmpty()){
            E item=temp.top();
            temp.pop();
            push(item);
        }
        return true;
    }
    public boolean removeAll(Stack<E> c){
        boolean isChanged=false;
        MyLinkedList<E> tempL1=new MyLinkedList(size()+10);
        while(!isEmpty()){
            tempL1.add(top());
            pop();
        }
        MyLinkedList<E> tempL2=new MyLinkedList(c.size()+10);
        while(!c.isEmpty()){
            tempL2.add(top());
            c.pop();
        }
        isChanged=tempL1.removeAll(tempL2);
        //return to stack
        while(!tempL1.isEmpty()){
            push(tempL1.get(tempL1.size()));
            tempL1.remove(tempL1.size());
        }
        while(!tempL2.isEmpty()){
            c.push(tempL2.get(tempL2.size()));
            tempL2.remove(tempL2.size());
        }
        return isChanged;
    }
    public boolean equals(Object o){
        if(o instanceof Stack){
            Stack comp=(Stack)o;
            if(comp.size()!=size()){
                return false;
            }
            Stack A=copyOf();
            Stack B=comp.copyOf();
            while(!A.isEmpty()){
                if(!A.top().equals(B.top())){
                    return false;
                }
                A.pop();
                B.pop();
            }
            return true;
        } else {
            return false;
        }
    }
    
    public Stack<E> intersection(Stack<E> comp){
        Stack<E> ans=new Stack<>(size()>comp.size()?
                size()+10:comp.size()+10);//ternary operator
        for(int i=0;i<=top;i++){
            E item=stackArray[i];
            if(comp.contains(item)){
                ans.push(item);
            }
        }
        return ans;
    }
}