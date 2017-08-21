/**
 * Created by Jacob on 6/25/2016.
 */
package datasal.Narag.calculator;
public class Divide extends BinaryOperator {
    public Divide(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return (getSecond().getValue() / getFirst().getValue());
    }
}
