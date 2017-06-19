import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Window extends JPanel{
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame restaurantFrame = new JFrame("restaurant");
				restaurantFrame.setSize(800,825);
				restaurantFrame.setResizable(false);
				restaurantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				restaurantFrame.add(new Window());
				restaurantFrame.setVisible(true);		
			}
		});

	} 
	
	private  BufferedImage tableImage=null;
	private BufferedImage stairsImage=null;
	private BufferedImage backgroundImage=null;
	private BufferedImage entryImage=null;
	private BufferedImage kitchenImage=null;
	private BufferedImage waiterImage=null;
	private BufferedImage clientImage=null;
	public Window(){
		
		File tableFile = new File("img/table1.png");
		File stairsFile = new File("img/stairs.png");
		File backgroundFile = new File("img/background.png");
		File entryFile = new File("img/entry.png");
		File kitchenFile = new File("img/kitchen.png");
		File waiterFile = new File("img/waiter.png");
		File clientFile = new File("img/client.png");
		try {
			tableImage = ImageIO.read(tableFile);
			stairsImage = ImageIO.read(stairsFile);
			backgroundImage = ImageIO.read(backgroundFile);
			entryImage = ImageIO.read(entryFile);
			kitchenImage = ImageIO.read(kitchenFile);
			waiterImage = ImageIO.read(waiterFile);
			clientImage = ImageIO.read(clientFile);
			
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
		}
		
		new RestaurationManager().start();
		
		
		/*Timer timer = new Timer(1, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}
			
		});
		timer.start();*/
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
				{
					repaint();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();	
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(backgroundImage, 0, 0, this);
		
	//	g2.drawLine(400, 0, 400, 800);
		//table1
		//g2.drawImage(tableImage, 150, 50, this);
		

		
		g2.setColor(Color.WHITE);
		//g2.fillRect(150, 50, 100, 100);				//kitchen (150,50)
	//	g2.fillRect(550, 50, 100, 100);				//entry		(550,50)
		g2.drawImage(entryImage, 550, 50, this);
		
		g2.drawImage(kitchenImage, 150, 50, this);
		//table1
		g2.drawImage(tableImage, 150, 400, this);
		//table2
		g2.drawImage(tableImage, 150, 675, this);
		
		//table3
		g2.drawImage(tableImage, 550, 400, this);
		
		g2.drawImage(tableImage, 550, 575, this);
		g2.drawImage(tableImage, 150, 575, this);
		//table4
		g2.drawImage(tableImage, 550, 675, this);
					
		g2.drawImage(stairsImage, 360, 175,this);		//stairs
		g2.setColor(Color.BLUE);
		
		synchronized(RestaurationManager.Instance().ClientList)
		{
			for (Client c: RestaurationManager.Instance().ClientList) {
				g2.drawImage(clientImage, (int)c.getPosition().getX(), (int)c.getPosition().getY(),this);
				
				
			}
		}
		
		g2.setColor(Color.RED);
		synchronized(RestaurationManager.Instance().WaiterList)
		{
			for (Waiter w: RestaurationManager.Instance().WaiterList) {
				g2.drawImage(waiterImage, (int)w.getPosition().getX(), (int)w.getPosition().getY(),this);
			} 
		}
		
	}
} 

