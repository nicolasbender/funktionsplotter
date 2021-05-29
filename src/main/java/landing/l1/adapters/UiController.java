package landing.l1.adapters;

import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SymbolException;
import landing.l3.domainCode.exceptions.SyntaxException;
import landing.l2.applicationCode.function.Function;
import landing.l0.plugins.ui.GraphPanel;
import landing.l0.plugins.ui.InputPanel;
import landing.l0.plugins.ui.MainWindow;

import javax.swing.*;

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
