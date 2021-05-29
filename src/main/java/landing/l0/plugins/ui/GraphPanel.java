package landing.l0.plugins.ui;

import landing.l1.adapters.Graph;
import landing.l1.adapters.ResolutionOfxValues;
import landing.l1.adapters.ValueTable;
import landing.l2.applicationCode.function.Function;
import landing.l3.domainCode.representation.PixelCoordinate;
import landing.l3.domainCode.representation.ValueCoordinate;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private static final int MINIMUM_GRID_WIDTH = 5;
	private Graph graph;
	private int gridWidthInPixels;
	private PixelCoordinate centerOfCoordinateSystem;
	private Function function;
	private boolean drawWithDerivative;

	public GraphPanel() {
		setGridWidthInPixels(100);
		drawWithDerivative = false;
		createPanel();
	}

	/*
	* Drawing methods
	* */

	private void createPanel() {
		repaint();
		revalidate();
	}

	public void paint(Graphics g) {
		createBackground(g);
		createAxesAndGrid(g);
		drawFunction(g);
	}

	private void createBackground(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	private void createAxesAndGrid(Graphics g) {
		if(this.centerOfCoordinateSystem == null) {
			determineInitialCenterOfCoordinateSystem();
		}
		createGrid(g);
		createAxes(g);
	}

	private void createGrid(Graphics g) {
		drawxAxisMarks(g);
		drawyAxisMarks(g);
	}

	private void createAxes(Graphics g) {
		drawxAxis(g);
		drawyAxis(g);
	}

	private void drawxAxis(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(0, getCenterOfCoordinateSystem().getY(), this.getWidth(), getCenterOfCoordinateSystem().getY());
	}

	private void drawxAxisMarks(Graphics g) {
		int upperBorder = 0;
		int lowerBorder = this.getHeight();

		g.setColor(Color.LIGHT_GRAY);
		for (int i = -getCenterOfCoordinateSystem().getX()
				/ getGridWidthInPixels(); i < getCenterOfCoordinateSystem().getX() / getGridWidthInPixels() + 1; i++) {
			if (i != 0) {
				g.drawLine(getCenterOfCoordinateSystem().getX() - getGridWidthInPixels() * i, upperBorder,
						getCenterOfCoordinateSystem().getX() - getGridWidthInPixels() * i, lowerBorder);
				g.drawString("" + i, getCenterOfCoordinateSystem().getX() + getGridWidthInPixels() * i,
						getCenterOfCoordinateSystem().getY() );
			}
		}
	}

	private void drawyAxis(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getCenterOfCoordinateSystem().getX(), 0, getCenterOfCoordinateSystem().getX(), this.getHeight());
	}

	private void drawyAxisMarks(Graphics g) {
		int leftBorder = 0;
		int rightBorder = this.getWidth();

		g.setColor(Color.LIGHT_GRAY);
		for (int i = -getCenterOfCoordinateSystem().getY()
				/ getGridWidthInPixels(); i < getCenterOfCoordinateSystem().getY() / getGridWidthInPixels() + 1; i++) {
			if (i != 0) {
				g.drawLine(leftBorder, getCenterOfCoordinateSystem().getY() - getGridWidthInPixels() * i, rightBorder,
						getCenterOfCoordinateSystem().getY() - getGridWidthInPixels() * i);
				g.drawString("" + i, getCenterOfCoordinateSystem().getX(),
						getCenterOfCoordinateSystem().getY() - getGridWidthInPixels() * i);
			}
		}
	}

	private void drawFunction(Graphics g) throws ArithmeticException {
		ValueCoordinate formerValue;
		PixelCoordinate pixelFormerValue;
		ValueCoordinate formerDerivativeValue;
		PixelCoordinate pixelFormerDerivativeValue;
		ValueCoordinate currentValue;
		PixelCoordinate pixelCurrentValue;
		ValueCoordinate currentDerivativeValue;
		PixelCoordinate pixelCurrentDerivativeValue;

		if(function != null) {
			if(graph == null) {
				graph = new Graph(function, getValueOfPixelMostLeft(), getValueOfPixelMostRight());
			} else {
				graph.setFunction(function);
				graph.updateValueTable(getValueOfPixelMostLeft(), getValueOfPixelMostRight());
			}
			ValueTable valueTable = graph.getValueTable();

			formerValue = valueTable.getValueTable().get(0);
			pixelFormerValue = convertValueToPixelCoordinate(formerValue);
			formerDerivativeValue = valueTable.getDerivativeValueTable().get(0);
			pixelFormerDerivativeValue = convertValueToPixelCoordinate(formerDerivativeValue);
			for (int i = 1; i < valueTable.getSize(); i++) {
				currentValue = valueTable.getValueTable().get(i);
				pixelCurrentValue = convertValueToPixelCoordinate(currentValue);
				currentDerivativeValue = valueTable.getDerivativeValueTable().get(i);
				pixelCurrentDerivativeValue = convertValueToPixelCoordinate(currentDerivativeValue);
				if (drawWithDerivative) {
					g.setColor(Color.cyan);
					g.drawLine(pixelFormerDerivativeValue.getX(), pixelFormerDerivativeValue.getY(), pixelCurrentDerivativeValue.getX(), pixelCurrentDerivativeValue.getY());
				}
				g.setColor(Color.blue);
				g.drawLine(pixelFormerValue.getX(), pixelFormerValue.getY(), pixelCurrentValue.getX(), pixelCurrentValue.getY());

				pixelFormerValue = pixelCurrentValue;
				pixelFormerDerivativeValue = pixelCurrentDerivativeValue;
			}
			createPanel();
		}
	}

	/*
	 * Helper
	 * */

	private void determineInitialCenterOfCoordinateSystem() {
		int halfOfxAxis = this.getWidth() / 2;
		int halfOfyAxis = this.getHeight() / 2;
		setCenterOfCoordinateSystem(new PixelCoordinate(halfOfxAxis, halfOfyAxis));
	}

	public PixelCoordinate convertValueToPixelCoordinate(ValueCoordinate valueCoordinate) {
		Integer x = getCenterOfCoordinateSystem().getX() + (int) (valueCoordinate.getX()*getGridWidthInPixels());
		Integer y = getCenterOfCoordinateSystem().getY() - (int) (valueCoordinate.getY()*getGridWidthInPixels());
		return new PixelCoordinate(x, y);
	}

	public ValueCoordinate getValueOfPixelMostLeft() {
		Double x = -(double)getCenterOfCoordinateSystem().getX() / getGridWidthInPixels();
		Double y = -(double)getCenterOfCoordinateSystem().getY() / getGridWidthInPixels();
		return new ValueCoordinate(x, y);
	}

	public ValueCoordinate getValueOfPixelMostRight() {
		Double x = (double)(getWidth() - getCenterOfCoordinateSystem().getX()) / getGridWidthInPixels();
		Double y = (double)(getHeight() - getCenterOfCoordinateSystem().getY()) / getGridWidthInPixels();
		return new ValueCoordinate(x, y);
	}

	/*
	 * Getter and Setter
	 * */

	public int getGridWidthInPixels() {
		return gridWidthInPixels;
	}

	public void setGridWidthInPixels(int gridWidthInPixels) {
		if(gridWidthInPixels >= MINIMUM_GRID_WIDTH) {
			this.gridWidthInPixels = gridWidthInPixels;
		} else{
			throw new IllegalArgumentException("GridWidth has to be greater than "+MINIMUM_GRID_WIDTH+": " + gridWidthInPixels);
		}
		createPanel();
	}

	public void setResolutionOfxValues(double resolutionOfxValues) {
		graph.setResolutionOfxValues(resolutionOfxValues);
	}

	public void setResolutionOfxValues(ResolutionOfxValues resolutionOfxValues) {
		graph.setResolutionOfxValues(resolutionOfxValues, gridWidthInPixels);
	}

	public double getResolutionOfxValues() {
		return graph.getResolutionOfxValues();
	}

	public PixelCoordinate getCenterOfCoordinateSystem() {
		return centerOfCoordinateSystem;
	}

	public void setCenterOfCoordinateSystem(PixelCoordinate centerOfCoordinateSystem) {
		this.centerOfCoordinateSystem = centerOfCoordinateSystem;
		createPanel();
	}

	public void setFunction(Function function) {
		this.function = function;
		createPanel();
	}

	public void setDrawWithDerivative(boolean drawWithDerivative) {
		this.drawWithDerivative = drawWithDerivative;
		createPanel();
	}
}
