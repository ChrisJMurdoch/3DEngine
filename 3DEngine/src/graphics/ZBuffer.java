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
	private BufferedImage secondBuffer;

	public void createImage(int width, int height) {
		zBuffer = new double[height * width];
		for (int i = 0; i < zBuffer.length; i++) {
			zBuffer[i] = 10000;
		}
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public void drawTriangle(Triangle3D triangle3D) {
		if (triangle3D.points[0].getZ() < 5 || triangle3D.points[1].getZ() < 5 || triangle3D.points[2].getZ() < 5) {
			return;
		}
		// create triangle image
		triangle3D.setShade();
		Triangle3D triangle2D = triangle3D.project();
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		for (int i = 0; i < triangle2D.points.length; i++) {
			xPoints[i] = (int) triangle2D.points[i].getX();
			yPoints[i] = (int) triangle2D.points[i].getY();
		}
		Polygon pol = new Polygon(xPoints, yPoints, 3);
		Rectangle bounds = pol.getBounds();
		try {
			secondBuffer = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB);
			Graphics tGraphics = secondBuffer.getGraphics();
			triangle2D.paint(tGraphics, -bounds.x, -bounds.y);
			// prepare
			Point3D p = triangle2D.points[0];
			Point3D v = triangle2D.getCrossProduct();
			// draw onto backBuffer
			int[] colours = ((DataBufferInt) secondBuffer.getRaster().getDataBuffer()).getData();
			for (int i = 0; i < colours.length; i++) {
				if (outOfBounds(i, backBuffer.getWidth(), backBuffer.getHeight(), bounds.width, bounds.x, bounds.y)) {
					continue;
				}
				if (((colours[i] >> 24) & 0xFF) > 0) {
					double z = VectorMath.planeIntersectZ(p, v, (i % bounds.width) + bounds.x, ((i - (i % bounds.width)) / bounds.width) + bounds.y);
					int index = getIndex(secondBuffer.getWidth(), backBuffer.getWidth(), i, bounds.x, bounds.y);
					if (zBuffer[index] > z) {
						zBuffer[index] = z;
						backBuffer.setRGB((i % bounds.width) + bounds.x, ((i - (i % bounds.width)) / bounds.width) + bounds.y, colours[i]);
					}
				}
			}
			secondBuffer = null;
		} catch (Exception e) {
			secondBuffer = null;
			return;
		}
	}
	
	private boolean outOfBounds(int index, int width, int height, int subWidth, int subX, int subY) {
		if ((index%subWidth)+subX >= width)
			return true;
		if ((index%subWidth)+subX < 0)
			return true;
		if ((index - (index%subWidth))/subWidth + subY >= height)
			return true;
		if ((index - (index%subWidth))/subWidth + subY < 0)
			return true;
		return false;
	}

	private int getIndex(int widthA, int widthB, int indexA, int offsetAx, int offsetAy) {
		int x = indexA % widthA;
		int y = (indexA - (indexA % widthA)) / widthA;
		return (x + offsetAx) + ((y + offsetAy) * widthB);
	}

	public void drawBuffer(Graphics g) {
		g.drawImage(backBuffer, 0, 0, null);
	}
}
