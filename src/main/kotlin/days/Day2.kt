package days

class Day2 : Day(2) {

    val RED_BAG = 12
    val GREEN_BAG = 13
    val BLUE_BAG = 14
    val regGame = Regex("(?<=Game )\\d+")
    val regBlue = Regex("\\d+(?= blue)")
    val regRed = Regex("\\d+(?= red)")
    val regGreen = Regex("\\d+(?= green)")

    override fun partOne(): Any {
        return inputList
            .filterNot { regBlue.findAll(it).any{ it.value.toInt() > BLUE_BAG } }
            .filterNot { regRed.findAll(it).any{ it.value.toInt() > RED_BAG } }
            .filterNot { regGreen.findAll(it).any{ it.value.toInt() > GREEN_BAG } }
            .map { regGame.find(it)?.value?.toInt() ?: 0 }
            .reduce { acc, t -> acc + t }
    }

    override fun partTwo(): Any {
        return inputList.map {
            val maxBlue = regBlue.findAll(it).map{ it.value.toInt() }.max()
            val maxRed = regRed.findAll(it).map{ it.value.toInt() }.max()
            val maxGreen = regGreen.findAll(it).map{ it.value.toInt() }.max()
            maxBlue * maxRed * maxGreen
        }.reduce { acc, t -> acc + t }
    }
}