package entities.npcs.enemies;

import entities.npcs.NPC;

public class Enemy extends NPC {
	
	protected float npc_X_Velocity, npc_Y_Velocity;
	private double npc_Attack_Damage;
	private double npc_Current_Health, npc_Maximum_Health;
	protected boolean is_Facing_Left;
	protected int line_Of_Sight;
	
	public Enemy(float npc_X, float npc_Y, int npc_W, int npc_H, double npc_Attack_Damage, double npc_Maximum_Health, int line_Of_Sight){
		super(npc_X, npc_Y, npc_W, npc_H);
		this.npc_Attack_Damage = npc_Attack_Damage;
		this.npc_Maximum_Health = npc_Maximum_Health;
		this.line_Of_Sight = line_Of_Sight;
	}
	
	public void npcUpdate(){
		super.npcUpdate();
		npc_X += npc_X_Velocity;
		npc_Y += npc_Y_Velocity;
	}
	
	public void pathing(){
		
	}


}
