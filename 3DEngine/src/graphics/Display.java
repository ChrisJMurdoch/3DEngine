package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import world.World;

@SuppressWarnings("serial")
public class Display extends JPanel {

	// Constants
	public static final int HDWIDTH = 1920;
	public static final int HDHEIGHT = 1040;

	// Fields
	public static String dataOut;//Screen SysOut for debugging
	private boolean rendered;
	public int fps;

	// Objects
	public JFrame frame;
	private final World world;
	private ZBuffer zBuffer;

	public Display(World world) {

		this.world = world;

		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(0, 0, HDWIDTH, HDHEIGHT);
		frame.setLayout(null);

		setBounds(0, 0, HDWIDTH, HDHEIGHT);
		setLayout(null);
		frame.add(this);
		setFocusable(true);

		frame.setVisible(true);
		
		zBuffer = new ZBuffer();
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
		// Draw world using z buffer
		zBuffer.createImage(HDWIDTH, HDHEIGHT);
		world.paint(zBuffer);
		zBuffer.drawBuffer(g);
		// Draw HUD
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + fps, 10, 20);
		g.drawString("DATA: " + dataOut, 10, 35);
		// finish
		rendered = true;
	}
}