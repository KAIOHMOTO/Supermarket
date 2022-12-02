import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.apache.lucene.search.spell.LevensteinDistance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search2 {

    static String near_item;
    static String company;
    static String near_category;

    static String near_item_id;

    static ArrayList<String> near_category_id;

    /**名詞を引数に今度は、引数の名詞と実際の商品、ジャンルをジャロ・ウィンクラー距離を用いて類似度を計算*/
    public String Search2(String item) {

        /**sudachiにて得られた名詞*/
        String target = item;

        /**商品リスト一覧のためのpath読み込み
         * pathが商品名一覧
         * path2がジャンル一覧*/

        Path path = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\use4.csv");
        Path path2 = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\class2.csv");

        /**単語間の距離を図るためのコード(ジャロ・ウィンクラー距離)*/
        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();

        /**比較のためのcount*/
        float count = (float) 0.65;
        /**csvファイルから得た商品名を保存するためのlist*/
        String list="";
        /**最終的に一番近い単語の名前を保存するnear_itemとその商品の会社名を保存するためのcompany
         * 一番近いジャンルを保存するためのnear_category
         * 一番近い商品、ジャンルの場所を保存するためのnear_item_id,near_category_id*/
        near_item="";
        company="";
        near_category="";
        near_item_id="";
        near_category_id=new ArrayList<String>();


        /**比較のためのcount*/
        float count2 = (float) 0.65;
        /**csvファイルから得たジャンルを保存するためのlist*/
        String category="";

        /**引数の名詞と商品名との類似度を計算*/
        try {
            // CSVファイルの読み込み
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split(",");
                list =data[3];
                /**初期値0.65よりスコアの大きいものが随時更新されていく。*/
                if (jaroWinklerDistance.getDistance(target, list)>=count) {
                    count= (float) jaroWinklerDistance.getDistance(target,list);
                    near_item=list;
                    company=data[5];
                }
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }

        /**引数の名詞とジャンルの類似度を計算*/
        try {
            // CSVファイルの読み込み
            List<String> lines2 = Files.readAllLines(path2);
            for (int i = 0; i < lines2.size(); i++) {
                String[] data = lines2.get(i).split(",");
                category =data[2];
                /**初期値0.65よりスコアの大きいものが随時更新されていく。*/
                if (jaroWinklerDistance.getDistance(target, category)>=count2) {
                    count2= (float) jaroWinklerDistance.getDistance(target,category);
                    near_category=category;
                }
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }

        Sparql sparql=new Sparql();

        /**商品名ともジャンルとも一致しない場合*/
        if(count == count2){
            System.out.println("出力結果は、item:"+count +"category:"+count2+"である");
            return "見つかりませんでした。";
        }
        /**引数の名詞が商品名と類似度が高い場合*/
        else if(count>count2){
            /**商品名を引数として、その商品が置かれている棚IDを取得*/
            near_item_id=sparql.sparql(near_item);

            System.out.println("出力結果は、item:"+count +"category:"+count2+"である");
            System.out.println("類似する単語は"+near_item+"である。");
            return "商品が見つかりました。";
        }
        /**引数の名詞がジャンルと類似度が高い場合*/
        else {
            /**ジャンルを引数として、そのジャンルに該当する棚IDを取得*/
            near_category_id=sparql.sparql2(near_category);

            System.out.println("出力結果は、item:"+count +"category:"+count2+"である");
            System.out.println("類似するカテゴリーは"+near_category+"である。");

            return "ジャンルが見つかりました。";
        }

    }

    public String getter(){
        return near_item;
    }

    public  String getter2(){
        return  company;
    }

    public static String getNear_category() {
        return near_category;
    }

    public static String getNear_item_id() {
        return near_item_id;
    }

    public static ArrayList<String> getNear_category_id() {
        return near_category_id;
    }
}

