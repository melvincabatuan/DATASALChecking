package ph.edu.dlsu.datasal.wenceslao.calculator;

public class Subtract extends BinaryOperator {
    public Subtract(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getSecond().getValue() - getFirst().getValue();
    }
}
