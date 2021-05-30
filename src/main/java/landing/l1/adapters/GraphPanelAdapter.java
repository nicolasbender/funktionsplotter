package landing.l1.adapters;

import landing.l0.plugins.ui.GraphPanel;
import landing.l3.domainCode.representation.PixelCoordinate;
import landing.l3.domainCode.representation.ValueCoordinate;

public class GraphPanelAdapter {
    private GraphPanel graphPanel;

    public GraphPanelAdapter(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public void determineInitialCenterOfCoordinateSystem() {
        int halfOfxAxis = graphPanel.getWidth() / 2;
        int halfOfyAxis = graphPanel.getHeight() / 2;
        graphPanel.setCenterOfCoordinateSystem(new PixelCoordinate(halfOfxAxis, halfOfyAxis));
    }

    public PixelCoordinate convertValueToPixelCoordinate(ValueCoordinate valueCoordinate) {
        Integer x = graphPanel.getCenterOfCoordinateSystem().getX() + (int) (valueCoordinate.getX()*graphPanel.getGridWidthInPixels());
        Integer y = graphPanel.getCenterOfCoordinateSystem().getY() - (int) (valueCoordinate.getY()*graphPanel.getGridWidthInPixels());
        return new PixelCoordinate(x, y);
    }

    public ValueCoordinate getValueOfPixelMostLeft() {
        Double x = -(double)graphPanel.getCenterOfCoordinateSystem().getX() / graphPanel.getGridWidthInPixels();
        Double y = -(double)graphPanel.getCenterOfCoordinateSystem().getY() / graphPanel.getGridWidthInPixels();
        return new ValueCoordinate(x, y);
    }

    public ValueCoordinate getValueOfPixelMostRight() {
        Double x = (double)(graphPanel.getWidth() - graphPanel.getCenterOfCoordinateSystem().getX()) / graphPanel.getGridWidthInPixels();
        Double y = (double)(graphPanel.getHeight() - graphPanel.getCenterOfCoordinateSystem().getY()) / graphPanel.getGridWidthInPixels();
        return new ValueCoordinate(x, y);
    }
}
