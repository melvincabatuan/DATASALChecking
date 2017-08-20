package ph.edu.dlsu.datasal.wenceslao.calculator;

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
