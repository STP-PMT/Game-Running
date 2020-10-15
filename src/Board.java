import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Random;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameFrame s = new GameFrame();
	private Picture p = new Picture();

	private JButton newButton = new JButton("NEW GAME");
	private JButton ssButton = new JButton("START");

	private JLabel[] Character = new JLabel[12];
	private JLabel[] score = new JLabel[3];

	private final int[] X1 = { 565, 610, 660, 990, 1060, 1105, 555, 515, 470, 15, 65, 115 };
	private final int[] Y1 = { 485, 515, 560, 235, 270, 305, -20, 15, 55, 235, 270, 305 };
	private int[] X = { 565, 610, 660, 990, 1060, 1105, 555, 515, 470, 15, 65, 115 };
	private int[] Y = { 485, 515, 560, 235, 270, 305, -20, 15, 55, 235, 270, 305 };
	private int[] stop = new int[3];
	private int[] reset = new int[3];
	private int size_x = s.getX();
	private int size_y = s.getY();
	private int sum = 0; // ใช้สำหลับเก็บค่าคนที่ถึงเส้นชัยก่อน

	private Move[] team1 = new Move[4];
	private Move[] team2 = new Move[4];
	private Move[] team3 = new Move[4];

	private boolean isChecked = false;

	public Board() {
		setSize(size_x, size_y);
		setLocation(0, 0);
		setLayout(null);
		newButton.setSize(200, 50);
		newButton.setEnabled(false);
		newButton.setLocation(190, 150);
		ssButton.setSize(200, 50);
		ssButton.setLocation(190, 210);

		for (int i = 0; i < 12; i++) {
			Character[i] = new JLabel();
			Character[i].setLocation(X[i], Y[i]);
			Character[i].setSize(100, 100);
			Character[i].setIcon(p.allStart[i]);
			add(Character[i]);
		}
		setNumber();
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

				if (isChecked) {
					newButton.setEnabled(true);
					ssButton.setText("START");
					isChecked = false;

					for (int i = 0; i < X.length; i++) {
						Character[i].setIcon(p.allStart[i]);
					}
				} else {
					ssButton.setText("STOP");
					newButton.setEnabled(false);
					isChecked = true;
				}
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
			score[i].setText("");
		}
		for (int i = 0; i < X.length; i++) {
			X[i] = X1[i];
			Y[i] = Y1[i];
			Character[i].setLocation(X1[i], Y1[i]);
			Character[i].setSize(100, 100);
			Character[i].setIcon(p.allStart[i]);
		}
		sum = 0;
		setTeam();
	}

	public void setTeam() {
		team1[0] = new Move(Character[0], 0, 3, 1, 0, p.player1);
		team1[1] = new Move(Character[3], 3, 8, 2, 0, p.player4);
		team1[2] = new Move(Character[8], 8, 11, 3, 0, p.player9);
		team1[3] = new Move(Character[11], 11, 0, 4, 0, p.player12);

		team2[0] = new Move(Character[1], 1, 4, 1, 1, p.player2);
		team2[1] = new Move(Character[4], 4, 7, 2, 1, p.player5);
		team2[2] = new Move(Character[7], 7, 10, 3, 1, p.player8);
		team2[3] = new Move(Character[10], 10, 1, 4, 1, p.player11);

		team3[0] = new Move(Character[2], 2, 5, 1, 2, p.player3);
		team3[1] = new Move(Character[5], 5, 6, 2, 2, p.player6);
		team3[2] = new Move(Character[6], 6, 9, 3, 2, p.player7);
		team3[3] = new Move(Character[9], 9, 2, 4, 2, p.player10);

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

	public void setNumber() {
		Font font = new Font("", Font.BOLD, 20);
		JLabel[] order = new JLabel[3];
		JLabel[] num = new JLabel[3];

		for (int i = 0; i < 3; i++) {
			num[i] = new JLabel("" + (i + 1));
			num[i].setSize(100, 50);
			num[i].setFont(font);
			num[i].setForeground(Color.BLACK);

			score[i] = new JLabel("");
			score[i].setSize(100, 50);
			score[i].setFont(font);
			score[i].setForeground(Color.BLACK);

			order[i] = new JLabel("NO."+(i+1)+" : ");
			order[i].setSize(100, 50);
			order[i].setFont(font);
			order[i].setForeground(Color.BLACK);
		}

		num[0].setLocation(630, 520);
		num[1].setLocation(670, 555);
		num[2].setLocation(710, 595);

		score[0].setLocation(480, 135);
		score[1].setLocation(480, 180);
		score[2].setLocation(480, 225);

		order[0].setLocation(420, 135);
		order[1].setLocation(420, 180);
		order[2].setLocation(420, 225);
		
		for (int i = 0; i < 3; i++) {
			add(score[i]);
			add(num[i]);
			add(order[i]);
		}

	}

	class Move extends Thread {
		private JLabel label;

		private int index, j;
		private int sleep = 0;
		private int row;
		private int team;

		private boolean flag = false;
		private boolean Go1 = true;
		private boolean Go2 = true;
		private boolean Go3 = true;
		private boolean Go4 = true;

		private Icon action[];

		Move(JLabel label, int index, int j, int row, int team, Icon[] action) {
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
				label.setIcon((Icon) action[1]);
				label.setLocation(X[index], Y[index]--);
				if (Y[index] == Y1[j]) {
					label.setIcon((Icon) action[0]);
					Go1 = false;
					Board.this.stop[team] = 1;
					setSleep();
				}
			} else if (Board.this.stop[team] == 0) {
				label.setIcon((Icon) action[2]);
				label.setLocation(X[index]++, Y[index]);
			} else {
				if (Board.this.stop[team] == -1) {
					reset[team] = 1;
					if (Y[index] == Y1[index]) {
						label.setIcon((Icon) action[1]);
						reset[team] = 0;
						label.setLocation(X[index]--, Y[index]);
						if (X[index] == X1[index]) {
							label.setIcon((Icon) action[0]);
							Board.this.stop[team] = -5;
							if (sum == 0) {
								score[0].setText("Team  " + (team + 1));
								sum = 1;
							} else if (sum == 1) {
								score[1].setText("Team  " + (team + 1));
								sum = 2;
							} else {
								score[2].setText("Team  " + (team + 1));
							}
							System.out.println("Win");
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon) action[1]);
						label.setLocation(X[index], Y[index]++);
					}
				}
			}
		}

		public void runRow2() {
			if (Y[index] == Y1[j] && Go2) {
				label.setIcon((Icon) action[1]);
				label.setLocation(X[index]--, Y[index]);
				if (X[index] == X1[j]) {
					label.setIcon((Icon) action[0]);
					Go2 = false;
					Board.this.stop[team] = 2;
					setSleep();
				}
			} else if (Board.this.stop[team] == 1) {
				label.setIcon((Icon) action[1]);
				label.setLocation(X[index], Y[index]--);
			} else {
				if (Board.this.stop[team] == -2) {
					reset[team] = 1;
					if (X[index] == X1[index]) {
						label.setIcon((Icon) action[1]);
						reset[team] = 0;
						label.setLocation(X[index], Y[index]++);
						if (Y[index] == Y1[index]) {
							label.setIcon((Icon) action[0]);
							Board.this.stop[team] = -1;
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon) action[2]);
						label.setLocation(X[index]++, Y[index]);
					}
				}
			}
		}

		public void runRow3() {
			if (X[index] == X1[j] && Go3) {
				label.setIcon((Icon) action[2]);
				label.setLocation(X[index], Y[index]++);
				if (Y[index] == Y1[j]) {
					label.setIcon((Icon) action[0]);
					Go3 = false;
					Board.this.stop[team] = 3;
					setSleep();
				}
			} else if (Board.this.stop[team] == 2) {
				label.setIcon((Icon) action[1]);
				label.setLocation(X[index]--, Y[index]);
			} else {
				if (Board.this.stop[team] == -3) {
					reset[team] = 1;
					if (Y[index] == Y1[index]) {
						label.setIcon((Icon) action[2]);
						reset[team] = 0;
						label.setLocation(X[index]++, Y[index]);
						if (X[index] == X1[index]) {
							label.setIcon((Icon) action[0]);
							Board.this.stop[team] = -2;
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon) action[2]);
						label.setLocation(X[index], Y[index]--);
					}

				}
			}
		}

		public void runRow4() {
			if (Y[index] == Y1[j] && Go4) {
				label.setIcon((Icon) action[2]);
				label.setLocation(X[index]++, Y[index]);
				if (X[index] == X1[j]) {
					label.setIcon((Icon) action[0]);
					Go4 = false;
					Board.this.stop[team] = 4;
					setSleep();
				}
			} else if (Board.this.stop[team] == 3) {
				label.setIcon((Icon) action[2]);
				label.setLocation(X[index], Y[index]++);
			} else {

				if (Board.this.stop[team] == 4) {
					reset[team] = 1;
					if (X[index] == X1[index]) {
						label.setIcon((Icon) action[2]);
						reset[team] = 0;
						label.setLocation(X[index], Y[index]--);
						if (Y[index] == Y1[index]) {
							label.setIcon((Icon) action[0]);
							Board.this.stop[team] = -3;
						}
					}
					if (reset[team] == 1) {
						label.setIcon((Icon) action[1]);
						label.setLocation(X[index]--, Y[index]);
					}

				}
			}
		}
	}
}
