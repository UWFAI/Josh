import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Ball extends GameObject{

	public double vspeed = 5;
	public double hspeed = 6;
	private Color color;
	
	public Ball(String name, int x, int y)
	{
		super(name, x, y);
		/*this.name = name;
		this.x = x;
		this.y = y;*/
		x_pre = x;
		y_pre = y;
		
		draw_width = 32;
		draw_height = 32;
		
		Random rand = new Random();
		vspeed = rand.nextInt(5)+5;
		hspeed = rand.nextInt(5)+5;
		
		color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}
	
	@Override
	void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, 32, 32);
	}

	@Override
	void update() {
		
		bounce();
		x_pre = x;y_pre = y;
		x+=hspeed;y+=vspeed;
	}
	
	private void bounce()
	{
		if (x < 0 || x > 640)
			hspeed = -hspeed;
		if (y < 0 || y > 480)
			vspeed = -vspeed;
		
	}

}
