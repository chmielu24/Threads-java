import java.util.ArrayList;

public class Person extends Thread {

	protected Point position;
	protected boolean IsInRestaurant;
	protected boolean IsWait = false;
	private boolean IsAfterStaris = false;
	
	protected ArrayList<Point> TravelPoints = new ArrayList<Point>();
	protected int AccualPoint = 0;
	protected int direction = 1;
	protected float speed = 2f;
	
	protected int WaitPoints[] = {2,3};
	
	public Person(Point p)
	{
		position = new Point(p.getX(), p.getY());
		IsInRestaurant = true;
		
		TravelPoints.add(new Point(p.getX(), p.getY()));
		TravelPoints.add(new Point(400, 90));
		TravelPoints.add(new Point(400, 150));
		TravelPoints.add(new Point(400, 350));
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position.set(position.getX(), position.getY());
	}
	
	public void Move(float x, float y)
	{
		position.set(position.getX() + x, position.getY() + y);
	}
	
	protected void Update()
	{		
		if(TravelPoints.size() == 0 || AccualPoint == -1 || IsWait)
			return;	
		

		if(direction == 1)
		{		
			Point v = Point.NormalizedVector(TravelPoints.get(AccualPoint), position);
						
			if(Point.Distance(TravelPoints.get(AccualPoint), position) <= speed)
			{
				setPosition(TravelPoints.get(AccualPoint));
				
				if(checkStairs(false))
				{
					return;
				}
				
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
		else if(direction == -1)
		{		
			Point v = Point.NormalizedVector(position, TravelPoints.get(AccualPoint));
			
			if(Point.Distance(position, TravelPoints.get(AccualPoint)) <= speed)
			{
				setPosition(TravelPoints.get(AccualPoint));
				
				if(checkStairs(true))
					return;
				
				AccualPoint--;
				if(AccualPoint == -1)
					EndTravel();

			}
			else
				Move(v.getX() * -speed, v.getY() * -speed);
			
		}
	}
	
	protected boolean checkStairs(boolean isBack)
	{
		for (int point : WaitPoints) {
			if (point == AccualPoint)
			{
				if(!IsAfterStaris)
				{
					AccualPoint+=direction;
					IsWait = true;
					IsAfterStaris = true;
					Stairs.Instance().Go(this, TravelPoints.get(AccualPoint), isBack);
					return true;
				}
				
				IsAfterStaris = false;
			}
		}
		
		return false;
	}
	
	protected void IsOnEndPoint()
	{
		direction = -1;
	}
	
	protected void EndTravel()
	{
		IsInRestaurant = false;
	}
	
	protected void Sleep(int time)
	{
		try {
			sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void StairsEnd()
	{
		IsWait = false;
	}
}
