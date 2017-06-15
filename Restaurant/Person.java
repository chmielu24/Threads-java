
public class Person extends Thread {

	protected Point position;
	protected boolean IsInRestaurant;
	
	public Person(Point p)
	{
		position = new Point(p.getX(), p.getY());
		IsInRestaurant = true;
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
