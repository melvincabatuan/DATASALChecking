/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.datasal.chan.myqueue;
//import java.lang.reflect.Field;//optional,for toString shortcut

import ph.edu.dlsu.datasal.chan.mystack.*;
import ph.edu.dlsu.datasal.chan.myarraylist.*;
//import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class QueueInt extends Queue<Integer> {
    
        
    public QueueInt(){//constructor
        super();
    }
    
    //other methods
    //highest is Rear
    public QueueInt sort(QueueInt input){
        if(input.size()<=0){
            throw new IllegalStateException("Empty");
        } else if (input.size()==1){
            return input;
        } else {
            int half=(int)Math.floor(input.size()/2);
            QueueInt low=new QueueInt();
            QueueInt high=new QueueInt();
            for(int i=1;i<=half;i++){
                low.push(input.front());
                input.pop();
            }
            for(int i=half+1;i<=input.size();i++){
                high.push(input.front());
                input.pop();
            }
            low=sort(low);
            high=sort(high);
            return merge(low,high);
        }
    }
    private QueueInt merge(QueueInt A,QueueInt B){
        QueueInt result=new QueueInt();
        while(!A.isEmpty() && !B.isEmpty()){
            if(A.front()>B.front()){
                result.push(B.front());
                B.pop();
            } else {
                result.push(A.front());
                A.pop();
            }
        }
        //left
        while(!A.isEmpty()){
            result.push(A.front());
            A.pop();
        }
        while(!B.isEmpty()){
            result.push(B.front());
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
