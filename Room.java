
import java.util.Random;

public class Room {
	public int width;
	public int height;
	private Controller controller;

	public Room(Controller c, int width, int height) {
		this.width = width;
		this.height = height;
		controller = c;
	}

	public void start() {
		// fill room with things
		Random rand = new Random();
		for (int i = 0; i < 100; i++)
			controller.list.add(new Ball("ball", rand.nextInt(width-32), rand.nextInt(height-32)));
			
		controller.list.add(new AI_simple("simple", 64, 64));
	}
}
