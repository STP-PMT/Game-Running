import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

public class Picture {

        Image background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator
                        + "src" + File.separator + "images" + File.separator + "Back.png");
        Icon[] allStart = new Icon[12];
        Icon[] player1 = new Icon[3];
        Icon[] player2 = new Icon[3];
        Icon[] player3 = new Icon[3];
        Icon[] player4 = new Icon[3];
        Icon[] player5 = new Icon[3];
        Icon[] player6 = new Icon[3];
        Icon[] player7 = new Icon[3];
        Icon[] player8 = new Icon[3];
        Icon[] player9 = new Icon[3];
        Icon[] player10 = new Icon[3];
        Icon[] player11 = new Icon[3];
        Icon[] player12 = new Icon[3];

        Picture() {
                setImage(player1, 1, 80, 80);
                setImage(player2, 2, 50, 50);
                setImage(player3, 3, 50, 50);
                setImage(player4, 4, 80, 80);
                setImage(player5, 5, 60, 60);
                setImage(player6, 6, 50, 50);
                setImage(player7, 7, 60, 60);
                setImage(player8, 8, 60, 60);
                setImage(player9, 9, 50, 50);
                setImage(player10, 10, 80, 80);
                setImage(player11, 11, 60, 60);
                setImage(player12, 12, 80, 80);
        }

        public void setImage(Icon[] player, int n, int x, int y) {
                player[0] = new ImageIcon(setPic("p" + n + "_center.gif").getScaledInstance(x, y, Image.SCALE_DEFAULT));
                player[1] = new ImageIcon(setPic("p" + n + "_left.gif").getScaledInstance(x, y, Image.SCALE_DEFAULT));
                player[2] = new ImageIcon(setPic("p" + n + "_right.gif").getScaledInstance(x, y, Image.SCALE_DEFAULT));
                allStart[n - 1] = new ImageIcon(
                                setPic("p" + n + "_center.gif").getScaledInstance(x, y, Image.SCALE_DEFAULT));

        }

        public Image setPic(String Url) {
                return Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
                                + File.separator + "images" + File.separator + Url);
        }

}
