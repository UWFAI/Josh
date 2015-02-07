
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

		img = Toolkit.getDefaultToolkit().createImage("images/grass.jpg");
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
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// draw the image only in the room
		g2.setClip(- view_xview, - view_yview, controller.room.width+1, controller.room.height+1);
		g2.drawImage(img, - view_xview, - view_yview, null);
		
		// make a copy of the main list so not to cause errors
		@SuppressWarnings("unchecked")
		ArrayList<GameObject> copy = ((ArrayList<GameObject>) controller.list.clone());
		// draw each object
		for(GameObject item : copy)
		{
			// git the deference of the view and object so to create the illusion of a view
			int drawX = (int) (item.getX() - view_xview);
			int drawy = (int) (item.getY() - view_yview);

			// draw the oval
			g2.setColor(item.color);
			g2.fillOval(drawX, drawy, 32, 32);

			// draw the direction line
			int cX = drawX+16;
			int cY = drawy+16;
			g2.setColor(Color.black);
			g2.drawLine(cX, cY,
					cX+(int)ALL.lengthdir_x(16.0, item.getDirection()),
					cY+(int)ALL.lengthdir_y(16.0, item.getDirection()));
		}

		// draw the edge of the room
		g2.drawRect(- view_xview, - view_yview, controller.room.width, controller.room.height);

		// free
		g2.dispose();

		// draw the buffer
		bufferStrategy.show();

		// stop the lag
		Toolkit.getDefaultToolkit().sync();
	}
}