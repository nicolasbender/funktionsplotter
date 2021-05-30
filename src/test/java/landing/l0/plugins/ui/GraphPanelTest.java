package landing.l0.plugins.ui;

import landing.l1.adapters.GraphPanelAdapter;
import landing.l3.domainCode.representation.PixelCoordinate;
import landing.l3.domainCode.representation.ValueCoordinate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class GraphPanelTest {
    @Mock
    GraphPanel graphPanel;

    @Before
    public void initMock() {
        when(graphPanel.getGridWidthInPixels()).thenReturn(50);
        when(graphPanel.getCenterOfCoordinateSystem()).thenReturn(new PixelCoordinate(400, 300));
        when(graphPanel.getHeight()).thenReturn(1000);
        when(graphPanel.getWidth()).thenReturn(1000);
    }

    @Test
    public void testConvertValueToPixel() {
        GraphPanelAdapter graphPanelAdapter = new GraphPanelAdapter(graphPanel);
        PixelCoordinate pixelCoordinateCenter = graphPanelAdapter.convertValueToPixelCoordinate(new ValueCoordinate(0.0,0.0));
        assertEquals(pixelCoordinateCenter, new PixelCoordinate(400, 300));
        PixelCoordinate pixelCoordinateUpperLeft = graphPanelAdapter.convertValueToPixelCoordinate(new ValueCoordinate(-2.5,4.0));
        assertEquals(pixelCoordinateUpperLeft, new PixelCoordinate(275, 100));
        PixelCoordinate pixelCoordinateLowerRight = graphPanelAdapter.convertValueToPixelCoordinate(new ValueCoordinate(3.2,-1.378));
        assertEquals(pixelCoordinateLowerRight, new PixelCoordinate(560, 368));
    }

    @Test
    public void testGetValueOfPixelMostLeft() {
        GraphPanelAdapter graphPanelAdapter = new GraphPanelAdapter(graphPanel);
        ValueCoordinate mostLeft = graphPanelAdapter.getValueOfPixelMostLeft();
        assertEquals(mostLeft, new ValueCoordinate(-8.0, -6.0));
    }

    @Test
    public void testGetValueOfPixelMostRight() {
        GraphPanelAdapter graphPanelAdapter = new GraphPanelAdapter(graphPanel);
        ValueCoordinate mostRight = graphPanelAdapter.getValueOfPixelMostRight();
        assertEquals(mostRight, new ValueCoordinate(12.0, 14.0));
    }
}

