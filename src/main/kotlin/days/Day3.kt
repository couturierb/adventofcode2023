package days

import kotlin.math.log

class Day3 : Day(3) {

    val lineLength = inputList[0].length + 1
    val numbers: Sequence<MatchResult> = Regex("\\d+").findAll(inputString)
    val symbols: Sequence<MatchResult> = Regex("[^\\d.\\w\\s]").findAll(inputString)
    val gears: Sequence<MatchResult> = Regex("\\*").findAll(inputString)

    override fun partOne(): Any {
        return numbers
            .filter { isIntersect(it, symbols.map{ it.range }) }
            .map { it.value.toInt() }
            .reduce { acc, t -> acc + t }
    }

    override fun partTwo(): Any {
        return gears
            .map{ it.range }
            .map { numbers.filter { number -> isIntersect(number, sequenceOf(it)) } }
            .filter { it.count() == 2}
            .map { it.first().value.toInt() * it.last().value.toInt() }
            .reduce { acc, t -> acc + t }
    }

    private fun isIntersect(numberRange : MatchResult, symbols : Sequence<IntRange>): Boolean {
        return symbols.any {
            val target = it.first

            numberRange.range.contains(target-1)
                    || numberRange.range.contains(target+1)
                    || numberRange.range.contains(target-lineLength)
                    || numberRange.range.contains(target-lineLength-1)
                    || numberRange.range.contains(target-lineLength+1)
                    || numberRange.range.contains(target+lineLength)
                    || numberRange.range.contains(target+lineLength-1)
                    || numberRange.range.contains(target+lineLength+1)
        }
    }
}