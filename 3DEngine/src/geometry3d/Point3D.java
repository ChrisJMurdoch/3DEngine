package geometry3d;

import graphics.Display;
import utility.MatrixMath;

public class Point3D {

	protected double[][] matrix = new double[3][1];
	
	public Point3D(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public Point3D project() {
		return new Point3D(MatrixMath.multiply(matrix, MatrixMath.perspective(matrix[2][0]))).scale();
	}
	
	private Point3D scale() {
		int x = (int) (matrix[0][0] * Display.HDWIDTH) + Display.HDWIDTH / 2;
		int y = (int) -(matrix[1][0] * Display.HDHEIGHT) + Display.HDHEIGHT / 2;
		return new Point3D(new double[][] { { x }, { y }, {matrix[2][0]} });
	}
}
