package logic.Component;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import logic.GameController;

public class LayoutPane extends GridPane{
	private ArrayList<ArrayList<EachPane>> map;
	private final int mapwidth = 17;
	private final int mapHeight = 9;

	// don't forget to initailize mapsize
	public LayoutPane(ArrayList<ArrayList<EachPane>> map) {
		this.map = map;
		this.setPrefSize(mapwidth*50, mapHeight*50);
		initializePane();
	}

	private void initializePane() {
		// TODO Auto-generated method stub
		
		for(ArrayList<EachPane> row : map) {
			for(EachPane eachpane : row) {
				this.add(eachpane, eachpane.getxPosition(), eachpane.getyPosition());
			}
		}
	}
	
	public void drawPane(Image image) {
		
	}
	
	public EachPane GetEachPane(int xPosition, int yPosition) {
		return map.get(yPosition).get(xPosition);
	}
	
	public ArrayList<ArrayList<EachPane>> getmap() {
		return this.map;
	}

	public int getMapwidth() {
		return mapwidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}
}
