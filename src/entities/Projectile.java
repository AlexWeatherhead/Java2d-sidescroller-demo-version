package entities;

public class Projectile {

	private float projectile_X, projectile_Y;
	private float projectile_X_Velocity, projectile_Y_Velocity;
	private int projectile_Width, projectile_Height;
	private float angle_Of_Trajectory;
	private int projectile_Life, life_Counter = 0;
	
	public Projectile(float target_X_Pos, float target_Y_Pos, float starting_X_Pos, float starting_Y_Pos, int projectile_Width, int projectile_Height, double projectile_Velocity, int projectile_Life){

		angle_Of_Trajectory = (float) Math.atan2(target_X_Pos - starting_X_Pos, target_Y_Pos - starting_Y_Pos);
		projectile_X_Velocity = (float) (projectile_Velocity * Math.sin(angle_Of_Trajectory));
		projectile_Y_Velocity = (float) (projectile_Velocity * Math.cos(angle_Of_Trajectory));
		
		this.projectile_Width = projectile_Width;
		this.projectile_Height = projectile_Height;
		this.projectile_X = starting_X_Pos;
		this.projectile_Y = starting_Y_Pos;
		this.projectile_Life = projectile_Life;
		
	}
	
	public void projectileUpdate(){
		life_Counter++;
		projectile_X += projectile_X_Velocity;
		projectile_Y += projectile_Y_Velocity;
	}

	public boolean isAlive(){
		if(life_Counter == projectile_Life)
			return false;
		return true;
	}
	
	
	
	public float getProjectile_X() {
		return projectile_X;
	}
	public float getProjectile_Y() {
		return projectile_Y;
	}
	public int getProjectile_Width() {
		return projectile_Width;
	}
	public int getProjectile_Height() {
		return projectile_Height;
	}	
}
