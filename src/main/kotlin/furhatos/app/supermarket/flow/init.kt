package furhatos.app.supermarket.flow

import Sparql4
import furhatos.app.supermarket.flow.main.Idle
import furhatos.app.supermarket.flow.main.Recipe2
import furhatos.app.supermarket.setting.distanceToEngage
import furhatos.app.supermarket.setting.maxNumberOfUsers
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.util.Gender
import furhatos.util.Language


val Init : State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
        furhat.setVoice(language = Language.JAPANESE, gender = Gender.MALE)
        furhat.setMask("jamin")
        //furhat.voice = Voice("Matthew")


        /**set list("ユーザの発話を理解しやすくするために、Sparql4のListクラスを実行して、レシピ、材料をリストとして渡す)*/
        Sparql4.List()
        val a:ArrayList<String> =Sparql4.getList()
        /**furhat.setSpeechRecPhrasesに先程のリストを渡す。*/
        furhat.setSpeechRecPhrases(a)
        /** start the interaction */
        goto(Idle)
//        goto(Recipe2)
    }
}
