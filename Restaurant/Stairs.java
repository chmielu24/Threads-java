import java.util.ArrayDeque;
import java.util.Deque;

public class Stairs extends Thread {
	
	private static Stairs instance;
	private Person AccualPersonOnStairs = null;
	private Point AccualPoint = null;
	
	private Deque<Person> queue = new ArrayDeque<Person>();
	private Deque<Point> queuePoints = new ArrayDeque<Point>();
	
	private Deque<Person> queue2 = new ArrayDeque<Person>();
	private Deque<Point> queuePoints2 = new ArrayDeque<Point>();

	private boolean isWait = false;
	private float speed = 2f;
	
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
		this.setPriority(MAX_PRIORITY);

		if(this != Instance())
			return;
		
		while(true)
		{
			Update();

			try {
				sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void Update()
	{
		if(AccualPersonOnStairs == null)
		{
				if(!queue2.isEmpty())
				{
					AccualPersonOnStairs = queue2.pop();
					AccualPoint = queuePoints2.pop();
				}
				else
				if(!queue.isEmpty())
				{
					AccualPersonOnStairs = queue.pop();
					AccualPoint = queuePoints.pop();
				}
				else if (!isWait)
				{
					try {
						isWait = true;
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		}
		else
		{
			if(Point.Distance(AccualPoint, AccualPersonOnStairs.getPosition()) <= speed)
			{
				AccualPersonOnStairs.setPosition(AccualPoint);
				AccualPersonOnStairs.StairsEnd();
				AccualPersonOnStairs = null;
				AccualPoint = null;
			}
			else
			{
				Point v = Point.NormalizedVector(AccualPoint, AccualPersonOnStairs.getPosition());
				AccualPersonOnStairs.Move(v.getX() * speed, v.getY() * speed);
			}
			
			
		}
	}
	
	public synchronized void Go(Person p, Point dest, boolean isBack)
	{
		if(isWait)
		{
			this.notify();
			isWait = false;
		}
		
		if(isBack)
		{
			queue2.addLast(p);
			queuePoints2.addLast(dest);
		}
		else
		//if(p.getClass().equals(Waiter.class))
		//{
			//queue.addFirst(p);
			//queuePoints.addFirst(dest);
		//}
		//else
		{
			queue.addLast(p);
			queuePoints.addLast(dest);
		}
		
	}
	
}
