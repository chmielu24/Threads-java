
public class Stairs extends Thread {
	
	private static Stairs instance;
	
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
