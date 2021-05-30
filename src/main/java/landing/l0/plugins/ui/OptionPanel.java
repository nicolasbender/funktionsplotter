package landing.l0.plugins.ui;

import landing.l1.adapters.ResolutionOfxValues;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class OptionPanel extends JPanel {
    private static final String LABEL_FOR_OPTIONPANEL = "Options";
    private static final String LABEL_FOR_OPTION_DERIVATIVE = "Show derivative";
    private static final String LABEL_FOR_OPTION_RESOLUTION = "Change resolution of x-values";
    private static final String LABEL_FOR_OPTION_RESOLUTION_INPUT = "Customized resolution:";
    private static final String RESOLUTION_BUTTON_TEXT = "Set resolution";
    private JPanel derivativePanel;
    private JPanel resolutionPanel;
    private JCheckBox derivativeCheckBox;
    private JComboBox resolutionDropdown;
    private JTextField resolutionInputField;
    private JButton resolutionButton;

    public OptionPanel() {
        createPanel();
    }

    private void createPanel() {
        createLayout();
        createLabelForOptionPanel();
        createOptionForDerivative();
        createOptionForXresolution();
    }

    private void createLayout() {
        this.setLayout(new GridLayout(4,1));
        derivativePanel = new JPanel();
        resolutionPanel = new JPanel();
        this.add(derivativePanel);
        this.add(resolutionPanel);
    }

    private void createLabelForOptionPanel() {
        this.setBorder(BorderFactory.createTitledBorder(LABEL_FOR_OPTIONPANEL));
    }

    private void createOptionForDerivative() {
        derivativeCheckBox = new JCheckBox();
        derivativeCheckBox.setText(LABEL_FOR_OPTION_DERIVATIVE);
        derivativePanel.add(derivativeCheckBox);
    }

    private void createOptionForXresolution() {
        resolutionPanel.setLayout(new GridLayout(4,1));
        resolutionPanel.add(new JLabel(LABEL_FOR_OPTION_RESOLUTION));

        Object[] optionsForResolution = Arrays.stream(ResolutionOfxValues.values()).toArray();
        resolutionDropdown = new JComboBox(optionsForResolution);
        JPanel comboboxPanel = new JPanel();
        comboboxPanel.add(resolutionDropdown);
        resolutionPanel.add(comboboxPanel);

        resolutionPanel.add(new JLabel(LABEL_FOR_OPTION_RESOLUTION_INPUT));
        resolutionInputField = new JTextField();
        resolutionButton = new JButton(RESOLUTION_BUTTON_TEXT);
        resolutionInputField.setColumns(5);
        JPanel inputPanel = new JPanel();
        inputPanel.add(resolutionInputField);
        inputPanel.add(resolutionButton);
        resolutionPanel.add(inputPanel);
    }


    public JCheckBox getDerivativeCheckBox() {
        return derivativeCheckBox;
    }

    public JComboBox getResolutionDropdown() {
        return resolutionDropdown;
    }

    public JTextField getResolutionInputField() {
        return resolutionInputField;
    }

    public JButton getResolutionButton() {
        return resolutionButton;
    }
}
