/**
 * MatrixPanel.java
 *
 * This panel contains the squares which will be
 * manipulated by each algorithm.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({ "unused", "serial" })
public class MatrixPanel extends JPanel
{
	private JButton[][] nodes;				// the squares
	private int[][] values;					// the value of each square
	private final Font defaultFont;				// the default button font
	private final Color defaultColor;			// the default button color
        public static final int SIZE = 6;			// the size of the SIZE X SIZE matrix
        private static final int MAX_RANDOM_NUMBER = 10;	// the maximum random number

	public MatrixPanel() {
		setLayout(new GridLayout(SIZE, SIZE));

		nodes = new JButton[SIZE][SIZE];
		values = new int[SIZE][SIZE];

		// initialize the nodes with a random set of integers 0 .. MAX_RANDOM_NUMBER
		for (int i = 0; i < SIZE; i++) 
			for (int j = 0; j < SIZE; j++) {
				int num = (int)(Math.random() * MAX_RANDOM_NUMBER);
				nodes[i][j] = new JButton(new Integer(num).toString());
				values[i][j] = num;
				add(nodes[i][j]);
			}

		// get the default font for the buttons 
		defaultFont = nodes[0][0].getFont();

		// set the default color
		defaultColor = Color.black;
	}

	/**
	 * set the color at node[row][column]
	 *
	 * @param row the row.
	 * @param column the column.
	 * @param color the color to set node[row][]column] to.
	 */
	public void setValues(int row, int column, Color color) {
		nodes[row][column].setForeground(color);
		nodes[row][column].setFont(new Font("SansSerif", Font.BOLD, 24));
		nodes[row][column].repaint();
	}

	/**
	 * reset the values of the buttons to
	 * original colors and new random values.
	 */	
	public void resetValues() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++) {
				int num = (int)(Math.random() * MAX_RANDOM_NUMBER);
				nodes[i][j].setFont(defaultFont);
				nodes[i][j].setForeground(defaultColor);
				nodes[i][j].setText(new Integer(num).toString());
				values[i][j] = num;
			}
	}

	/**
	 * Returns the value of node[row][column]
	 */
	public int valueOf(int row, int column) {
		return values[row][column];
	}
}
