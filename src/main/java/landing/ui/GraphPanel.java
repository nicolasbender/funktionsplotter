package landing.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private final MainWindow mainWindow;
	private int gridWidthInPixels;
	private PixelCoordinate centerOfCoordinateSystem;

	public GraphPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setGridWidthInPixels(100);
		createPanel();
	}

	private void createPanel() {
		repaint();
		revalidate();
	}

	public void paint(Graphics g) {
		createBackground(g);
		createAxesAndGrid(g);
	}

	private void createBackground(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void createAxesAndGrid(Graphics g) {
		if(this.centerOfCoordinateSystem == null) {
			determineInitialCenterOfCoordinateSystem();
		}
		createGrid(g);
		createAxes(g);
	}

	private void determineInitialCenterOfCoordinateSystem() {
		int halfOfxAxis = this.getWidth() / 2;
		int halfOfyAxis = this.getHeight() / 2;
		setCenterOfCoordinateSystem(new PixelCoordinate(halfOfxAxis, halfOfyAxis));
	}

	private void createGrid(Graphics g) {
		drawxAxisMarks(g);
		drawyAxisMarks(g);
	}

	private void createAxes(Graphics g) {
		drawxAxis(g);
		drawyAxis(g);
	}

	public int getGridWidthInPixels() {
		return gridWidthInPixels;
	}

	public void setGridWidthInPixels(int gridWidthInPixels) {
		if(gridWidthInPixels > 0) {
			this.gridWidthInPixels = gridWidthInPixels;
		} else{
			throw new IllegalArgumentException("GridWidth has to be greater than 0: " + gridWidthInPixels);
		}
	}

	public PixelCoordinate getCenterOfCoordinateSystem() {
		return centerOfCoordinateSystem;
	}

	public void setCenterOfCoordinateSystem(PixelCoordinate centerOfCoordinateSystem) {
		this.centerOfCoordinateSystem = centerOfCoordinateSystem;
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
}
