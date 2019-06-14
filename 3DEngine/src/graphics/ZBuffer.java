package graphics;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

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
		}
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public void drawTriangle(Triangle3D triangle3D) {
		
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
		Polygon pol = new Polygon(xPoints, yPoints, 3);
		Rectangle bounds = pol.getBounds();
		
		// Validate polygon bounds
		if (!validateImage(bounds, backBuffer)) {
			return;
		}
		
		// Get lighting
		triangle3D.setShade();
		
		// Get triangle image
		BufferedImage secondBuffer = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB);
		triangle2D.paint(secondBuffer.getGraphics(), -bounds.x, -bounds.y);
		
		// Get triangle plane
		Point3D p = triangle2D.points[0];
		Point3D v = triangle2D.getCrossProduct();
		
		// Get image pixels
		int[] colours = ((DataBufferInt) secondBuffer.getRaster().getDataBuffer()).getData();
		
		// All image pixels
		for (int i = 0; i < colours.length; i++) {
			
			// Validate colour
			if (!validateColour(colours[i])) {
				continue;
			}
			
			// Validate pixel bounds
			int x = (i % bounds.width) + bounds.x;
			int y = ((i - (i % bounds.width)) / bounds.width) + bounds.y;
			if (!validatePixel(x, y)) {
				continue;
			}
			
			// Validate depth
			double z = VectorMath.planeIntersectZ(p, v, x, y);
			int index = x + y*backBuffer.getWidth();
			if (!(zBuffer[index] > z)) {
				continue;
			}
			
			// Write to z-buffer and image
			zBuffer[index] = z;
			backBuffer.setRGB(x, y, colours[i]);
		}
	}

	private boolean validateDepth(Triangle3D triangle) {
		// Triangle too close or behind
		double closePlane = 5;
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
	
	private boolean validateColour(int colour) {
		//Alpha not zero
		return ((colour >> 24) & 0xFF) != 0;
	}
	
	private boolean validatePixel(int x, int y) {
		// Pixel off screen
		if (x >= backBuffer.getWidth() || x < 0)
			return false;
		if (y >= backBuffer.getHeight() || y < 0)
			return false;
		return true;
	}

	public void drawBuffer(Graphics g) {
		g.drawImage(backBuffer, 0, 0, null);
	}
}
