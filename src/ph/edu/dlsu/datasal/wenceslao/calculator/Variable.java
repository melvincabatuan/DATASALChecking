package ph.edu.dlsu.datasal.wenceslao.calculator;

public class Variable implements Operand {
    private String name;
    private double value;

    public Variable(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}