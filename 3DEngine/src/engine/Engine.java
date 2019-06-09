package engine;

import java.awt.Color;

import geometry2d.Point2D;
import geometry2d.Triangle2D;
import graphics.Display;
import inputs.KL;
import inputs.MML;
import world.World;

public class Engine {

	private World world;
	private Display display;
	private MML mouse;
	private KL keyboard;

	private Engine() {
		Point2D[] points1 = { new Point2D(10, 110), new Point2D(100, 110), new Point2D(100, 30) };
		Point2D[] points2 = { new Point2D(300, 105), new Point2D(95, 200), new Point2D(70, 70) };
		Triangle2D[] triangles = { new Triangle2D(points1, Color.RED), new Triangle2D(points2, Color.BLUE) };
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
			//try { Thread.sleep(16);
			//} catch (InterruptedException e) { e.printStackTrace();}

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
