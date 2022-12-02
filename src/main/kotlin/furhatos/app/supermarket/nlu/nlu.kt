import furhatos.nlu.Intent
import furhatos.util.Language

class YesJ() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("はい","うん","お願いします")
    }
}

class Noj() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("いいえ", "必要ない","要りません","使いません","使わない","いえ")
    }
}

class Sale() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("チラシ","今週のセール","お買い得","安い商品")
    }
}

class Kensaku() : Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("商品検索","商品の場所","位置情報","商品の棚を知りたい。","売り場")
    }
}

class Recipe() : Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("作り方","レシピ","料理")
    }
}