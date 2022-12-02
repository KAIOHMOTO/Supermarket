package furhatos.app.supermarket.flow.main

import DrawApp1
import DrawApp2
import Noj
import Panel2
import Search2
import Search4
import Sparql2
import Sudachi
import YesJ
import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.*
import org.apache.jena.reasoner.rulesys.builtins.Print

/**商品が見つかった場合の確認処理*/
val confim1: State = state(Parent) {

    /**類似する商品名とその製造会社を保存するためのitemとcompany*/
    val item:String =Search2().getter()
    val company:String = Search2().getter2()

    /**確認*/
    onEntry {
        furhat.ask("お探しの商品は、"+company+"の"+item+"で合っていますか？")
    }

    /**売り場の表示*/
    onResponse<YesJ> {
        furhat.say("お探しの商品の場所を表示します。")
        val pic=DrawApp1().drawapp1()
        goto(Guidance2)
    }

    /**Guidanceに戻る*/
    onResponse<Noj> {
        furhat.say("もう一度検索いたします。")
        goto(Guidance)
    }

    /**はい、いいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim1_1)
    }

    /**聞こえない場合*/
    onNoResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim1_1)
    }
}

/**基本的にはconfim1と同じ*/
val confim1_1: State = state(Parent) {

    val item:String =Search2().getter()
    val company:String = Search2().getter2()
    onEntry {
        furhat.ask("お探しの商品は、"+company+"の"+item+"で合っていますか？")
    }

    onResponse<YesJ> {
        furhat.say("お探しの商品の場所を表示します。")
        val pic=DrawApp1().drawapp1()
        goto(Guidance2)
    }

    onResponse<Noj> {
        furhat.say("もう一度検索いたします。")
        goto(Guidance)
    }

    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim1)
    }
    onNoResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim1)
    }

}

/**作りたい料理が見つかった場合*/
val confim2: State = state(Parent) {

    val item:String =Search4().getter()
    onEntry {
        furhat.ask("知りたいレシピは、"+item+"で合っていますか？")
    }

    /**合っている場合*/
    onResponse<YesJ> {
        furhat.say("承知致しました。")
        furhat.say("使う食材を伝えます。")
        val a: HashMap<String, String> = Search4().getHm();
        for ((key,value)in a){
            furhat.say(key)
         }
        furhat.say("です。")
        goto(confim4)
    }
    /**違う場合*/
    onResponse<Noj> {
        furhat.say("もう一度検索いたします。")
        goto(Recipe2)
    }

    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim2_1)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim2_1)
    }


}
/**基本的にはconfim2と同じ*/
val confim2_1: State = state(Parent) {

    val item:String =Search4().getter()
    onEntry {
        furhat.ask("知りたいレシピは、"+item+"で合っていますか？")
    }

    onResponse<YesJ> {
        furhat.say("承知致しました。")
        furhat.say("使う食材を伝えます。")
        val a: HashMap<String, String> = Search4().getHm();
        for ((key,value)in a){
            furhat.say(key)
        }
        furhat.say("です。")
        goto(confim4)
    }

    onResponse<Noj> {
        furhat.say("もう一度検索いたします。")
        goto(Recipe2)
    }

    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim2)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim2)
    }

}

/**希望するジャンルが見つかった場合*/
val confim3: State = state(Parent) {

    val category:String =Search2.getNear_category()
    val cateory_id:ArrayList<String> = Search2.getNear_category_id()
    onEntry {
        furhat.ask("お探しの売り場は、"+category+"で合っていますか？")
    }

    /**合っている場合*/
    onResponse<YesJ> {
        furhat.say("お探しの売り場を表示します。")
        val pic=DrawApp1().drawapp1_1()
        goto(Guidance2)
    }
    /**間違っている場合*/
    onResponse<Noj> {
        furhat.say("もう一度検索いたします。")
        goto(Guidance)
    }


    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim3_1)
    }

    /**聞こえない場合*/
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim3_1)
    }
}

/**基本的にはconfim3と同じ*/
val confim3_1: State = state(Parent) {

    val category:String =Search2.getNear_category()
    val cateory_id:ArrayList<String> = Search2.getNear_category_id()
    onEntry {
        furhat.ask("お探しの売り場は、"+category+"で合っていますか？")
    }

    /**合っている場合*/
    onResponse<YesJ> {
        furhat.say("お探しの売り場を表示します。")
        val pic=DrawApp1().drawapp1_1()
        goto(Guidance2)
    }

    onResponse<Noj> {
        furhat.say("もう一度検索いたします。")
        goto(Guidance)
    }

    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim3)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(confim3)
    }

}
val confim4: State = state(Parent) {

    val before_list:HashMap<String,String> = Search4().getHm()

    onEntry {
        furhat.ask("先程の材料一覧で、既に場所を知っていて、案内が必要のない食材はありますか？")
    }
    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(confim4_1)
    }

    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(confim4_2)
    }

    onResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim4_0)
    }

    onNoResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim4_0)
    }
}

