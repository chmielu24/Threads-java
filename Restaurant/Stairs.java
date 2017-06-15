
public class Stairs extends Thread {
	
	private static Stairs instance;
	private Person AccualPersonOnStairs;
	
	private Point a = new Point(0, 0);
	private Point b = new Point(0, 0);
	
	public static Stairs Instance()
	{
		return instance;
	}
	
	public Stairs()
	{
		if(instance == null)
			instance = this;
	}
	
	@Override
	public void run()
	{
		if(this != Instance())
			return;
		
	}
	
	
	
}
