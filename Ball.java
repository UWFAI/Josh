import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

/*
	Ball that bounces around the room colecting resources as it goes 
*/
public class Ball extends GameObject
{
	// where it is bouncing around
	private final Room room;
	
	public Ball(String name, int x, int y, Room room)
	{
		// set up basic info about this ball
		super(name, x, y);
		abilitySet(90, 10, 0);
		this.room = room;
		
		// define how this ball is going to move
		Random rand = new Random();
		motion_set((double)rand.nextInt(360), rand.nextInt(2)+2);
		
		// defines a random color for the ball
		color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}

	// make sure that it keep moving and showing how much resource it has collected
	@Override
	public void update_logic()
	{
		bounce();
		debugString = Integer.toString(resources);
	}

	// draw the ball
	@Override
	public void draw(Graphics2D g){
				Drawer.drawDirectedCircle(g, this, Drawer.CIRCLE_SIZE);
	}
	
	// bounce but there is a possibility of getting stuck
	private void bounce()
	{
		if (x < 0 || x > room.width - Drawer.CIRCLE_SIZE)
			hspeed = -hspeed;
		if (y < 0 || y > room.height - Drawer.CIRCLE_SIZE)
			vspeed = -vspeed;
	}
}
