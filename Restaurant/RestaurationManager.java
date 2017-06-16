import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class RestaurationManager extends Thread {
	
	private static RestaurationManager instance;
	
	public static RestaurationManager Instance()
	{
		return instance;
	}
	
	ArrayList<Client> ClientList = new ArrayList<Client> ();
	ArrayList<Waiter> WaiterList = new ArrayList<Waiter> ();

	
	private int MaxClients = 50;
	private float SpawnClientTimeCount = 2;
	private Point ClientSpawnPoint = new Point(590, 90);
	
	private Point WaiterSpawnPoint = new Point(190, 90);
	
	private boolean isTableReserved[] = new boolean[6];
	
	public RestaurationManager()
	{
		if(instance == null)
			instance = this;
	}
	
	@Override
	public void run()
	{
		if(this != Instance())
			return;
		new Stairs().start();

		
		  ActionListener ClientSpawner = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  RestaurationManager.Instance().SpawnClient();
		      }
		  };
		  new Timer((int) (SpawnClientTimeCount * 1000), ClientSpawner).start();
		
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			update();
		}
	}
	
	synchronized private void update()
	{
		for (Client client : ClientList) {
			if(client.isWaitToWaiter())
			{
				SpawnWaiter(client);
				client.setWaitToWaiter(false);
			}
		}
	}
	
	synchronized void SpawnClient()
	{
		if(ClientList.size() < MaxClients)
		{
			Client c = new Client(ClientSpawnPoint);
			if(findTable(c))
			{
				c.start();
				ClientList.add(c);
			}
			else
				c=null;
		}
	}
	
	synchronized void RemoveClient(Client c)
	{
		ClientList.remove(c);
	}
	
	synchronized void SpawnWaiter(Client destination)
	{
		Waiter w = new Waiter(WaiterSpawnPoint, destination);
		w.start();
		WaiterList.add(w);
	}
	
	synchronized void RemoveWaiter(Waiter c)
	{
		WaiterList.remove(c);
	}
	
	synchronized private boolean findTable(Client c)
	{
		for(int i=0; i< isTableReserved.length; i++)
		{
			if(!isTableReserved[i])
			{
				isTableReserved[i] = true;
				c.SetTableIndex(i);
				switch(i)
				{
					case 0: c.AddPoint(new Point(400, 450)); c.AddPoint(new Point(200, 450)); break;
					case 1: c.AddPoint(new Point(400, 450)); c.AddPoint(new Point(600, 450)); break;
					case 2: c.AddPoint(new Point(400, 650)); c.AddPoint(new Point(200, 650)); break;
					case 3: c.AddPoint(new Point(400, 650)); c.AddPoint(new Point(600, 650)); break;
					case 4: c.AddPoint(new Point(400, 550)); c.AddPoint(new Point(600, 550)); break;
					case 5: c.AddPoint(new Point(400, 550)); c.AddPoint(new Point(200, 550)); break;

				}
				
				return true;
			}
		}
		
		return false;
	}
	
	synchronized public void FreeTable(int i)
	{
		isTableReserved[i] = false;
	}
	
}
