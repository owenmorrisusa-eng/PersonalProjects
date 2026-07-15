package gameOfLife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LifeFrame extends JFrame implements ActionListener {

	private Game game;
	private JButton startButton = new JButton();

	
	public void LifeFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel lifeButtonsPanel = new JPanel();
		lifeButtonsPanel.setLayout(new GridLayout(5, 5));
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				Cell button=new Cell(i, j);
				button = game.cells.get(i).set(j, button);
				lifeButtonsPanel.add(button);
				button.addActionListener(this);
			}	
		}
		
		JPanel operationButtonsPanel=new JPanel();
		operationButtonsPanel.setLayout(new FlowLayout());
		startButton.setText("Start");
		operationButtonsPanel.add(startButton);
		startButton.addActionListener(this);
		
		JPanel wholePanel=new JPanel();
		wholePanel.setLayout(new BorderLayout());
		wholePanel.add(operationButtonsPanel, BorderLayout.SOUTH);
		wholePanel.add(lifeButtonsPanel, BorderLayout.CENTER);
		
		this.setContentPane(wholePanel);
		this.setPreferredSize(new Dimension(600, 600));
		this.pack();
	
	}

	/**
	 * Returns the light status of a light button in the frame.
	 * 
	 * @param row
	 * @param column
	 * @return buttonArray[row][column].isOn()
	 * @throws IndexOutOfBoundsException
	 */
//	public boolean lightIsOn(int row, int column) throws IndexOutOfBoundsException {
//		if (row > 5 || row < 0 || column > 5 || column < 0) {
//			throw new IndexOutOfBoundsException("Index is incorrect");
//		}
//		return buttonArray[row][column].isOn();
//	}

	/**
	 * Toggles the light of a button along with its neighbor light buttons.
	 * 
	 * @param row
	 * @param column
	 * @throws IndexOutOfBoundsException
	 */
//	public void toggleLight(int row, int column) throws IndexOutOfBoundsException {
//		if (row > 5 || row < 0 || column > 5 || column < 0) {
//			throw new IndexOutOfBoundsException("Index is incorrect");
//		}
//		buttonArray[row][column].toggle();
//		if (row + 1 < 5) {
//			buttonArray[row + 1][column].toggle();
//		}
//
//		if (row - 1 >= 0) {
//			buttonArray[row - 1][column].toggle();
//		}
//
//		if (column + 1 < 5) {
//			buttonArray[row][column + 1].toggle();
//		}
//
//		if (column - 1 >= 0) {
//			buttonArray[row][column - 1].toggle();
//		}
//	}

	/**
	 * Performs and calls all relevant operations when a button is clicked, or
	 * condition has been fulfilled.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof Cell) {
			Cell clickedCell = (Cell) event.getSource();
			
			clickedCell.toggleAlive();
		}

		if (event.getSource().equals(startButton)) {
			this.game = new Game();
			while(true) {
				for(ArrayList<Cell> row : game.cells){
					for(Cell cell : row) {
						game.elavaluateCell(cell);
					}
				}
			}
		}
	}
}
