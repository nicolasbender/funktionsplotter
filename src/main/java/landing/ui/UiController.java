package landing.ui;

import landing.exceptions.ParenthesisException;
import landing.exceptions.SymbolException;
import landing.exceptions.SyntaxException;
import landing.function.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class UiController {
    private MainWindow mainWindow;
    private GraphPanel graphPanel;
    private InputPanel inputPanel;
    private JTextField inputField;
    private JButton calculateButton;
    private boolean drawWithDerivative = false;

    public UiController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.graphPanel = mainWindow.getGraphPanel();
        this.inputPanel = mainWindow.getInputPanel();
        this.inputField = inputPanel.getInputField();
        this.calculateButton = inputPanel.getCalculateButton();
        bindActionListener();
    }

    private void bindActionListener() {
        inputField.addActionListener(e -> runCalculation());
        calculateButton.addActionListener(e -> runCalculation());
        graphPanel.addMouseWheelListener(e -> {
            if(e.getWheelRotation() < 0) {
                graphPanel.setGridWidthInPixels(graphPanel.getGridWidthInPixels() + e.getScrollAmount());
            } else {
                graphPanel.setGridWidthInPixels(graphPanel.getGridWidthInPixels() - e.getScrollAmount());
            }
        });

    }

    public void runCalculation() {
        try {
            Function function = new Function(inputPanel.getInputTerm());
            graphPanel.setFunction(function);
        } catch (ParenthesisException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Parenthesis Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SymbolException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Symbol Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SyntaxException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Syntax Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Arithmetic Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
