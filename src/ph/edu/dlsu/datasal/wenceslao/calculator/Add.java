package ph.edu.dlsu.datasal.wenceslao.calculator;

public class Add extends BinaryOperator {
    public Add(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getFirst().getValue() + getSecond().getValue();
    }
}
