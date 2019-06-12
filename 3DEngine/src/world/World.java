package world;

import geometry3d.Point3D;
import geometry3d.Shape3D;
import graphics.ZBuffer;

public class World {

	public Shape3D[] shapes;

	public World(Shape3D[] shapes) {
		this.shapes = shapes;
	}

	public void paint(ZBuffer buffer) {
		// Paint triangles
		for (Shape3D i : shapes) {
			i.paint(buffer);
		}
	}
	
	public void move(double x, double y, double z) {
		move(new double[][] { {x}, {y}, {z}} );
	}
	public void move(double[][] moveMatrix) {
		for (Shape3D i : shapes) {
			i.move(moveMatrix);
		}
	}
	public void rotateX(double angle, Point3D pivot) {
		for (Shape3D i : shapes) {
			i.rotateX(angle, pivot);
		}
	}
	public void rotateY(double angle, Point3D pivot) {
		for (Shape3D i : shapes) {
			i.rotateY(angle, pivot);
		}
	}
	public void rotateZ(double angle, Point3D pivot) {
		for (Shape3D i : shapes) {
			i.rotateZ(angle, pivot);
		}
	}
}