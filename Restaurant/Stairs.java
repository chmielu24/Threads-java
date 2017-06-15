import java.util.PriorityQueue;

public class Stairs extends Thread {
	
	private static Stairs instance;
	private Person AccualPersonOnStairs = null;
	
	private PriorityQueue<Person> queue = new PriorityQueue<Person>();
	
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
		
		while(true)
		{
			if(AccualPersonOnStairs == null)
			{
				if(!queue.isEmpty())
				AccualPersonOnStairs = queue.remove();
				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void Go(Person p)
	{
		queue.add(p);
	}
	
}
