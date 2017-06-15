
public class Client extends Person {

	private boolean isWaitToWaiter = false;
	private boolean isWaiterComming = false;
	private boolean isEat = false;

	
	public Client(Point p) {
		super(p);
		
		TravelPoints.add(new Point(p.getX(), p.getY()));
		TravelPoints.add(new Point(400, 90));
		TravelPoints.add(new Point(400, 600));
		TravelPoints.add(new Point(500, 600));
	}
	
	public void run()
	{
		while(IsInRestaurant)
		{
			if(!isWaiterComming)
			{
				Update();
			}
			
			if(isEat)
			{
				Eat();
				isEat=false;
			}
			
			Sleep(100);
		}
		
		RestaurationManager.Instance().RemoveClient(this);
	}


	public synchronized boolean isWaitToWaiter() {
		return isWaitToWaiter;
	}

	public synchronized void setWaitToWaiter(boolean b) {
		this.isWaitToWaiter = b;

	}
	
	public synchronized void waiterCome() {
		isWaiterComming = false;
		isEat = true;
	}
	
	@Override
	protected void IsOnEndPoint() {
		super.IsOnEndPoint();
		
		isWaiterComming = true;
		setWaitToWaiter(true);
	}
	
	public void Eat()
	{
		this.Sleep(5000);
	}
}
