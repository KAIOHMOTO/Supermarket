package furhatos.app.supermarket.flow.main

import Noj
import YesJ
import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.*


/**希望する商品、ジャンルが上手く検索できなかった場合*/
val Again: State = state(Parent) {
    onEntry {
        furhat.ask("もう一度検索しますか？")
    }

    /**もう一度検索したい場合*/
    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Guidance)
    }

    /**検索しない場合*/
    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }
    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Again_1)
    }

    /**聞こえない場合*/
    onNoResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Again_1)
    }
}

/**基本的にはAgain1と同じ*/
val Again_1: State = state(Parent) {
    onEntry {
        furhat.ask("もう一度検索しますか？")
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
        goto(Again)
    }

    onNoResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Again)
    }

}

/**希望するレシピが見つからなかった場合*/
val Again2: State = state(Parent) {
    onEntry {
        furhat.ask("もう一度レシピを検索しますか？")
    }
    /**もう一度検索する場合*/
    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Recipe1)
    }
    /**検索しない場合*/
    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }
    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてください。")
        goto(Again2_1)
    }
    /**聞こえない場合*/
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてください。")
        goto(Again2_1)
    }
}

/**基本的にはAgain2と同じ*/
val Again2_1: State = state(Parent) {
    onEntry {
        furhat.ask("もう一度レシピを検索しますか？")
    }

    onResponse<YesJ> {
        furhat.say("承知いたしました。")
        goto(Recipe1)
    }

    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }
    onResponse{
        furhat.say("すみません、はいかいいえで答えてください。")
        goto(Again2)
    }

    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてください。")
        goto(Again2)
    }
}