
import java.awt.Color;
import java.util.Random;

public class Ball extends GameObject{

	public double vspeed = 5;
	public double hspeed = 6;
	
	public Ball(String name, int x, int y)
	{
		super(name, x, y);
		x_pre = x;
		y_pre = y;
		
		draw_width = 32;
		draw_height = 32;
		
		Random rand = new Random();
		vspeed = rand.nextInt(20)-10;
		hspeed = rand.nextInt(20)-10;
		
		color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}

	@Override
	public void update() {
		bounce();
		x_pre = x;y_pre = y;
		x+=hspeed;y+=vspeed;
		direction = ALL.point_direction(x_pre, y_pre, x, y);
	}
	
	private void bounce()
	{
		if (x < 0 || x > room_width-32)
			hspeed = -hspeed;
		if (y < 0 || y > room_height-32)
			vspeed = -vspeed;
		
	}

}
