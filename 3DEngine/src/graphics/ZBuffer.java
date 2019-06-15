package graphics;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;

import engine.VectorMath;
import geometry3d.Point3D;
import geometry3d.Triangle3D;

public class ZBuffer {

	private double[] zBuffer;
	private BufferedImage backBuffer;

	public void createImage(int width, int height) {
		zBuffer = new double[height * width];
		for (int i = 0; i < zBuffer.length; i++) {
			zBuffer[i] = 10000;
			//pixels[i] = 0;
		}
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public void drawTriangle(Triangle3D triangle3D) {
		
		// Get lighting
		triangle3D.setShade();
		
		// Project triangle
		Triangle3D triangle2D = triangle3D.project();
		
		// Validate projected depth
		if (!validateDepth(triangle2D)) {
			return;
		}
		
		// Get bounds
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		for (int i = 0; i < triangle2D.points.length; i++) {
			xPoints[i] = (int) triangle2D.points[i].getX();
			yPoints[i] = (int) triangle2D.points[i].getY();
		}
		Rectangle bounds = clipToScreen(new Polygon(xPoints, yPoints, 3).getBounds(), backBuffer);
		
		// Validate bounds
		if (!validateImage(bounds, backBuffer)) {
			return;
		}
		
		// Get triangle image
		BufferedImage secondBuffer = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
		triangle2D.paint(secondBuffer.getGraphics(), -bounds.x, -bounds.y);
		
		// Get triangle plane
		Point3D p = triangle2D.points[0];
		Point3D v = triangle2D.getCrossProduct();
		
		// Get image pixels
		int[] colours = ((DataBufferInt) secondBuffer.getRaster().getDataBuffer()).getData();
		
		// Depth calculation prep
		double a = (p.getZ()*v.getZ())+(p.getY()*v.getY())+(p.getX()*v.getX());
		// All image pixels
		int i = 0;
		for (int y = bounds.y; y < bounds.y+bounds.height; y++) {
			for (int x = bounds.x; x < bounds.x+bounds.width; x++, i++) {
				
				// Validate colour
				if (colours[i] == 0)
					continue;
				
				// Validate depth
				double z = (a-(v.getX()*x)-(v.getY()*y))/v.getZ();
				int index = x + (y*backBuffer.getWidth());
				if ((zBuffer[index] <= z)) {
					continue;
				}
				
				// Write to z-buffer and image
				zBuffer[index] = z;
				backBuffer.setRGB(x, y, colours[i]);
			}
		}
	}

	private boolean validateDepth(Triangle3D triangle) {
		// Triangle too close or behind
		double closePlane = 1;
		if (triangle.points[0].getZ() < closePlane || triangle.points[1].getZ() < closePlane || triangle.points[2].getZ() < closePlane)
			return false;
		return true;
	}
	
	private boolean validateImage(Rectangle bounds, BufferedImage screen) {
		// Image fully off screen
		if (bounds.x > screen.getWidth() || (bounds.x+bounds.width) < 0 )
			return false;
		if (bounds.y > screen.getHeight() || (bounds.y+bounds.height) < 0 )
			return false;
		// Image too thin
		if (bounds.width <= 0 || bounds.height <= 0)
			return false;
		return true;
	}
	
	private Rectangle clipToScreen(Rectangle in, BufferedImage screen) {
		// X
		int x = (in.x < 0) ? x = 0 : in.x;
		// Y
		int y = (in.y < 0) ? y = 0 : in.y;
		// Width
		int w = (x+in.width >= backBuffer.getWidth()-1) ? backBuffer.getWidth()-1-x : in.width;
		// Height
		int h = (y+in.height >= backBuffer.getHeight()-1) ? backBuffer.getHeight()-1-y : in.height;
		return new Rectangle(x, y, w, h);
	}

	public void drawBuffer(Graphics g) {
		g.drawImage(backBuffer, 0, 0, null);
	}
}
