
public class PointInfo implements Comparable<PointInfo>{
	public int trueColor;
	public int x;
	public int y;
	public double d; //distance to camera 
	public double aMag;
	public Point point;
	public PointInfo(double[] info, Point point){
		this.point=point;
		trueColor = (int) info[3];
		x = (int) info[0] + 500;
		y= 300 - (int) info[1];
		d = info[2];
		aMag = info[4];
	}
	
	@Override
	public int compareTo(PointInfo o) {
		return Double.compare(o.d, this.d);
	}
	
	
}
