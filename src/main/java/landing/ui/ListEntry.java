package landing.ui;

public class ListEntry {
	ValueCoordinate valueCoordinate;
	
	public ListEntry(ValueCoordinate valueCoordinate) {
		this.valueCoordinate = valueCoordinate;
	}

	public Double getxValue() {
		return valueCoordinate.getX();
	}

	public Double getyValue() {
		return valueCoordinate.getY();
	}
}
