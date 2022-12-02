package furhatos.app.supermarket.flow.main


import Noj
import Reccomend
import Search4
import Sudachi
import YesJ
import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.*

/**お客様がレシピに関するサービスを利用したい場合の処理*/
val Recipe1: State = state(Parent) {
    onEntry {
        furhat.ask("作りたい料理がすでに決まっていますか？")
    }
    /**作りたい料理が決待っている場合*/
    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Recipe2)
    }
    /**作りたい料理が決まっていない場合*/
    onResponse<Noj>{
        furhat.say("承知いたしました。")
        goto(Recipe3)

    }
    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe1_1)
    }
    /**聞こえない場合*/
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe1_1)
    }

}

/**基本的にはRecipe1と同じ*/
val Recipe1_1: State = state(Parent) {
    onEntry {
        furhat.ask("作りたい料理がすでに決まっていますか？")
    }
    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Recipe2)
    }
    onResponse<Noj>{
        furhat.say("承知いたしました。")
        goto(Recipe3)

    }

    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe1)
    }
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe1)
    }

}

/**作りたい料理が決まっている場合*/
val Recipe2: State = state(Parent) {
    onEntry {
        furhat.ask("何の料理を作りたいですか？料理名を教えてください。")
    }


    onResponse{
        val text: String  =it.text;
        val a=Sudachi();
        val check=a.sudachi2(text);

        /**作りたい料理が見つかった場合*/
        if(check.equals("見つかりました。")){
            furhat.say("お客様の希望する料理に類似するレシピが見つかりました。")
            goto(confim2)
        }
        /**作りたい料理が見つからなかった場合*/
        else{
            furhat.say("お探しのレシピが見つかりませんでした。")
            goto(Again2)
        }
    }

}

/**作りたい料理が決まっていない場合*/
val Recipe3: State = state(Parent) {
    onEntry {
        furhat.ask("使いたい食材はありますか？")
    }
    /**使いたい食材がある場合*/
    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Recipe4)
    }
    /**使いたい食材がない場合*/
    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(Recipe7)
    }
    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe3_1)
    }

    /**聞こえない場合*/
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe3_1)
    }

}
val Recipe3_1: State = state(Parent) {
    onEntry {
        furhat.ask("使いたい食材はありますか？")
    }
    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Recipe4)
    }

    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(Recipe7)
    }

    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe3)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe3)
    }

}

/**使いたい食材がある場合*/
val Recipe4: State = state(Parent) {
    onEntry {
        furhat.ask("何の材料を使いたいですか？教えてください。")
    }

    onResponse{
        val text: String  =it.text;
        val a=Sudachi();
        val check=a.sudachi3(text);

        /**希望する食材を用いたレシピが見つかった場合*/
        if(check.equals("見つかりました。")){
            furhat.say("希望する材料を用いたレシピが見つかりました。")
            goto(Recipe5)
        }
        /**見つからなかった場合*/
        else{
            furhat.say("希望材料を用いたレシピが見つかりませんでした。")
            goto(Again2)
        }
    }

}

/**材料を用いたレシピを紹介する処理*/
val Recipe5: State = state(Parent) {
    onEntry {
        val a=Search4().getter();
        furhat.say("お客様の希望する"+a+"を用いたレシピを紹介します。")

        val b:ArrayList<String> = Search4.getRecipe_candidate();
        for(i in b){
            furhat.say(i)
        }
        furhat.say("です。")
        furhat.ask("今のレシピの紹介の中で作りたい料理はありますか？")
    }

    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(Recipe6)
    }
    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(Again2)
    }
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe5_1)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe5_1)
    }
}

/**材料を用いたレシピを紹介する処理*/
val Recipe5_1: State = state(Parent) {
    onEntry {
        val a=Search4().getter();
        furhat.say("お客様の希望する"+a+"を用いたレシピを紹介します。")

        val b:ArrayList<String> = Search4.getRecipe_candidate();
        for(i in b){
            furhat.say(i)
        }
        furhat.say("です。")
        furhat.ask("今のレシピの紹介の中で作りたい料理はありますか？")
    }

    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(Recipe6)
    }
    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(Again2)
    }
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe5)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Recipe5)
    }
}

val Recipe6: State = state(Parent) {
    onEntry {
        furhat.ask("どの料理を作りたいですか。料理名を教えてください。")
    }
    onResponse{
        val text: String  =it.text;
        val a=Sudachi();
        val b=1
        val check=a.sudachi4(text,b);

        /**候補の中の作りたい料理と一致した場合*/
        if(check.equals("見つかりました。")){
            furhat.say("承知致しました。")
            goto(confim5)
        }
        /**一致しなかった場合*/
        else{
            furhat.say("先程の候補の中からお探しのレシピが見つかりませんでした。")
            goto(Again2)
        }
    }
}

val Recipe7: State = state(Parent) {
    onEntry {
        Reccomend.reccomend()
        val a=Search4.getRecipe_candidate()
        furhat.say("作りたい料理、使いたい食材が無いようなので、レシピ一覧から3つのレシピ名を伝えます。")
        for(i in a){
            furhat.say(i)
        }
        goto(Recipe8)
    }

}
val Recipe8: State = state(Parent) {

    onEntry {
        furhat.ask("今のレシピの中で、材料も知りたい物はありましたか")
    }

    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(Recipe9)
    }

    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(Again2)
    }

    onResponse {
        furhat.say("すみません、あるかないかで答えてください。")
        goto(Recipe8_1)
    }

    onNoResponse {
        furhat.say("すみません、あるかないかで答えてください。")
        goto(Recipe8_1)
    }
}

val Recipe8_1: State = state(Parent) {

    onEntry {
        val a=Search4.getRecipe_candidate()
        for(i in a){
            furhat.say(i)
        }
        furhat.ask("今のレシピの中で、材料も知りたい物はありましたか")
    }

    onResponse<YesJ> {
        furhat.say("承知致しました。")
        goto(Recipe9)
    }

    onResponse<Noj> {
        furhat.say("承知致しました。")
        goto(Again2)
    }

    onResponse {
        furhat.say("すみません、あるかないかで答えてください。")
        goto(Recipe8)
    }

    onNoResponse {
        furhat.say("すみません、あるかないかで答えてください。")
        goto(Recipe8)
    }
}

val Recipe9: State = state(Parent) {
    onEntry {
        furhat.ask("どの料理を作りたいですか。料理名を教えてください。")
    }
    onResponse{
        val text: String  =it.text;
        val a=Sudachi();
        val b=2;
        val check=a.sudachi4(text,b);

        /**候補の中の作りたい料理と一致した場合*/
        if(check.equals("見つかりました。")){
            furhat.say("承知致しました。")
            goto(confim2)
        }
        /**一致しなかった場合*/
        else{
            furhat.say("先程の候補の中からお探しのレシピが見つかりませんでした。")
            goto(Again2)
        }
    }
}