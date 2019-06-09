package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import world.World;

@SuppressWarnings("serial")
public class Display extends JPanel {

	// Constants
	private static final int HDWIDTH = 1920;
	private static final int HDHEIGHT = 1040;

	// Fields
	private boolean rendered;
	public int fps;

	// Objects
	private final World world;
	private JFrame frame;

	public Display(World world) {

		this.world = world;

		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setBounds(0, 0, HDWIDTH, HDHEIGHT);
		frame.setLayout(null);

		setBounds(0, 0, HDWIDTH, HDHEIGHT);
		setLayout(null);
		frame.add(this);
		setFocusable(true);

		frame.setVisible(true);
	}

	public void renderAndWait() {
		rendered = false;
		repaint();
		while (!rendered) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// Draw background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, HDWIDTH, HDHEIGHT);
		// Draw world
		world.paint(g);
		// Draw HUD
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + fps, 10, 20);
		// finish
		rendered = true;
	}
}