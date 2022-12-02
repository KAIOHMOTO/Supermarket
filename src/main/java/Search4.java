
import org.apache.lucene.search.spell.JaroWinklerDistance;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search4 {

    static String near_item;

    static String near_item2;

    static HashMap<String, String> hm;

    static ArrayList<String> Recipe_candidate;

    /**
     * 希望する料理がレシピデータベースにあるか検索
     */
    public String Search4(String item) {

        /**お客様が希望する料理をtargetに格納*/
        String target = item;

        /**hmには材料とその棚IDをMAPとして保持*/
        hm = new HashMap<String, String>();

        /**レシピ一覧のためのpath読み込み*/
        Path path = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\Recipe.csv");

        /**単語間の距離を図るためのコード(ジャロ・ウィンクラー距離を使用)*/
        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();

        /**類似度を比較のためのcount*/
        float count = (float) 0.65;
        /**csvファイルから得たレシピ名を保存するためのlist*/
        String list = "";
        /**最終的に一番近い単語(レシピ)の名前を保存するnear_item*/
        near_item = "";

        String kensaku = "";


        try {
            // CSVファイルの読み込み
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split(",");
                list = data[0];
                /**countの初期値0.65より大きいレシピを随時更新*/
                if (jaroWinklerDistance.getDistance(target, list) >= count) {
                    count = (float) jaroWinklerDistance.getDistance(target, list);
                    near_item = list;
                }
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }
        System.out.println("出力結果は、" + count);
        System.out.println("類似する単語(レシピ)は" + near_item + "である。");

        kensaku = near_item;

        /**sparql2にて材料とその場所を検索する*/
        Sparql2 sparql2 = new Sparql2();
        hm = sparql2.MAP(kensaku);

//        System.out.println("hm");
//        for(Iterator<String> itr = hm.keySet().iterator(); itr.hasNext();){
//            String key=itr.next();
//            System.out.println(key +" : " +hm.get(key)+"です。");
//        }

        /**希望する料理と類似度が高いレシピが見つかった場合*/
        if (kensaku != "") {
            return "見つかりました。";
        }
        /**見つからなかった場合*/
        else {
            return "見つかりませんでした。";
        }

    }

    /**
     * ユーザの発話のレシピが候補に入っていたかどうかの判断。
     */
    public String Search4_1(String item) {

        /**お客様が希望する料理をtargetに格納*/
        String target = item;

        /**hmには材料とその棚IDをMAPとして保持*/
        hm = new HashMap<String, String>();

        /**単語間の距離を図るためのコード(ジャロ・ウィンクラー距離を使用)*/
        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();

        /**類似度を比較のためのcount*/
        float count = (float) 0.65;

        /**最終的に一番近い単語(レシピ)の名前を保存するnear_item2*/
        near_item2 = "";

        String kensaku = "";


        for (int i = 0; i < Recipe_candidate.size(); i++) {
            if (jaroWinklerDistance.getDistance(target, Recipe_candidate.get(i)) >= count) {
                count = (float) jaroWinklerDistance.getDistance(target, Recipe_candidate.get(i));
                near_item2 = Recipe_candidate.get(i);
            }
        }


        System.out.println("類似する単語(レシピ)は" + near_item2 + "である。");

        kensaku = near_item2;

        /**sparql2にて材料とその場所を検索する*/
        Sparql2 sparql2 = new Sparql2();
        hm = sparql2.MAP(kensaku);


        if(Sudachi.b==1){
            hm.remove(near_item);
        }else{
            near_item=kensaku;
        }

//        System.out.println("hm");
//        for(Iterator<String> itr = hm.keySet().iterator(); itr.hasNext();){
//            String key=itr.next();
//            System.out.println(key +" : " +hm.get(key)+"です。");
//        }

        Sudachi.b=0;
        if (kensaku != "") {
            return "見つかりました。";
        }
        /**見つからなかった場合*/
        else {
            return "見つかりませんでした。";
        }

    }


    /**材料からレシピを検索するための処理*/
    public String Search4_2(String item) {

        /**名詞（材料）をtargetに格納*/
        String target = item;

        /**材料リスト一覧のためのpath読み込み*/
        Path path = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\Ingredient.csv");

        /**単語間の距離を図るためのコード(ジャロ・ウィンクラー距離)*/
        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();

        /**比較のためのcount*/
        float count = (float) 0.65;
        /**csvファイルから得た商品名を保存するためのlist*/
        String list = "";
        /**最終的に一番近い単語(材料)の名前を保存するnear_item*/
        near_item = "";

        /**使いたい食材を用いたレシピを格納するためのRecipe_candidate*/
        Recipe_candidate=new ArrayList<String >();

        String kensaku="";


        try {
            // CSVファイルの読み込み
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split(",");
                list = data[0];
                if (jaroWinklerDistance.getDistance(target, list) >= count) {
                    count = (float) jaroWinklerDistance.getDistance(target, list);
                    near_item = list;
                }
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }
        System.out.println("出力結果は、" + count);
        System.out.println("類似する単語(材料)は" + near_item + "である。");

        kensaku=near_item;
        //System.out.println("kensakuの中身は"+kensaku);


        /**Sparql2のAlで希望する食材を用いたレシピを検索してリストとして返す*/
        Sparql2 sparql2=new Sparql2();
        Recipe_candidate=sparql2.AL(near_item);

        if(kensaku!=""){
            return "見つかりました。";
        }
        else{
            return "見つかりませんでした。";
        }

    }

    public String getter() {
        return near_item;
    }

    public String getNear_item2() {
        return near_item2;
    }

    public HashMap<String, String> getHm() {
        return hm;
    }

    public static void setHm(HashMap<String, String> hm) {
        Search4.hm = hm;
    }

    public static ArrayList<String> getRecipe_candidate() {
        return Recipe_candidate;
    }
}


