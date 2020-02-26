import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Input {
	public static void test(ArrayList<Point> points){
		Point p = new Point(-100,500,0,0);
		Point p2 = new Point(-100,500,100,0);
		Point p3 = new Point(100,500,0,0);
		Point p4 = new Point(100,500,100,0);
		
		Point p5 = new Point(-100,1000,0,0);
		Point p6 = new Point(-100,1000,100,0);
		Point p7 = new Point(100,1000,0,0);
		Point p8 = new Point(100,1000,100,0);
		
		
		points.add(p);
		points.add(p2);
		points.add(p3);
		points.add(p4);

		points.add(p5);
		points.add(p6);
		points.add(p7);
		points.add(p8);
	}
	public static void test2(ArrayList<Point> points){
		File img = new File("jisoo3.jpg");
		
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(0, 0, buff.getWidth(), buff.getHeight(), null, 0, buff.getWidth());
			
			for(int i=0;i<array.length;i++){
				Screen.pixels[i%buff.getWidth()+i/buff.getWidth()*1000]=array[i];
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public static void test3(ArrayList<Point> points){
		File img = new File("jisoo3.jpg");
		
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(70, 16, 100, 150, null, 0, 100);
			
			for(int i=0;i<array.length;i++){
				points.add(new Point(i%100, 200 , -i/100, array[i]));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public static void test4(ArrayList<Point> points){
		File img = new File("jisoo3.jpg");
		
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(70, 16, 100, 150, null, 0, 100);
			
			for(int i=0;i<array.length;i++){
				points.add(new Point(i%100, (int)((i%100-50)*(i%100-50)/100.0) + 450 , -i/100, array[i]));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public static void test5(ArrayList<Point> points){
		File img = new File("jisoo3.jpg");
		
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(70, 16, 100, 150, null, 0, 100);
			
			for(int i=0;i<array.length;i++){
				points.add(new Point(i%100, (int)((i%100-50)*(i%100-50)/100.0) + 450 , -i/100, array[i]));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public static void test6(ArrayList<Point> points){
		File img = new File("jisoo3.jpg");
		
		try {
			BufferedImage buff = ImageIO.read(img);
			int[] array = buff.getRGB(70, 16, 100, 150, null, 0, 100);
			
			for(int i=0;i<array.length;i++){
				points.add(new Point(i%100,  + 450 , -i/100, array[i]));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public static void test7(ArrayList<Point> points){
			for(int i=0;i<100*100;i++){
				points.add(new Point(i%100,  + 450 , -i/100, 423525526));
			}	
	}
	public static void test8(ArrayList<Point> points){
		double x;
		double y;
		double z;
		int color;
		try {
            BufferedReader fin = new BufferedReader(new FileReader("Test.txt"));
            while(fin.readLine()!=null){
            	
            	x = Double.parseDouble(fin.readLine());
            	y = Double.parseDouble(fin.readLine());
            	z = Double.parseDouble(fin.readLine());
            	color = Integer.parseInt(fin.readLine());
            	points.add(new Point(x,y,z,color));
            }
            fin.close();
        }catch (IOException e) {
            System.out.println("File operation in isCorrect failed.");
        } 
	}
	public static void test9(ArrayList<Point> points, World w){
		Point p1 = new Point(0,510,52,33333);
		Point p2 = new Point(3,510,52,33333);
		Point p3 = new Point(10,513,52,33333);
		Point p4 = new Point(13,514,57,33333);
		Point p5 = new Point(3,500,57,33333);
		Point p6 = new Point(0,500,57,33333);
		Point p7 = new Point(0,515,89,33333);
		Point p8 = new Point(2,515,89,33333);
		Point p9 = new Point(4,516,89,33333);
	
		//nose
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p7);
		points.add(p8);
		points.add(p9);

		
		Utilities.drawQuad(points, p1.position, p2.position, p6.position, p5.position, 6815115);
		Utilities.drawTriangle(points, p3.position, p2.position, p5.position, 36594549);
		Utilities.drawQuad(points, p6.position, p5.position, p7.position, p8.position, 3346243);
		Utilities.drawTriangle(points, p5.position, p4.position, p8.position, 3151563);
		Utilities.drawTriangle(points, p3.position, p4.position, p5.position, 3665963);
		Utilities.drawTriangle(points, p8.position, p9.position, p4.position, 5251643);
		
		//uppercheek
		Point p10 = new Point(42,520,78,33333);
		Utilities.drawTriangle(points, p9.position, p4.position, p10.position, 31414);
		
		//headsidetop
		Point p11 = new Point(11,512,103,33333);
		Point p12 = new Point(21,515,97,33333);
		Point p13 = new Point(39,522,96,33333);
		Point p14 = new Point(48,539,102,33333);
		Point p15 = new Point(48,536,73,33333);
		Point p16 = new Point(0,511,103,33333);

		//nose
		points.add(p11);
		points.add(p12);
		points.add(p13);
		points.add(p14);
		points.add(p15);
		points.add(p16);

		Utilities.drawTriangle(points, p10.position, p9.position, p12.position, 3536);
		Utilities.drawTriangle(points, p9.position, p11.position, p12.position, 31769);
		Utilities.drawTriangle(points, p10.position, p13.position, p12.position, 3694649);
		Utilities.drawQuad(points, p10.position, p13.position, p14.position,p15.position, 314582);
		Utilities.drawQuad(points, p16.position, p7.position, p11.position,p9.position,236414);
		
		//around, above, and lips
		Point p17 = new Point(0,512,40,33333);
		Point p18 = new Point(5,509,42,33333);
		Point p19 = new Point(15,518,34,33333);
		Point p20 = new Point(0,515,34,33333);
		Point p21 = new Point(0,513,33,33333);
		Point p22 = new Point(0,513,33,33333);
		
		Point p23 = new Point(0,511,28,33333);
		Point p24 = new Point(8,511,28,33333);
		Point p25 = new Point(0,516,21,33333);
		Point p26 = new Point(8,515,23,33333);

		
		Utilities.drawQuad(points, p1.position, p2.position, p17.position,p18.position, 69696);
		Utilities.drawQuad(points, p2.position, p3.position, p18.position,p19.position, 4646);
		Utilities.drawTriangle(points, p17.position, p18.position, p20.position, 7968689);
		Utilities.drawTriangle(points, p18.position, p20.position,p19.position, 7664722);
		//Utilities.drawQuad(points, p20.position, p19.position, p21.position,p22.position, 9365826);
		
		Utilities.drawQuad(points, p20.position, p22.position, p23.position,p24.position, 6064694);
		Utilities.drawQuad(points, p23.position, p24.position, p25.position,p26.position, 546);
		Utilities.drawTriangle(points, p22.position, p24.position,p19.position, 65458388);
		Utilities.drawTriangle(points, p24.position, p26.position,p19.position, 679690);
		
		//more cheeks
		Point p27 = new Point(33,527,28,33333);
		
		Utilities.drawTriangle(points, p10.position, p3.position,p19.position, 5859);
		
		Utilities.drawTriangle(points, p19.position, p10.position,p27.position, 35);
		Utilities.drawTriangle(points, p15.position, p10.position,p27.position, 58474);
		
		//side
		Point p28 = new Point(50,556,73,33333);
		Point p29 = new Point(45,564,47,33333);
		Point p30 = new Point(50,556,102, 2222);
		
		Utilities.drawQuad(points, p15.position, p28.position, p27.position,p29.position, 243425);
		Utilities.drawQuad(points, p15.position, p28.position, p14.position,p30.position, 345267);
		Utilities.drawTriangle(points, p27.position, p19.position,p26.position, 35);
		
		//chin
		Point p31 = new Point(0,516,5,33333);
		Point p32 = new Point(24,524,15,33333);
		Point p33 = new Point(0,524,0, 2222);
		Point p34 = new Point(0,518,21,33333);
		Point p35 = new Point(8,517,23,33333);
		
	
		Utilities.drawTriangle(points, p31.position, p32.position,p33.position, 335244);
		Utilities.drawQuad(points, p26.position, p25.position, p34.position,p35.position, 26662666);
		Utilities.drawQuad(points, p34.position, p35.position, p31.position,p32.position, 2362666);
		Utilities.drawTriangle(points, p27.position, p29.position,p32.position, 2636266);
		Utilities.drawTriangle(points, p27.position, p35.position,p32.position, 263626256);
		
		//forehead
		Point p36 = new Point(0,513,136,33333);
		Point p37 = new Point(21,515,130,33333);
		Point p38 = new Point(45,530,125,33333);
		
		
		
		
		Utilities.drawTriangle(points, p16.position, p36.position,p11.position,346363266);
		Utilities.drawTriangle(points, p12.position, p36.position,p11.position,255*256*256+425);
		Utilities.drawTriangle(points, p12.position, p36.position,p37.position,255*256*256+45234365);
		Utilities.drawTriangle(points, p37.position, p38.position,p12.position,32525);
		Utilities.drawTriangle(points, p13.position, p38.position,p12.position,325252424);
		Utilities.drawTriangle(points, p13.position, p38.position,p14.position,3242424);
		Utilities.drawTriangle(points, p30.position, p38.position,p14.position,3283424);
		
		Utilities.mirror(w);
		Utilities.map(points, "jisoo3Small.jpg", 0, 0, -50, 165, 1);	
	}
	public static void test10(World w){
		//Utilities.generateGeometry2(w.points ,w.pivots, "IUPivotsFront.png", "IUPivotsSide.png", 700, 750, 0, 0, 1/7.0);
		Utilities.generateGeometry2(w.points ,w.pivots, "Front.png", "Side.png", 1900, 2500, 0, 0, 1/7.0);
		
	}
	
	
	
	
}
