import org.apache.jena.base.Sys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Reccomend {

    public static void reccomend(){
        ArrayList<String> copy=new ArrayList<>();
        ArrayList<String> a=new ArrayList<>();
        copy=Sparql4.getRecipe_list();
        Collections.shuffle(copy);

        for(int i=0;i<3;i++){
            a.add(copy.get(i));
        }
        Search4.Recipe_candidate=a;
    }

}
