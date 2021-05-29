package landing.l1.adapters;

import landing.l2.applicationCode.function.Function;
import landing.l3.domainCode.representation.PixelCoordinate;
import landing.l3.domainCode.representation.ValueCoordinate;
import landing.l0.plugins.ui.GraphPanel;

public class Graph {
	private final ValueTable valueTable;
	private final GraphPanel graphPanel;
	private double resolutionOfxValuesAsNumber;
	private final Function function;
	
	public Graph(GraphPanel graphPanel, Function function) {
		this.graphPanel = graphPanel;
		this.function = function;
		resolutionOfxValuesAsNumber = 0.01;
		valueTable = new ValueTable(this);
		updateValueTable();
	}

	public void updateValueTable() throws ArithmeticException {
		valueTable.calculateValues();
		valueTable.calculateDerivativeValues();
	}

	public PixelCoordinate convertValueToPixelCoordinate(ValueCoordinate valueCoordinate) {
		Integer x = graphPanel.getCenterOfCoordinateSystem().getX() + (int) (valueCoordinate.getX()*graphPanel.getGridWidthInPixels());
		Integer y = graphPanel.getCenterOfCoordinateSystem().getY() - (int) (valueCoordinate.getY()*graphPanel.getGridWidthInPixels());
		return new PixelCoordinate(x, y);
	}

	public ValueCoordinate getValueToPixelMostLeft() {
		Double x = -(double)graphPanel.getCenterOfCoordinateSystem().getX() / graphPanel.getGridWidthInPixels();
		Double y = -(double)graphPanel.getCenterOfCoordinateSystem().getY() / graphPanel.getGridWidthInPixels();
		return new ValueCoordinate(x, y);
	}
	
	public ValueCoordinate getValueToPixelMostRight() {
		Double x = (double)(graphPanel.getWidth() - graphPanel.getCenterOfCoordinateSystem().getX()) / graphPanel.getGridWidthInPixels();
		Double y = (double)(graphPanel.getHeight() - graphPanel.getCenterOfCoordinateSystem().getY()) / graphPanel.getGridWidthInPixels();
		return new ValueCoordinate(x, y);
	}

	public double getResolutionOfxValues() {
		return resolutionOfxValuesAsNumber;
	}

	public void setResolutionOfxValues(double resolutionOfxValuesAsNumber) {
		this.resolutionOfxValuesAsNumber = resolutionOfxValuesAsNumber;
		updateValueTable();
	}
	
	public void setResolutionOfxValues(ResolutionOfxValues resolutionOfxValues) {
		switch(resolutionOfxValues) {
			case ACCORDING_TO_PIXEL: setResolutionOfxValues(1.0 / graphPanel.getGridWidthInPixels());
			break;
			case LOW: setResolutionOfxValues(10.0 / graphPanel.getGridWidthInPixels());
			break;
			case HIGH: setResolutionOfxValues(1.0 / (3 * graphPanel.getGridWidthInPixels()));
			break;
			case CUSTOMIZED: setResolutionOfxValues(1.0);
			break;
			default:
				throw new IllegalStateException("Unexpected type: " + resolutionOfxValues);
		}
	}

	public Function letFunction() {
		return function;
	}

	public ValueTable getValueTable() {
		return valueTable;
	}
}
