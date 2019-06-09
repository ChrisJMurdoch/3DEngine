package geometry2d;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle2D {
	
	private Point2D[] points = new Point2D[3];
	private Color colour;
	
	public Triangle2D(Point2D[] points, Color colour) {
		this.points = points;
		this.colour = colour;
	}
	
	public void paint(Graphics g) {
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i=0; i<points.length; i++) {
			xPoints[i] = (int) points[i].x;
			yPoints[i] = (int) points[i].y;
		}
		g.setColor(colour);
		g.fillPolygon(xPoints, yPoints, points.length);
		g.setColor(Color.WHITE);
		g.drawPolygon(xPoints, yPoints, points.length);
	}
}
