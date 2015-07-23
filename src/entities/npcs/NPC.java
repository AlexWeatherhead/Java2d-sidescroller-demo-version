package entities.npcs;

public class NPC {
	
	protected float npc_X, npc_Y;
	protected int npc_W, npc_H;
	
	public NPC(float npc_X, float npc_Y, int npc_W, int npc_H){
		this.npc_X = npc_X;
		this.npc_Y = npc_Y;
		this.npc_W = npc_W;
		this.npc_H = npc_H;
	}
	
	public void npcUpdate(){
		
	}

	public float getNpc_X() {
		return npc_X;
	}
	public float getNpc_Y() {
		return npc_Y;
	}
	public int getNpc_W() {
		return npc_W;
	}
	public int getNpc_H() {
		return npc_H;
	}
	
}
