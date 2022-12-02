import com.worksap.nlp.sudachi.*;
import com.worksap.nlp.sudachi.Dictionary;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Delete {

    public static void delete(ArrayList<String> list){


        HashMap<String,String> hm= new LinkedHashMap<>();

        hm=new Search4().getHm();


//        for(Map.Entry<String,String> entry:hm.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }

//        System.out.println("削除後");

        for(int i=0;i<list.size();i++){
            if (hm.containsKey(list.get(i))){
                hm.remove(list.get(i));
            }
        }

//        for(Map.Entry<String,String> entry:hm.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }

        Search4.setHm(hm);

    }
}
