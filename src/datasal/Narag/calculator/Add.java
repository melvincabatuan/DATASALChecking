/**
 * Created by Jacob on 6/25/2016.
 */
package datasal.Narag.calculator;
public class Add extends BinaryOperator {
    public Add(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getFirst().getValue() + getSecond().getValue();
    }
}
