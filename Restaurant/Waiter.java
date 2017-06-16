public class Waiter extends Person {

	Client destinationClient;
	
	public Waiter(Point p, Client destination) {
		super(p);
		this.destinationClient = destination;
		
		TravelPoints.add(new Point(400, 450));
		TravelPoints.add(destinationClient.position);
		speed *=2;
	}
	
	
	
	public void run()
	{
		while(IsInRestaurant)
		{
			Update();
			Sleep(16);
		}
		
		RestaurationManager.Instance().RemoveWaiter(this);
	}
	
	
	@Override
	protected void IsOnEndPoint() {
		super.IsOnEndPoint();
		
		destinationClient.waiterCome();
	}
}
