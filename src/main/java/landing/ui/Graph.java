package landing.ui;

import landing.function.FunctionManager;

public class Graph {
	private ValueTable valueTable;
	private final GraphPanel graphPanel;
	private double resolutionOfxValuesAsNumber;
	private FunctionManager functionManager;
	
	public Graph(GraphPanel graphPanel) { // TODO: All functions in GraphPanel, ValueTable belongs in here
		this.graphPanel = graphPanel;
		setResolutionOfxValues(0.01);
		valueTable = new ValueTable(this);
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
	}
	
	public void setResolutionOfxValues(ResolutionOfxValues resolutionOfxValues) {
		switch(resolutionOfxValues) {
			case ACCORDING_TO_PIXEL: resolutionOfxValuesAsNumber = 1.0 / graphPanel.getGridWidthInPixels();
			break;
			case LOW: resolutionOfxValuesAsNumber = 10.0 / graphPanel.getGridWidthInPixels();
			break;
			case HIGH: resolutionOfxValuesAsNumber = 1.0 / (3 * graphPanel.getGridWidthInPixels());
			break;
			case CUSTOMIZED: resolutionOfxValuesAsNumber = 1.0;
			break;
			default:
				throw new IllegalStateException("Unexpected type: " + resolutionOfxValues);
		}
	}

	public FunctionManager letFunction() {
		return null;
	}
}