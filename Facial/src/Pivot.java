
public class Pivot extends Point implements Comparable<Pivot>{
	public int id;
	public Pivot(double x, double y, double z, int origColor, int id) {
		super(x, y, z, origColor);
		this.id = id;
	}
	@Override
	public int compareTo(Pivot o) {
		return this.id - o.id;
	}

}
