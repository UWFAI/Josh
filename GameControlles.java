
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

@SuppressWarnings("serial")
public class GameControlles  extends JPanel{
	private JFrame frame;
	private Controller controller;
	//public JPanel map;
	
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
		
		//map = new Map();
		add(new Map());
	}
	
	
    class Map extends JPanel {

    	int size = 200;
    	double multW = 1.0;
    	double multH = 1.0;
    	
    	Map() {
            setPreferredSize(new Dimension(size,size));
            setBorder(BorderFactory.createLineBorder(Color.black));
            setBackground(Color.white);
            //
            addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent e) {}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if (getMousePosition() != null)
					updateView(getMousePosition().x, getMousePosition().y);
				}
			});
            //
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
    	
    	void updateView(int x, int y)
    	{
    		int view_width = (int) (controller.gamePanel.getWidth()/multW);
    		int view_height = (int) (controller.gamePanel.getHeight()/multH);
    		int xDiff = (view_width/2);
			int yDiff = (view_height/2);
    		
    		controller.gamePanel.view_xview = (int) ((x-xDiff)*multW);
			controller.gamePanel.view_yview = (int) ((y-yDiff)*multH);
    	}
    	
    	

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
    		controller.list = copy;
    		
    		

            
        }
    }
}


