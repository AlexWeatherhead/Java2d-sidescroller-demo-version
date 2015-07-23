package entities.npcs.enemies;

import entities.Projectile;



/*
 * Infantry Function:
 * 	Patrol
 * 	Push Forward
 *  Idle/Guard
 *  Escort
 */


public class Infantry extends Enemy{
	
	// Weapon Class //
	private int weapon_Class; // 1) assault rifle, 2) sniper rifle 
	
	// Max Infantry Movement Speed //
	protected final static float INFANTRY_X_VELOCITY = 2f;
	
	// Line of Sight //
	protected boolean unit_Alerted; 
	
	// Infantry Bullet Cooldown //
	protected int bullet_Cooldown_Counter = 0;
	protected boolean infantry_Can_Shoot = true;
	
	public Infantry (float npc_X, float npc_Y, int npc_W, int npc_H, boolean is_Facing_Left, double bullet_Damage, double max_Health, int line_Of_Sight){
		super(npc_X, npc_Y, npc_W, npc_H, bullet_Damage, max_Health, line_Of_Sight);
		this.is_Facing_Left = is_Facing_Left;
	}	
	
	public void npcUpdate(float player_X, float player_Y){
		super.npcUpdate();
		
	}
	
	public Projectile infantryAttack(float player_X, float player_Y, int bullet_Width, int bullet_Height, double bullet_Velocity, int bullet_Life){
		infantry_Can_Shoot = false;
		Projectile player_Bullet = new Projectile(player_X, player_Y, npc_X, npc_Y, bullet_Width, bullet_Height, bullet_Velocity, bullet_Life);
		return player_Bullet;
	}
	

	
}
