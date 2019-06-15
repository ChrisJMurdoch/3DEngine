package geometry3d;

import engine.VectorMath;
import graphics.ZBuffer;

public class Shape3D {

	private Triangle3D[] triangles;
	private Point3D centre;

	public Shape3D(Triangle3D[] triangles, Point3D centre) {
		this.triangles = new Triangle3D[triangles.length];
		for (int i=0; i<triangles.length; i++) {
			this.triangles[i] = triangles[i].clone();
		}
		this.centre = centre.clone();
	}
	public Shape3D clone() {
		Triangle3D[] cloned = new Triangle3D[triangles.length];
		for (int i=0; i<triangles.length; i++) {
			cloned[i] = triangles[i].clone();
		}
		return new Shape3D(cloned, centre.clone());
	}

	public void paint(ZBuffer buffer) {
		// Paint triangles
		for (Triangle3D i : triangles) {
			if (VectorMath.dotProduct(i.getCrossProduct(), i.points[0]) < 0) {
				buffer.drawTriangle(i);
			}
		}
	}
	
	//Movement
	
	public void move(double x, double y, double z) {
		move(new double[][] { {x}, {y}, {z}} );
	}
	public void move(double[][] moveMatrix) {
		for (Triangle3D i : triangles) {
			i.move(moveMatrix);
		}
		centre.move(moveMatrix);
	}
	
	//Rotation round given pivot
	
	public void rotateX(double angle, Point3D pivot) {
		for (Triangle3D i : triangles) {
			i.rotateX(angle, pivot);
		}
		this.centre.rotateX(angle, pivot);
	}
	public void rotateY(double angle, Point3D pivot) {
		for (Triangle3D i : triangles) {
			i.rotateY(angle, pivot);
		}
		this.centre.rotateY(angle, pivot);
	}
	public void rotateZ(double angle, Point3D pivot) {
		for (Triangle3D i : triangles) {
			i.rotateZ(angle, pivot);
		}
		this.centre.rotateZ(angle, pivot);
	}
	
	//Rotation round centre
	
	public void rotateX(double angle) {
		for (Triangle3D i : triangles) {
			i.rotateX(angle, centre);
		}
	}
	public void rotateY(double angle) {
		for (Triangle3D i : triangles) {
			i.rotateY(angle, centre);
		}
	}
	public void rotateZ(double angle) {
		for (Triangle3D i : triangles) {
			i.rotateZ(angle, centre);
		}
	}
}
