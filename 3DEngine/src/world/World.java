package world;

import geometry3d.Camera;
import geometry3d.Point3D;
import geometry3d.Shape3D;
import graphics.ZBuffer;

public class World {

	public Shape3D[] shapes;

	public World(Shape3D[] shapes) {
		this.shapes = shapes;
	}
	public World clone() {
		Shape3D[] cloned = new Shape3D[shapes.length];
		for (int i=0; i<shapes.length; i++ ) {
			cloned[i] = shapes[i].clone();
		}
		return new World(cloned);
	}

	public void paint(ZBuffer buffer, Camera camera) {
		World clone = clone();
		clone.move(camera.getX(), camera.getY(), camera.getZ());
		clone.rotateY(camera.yaw, new Point3D(0,0,0));
		clone.rotateX(camera.pitch, new Point3D(0,0,0));
		// Paint triangles
		for (Shape3D i : clone.shapes) {
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