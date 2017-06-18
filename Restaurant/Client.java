
public class Client extends Person {

	private boolean isWaitToWaiter = false;
	private boolean isWaiterComming = false;
	private boolean isEat = false;
	private int TableIndex;
	
	public Client(Point p) {
		super(p);
	}
	
	public void AddPoint(Point p)
	{
		TravelPoints.add(p);
	}
	
	public void SetTableIndex(int i)
	{
		TableIndex = i;
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
			
			Sleep(16);
		}
		
		RestaurationManager.Instance().RemoveClient(this);
	}


	public boolean isWaitToWaiter() {
		return isWaitToWaiter;
	}

	public void setWaitToWaiter(boolean b) {
		this.isWaitToWaiter = b;

	}
	
	public synchronized void waiterCome() {
		isWaiterComming = false;
		isEat = true;
		
		this.notify();
	}
	
	@Override
	protected synchronized void IsOnEndPoint() {
		super.IsOnEndPoint();
		
		isWaiterComming = true;
		setWaitToWaiter(true);
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Eat()
	{
		this.Sleep(7000);
		RestaurationManager.Instance().FreeTable(TableIndex);
	}
}
