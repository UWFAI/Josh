import java.util.ArrayList;

/*
	Main class of the application basicly it initializes and keep
	everything running. By everything read:

		- Mini Map Window
		- Main Window
		- Room (shown on the Main Window) and all its objects inside
*/
public class Controller extends GameLoop {

	public GamePanel gamePanel;
	public GameControlles gameControlles;
	public Room room;	

	public Controller() {
		gamePanel = new GamePanel(this, 1, 1);
		gameControlles = new GameControlles(this, 1, 1);
		
		room = new Room("Room", 0, 0, 2000, 2000);

		start();
	}
	
	// update every object that are being handled by this controller 
	@Override
	public void update()
	{
		room.update();
	}

	// draw the two windows 
	@Override
	public void draw(float interpolation) 
	{
		gamePanel.paint(interpolation);
		gameControlles.repaint();
	}
	
	// runs the aplication
	public static void main(String[] args) 
	{
		Controller con = new Controller();
		// ALL.controller = con;
	}

}
