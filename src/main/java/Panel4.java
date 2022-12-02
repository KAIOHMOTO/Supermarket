import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel4 extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\k-omoto\\Pictures\\Camera Roll\\map.png"));
            g2d.drawImage(image, null, 10, 10);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
