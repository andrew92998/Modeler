
public class Tester {

	public static void main(String[] args) {
		Screen.white();
		Camera c = new Camera(0,0,0,0);
		Point p = new Point(0,6.25,4,0);
		Point p2 = new Point(0,12.5,8,0);
		
		Screen.addPoint(p, c);
		Screen.addPoint(p2, c);
		
		Screen.drawPoints();
		Utilities.runDisplay();
		//System.out.println(Math.sqrt(2.25*2.25+9));
	}

}
