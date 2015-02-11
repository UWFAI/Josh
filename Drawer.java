import java.awt.Color;
import java.awt.Graphics2D;

/*
  Drawer component that implements usual forms of drawing an game object
*/
public class Drawer{
  // usual size for circles
  public static final int CIRCLE_SIZE = 32;

  // draw a gameobject as a circle
  public static void drawCircle(Graphics2D g, GameObject obj, int size)
  {
    // position of the center of the ball
    int cX = (int)obj.getX() + size / 2;
    int cY = (int)obj.getY() + size / 2;

    // draw the oval
    g.setColor(obj.color);
    g.fillOval((int)obj.getX(), (int)obj.getY(), size, size);   


    // draw the debug string
    g.setColor(Color.black);
    Drawer.drawString(g, obj.debugString, cX, cY + size); 
  }

  /* 
    draw a gameobject as as circle with a inside it represent 
    the direction it is faccing
  */
  public static void drawDirectedCircle(Graphics2D g, GameObject obj, int size)
  {
    // position of the center of the ball
        int cX = (int)obj.getX() + size / 2;
        int cY = (int)obj.getY() + size / 2;

    // draw the circle
    Drawer.drawCircle(g, obj, size);

    // draw the direction line
    g.setColor(Color.black);
    g.drawLine(cX, cY,
               cX + (int)ALL.lengthdir_x(size / 2, obj.getDirection()),
               cY + (int)ALL.lengthdir_y(size / 2, obj.getDirection())
              );
  }

  // draw a text on the graphical context passed 
  public static void drawString(Graphics2D g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }
}