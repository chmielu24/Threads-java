
public class Person extends Thread {

	protected Point position;
	
	public Person(int x, int y)
	{
		setPosition(new Point(x, y));
	}

	public synchronized Point getPosition() {
		return position;
	}

	public synchronized void setPosition(Point position) {
		this.position = position;
	}
	
	public void Move(int x, int y)
	{
		position.set(position.getX() + x, position.getY() + y);
	}
	
}
