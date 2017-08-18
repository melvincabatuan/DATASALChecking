package ph.edu.dlsu.datasal.chan.myqueue;

import java.util.Collection;
import ph.edu.dlsu.datasal.chan.mylinkedlist.MyLinkedList;

/* © 2017 by Patrick Matthew Chan */

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Queue<E> extends QueueBasic<E>{
    //constructor
    public Queue(){
        super();
    }
    
    //addtl methods
    public void clear(){
        items.clear();//reset
    }
    
    public String toString(){
        return new String("Q:"+items.toString());
    }
    
    
    
    
    
    
    //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////
    public Queue<E> copyOf(){
        Queue<E> tempQ=new Queue<>();
        for(int i=1;i<=items.size();i++){
            tempQ.push(items.get(i));
        }
        return tempQ;
    }
    
    
    public boolean contains(Object o){//maybe improve later
        return items.contains(o);
    }
    public boolean containsAll(Collection<?> c){
        for(Object o:c){
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }
    public boolean addAll(Collection<? extends E> c){
        for(Object o:c){
            try{
                items.add((E) o);
            } catch (ClassCastException err){
                err.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean removeAll(Collection<? extends E> c){
        boolean isChanged=false;
        for(Object o:c){
            if(items.remove(o)){
                isChanged=true;
            }
        }
        return isChanged;
    }
    public boolean equals(Object o){
        if(o instanceof Queue){
            Queue comp=(Queue)o;
            if(comp.size()!=size()){
                return false;
            }
            Queue A=copyOf();
            Queue B=comp.copyOf();
            while(!A.isEmpty()){
                if(!A.front().equals(B.front())){
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
    
}
