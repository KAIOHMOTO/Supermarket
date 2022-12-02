import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Jlist implements ListSelectionListener {
    private String[] items = { "牛肉","水","セロリ","にんじん" };
    private JList<String> list;
    private JLabel label;

    static JFrame frame = new JFrame("JListTest4");




    /** コンストラクタ*/
    public Jlist() {


        DefaultListModel model =new DefaultListModel();
        for(int i=0;i<items.length;i++){
            model.addElement(items[i]);
        }
        list = new JList<String>(model);        //itemsを元にJListを生成

        //変化の内容を表示するラベルを生成
        label = new JLabel("Message");
        //ボタンのイベント通知登録
        list.addListSelectionListener(this);
    }
    @Override
    /** リスト内で選択範囲が変更された時の処理 */
    public void valueChanged(ListSelectionEvent evt) {
        String str;
        if(evt.getValueIsAdjusting()) {		//選択中
            return;
        }
        else {
            //選択が変更された可能性のあるインデックスを取得
//            str = evt.getFirstIndex() + "-" + evt.getLastIndex();
            str= list.getSelectedValue();

            if(str=="水"){
                int x=190;
                int y=445;
                int w=80;
                int h=50;
                Panel5 panel5=new Panel5(x,y,w,h);
                frame.add(panel5);
            }else{
                Panel4 panel4=new Panel4();
                frame.add(panel4);
            }
        }
        //文字列をラベルに設定
        label.setText(str+"が選択されました");
    }
    /** main() */
    public static void main(String[] args) {

        Jlist sample = new Jlist();
//        JFrame frame = new JFrame("JListTest4");

        Panel4 panel4=new Panel4();
        panel4.setBackground(Color.WHITE);


        frame.setSize(1100,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(sample.list, "Center");
        frame.getContentPane().add(sample.label, "South");
        frame.pack();


        JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel4,sample.list);
        splitPane.setDividerLocation(5000);
        frame.add(splitPane);
//        frame.add(panel4);
        frame.setVisible(true);

    }


}