import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.apache.lucene.search.spell.LevensteinDistance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search3 {

    public static void main(String args[]){

        LevensteinDistance levensteinDistance = new LevensteinDistance();
        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();

        float lev_count = (float) 0.7;
        float jar_count = (float) 0.7;
        String target="パンケーキ";

        String list = "";

        String lev_item = "";
        String jar_item = "";
        String near_item ="";

        Path path = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\Recipe.csv");

        try {
            // CSVファイルの読み込み
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split(",");
                list = data[0];
                int score_1=list.lastIndexOf(target);
                /**countの初期値0.65より大きいレシピを随時更新*/
                if (levensteinDistance.getDistance(target, list) >= lev_count) {
                    lev_count = (float) levensteinDistance.getDistance(target, list);
                    lev_item = list;
                    System.out.println("lev:"+lev_item+",score:"+lev_count);
                }
                if (jaroWinklerDistance.getDistance(target, list) >= jar_count) {
                    jar_count = (float) jaroWinklerDistance.getDistance(target, list);
                    jar_item = list;
                    System.out.println("jar:"+jar_item+",score:"+jar_count);
                }

                if(score_1 != -1){
                    near_item=list;
                    System.out.println("後方文字列検索:"+near_item);
                }
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }







//        String a="ポテトチップス";
//        String b="ポテチ";
//        String c="コーラサンパンケーキ";
//        String d="パンケーキ";
//        String e="パンケーキ";
//        String f="白パン";
//        String g="パンケーキ";
//        String h="コーラサンパンケーキ";
//
//
//        System.out.println("levenstein");
//        System.out.println(a+"と"+ b+"の文字列類似度スコアは"+levensteinDistance.getDistance(a, b));
////        System.out.println(a+"と"+ a+"の文字列類似度スコアは"+levensteinDistance.getDistance(a, a));
//        System.out.println(c+"と"+ d+"の文字列類似度スコアは"+levensteinDistance.getDistance(c, d));
//        System.out.println(e+"と"+ f+"の文字列類似度スコアは"+levensteinDistance.getDistance(e,f));
//        System.out.println(g+"と"+ h+"の文字列類似度スコアは"+levensteinDistance.getDistance(g,h));
//
//        System.out.println("jarowinkler");
//        System.out.println(a+"と"+ b+"の文字列類似度スコアは"+jaroWinklerDistance.getDistance(a, b));
////        System.out.println(a+"と"+ a+"の文字列類似度スコアは"+jaroWinklerDistance.getDistance(a, a));
//        System.out.println(c+"と"+ d+"の文字列類似度スコアは"+jaroWinklerDistance.getDistance(c, d));
//        System.out.println(e+"と"+ f+"の文字列類似度スコアは"+jaroWinklerDistance.getDistance(e,f));
//        System.out.println(g+"と"+ h+"の文字列類似度スコアは"+levensteinDistance.getDistance(g,h));
    }
}
