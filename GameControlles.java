
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

// this is the user interface for the simulation
// no need to look in here
@SuppressWarnings("serial")
public class GameControlles extends JPanel{
	private JFrame frame;
	private Controller controller;
	
	public GameControlles(Controller c, int x, int y) {
		super();
		this.controller = c;
		setPreferredSize(new Dimension(220, 220));
		
		frame = new JFrame("Controlles");
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(x, y);

		add(new Map());
	}
	
	// the mini map!
    class Map extends JPanel {
    	// less math if the map is square
    	int size = 200;
    	// default multipliers
    	double multW = 1.0;
    	double multH = 1.0;
    	
    	// 
    	Map() {
            setPreferredSize(new Dimension(size,size));
            setBorder(BorderFactory.createLineBorder(Color.black));
            setBackground(Color.white);
            
            // just want to know when the mouse is dragged
            addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent e) {}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if (getMousePosition() != null)
					updateView(getMousePosition().x, getMousePosition().y);
				}
			});
            
            // just want to know when the mouse is pressed
            addMouseListener(new MouseListener() {

				@Override
				public void mousePressed(MouseEvent e) {
					updateView(getMousePosition().x, getMousePosition().y);
				}
				

				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseReleased(MouseEvent e) {}
			});
        }
    	
    	// update the game panel view to the x and y pos
    	void updateView(int x, int y)
    	{
    		int view_width = (int) (controller.gamePanel.getWidth()/multW);
    		int view_height = (int) (controller.gamePanel.getHeight()/multH);
    		int xDiff = (view_width/2);
			int yDiff = (view_height/2);
    		
    		controller.gamePanel.view_xview = (int) ((x-xDiff)*multW);
			controller.gamePanel.view_yview = (int) ((y-yDiff)*multH);
    	}
    	
    	
    	// draw the mini map!
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // keeps these updated
            multW = controller.room.width/size;
            multH = controller.room.height/size;

            // bounds of the view
     		int view_width = (int) (controller.gamePanel.getWidth()/multW);
    		int view_height = (int) (controller.gamePanel.getHeight()/multH);
            int x = (int) (controller.gamePanel.view_xview/multW);
            int y = (int) (controller.gamePanel.view_yview/multH);           
            
            g.setColor(new Color(200,255,255));
            g.fillRect(x, y, view_width, view_height);     
            
            g.setColor(Color.black);
            g.drawRect(x, y, view_width, view_height);            
            
            // draw each object
    		@SuppressWarnings("unchecked")
			ArrayList<GameObject> copy = (ArrayList<GameObject>) controller.list.clone();
    		for(GameObject item : copy)
    		{
    			int dx = (int) (item.getX()/multW);
    			int dy = (int) item.getY()/(controller.room.height/size);
    			
    			
    			g.fillOval(dx, dy, 2, 2);
    		}
        }
    }
}


