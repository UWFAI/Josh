
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	public JFrame frame;
	private Controller controller;
	public float interpolation; // might re-add???
	BufferStrategy bufferStrategy;
	Image img;

	// the top left of the view
	int view_xview = 0;
	int view_yview = 0;

	public GamePanel(Controller c, int x, int y) {
		super();
		this.controller = c;

		// the size the JPanel wants to be on start up
		setPreferredSize(new Dimension(640, 480));

		// the frame/window properties
		frame = new JFrame("game");
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.createBufferStrategy(2);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		bufferStrategy = frame.getBufferStrategy();

		img = Toolkit.getDefaultToolkit().createImage("images/texture.jpg");
	}


	// where all the paint things go
	public void paint(float interpolation) {
		// if there is no bufferStrategy, create one
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
        if (bufferStrategy == null) {
        	frame.createBufferStrategy(3);
            return;
        }

        // get the buffers graphics to draw with
		Graphics2D g2 = (Graphics2D) bufferStrategy.getDrawGraphics();

		// draw the background of the room
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getWidth()+500, getHeight()+500);
		g2.drawImage(img, - view_xview, - view_yview, null);

		// if we have a room to draw
		if (controller.room != null)
		{
			// draw the image only in the room
			g2.setClip(- view_xview, - view_yview, controller.room.width+1, controller.room.height+1);
			//g2.drawImage(img, - view_xview, - view_yview, null);

			// set a translation so it just draw what is inside the view
			g2.translate(-view_xview, -view_yview);

			// draw room
			controller.room.draw(g2);

			// set everything back to the original
			g2.translate(view_xview, view_yview);

		}

		// free
		g2.dispose();

		// draw the buffer
		bufferStrategy.show();

		// stop the lag
		Toolkit.getDefaultToolkit().sync();
	}
}
