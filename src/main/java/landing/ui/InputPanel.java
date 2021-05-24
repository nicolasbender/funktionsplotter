package landing.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {
	private static final String LABEL_FOR_INPUTFIELD = "f(x) = ";
	private static final String BUTTON_TEXT = "Calculate!";
	private static final int COLUMNS_IN_TEXTFIELD = 40;
	private JLabel labelForInputField;
	private JTextField inputField;
	private JButton calculateButton;
	
	public InputPanel() {
		createPanel();
	}
	
	private void createPanel() {
		createLabelForInputField();
		createInputField();
		createCalculateButton();
	}

	private void createLabelForInputField() {
		labelForInputField = new JLabel(LABEL_FOR_INPUTFIELD);
		this.add(labelForInputField);
	}

	private void createInputField() {
		inputField = new JTextField();
		inputField.setColumns(COLUMNS_IN_TEXTFIELD);
		this.add(inputField);
	}
	
	private void createCalculateButton() {
		calculateButton = new JButton(BUTTON_TEXT);
		this.add(calculateButton);
	}
	
	public String getInputTerm() {
		return inputField.getText();
	}

	public JButton getCalculateButton() {
		return calculateButton;
	}
}
