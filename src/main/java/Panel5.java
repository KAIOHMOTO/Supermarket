import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Panel5 extends JPanel {

    private int X;
    private int Y;
    private int W;
    private int H;

    public Panel5(int x,int y,int w,int h ){
        X=x;
        Y=y;
        W=w;
        H=h;
    }




    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(10.0f);

        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\k-omoto\\Pictures\\Camera Roll\\map.png"));
            g2d.drawImage(image, null, 10, 10);

        } catch (IOException e) {
            e.printStackTrace();
        }


        g2d.setColor(Color.RED);
        g2d.setStroke(stroke);
        g2d.drawRect(X,Y,W,H);


    }

}