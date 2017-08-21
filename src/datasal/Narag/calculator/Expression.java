/**
 * Created by Gershom
 */
package datasal.Narag.calculator;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Expression implements Operand {
    private Map<String, Variable> variables = new TreeMap<String, Variable>();

    private Operand operand;

    public Expression(String line) {
        Stack<Operand> stack = new Stack<Operand>();
        for (String token : line.split("\\s+")) {
            if ("+-*/".contains(token)) {
                switch (token) {
                    case "+":
                        stack.push(new Add(stack.pop(), stack.pop()));
                        break;
                    case "-":
                        stack.push(new Subtract(stack.pop(), stack.pop()));
                        break;
                    case "*":
                        stack.push(new Multiply(stack.pop(), stack.pop()));
                        break;
                    case "/":
                        stack.push(new Divide(stack.pop(), stack.pop()));
                        break;
                }
            } else if (Character.isDigit(token.charAt(0))) {
                stack.push(new Constant(Double.valueOf(token)));
            } else {
                stack.push(getVariable(token));
            }
        }
        this.operand = stack.pop();
    }

    
    public double getValue() {
        return operand.getValue();
    }

    public Operand getVariable(String name) {
        Variable variable = variables.get(name);
        if (variable == null) {
            variable = new Variable(name);
            variables.put(name, variable);
        }
        return variable;
    }
}
