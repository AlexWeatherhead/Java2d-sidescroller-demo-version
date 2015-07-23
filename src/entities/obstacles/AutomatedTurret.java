package entities.obstacles;

import entities.Projectile;

public class AutomatedTurret extends Obstacle{
	
	// Turret Features //
	private int aT_X, aT_Y;																// X and Y coordinates of Turret
	private int aT_W, aT_H;																// Width and Height of Turret
	private boolean is_Facing_Left;														// direction which the turret is facing
	private final static int aT_R = 200; 												// Range of Turret
	private boolean turret_Can_Shoot = true;
	private int turret_Cooldown_Counter = 0;
	
	// Turret Bullet Features //
	private final static double TURRET_BULLET_VELOCITY = 4.0;							// velocity of bullets when shot		
	private final static double TURRET_BULLET_DAMAGE = 0.5;								// damage which the bullets inflict when they hit target
	private final static int TURRET_BULLET_WIDTH = 8, TURRET_BULLET_HEIGHT = 4;			// dimensions of bullets fired
	private final static int TURRET_BULLET_LIFE = 200;									// the life of each individual bullet - value representative of number of ticks
	private final static int TURRET_BULLET_COOLDOWN_TIME = 20;							// cooldown for turret firing - value representative of number of ticks
	
	// Turret Bullet Clip Features //
	private final static int TURRET_CLIP_SIZE = 3;										// this many of this turrets bullets can be in existence at any one time
	private Projectile [] unit_Clip = new Projectile[TURRET_CLIP_SIZE];					// a collection of all current bullets
	
	
	public AutomatedTurret(int aT_X, int aT_Y, int aT_W, int aT_H, boolean is_Facing_Left){
		this.aT_X = aT_X;
		this.aT_Y = aT_Y;
		this.aT_W = aT_W;
		this.aT_H = aT_H;
		this.is_Facing_Left = is_Facing_Left;
	}

	public void obstacleUpdate(float player_X, float player_Y){
		
		if(!turret_Can_Shoot)
			turret_Cooldown_Counter ++;
		
		for(int counter = TURRET_CLIP_SIZE - 1; counter >= 0; counter--){
			if(unit_Clip[counter] == null){
				if(turret_Can_Shoot){
					if(is_Facing_Left){
						if(player_X < aT_X && aT_X - player_X <= aT_R)
							unit_Clip[counter] = attack(player_X, player_Y);
					}
					else{
						if(player_X > aT_X && player_X - aT_X <= aT_R)
							unit_Clip[counter] = attack(player_X, player_Y);
					}
				}
			}
			else{
				unit_Clip[counter].projectileUpdate();
				if(unit_Clip[counter].isAlive() == false)
					unit_Clip[counter] = null;
			}
		}
		
		if(turret_Cooldown_Counter == TURRET_BULLET_COOLDOWN_TIME){
			turret_Can_Shoot = true;
			turret_Cooldown_Counter = 0;
		}
	}
	
	
	public Projectile attack(float player_X, float player_Y){
		turret_Can_Shoot = false;
		Projectile player_Bullet = new Projectile(player_X, player_Y, aT_X, aT_Y, TURRET_BULLET_WIDTH, TURRET_BULLET_HEIGHT, TURRET_BULLET_VELOCITY, TURRET_BULLET_LIFE);
		return player_Bullet;
	}
	
	public Projectile [] getUnit_Clip(){
		return unit_Clip;
	}

	
	
	@Override
	public void obstacleDraw() {
		// TODO Auto-generated method stub
		
	}
}
