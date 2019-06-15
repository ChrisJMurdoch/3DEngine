package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry3d.Camera;
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
	public final JFrame frame;
	private final World world;
	private final Camera camera;
	private final ZBuffer zBuffer;

	public Display(World world, Camera camera) {

		this.world = world;
		this.camera = camera;

		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(0, 0, HDWIDTH, HDHEIGHT);
		frame.setLayout(null);

		setBounds(0, 0, HDWIDTH, HDHEIGHT);
		setLayout(null);
		frame.add(this);
		setFocusable(true);
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
		frame.getContentPane().setCursor(blankCursor);

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
		world.paint(zBuffer, camera);
		zBuffer.drawBuffer(g);
		// Draw HUD
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + fps, 10, 20);
		g.drawString("DATA: " + dataOut, 10, 35);
		// finish
		rendered = true;
	}
}