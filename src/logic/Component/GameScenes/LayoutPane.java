package logic.Component.GameScenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import object.*;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import logic.GameController;

public class LayoutPane extends GridPane{
	private ArrayList<ArrayList<EachPane>> map;
	private ArrayList<ArrayList<Integer>> mapint;
	private int mapNum;
	private final int mapwidth = 17;
	private final int mapHeight = 9;

	// don't forget to initailize mapsize
	//public LayoutPane(ArrayList<ArrayList<EachPane>> map) {
		//this.map = map;
		//initializePane();
	//}
	public LayoutPane(ArrayList<ArrayList<Integer>> mapint, int mapNum) {
		this.mapint = mapint;
		this.mapNum = mapNum;
		this.setPrefSize(mapwidth*50, mapHeight*50);
		map = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
		    List<EachPane> row = new ArrayList<>();
		    for (int j = 0; j < 17; j++) {
		        row.add(new EachPane(new Floor(i, j), i, j, mapNum)); // or whatever constructor is appropriate
		    }
		    map.add((ArrayList<EachPane>) row);
		}
		initializePane();
	}

	private void initializePane() {
		// TODO Auto-generated method stub
		for(int j = 0; j < mapint.size(); j++) {
			for(int i = 0; i < mapint.get(j).size() ; i++) {
				switch (mapint.get(j).get(i)) {
				case 0 -> {
					map.get(j).set(i, new EachPane(new Floor(i, j), i, j, mapNum));
					this.add(map.get(j).get(i), i, j);
				}
				case 1 -> {
					map.get(j).set(i, new EachPane(new Wall(i, j), i, j, mapNum));
					this.add(map.get(j).get(i), i, j);
				}
				case 2 -> {
					map.get(j).set(i, new EachPane(new NormalObject(i, j), i, j, mapNum));
					this.add(map.get(j).get(i), i, j);
				}
				case 3 -> {
					map.get(j).set(i, new EachPane(new ItemObject(i, j), i, j, mapNum));
					this.add(map.get(j).get(i), i, j);
				}
				case 4 -> {
					map.get(j).set(i, new EachPane(new BuffObject(i, j), i, j, mapNum));
					this.add(map.get(j).get(i), i, j);
				}
				}
			}
		}
		//for(ArrayList<EachPane> row : map) {
			//for(EachPane eachpane : row) {
				//this.add(eachpane, eachpane.getxPosition(), eachpane.getyPosition());
			//}
		//}
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
