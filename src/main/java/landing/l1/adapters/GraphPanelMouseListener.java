package landing.l1.adapters;

import landing.l0.plugins.ui.GraphPanel;
import landing.l3.domainCode.representation.PixelCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphPanelMouseListener implements MouseListener {
    private final JFrame mainWindow;
    private final GraphPanel graphPanel;
    private PixelCoordinate oldPosition;

    public GraphPanelMouseListener(JFrame mainWindow, GraphPanel graphPanel) {
        this.mainWindow = mainWindow;
        this.graphPanel = graphPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        oldPosition = new PixelCoordinate(e.getX(), e.getY());
        mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PixelCoordinate newPosition = new PixelCoordinate(e.getX(), e.getY());
        int differenceX = oldPosition.getX() - newPosition.getX();
        int differenceY = oldPosition.getY() - newPosition.getY();

        int newCenterX = graphPanel.getCenterOfCoordinateSystem().getX();
        int newCenterY = graphPanel.getCenterOfCoordinateSystem().getY();

        PixelCoordinate newCenter = new PixelCoordinate(newCenterX - differenceX, newCenterY - differenceY);
        graphPanel.setCenterOfCoordinateSystem(newCenter);
        mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
