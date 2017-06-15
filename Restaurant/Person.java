import java.util.ArrayList;

public class Person extends Thread {

	protected Point position;
	protected boolean IsInRestaurant;
	
	protected ArrayList<Point> TravelPoints = new ArrayList<Point>();
	protected int AccualPoint = 0;
	protected int direction = 1;
	private float speed = 3f;
	
	public Person(Point p)
	{
		position = new Point(p.getX(), p.getY());
		IsInRestaurant = true;
	}

	synchronized public Point getPosition() {
		return position;
	}

	synchronized public void setPosition(Point position) {
		this.position.set(position.getX(), position.getY());
	}
	
	synchronized public void Move(float x, float y)
	{
		position.set(position.getX() + x, position.getY() + y);
	}
	
	public void Update()
	{		
		
		if(TravelPoints.size() == 0 || AccualPoint == -1)
			return;	
		

		if(direction == 1)
		{		
			Point v = Point.NormalizedVector(TravelPoints.get(AccualPoint), position);
						
			if(Point.Distance(TravelPoints.get(AccualPoint), position) <= speed)
			{
				setPosition(TravelPoints.get(AccualPoint));
				
				if(AccualPoint == TravelPoints.size() - 1)
				{
					IsOnEndPoint();
				}
				else
					AccualPoint++;
			}
			else
				Move(v.getX() * speed, v.getY() * speed);
		}
		else
		{		
			Point v = Point.NormalizedVector(position, TravelPoints.get(AccualPoint));
			
			if(Point.Distance(position, TravelPoints.get(AccualPoint)) <= speed)
			{
				setPosition(TravelPoints.get(AccualPoint));
				AccualPoint--;
				
				if(AccualPoint == -1)
					EndTravel();
			}
			else
				Move(v.getX() * -speed, v.getY() * -speed);
			
		}
	}
	
	protected void IsOnEndPoint()
	{
		direction = -1;
	}
	
	protected void EndTravel()
	{
		IsInRestaurant = false;

	}
	
	protected void SleepAfterMove(int time)
	{
		try {
			sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
