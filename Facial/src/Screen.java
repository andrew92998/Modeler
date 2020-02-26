import java.util.ArrayList;

public class Screen {
	public static int[] pixels = new int[1000 * 600];
	public static int backdrop = 0;
	public static boolean drawing = false;
	public static double radius;
	public static int x;
	public static int y;
	public static int moldColor = 255;
	public static ArrayList<PointInfo> allPoints = new ArrayList<>();
	private static double pixelRadius = 600;

	public static void render(World world) {
		white();
		clearPoints();
		for (Point p : world.points) {
			addPoint(p, world.camera);
		}
		drawPoints();
		radius = Molder.moldRadius;
		Screen.drawMoldCircle();
	}

	public static void clearPoints() {

		while (!allPoints.isEmpty()) {
			allPoints.remove(0);
		}
	}

	public static void drawMoldCircle() {
		for (int i = x - (int) radius; i < x + (int) radius; i++)
			for (int j = y - (int) radius; j < y + (int) radius; j++)
				if (((double) i - (double) x) * ((double) i - (double) x)
						+ ((double) j - (double) y) * ((double) j - (double) y) < radius * radius
						&& ((double) i - x) * ((double) i - x) + ((double) j - y) * ((double) j - y) > radius * radius
								- 100) {

					if (i > 0 && i < 1000 && j > 0 && j < 600)
						pixels[i + j * 1000] = moldColor;
				}

	}

	public static void white() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 255 * 256 * 256 + 255 * 256 + 255;
			//pixels[i] = 0;
	}

	public static double[] getInfo(Point p, Camera camera){
		double[] c = Utilities.subtract(p.position, camera.position);
		double aMag = Utilities.dot(c, Utilities.normal(camera.attitude));
		double[] a = Utilities.multiply(Utilities.normal(camera.attitude), aMag);
		double[] b = Utilities.subtract(c, a);

		double[] flat = new double[3];
		flat[0] = -camera.attitude[1];
		flat[1] = camera.attitude[0];
		flat[2] = 0;
		flat = Utilities.normal(flat);

		double[] perpFlat = Utilities.normal(Utilities.cross(camera.attitude, flat));

		double[] POV = new double[3];
		POV[0] = -Utilities.dot(b, flat);
		POV[1] = Utilities.dot(b, perpFlat);

		double[] POVAdjusted = Utilities.multiply(POV, 1 / aMag * camera.zoom);

		double[] info = new double[5];
		info[0] = POVAdjusted[0];
		info[1] = POVAdjusted[1];
		info[2] = Utilities.mag(c);
		info[3] = p.trueColor + 0.1; // just in case some floating number
										// business goes on when converting it
										// back to int
		info[4] = aMag;
		return info;
	}

	public static void addPoint(Point p, Camera c) {
		allPoints.add(new PointInfo(getInfo(p, c), p));
	}

	public static void sort() {
		allPoints.sort(null);
	}

	public static void drawPoints() {
		sort();

		for (PointInfo p : allPoints) {
			if(p.aMag>0){
			int width = (int) ((1 / p.d) * pixelRadius);
			if (width < 1)
				width = 1;// radius sort of thing
			if (p.x - width >= 0 && p.x + width < 1000 && p.y - width >= 0 && p.y + width < 600) {

				for (int i = p.x - width; i < p.x + width; i++) {
					for (int j = p.y - width; j < p.y + width; j++) {
						pixels[i + 1000 * j] = p.trueColor;
					}

				}
			}
			}
		}

	}

	public static void setXY(int x2, int y2) {
		x = x2;
		y = y2;
	}

}
