package landing.l1.adapters;

import landing.l3.domainCode.representation.ValueCoordinate;

import java.util.ArrayList;
import java.util.List;

public class ValueTable {
	private List<ValueCoordinate> valueTable;
	private List<ValueCoordinate> derivativeValueTable;
	private final Graph graph;
	
	public ValueTable(Graph graph) {
		this.graph = graph;
	}

	public List<ValueCoordinate> getValueTable() {
		return valueTable;
	}

	public List<ValueCoordinate> getDerivativeValueTable() {
		return derivativeValueTable;
	}

	public void calculateValuesBetween(ValueCoordinate valueMostLeft, ValueCoordinate valueMostRight) throws ArithmeticException {
		this.valueTable = new ArrayList<>();
		double xResolution = graph.getResolutionOfxValues();

		for(double currentX = valueMostLeft.getX(); currentX <= valueMostRight.getX(); currentX += xResolution) {
			double currentY = graph.letFunction().calculateValueOf(currentX);
			this.valueTable.add(new ValueCoordinate(currentX, currentY));
		}

		DerivativeCalculator derivativeCalculator = new DerivativeCalculator(xResolution);
		derivativeValueTable = derivativeCalculator.calculateDerivativeValuesFor(valueTable);
	}

	public int getSize() {
		return valueTable.size();
	}
}
