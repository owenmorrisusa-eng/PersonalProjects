package gameOfLife;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Cell extends JButton{
	private boolean isAlive;
	
	private ImageIcon lightOn;
	
	private ImageIcon lightOff;

	private ArrayList<CellCoord> neighborCoords = new ArrayList<>();

	public CellCoord coord;

	public Cell(int x, int y) {
		this.coord = new CellCoord(x, y);
		this.lightOn=new ImageIcon("src/gameOfLife/light_on-4.jpg");
		this.lightOff=new ImageIcon("src/gameOfLife/light_off-4.jpg");
		this.setIcon(lightOff);
	}

	public boolean getAlive() {
		return this.isAlive;
	}

	public void toggleAlive() {
		System.out.println("toggle");
		if(this.isAlive==true) {
			this.isAlive=false;
			this.setIcon(lightOff);
		}else {
			this.isAlive=true;
			this.setIcon(lightOn);
		}
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
	
	public int getXCoord() {
		return coord.getX();
	}
	
	public int getYCoord() {
		return coord.getY();
	}
}
