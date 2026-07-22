package gameOfLife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LifeFrame extends JFrame implements ActionListener {

	private Game game = new Game();
	private JButton startButton = new JButton();
	private ExecutorService exe = Executors.newSingleThreadExecutor();

	public LifeFrame() {

		System.out.println("in frame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel lifeButtonsPanel = new JPanel();
		lifeButtonsPanel.setLayout(new GridLayout(5, 5));

		for (int i = 0; i < 5; i++) {
			ArrayList<Cell> setOfCells = new ArrayList<>();
			game.cells.add(setOfCells);
			for (int j = 0; j < 5; j++) {
				Cell button = new Cell(i, j);
				setOfCells.add(button);
				lifeButtonsPanel.add(button);
				button.addActionListener(this);
				button.addNeighborCoords();
			}
		}

		JPanel operationButtonsPanel = new JPanel();
		operationButtonsPanel.setLayout(new FlowLayout());
		startButton.setText("Start");
		operationButtonsPanel.add(startButton);
		startButton.addActionListener(this);

		JPanel wholePanel = new JPanel();
		wholePanel.setLayout(new BorderLayout());
		wholePanel.add(operationButtonsPanel, BorderLayout.SOUTH);
		wholePanel.add(lifeButtonsPanel, BorderLayout.CENTER);

		this.setContentPane(wholePanel);
		this.setPreferredSize(new Dimension(600, 600));
		this.pack();

	}

	/**
	 * Performs and calls all relevant operations when a button is clicked, or
	 * condition has been fulfilled.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof Cell) {
			Cell clickedCell = (Cell) event.getSource();
			
			System.out.println("click cell");
			clickedCell.toggleAlive();
		}

		if (event.getSource().equals(startButton)) {
			
			exe.submit(() ->{
				while(!Thread.currentThread().isInterrupted()) {
					for(ArrayList<Cell> row : game.cells){
						for(Cell cell : row) {
							game.elavaluateCell(cell);
						}
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
