import com.worksap.nlp.sudachi.*;
import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.apache.commons.text.similarity.LongestCommonSubsequenceDistance;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Sudachi {

    static String a;

    static int b;


    static ArrayList<String> delete_list=new ArrayList<String>();


    /** Guidancenにてユーザの発話をsudachiを用いて、形態素解析を行う。 */
    public  String sudachi1(String user_item) {

        /**ユーザの発話を保存するためのtext　(ex.「かっぱえびせんはどこにありますか」)*/
        final String text = user_item;

        StringBuilder sb = new StringBuilder();

        try {
            Config config = Config.fromClasspath("sudachi/sudachi.json");
            Path path = Paths.get("C:\\Users\\k-omoto\\system_core.dic");
            config.systemDictionary(path);
            Dictionary dictionary = new DictionaryFactory().create(config);

            Tokenizer tokenizer = dictionary.create();

            /**名詞をみつけたらsbに追加していく*/
            for (List<Morpheme> list : tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, text)) {
                for (Morpheme morpheme : list) {
                    System.out.println(morpheme.surface() + "\t" + morpheme.partOfSpeech().get(0));

                    if(morpheme.partOfSpeech().get(0).equals("名詞")){
                        sb.append(morpheme.surface());
                    }
                }
            }

            System.out.println(sb.toString());

            /** itemはsudachi処理後の名詞保存場所*/
            String item=sb.toString();

            /**希望する商品もしくは、ジャンルがあるか*/
            Search2 search2 = new Search2();

            /**aにはsearch2クラスのSearch2メソッドreturnを格納*/
            /**商品が見つかったら,「商品が見つかりました。」
             * ジャンルが見つかったら「ジャンルが見つかりました。」
             * 商品もジャンルも見つからなかったら「見つかりませんでした。」
             */
            a=search2.Search2(item);


        } catch (IOException e) {
            System.out.println("ファイルを開くことができません。");
        }
        return a;

    }

    /**作りたい料理が決まっている場合の処理*/
    public  String sudachi2(String user_item) {

        /**textにはユーザの発話を保存する*/
        final String text = user_item;
        StringBuilder sb = new StringBuilder();


        try {
            Config config = Config.fromClasspath("sudachi/sudachi.json");
            Path path = Paths.get("C:\\Users\\k-omoto\\system_core.dic");
            config.systemDictionary(path);
            Dictionary dictionary = new DictionaryFactory().create(config);

            Tokenizer tokenizer = dictionary.create();

            for (List<Morpheme> list : tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, text)) {
                for (Morpheme morpheme : list) {
                    System.out.println(morpheme.surface() + "\t" + morpheme.partOfSpeech().get(0));

                    if(morpheme.partOfSpeech().get(0).equals("名詞")){
                        sb.append(morpheme.surface());
                    }
                }
            }

            System.out.println(sb.toString());

            /**recipeにはsudachiにて取り出した名詞を保存*/
            String recipe=sb.toString();

            /**希望するレシピがあるか検索*/
            Search4 search4 = new Search4();

            /**aには希望する料理があれば「見つかりました。」
             * 希望する料理がなければ「見つかりませんでした。」*/
            a=search4.Search4(recipe);
            //System.out.println("お探しの商品は"+search.Search(item)+"にあります。");

        } catch (IOException e) {
            System.out.println("ファイルを開くことができません。");
        }

        return a;

    }

    /**使いたい食材がある場合*/
    public  String sudachi3(String user_item) {

        /**textにはユーザの発話を保存*/
        final String text = user_item;
        StringBuilder sb = new StringBuilder();

        try {
            Config config = Config.fromClasspath("sudachi/sudachi.json");
            Path path = Paths.get("C:\\Users\\k-omoto\\system_core.dic");
            config.systemDictionary(path);
            Dictionary dictionary = new DictionaryFactory().create(config);

            Tokenizer tokenizer = dictionary.create();

            for (List<Morpheme> list : tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, text)) {
                for (Morpheme morpheme : list) {
                    System.out.println(morpheme.surface() + "\t" + morpheme.partOfSpeech().get(0));

                    if(morpheme.partOfSpeech().get(0).equals("名詞")){
                        sb.append(morpheme.surface());
                    }
                }
            }


            System.out.println(sb.toString());
            /**itemにはSudachiで得た名詞を格納*/
            String item=sb.toString();

            /**希望する材料を使ったレシピがあるか調べる*/
            Search4 search4 = new Search4();

            /**レシピがある場合には「見つかりました。」
             * ない場合には「見つかりませんでした。」*/
            a=search4.Search4_2(item);

        } catch (IOException e) {
            System.out.println("ファイルを開くことができません。");
        }

        return a;

    }

    public  String sudachi4(String user_item,int i) {

        /**textにはユーザの発話を保存*/
        final String text = user_item;
        StringBuilder sb = new StringBuilder();
        b=i;

        try {
            Config config = Config.fromClasspath("sudachi/sudachi.json");
            Path path = Paths.get("C:\\Users\\k-omoto\\system_core.dic");
            config.systemDictionary(path);
            Dictionary dictionary = new DictionaryFactory().create(config);

            Tokenizer tokenizer = dictionary.create();

            for (List<Morpheme> list : tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, text)) {
                for (Morpheme morpheme : list) {
                    System.out.println(morpheme.surface() + "\t" + morpheme.partOfSpeech().get(0));

                    if(morpheme.partOfSpeech().get(0).equals("名詞")){
                        sb.append(morpheme.surface());
                    }
                }
            }


            System.out.println(sb.toString());
            /**itemにはSudachiで得た名詞を格納*/
            String item=sb.toString();

            /**希望する材料を使ったレシピがあるか調べる*/
            Search4 search4 = new Search4();

            /**レシピがある場合には「見つかりました。」
             * ない場合には「見つかりませんでした。」*/
            a=search4.Search4_1(item);

        } catch (IOException e) {
            System.out.println("ファイルを開くことができません。");
        }

        return a;

    }


    public static void sudachi5(String text){
        try {
            Config config = Config.fromClasspath("sudachi/sudachi.json");
            Path path = Paths.get("C:\\Users\\k-omoto\\system_core.dic");
            config.systemDictionary(path);
            Dictionary dictionary = new DictionaryFactory().create(config);

            Tokenizer tokenizer = dictionary.create();

            delete_list=new ArrayList<>();

            /**名詞をみつけたらsbに追加していく*/
            for (List<Morpheme> list : tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, text)) {
                for (Morpheme morpheme : list) {
                    System.out.println(morpheme.surface() + "\t" + morpheme.partOfSpeech().get(0));

                    if(morpheme.partOfSpeech().get(0).equals("名詞")){
                        delete_list.add(morpheme.surface());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ファイルを開くことができません。");
        }

        Delete.delete(delete_list);
    }

    public static void setDelete_list(ArrayList<String> delete_list) {
        Sudachi.delete_list = delete_list;
    }

    public static ArrayList<String> getDelete_list() {
        return delete_list;
    }
}