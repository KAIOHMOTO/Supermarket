import org.apache.jena.base.Sys;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sparql4 {

    static RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination("http://localhost:3030/ddd");

    /**レシピ、材料名を格納するためのList(後で辞書としてFurhatの自然言語処理に利用)*/
    static ArrayList<String> List;
    /**セール商品一覧を格納するSale_List*/
    static ArrayList<String> Sale_List;

    /**レシピ一覧を入れるためのRecipe_List*/
    static ArrayList<String> Recipe_list;
    public static void List() {

        List = new ArrayList<String>();
        Sale_List = new ArrayList<String>();
        Recipe_list=new ArrayList<String>();

        final String[] a = {""};
        final String[] b = {""};
        final String[] c = {""};

        String d="";

        Path path = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\class2.csv");

        /**レシピ一覧を取得するクエリ*/
        final Query[] query2 = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "PREFIX food: <http://purl.org/heals/food/>" +
                        "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
                        "SELECT ?label WHERE" +
                        "{?s rdf:type food:Recipe." +
                        "?s rdfs:label ?label." +
                        "FILTER (lang(?label) = 'ja')}"
        )};

        /**材料一覧を取得するクエリ*/
        final Query[] query3 = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "PREFIX food: <http://purl.org/heals/food/>" +
                        "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
                        "SELECT ?label WHERE" +
                        "{?s rdf:type food:Ingredient." +
                        "?s rdfs:label ?label." +
                        "FILTER (lang(?label) = 'ja')}"
        )};

        /**セール商品一覧を取得するクエリ*/
        final Query[] query4 = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "PREFIX food: <http://purl.org/heals/food/>" +
                        "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
                        "SELECT ?name WHERE" +
                        "{?s :sale_price ?o;" +
                        " rdfs:label ?name.}"
        )};

        List.add("とにんじん");
        List.add("いいえ");

        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
            conn.querySelect(query2[0], (qs) -> {
                Literal subject = qs.getLiteral("label");
                a[0] = subject.getString();
                List.add(a[0]);
                Recipe_list.add(a[0]);
            });
        }
        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
            conn.querySelect(query3[0], (qs) -> {
                Literal subject = qs.getLiteral("label");
                b[0] = subject.getString();
                List.add(b[0]);
            });
        }


        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
            conn.querySelect(query4[0], (qs) -> {
                Literal subject = qs.getLiteral("name");
                c[0] = subject.getString();
                Sale_List.add((c[0]));
            });
        }

        try {
            // CSVファイルの読み込み
            java.util.List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split(",");
                 d=data[2];
                 List.add(d);
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }
        System.out.println(List.size());
        System.out.println(Recipe_list.size());


    }


    public static ArrayList<String> getList() {
        return List;
    }

    public static ArrayList<String> getSale_List() {
        return Sale_List;
    }

    public static ArrayList<String> getRecipe_list() {
        return Recipe_list;
    }
}
