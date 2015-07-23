package gameExecution;


import java.awt.Color;												// TEST
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Camera;												// Test
import entities.Player;												// Test?? but need listener
import entities.Projectile;											// TEST
import entities.npcs.enemies.Infantry;								// Test
import entities.worldObjects.Tile;									// TEST
import entities.worldObjects.TileMap;								// TEST


public class GameInstance extends JFrame  {

	private static int scaling_Factor = 2;
	// The following four lines should be within level class
	private static Camera camera = new Camera(1280,720);							// TEST
	private static Player player = new Player(camera, scaling_Factor, 335f, 128.1f, 5); 			// TEST
	TileMap tileMap = new TileMap("res/tileMaps/protoMap1.txt", scaling_Factor);					// TEST
	
	
	public GameInstance(){
		
		addKeyListener(player);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						
		setSize(1280, 720);
		setResizable(false);
		setVisible(true);
		setFocusable(true);
		requestFocus();
		
	}
	
	public static void main(String ars[]){
		
		String curDir = System.getProperty("user.dir");
		System.out.println("Current sys dir: " + curDir);
		
		GameInstance game_Instance = new GameInstance();
		game_Instance.add(game_Instance.new IllustrationSystem());
		game_Instance.addKeyListener(player);
		
		boolean is_Running = true;
		
		while(is_Running){
		
			//npc.npcUpdate(player.getPlayer_X(), player.getPlayer_Y()); 					// TEST
			player.playerUpdate();
			game_Instance.repaint();
			
			try{
				Thread.sleep(17);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		
	}
	
    protected class IllustrationSystem extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            
            g.translate(-camera.getCam_X(), -camera.getCam_Y());			// Camera Translation
            
            Graphics2D g2d = (Graphics2D) g.create();
            
            for(int x = 0; x < tileMap.getMapHeight(); x++){
            	for(int y = 0; y < tileMap.getMapWidth(); y++){
                	Tile o = tileMap.getMap()[x][y];
            		if(o.getTile_V() == 0)
                		g2d.setColor(Color.BLUE);
                	else if(o.getTile_V() == 1)
                		g2d.setColor(Color.BLACK);
                	else if(o.getTile_V() == 5 || o.getTile_V() == 6)
                		g2d.setColor(Color.GREEN);
                	else if(o.getTile_V() == 3 || o.getTile_V() == 4)
                		g2d.setColor(Color.yellow);
                	else if(o.getTile_V() == 7)
                		g2d.setColor(Color.MAGENTA);
                	else if(o.getTile_V() == 8)
                		g2d.setColor(Color.cyan);
                	
            		g2d.fill(new Rectangle2D.Float(y*32f*scaling_Factor, x*32f*scaling_Factor, (32f*scaling_Factor), (32f*scaling_Factor))); 
                	//g.fillRect(y*32, x*32, (32*scaling_Factor), (32*scaling_Factor));
                }
           
            }

            
            
            g2d.setColor(Color.WHITE);
            g2d.draw(new Rectangle2D.Float(player.getPlayer_X(), player.getPlayer_Y(), 32*scaling_Factor, 32*scaling_Factor));
            g2d.setColor(Color.RED);
           
            
            
            /*
            g2d.fill(new Rectangle2D.Float(npc.getNpc_X(), npc.getNpc_Y(), npc.getNpc_W(), npc.getNpc_H()));
            Projectile [] clip = npc.getUnit_Clip();
            for(int x = 0; x < clip.length; x++){
            	if(clip[x] != null)
            		g2d.fill(new Rectangle2D.Float(clip[x].getProjectile_X(),clip[x].getProjectile_Y(), clip[x].getProjectile_Width(), clip[x].getProjectile_Height()));
            }
            
            */
            
            g2d.dispose();

        }

    }	

}


