package geometry3d;

import java.awt.Color;
import java.awt.Graphics;
import engine.VectorMath;
import graphics.Display;

public class Triangle3D {

	public Point3D[] points = new Point3D[3];
	private Color colour;
	private Color shade;

	public Triangle3D(Point3D[] points, Color colour) {
		this.points = points;
		this.colour = colour;
		this.shade = Color.BLACK;
	}
	public Triangle3D(Point3D[] points, Color colour, Color shade) {
		this.points = points;
		this.colour = colour;
		this.shade = shade;
	}
	public Triangle3D clone() {
		Point3D[] cloned = new Point3D[points.length];
		for (int i=0; i<points.length; i++) {
			cloned[i] = points[i].clone();
		}
		return new Triangle3D(cloned, colour, shade);
	}
	
	public Triangle3D project() {
		Point3D[] points3d = new Point3D[3];
		for (int i=0; i<points.length; i++) {
			points3d[i] = points[i].project();
		}
		return new Triangle3D(points3d, colour, shade);
	}
	

	public void paint(Graphics g, int xOff, int yOff) {
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i=0; i<points.length; i++) {
			xPoints[i] = (int) points[i].matrix[0][0] + xOff;
			yPoints[i] = (int) points[i].matrix[1][0] + yOff;
		}
		g.setColor(shade);
		g.fillPolygon(xPoints, yPoints, points.length);
		g.setColor(Color.BLACK);
		//g.drawPolygon(xPoints, yPoints, points.length);
	}
	
	public void setShade() {
		double sensitivity = 1;
		Point3D normal = getCrossProduct();
		double y = normal.getY()/VectorMath.magnitude(normal);
		double x = normal.getX()/VectorMath.magnitude(normal);
		double f = (y*0.75) + (x*0.25);
		if (f < 0) {
			f = -f;
			f = Math.pow(f, 1/sensitivity);
			f = -f;
		} else {
			f = Math.pow(f, 1/sensitivity);
		}
		double mult = (f+1)/2;
		shade = new Color((int)(colour.getRed()*mult), (int)(colour.getGreen()*mult), (int)(colour.getBlue()*mult));
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
