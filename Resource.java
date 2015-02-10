import java.util.ArrayList;
import java.awt.Color;

public class Resource extends GameObject{

	public Resource(String name, int x, int y) {
		super(name, x, y);
		color = Color.GREEN;
		debugString = "Resource";
		abilitySet(100, 0, 0);
	}

	@Override
	public void update_logic() {

		for(GameObject item : sightList)
		{
			double dis = ALL.point_distance(x+16, y+16, item.getX()+16, item.getY()+16);
			if (dis < 32.0)
			{
				destory = true;
				item.resources += 1;
			}
		}
	}
}
