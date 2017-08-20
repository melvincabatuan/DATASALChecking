
package ph.edu.dlsu.datasal.ocampo.calculator;
import java.util.*;

public class MyStringStack {
    
    private LinkedList<String> stringStack;
    
    // Constructor
    public MyStringStack() {
        stringStack = new LinkedList<>();
    }
    
    // Is empty?
    public boolean isEmpty() {
        return stringStack.isEmpty();
    }
    
    // Size
    public int size() {
        return stringStack.size();
    }
    
    // Push
    public void push(String item) {
        stringStack.add(0, item);
    }
    
    // Top
    public String getTop() {
        String r="";
        if(!isEmpty()) r = stringStack.get(0);
        return r;
    }
    
    // Pop
    public void pop() {
        if(!isEmpty()) stringStack.remove(0);
    }
    
    public void popAll() {
        if(!isEmpty()) stringStack.clear();
    }
    
} // end of class
