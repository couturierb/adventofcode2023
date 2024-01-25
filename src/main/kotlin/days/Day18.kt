package days

class Day18 : Day(18) {

    val reg = Regex("\w+")
    val lagoon : Array<Array<String>> = init()

    fun init(): Array<Array<String>> {
        var sizeX = 1
        var sizeY = 1

        inputList
            .map { reg.findAll(it) }
            .forEach {
                val direction = it.first().value
                val meter = it.elementAt(1).value.toInt()
                when (direction) {
                    "R" -> sizeX += meter
                    "L" -> sizeX -= meter
                    "D" -> sizeY += meter
                    "U" -> sizeY -= meter
                }
            }

        return Array(sizeY) { Array(sizeX) {"."} }
    }

    fun parcours() {
        inputList
            .map { reg.findAll(it) }
            .forEach {
                val direction = it.first().value
                val meter = it.elementAt(1).value.toInt()
                // TODO
            }
    }

    override fun partOne(): Any {
        parcours()
        return ""
    }

    override fun partTwo(): Any {
       return ""
    }

}