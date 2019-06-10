package world;

import java.awt.Graphics;
import geometry3d.Triangle3D;

public class World {

	private Triangle3D[] triangles;

	public World(Triangle3D[] triangles) {
		this.triangles = triangles;
	}

	public void paint(Graphics g) {
		// Paint triangles
		for (Triangle3D i : triangles) {
			i.project().paint(g);
		}
	}
}