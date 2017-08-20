package ph.edu.dlsu.datasal.wenceslao.calculator;

public class Multiply extends BinaryOperator {
    public Multiply(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getFirst().getValue() * getSecond().getValue();
    }
}
