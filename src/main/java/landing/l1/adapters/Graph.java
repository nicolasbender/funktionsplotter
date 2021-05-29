package landing.l1.adapters;

import landing.l2.applicationCode.function.Function;
import landing.l3.domainCode.representation.ValueCoordinate;

public class Graph {
	private final ValueTable valueTable;
	private ValueCoordinate valueOfPixelMostLeft;
	private ValueCoordinate valueOfPixelMostRight;
	private double resolutionOfxValuesAsNumber;
	private Function function;
	
	public Graph(Function function, ValueCoordinate valueOfPixelMostLeft, ValueCoordinate valueOfPixelMostRight) {
		this.function = function;
		this.valueOfPixelMostLeft = valueOfPixelMostLeft;
		this.valueOfPixelMostRight = valueOfPixelMostRight;
		resolutionOfxValuesAsNumber = 0.01;
		valueTable = new ValueTable(this);
		updateValueTable(valueOfPixelMostLeft, valueOfPixelMostRight);
	}

	public void updateValueTable(ValueCoordinate valueOfPixelMostLeft, ValueCoordinate valueOfPixelMostRight) throws ArithmeticException {
		this.valueOfPixelMostLeft = valueOfPixelMostLeft;
		this.valueOfPixelMostRight = valueOfPixelMostRight;
		valueTable.calculateValuesBetween(valueOfPixelMostLeft, valueOfPixelMostRight);
	}

	public double getResolutionOfxValues() {
		return resolutionOfxValuesAsNumber;
	}

	public void setResolutionOfxValues(double resolutionOfxValuesAsNumber) {
		if(resolutionOfxValuesAsNumber <= 0 || resolutionOfxValuesAsNumber > 10) {
			throw new NumberFormatException();
		} else {
			this.resolutionOfxValuesAsNumber = resolutionOfxValuesAsNumber;
			updateValueTable(valueOfPixelMostLeft, valueOfPixelMostRight);
		}
	}
	
	public void setResolutionOfxValues(ResolutionOfxValues resolutionOfxValues, int gridWidthInPixels) {
		switch(resolutionOfxValues) {
			case ACCORDING_TO_PIXEL: setResolutionOfxValues(1.0 / gridWidthInPixels);
			break;
			case LOW: setResolutionOfxValues(50.0 / gridWidthInPixels);
			break;
			case HIGH: setResolutionOfxValues(1.0 / (3 * gridWidthInPixels));
			break;
			case CUSTOMIZED: setResolutionOfxValues(0.01);
			break;
			default:
				throw new IllegalStateException("Unexpected type: " + resolutionOfxValues);
		}
	}

	public Function letFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public ValueTable getValueTable() {
		return valueTable;
	}
}
