public class Waiter extends Person {

	Client destinationClient;
	
	public Waiter(Point p, Client destination) {
		super(p);
		this.destinationClient = destination;
		
		TravelPoints.add(p);
		TravelPoints.add(destinationClient.position);
	}
	
	
	
	public void run()
	{
		while(IsInRestaurant)
		{
			Update();
			SleepAfterMove(100);
		}
		
		RestaurationManager.Instance().RemoveWaiter(this);
	}

}
