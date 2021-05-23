package landing.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValueTable {
	private List<ValueCoordinate> valueTable;
	private List<ValueCoordinate> derivativeValueTable;
	private final Graph graph;
	
	public ValueTable(Graph graph) {
		this.graph = graph;
		this.valueTable = new ArrayList<>();
		this.derivativeValueTable = new ArrayList<>();
	}

	public List<ValueCoordinate> getValueCoordinate() {
		return valueTable;
	}

	public List<ValueCoordinate> getDerivativeValueCoordinate() {
		return derivativeValueTable;
	}

	public void calculateValues() {
		ValueCoordinate valueMostLeft = graph.getValueToPixelMostLeft();
		ValueCoordinate valueMostRight = graph.getValueToPixelMostRight();
		double xResolution = graph.getResolutionOfxValues();
		for(double currentX = valueMostLeft.getX(); currentX < valueMostRight.getX(); currentX += xResolution) {
			double currentY = graph.letFunction().calculateValueOf(currentX);
			this.valueTable.add(new ValueCoordinate(currentX, currentY));
		}
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
		derivativeValue = firstOrderDerivativeCentral(yCurrent, yAfter);
		valueTable.add(new ValueCoordinate(xCurrent, derivativeValue));

		for(int tableIndex = 1; tableIndex < secondLastIndex; tableIndex++) {
			xCurrent = valueTable.get(tableIndex).getX();
			yBefore = valueTable.get(tableIndex - 1).getY();
			yAfter = valueTable.get(tableIndex + 1).getY();
			derivativeValue = firstOrderDerivativeCentral(yBefore, yAfter);
			valueTable.add(new ValueCoordinate(xCurrent, derivativeValue));
		}

		yBefore = valueTable.get(secondLastIndex).getY();
		xCurrent = valueTable.get(lastIndex).getX();
		yCurrent = valueTable.get(lastIndex).getY();
		derivativeValue = firstOrderDerivativeBackward(yBefore, yCurrent);
		valueTable.add(new ValueCoordinate(xCurrent, derivativeValue));
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
