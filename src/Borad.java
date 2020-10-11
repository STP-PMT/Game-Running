
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Borad extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameFrame s = new GameFrame();
	Picture p = new Picture();
	Timer t = new Timer();
	private int size_x = s.getX();
	private int size_y = s.getY();

	int run_x = 0;
	int run_y = 0;
	int X = 0;
	int Y = 0;
	boolean back = false;

	public Borad() {
		setSize(size_x, size_y);
		setLocation(0, 0);
		setLayout(null);
		t.scheduleAtFixedRate(new Move(this), 0, new Random().nextInt(30) + 5);

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				X = e.getX();
				Y = e.getY();
			}

		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(p.background, 0, 0, size_x - 15, size_y - 38, this);
		//ชุดแรก
		g.drawImage(p.p1_right, 565, 485, 80, 80, this);
		g.drawImage(p.p1_right, 610, 525, 80, 80, this);
		g.drawImage(p.p1_right, 660, 560, 80, 80, this);
		//ชุด2
		g.drawImage(p.p1_left, 990, 235, 80, 80, this);
		g.drawImage(p.p1_left, 1040, 270, 80, 80, this);
		g.drawImage(p.p1_left, 1090, 305, 80, 80, this);
		//ชุด3
		g.drawImage(p.p1_left, 555, 0, 80, 80, this);
		g.drawImage(p.p1_left, 515, 25, 80, 80, this);
		g.drawImage(p.p1_left, 465, 55, 80, 80, this);
		//ชุด4
		g.drawImage(p.p1_right, 15, 235, 80, 80, this);
		g.drawImage(p.p1_right, 65, 270, 80, 80, this);
		g.drawImage(p.p1_right, 115, 305, 80, 80, this);
	}
}

class Move extends TimerTask {
	private Borad panel;

	Move(Borad panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		if (panel.back) {
			panel.run_y += 1;
			if (panel.run_y == 500) {
				panel.back = false;
			}
		} else {
			if (panel.run_y == 0 || panel.back) {
				panel.back = true;
			}
			panel.run_y += 1;
		}

		this.panel.repaint();
		System.out.print("\r" + panel.X + " " + panel.Y);
	}
}
