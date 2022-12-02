package furhatos.app.supermarket.flow.main


import Noj
import Sudachi
import YesJ
import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.*

/**お客様に探している商品もしくはジャンル(ex.お菓子)を尋ねる*/
val Guidance: State = state(Parent) {
    onEntry {
        furhat.ask("何の商品、ジャンルを探していますか、商品名もしくはジャンルを教えてください。")
    }

    /** お客様が話した後の処理*/
    onResponse{
        val user_item: String  =it.text;
        val a=Sudachi();
        val b=a.sudachi1(user_item);

        /**類似する商品名が見つかった場合*/
        if (b.equals("商品が見つかりました。")){
            //furhat.say("お探しの商品は、"+a.sudachi(user_item)+ "にあります。")
            furhat.say("お探しの商品が見つかりました。")
            goto(confim1)
        }
        /**類似するジャンルが見つかった場合*/
        else if(b.equals("ジャンルが見つかりました。")){
            furhat.say("お探しの売り場が見つかりました。")
            goto(confim3)
        }
        /**類似する商品名、売り場が見つからなかった場合*/
        else{
            furhat.say("お探しの商品、および売り場が見つかりませんでした。")
            goto(Again)
        }
    }

}

/**他に探している商品、ジャンルがあるか確認*/
val Guidance2 : State = state(Parent) {
    onEntry {
        furhat.ask("他にお探しですか？")
    }

    /**他にも探しているものがある場合*/
    onResponse<YesJ> {
        furhat.say("承知いたしました。。")
        goto(Guidance)
    }

    /**他に探しているものは無い場合*/
    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }
    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Guidance2_1)
    }

    /**聞こえない場合*/
    onNoResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(Guidance2_1)
    }
}

/**基本的にはGuidance2と同じ処理*/
val Guidance2_1 : State = state(Parent) {
    onEntry {
        furhat.ask("他にお探しですか？")
    }

    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Guidance)
    }

    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Guidance2)
    }

    onNoResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        furhat.say("もう一度お尋ねします。")
        goto(Guidance2)
    }
}



