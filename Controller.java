
import java.util.ArrayList;

public class Controller extends GameLoop {

	public GamePanel gamePanel;
	public GameControlles gameControlles;
	public Room room;
	public ArrayList<GameObject> list = new ArrayList<GameObject>();
	

	public Controller() {
		gamePanel = new GamePanel(this, 1, 1);
		gameControlles = new GameControlles(this, 1, 1);
		start();
		
		room = new Room(this, 2000, 2000);
		room.start();
	}
	
	@Override
	public void update() {
		//for (GameObject item : list) {
		for (int i = list.size() - 1; i >= 0; i--) 
		{
			GameObject item = list.get(i);

			item.room_width = room.width;
			item.room_height = room.height;
			item.update();
			
			if (item.getDestory() == true)
			{
				list.remove(i);
			}
		}

	}

	@Override
	public void draw(float interpolation) {
		gamePanel.paint(interpolation);
		gameControlles.repaint();
	}
	
	
	public static void main(String[] args) {
		Controller con = new Controller();
		ALL.controller = con;
	}


}
