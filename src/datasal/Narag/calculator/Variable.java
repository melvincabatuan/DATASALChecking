/**
 * Created by Jacob on 6/25/2016.
 */
package datasal.Narag.calculator;
public class Variable implements Operand {
    private String name;
    private double value;

    public Variable(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}