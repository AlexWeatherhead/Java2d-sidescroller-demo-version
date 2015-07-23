package entities.worldObjects;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Tile {
	
	private int tile_X, tile_Y;						// x and y coordinate of world object
	private int tile_Dimension = 32;			// width and height of world object
	private int tile_V;								// value of the tile
	private int tile_R, tile_C;						// row and column of the tile
	
	public Tile(int scaling_Factor, int tile_V, int tile_X_Pos, int tile_Y_Pos, int tile_R, int tile_C){
		this.tile_V = tile_V;
		this.tile_X = tile_X_Pos * scaling_Factor;
		this.tile_Y = tile_Y_Pos * scaling_Factor;
		this.tile_Dimension = this.tile_Dimension * scaling_Factor;
		this.tile_R = tile_R;
		this.tile_C = tile_C;		
	}
	
	public Rectangle2D getRect() {
        return new Rectangle2D.Float(tile_X, tile_Y, tile_Dimension, tile_Dimension);
    }
	
	public int getTile_X() {
		return tile_X;
	}
	public int getTile_Y() {
		return tile_Y;
	}
	public int getTile_V() {
		return tile_V;
	}
	public int getTile_R(){
		return tile_R;
	}
	public int getTile_C(){
		return tile_C;
	}

}
