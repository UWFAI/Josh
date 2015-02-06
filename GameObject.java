
import java.awt.Color;
import java.util.ArrayList;

public abstract class GameObject {
	protected double x;
	protected double y;
	protected double x_pre;
	protected double y_pre;
	protected double direction;
	
	protected int draw_width;
	protected int draw_height;
	
	// the type of call object
	protected String name = null;
	
	public int room_width = 640;
	public int room_height = 640;
	
	public Color color = Color.BLUE;
	
	public GameObject(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		direction = 0;
	}
	
	protected ArrayList<GameObject> see()
	{
		return null;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getDirection()
	{
		return direction;
	}

	public abstract void update();
}
