import java.awt.Image;
import java.io.File;
import java.awt.Toolkit;

public class Picture {

    Image background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "images" + File.separator + "Background.jpg");
    Image p1_right = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "images" + File.separator + "p1_right.gif");
    Image p1_left = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "images" + File.separator + "p1_left.gif");

}
