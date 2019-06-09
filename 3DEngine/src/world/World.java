package world;

import java.awt.Graphics;
import geometry2d.Triangle2D;

public class World {

	private Triangle2D[] triangles;

	public World(Triangle2D[] triangles) {
		this.triangles = triangles;
	}

	public void paint(Graphics g) {
		// Paint triangles
		for (Triangle2D i : triangles) {
			i.paint(g);
		}
	}
}