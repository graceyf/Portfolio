/**
 * ControlPanel.java
 *
 * This panel contains the buttons for making a
 * choice of either the (1) greedy or (2) dynamic
 * algorithms.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings({ "unused", "serial" })
public class ControlPanel extends JPanel implements ActionListener
{
	private JTextField totalField;	// the total cost generated by the algorithm
	private JButton greedyButton;	// the greedy button
	private JButton dynamicButton;	// the dynamic button
	private JButton resetButton;	// the reset button
	private MatrixPanel matrix;	// the panel containing the squares

	/**
	 * This panel contains the buttons for the greedy and
	 * dynamic algorithms as well as the total field.
	 *
	 * @param matrix The panel for the squares.
	 */
	public ControlPanel(MatrixPanel matrix) {
		this.matrix = matrix;
		
		add(greedyButton = new JButton("Greedy"));
		greedyButton.addActionListener(new Greedy(matrix,this));

		add(dynamicButton = new JButton("Dynamic"));
		dynamicButton.addActionListener(new Dynamic(matrix,this));

		add(resetButton = new JButton("Reset"));
		resetButton.addActionListener(this);

		add(new JLabel("Total"));
		add(totalField = new JTextField(5));

		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Choices");
		setBorder(titled);
	}

	/**
	 * The ActionListener for the "Reset" button.
	 * Restores the default colors and generates new random values.
	 */
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource()  == resetButton) {
			totalField.setText("0");
			matrix.resetValues();
		}
	}	

	/**
	 * Sets the total field based upon the chosen algorithm.
	 *
	 * @param total the value to set the total field to.
	 */
	public void setTotal(int total) {
		totalField.setText(new Integer(total).toString());
	}
}