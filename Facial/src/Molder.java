import java.util.ArrayList;

import javax.print.attribute.standard.PrinterMessageFromOperator;

public class Molder {
	public static double moldRadius = 20;
	public static int moldX;
	public static int moldY;
	public static int moldType = 1;
	public static final int maxTypes = 8;
	public static double planeD;
	public static ArrayList<Point> selectedPoints = new ArrayList<>();

	public static void switchType() {
		moldType++;
		planeD = 0.0;
		if (moldType > maxTypes)
			moldType = 1;
		if (moldType == 1)
			Screen.moldColor = 255;
		if (moldType == 2)
			Screen.moldColor = 255 * 256;
		if (moldType == 3)
			Screen.moldColor = 255 * 256 * 256;
		if (moldType == 4)
			Screen.moldColor = 0;
		if (moldType == 5)
			Screen.moldColor = 255 + 255 * 256 * 256;
		if (moldType == 6)
			Screen.moldColor = 255 * 256 + 255 * 256 * 256;
		if (moldType == 7)
			Screen.moldColor = 255 * 256 + 34 * 256 * 256 + 140;
		if (moldType == 8)
			Screen.moldColor = 128 * 256 + 255 * 256 * 256 + 140;
	}

	public static void mold(int x, int y, World world) {
		if (moldType == 1)
			mold1(x, y, world);
		if (moldType == 2)
			mold2(x, y, world);
		if (moldType == 3)
			mold3(x, y, world);
		if (moldType == 4)
			mold4(x, y, world);
		if (moldType == 5)
			mold5(x, y, world);
		if (moldType == 6)
			mold6(x, y, world);
		if (moldType == 7)
			mold7(x, y, world);
		if (moldType == 8)
			mold8(x, y, world);
	}

	

	public static void mold1(int x, int y, World world) {
		for (PointInfo pInfo : Screen.allPoints) {
			double r = (pInfo.x - x) * (pInfo.x - x) + (pInfo.y - y) * (pInfo.y - y);
			if (r <= moldRadius * moldRadius) {
				pInfo.point.select();
			}
		}

	}
	
	public static void mold2(int x, int y, World world) {
		Camera camera = world.camera;
		double sens = 0.03;
		moldX = x;
		moldY = y;
		for (PointInfo pInfo : Screen.allPoints) {
			double r = (pInfo.x - x) * (pInfo.x - x) + (pInfo.y - y) * (pInfo.y - y);
			if (r <= moldRadius * moldRadius && pInfo.point.selected) {
				pInfo.point.position = Utilities.add(pInfo.point.position,
						Utilities.multiply(camera.attitude, sens * (moldRadius * moldRadius - r) / pInfo.d));
			}
		}
	}
	public static void mold3(int x, int y, World world) {
		Camera camera = world.camera;
		Screen.sort();
		for (PointInfo pInfo : Screen.allPoints) {
			if (pInfo.point.selected) {

				if (planeD < 0.1)
					planeD = pInfo.aMag;
				if (pInfo.aMag < planeD)
					pInfo.point.position = Utilities.add(pInfo.point.position,
							Utilities.multiply(camera.attitude, 0.1));
			}
		}
	}

	public static void mold4(int x, int y, World world) {
		for (PointInfo pInfo : Screen.allPoints) {
			double r = (pInfo.x - x) * (pInfo.x - x) + (pInfo.y - y) * (pInfo.y - y);
			if (r <= moldRadius * moldRadius && pInfo.point.selected) {
				pInfo.point.deselect();
			}
		}

	}

	public static void mold5(int x, int y, World world) {
		for (PointInfo pInfo : Screen.allPoints) {
			double r = (pInfo.x - x) * (pInfo.x - x) + (pInfo.y - y) * (pInfo.y - y);
			if (r <= moldRadius * moldRadius && pInfo.point.selected) {
				world.points.remove(pInfo.point);
			}
		}
	}

