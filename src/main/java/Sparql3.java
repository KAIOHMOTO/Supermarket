import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

import java.util.ArrayList;
import java.util.List;

public class Sparql3 {

    /**Apache Jena Fusekiを使うためのコード*/
    static RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination("http://localhost:3030/ddd");

    /**材料のIDを保存するためのID*/
    static String ID;

    /**材料からその棚IDを取得するための処理*/
    public static void a(String item) {

        String zairyo = item;
        ID = "";
        final String[] a = {""};
        final Query[] query2 = {QueryFactory.create(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "PREFIX :     <http://ke.it.aoyama.ac.jp/super_market/>" +
                        "PREFIX food: <http://purl.org/heals/food/>" +
                        "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
                        "SELECT ?ID WHERE" +
                        "{ ?s rdfs:label \"" + zairyo + "\"@ja;" +
                        "skos:related ?o." +
                        "?a rdf:type ?o." +
                        "?a :tana_position ?aa." +
                        "?aa rdfs:label ?ID}" +
                        "LIMIT 1"

        )};

        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
            conn.querySelect(query2[0], (qs) -> {
                Literal subject = qs.getLiteral("ID");
                a[0] = subject.getString();
                ID = a[0];
            });


        }
    }
}
