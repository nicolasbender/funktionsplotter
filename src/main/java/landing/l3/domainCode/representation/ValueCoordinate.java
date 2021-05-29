package landing.l3.domainCode.representation;

import landing.l4.abstractionCode.Tuple;

import java.util.Objects;

public class ValueCoordinate implements Tuple<Double> {
    private final Double x;
    private final Double y;

    public ValueCoordinate(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueCoordinate that = (ValueCoordinate) o;
        return Math.abs(x - that.x) < 1E-6 && Math.abs(y - that.y) < 1E-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
