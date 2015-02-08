# Josh
Josh's Java Game Environment

Grab the files either from the "download zip" button in the lower right,
or if you have git installed on your machine, in a terminal write

```git clone https://github.com/UWFAI/Josh```

Assuming you have java installed on your machine, the fastest way is to compile 
from the command line. "cd" into the "Josh" directory and write

```javac *.java && java Controller```

# How to start
First thing that you need to do is to get the environment ready to go. There should 
be a bunch of bouncing balls when you start it up, if there is no ask for sum help. 

Next you need to create a new class, name it whatever you like. After you make it, it
needs to look extend GameObject. After that it should look something like:
```
public class AI_simple extends GameObject{

	public AI_simple(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void update() {
		mandatory_update();
	}

}
```

Congratulations you created your first agent, well kinda. To see what your new
creation looks like you need to edit Room.java.
In the method "start()" you can see that there is that there is already some code
in there, that creates the 100 bouncing balls. You can take it out if you want, but
this is where you need to put the code to create your now agent. It should look like:
```controller.list.add(new AI_simple("simple", 64, 64));```

Now save, compile, and run and you should see a little blue ball, if not you most likely
messed everything and you need help.

# helpful things
My goal in making this was to make it easy to make AI. I did not want you to worry
about how to move in a direction or anything like that. So here are some helpful stuff
that hopefully makes your day.

```point_distance(double x1, double y1, double x2, double y2)```
Just the distance formula nothing special but all of you will use it so why not make it.

```point_direction(double x1, double y1, double x2, double y2)```
gives you the direction from one point to another again simple but very helpful.

```motion_set(double direction, double speed)```
Want to move in a direction at a set speed? You might want to use this.

```see()```
This returns a list of other agents around you.








