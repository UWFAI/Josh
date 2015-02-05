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
		//for (int i = 0; i < 1000; i++)
		//	controller.list.add(new Ball("ball", rand.nextInt(640), rand.nextInt(480)));
		controller.list.add(new Life("life", 0, 0));
	}
}
