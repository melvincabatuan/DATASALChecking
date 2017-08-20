package ph.edu.dlsu.datasal.wenceslao.calculator;

public class Divide extends BinaryOperator {
    public Divide(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public double getValue() {
        return getSecond().getValue() / getFirst().getValue();
    }
}
