package ph.edu.dlsu.datasal.wenceslao.calculator;

public abstract class BinaryOperator implements Operand {
    private Operand first;
    private Operand second;

    public BinaryOperator(Operand first, Operand second) {
        super();
        this.first = first;
        this.second = second;
    }

    public Operand getFirst() {
        return first;
    }

    public Operand getSecond() {
        return second;
    }
}
