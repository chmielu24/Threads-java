import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
		
	
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(tableImage, 0,0, this);
	}
} 
