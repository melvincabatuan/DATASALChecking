/**
 * Created by Jacob on 6/25/2016.
 */
package datasal.Narag.calculator;
public class Multiply extends BinaryOperator {
    public Multiply(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getFirst().getValue() * getSecond().getValue();
    }
}
