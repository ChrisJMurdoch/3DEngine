package geometry3d;

import java.awt.Color;
import java.awt.Graphics;
import engine.VectorMath;

public class Triangle3D {

	public Point3D[] points = new Point3D[3];
	private Color colour;
	
	public Triangle3D(Point3D[] points, Color colour) {
		this.points = points;
		this.colour = colour;
	}
	
	public Triangle3D project() {
		Point3D[] points3d = new Point3D[3];
		for (int i=0; i<points.length; i++) {
			points3d[i] = points[i].project();
		}
		return new Triangle3D(points3d, colour);
	}
	

	public void paint(Graphics g) {
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i=0; i<points.length; i++) {
			xPoints[i] = (int) points[i].matrix[0][0];
			yPoints[i] = (int) points[i].matrix[1][0];
		}
		g.setColor(colour);
		g.fillPolygon(xPoints, yPoints, points.length);
		g.setColor(Color.WHITE);
		//g.drawPolygon(xPoints, yPoints, points.length);
	}

	public Point3D getCrossProduct() {
		Point3D aVec = VectorMath.subtract(points[0], points[2]);
		Point3D bVec = VectorMath.subtract(points[0], points[1]);
		return VectorMath.crossProduct(aVec, bVec);
	}
	
	public void move(double[][] moveMatrix) {
		for (Point3D i : points) {
			i.move(moveMatrix);
		}
	}
	public void rotateX(double angle, Point3D pivot) {
		for (Point3D i : points) {
			i.rotateX(angle, pivot);
		}
	}
	public void rotateY(double angle, Point3D pivot) {
		for (Point3D i : points) {
			i.rotateY(angle, pivot);
		}
	}
	public void rotateZ(double angle, Point3D pivot) {
		for (Point3D i : points) {
			i.rotateZ(angle, pivot);
		}
	}
}
