
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

import java.util.ArrayList;

public class Sparql {

    /**棚IDを取得したものを保存するためのID(商品名に用いる)*/
    static String ID;

    /**棚IDを取得したものを保存するためのCategory_ID(ジャンルに用いる)*/
    static ArrayList<String> Category_ID;

    /**Apache Jena Fusekiにアクセスするためのコード*/
    static RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination("http://localhost:3030/ddd");

    /**商品名の棚IDを取得するためのクエリ*/
    public String sparql(String text){

        /**itemに商品名を格納。*/
        String item=text;
        final String[] a = {""};
        ID="";


        final Query[] query = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "SELECT ?o WHERE" +
                        "{ ?s rdfs:label \"" + item + "\";" +
                        " :tana_position ?ID." +
                        "?ID rdfs:label ?o.}")};

        try(RDFConnectionFuseki  conn =(RDFConnectionFuseki)builder.build() ){
            conn.querySelect(query[0], (qs) -> {
                Literal subject = qs.getLiteral("o") ;
                a[0] =subject.getString();
            }) ;

        }
        ID =a[0];
//        System.out.println(ID);

        return ID;

    }

    /**ジャンルの棚IDを取得するためのクエリ*/
    public static ArrayList<String> sparql2(String text){

        /**near_categoryにジャンルを格納。*/
        String near_category=text;
        final String[] a = {""};
        Category_ID=new ArrayList<String>();

        final Query[] query2 = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>"+
                        "SELECT DISTINCT ?ID WHERE" +
                        "{?o rdfs:label \"" + near_category + "\"." +
                        " ?s rdfs:subClassOf* ?o."+
                        " ?s rdfs:label ?x."+
                        " ?I rdf:type ?s."+
                        " ?I :tana_position ?D."+
                        " ?D rdfs:label ?ID.}"
        )};

        try(RDFConnectionFuseki  conn =(RDFConnectionFuseki)builder.build() ){
            conn.querySelect(query2[0], (qs) -> {
                Literal subject = qs.getLiteral("ID") ;
//                System.out.println("Subject: "+subject.toString()) ;
                a[0] =subject.getString();
                Category_ID.add(a[0]);
            }) ;

        }

        return Category_ID;

    }



}
