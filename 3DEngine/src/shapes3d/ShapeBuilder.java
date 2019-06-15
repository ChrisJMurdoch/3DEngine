package shapes3d;

import java.awt.Color;

import geometry3d.Point3D;
import geometry3d.Shape3D;
import geometry3d.Triangle3D;

public class ShapeBuilder {
	
	public static Shape3D buildCube(double x, double y, double z, double width, double height, double depth, Color colour) {
		Point3D ooo = new Point3D(x, y, z);
		Point3D ioo = new Point3D(x+width, y, z);
		Point3D iio = new Point3D(x+width, y+height, z);
		Point3D oio = new Point3D(x, y+height, z);
		Point3D ooi = new Point3D(x, y, z+depth);
		Point3D ioi = new Point3D(x+width, y, z+depth);
		Point3D iii = new Point3D(x+width, y+height, z+depth);
		Point3D oii = new Point3D(x, y+height, z+depth);
		
		//south face
		Triangle3D sbr = new Triangle3D(new Point3D[] {ooo, ioo, iio}, colour) ;
		Triangle3D stl = new Triangle3D(new Point3D[] {iio, oio, ooo}, colour);
		//north face
		Triangle3D nbr = new Triangle3D(new Point3D[] {ooi, iii, ioi}, colour) ;
		Triangle3D ntl = new Triangle3D(new Point3D[] {iii, ooi, oii}, colour);
		//west face
		Triangle3D wbf = new Triangle3D(new Point3D[] {ooo, oii, ooi}, colour) ;
		Triangle3D wtc = new Triangle3D(new Point3D[] {oii, ooo, oio}, colour);
		//east face
		Triangle3D ebf = new Triangle3D(new Point3D[] {ioo, ioi, iii}, colour) ;
		Triangle3D etc = new Triangle3D(new Point3D[] {iii, iio, ioo}, colour);
		//bottom face
		Triangle3D brc = new Triangle3D(new Point3D[] {ooo, ioi, ioo}, colour) ;
		Triangle3D blf = new Triangle3D(new Point3D[] {ioi, ooo, ooi}, colour);
		//top face
		Triangle3D trc = new Triangle3D(new Point3D[] {oio, iio, iii}, colour) ;
		Triangle3D tlf = new Triangle3D(new Point3D[] {iii, oii, oio}, colour);
		
		return new Shape3D(new Triangle3D[] { sbr, stl, nbr, ntl, ebf, etc, wbf, wtc, brc, blf, trc, tlf }, new Point3D(x+width/2, y+height/2, z+depth/2));
	}

	public static Shape3D[] buildShapes1() {
		final Color PRIMARY = Color.LIGHT_GRAY;
		final Color SECONDARY = Color.DARK_GRAY;
		final Color TERTIARY = Color.CYAN;
		
		Shape3D[] shapes = {
			ShapeBuilder.buildCube(-1, -1, 11.5, 2, 2, 2, PRIMARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 9, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 9, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 9, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 9, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 11, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 11, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 11, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 11, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 13, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 13, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 13, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 13, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 15, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 15, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 15, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 15, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 17, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 17, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 17, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 17, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 19, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 19, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 19, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 19, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 21, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 21, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 21, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 21, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-3.5, -2, 23, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(-1.5, -2, 23, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(0.5, -2, 23, 1, 1, 1, SECONDARY),
			ShapeBuilder.buildCube(2.5, -2, 23, 1, 1, 1, SECONDARY),
			
			ShapeBuilder.buildCube(-7, -3, 23.5, 14, 7, 1, TERTIARY),
			
		};
		
		return shapes;
	}
	
	public static Shape3D[] buildShapes2() {
		final Color PRIMARY = Color.LIGHT_GRAY;
		final Color SECONDARY = Color.DARK_GRAY;
		final Color TERTIARY = Color.CYAN;
		
		Shape3D[] shapes = {
			ShapeBuilder.buildCube(-1, -1, 11.5, 2, 2, 2, PRIMARY),
			
			ShapeBuilder.buildCube(-2, -2, 10.5, 4, 0.6, 4, SECONDARY),
			ShapeBuilder.buildCube(-2.5, -2.6, 10, 5, 0.6, 5, SECONDARY),
		};
		
		return shapes;
	}
}
