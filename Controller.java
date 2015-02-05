import java.util.ArrayList;
import java.util.Random;

public class Controller extends GameLoop {

	private GamePanel gamePanel;
	public static Room room;
	public ArrayList<GameObject> list = new ArrayList<GameObject>();
	

	public Controller() {
		gamePanel = new GamePanel(this, 25, 25);
		start();
		
		room = new Room(this, 1000, 1000);
		room.start();
	}
	
	@Override
	public void update() {
		for (GameObject item : list) {
			item.update();
		}
	}

	@Override
	public void draw(float interpolation) {
		gamePanel.paint(interpolation);
	}
	
	
	public static void main(String[] args) {
		Controller con = new Controller();
	}


}
