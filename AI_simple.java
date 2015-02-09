import java.util.ArrayList;

public class AI_simple extends GameObject{

	public AI_simple(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void update_logic() {
		
		// get all the angles of all the objects it can see
		ArrayList<Double> angleList = new ArrayList<Double>();
		for(GameObject item : sightList)
		{
			angleList.add(ALL.point_direction(x, y, item.getX(), item.getY()));
		}
		
		// only if there is something to run from
		if (angleList.size() > 0)
		{
			// do all the hard math
			double angle = ALL.getAverageAngle(angleList);
			// run away from it
			motion_set(angle+180.0, 3);
		}
		
		// show a debug message
		debugString = Double.toString(x) + "\n" + Double.toString(y);
		
		stayInRoom();
	}
	
	public void stayInRoom()
	{
		if (x<0) x = 0;
		if (y<0) y = 0;
		
		if (x>ALL.controller.room.width-32) x = ALL.controller.room.width-32;
		if (y>ALL.controller.room.height-32) y = ALL.controller.room.height-32;
	}
}
