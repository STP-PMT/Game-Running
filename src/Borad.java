
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Image;
import javax.swing.JLabel;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Borad extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameFrame s = new GameFrame();
	Picture p = new Picture();

	JButton newButton = new JButton("New Game");
	JButton ssButton = new JButton("Start");
	private int size_x = s.getX();
	private int size_y = s.getY();

	JLabel[] Character = new JLabel[12];
	int[] X = { 565, 610, 660, 990, 1040, 1090, 555, 515, 465, 15, 65, 115 };
	int[] Y = { 485, 525, 560, 235, 270, 305, 0, 25, 55, 235, 270, 305 };

	int[] X1 = { 565, 610, 660, 990, 1040, 1090, 555, 515, 465, 15, 65, 115 };
	int[] Y1 = { 485, 525, 560, 235, 270, 305, 0, 25, 55, 235, 270, 305 };

	public Borad() {
		setSize(size_x, size_y);
		setLocation(0, 0);
		setLayout(null);

		newButton.setSize(200, 50);
		newButton.setLocation(190, 150);

		ssButton.setSize(200, 50);
		ssButton.setLocation(190, 210);
		add(newButton);
		add(ssButton);

		for (int i = 0; i < 12; i++) {
			Character[i] = new JLabel(new ImageIcon(p.p1_right.getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
			Character[i].setLocation(X[i], Y[i]);
			Character[i].setSize(80, 80);
			add(Character[i]);
		}

		Move c1 = new Move(Character[0],0);
		c1.setSleep();
		Move c2 = new Move(Character[1],1);
		c2.setSleep();
		// Move c3 = new Move(Character[2], 2, 5, stop, go, 1);

		c1.start();
		c2.start();
		// c3.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(p.background, 0, 0, size_x - 15, size_y - 38, this);
	}

	class Move extends Thread {
		private JLabel label;
		private int index;
		private int sleep = 0;

		Move(JLabel label,int index) {
			this.label = label;
			this.index = index;
		}
	
		public void setSleep() {
			this.sleep = new Random().nextInt(30)+1;
		}
	
		@Override
		public void run() {
			while (true) {
				label.setLocation(X[index]++,Y[index]);
				try {
					Thread.sleep(sleep);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	
		}
	}
}



