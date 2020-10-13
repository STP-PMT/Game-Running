import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

public class Picture {

        Image background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator
                        + "src" + File.separator + "images" + File.separator + "Back.png");

        Image p1_right = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
                        + File.separator + "images" + File.separator + "p1_right.gif");
        Image p1_left = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
                        + File.separator + "images" + File.separator + "p1_left.gif");
        Image p1_center = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator
                        + "src" + File.separator + "images" + File.separator + "p1_center.gif");
        Image p2_right = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
                        + File.separator + "images" + File.separator + "p2_right.gif");

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
                player1[0] = new ImageIcon(p1_center.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                player1[1] = new ImageIcon(p1_left.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                player1[2] = new ImageIcon(p1_right.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

                player2[0] = new ImageIcon(p2_right.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                player2[1] = new ImageIcon(p2_right.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                player2[2] = new ImageIcon(p2_right.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                
                System.out.println("Hello");
        }

}
