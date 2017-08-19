/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.datasal.chan.mystack;
//import java.lang.reflect.Field;//optional,for toString shortcut

import ph.edu.dlsu.datasal.chan.myarraylist.*;
//import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class StackInt extends Stack<Integer> {
    
        
    public StackInt(){//constructor
        super();
    }
    public StackInt(int MaxList){
        super(MaxList);
    }
    
    //other methods
    //highest is TOS
    public void sort(){
        StackInt temp=mergeSort(this);
        clear();
        StackInt temp2=new StackInt(temp.size()+10);
        while(!temp.isEmpty()){
            temp2.push(temp.top());
            temp.pop();
        }
        while(!temp2.isEmpty()){
            push(temp2.top());
            temp2.pop();
        }
    }
    
    public StackInt mergeSort(StackInt input){
        if(input.size()<=0){
            throw new IllegalStateException("Empty");
        } else if (input.size()==1){
            return input;
        } else {
            int half=(int)Math.floor(input.size()/2);
            StackInt low=new StackInt(half+10);
            StackInt high=new StackInt(half+10);
            for(int i=1;i<=half;i++){
                low.push(input.top());
                input.pop();
            }
            for(int i=half+1;i<=input.size();i++){
                high.push(input.top());
                input.pop();
            }
            low=mergeSort(low);
            high=mergeSort(high);
            return merge(low,high);
        }
    }
    private StackInt merge(StackInt A,StackInt B){
        StackInt result=new StackInt(A.size()+B.size()+10);
        while(!A.isEmpty() && !B.isEmpty()){
            if(A.top()>B.top()){
                result.push(B.top());
                B.pop();
            } else {
                result.push(A.top());
                A.pop();
            }
        }
        //left
        while(!A.isEmpty()){
            result.push(A.top());
            A.pop();
        }
        while(!B.isEmpty()){
            result.push(B.top());
            B.pop();
        }
        return result;
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString shortcut">
    /*//++toString shortcut
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Field f: getClass().getDeclaredFields()) {
            try {
            result
            .append(f.getName())
            .append(" : ")
            .append(f.get(this))
            .append(System.getProperty("line.separator"));
            }
            catch (IllegalStateException ise) {
                result
                .append(f.getName())
                .append(" : ")
                .append("[cannot retrieve value]")
                .append(System.getProperty("line.separator"));
            }
            // nope
            catch (IllegalAccessException iae) {}
        }
        return result.toString();
    }*/
    // </editor-fold>
}
