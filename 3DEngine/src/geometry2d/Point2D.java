package geometry2d;

import java.awt.Color;
import java.awt.Graphics;

public class Point2D {
	
	private double x, y;
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, 0, 0);
	}
}
