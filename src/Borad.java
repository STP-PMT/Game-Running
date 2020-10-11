
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
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
	boolean back = false;

	public Borad() {
		setSize(size_x, size_y);
		setLocation(0, 0);
		setLayout(null);
		t.scheduleAtFixedRate(new Move(this), 0, new Random().nextInt(30)+5);
		repaint();
	}

	public Image getImage(String Url) {
		return Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "images" + File.separator + Url);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(p.background, 0, 0, size_x, size_y, this);
		if (run_x == 900 || back) {
			back = true;
			g.drawImage(p.c2, run_x, run_y, 100, 100, this);
			if (run_x == 0) {
				back = false;
			}
		} else {
			g.drawImage(p.c1, run_x, run_y, 100, 100, this);
		}

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
			panel.run_y +=1;
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
		System.out.print("\r" + panel.run_x + " " + "0");
	}
}
