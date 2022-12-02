import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

import java.util.*;

public class Sparql2 {

    /**Apache Jena Fusekiを使うためのコード*/
    static RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination("http://localhost:3030/ddd");

    /**レシピの材料とその場所を保存するためのexMAp*/
    static Map<String,String> exMAp;
    /**棚IDを保存するためのID*/
    static String ID = "";

    /**材料からレシピ一覧を保存するためのRecipeList*/
    static ArrayList<String> Recipelist;

    /**レシピから材料とその場所を保存するための処理*/
    public static HashMap<String ,String> MAP(String A) {

        /**itemにレシピ名を格納。*/
        String item = A;
        final String[] a = {""};
        ID="";

        exMAp=new HashMap<String, String>();

        /**材料一覧を保存するためのtestList*/
        final List<String> testList = new ArrayList<String>();

        final Query[] query = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "PREFIX food: <http://purl.org/heals/food/>" +
                        "SELECT ?zairyo WHERE" +
                        "{ ?s rdfs:label \"" + item + "\"@ja;" +
                        "food:hasIngredient ?o." +
                        "?o rdfs:label ?zairyo." +
                        "FILTER (lang(?zairyo) = 'ja')}"
        )};

        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
            conn.querySelect(query[0], (qs) -> {
                Literal subject = qs.getLiteral("zairyo");
                a[0] = subject.getString();
                testList.add(a[0]);
            });
//            for (int i = 0; i < testList.size(); i++) {
//                System.out.println(i + 1 + "番目の食材は" + testList.get(i) + "です。");
//            }


        }

        /**材料一覧が格納されたtestListを用いて、Sparql3のaの処理を実行してIDを検索する*/

        for (int i = 0; i < testList.size(); i++) {
            Sparql3.a(testList.get(i));
            if (Sparql3.ID.equals("")){
                ID="--";
            }else{
                ID=Sparql3.ID;
            }
            /**キーに材料、値には棚IDを指定して保存する*/
            exMAp.put(testList.get(i),ID);

        }

//        for(Iterator<String> itr =exMAp.keySet().iterator();itr.hasNext();){
//            String key=itr.next();
//            System.out.println(key +" : " +exMAp.get(key));
//        }

        return (HashMap<String, String>) exMAp;


    }

    /**使いたい食材を使ったレシピを検索するための処理  */
    public static ArrayList<String> AL(String A) {

        /**Zairyoに使いたい食材を格納。*/
        String Zairyo = A;
        final String[] a = {""};
        Recipelist=new ArrayList<String>();

//        final List<String> testList = new ArrayList<String>();

        final Query[] query = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "PREFIX food: <http://purl.org/heals/food/>" +
                        "SELECT DISTINCT ?o WHERE" +
                        "{ ?recipe food:hasIngredient ?a." +
                        " ?a rdfs:label \"" + Zairyo + "\"@ja." +
                        " ?recipe rdfs:label ?o." +
                        "FILTER (lang(?o) = 'ja')}"
        )};

        System.out.println("zairyo");
        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
            conn.querySelect(query[0], (qs) -> {
                Literal subject = qs.getLiteral("o");
                a[0] = subject.getString();
                Recipelist.add(a[0]);
            });
//            System.out.println(Zairyo+"を用いた食材の候補は");
//            for (int i = 0; i < Recipelist.size(); i++) {
//                System.out.println(i + 1 + "番目は" + Recipelist.get(i) + "です。");
//            }

        }
        /**使いたい食材を用いたレシピを格納した配列を返す*/
        return Recipelist;


    }


}

