import javax.swing.SwingUtilities;

public class Window {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MyFrame myframe = new MyFrame("restauracja");
			}
		});

	}
	
	
	public Window(){
		
		
		
	}
} 
