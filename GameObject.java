
import java.awt.Color;
import java.util.ArrayList;

// all object in the game must extend this class!
public abstract class GameObject {
	protected double x;
	protected double y;
	protected double x_pre;
	protected double y_pre;
	protected double direction;
	protected double vspeed = 0;
	protected double hspeed = 0;
	protected int ID = 0;
	
	public String debugString = "test";
	public ArrayList<GameObject> sightList = null;	
	
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
		ID = ALL.ID;
		ALL.ID++;
	}
	
	// 
	protected ArrayList<GameObject> see()
	{
		ArrayList<GameObject> outList = new ArrayList<GameObject>();
		// draw each object
		for(GameObject item : ALL.controller.list)
		{
			double dis = ALL.point_distance(x, y, item.getX(), item.getY());
			if (dis  < 100 && ID != item.getID()) 
			{
				outList.add(item);
			}
		}
		return outList;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public int getID()
	{
		return ID;
	}
	public double getDirection()
	{
		return direction;
	}
	//Sets the motion of the calling object to the given direction and speed
	public void motion_set(double direction, double speed)
	{
		vspeed = ALL.lengthdir_y(speed, direction);
		hspeed = ALL.lengthdir_x(speed, direction);
	}
	// the updates the need to happen the the teams should not mess with
	final void mandatory_update()
	{
		x_pre = x;
		y_pre = y;
		x+=hspeed;
		y+=vspeed;
		sightList = see();
		direction = ALL.point_direction(x_pre, y_pre, x, y);
	}

	// will update every frame
	public abstract void update();
}
