package entities.worldObjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 *	This class creates tile maps by reading in text files and allotting tiles (32 x 32) numerical values.
 *  Different numerical values represent different types of world objects; the values what they represent are listed below:
 * 	
 */

public class TileMap {
	
	private int mapWidth, mapHeight;
	private Tile [][] map;
	
	public TileMap(String mapName, int scaling_Factor){
		
		try{
			File file = new File(mapName);
			BufferedReader br = new BufferedReader(new FileReader(mapName));
			
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new Tile[mapHeight][mapWidth];
			
			String delimeters = " ";
			for (int row = 0; row < mapHeight; row++){
				String line = br.readLine();
				String [] tokens = line.split(delimeters); 
				for(int col = 0; col < mapWidth; col++){
					 Tile tile = new Tile(scaling_Factor, Integer.parseInt(tokens[col]), col * 32, row * 32, row, col);
					 map[row][col] = tile;
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public Tile[][] getMap() {
		return map;
	}
	

}
