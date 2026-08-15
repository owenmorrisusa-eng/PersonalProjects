package gameOfLife;

import java.util.ArrayList;

public class Game {

	public ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

	public boolean elavaluateCell(Cell cell) {
		
		//System.out.println("evaluating: "+ " X "+cell.getXCoord()+ " Y "+cell.getYCoord());
		int aliveCount = 0;
		for (CellCoord coord : cell.getNeighborCoords()) {
			int coordX = coord.getX();
			int coordY = coord.getY();

			if (coordX >= 0 && coordY >= 0 && coordX < cells.size() && coordY < cells.get(coordX).size()) {
				Cell neighborCell = cells.get(coordX).get(coordY);
				
				//System.out.println("X: "+neighborCell.getXCoord()+" Y: "+neighborCell.getYCoord()+" Is alive: "+ neighborCell.getAlive());
				if (neighborCell.getAlive()) {
					aliveCount++;
				}

			}
		}

		
		//System.out.println(aliveCount);
		if (cell.getAlive() && (aliveCount == 1 || aliveCount == 0)) {
			return true;
		} else if (cell.getAlive() && aliveCount >= 4) {
			return true;
		} else if ((!cell.getAlive()) && aliveCount == 3) {
			return true;
		}
		return false;

	}
	
//	public Game copyGameState() {
//		Game newGame = new Game();
//		for (ArrayList<Cell> row : this.cells) {
//			for(Cell cell : row) {
//				newGame.cells.add(cell);
//			}
//		}
//	}
}
