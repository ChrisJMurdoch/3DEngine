package engine;

import geometry3d.Shape3D;
import graphics.Display;
import inputs.KL;
import inputs.MML;
import shapes3d.ShapeBuilder;
import world.World;

public class Engine {

	private World world;
	private Display display;
	private KL keyboard;
	private MML mouse;


	private Engine() {
		world = new World(ShapeBuilder.buildShapes());
		display = new Display(world);
		display.addKeyListener(keyboard = new KL());
	}

	private void run() {
		world.shapes[0].rotateZ(Math.PI*0.25);
		world.shapes[0].rotateX(Math.PI*0.196);
		ScreenTimer timer = new ScreenTimer();
		while (!keyboard.esc) {
			//Time-Delta
			int delta = timer.tick();
			// Controls
			control(delta);
			// Physics
			world.shapes[0].rotateY((double) delta/1000);
			// Render
			display.fps = (int) timer.getFPS();
			display.renderAndWait();
		}
		display.frame.dispose();
	}
	
	private void control(int delta) {
		Shape3D shape = world.shapes[0];
		if (keyboard.w)
			world.move(0, 0, -(double)delta/100);
		if (keyboard.s)
			world.move(0, 0, (double)delta/100);
		if (keyboard.d)
			world.move(-(double)delta/100, 0, 0);
		if (keyboard.a)
			world.move((double)delta/100, 0, 0);
		//if (keyboard.right)
			//shape.rotateY((double) delta/1000);
		//if (keyboard.left)
			//shape.rotateY((double) -delta/1000);
		//if (keyboard.up)
			//shape.rotateX((double) delta/1000);
		//if (keyboard.down)
			//shape.rotateX((double) -delta/1000);
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		engine.run();
	}

	private class ScreenTimer {

		private long lastTick;
		private double[] fpsHistory = new double[30];
		private int fpsIndex = 0;

		private ScreenTimer() {
			lastTick = System.currentTimeMillis() - 1;
		}

		private int tick() {
			long saved = lastTick;
			lastTick = System.currentTimeMillis();
			long timeElapsed = lastTick - saved;
			fpsHistory[fpsIndex++] = (int) (1000 / (double)timeElapsed);
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