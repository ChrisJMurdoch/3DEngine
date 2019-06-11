package world;

import engine.VectorMath;
import geometry3d.Point3D;
import geometry3d.Triangle3D;
import graphics.ZBuffer;

public class World {

	private Triangle3D[] triangles;

	public World(Triangle3D[] triangles) {
		this.triangles = triangles;
	}

	public void paint(ZBuffer buffer) {
		// Paint triangles
		for (Triangle3D i : triangles) {
			if (VectorMath.dotProduct(i.getCrossProduct(), i.points[0]) < 0) {
				buffer.drawTriangle(i);
			}
		}
	}
	
	public void move(double x, double y, double z) {
		move(new double[][] { {x}, {y}, {z}} );
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