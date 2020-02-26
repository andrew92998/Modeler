
public class Light extends Point {

	public double intensity;
	public Light(double[] position, int origColor, double intensity) {
		super(position, origColor);
		this.intensity=intensity;
	}

}
