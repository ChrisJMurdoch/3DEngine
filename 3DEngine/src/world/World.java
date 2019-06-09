package world;

import java.awt.Graphics;
import geometry2d.Point2D;

public class World {
	
	private Point2D[] point2Ds;
	
	public World(Point2D[] point2Ds) {
		this.point2Ds = point2Ds;
	}
	
	public void paint(Graphics g) {
		//Paint points
		for (Point2D i : point2Ds) {
			i.paint(g);
		}
	}
}