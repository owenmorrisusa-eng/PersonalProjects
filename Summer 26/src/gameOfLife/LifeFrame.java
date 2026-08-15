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

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel lifeButtonsPanel = new JPanel();
		lifeButtonsPanel.setLayout(new GridLayout(10, 10));

		for (int i = 0; i < 10; i++) {
			ArrayList<Cell> setOfCells = new ArrayList<>();
			game.cells.add(setOfCells);
			for (int j = 0; j < 10; j++) {
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

			clickedCell.toggleAlive();
		}

		if (event.getSource().equals(startButton)) {

			exe.submit(() -> {
				while (!Thread.currentThread().isInterrupted()) {
					ArrayList<Cell> toggleCells = new ArrayList<Cell>();
					for (ArrayList<Cell> row : game.cells) {
						for (Cell cell : row) {
							if (game.elavaluateCell(cell)) {
								toggleCells.add(cell);
							}
						}
					}

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (Cell cell : toggleCells) {
						cell.toggleAlive();
					}
				}

			});
		}
	}
}
