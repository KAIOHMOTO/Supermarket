package furhatos.app.supermarket

import furhatos.app.supermarket.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class SupermarketSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
