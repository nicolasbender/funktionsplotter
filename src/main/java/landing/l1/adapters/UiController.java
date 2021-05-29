package landing.l1.adapters;

import landing.l0.plugins.ui.OptionPanel;
import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SymbolException;
import landing.l3.domainCode.exceptions.SyntaxException;
import landing.l2.applicationCode.function.Function;
import landing.l0.plugins.ui.GraphPanel;
import landing.l0.plugins.ui.InputPanel;
import landing.l0.plugins.ui.MainWindow;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;

public class UiController {
    private final MainWindow mainWindow;
    private final GraphPanel graphPanel;
    private final InputPanel inputPanel;
    private final JTextField inputField;
    private final JButton calculateButton;
    private final JTextField resolutionInputField;
    private final JComboBox resolutionDropdown;
    private final JButton resolutionButton;
    private final JCheckBox derivativeCheckBox;

    public UiController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.graphPanel = mainWindow.getGraphPanel();
        this.inputPanel = mainWindow.getInputPanel();
        OptionPanel optionPanel = mainWindow.getOptionPanel();
        this.derivativeCheckBox = optionPanel.getDerivativeCheckBox();
        this.resolutionInputField = optionPanel.getResolutionInputField();
        this.resolutionDropdown = optionPanel.getResolutionDropdown();
        this.resolutionButton = optionPanel.getResolutionButton();
        this.inputField = inputPanel.getInputField();
        this.calculateButton = inputPanel.getCalculateButton();
        bindActionListener();
    }

    private void bindActionListener() {
        inputField.addActionListener(e -> runCalculation());
        calculateButton.addActionListener(e -> runCalculation());
        graphPanel.addMouseWheelListener(this::runZoomInPanel);
        derivativeCheckBox.addActionListener(e -> runDerivativeAction());
        resolutionDropdown.addActionListener(e -> runResolutionDropdown());
        resolutionButton.addActionListener(e -> runResolutionButton());
    }

    private void runCalculation() {
        try {
            Function function = new Function(inputPanel.getInputTerm());
            graphPanel.setFunction(function);
        } catch (ParenthesisException e) {
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Parenthesis Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SymbolException e) {
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Symbol Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SyntaxException e) {
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Syntax Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(mainWindow,
                    e.getMessage(),
                    "Arithmetic Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void runDerivativeAction() {
        graphPanel.setDrawWithDerivative(derivativeCheckBox.isSelected());
    }

    private void runZoomInPanel(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0) {
            graphPanel.setGridWidthInPixels(graphPanel.getGridWidthInPixels() + e.getScrollAmount());
        } else {
            graphPanel.setGridWidthInPixels(graphPanel.getGridWidthInPixels() - e.getScrollAmount());
        }
    }

    private void runResolutionDropdown() {
        ResolutionOfxValues resolutionOfxValues = (ResolutionOfxValues) resolutionDropdown.getSelectedItem();
        try {
            graphPanel.setResolutionOfxValues(resolutionOfxValues);
            String resolutionAsString = String.valueOf(graphPanel.getResolutionOfxValues());
            if(resolutionAsString.length() > 5) {
                resolutionAsString = resolutionAsString.substring(0, 5);
            }
            resolutionInputField.setText(resolutionAsString);
        } catch (NullPointerException npe) {
            String message = "Please input a function first";
            JOptionPane.showMessageDialog(mainWindow,
                    message,
                    "Function not found",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void runResolutionButton() {
        try {
            graphPanel.setResolutionOfxValues(Double.parseDouble(resolutionInputField.getText()));
        } catch (NumberFormatException nfe) {
            String message = "Please choose a float value between 0 and 10";
            JOptionPane.showMessageDialog(mainWindow,
                    message,
                    "Not a valid value",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
