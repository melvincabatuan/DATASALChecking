/*
 * CalculatorOperators is a class that handles both operators
 * and operands
 */
package ph.edu.dlsu.datasal.ocampo.calculator;

public class CalculatorOperators {
    
    private String item;
    
    // Constructor
    public CalculatorOperators(String symbol) {
        item = symbol;
    }
    
    // Get and set operators
    public String getSymbol() {
        return item;
    }
    
    public void setSymbol(String symbol) {
        item = symbol;
    }
    
    // Is operator
    public boolean isMDAS() {
        return (item.equals("*") || item.equals("/") ||
                item.equals("+") || item.equals("-"));
    }
    
    public boolean isOperator() {
        return (isMDAS() || item.equals("^"));
    }
    
    public boolean isEquals() {
        return item.equals("=");
    }
    
    private boolean isMDAS2(String symbol) {
        return (symbol.equals("*") || symbol.equals("/") ||
                symbol.equals("+") || symbol.equals("-"));
    }
    
    private boolean isOperator2(String symbol) {
        return (isMDAS2(symbol) || symbol.equals("^"));
    }
    
    // Is operand
    public boolean isOperand() {
        boolean y=true;
        for(int i=0; i<item.length(); i++) {
            if(!(Character.isDigit(item.charAt(i)) || item.charAt(i)=='.')) {
                y=false; break;
            }
        }
        return y;
    }
    
    // Operator precedence
    private int precedenceOver(String operator2) {
        int a=0, b=0;
        if(isOperator() && isOperator2(operator2)) {
            if(item.equals("^")) a=2;
            else if(item.equals("*") || item.equals("/")) a=1;
            if(operator2.equals("^")) b=2;
            else if(operator2.equals("*") || operator2.equals("/")) b=1;
        }
        return (a-b);
    }
    
    public boolean isHigherPrecedenceThan(String operator2) {
        return (precedenceOver(operator2)>0);
    }
    
    public boolean isLowerPrecedenceThan(String operator2) {
        return (precedenceOver(operator2)<0);
    }
    
    public boolean isEqualPrecedenceAs(String operator2) {
        return (precedenceOver(operator2)==0 && isOperator() && isOperator2(operator2));
    }
    
    // Integer
    public boolean isInteger() {
        boolean y=true;
        if(isOperand()) {
            for(int i=0; i<item.length(); i++) {
                if(item.charAt(i)=='.') {
                    y=false; break;
                }
            }
        }
        else y=false;
        
        return y;
    }
} // end of class
