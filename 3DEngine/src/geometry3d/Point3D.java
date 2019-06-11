package geometry3d;

import engine.MatrixMath;
import graphics.Display;

public class Point3D {

	protected double[][] matrix;

	public Point3D(double[][] matrix) {
		this.matrix = matrix;
	}
	public Point3D(double x, double y, double z) {
		matrix = new double[3][1];
		matrix[0][0] = x;
		matrix[1][0] = y;
		matrix[2][0] = z;
	}

	public Point3D clone() {
		return new Point3D(matrix);
	}

	public Point3D project() {
		return new Point3D(MatrixMath.multiply(matrix, MatrixMath.perspective(matrix[2][0]))).scale(matrix[2][0]);
	}

	private Point3D scale(double zPreserve) {
		int scale = Display.HDWIDTH;
		int border = (Display.HDHEIGHT - scale) / 2;
		int x = (int) (matrix[0][0] * scale) + scale/2;
		int y = (int) -(matrix[1][0] * scale) + scale/2 + border;
		return new Point3D(x, y, zPreserve);
	}

	public double getX() {
		return matrix[0][0];
	}
	public double getY() {
		return matrix[1][0];
	}
	public double getZ() {
		return matrix[2][0];
	}
	
	public void move(double[][] moveMatrix) {
		matrix[0][0] += moveMatrix[0][0];
		matrix[1][0] += moveMatrix[1][0];
		matrix[2][0] += moveMatrix[2][0];
	}
	public void rotateX(double angle, Point3D pivot) {
		move(new double[][] {{-pivot.matrix[0][0]},{-pivot.matrix[1][0]},{-pivot.matrix[2][0]}});
		matrix = MatrixMath.multiply(MatrixMath.rotateX(angle), matrix);
		move(new double[][] {{pivot.matrix[0][0]},{pivot.matrix[1][0]},{pivot.matrix[2][0]}});
	}
	public void rotateY(double angle, Point3D pivot) {
		move(new double[][] {{-pivot.matrix[0][0]},{-pivot.matrix[1][0]},{-pivot.matrix[2][0]}});
		matrix = MatrixMath.multiply(MatrixMath.rotateY(angle), matrix);
		move(new double[][] {{pivot.matrix[0][0]},{pivot.matrix[1][0]},{pivot.matrix[2][0]}});
	}
	public void rotateZ(double angle, Point3D pivot) {
		move(new double[][] {{-pivot.matrix[0][0]},{-pivot.matrix[1][0]},{-pivot.matrix[2][0]}});
		matrix = MatrixMath.multiply(MatrixMath.rotateZ(angle), matrix);
		move(new double[][] {{pivot.matrix[0][0]},{pivot.matrix[1][0]},{pivot.matrix[2][0]}});
	}
}
