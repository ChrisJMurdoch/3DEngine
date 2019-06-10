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

	private Engine() {
		
		double[][] one = { { 10 }, { -10 }, { 120 } };
		double[][] two = { { 10 }, { -10 }, { 140 } };
		double[][] thr = { { 10 }, { 10 }, { 120 } };
		double[][] fou = { { 10 }, { -10 }, { 140 } };
		double[][] fiv = { { 10 }, { 10 }, { 120 } };
		double[][] six = { { 10 }, { 10 }, { 140 } };
		double[][] sev = { { -10 }, { -10 }, { 120 } };
		double[][] eig = { { -10 }, { -10 }, { 140 } };
		double[][] nin = { { -10 }, { 10 }, { 120 } };
		double[][] ten = { { -10 }, { -10 }, { 140 } };
		double[][] ele = { { -10 }, { 10 }, { 120 } };
		double[][] twe = { { -10 }, { 10 }, { 140 } };

		Point3D[] points1 = { new Point3D(one), new Point3D(two), new Point3D(thr), };
		Point3D[] points2 = { new Point3D(fou), new Point3D(fiv), new Point3D(six), };
		Point3D[] points3 = { new Point3D(sev), new Point3D(eig), new Point3D(nin), };
		Point3D[] points4 = { new Point3D(ten), new Point3D(ele), new Point3D(twe), };
		Triangle3D[] triangles = { new Triangle3D(points1, Color.RED), new Triangle3D(points2, Color.BLUE) , new Triangle3D(points3, Color.RED) , new Triangle3D(points4, Color.BLUE) };
		
		world = new World(triangles);
		display = new Display(world);
		display.addKeyListener(keyboard = new KL());
	}

	private void run() {
		ScreenTimer timer = new ScreenTimer();
		while (!keyboard.esc) {
			// Time-delta
			long timeElapsed = timer.tick();
			// Controls
			// Physics
			// Render
			display.renderAndWait();
			// try { Thread.sleep(16);
			// } catch (InterruptedException e) { e.printStackTrace();}

			display.fps = (int) timer.getFPS();
		}
		display.frame.dispose();
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

		private long tick() {
			long saved = lastTick;
			lastTick = System.currentTimeMillis();
			long timeElapsed = lastTick - saved;
			fpsHistory[fpsIndex++] = (int) (1000 / timeElapsed);
			fpsIndex = fpsIndex % fpsHistory.length;
			return timeElapsed;
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
