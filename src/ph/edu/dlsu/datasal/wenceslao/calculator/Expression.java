package ph.edu.dlsu.datasal.wenceslao.calculator;

import ph.edu.dlsu.datasal.wenceslao.mystack.MyStack;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Expression implements Operand {
    private Map<String, Variable> variables = new TreeMap<String, Variable>();
    private Operand operand ;
    public Operand c ;
   
    public Expression (String line) {
        MyStack<Operand> stack = new MyStack<Operand>();
        for (String token : line.split( " \\ s+" )) {
            if ( "+-*/" .contains(token)) {
            switch (token) {
                case "+" :
                    Operand a = stack.top();
                    stack.pop();
                    Operand b = stack.top();
                    double c = a.getValue() + b.getValue();
                    stack.top().equals(c);
                    break ;
                case "-" :
                    Operand d = stack.top();
                    stack.pop();
                    Operand e = stack.top();
                    c = e.getValue() - d.getValue();
                    stack.top().equals(c);
                    break ;
                case "*" :
                    Operand g = stack.top();
                    stack.pop();
                    Operand h = stack.top();
                    c = g.getValue() * h.getValue();
                    stack.top().equals(c);
                    break ;
                case "/" :
                    Operand i = stack.top();
                    stack.pop();
                    Operand n = stack.top();
                    c = n.getValue() / i.getValue();
                    stack.top().equals(c);
                    break ;
                }
            } 
            else if (Character. isDigit (token.charAt( 0 ))) {
                stack.push( new Constant(Double. valueOf (token)));
            } 
            else {
                stack.push(getVariable(token));
            }
        }
        this . operand = c ;
    }
    
    @Override
    public double getValue() {
        return operand .getValue();
    }
        
    public Operand getVariable(String name) {
        Variable variable = variables.get(name);
        if (variable == null ) {
            variable = new Variable(name);
            variables .put(name, variable);
        }
        return variable;
    }
}