package gameOfLife;

import java.util.ArrayList;

public class Game {
	
	public ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

	public void elavaluateCell(Cell cell) {

		int aliveCount = 0;
		for (CellCoord coord : cell.getNeighborCoords()) {
			int coordX = coord.getX();
			int coordY = coord.getY();
			Cell neighborCell = cells.get(coordX).get(coordY);
			if (neighborCell.getAlive()) {
				aliveCount++;
			}
		}

		if (cell.getAlive() && (aliveCount == 1 || aliveCount == 0)) {
			cell.toggleAlive();
		} else if (cell.getAlive() && aliveCount >= 4) {
			cell.toggleAlive();
		} else if ((!cell.getAlive()) && aliveCount == 3) {
			cell.toggleAlive();
		}

	}
}
