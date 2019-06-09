package engine;

import geometry2d.Point2D;
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
		Point2D[] points = { new Point2D(10, 110), new Point2D(100, 110), new Point2D(100, 30), new Point2D(10, 30) };
		world = new World(points);
		display = new Display(world);
	}

	private void run() {
		ScreenTimer timer = new ScreenTimer();
		while (true) {
			// Time-delta
			long timeElapsed = timer.tick();
			// Controls
			// Physics
			// Render
			display.renderAndWait();
			//try { Thread.sleep(16);
			//} catch (InterruptedException e) { e.printStackTrace();}

			display.fps = (int)timer.getFPS();
		}
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
			return (fpsHistory[fpsHistory.length-1] == 0) ? 0 : fps;
		}
	}
}
