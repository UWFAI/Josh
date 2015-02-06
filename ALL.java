

public final class ALL {
	public static double lengthdir_x(double len,double dir)
	{
		return Math.cos(Math.toRadians(dir))*len;
	}
	public static double lengthdir_y(double len,double dir)
	{
		return -Math.sin(Math.toRadians(dir))*len;
	}
	public static double point_direction(double x1, double y1, double x2, double y2)
	{
		double dx = x2 - x1;
		double dy = y2 - y1;

		return -Math.toDegrees(Math.atan2(dy,dx));
	}
}
