import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame extends JFrame{

	public MyFrame(String title) throws HeadlessException{
		super(title);
		setSize(800,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel myPanel = new MyPanel();
		add(myPanel);
		setVisible(true);
		Timer timer = new Timer(50, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				myPanel.repaint();
			}
			
		});
		timer.start();
	}

}