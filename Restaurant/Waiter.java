public class Waiter extends Person {

	Client destinationClient;
	
	public Waiter(Point p, Client destination) {
		super(p);
		this.destinationClient = destination;
		
		TravelPoints.add(new Point(p.getX(), p.getY()));
		TravelPoints.add(destinationClient.position);
	}
	
	
	
	public void run()
	{
		while(IsInRestaurant)
		{
			Update();
			Sleep(100);
		}
		
		RestaurationManager.Instance().RemoveWaiter(this);
	}
	
	
	@Override
	protected void IsOnEndPoint() {
		super.IsOnEndPoint();
		
		destinationClient.waiterCome();
	}
}
