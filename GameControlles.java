import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

// this is the user interface for the simulation
// no need to look in here
// even if it is yourself, someone, some day will need to look here! - Renan
@SuppressWarnings("serial")
public class GameControlles extends JPanel{
	private JFrame frame;
	private Controller controller;
	
	public GameControlles(Controller c, int x, int y) {
		super();
		this.controller = c;
		//setPreferredSize(new Dimension(640, 480));
		
		frame = new JFrame("Controlles");
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(x, y);

		add(new Map(), BorderLayout.PAGE_START);
		add(new Table(), BorderLayout.PAGE_START);

		/*JTable table = new JTable(2, 3);

		table.setEnabled(false);
		add(table, BorderLayout.PAGE_START);
		*/
		frame.pack();
	}
	
	// the info table
	class Table extends JPanel {
		int col_num = 3;
		int row_num = 12;
		JTable table;
		
		Table() {
			setBorder(BorderFactory.createLineBorder(Color.black));
			
			table = new JTable(row_num, col_num);
			//table.setEnabled(false);
			add(table);
		}
		
		// notreally using this to paint...
		@Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

			// top row
        	table.setValueAt("Name", 0, 0);
        	table.setValueAt("Number Of", 0, 1);
        	table.setValueAt("Resources", 0, 2);
        	
        	String[] names 	= new String[11];
        	int[] num 		= new int[]{0,0,0,0,0,0,0,0,0,0,0};
        	int[] res 		= new int[]{0,0,0,0,0,0,0,0,0,0,0};
        	
          if (controller.room == null)
          {
            return;
          }

        	@SuppressWarnings("unchecked")
      ArrayList<GameObject> copy = controller.room.getObjectListCopy();
      // ArrayList<GameObject> copy = new ArrayList<>();
    		for(GameObject item : copy)
    		{
    			String name = item.getName();
				for(int i=0; i<11; i++)
				{
					if (names[i] == null)
					{
						names[i] = name;
						num[i]++;
						res[i]+=item.resources;
						break;
					}
					if (names[i] == name)
					{
						num[i]++;
						res[i]+=item.resources;
						break;
					}
				}
    		}
    		
		    for(int i=0; i<11; i++)
			{
				table.setValueAt(names[i], i+1, 0);
				table.setValueAt(num[i], i+1, 1);
				table.setValueAt(res[i], i+1, 2);
			}
        }
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

            // error cheaking
            if (controller.room == null)
            	return;
            	
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
  			ArrayList<GameObject> copy = controller.room.getObjectListCopy();
      		for(GameObject item : copy)
      		{
            int dx = (int)(item.getX() / multW);
            int dy = (int)(item.getY() / multH);
      			g.fillOval(dx, dy, 2, 2);
      		}
        }
    }
}


