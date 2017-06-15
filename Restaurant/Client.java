
public class Client extends Person {

	private boolean isWaitToWaiter = false;
	
	public Client(Point p) {
		super(p);
		
		TravelPoints.add(p);
		TravelPoints.add(new Point(100, 100));
		
	}
	
	public void run()
	{
		while(IsInRestaurant)
		{
			Update();
		}
		
		RestaurationManager.Instance().RemoveClient(this);
	}


	public boolean isWaitToWaiter() {
		return isWaitToWaiter;
	}


	public void setWaitToWaiter(boolean isWaitToWaiter) {
		this.isWaitToWaiter = isWaitToWaiter;
	}
}
