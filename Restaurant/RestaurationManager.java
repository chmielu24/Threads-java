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

	
	private int MaxClients = 1;
	private float SpawnClientTimeCount = 3;
	private Point ClientSpawnPoint = new Point(20, 20);
	
	private Point WaiterSpawnPoint = new Point(10, 10);

	
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
		
		  ActionListener ClientSpawner = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  RestaurationManager.Instance().SpawnClient();
		      }
		  };
		  new Timer((int) (SpawnClientTimeCount * 1000), ClientSpawner).start();
		
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Client client : ClientList) {
				if(client.isWaitToWaiter())
				{
					SpawnWaiter(client);
					client.setWaitToWaiter(false);
				}
			}
		}
	}
	
	
	synchronized void SpawnClient()
	{
		if(ClientList.size() < MaxClients)
		{
			Client c = new Client(ClientSpawnPoint);
			c.start();
			ClientList.add(c);
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
	
}
