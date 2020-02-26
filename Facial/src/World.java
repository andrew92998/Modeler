import java.util.ArrayList;

public class World {
	public boolean inPlay=false;
	public ArrayList<Point> points = new ArrayList<Point>();
	public ArrayList<Pivot> pivots = new ArrayList<Pivot>();
	public ArrayList<Plane> planes = new ArrayList<Plane>();
	public ArrayList<Light> lights = new ArrayList<Light>();
	public Camera camera = new Camera(0,0,0,0);
	public int groundColor = 255;
	private boolean requestedReCalc = false;
	public World(){
		Input.test8(points);
		//Utilities.mirror(this);
		Utilities.map(points, "Me.jpg", 0, 0, -146.14, 171.14, 1/7.0);
		//System.out.println(points.size());
		
	}
	public void render(int toRight,int toUp, int toTilt, int forward, int sideways, int upward, int x, int y) {
		
		double[] flat = new double[3];
		int stillRadius = 60;
		flat[0] = -camera.attitude[1];
		flat[1] = camera.attitude[0];
		flat[2] = 0;
		flat = Utilities.normal(flat);
		double sens = 1;

		if(points.size()>1000) sens = points.size()/500;
		double[] perpFlat = Utilities.normal(Utilities.cross(camera.attitude, flat));
		
		//if(x > stillRadius || x < - stillRadius || y > stillRadius || y < - stillRadius)camera.attitude = Utilities.normal(Utilities.add(Utilities.add(camera.attitude, Utilities.multiply(flat, x/1000000.0*sens)), Utilities.multiply(perpFlat, y/1000000.0*sens)));
		camera.attitude = Utilities.normal(Utilities.add(Utilities.add(camera.attitude, Utilities.multiply(flat, -toRight/10000.0*sens)), Utilities.multiply(perpFlat, toUp/10000.0*sens)));
		camera.position = Utilities.add(Utilities.add(Utilities.add(camera.position, Utilities.multiply(flat, -sideways/100.0*sens)), Utilities.multiply(perpFlat, upward/100.0*sens)),Utilities.multiply(camera.attitude, forward/100.0*sens));
		
		//System.out.println(Math.atan(flat[1]/flat[0]));
		if(requestedReCalc){
			points = new ArrayList<Point>();
			for(Pivot p: pivots){
				points.add(p);
			}
			Utilities.drawAllPlanes(points,planes);
			//Utilities.map(points, "IU.jpg", 0, 0, 700, 750, -53, 43.2, 1/7.0);
			Utilities.map(points, "Me.jpg", 0, 0, -146.14, 171.14, 1/7.0);

			requestedReCalc = false;
				
		}
	}
	public void requestReCalculation(){
		requestedReCalc = true;
	}
}
