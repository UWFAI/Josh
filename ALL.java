
// things that all classes need
public final class ALL {

	// making the controller global
	public static Controller controller = null;
	public static int ID = 0;
	
	//Returns the horizontal x-component of the vector determined by the indicated length and direction.
	public static double lengthdir_x(double len,double dir)
	{
		return Math.cos(Math.toRadians(dir))*len;
	}
	
	//Returns the horizontal y-component of the vector determined by the indicated length and direction.
	public static double lengthdir_y(double len,double dir)
	{
		return -Math.sin(Math.toRadians(dir))*len;
	}
	
	//Returns the direction, in degrees, of a vector comprised.
	public static double point_direction(double x1, double y1, double x2, double y2)
	{
		double dx = x2 - x1;
		double dy = y2 - y1;

		return -Math.toDegrees(Math.atan2(dy,dx));
	}
	
	//Returns the length of a vector comprised of the specified components.
	public static double point_distance(double x1, double y1, double x2, double y2)
	{
		double disX = (x1-x2)*(x1-x2);
		double disY = (y1-y2)*(y1-y2);

		return Math.sqrt(disX+disY);
	}
}
