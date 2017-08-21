/**
 * Created by Jacob on 6/25/2016.
 */
package datasal.Narag.calculator;
public class Subtract extends BinaryOperator {
    public Subtract(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getSecond().getValue() - getFirst().getValue();
    }
}
