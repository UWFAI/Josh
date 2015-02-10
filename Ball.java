
import java.awt.Color;
import java.util.Random;

public class Ball extends GameObject{
	
	public Ball(String name, int x, int y)
	{
		super(name, x, y);
		abilitySet(90, 10, 0);
		
		Random rand = new Random();
		motion_set((double)rand.nextInt(360), rand.nextInt(2)+2);
		
		color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		
	}

	@Override
	public void update_logic() {
		bounce();
		
		debugString = Integer.toString(resources);
	}
	
	// bounce but there is a possibility of getting stuck
	private void bounce()
	{
		if (x < 0 || x > room_width-32)
			hspeed = -hspeed;
		if (y < 0 || y > room_height-32)
			vspeed = -vspeed;
	}
}
