import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Life  extends GameObject{

	int cellSize = 5;
	int width = 128;
	int height = 99;
	boolean[][] grid = new boolean[height][width];
	
	public Life(String name, int x, int y) {
		super(name, x, y);
		
		Random rand = new Random();
		
		// fill 2d array
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				grid[i][j] = (rand.nextInt(100) < 15); // 15% chance of being true
	}

	@Override
	void draw(Graphics2D g) {
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
			{
				int x1 = (j*cellSize);
				int y1 = (i*cellSize);

				g.setColor(Color.white);
				if (grid[i][j]) g.setColor(Color.blue);
				
				g.fillRect(x1, y1, cellSize, cellSize);
			}
		
	}
	
	int getCell(boolean[][] grid, int x, int y)
	{
		if (x<0 || y<0 || x>=width || y>=height)
		{
			return 0;
		} else if (grid[y][x])
		{
			return 1;
		} else {
			return 0;
		}
	}
	int getNeighborNumber(boolean[][] grid, int x, int y)
	{
		int out = 0;
		out += getCell(grid, x-1, y-1);
		out += getCell(grid, x, y-1);
		out += getCell(grid, x+1, y-1);
		out += getCell(grid, x-1, y);
		out += getCell(grid, x+1, y);
		out += getCell(grid, x-1, y+1);
		out += getCell(grid, x, y+1);
		out += getCell(grid, x+1, y+1);
		
		return out;
	}

	@Override
	void update() {
		// create a new grid
		boolean[][] newGrid = new boolean[height][width];
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
			{
				int num = getNeighborNumber(grid, j, i);
				if (grid[i][j])
				{
					if ( num < 2)
					{
						newGrid[i][j] = false;
					} else if (num == 2 || num == 3)
					{
						newGrid[i][j] = true;
					} else
					{
						newGrid[i][j] = false;
					}
				} else {
					if ( num == 3)
					{
						newGrid[i][j] = true;
					}
				}
			}
		
		// set new grid to old grid
		grid = newGrid;
	}
}
