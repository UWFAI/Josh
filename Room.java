import java.util.Random;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Color;

/*
	This is a square container for objects, that is able to 
	keep all objects within updated, including what they are seeing around.
*/
public class Room extends GameObject
{
	// room's dimension
	public int width;
	public int height;

	// list containing all object that are on the room
	private final ArrayList<GameObject> objectsList;

	public Room(String name, int x, int y, int width, int height) 
	{
		super(name, x, y);

		// set dimensions of the room
		this.width = width;
		this.height = height;


		// init the list of object 
		objectsList = new ArrayList<>();
		
		// adds Balls to the room
		Random rand = new Random();
		for (int i = 0; i < 10; i++)
			objectsList.add(new Ball("ball", rand.nextInt(width-32), rand.nextInt(height-32), this));
		
		// adds simple robots to the room	
		for (int i = 0; i < 10; i++)
			objectsList.add(new AI_simple("simple", rand.nextInt(width-32), rand.nextInt(height-32)));
		
		// adds "resources" to the room
		for (int i = 0; i < 100; i++)
			objectsList.add(new Resource("resource", rand.nextInt(width-32), rand.nextInt(height-32)));

	}

	// thread safe func. that copies the current list of objects and returns it
	public synchronized ArrayList<GameObject> getObjectListCopy()
	{
		return new ArrayList<>(objectsList);
	}

	// make sure that no object get off the room
	final private void stayInRoom(GameObject obj)
	{
		double x = obj.getX();
		double y = obj.getY();

		// if we passed the left wall
		if (x < 0)
		{
			obj.setX(0);
		}

		// if we passed the top wall
		if (y < 0)
		{
			obj.setY(0);
		}
		
		// if we passed the right wall
		if (x > width-32)
		{
			obj.setX(width-32);
		} 

		// if we passed the bottom wall
		if (y > height-32)
		{
			obj.setY(height-32);
		} 
	}

	/*
		updates the objects on the room
	*/
	public void update_logic(){
		synchronized(this){
			// crate a new list to store the agents that can be seen
			ArrayList<GameObject> sightList = new ArrayList<>();

			// between every pair of objects on the room 
			for (GameObject i : objectsList)
			{
				// clear the list
				sightList.clear();

				// compare the current object with all the other objects
				for(GameObject j: objectsList)
				{
					// an object can NOT see itself
					if (i.getID() == j.getID())
					{
						continue;
					}

					// get the distance between i and j
					double dis = ALL.point_distance(i.getX(), i.getY(), j.getX(), j.getY());
					
					// if obj j is into the obj i range then add j to the i's sight list
					if (dis  < (i.ability_sight)) 
					{
						sightList.add(j);
					}
				}

				// update the sight list of i
				i.setSightList(sightList);
			}
			
			// iterate through the objects updating them
			for (GameObject obj: new ArrayList<GameObject>(objectsList))
			{
				// update the current object
				obj.update();
				stayInRoom(obj);

				/*
				 if durring update was detected that this objected died, 
				 removes it from the list
				*/
				if (obj.getDestory() == true)
				{
					objectsList.remove(obj);
				}
			}
		}
	}

	/*
		Draw the objects on the room
	*/
	public void draw(Graphics2D g){
		// iterate through the objects drawing them
		synchronized(this){
			for (GameObject obj: objectsList)
			{
				obj.draw(g);
			}
		}

		// draw the edge of the room
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);

	}
}
