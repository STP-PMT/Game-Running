
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		Borad borad = new Borad();
		frame.add(borad);
		frame.setVisible(true);
	}

}

class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int size_x = 1200;
	private int size_y = 700;

	GameFrame() {
		setSize(size_x, size_y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
	}
	
	public int getX() {
		return size_x;
	}
	
	public int getY() {
		return size_y;
	}
}
