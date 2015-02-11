import java.util.ArrayList;
import java.awt.Graphics2D;

/*
	Simple bot that just avoids contact with other objects
*/
public class AI_simple extends GameObject
{
	// initializes the bot
	public AI_simple(String name, int x, int y)
	{
		super(name, x, y);
		abilitySet(90, 10, 0);
	}

	@Override
	public void update_logic()
	{
		
		// get all the angles of all the objects it can see
		ArrayList<Double> angleList = new ArrayList<Double>();
		for(GameObject item : sightList)
		{
			angleList.add(ALL.point_direction(x, y, item.getX(), item.getY()));
		}
		
		// only if there is something to run from
		if (angleList.size() > 0)
		{
			runFromOthers(angleList);
		} else {
			motion_set(direction, 0);
		}
		
		// show a debug message
		debugString = Integer.toString(resources);

	}

	@Override
	public void draw(Graphics2D g){
				Drawer.drawDirectedCircle(g, this, Drawer.CIRCLE_SIZE);
	}
	
	public void runFromOthers(ArrayList<Double> angleList)
	{
		// do all the hard math
		double angle = ALL.getAverageAngle(angleList);
		// run away from it
		motion_set(angle+180.0, 3);
	}
}
