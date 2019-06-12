package geometry3d;

import java.awt.Color;
import java.awt.Graphics;
import engine.VectorMath;
import graphics.Display;

public class Triangle3D {

	public Point3D[] points = new Point3D[3];
	private Color colour;
	
	public Triangle3D(Point3D[] points, Color colour) {
		this.points = points;
		this.colour = colour;
	}
	public Triangle3D clone() {
		Point3D[] cloned = new Point3D[points.length];
		for (int i=0; i<points.length; i++) {
			cloned[i] = points[i].clone();
		}
		return new Triangle3D(cloned, colour);
	}
	
	public Triangle3D project() {
		Point3D[] points3d = new Point3D[3];
		for (int i=0; i<points.length; i++) {
			points3d[i] = points[i].project();
		}
		return new Triangle3D(points3d, colour);
	}
	

	public void paint(Graphics g, int xOff, int yOff) {
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i=0; i<points.length; i++) {
			xPoints[i] = (int) points[i].matrix[0][0] + xOff;
			yPoints[i] = (int) points[i].matrix[1][0] + yOff;
		}
		g.setColor(colour);
		g.fillPolygon(xPoints, yPoints, points.length);
		g.setColor(Color.BLACK);
		//g.drawPolygon(xPoints, yPoints, points.length);
	}
	
	public void setShade() {
		double sensitivity = 1;
		int min = 75;
		int max = 175;
		Point3D normal = getCrossProduct();
		double y = normal.getY()/VectorMath.magnitude(normal);
		if (y < 0) {
			y = -y;
			y = Math.pow(y, 1/sensitivity);
			y = -y;
		} else {
			y = Math.pow(y, 1/sensitivity);
		}
		double mult = (y+1)/2;
		int shade = min + (int)(mult * (max-min));
		colour = new Color(shade, shade, shade);
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
