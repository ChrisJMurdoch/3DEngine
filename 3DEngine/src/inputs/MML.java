package inputs;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import graphics.Display;

public class MML implements MouseMotionListener {

	public float xAngle = 0;
	public float yAngle = 0;
	
	private int dx = 0;
	private int dy = 0;

	private int defX;
	private int defY;

	private Robot robot;

	public MML() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		defX = toolkit.getScreenSize().width / 2;
		defY = toolkit.getScreenSize().height / 2;
	}

	public void mouseDragged(MouseEvent e) {
		this.mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		if (x != defX || y != defY) {
			dx += x - defX;
			dy += y - defY;
			robot.mouseMove(defX, defY);
		}
		regulate();
	}
	
	private void regulate() {
		float multiplier = 2f;
		if (dy/(float)Display.HEIGHT*multiplier > 0.95)
			dy = (int) (0.95f*(float)Display.HEIGHT/multiplier);
		if (dy/(float)Display.HEIGHT*multiplier < -0.95)
			dy = (int) (-0.95f*(float)Display.HEIGHT/multiplier);
		xAngle = dx / (float)Display.WIDTH * multiplier;
		yAngle = dy / (float)Display.HEIGHT * multiplier;
	}
}