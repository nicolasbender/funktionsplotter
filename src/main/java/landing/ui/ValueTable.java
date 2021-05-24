package landing.ui;

import java.util.ArrayList;
import java.util.List;

public class ValueTable {
	private final List<ValueCoordinate> valueTable;
	private final List<PixelCoordinate> pixelValueTable;
	private final List<ValueCoordinate> derivativeValueTable;
	private final List<PixelCoordinate> pixelDerivativeValueTable;
	private final Graph graph;
	
	public ValueTable(Graph graph) {
		this.graph = graph;
		this.valueTable = new ArrayList<>();
		this.pixelValueTable = new ArrayList<>();
		this.derivativeValueTable = new ArrayList<>();
		this.pixelDerivativeValueTable = new ArrayList<>();
	}

	public List<ValueCoordinate> getValueTable() {
		return valueTable;
	}

	public List<ValueCoordinate> getDerivativeValueTable() {
		return derivativeValueTable;
	}

	public List<PixelCoordinate> getPixelValueTable() {
		return pixelValueTable;
	}

	public List<PixelCoordinate> getPixelDerivativeValueTable() {
		return pixelDerivativeValueTable;
	}

	public void calculateValues() {
		ValueCoordinate valueMostLeft = graph.getValueToPixelMostLeft();
		ValueCoordinate valueMostRight = graph.getValueToPixelMostRight();
		double xResolution = graph.getResolutionOfxValues();
		for(double currentX = valueMostLeft.getX(); currentX < valueMostRight.getX(); currentX += xResolution) {
			double currentY = graph.letFunction().calculateValueOf(currentX);
			addValueToTable(new ValueCoordinate(currentX, currentY));
		}
	}

	private void addValueToTable(ValueCoordinate currentValue) {
		this.valueTable.add(currentValue);
		this.pixelValueTable.add(graph.convertValueToPixelCoordinate(currentValue));
	}

	private void addValueToDerivativeTable(ValueCoordinate currentValue) {
		this.derivativeValueTable.add(currentValue);
		this.pixelDerivativeValueTable.add(graph.convertValueToPixelCoordinate(currentValue));
	}

	public void calculateDerivativeValues() {
		double yBefore;
		double xCurrent;
		double yCurrent;
		double yAfter;
		double derivativeValue;

		int startIndex = 0;
		int lastIndex = valueTable.size() - 1;
		int secondLastIndex = lastIndex - 1;

		xCurrent = valueTable.get(startIndex).getX();
		yCurrent = valueTable.get(startIndex).getY();
		yAfter = valueTable.get(startIndex + 1).getY();
		derivativeValue = firstOrderDerivativeForward(yCurrent, yAfter);
		addValueToDerivativeTable(new ValueCoordinate(xCurrent, derivativeValue));

		for(int tableIndex = 1; tableIndex < secondLastIndex; tableIndex++) {
			xCurrent = valueTable.get(tableIndex).getX();
			yBefore = valueTable.get(tableIndex - 1).getY();
			yAfter = valueTable.get(tableIndex + 1).getY();
			derivativeValue = firstOrderDerivativeCentral(yBefore, yAfter);
			addValueToDerivativeTable(new ValueCoordinate(xCurrent, derivativeValue));
		}

		yBefore = valueTable.get(secondLastIndex).getY();
		xCurrent = valueTable.get(lastIndex).getX();
		yCurrent = valueTable.get(lastIndex).getY();
		derivativeValue = firstOrderDerivativeBackward(yBefore, yCurrent);
		addValueToDerivativeTable(new ValueCoordinate(xCurrent, derivativeValue));
	}

	public int getSize() {
		return valueTable.size();
	}

	public double firstOrderDerivativeCentral(double yBefore, double yAfter) {
		return (yAfter - yBefore) / 2;
	}
	public double firstOrderDerivativeForward(double yCurrent, double yAfter) {
		return yCurrent - yAfter;
	}
	public double firstOrderDerivativeBackward(double yBefore, double yCurrent) {
		return yCurrent - yBefore;
	}
}
