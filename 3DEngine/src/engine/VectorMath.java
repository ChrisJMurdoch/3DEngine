package engine;

import geometry3d.Point3D;

public class VectorMath {

	public static double magnitude(Point3D aVec) {
		double mag = Math.sqrt(Math.pow(aVec.getX(), 2) + Math.pow(aVec.getY(), 2) + Math.pow(aVec.getZ(), 2));
		return mag;
	}

	public static Point3D crossProduct(Point3D aVec, Point3D bVec) {
		double x = aVec.getY() * bVec.getZ() - aVec.getZ() * bVec.getY();
		double y = aVec.getZ() * bVec.getX() - aVec.getX() * bVec.getZ();
		double z = aVec.getX() * bVec.getY() - aVec.getY() * bVec.getX();
		Point3D crossProduct = new Point3D(x, y, z);
		return crossProduct;
	}

	public static double dotProduct(Point3D aVec, Point3D bVec) {
		double x = aVec.getX() * bVec.getX();
		double y = aVec.getY() * bVec.getY();
		double z = aVec.getZ() * bVec.getZ();
		return x + y + z;
	}

	public static Point3D add(Point3D aVec, Point3D bVec) {
		double x = aVec.getX() + bVec.getX();
		double y = aVec.getY() + bVec.getY();
		double z = aVec.getZ() + bVec.getZ();
		return new Point3D(x, y, z);
	}

	public static Point3D subtract(Point3D aVec, Point3D bVec) {
		double x = aVec.getX() - bVec.getX();
		double y = aVec.getY() - bVec.getY();
		double z = aVec.getZ() - bVec.getZ();
		return new Point3D(x, y, z);
	}

	public static double planeIntersectZ(Point3D planePoint, Point3D planeNormal, double x, double y) {
		return (planePoint.getZ() - (((planeNormal.getX() * (x - planePoint.getX())) + (planeNormal.getY() * (y - planePoint.getY()))) / (planeNormal.getZ())));
	}
}
