package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapReader {
	private String fileString;
	private ArrayList<ArrayList<Integer>> mapint;
	public MapReader(String fileString) {
		
		this.setFileString(fileString);
		mapint = createList();
		InputStream in = getClass().getResourceAsStream("/Map4.txt");
		Scanner scanner = new Scanner(in);
		int rownum = 0;
		while (scanner.hasNextLine()) {
		    String line = scanner.nextLine();
		    String[] row = line.split(" ");
		    
		    for(int col = 0; col < row.length; col ++) {
		        int tile = Integer.parseInt(row[col]); 
		        mapint.get(rownum).set(col, tile); 
		    }
		    
		    rownum += 1;
		}
		
		scanner.close();
	}
	
	public ArrayList<ArrayList<Integer>> getMapint() {
		return mapint;
	}

	public void setMapint(ArrayList<ArrayList<Integer>> mapint) {
		this.mapint = mapint;
	}

	public String getFileString() {
		return fileString;
	}

	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
	
	private ArrayList<ArrayList<Integer>> createList() {
		ArrayList<ArrayList<Integer>> newlist = new ArrayList<>();
		for(int i = 0; i<9; i++) {
			ArrayList<Integer> row = new ArrayList<>();
			for(int j = 0; j<17; j++) {
				row.add(0);
			}
			newlist.add(row);
		}
		return newlist;
	}
	
}
