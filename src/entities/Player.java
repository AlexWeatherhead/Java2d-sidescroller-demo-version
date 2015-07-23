package entities;


import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import entities.worldObjects.Tile;
import entities.worldObjects.TileMap;

public class Player extends KeyAdapter {
	
	private int scaling_Factor;
	// Player Camera //
	private Camera camera;
	
	// Player Dimensions //
	private int player_Width_And_Height = 32;
	
	// Player Health //
	private double current_Player_Health;
	private final double MAXIMUM_PLAYER_HEALTH = 4.0;
	
	// Player Position //
	private float player_X, player_Y;															// Current x and y position of the player
	private Rectangle2D player_Rect = new Rectangle.Float(player_X, player_Y, 32, 32);		
	private Tile [][] map_Boundaries;															// Stores the tiles in the current tile map
	
	// Player State //
	private boolean on_Surface = true;	 	
	private boolean on_Ladder = false;
	private boolean is_Jumping = false;
	// is_Attacking
	// on_Slope,
	
	// Player Movement //
	private float player_X_Velocity, player_Y_Velocity;													// Current x and y velocities of the player
	private boolean [] movement_Direction = {false, false, false, false};								// Four booleans representing player's movement directions
	private final float PLAYER_MAX_X_VELOCITY = 3.4f;
	private double player_Climbing_Velocity = 2.5;
	// max x speed, climbing speed


	
	public Player (Camera camera, int scaling_Factor, float player_X_Pos, float player_Y_Pos, double current_Player_Health){
		this.camera = camera;
		this.scaling_Factor = scaling_Factor;
		this.player_X = (player_X_Pos * this.scaling_Factor);																		// Initializes player's X-position on load-up
		this.player_Y = (player_Y_Pos * this.scaling_Factor);																		// Initializes player's Y-position on load-up
		this.player_Width_And_Height = (this.player_Width_And_Height * this.scaling_Factor);											
		this.player_Climbing_Velocity = this.player_Climbing_Velocity * this.scaling_Factor;
		this.current_Player_Health = current_Player_Health;																			// Initializes player's maximum health on load-up				
		
		setMapBounds();				/// TEMP
	
	}
	
	
	public void playerUpdate(){
	
		System.out.println(player_X_Velocity);
		System.out.println(player_Y_Velocity);
		
		if(on_Surface){																							//TEMP
			
			player_Y_Velocity = 0;																															// if on_Surface, player is not moving vertically anymore
			
			// Player Jump //
			if(is_Jumping){
				player_Y_Velocity = -6;
				is_Jumping = false;
				on_Surface = false;
			}
			
			// Friction //
			if(movement_Direction[2] == false && player_X_Velocity < 0)	player_X_Velocity = player_X_Velocity * 0.8f;										// Leftward Friction	
			if(movement_Direction[3] == false && player_X_Velocity > 0)	player_X_Velocity = player_X_Velocity * 0.8f;										// Rightward Friction
		
		}
		else{
			// Gravity //
			player_Y_Velocity += 0.4;	  																													// Force of Gravity
		}
		
		
		if(on_Ladder){
			 
			player_Y_Velocity = 0; 							// If on ladder, player will not be accelerating upwards
			player_X_Velocity = 0;							// If on ladder, player will not be accelerating left or right
			
			if(movement_Direction[0] == true)
				player_Y -= player_Climbing_Velocity;
			else if(movement_Direction[1] == true)
				player_Y += player_Climbing_Velocity;
			
			if(movement_Direction[2] || movement_Direction[3] || is_Jumping)		// If jump off or move off of ladder, then player no longer on ladder
				on_Ladder = false;
		
		}
		
		
		// Player Velocities
		player_X += player_X_Velocity * scaling_Factor;
		player_Y += player_Y_Velocity * scaling_Factor;
		
		if(movement_Direction[2] == true) player_X_Velocity = Math.max(-PLAYER_MAX_X_VELOCITY,  (player_X_Velocity -1));	
		if(movement_Direction[3] == true) player_X_Velocity = Math.min(PLAYER_MAX_X_VELOCITY, (player_X_Velocity + 1));// * scaling_Factor);	
		
		camera.setCameraCoordinates((int)player_X, (int)player_Y);		// Potentially significant rounding errors with camera
		playerCollision();		
	}
	
	
	private void playerCollision(){				// EXPLORE OPTIMIZATION STRATEGIES - Separate screen into grids
	
		on_Surface = false;
		
		player_Rect.setRect(player_X, player_Y, player_Width_And_Height, player_Width_And_Height);										//Only needs to be called after movement
		
		for(int x = 0; x < map_Boundaries.length; x++){
			
			for(int y = 0; y < map_Boundaries[0].length; y++){
				
				Tile map_Tile = map_Boundaries[x][y];											// Temp variable for player comparison
				playerEnvironmentCollision(map_Tile);
				
			}	
		
		}
	
	}
	private void playerEnvironmentCollision(Tile map_Tile){

		int tile_Value = map_Tile.getTile_V();

		if(tile_Value == 1){
			if(player_Rect.intersects(map_Tile.getRect())){															// Checks player intersection with blocks of value 1 (1 = surface)		
				if(player_Y < map_Tile.getTile_Y()){
					player_Y = map_Tile.getTile_Y() - (player_Width_And_Height - .1f);
					on_Surface = true;
				}	
			}
		}
		
		else if(tile_Value == 3){
			if(player_Rect.intersects(map_Tile.getRect())){	
				if(player_X_Velocity < 0)
					player_X_Velocity = 0;
			}
		}
		
		
		
		else if(tile_Value == 4){	
			if(player_Rect.intersects(map_Tile.getRect())){	
				if(player_X_Velocity > 0)
					player_X_Velocity = 0;
			}
		}
				
		
		
		else if(tile_Value == 5){
			if(player_Rect.intersects(map_Tile.getRect())){									
				
				if(player_Y < map_Tile.getTile_Y() && (map_Tile.getTile_X() - player_X) < (player_Width_And_Height-4)){
					player_Y = map_Tile.getTile_Y() - (player_Width_And_Height - .1f);
					on_Surface = true;
					
					
				}
				
				if(player_X_Velocity > 0 && !on_Surface){
					player_X_Velocity = 0;
				}
				
			}
		}
		
		
		else if(tile_Value == 6){
			if(player_Rect.intersects(map_Tile.getRect())){									
				
				if(player_Y < map_Tile.getTile_Y() && ( player_X - map_Tile.getTile_X() ) < (player_Width_And_Height-4)){
					player_Y = map_Tile.getTile_Y() - (player_Width_And_Height - .1f);
					on_Surface = true;					
				}
				
				if(player_X_Velocity < 0 && !on_Surface){
					player_X_Velocity = 0;
				}
				
				
			}
		}
		

		else if(tile_Value == 8){
			if(player_Rect.intersects(map_Tile.getRect())){	
				if(movement_Direction[0]){
					
					player_X = map_Tile.getTile_X(); 				// Player becomes centered on ladder
					on_Ladder = true;								// Player now on the ladder
					on_Surface = false;								// Player no longer on the surface
					
					if(player_Y <= map_Tile.getTile_Y() && map_Boundaries[map_Tile.getTile_R()-1][map_Tile.getTile_C()].getTile_V() != 8)
						player_Y += player_Climbing_Velocity;
				}
			}
		}	
		
	}
	
	
	

