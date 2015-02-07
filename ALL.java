
// things that all classes need
public final class ALL {
	
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
}
