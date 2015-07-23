package entities;

public class Camera {

	private int cam_X, cam_Y;								// The X and Y coordinates of the Camera 
	private int cam_View_Port_Width, cam_View_Port_Height;	// Width and Height of the Camera's View-Port
	private int world_Width, world_Height;					// Width and Height of the entire world
	private int cam_Min_X_Offset, cam_Min_Y_Offset;			// Minimum X and Y offsets for Camera
	private int cam_Max_X_Offset, cam_Max_Y_Offset;			// Maximum X and Y offsets for Camera
	
	public Camera(int cam_View_Port_Width, int cam_View_Port_Height){
		this.cam_View_Port_Width = cam_View_Port_Width;
		this.cam_View_Port_Height = cam_View_Port_Height;
	}
	
	public void setCameraCoordinates(int player_X, int player_Y){		
		cam_X = player_X + 100 - (cam_View_Port_Width/2);
		cam_Y = player_Y - 100 - (cam_View_Port_Height/2);
	}

	public int getCam_X() {
		return cam_X;
	}
	public int getCam_Y() {
		return cam_Y;
	}
	
	
}
