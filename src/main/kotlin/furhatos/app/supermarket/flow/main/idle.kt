package furhatos.app.supermarket.flow.main

import furhatos.flow.kotlin.*

val Idle: State = state {

    init {
        when {
            users.count > 0 -> {
                furhat.attend(users.random)
                /**Greeting0に移動*/
                goto(Greeting0)

            }
            users.count == 0 && furhat.isVirtual() -> furhat.say("誰もいません.仮想空間にユーザを追加してください。 ")
            users.count == 0 && !furhat.isVirtual() -> furhat.say("誰もいません。近くに来てください")
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        /**Greeting0に移動*/
        goto(Greeting0)
    }
}
