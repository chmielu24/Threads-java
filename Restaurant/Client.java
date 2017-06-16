
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
	
	public void waiterCome() {
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
		this.Sleep(7000);
		RestaurationManager.Instance().FreeTable(TableIndex);
	}
}
