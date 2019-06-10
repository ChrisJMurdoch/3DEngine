package world;

import java.awt.Graphics;

import geometry3d.Point3D;
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
	
	public void move(double[][] moveMatrix) {
		for (Triangle3D i : triangles) {
			i.move(moveMatrix);
		}
	}
	public void rotateX(double angle, Point3D pivot) {
		for (Triangle3D i : triangles) {
			i.rotateX(angle, pivot);
		}
	}
	public void rotateY(double angle, Point3D pivot) {
		for (Triangle3D i : triangles) {
			i.rotateY(angle, pivot);
		}
	}
	public void rotateZ(double angle, Point3D pivot) {
		for (Triangle3D i : triangles) {
			i.rotateZ(angle, pivot);
		}
	}
}