	public static void mold6(int x, int y, World world) {
		//System.out.println(world.points.get(1).position[0]+" "+world.points.get(1).position[1]+" "+world.points.get(1).position[2]);
		double scale = .2;
		Camera camera = world.camera;
		Screen.sort();
		for (PointInfo pInfo : Screen.allPoints) {
			if (pInfo.point.selected) {
				double[] overallDelta = new double[3];
		
				double[] zPoint  = pInfo.point.position;
				double minZ = 10000;
				for (PointInfo p : Screen.allPoints) {
					double zComp = p.point.position[2] - pInfo.point.position[2];
					double d = Utilities.mag(Utilities.subtract(p.point.position, pInfo.point.position));
					double temp = d * zComp
							* zComp * zComp;
					if (zComp < 2 && zComp > 0.1 && temp < minZ && temp > 0 && d <3) {
						minZ = temp;
						zPoint = p.point.position;
					}
				}

				double[] negZPoint  = pInfo.point.position;
				double minNegZ = -10000;
				for (PointInfo p : Screen.allPoints) {
					double negZComp = p.point.position[2] - pInfo.point.position[2];
					double d = Utilities.mag(Utilities.subtract(p.point.position, pInfo.point.position));
					double temp =  d* negZComp
							* negZComp * negZComp;
					if (negZComp > -2 && negZComp < -0.1 &&  temp > minNegZ && temp < 0 && d < 3) {
						minNegZ = temp;
						negZPoint = p.point.position;
					}
				}

				double[] xPoint  = pInfo.point.position;
				double minX = 10000;
				for (PointInfo p : Screen.allPoints) {
					double xComp = p.point.position[0] - pInfo.point.position[0];
					double d = Utilities.mag(Utilities.subtract(p.point.position, pInfo.point.position));
					double temp = d * xComp
							* xComp * xComp * xComp * xComp ;
					if (xComp < 2 && xComp >0.1 && temp < minX && temp > 0 && d<3) {
						minX = temp;
						xPoint = p.point.position;
					}
				}
				double[] negXPoint = pInfo.point.position;
				double minNegX = -10000;
				for (PointInfo p : Screen.allPoints) {
					double xNegComp = p.point.position[0] - pInfo.point.position[0];
					
					double d = Utilities.mag(Utilities.subtract(p.point.position, pInfo.point.position));
					double temp = d* xNegComp
							* xNegComp * xNegComp * xNegComp * xNegComp ;
					if (xNegComp >  -2 && xNegComp <  -0.1 && temp > minNegX && temp < 0&& d<3) {
						minNegX = temp;
						negXPoint = p.point.position;
					}
				}
				
				if(minNegX != -10000) {
					//overallDelta[0] += xPoint[0] - pInfo.point.position[0];
					overallDelta[1] += (xPoint[1] - pInfo.point.position[1]) * 4;
				}
				if(minX != 10000){
					//overallDelta[0] += negXPoint[0] - pInfo.point.position[0];
					overallDelta[1] += (negXPoint[1] - pInfo.point.position[1]) * 4;
				}
				if(minNegZ != -10000) {
					//overallDelta[0] += zPoint[0] - pInfo.point.position[0];
					overallDelta[1] += (zPoint[1] - pInfo.point.position[1]) * 4;
				}
				if(minZ != 10000) {
					//overallDelta[0] += negZPoint[0] - pInfo.point.position[0];
					overallDelta[1] += (negZPoint[1] - pInfo.point.position[1]) * 4;
				}
				if(Utilities.mag(overallDelta) > .6){
					overallDelta = Utilities.normal(overallDelta);
				}
				else overallDelta = new double[3];
				
				pInfo.point.position = Utilities.add(pInfo.point.position, Utilities.multiply(overallDelta, scale));
			}
		}
	}
	
	public static void mold7(int x, int y, World world){
		double sens = 0.03;
		moldX = x;
		moldY = y;
		for (PointInfo pInfo : Screen.allPoints) {
			double r = (pInfo.x - x) * (pInfo.x - x) + (pInfo.y - y) * (pInfo.y - y);
			if (r <= moldRadius * moldRadius && pInfo.point.selected && pInfo.point instanceof Pivot) {
				pInfo.point.position[1] += world.camera.attitude[1];
						
			}
		}
		world.requestReCalculation();
	}
	public static void mold8(int x, int y, World world){
		double sens = 0.03;
		moldX = x;
		moldY = y;
		ArrayList<Pivot> pivs = new ArrayList<Pivot>();
		
		for (PointInfo pInfo : Screen.allPoints) {
			
			if (pInfo.point.selected && pInfo.point instanceof Pivot) {
				pivs.add((Pivot) pInfo.point);
				pInfo.point.deselect();
			}
		}
		if(pivs.size() >=3){
			//Utilities.drawTriangle(world.points, pivs.get(0).position,pivs.get(1).position,pivs.get(2).position, 0);
			world.planes.add(new Plane(pivs.get(0),pivs.get(1),pivs.get(2),0));
			world.requestReCalculation();
		}
	}
}
