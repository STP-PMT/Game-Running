
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Borad extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameFrame s = new GameFrame();
	Picture p = new Picture();

	JButton newButton = new JButton("NEW GAME");
	JButton ssButton = new JButton("START/STOP");
	private int size_x = s.getX();
	private int size_y = s.getY();

	JLabel[] Character = new JLabel[12];
	int[] X = { 565, 610, 660, 990, 1040, 1090, 555, 515, 465, 15, 65, 115 };
	int[] Y = { 485, 525, 560, 235, 270, 305, -10, 25, 55, 235, 270, 305 };

	int[] X1 = { 565, 610, 660, 990, 1040, 1090, 555, 515, 465, 15, 65, 115 };
	int[] Y1 = { 485, 525, 560, 235, 270, 305, -10, 25, 55, 235, 270, 305 };

	Move[] team1 = new Move[4];
	Move[] team2 = new Move[4];
	Move[] team3 = new Move[4];

	int[] stop = new int[3];

	int reset[] = new int[3];

	public Borad() {
		setSize(size_x, size_y);
		setLocation(0, 0);
		setLayout(null);

		newButton.setSize(200, 50);
		newButton.setLocation(190, 150);

		ssButton.setSize(200, 50);
		ssButton.setLocation(190, 210);

		for (int i = 0; i < 12; i++) {
			Character[i] = new JLabel();
			Character[i].setLocation(X[i], Y[i]);
			Character[i].setSize(80, 80);
			Character[i].setIcon(p.player1[0]);
			add(Character[i]);
		}

		setTeam();

		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGameClicked();
			}
		});
		ssButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 4; i++) {
					team1[i].setFlag(!team1[i].isFlag());
					team2[i].setFlag(!team2[i].isFlag());
					team3[i].setFlag(!team3[i].isFlag());
				}
			}
		});
		add(newButton);
		add(ssButton);
	}

	private void newGameClicked() {
		for (int i = 0; i < 3; i++) {
			stop[i] = 0;
			reset[i] = 0;
		}
		for (int i = 0; i < X.length; i++) {
			X[i] = X1[i];
			Y[i] = Y1[i];
			Character[i].setLocation(X1[i], Y1[i]);
			Character[i].setSize(80, 80);
		}
		setTeam();
	}

	public void setTeam() {
		team1[0] = new Move(Character[0], 0, 3, 1, 0,p.player1);
		team1[1] = new Move(Character[3], 3, 8, 2, 0,p.player2);
		team1[2] = new Move(Character[8], 8, 11, 3, 0,p.player3);
		team1[3] = new Move(Character[11], 11, 0, 4, 0,p.player4);

		 team2[0] = new Move(Character[1], 1, 4, 1, 1,p.player5);
		 team2[1] = new Move(Character[4], 4, 7, 2, 1,p.player6);
		team2[2] = new Move(Character[7], 7, 10, 3, 1,p.player7);
		team2[3] = new Move(Character[10], 10, 1, 4, 1,p.player8);

		 team3[0] = new Move(Character[2], 2, 5, 1, 2,p.player9);
		 team3[1] = new Move(Character[5], 5, 6, 2, 2,p.player10);
		team3[2] = new Move(Character[6], 6, 9, 3, 2,p.player11);
		team3[3] = new Move(Character[9], 9, 2, 4, 2,p.player12);
	
		for (int i = 0; i < 4; i++) {
			team1[i].setSleep();
			team2[i].setSleep();
			team3[i].setSleep();

			team1[i].start();
			team2[i].start();
			team3[i].start();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(p.background, 0, 0, size_x - 15, size_y - 38, this);
	}

	class Move extends Thread {
		private JLabel label;
		private int index, j;
		private int sleep = 0;
		private boolean Go1 = true;
		private boolean Go2 = true;
		private boolean Go3 = true;
		private boolean Go4 = true;
		private int row;
		private int team;
		private boolean flag = false;
		private Icon action[];

		Move(JLabel label, int index, int j, int row, int team,Icon []action) {
			this.label = label;
			this.index = index;
			this.j = j;
			this.row = row;
			this.team = team;
			this.action = action;
		}

		public void setSleep() {
			this.sleep = new Random().nextInt(20) + 5;
			System.out.println(sleep);
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		@Override
		public void run() {
			while (true) {
				if (isFlag()) {
					if (row == 1) {
						runRow1();
					} else if (row == 2) {
						runRow2();
					} else if (row == 3) {
						runRow3();
					} else if (row == 4) {
						runRow4();
					}
				}
				try {
					Thread.sleep(sleep);
				} catch (Exception e) {

				}
			}

		}

		public void runRow1() {
			if (X[index] == X1[j] && Go1) {
				label.setIcon((Icon)action[1]);
				label.setLocation(X[index], Y[index]--);
				if (Y[index] == Y1[j]) {
					label.setIcon((Icon)action[0]);
					Go1 = false;
					Borad.this.stop[team] = 1;
					setSleep();
				}
			} else if (Borad.this.stop[team] == 0) {
				label.setIcon((Icon)action[2]);
				label.setLocation(X[index]++, Y[index]);
			} else {
				if (Borad.this.stop[team] == -1) {
					reset[team] = 1;
					if (Y[index] == Y1[index]) {
						label.setIcon((Icon)action[1]);
						reset[team] = 0;
						label.setLocation(X[index]--, Y[index]);
						if (X[index] == X1[index]) {
							label.setIcon((Icon)action[0]);
							Borad.this.stop[team] = -5;
							System.out.println("Win");
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon)action[1]);
						label.setLocation(X[index], Y[index]++);
					}
				}
			}
		}

		public void runRow2() {
			if (Y[index] == Y1[j] && Go2) {
				label.setIcon((Icon)action[1]);
				label.setLocation(X[index]--, Y[index]);
				if (X[index] == X1[j]) {
					label.setIcon((Icon)action[0]);
					Go2 = false;
					Borad.this.stop[team] = 2;
					setSleep();
				}
			} else if (Borad.this.stop[team] == 1) {
				label.setIcon((Icon)action[1]);
				label.setLocation(X[index], Y[index]--);
			} else {
				if (Borad.this.stop[team] == -2) {
					reset[team] = 1;
					if (X[index] == X1[index]) {
						label.setIcon((Icon)action[1]);
						reset[team] = 0;
						label.setLocation(X[index], Y[index]++);
						if (Y[index] == Y1[index]) {
							label.setIcon((Icon)action[0]);
							Borad.this.stop[team] = -1;
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon)action[2]);
						label.setLocation(X[index]++, Y[index]);
					}
				}
			}
		}

		public void runRow3() {
			if (X[index] == X1[j] && Go3) {
				label.setIcon((Icon)action[2]);
				label.setLocation(X[index], Y[index]++);
				if (Y[index] == Y1[j]) {
					label.setIcon((Icon)action[0]);
					Go3 = false;
					Borad.this.stop[team] = 3;
					setSleep();
				}
			} else if (Borad.this.stop[team] == 2) {
				label.setIcon((Icon)action[1]);
				label.setLocation(X[index]--, Y[index]);
			} else {
				if (Borad.this.stop[team] == -3) {
					reset[team] = 1;
					if (Y[index] == Y1[index]) {
						label.setIcon((Icon)action[2]);
						reset[team] = 0;
						label.setLocation(X[index]++, Y[index]);
						if (X[index] == X1[index]) {
							label.setIcon((Icon)action[0]);
							Borad.this.stop[team] = -2;
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon)action[2]);
						label.setLocation(X[index], Y[index]--);
					}

				}
			}
		}

		public void runRow4() {
			if (Y[index] == Y1[j] && Go4) {
				label.setIcon((Icon)action[2]);
				label.setLocation(X[index]++, Y[index]);
				if (X[index] == X1[j]) {
					label.setIcon((Icon)action[0]);
					Go4 = false;
					Borad.this.stop[team] = 4;
					setSleep();
				}
			} else if (Borad.this.stop[team] == 3) {
				label.setIcon((Icon)action[2]);
				label.setLocation(X[index], Y[index]++);
			} else {

				if (Borad.this.stop[team] == 4) {
					reset[team] = 1;
					if (X[index] == X1[index]) {
						label.setIcon((Icon)action[1]);
						reset[team] = 0;
						label.setLocation(X[index], Y[index]--);
						if (Y[index] == Y1[index]) {
							label.setIcon((Icon)action[0]);
							Borad.this.stop[team] = -3;
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon)action[1]);
						label.setLocation(X[index]--, Y[index]);
					}

				}

			}
		}
	}
}
