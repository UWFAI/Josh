import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GamePanel extends JPanel{
	private JFrame frame;
	private Controller controller;
	public float interpolation;
	BufferStrategy bufferStrategy;
	
	public GamePanel(Controller c, int x, int y) {
		super();
		this.controller = c;
		
		setPreferredSize(new Dimension(640, 480));
		
		frame = new JFrame("game");
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.createBufferStrategy(2);
		frame.setVisible(true);
		frame.setLocation(x, y);
		
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
		
		for(GameObject item : controller.list)
		{
			item.draw(g2);
		}
		g2.dispose();
		
		bufferStrategy.show();
		
		Toolkit.getDefaultToolkit().sync();
	}
}
