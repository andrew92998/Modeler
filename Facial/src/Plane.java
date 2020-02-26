
public class Plane {
	public Point p1;
	public Point p2;
	public Point p3;
	public double[] perpVec;
	public int color;
	public Plane(Point p1, Point p2, Point p3, int color){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.color = color;
		perpVec = Utilities.cross(Utilities.subtract(p2.position, p1.position), Utilities.subtract(p3.position, p1.position));
	}
}
