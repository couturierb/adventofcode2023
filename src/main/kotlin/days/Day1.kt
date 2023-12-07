package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        val reg = Regex("\\d")
        return inputString.split("\n")
            .map { reg.findAll(it) }
            .filterNot { it.none() }
            .map {it.first().value.toInt() * 10 + it.last().value.toInt() }
            .reduce { acc, t -> acc + t }
    }

    override fun partTwo(): Any {
        val regFirst = Regex("\\d|one|two|three|four|five|six|seven|eight|nine")
        val regLast = Regex("\\d|one$|two$|three$|four$|five$|six$|seven$|eight$|nine$")
        return inputString.split("\n")
            .map {
                val first = regFirst.findAll(it).first().value
                val last = regLast.findAll(it).last().value
                var returnValue = 0
                try {
                    returnValue = first.toInt() * 10
                } catch (e : NumberFormatException) {
                    returnValue = Number.valueOf(first).numeric * 10
                }

                try {
                    returnValue += last.toInt()
                } catch (e : NumberFormatException) {
                    returnValue += Number.valueOf(last).numeric
                }

                returnValue
            }
            .reduce { acc, t -> acc + t }
    }
}

private enum class Number(val numeric : Int) {
    one(1),
    two(2),
    three(3),
    four(4),
    five(5),
    six(6),
    seven(7),
    eight(8),
    nine(9)
}