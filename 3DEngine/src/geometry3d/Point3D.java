package geometry3d;

import engine.MatrixMath;
import graphics.Display;

public class Point3D {

	protected double[][] matrix;

	public Point3D(double[][] matrix) {
		this.matrix = matrix;
	}

	public Point3D clone() {
		return new Point3D(matrix);
	}

	public Point3D project() {
		return new Point3D(MatrixMath.multiply(matrix, MatrixMath.perspective(matrix[2][0]))).scale();
	}

	private Point3D scale() {
		int x = (int) (matrix[0][0] * Display.HDWIDTH) + Display.HDWIDTH / 2;
		int y = (int) -(matrix[1][0] * Display.HDHEIGHT) + Display.HDHEIGHT / 2;
		return new Point3D(new double[][] { { x }, { y }, { matrix[2][0] } });
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
