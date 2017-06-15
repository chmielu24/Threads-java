import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
				restaurantFrame.setSize(800,800);
				restaurantFrame.setResizable(false);
				restaurantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				restaurantFrame.add(new Window());
				restaurantFrame.setVisible(true);		
			}
		});

	} 
	
	private  BufferedImage tableImage=null;
	
	public Window(){
		
		File imageFile = new File("img/s.png");
		
		try {
			tableImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
		}
		
		new RestaurationManager().start();
		
		Timer timer = new Timer(50, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}
			
		});
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
	//	g2.drawLine(400, 0, 400, 800);
		
		
		g2.setColor(Color.WHITE);
		g2.fillRect(150, 50, 100, 100);				//kitchen (150,50)
		g2.fillRect(550, 50, 100, 100);				//entry		(550,50)
		//table1
		g2.drawImage(tableImage, 150, 400, this);
		//table2
		g2.drawImage(tableImage, 150, 650, this);
		
		//table3
		g2.drawImage(tableImage, 550, 400, this);
		//table4
		g2.drawImage(tableImage, 550, 650, this);
		
		g2.setColor(Color.GREEN);
		g2.fillRect(350, 175, 100, 175);			//stairs
		g2.setColor(Color.BLUE);
		for (Client c: RestaurationManager.Instance().ClientList) {
			g2.fillRect((int)c.getPosition().getX(), (int)c.getPosition().getY(), 25, 25);
			
		}
		
		g2.setColor(Color.RED);
		for (Waiter w: RestaurationManager.Instance().WaiterList) {
			g2.fillRect((int)w.getPosition().getX(), (int)w.getPosition().getY(), 25, 25);
		} 
	}
} 

