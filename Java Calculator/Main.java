import javax.swing.JFrame;

public class Main {
	
	public static void main(String[]args) {
		Calculator c = new Calculator();
		c.setSize(300, 300);
		c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c.setVisible(true);
		c.setResizable(false);
	}

}
