package utility;

public class MatrixMath {

	public static final double[][] ORTHOGRAPHIC = {
		{ 1, 0, 0 },
		{ 0, 1, 0 }
	};

	public static double[][] perspective (double z) {
		return new double[][] {
			{ 1/z, 0, 0 },
			{ 0, 1/z, 0 }
		};
	}

	public static double[][] rotateX(double angle) {
		return new double[][] {
			{ 1, 0, 0 },
			{ 0, (double) Math.cos(angle), (double) -Math.sin(angle) },
			{ 0, (double) Math.sin(angle), (double) Math.cos(angle) }
		};
	}
	
	public static double[][] rotateY(double angle) {
		return new double[][] {
			{ (double) Math.cos(angle), 0, (double) -Math.sin(angle) },
			{ 0, 1, 0 },
			{ (double) Math.sin(angle), 0, (double) Math.cos(angle)}
		};
	}
	
	public static double[][] rotateZ(double angle) {
		return new double[][] {
			{ (double) Math.cos(angle), (double) -Math.sin(angle), 0},
			{ (double) Math.sin(angle), (double) Math.cos(angle), 0},
			{ 0, 0, 1}
		};
	}
	
	public static double[][] multiply(double[][] a, double[][] b) {
		double[][] product = new double[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {// for each row in a
			for (int j = 0; j < b[0].length; j++) {// for each column in b
				double sum = 0;
				for (int k = 0; k < a[0].length; k++) {// for each (column in a) and (row in b)
					sum += a[i][k] * b[k][j];
				}
				product[i][j] = sum;
			}
		}
		return product;
	}
}