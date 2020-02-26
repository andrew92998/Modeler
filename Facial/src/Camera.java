
public class Camera extends Point {
	public double[] attitude = new double[3];
	public double angleFromHorizon;
	public double zoom;
	public Camera(double[] position, int origColor) {
		super(position, origColor);
		attitude[0]=0;
		attitude[1]=1;
		attitude[2]=0;
		angleFromHorizon = 0;
		zoom = 1;
	}
	
	public Camera(double x, double y, double z, int origColor) {
		super(x,y, z, origColor);
		attitude[0]=0;
		attitude[1]=1;
		attitude[2]=0;
		angleFromHorizon = 0;
		zoom = 400;
	}
	

}
