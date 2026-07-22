package gameOfLife;

import java.util.ArrayList;

public class Game {

	public ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

	public void elavaluateCell(Cell cell) {
		
		System.out.println("in eval");
		int aliveCount = 0;
		for (CellCoord coord : cell.getNeighborCoords()) {
			int coordX = coord.getX();
			int coordY = coord.getY();

//			System.out.println(coordX);
//			System.out.println(coordY);
			if (coordX >= 0 && coordY >= 0 && coordX < cells.size() && coordY < cells.get(coordX).size()) {
//				System.out.println("In valid coord");
				Cell neighborCell = cells.get(coordX).get(coordY);
				
				System.out.println("X: "+neighborCell.getXCoord()+" Y: "+neighborCell.getYCoord()+" Is alive: "+ neighborCell.getAlive());
				if (neighborCell.getAlive()) {
					System.out.println("In alive");
					aliveCount++;
				}

			}
		}

//		System.out.println(aliveCount);
		if (cell.getAlive() && (aliveCount == 1 || aliveCount == 0)) {
			cell.toggleAlive();
		} else if (cell.getAlive() && aliveCount >= 4) {
			cell.toggleAlive();
		} else if ((!cell.getAlive()) && aliveCount == 3) {
			cell.toggleAlive();
		}

	}
}
