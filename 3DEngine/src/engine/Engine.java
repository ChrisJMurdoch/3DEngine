package engine;

import java.awt.Color;
import geometry3d.Point3D;
import geometry3d.Triangle3D;
import graphics.Display;
import inputs.KL;
import inputs.MML;
import world.World;

public class Engine {

	private World world;
	private Display display;
	private KL keyboard;
	private MML mouse;

	private Point3D pivot = new Point3D(0,0,13);

	private Engine() {
		
		Point3D[] points1 = { new Point3D(-1,1,14), new Point3D(1,-1,14), new Point3D(-1,-1,14), };
		Point3D[] points2 = { new Point3D(-1,1,14), new Point3D(1,1,14), new Point3D(1,-1,14), };
		Point3D[] points3 = { new Point3D(-1,1,12), new Point3D(-1,-1,12), new Point3D(1,-1,12), };
		Point3D[] points4 = { new Point3D(-1,1,12), new Point3D(1,-1,12), new Point3D(1,1,12), };
		Point3D[] points5 = { new Point3D(1,1,12), new Point3D(1,-1,12), new Point3D(1,-1,14), };
		Point3D[] points6 = { new Point3D(1,1,12), new Point3D(1,-1,14), new Point3D(1,1,14), };
		Point3D[] points7 = { new Point3D(-1,1,12), new Point3D(-1,-1,14), new Point3D(-1,-1,12), };
		Point3D[] points8 = { new Point3D(-1,1,12), new Point3D(-1,1,14), new Point3D(-1,-1,14), };
		Point3D[] points9 = { new Point3D(-1,1,14), new Point3D(-1,1,12), new Point3D(1,1,14), };
		Point3D[] points10 = { new Point3D(1,1,12), new Point3D(1,1,14), new Point3D(-1,1,12), };
		Point3D[] points11 = { new Point3D(-1,-1,14), new Point3D(1,-1,14), new Point3D(-1,-1,12), };
		Point3D[] points12 = { new Point3D(1,-1,12), new Point3D(-1,-1,12), new Point3D(1,-1,14), };
		
		Triangle3D[] triangles = {
			new Triangle3D(points1, Color.ORANGE),
			new Triangle3D(points2, Color.ORANGE),
			new Triangle3D(points3, Color.BLUE),
			new Triangle3D(points4, Color.BLUE),
			new Triangle3D(points5, Color.GREEN),
			new Triangle3D(points6, Color.GREEN),
			new Triangle3D(points7, Color.MAGENTA),
			new Triangle3D(points8, Color.MAGENTA),
			new Triangle3D(points9, Color.CYAN),
			new Triangle3D(points10, Color.CYAN),
			new Triangle3D(points11, Color.RED),
			new Triangle3D(points12, Color.RED),
				
		};

		world = new World(triangles);
		display = new Display(world);
		display.addKeyListener(keyboard = new KL());
	}

	private void run() {
		ScreenTimer timer = new ScreenTimer();
		while (!keyboard.esc) {
			//Time-Delta
			int delta = timer.tick();
			// Controls
			control(delta);
			// Physics
			
			// Render
			display.fps = (int) timer.getFPS();
			display.renderAndWait();
		}
		display.frame.dispose();
	}
	
	private void control(int delta) {
		if (keyboard.w)
			world.move(0, (double)delta/100, 0);
		if (keyboard.s)
			world.move(0, -(double)delta/100, 0);
		if (keyboard.d)
			world.move((double)delta/100, 0, 0);
		if (keyboard.a)
			world.move(-(double)delta/100, 0, 0);
		if (keyboard.right)
			world.rotateY((double) delta/1000, pivot);
		if (keyboard.left)
			world.rotateY((double) -delta/1000, pivot);
		if (keyboard.up)
			world.rotateX((double) delta/1000, pivot);
		if (keyboard.down)
			world.rotateX((double) -delta/1000, pivot);
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		engine.run();
	}

	private class ScreenTimer {

		private long lastTick;
		private double[] fpsHistory = new double[100];
		private int fpsIndex = 0;

		private ScreenTimer() {
			lastTick = System.currentTimeMillis() - 1;
		}

		private int tick() {
			long saved = lastTick;
			lastTick = System.currentTimeMillis();
			long timeElapsed = lastTick - saved;
			fpsHistory[fpsIndex++] = (int) (1000 / timeElapsed);
			fpsIndex = fpsIndex % fpsHistory.length;
			return (int)timeElapsed;
		}

		private double getFPS() {
			double fps = 0;
			for (double i : fpsHistory) {
				fps += i;
			}
			fps /= fpsHistory.length;
			return (fpsHistory[fpsHistory.length - 1] == 0) ? 0 : fps;
		}
	}
}
