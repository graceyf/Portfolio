/**
 * Lab 11
 * The dynamic programming solution to the matrix problem.
 * 
 * @author Grace Zhu and Eli Kerr
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Dynamic implements ActionListener
{
	private MatrixPanel elements;
	private ControlPanel controls;
	private int[][] dynamicValue;
	private int[][] predecessors;

	public Dynamic(MatrixPanel elements, ControlPanel controls) {
		this.elements = elements;
		this.controls = controls;
		dynamicValue = new int[MatrixPanel.SIZE][MatrixPanel.SIZE];
		predecessors = new int[MatrixPanel.SIZE][MatrixPanel.SIZE];
	}

	
	/**
	 * This will only be called when the "Dynamic"
	 * button is clicked.
	 * This is an implementation of the dynamic programming algorithm
	 * for the matrix problem. 
	 */
	public void actionPerformed(ActionEvent evt) {
		
		// the cost of this algorithm.
		int totalCost = 0;

		for (int i = 0; i < MatrixPanel.SIZE; i++) {
			for (int j = 0; j < MatrixPanel.SIZE; j++) {

				if (i == 0) 
					// if it's the first column, DV is itself's value
					dynamicValue[j][i] = elements.valueOf(j, i);
				
				else { 
					// if it's not the first column
					int min = -1;

					if (j == 0) {
						// if it's the first row, get the smaller of the left one and the left next one, then store the position in predecessors
						min = Math.min(dynamicValue[j][i - 1], dynamicValue[j + 1][i - 1]);

						if (min == dynamicValue[j][i - 1])
							predecessors[j][i] = j;
						else
							predecessors[j][i] = j + 1;
					}

					else if (j == MatrixPanel.SIZE - 1) {
						// if it's the last row, get the smaller of the left one and the left previous one, then store the position in predecessors
						min = Math.min(dynamicValue[j][i - 1], dynamicValue[j - 1][i - 1]);

						if (min == dynamicValue[j][i - 1])
							predecessors[j][i] = j;
						else
							predecessors[j][i] = j - 1;
					}

					else {
						// Otherwise, get the smaller of the left one, left previous one and the left next one, then store the position in predecessors
						min = Math.min(dynamicValue[j][i - 1], dynamicValue[j + 1][i - 1]);
						min = Math.min(min, dynamicValue[j - 1][i - 1]);

						if (min == dynamicValue[j][i - 1])
							predecessors[j][i] = j;
						else if (min == dynamicValue[j - 1][i - 1])
							predecessors[j][i] = j - 1;
						else
							predecessors[j][i] = j + 1;
					}
					
					// update the DV
					dynamicValue[j][i] = min + elements.valueOf(j, i);

				}
			}
		}
		
		// start with the last column, find the shortest route cost and color it to red
		int lowestINDEX = 0;
		for (int j = 0; j < MatrixPanel.SIZE; j++) {
			if (dynamicValue[j][MatrixPanel.SIZE - 1] < dynamicValue[lowestINDEX][MatrixPanel.SIZE - 1])
				lowestINDEX = j;
		}
		elements.setValues(lowestINDEX, MatrixPanel.SIZE - 1, Color.red);
		totalCost = dynamicValue[lowestINDEX][MatrixPanel.SIZE - 1];
		
		// then trace back to the previous columns using the predecessor table
		for (int i = MatrixPanel.SIZE - 1; i > 0; i--) {
			elements.setValues(predecessors[lowestINDEX][i], i - 1, Color.red);
			lowestINDEX = predecessors[lowestINDEX][i];
		}

		/**
		 * We are done. Now update the output for total cost.
		 */
		controls.setTotal(totalCost);
	}
}
