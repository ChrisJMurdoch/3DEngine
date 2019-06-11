package graphics;

import java.awt.Graphics;
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

	public void drawTriangle(Triangle3D t) {
		// create triangle image
		BufferedImage triangleImage = new BufferedImage(backBuffer.getWidth(), backBuffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics bGraphics = triangleImage.getGraphics();
		Triangle3D triangle = t.project();
		triangle.paint(bGraphics);
		// prepare
		Point3D p = triangle.points[0];
		Point3D v = triangle.getCrossProduct();
		// draw onto backBuffer
		int[] colours = ((DataBufferInt) triangleImage.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < colours.length; i++) {
			int a = (colours[i] >> 24) & 0xFF;
			if (a > 0) {
				double z = VectorMath.planeIntersectZ(p, v, i % backBuffer.getWidth(), (i - i % backBuffer.getWidth()) / backBuffer.getWidth());
				if (zBuffer[i] > z) {
					zBuffer[i] = z;
					backBuffer.setRGB(i % backBuffer.getWidth(), (i - i % backBuffer.getWidth()) / backBuffer.getWidth(), colours[i]);
				}
			}
		}
	}

	public void drawBuffer(Graphics g) {
		g.drawImage(backBuffer, 0, 0, null);
	}
}
