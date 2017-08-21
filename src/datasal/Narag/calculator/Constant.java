/**
 * Created by Jacob on 6/25/2016.
 */
package datasal.Narag.calculator;

public class Constant implements Operand {
    private double value;

    public Constant(double value) {
        super();
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}
