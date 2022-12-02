import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

@SuppressWarnings("serial")
public class DrawApp2 extends JFrame {



    private String ID;
    private String Zairyo;

    public DrawApp2(String zairyo,String id){
        Zairyo=zairyo;
        ID=id;
    }

    public DrawApp2(){

    }


    public void draw2() {

        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                DrawApp2 frame = new DrawApp2();
                frame.setSize(1100, 800);
//                System.out.println("DrawApp"+zairyo+":"+ID);

                System.out.println("MAPの引数のID:"+ID);
//                ID=id;
                System.out.println("代入後のID:"+ID);
                frame.setTitle(Zairyo+"の場所");
                frame.setDefaultCloseOperation(
                        WindowConstants.HIDE_ON_CLOSE);

                Panel2 panel2 = new Panel2(ID);
                panel2.setBackground(Color.WHITE);
                frame.add(panel2);

                frame.setVisible(true);
            }
        });
    }

}