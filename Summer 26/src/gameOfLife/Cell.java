package gameOfLife;

import java.util.ArrayList;

import javax.swing.JButton;

public class Cell extends JButton{
	private boolean isAlive;

	private ArrayList<CellCoord> neighborCoords;

	public CellCoord coord;

	public Cell(int x, int y) {
		this.coord = new CellCoord(x, y);
	}

	public boolean getAlive() {
		return this.isAlive;
	}

	public void toggleAlive() {
		this.isAlive = !(this.isAlive);
	}

	public ArrayList<CellCoord> getNeighborCoords() {
		return this.neighborCoords;
	}

	public void addNeighborCoords() {
		int cellX = coord.getX();
		int cellY = coord.getY();

		neighborCoords.add(new CellCoord(cellX - 1, cellY - 1));
		neighborCoords.add(new CellCoord(cellX - 1, cellY));
		neighborCoords.add(new CellCoord(cellX - 1, cellY + 1));
		neighborCoords.add(new CellCoord(cellX, cellY - 1));
		neighborCoords.add(new CellCoord(cellX + 1, cellY + 1));
		neighborCoords.add(new CellCoord(cellX + 1, cellY));
		neighborCoords.add(new CellCoord(cellX + 1, cellY - 1));
		neighborCoords.add(new CellCoord(cellX, cellY - 1));

	}
}
