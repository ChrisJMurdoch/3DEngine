package geometry3d;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle3D {

	private Point3D[] points = new Point3D[3];
	private Color colour;
	
	public Triangle3D(Point3D[] points, Color colour) {
		this.points = points;
		this.colour = colour;
	}
	
	public Triangle3D project() {
		Point3D[] points3d = new Point3D[3];
		for (int i=0; i<points.length; i++) {
			points3d[i] = points[i].project();
		}
		return new Triangle3D(points3d, colour);
	}
	

	public void paint(Graphics g) {
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i=0; i<points.length; i++) {
			xPoints[i] = (int) points[i].matrix[0][0];
			yPoints[i] = (int) points[i].matrix[1][0];
		}
		g.setColor(colour);
		g.fillPolygon(xPoints, yPoints, points.length);
		g.setColor(Color.WHITE);
		g.drawPolygon(xPoints, yPoints, points.length);
	}
}
