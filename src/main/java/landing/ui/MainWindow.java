package landing.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	private String title;
	private InputPanel inputPanel;
	private GraphPanel graphPanel;
	private static final Dimension MINIMUM_SIZE = new Dimension(800,600);
	
	public MainWindow(){
		new MainWindow("Graphical Interface for Mathematical Functions");
	}
	
	public MainWindow(String title) {
		this.title = title;
		initUI();
	}
	
	public InputPanel getInputPanel() {
		return inputPanel;
	}

	public GraphPanel getGraphPanel() {
		return graphPanel;
	}
	
	private void initUI() {
		createPanels();
		createLayout();
		createWindow();
	}

	private void createWindow() {
		this.setTitle(title);
		this.setVisible(true);
		this.setMinimumSize(MINIMUM_SIZE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createPanels() {
		this.inputPanel = new InputPanel();
		this.graphPanel = new GraphPanel();
	}
	
	private void createLayout() {
		this.setLayout(new BorderLayout());
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(graphPanel, BorderLayout.CENTER);
	}
}
