import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Panel3 extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\k-omoto\\Pictures\\Camera Roll\\map.png"));
            g2d.drawImage(image, null, 10, 10);
            BasicStroke stroke = new BasicStroke(10.0f);

            /**棚IDと対応した座標を示したファイル*/
            Path path1 = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\tana_draw.csv");

            /**赤い四角を囲むための変数*/
            int x=0;
            int y=0;
            int width=0;
            int height=0;

            /**売り場のID一覧を格納*/
            ArrayList<String> list=Search2.getNear_category_id();
            try {
                List<String> line1 = Files.readAllLines(path1);
                for(int i=0;i<list.size();i++){
                    String ID=list.get(i);
                    for(int j=0;j<line1.size();j++){
                        String[] data=line1.get(j).split(",");
                        if(ID.equals(data[0])){
                            x= Integer.parseInt(data[2]);
                            y= Integer.parseInt(data[3]);
                            width=Integer.parseInt(data[4]);
                            height=Integer.parseInt(data[5]);
                            System.out.println("x:"+x+"y:"+y+"width"+width+"height:"+height);
                        }
                    }
                    g2d.setColor(Color.RED);
                    g2d.setStroke(stroke);
                    g2d.drawRect(x,y,width,height);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}