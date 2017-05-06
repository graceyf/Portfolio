/**
 * TheMatrix.java
 *
 * This program solves the matrix move problem.
 * It provides two different algorithms:
 * Greedy - which is not optimal
 * Dynamic - which is optimal
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({ "unused", "serial" })
public class TheMatrix extends JFrame
{
	public TheMatrix() {
		setSize(500,500);
		MatrixPanel matrixPanel = new MatrixPanel();
		ControlPanel controlPanel = new ControlPanel(matrixPanel);
		getContentPane().add(controlPanel, "South");
		getContentPane().add(matrixPanel, "Center");

		/**
		 * This allows the window to close 
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		} );
	}

	public static void main(String[] args) {
		JFrame frame = new TheMatrix();
		frame.setTitle("The Matrix");
		//frame.show();
		frame.setVisible(true);
	}
}	