val confim4_0: State = state(Parent) {

    val before_list:HashMap<String,String> = Search4().getHm()

    onEntry {
        furhat.ask("先程の材料一覧で、既に場所を知っていて、案内が必要のない食材はありますか？")
    }
    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(confim4_1)
    }

    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(confim4_2)
    }

    onResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim4)
    }

    onNoResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim4)
    }

}

val confim4_1: State = state(Parent) {
    onEntry{
        furhat.ask("その食材を教えてください。")
    }

    onResponse {
        val user_text: String = it.text;
        val a = Sudachi.sudachi5(user_text)
        val after_list:HashMap<String,String> = Search4().getHm()

        furhat.say("教えていただき、ありがとうございます。")
        goto(confim4_2)
    }
}


val confim4_2:State = state(Parent) {
    onEntry{
        val recipe:String =Search4().getter()
        val delete_list:ArrayList<String> =Sudachi.getDelete_list()
        val size=delete_list.size

        furhat.say(recipe+"に必要な食材の場所を案内します。")
        if(delete_list.size!=0){
            furhat.say("また、案内には")
            for(i in 0..size-1){
                furhat.say(delete_list.get(i))
            }
            furhat.say("の場所を表示していませんのでご注意ください。")

        }
        val a: HashMap<String, String> = Search4().getHm();
        for ((key,value)in a){
            println("確認:"+key+":"+value)
            DrawApp2(key,value).draw2()

        }
        furhat.say("全ての場所を表示しました。")
        delete_list.clear()
        goto(End)

    }
}

val confim5: State = state(Parent) {

    val item:String =Search4().getNear_item2()

    onEntry{
        furhat.say(item+"で使う食材を伝えます。")
        val a: HashMap<String, String> = Search4().getHm();
        for ((key,value)in a){
            furhat.say(key)
        }
        furhat.say("です。")
        goto(confim6)
    }

}

val confim6: State = state(Parent) {

    onEntry {
        furhat.ask("先程の材料一覧で、既に場所を知っていて、案内が必要のない食材はありますか？")
    }
    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(confim6_1)
    }

    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(confim6_2)
    }

    onResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim6_0)
    }

    onNoResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim6_0)
    }
}

val confim6_0: State = state(Parent) {


    onEntry {
        furhat.ask("先程の材料一覧で、既に場所を知っていて、案内が必要のない食材はありますか？")
    }
    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(confim6_1)
    }

    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(confim6_2)
    }

    onResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim4)
    }

    onNoResponse {
        furhat.say("すみません、「有る」か「無い」で答えて欲しいです。")
        goto(confim4)
    }

}

val confim6_1: State = state(Parent) {
    onEntry{
        furhat.ask("その食材を教えてください。")
    }

    onResponse {
        val user_text: String = it.text;
        val a = Sudachi.sudachi5(user_text)
        val after_list:HashMap<String,String> = Search4().getHm()

        furhat.say("教えていただき、ありがとうございます。")
        goto(confim6_2)
    }
}


val confim6_2:State = state(Parent) {
    onEntry{
        val recipe:String =Search4().getNear_item2()
        val item:String =Search4().getter()
        val delete_list:ArrayList<String> =Sudachi.getDelete_list()
        val size=delete_list.size

        furhat.say(recipe+"に必要な食材の場所を案内します。")
        if(delete_list.size!=0){
            furhat.say("また、案内には、お客様が使いたい"+item+"及び、")
            for(i in 0..size-1){
                furhat.say(delete_list.get(i))
            }
            furhat.say("の場所を表示していませんのでご注意ください。")

        }else{
            furhat.say("また、案内には、お客様が使いたい"+item+"の場所を表示していませんのでご注意ください。")
        }
        val a: HashMap<String, String> = Search4().getHm();
        for ((key,value)in a){
            println("確認:"+key+":"+value)
            DrawApp2(key,value).draw2()

        }
        furhat.say("全ての場所を表示しました。")
        delete_list.clear()
        goto(End)

    }
}