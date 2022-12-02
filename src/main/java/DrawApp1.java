import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DrawApp1 extends JFrame {

    /**商品名の棚場所を表示(Ex.かっぱえびせんの場所)*/
    public void drawapp1() {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                DrawApp1 frame = new DrawApp1();
                frame.setSize(1100, 800);
                frame.setTitle(Search2.near_item+"の場所");
                frame.setDefaultCloseOperation(
                        WindowConstants.HIDE_ON_CLOSE);

                Panel1 panel1 = new Panel1();
                panel1.setBackground(Color.WHITE);
                frame.add(panel1);

                frame.setVisible(true);
            }
        });
    }

    /**売り場（ジャンル）の場所表示(Ex.お菓子売り場）*/
    public void drawapp1_1() {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                DrawApp1 frame = new DrawApp1();
                frame.setSize(1100, 800);
                frame.setTitle(Search2.getNear_category()+"の場所");
                frame.setDefaultCloseOperation(
                        WindowConstants.HIDE_ON_CLOSE);

                Panel3 panel3 = new Panel3();
                panel3.setBackground(Color.WHITE);
                frame.add(panel3);

                frame.setVisible(true);
            }
        });
    }
}