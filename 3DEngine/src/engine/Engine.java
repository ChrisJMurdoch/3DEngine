package engine;

import geometry3d.Camera;
import geometry3d.Point3D;
import geometry3d.Shape3D;
import graphics.Display;
import inputs.KL;
import inputs.MML;
import shapes3d.ShapeBuilder;
import world.World;

public class Engine {

	private World world;
	private Camera camera;
	private Display display;
	private KL keyboard;
	private MML mouse;


	private Engine() {
		world = new World(ShapeBuilder.buildShapes1());
		camera = new Camera(0,0,0);
		display = new Display(world, camera);
		display.addKeyListener(keyboard = new KL());
		display.addMouseMotionListener(mouse = new MML());
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
		camera.pitch = -mouse.yAngle/1000;
		camera.yaw = mouse.xAngle/1000;
		double z = 0;
		double x = 0;
		if (keyboard.w)
			z =- (double)delta/100;
		if (keyboard.s)
			z =+ (double)delta/100;
		if (keyboard.d)
			x =- (double)delta/100;
		if (keyboard.a)
			x =+ (double)delta/100;
		Point3D vector = new Point3D(x,0,z);
		vector.rotateY(-camera.yaw, new Point3D(0,0,0));
		camera.move(vector.getX(), vector.getY(), vector.getZ());
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		engine.run();
	}

	private class ScreenTimer {

		private long lastTick;
		private double[] fpsHistory = new double[10];
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