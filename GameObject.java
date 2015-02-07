
import java.awt.Color;
import java.util.ArrayList;

// all object in the game must extend this class!
public abstract class GameObject {
	protected double x;
	protected double y;
	protected double x_pre;
	protected double y_pre;
	protected double direction;
	
	// the type of call object
	protected String name = null;
	
	//default room size
	// needs to go away
	public int room_width = 640;
	public int room_height = 640;
	
	// default color
	public Color color = Color.BLUE;
	
	public GameObject(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		direction = 0;
	}
	
	// will work soon...
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
	// the updates the need to happen the the teams should not mess with
	final void mandatory_update()
	{
		x_pre = x;y_pre = y;
	}

	// will update every frame
	public abstract void update();
}
