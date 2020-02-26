
public class Point {
	public int origColor;
	public int trueColor;
	public boolean selected = false;
	public double[] position = new double[3];
	
	public Point(double[] position, int origColor){
		this.position[0] = position[0];
		this.position[1] = position[1];
		this.position[2] = position[2];
		this.origColor=origColor;
		trueColor = origColor;
	}
	public Point(double x, double y, double z, int origColor){
		this.position[0] = x;
		this.position[1] = y;
		this.position[2] = z;
		this.origColor=origColor;
		trueColor = origColor;
	}
	public void select(){
		trueColor = (origColor+324232475)%(255*256*256+255*256+255+1);
		selected = true;
	}
	public void deselect(){
		trueColor=origColor;
		selected = false;
	}
	public void setColor(int color){
		origColor = color;
		trueColor=origColor;
	}
}
