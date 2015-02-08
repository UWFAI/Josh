// no need to look in here
public abstract class GameLoop {
	boolean running = true;
	int fps = 60;//current fps, not target
	int frameCount = 0;
	
	// starts the simulation in a new thread
	public void start() {
		Thread update = new Thread() {

			public void run() {
				gameLoop();
			}
		};
		update.start();
	}

	// the game loop
	public void gameLoop() {
		final double GAME_HERTZ = 30.0;
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();

		final double TARGET_FPS = 10;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			// keep updating the world while running behind 
			while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) 
			{
				update();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}

			if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}

			// render the world
			float interpolation = Math.min(1.0f,
					(float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
			draw(interpolation);
			lastRenderTime = now;

			// find the fps
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) {
				fps = frameCount;
				frameCount = 0;
				lastSecondTime = thisSecond;
			}

			// gives up recourses to the cpu
			while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) 
			{
				Thread.yield();
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}

				now = System.nanoTime();
			}

		}
	}

	public abstract void update();

	public abstract void draw(float interpolation);
}