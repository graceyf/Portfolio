/**
 * Greedy.java
 * 
 * This is the greedy solution to the matrix problem.
 * Note this solution will not always produce the optimal solution
 * to the matrix problem.
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Greedy implements ActionListener
{
	private MatrixPanel elements;	// the panel for the squares
	private ControlPanel controls;	// the panel for the control buttons
        
	/**
	 * The greedy solution to the matrix problem.
	 *
	 * @param elements the GUI for the squares.
	 * @param controls the GUI for the control buttons.
	 */
	public Greedy(MatrixPanel elements, ControlPanel controls) {
		this.elements = elements;
		this.controls = controls;
	}

	/**
	 * Determine the mininum value between squares 'begin' and 'end'
	 * for the specified 'column'.
	 * 
	 * @param begin the index where to begin searching.
	 * @param end the index where to end searching.
	 * @param column the column to be searched.
	 *
	 * @return the index of the minimum value for the specified column
	 */
	public int minimum(int begin, int end, int column) {
		if (elements.valueOf(begin,column) < elements.valueOf(end,column))
			return begin;
		else
			return end;
	}

	/**
	 * Determine the index of the mininum value between squares 'index' and 
	 * 'index + 1' and 'index - 1' for the specified 'column'.
	 *
	 * @param index the index of the minimum value to be searched.
	 * @param column the column to be searched.
	 * 
	 * @return int the index of the minimum value for the specified column
	 */
	public int minimum(int index, int column) {
		int minimumIndex = index - 1;
		if (elements.valueOf(index,column) < elements.valueOf(minimumIndex,column))
			minimumIndex = index;
		if (elements.valueOf(index+1,column) < elements.valueOf(minimumIndex,column))
			minimumIndex = index + 1;

		return minimumIndex;
	}
		
		

	/**
	 * This will only be called when the "Greedy"
	 * button is clicked.
         *
	 * This is an implementation of the greedy algorithm
	 * for the matrix problem. 
	 */
	public void actionPerformed(ActionEvent evt) {
		/**
		 * the cost of this algorithm.
		 */
		int totalCost = 0;

		/**
		 * index represents the index of the lowest
		 * valued square for a given column.
		 */
		int index = 0;

		/**
	 	 * determine the index of the smallest square
		 * at the leftmost column.
		 */
		int smallest = elements.valueOf(0,0);

		for (int i = 1; i < MatrixPanel.SIZE; i++) { 
			if (elements.valueOf(i,0) < smallest) {
				smallest = elements.valueOf(i,0);
				index = i;
			}
		}
	
		totalCost = smallest;	

		// highlight the square	
		elements.setValues(index, 0, Color.blue);

		// now work over remaining columns
		for (int i = 1; i < MatrixPanel.SIZE; i++) {
			if (index == 0) 	// we are at the top of the column
				index = minimum(index,index+1,i);
			else if (index == MatrixPanel.SIZE - 1)	// we are at the bottom of the column
				index = minimum(index,index-1,i);
			else			// we are between the top and bottom of the column
				index = minimum(index,i);
	
			// highlight the square	
			elements.setValues(index, i, Color.blue);

			// update the total cost
			totalCost += elements.valueOf(index,i);
		}

		/**
		 * We are done. Now update the output for total cost.
		 */
		controls.setTotal(totalCost);
	}
}