	private void setMapBounds(){
		TileMap tileMap = new TileMap("res/tileMaps/protoMap1.txt", scaling_Factor);				// TEMP
		map_Boundaries = tileMap.getMap();		
		
		for (int row = 0; row < tileMap.getMapHeight(); row++){
			for(int col = 0; col < tileMap.getMapWidth(); col++){
				 System.out.print(map_Boundaries[row][col].getTile_V() + " ");
				
			}
			System.out.println();
		}
	}
	
	
	public float getPlayer_X() {
		return player_X;
	}
	public float getPlayer_Y() {
		return player_Y;
	}

	
	public void keyPressed(KeyEvent e) {        
		
		if(e.getKeyCode() == KeyEvent.VK_W )			// START MOVING UP
			movement_Direction[0] = true;
		
		if(e.getKeyCode() == KeyEvent.VK_S)				// START MOVING DOWN
			movement_Direction[1] = true;
		
		if(e.getKeyCode() == KeyEvent.VK_A)				// START MOVING LEFT
			movement_Direction[2] = true;
		
		if(e.getKeyCode() == KeyEvent.VK_D)				// START MOVING RIGHT
			movement_Direction[3] = true;	
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			is_Jumping = true;
		
	}
	public void keyReleased(KeyEvent e) {     
		
		if(e.getKeyCode() == KeyEvent.VK_W)				// STOP MOVING UP
			movement_Direction[0] = false;
		
		if(e.getKeyCode() == KeyEvent.VK_S)				// STOP MOVING DOWN
			movement_Direction[1] = false;
		
		if(e.getKeyCode() == KeyEvent.VK_A)				// STOP MOVING LEFT
			movement_Direction[2] = false;
		
		if(e.getKeyCode() == KeyEvent.VK_D)				// STOP MOVING RIGHT
			movement_Direction[3] = false;
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			is_Jumping = false;
    }   
	
	
}
