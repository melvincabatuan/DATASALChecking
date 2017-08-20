
package ph.edu.dlsu.datasal.ocampo.calculator;
import java.util.*;

public class Infix {
    
    private LinkedList<String> infix;
    private CalculatorOperators current;
    private String infixString;
    
    // Constructor
    public Infix(String expression) {
        infix = new LinkedList<>();
        current = new CalculatorOperators("0");
        infixString = expression;
        
        String temp1="", temp2="", temp3="";
        for(int i=0; i<expression.length(); i++) {
            current.setSymbol( Character.toString(expression.charAt(i)) );
            
            if(current.isOperand()) temp1 += current.getSymbol();
            else if(current.isOperator()) {
                if(temp1.length()>0) {
                    infix.add(temp1);
                    infix.add(current.getSymbol());
                    temp1 = "";
                }
                else {
                    if(i==0) {
                        temp2 = current.getSymbol();
                        if(temp2.equals("+") || temp2.equals("-")) infix.add("0");
                        infix.add(temp2);
                    }
                    else {
                        temp2 = infix.get(infix.size()-1);
                        if(current.isEqualPrecedenceAs(temp2)) {
                            if(temp2.equals("+")) infix.add("0");
                            else if(temp2.equals("-")) {
                                temp3 = current.getSymbol();
                                infix.remove(infix.size()-1);
                                infix.add("+");
                                infix.add(infix.size(), "0");
                                current.setSymbol(temp3.equals("+")?"-":"+");
                            }
                        }
                        infix.add(current.getSymbol());
                    }
                }
            }
        }
        
        if(temp1.length()>0) infix.add(temp1);
    }
    
    // Get and set infix
    public LinkedList<String> getInfix() {
        return infix;
    }
    
    public String getInfixString() {
        return infixString;
    }
    
    public void setInfix(String expression) {
        infix.clear();
        infixString = expression;
        
        String temp1="", temp2="", temp3="";
        for(int i=0; i<expression.length(); i++) {
            current.setSymbol( Character.toString(expression.charAt(i)) );
            
            if(current.isOperand()) temp1 += current.getSymbol();
            else if(current.isOperator()) {
                if(temp1.length()>0) {
                    infix.add(temp1);
                    infix.add(current.getSymbol());
                    temp1 = "";
                }
                else {
                    if(i==0) {
                        temp2 = current.getSymbol();
                        if(temp2.equals("+") || temp2.equals("-")) infix.add("0");
                        infix.add(temp2);
                    }
                    else {
                        temp2 = infix.get(infix.size()-1);
                        if(current.isEqualPrecedenceAs(temp2)) {
                            if(temp2.equals("+")) infix.add("0");
                            else if(temp2.equals("-")) {
                                temp3 = current.getSymbol();
                                infix.remove(infix.size()-1);
                                infix.add("+");
                                infix.add(infix.size(), "0");
                                current.setSymbol(temp3.equals("+")?"-":"+");
                            }
                        }
                        infix.add(current.getSymbol());
                    }
                }
            }
        }
        
        if(temp1.length()>0) infix.add(temp1);
    }
    
    // Get postfix
    public LinkedList<String> getPostfix() {
        LinkedList<String> postfix = new LinkedList<>();
        MyStringStack operatorStack = new MyStringStack();
        
        for(int i=0; i<infix.size(); i++) {
            current.setSymbol(infix.get(i));
            
            if(current.isOperand()) postfix.add(current.getSymbol());
            else if(current.isOperator()) {
                if(operatorStack.isEmpty()) operatorStack.push(current.getSymbol());
                else {
                    while(current.isLowerPrecedenceThan(operatorStack.getTop()) ||
                            current.isEqualPrecedenceAs(operatorStack.getTop())) {
                        postfix.add(operatorStack.getTop());
                        operatorStack.pop();
                    }
                    operatorStack.push(current.getSymbol());
                }
            }
        }
        
        while(!operatorStack.isEmpty()) {
            postfix.add(operatorStack.getTop());
            operatorStack.pop();
        }
        
        return postfix;
    }
    
} // end of class
