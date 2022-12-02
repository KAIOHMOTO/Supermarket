package furhatos.app.supermarket.flow.main

import Noj
import Sparql4
import YesJ
import furhatos.app.supermarket.flow.Parent
import furhatos.flow.kotlin.*

val leaflet1: State = state(Parent) {
    onEntry {

        val sale_item:ArrayList<String> = Sparql4.getSale_List()
        furhat.say("チラシの情報を伝えます。")
        furhat.say("今週のセール商品は")
        for(i in sale_item){
            furhat.say(i)
        }
        furhat.say("です。")
        goto(End)
    }


}