package landing.l3.domainCode.representation;

import landing.l4.abstractionCode.Tuple;

import java.util.Objects;

public class PixelCoordinate implements Tuple<Integer> {
	private final Integer x;
	private final Integer y;

	public PixelCoordinate(Integer x, Integer y) {
		//TODO check if x and y are valid values
		this.x = x;
		this.y = y;
	}

	@Override
	public Integer getX() {
		return x;
	}

	@Override
	public Integer getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PixelCoordinate that = (PixelCoordinate) o;
		return x.equals(that.x) && y.equals(that.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
