package furhatos.app.supermarket.flow.main

import Kensaku
import Noj
import Recipe
import Sale
import YesJ
import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.*

/**一番最初の処理*/
val Greeting0 : State = state(Parent) {
    onEntry {
        furhat.ask("いらっしゃいませ。ご利用は初めてですか？")
    }

    /**お客様が初めて利用する場合*/
    onResponse<YesJ> {
        furhat.say("承知いたしました。私はお客様のお買い物の手助けをします。具体的には、商品の場所を検索する、料理のレシピを伝える、チラシの情報を伝える事が出来ます。")
        goto(Greeting1)
    }

    /**すでに利用したことがある場合*/
    onResponse<Noj> {
        furhat.say("いつもご利用いただき、ありがとうございます。")
        goto(Greeting1)
    }
    /**はいかいいえ以外の発話をした際の処理*/
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Greeting0_1)
    }
    /**聞こえない場合*/
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Greeting0_1)
    }
}

/**内容はGreeting0と同じだが、Greeting0で「はい」「いいえ」以外で回答した場合はこちらに来る*/
val Greeting0_1 : State = state(Parent) {
    onEntry {
        furhat.ask("いらっしゃいませ。ご利用は初めてですか？")
    }

    onResponse<YesJ> {
        furhat.say("承知いたしました。私はお客様のお買い物の手助けをします。具体的には、商品の場所を検索する、料理のレシピを伝える、チラシの情報を教える事が出来ます。")
        goto(Greeting1)
    }
    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(Greeting1)
    }
    onResponse{
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Greeting0)
    }
    onNoResponse {
        furhat.say("すみません、はいかいいえで答えてほしいです。")
        goto(Greeting0)
    }
}

/**お客さんに使いたい機能を尋ねる*/
val Greeting1 : State = state(Parent) {
    onEntry {
        furhat.ask("どの機能を使われますか？")
    }

    /**商品に関するサービスを希望した場合*/
    onResponse<Kensaku> {
        furhat.say("商品の棚検索ですね。承知いたしました。")
        goto(Guidance)
    }

    /**レシピに関するサービスを希望した場合 */
    onResponse<Recipe> {
        furhat.say("レシピの提供ですね。承知いたしました。")
        goto(Recipe1)
    }
    /**チラシに関するサービスを希望した場合 */
    onResponse<Sale> {
        furhat.say("チラシの情報ですね。承知いたしました。")
        goto(leaflet1)
    }

    /**サービスを希望しない場合（間違えたなど）*/
    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }

    /**お客様が関係のないことを言った場合*/
    onResponse{
        furhat.say("すみません、商品検索、レシピ検索、チラシ情報、いいえのどれかで答えてください。")
        goto(Greeting1_1)
    }
    /**聞こえない場合*/
    onNoResponse{
        furhat.say("すみません、商品検索、レシピ検索、チラシ情報、いいえのどれかで答えてください。")
        goto(Greeting1_1)
    }
}

/**基本的にはGreeting1と中身は同じ*/
val Greeting1_1 : State = state(Parent) {
    onEntry {
        furhat.ask("どの機能を使われますか？")
    }

    onResponse<Kensaku> {
        furhat.say("商品の棚検索ですね。承知いたしました。")
        goto(Guidance)
    }

    onResponse<Recipe> {
        furhat.say("レシピの提供ですね。承知いたしました。")
        goto(Recipe1)
    }
    onResponse<Sale> {
        furhat.say("チラシの情報ですね。承知いたしました。")
        goto(leaflet1)
    }

    onResponse<Noj> {
        furhat.say("承知いたしました。")
        goto(End)
    }

    onResponse{
        furhat.say("すみません、商品検索、レシピ検索、チラシ情報、いいえのどれかで答えてください。")
        goto(Greeting1)
    }

    onNoResponse{
        furhat.say("すみません、商品検索、レシピ検索、チラシ情報、いいえのどれかで答えてください。")
        goto(Greeting1)
    }
}

