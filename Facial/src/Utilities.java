import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utilities {
	
	private static double resolution = 1.5;
	public static double[] normal(double[] vec){
		double[] newVec = new double[3];
		newVec[0] = vec[0];
		newVec[1] = vec[1];
		newVec[2] = vec[2];
		
		double mag = mag(vec);
		
		for(int i = 0; i < 3; i++)
			newVec[i]/=mag;
		
		return newVec;
		
	}
	public static double mag(double[] vec){
		return Math.sqrt(dot(vec,vec));
	}
	public static double addEntries(double[] vec){
		return vec[0]+vec[1]+vec[2];
	}
	public static double[] add(double[] vec, double[] vec2){
		double[] newVec = new double[3];
		newVec[0] = vec[0]+vec2[0];
		newVec[1] = vec[1]+vec2[1];
		newVec[2] = vec[2]+vec2[2];
		
		return newVec;
	}
	public static double[] subtract(double[] vec, double[] vec2){
		double[] newVec = new double[3];
		newVec[0] = vec[0]-vec2[0];
		newVec[1] = vec[1]-vec2[1];
		newVec[2] = vec[2]-vec2[2];
		
		return newVec;
		
	}
	public static double dot(double[] vec, double[] vec2){
		double[] newVec = new double[3];
		newVec[0] = vec[0]*vec2[0];
		newVec[1] = vec[1]*vec2[1];
		newVec[2] = vec[2]*vec2[2];
		
		return addEntries(newVec);
		
	}
	public static double[] multiply(double[] vec, double mag){
		double[] newVec = new double[3];
		newVec[0] = vec[0]*mag;
		newVec[1] = vec[1]*mag;
		newVec[2] = vec[2]*mag;
		
		return newVec;
		
	}
	public static double[] cross(double[] vec, double[] vec2){
		double[] newVec = new double[3];
		newVec[0] = vec[1]*vec2[2]-vec[2]*vec2[1];
		newVec[1] = vec[2]*vec2[0]-vec[0]*vec2[2];
		newVec[2] = vec[0]*vec2[1]-vec[1]*vec2[0];
		
		return newVec;
		
	}
	public static void print(double[] v){
		for(int i = 0; i < v.length; i++)
			System.out.print(v[i]+", ");
		System.out.println();
	}
	public static void runDisplay(){

		JPanel dummy = new JPanel();
		JFrame frame = new JFrame();
		Display Game = new Display(dummy,frame);
		
		// Console console = new Console();
		
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.add(dummy);
		frame.add(Game);
		dummy.requestFocusInWindow();
		frame.setAlwaysOnTop(false);
		frame.setVisible(true);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setTitle("Faces");
		Game.start();
	}
	public static void writeToFile(String filename, World world) {
		try {
			boolean append = false;
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, append));
			
			for(Point p:world.points)
			{
				bufferedWriter.write("yes"+"\n");
				bufferedWriter.write(String.valueOf(p.position[0])+"\n");
				bufferedWriter.write(String.valueOf(p.position[1])+"\n");
				bufferedWriter.write(String.valueOf(p.position[2])+"\n");
				bufferedWriter.write(String.valueOf(p.origColor)+"\n");
			}
			
			bufferedWriter.flush();
			bufferedWriter.close();

			System.out.println("Results can be found in file: " + filename);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void drawQuad(ArrayList<Point> w, double[] point1, double[] point2, double[] point3, double[] point4, int color){
		double[] vector1 = subtract(point2,point1);
		double[] vector2 = subtract(point3,point1);
		double mag3 = dot(subtract(point4,point2),normal(vector2));
		if(mag3 > 0){
			double mag1 = mag(vector1);
			double mag2 = mag(vector2);
			vector1 = normal(vector1);
			vector2 = normal(vector2);
			double[] pos = point1;
			for(double i = 0; i < mag1; i+=resolution){
				for(double j = 0; j < mag2 + (i/mag1)*(mag3-mag2); j+=resolution){
					pos = add(add(point1, multiply(vector1, i)), multiply(vector2, j));
					w.add(new Point(pos, color));
				}
			}
			drawTriangle(w, pos, point3, point4,color);
			drawTriangle(w, point2, point3, point4,color);
		}
		else drawQuad(w, point1, point2, point4, point3, color);
		
	}
	public static void drawTriangle(ArrayList<Point> w, double[] point1, double[] point2, double[] point3, int color) {
		double[] vector1 = subtract(point2,point1);
		double[] vector2 = subtract(point3,point1);
		double mag1 = mag(vector1);
		double mag2 = mag(vector2);
		vector1 = normal(vector1);
		vector2 = normal(vector2);
		double[] pos = point1;
		for(double i = 0; i < mag1; i+=resolution){
			for(double j = 0; j < mag2*(1-i/mag1); j+=resolution){
								pos = add(add(point1, multiply(vector1, i)), multiply(vector2, j));
				w.add(new Point(pos, color));
			}
		}
	}
	public static void mirror(World w){
		int s = w.points.size();
		for(int i=0; i<s; i++){
			if(w.points.get(i) instanceof Pivot && (w.points.get(i).position[0] > 1 || w.points.get(i).position[0] < -1)){
				Pivot p = new Pivot(-w.points.get(i).position[0],w.points.get(i).position[1],w.points.get(i).position[2], w.points.get(i).origColor, 1000 - ((Pivot)w.points.get(i)).id);
				w.points.add(p);
				w.pivots.add(p);
			}
			else w.points.add(new Point(-w.points.get(i).position[0],w.points.get(i).position[1],w.points.get(i).position[2], w.points.get(i).origColor));
			
		}
			
		
	}
	public static void map(ArrayList<Point> points,String filename, int xStart, int zStart, double xStart2, double zStart2, double aspect){
		File img = new File(filename);
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(xStart, zStart, buff.getWidth(), buff.getHeight(), null, 0, buff.getWidth());
			for(Point p:points){
				int x = (int)((p.position[0] - xStart2)/aspect);
				int y = (int)((zStart2 - p.position[2])/aspect);
				if(x<buff.getWidth() && x>0 && y<buff.getHeight() && y > 0){
					
					int index = (x)*buff.getWidth() + y;
					//index = (x) + buff.getWidth()*y; // for some pictures
					p.setColor(array[index]);
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void generateGeometry(ArrayList<Point> points, String filename1, String filename2, int xStart, int zStart, int width, int height, double xStart2, double zStart2, double aspect){
		int numPivots = 255;
		File img = new File(filename1);
		File depths = new File(filename2);
		ArrayList<Pivot> pivots = new ArrayList<>();
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(xStart, zStart, width, height, null, 0, width);
			double[] depthsArray = new double[numPivots];
			int color;
	            BufferedReader fin = new BufferedReader(new FileReader(depths));
	            for(int i = 0;  i < numPivots; i++){
	            	depthsArray[i] = Double.parseDouble(fin.readLine());
	            }
	            fin.close();
	            
	            
			for(int i = 0; i < array.length; i++){
				if(array[i] <= numPivots){
					double x = (i % width) * aspect + xStart2;
					double y = zStart2 - (i / width) * aspect;
					pivots.add(new Pivot(x,depthsArray[array[i]],y, 255, array[i]));	
				}
			}
			pivots.sort(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void drawAllPlanes(ArrayList<Point> points,ArrayList<Plane> planes) {
		for(Plane p: planes){
			drawTriangle(points, p.p1.position,p.p2.position,p.p3.position,p.color);
		}
		
	}
	public static void generateFlatPivots(ArrayList<Point> points, String filename1, int xStart, int zStart, int width, int height, double xStart2, double zStart2, double aspect){
		File img = new File(filename1);
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(xStart, zStart, width, height, null, 0, width);
			int counter=0;
	   
			for(int i = 0; i < array.length; i++){
				if(array[i]+16777216 == 8*256*256 + 255){
					double x = (i % width) * aspect + xStart2;
					double y = zStart2 - (i / width) * aspect;
					points.add(new Pivot(x, 500, y, 255, counter));

					counter++;
				}
			}
	        System.out.println(counter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void generateGeometry2(ArrayList<Point> points,ArrayList<Pivot> pivots, String filename1, String filename2, int width, int height, double xStart2, double zStart2, double aspect){
		int numPivots = 255;
		File img = new File(filename1);
		File img2 = new File(filename2);
		double[][][] hashMap = new double[numPivots][2][3];
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(0, 0, width, height, null, 0, width);
			BufferedImage buff2 = ImageIO.read(img2);
			int[] array2 = buff2.getRGB(0, 0, width, height, null, 0, width);		
			int rgbOffset = 16777216;
			for(int i = 0; i < array.length; i++){
				if(array[i] + rgbOffset <= numPivots){
					int index = (array[i]+ rgbOffset);
					if(hashMap[index][0][0]!=0)
						index++;
					hashMap[index][0][0] = i % width;
					hashMap[index][0][1] =  - (i / width);
					
				}
			}
			for(int i = 0; i < array2.length; i++){
				if(array2[i] + rgbOffset <= numPivots){ 
					int index = (array2[i]+ rgbOffset);
					if(hashMap[index][1][0]!=0)
						index++;//png rgb is different by that much due to transp
					hashMap[index][1][0] = i % width;
					hashMap[index][1][1] =  - (i / width);
				
				}
			}
			
			//point 0 is the center of the face
			// points 1 and 2 measure the width of pupil
			double scale = Utilities.mag(Utilities.subtract(hashMap[1][0], hashMap[2][0]))/Utilities.mag(Utilities.subtract(hashMap[1][1], hashMap[2][1]));
			for(int i = 0; i < numPivots; i++){
				hashMap[i][1] = Utilities.add(Utilities.multiply(Utilities.subtract(hashMap[i][1], hashMap[0][1]), scale), hashMap[0][1]);
			}
			//points 3 and 4 are width of eyebrows.
			double eyeRatio = Utilities.mag(Utilities.subtract(hashMap[3][1], hashMap[4][1]))/Utilities.mag(Utilities.subtract(hashMap[3][0], hashMap[4][0]));
			double theta = Math.acos(eyeRatio);
			
			for(int i = 0; i < numPivots; i++){
			//theta = Math.PI/4;
					eyeRatio = Math.cos(theta);
				if(hashMap[i][0][0] != 0 ){
					double length = Math.sin(theta) * 100 + eyeRatio * (hashMap[i][1][0] - hashMap[0][1][0]) - (hashMap[i][0][0] - hashMap[0][0][0]);
					double y = 100 - 100 * (eyeRatio) + Math.sin(theta) * (hashMap[i][1][0] - hashMap[0][1][0]) + length / Math.sin(theta) * eyeRatio;
					Pivot p = new Pivot((hashMap[i][0][0] - hashMap[0][0][0]) * aspect, y * aspect + 300, (hashMap[i][0][1] - hashMap[0][0][1]) * aspect, 255, i);
					pivots.add(p);
					points.add(p);
				}
			}
			pivots.sort(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
