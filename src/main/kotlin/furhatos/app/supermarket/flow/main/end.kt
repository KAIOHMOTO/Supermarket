package furhatos.app.supermarket.flow.main

import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

/**終了の処理*/
val End : State = state(Parent) {
    onEntry {
        furhat.say("ご利用ありがとうございました。またのご利用お待ちしております。")
        goto(Idle)
    }

}