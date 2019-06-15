package geometry3d;

public class Camera extends Point3D{

	public double yaw = 0;
	public double pitch = 0;
	public double roll = 0;
	
	public Camera(double x, double y, double z) {
		super(x, y, z);
	}

}
