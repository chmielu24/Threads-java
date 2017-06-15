import java.util.ArrayList;

public class Person extends Thread {

	protected Point position;
	protected boolean IsInRestaurant;
	
	protected ArrayList<Point> TravelPoints = new ArrayList<Point>();
	protected int AccualPoint = 0;
	protected int direction = 1;
	
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
	
	public synchronized void Move(float x, float y)
	{
		position.set(position.getX() + x, position.getY() + y);
	}
	
	public void Update()
	{
		if(TravelPoints.size() == 0)
			return;
		
		if(AccualPoint >= TravelPoints.size())
			direction = -1;	
		
		Point v = Point.NormalizedVector(TravelPoints.get(AccualPoint), position);
		
		if(Point.Distance(TravelPoints.get(AccualPoint), position) <= v.Lenght())
		{
			setPosition(TravelPoints.get(AccualPoint));
			AccualPoint += direction;
		}
		else
			Move(v.getX(), v.getY());

	}

	
}
