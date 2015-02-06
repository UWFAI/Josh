
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	public JFrame frame;
	private Controller controller;
	public float interpolation;
	BufferStrategy bufferStrategy;
	
	int view_xview = 0;
	int view_yview = 0;
	
	public GamePanel(Controller c, int x, int y) {
		super();
		this.controller = c;
		
		setPreferredSize(new Dimension(640, 480));
		
		frame = new JFrame("game");
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.createBufferStrategy(2);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		bufferStrategy = frame.getBufferStrategy();
	}


	//
	public void paint(float interpolation) {
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
        if (bufferStrategy == null) {
        	frame.createBufferStrategy(3);
            return;
        }

		Graphics2D g2 = (Graphics2D) bufferStrategy.getDrawGraphics();
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		@SuppressWarnings("unchecked")
		ArrayList<GameObject> copy = ((ArrayList<GameObject>) controller.list.clone());
		for(GameObject item : copy)
		{
			int drawX = (int) (item.getX() - view_xview);
			int drawy = (int) (item.getY() - view_yview);
			
			g2.setColor(item.color);
			g2.fillOval(drawX, drawy, 32, 32);
			
			int cX = drawX+16;
			int cY = drawy+16;
			g2.setColor(Color.black);
			g2.drawLine(cX, cY, 
					cX+(int)ALL.lengthdir_x(16.0, item.getDirection()), 
					cY+(int)ALL.lengthdir_y(16.0, item.getDirection()));
		}
		controller.list = copy;
		
		g2.drawRect(- view_xview, - view_yview, controller.room.width, controller.room.height);
		
		g2.dispose();
		
		bufferStrategy.show();
		
		Toolkit.getDefaultToolkit().sync();
	}
}
