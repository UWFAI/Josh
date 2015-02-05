import java.awt.Graphics2D;


public abstract class GameObject {
	protected double x;
	protected double y;
	protected double x_pre;
	protected double y_pre;
	
	protected int draw_width;
	protected int draw_height;
	// the type of call object
	protected String name = null;
	
	public GameObject(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	
	abstract void draw(Graphics2D g);
	abstract void update();
}
