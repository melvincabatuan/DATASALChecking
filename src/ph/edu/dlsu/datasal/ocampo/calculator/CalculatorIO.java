
package ph.edu.dlsu.datasal.ocampo.calculator;
import java.util.*;

public class CalculatorIO {
    private Infix infixExp;
    private CalculatorOperators current;
    private boolean mathErrStatus;
    private boolean syntaxErrStatus;
    
    // Constructor
    public CalculatorIO(String infixExpression) {
        infixExp = new Infix(infixExpression);
        current = new CalculatorOperators("0");
        mathErrStatus = false;
        syntaxErrStatus = false;
    }
    
    // Get and set inputs
    public void setInput(String infixExpression) {
        infixExp.setInfix(infixExpression);
    }
    
    // Evaluate operations
    private double performOperation(double value1, double value2) {
        double res = 0;
        String operator = current.getSymbol();
        
        if(operator.equals("+")) res = value1+value2;
        else if(operator.equals("-")) res = value1-value2;
        else if(operator.equals("*")) res = value1*value2;
        else if(operator.equals("/")) {
            if(value2==0) mathErrStatus = true;
            else res = value1/value2;
        }
        else if(operator.equals("^")) {
            if(value1==0 && value2<=0) mathErrStatus = true;
            else res = Math.pow(value1, value2);
        }
        
        return res;
    }
    
    public double evaluate() {
        double res=0;
        LinkedList<String> postfix = infixExp.getPostfix();
        MyStringStack operandStack = new MyStringStack();
        double tempVal1=0, tempVal2=0;
        
        for(int i=0; i<postfix.size(); i++) {
            current.setSymbol(postfix.get(i));
            
            if(current.isOperand()) operandStack.push(current.getSymbol());
            else if(current.isOperator()) {
                if(!operandStack.isEmpty()) {
                    tempVal2 = Double.parseDouble(operandStack.getTop());
                    operandStack.pop();
                }
                else {
                    syntaxErrStatus = true; break;
                }
                
                if(!operandStack.isEmpty()) {
                    tempVal1 = Double.parseDouble(operandStack.getTop());
                    operandStack.pop();
                    res = performOperation(tempVal1, tempVal2);
                    operandStack.push(String.valueOf(res));
                }
                else {
                    syntaxErrStatus = true; break;
                }
            }
        }
        
        if(!operandStack.isEmpty()) res = Double.parseDouble(operandStack.getTop());
        return res;
    }
    
    // Calculator Output
    public String displayOutput() {
        String out = String.valueOf(evaluate());
        if(syntaxErrStatus) out = "Syntax Error";
        else if(mathErrStatus) out = "Math Error";
        
        return out;
    }
    
    public String displayInput() {
        return infixExp.getInfixString();
    }
    
    // Error handling
    public boolean isErrorIncurred() {
        return (syntaxErrStatus || mathErrStatus);
    }
    
    // Clear and reset
    public void clearErrors() {
        syntaxErrStatus = false;
        mathErrStatus = false;
    }
} // end of class
