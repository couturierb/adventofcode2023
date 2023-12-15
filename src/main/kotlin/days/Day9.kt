package days

class Day9 : Day(9) {


    override fun partOne(): Any {
        return inputList
            .map {createHistory(it)}
            .map {findNext(it)}
            .reduce { acc, i -> acc+i }
    }

    override fun partTwo(): Any {
        return inputList
            .map {createHistory(it, true)}
            .map {findNext(it)}
            .reduce { acc, i -> acc+i }
    }

    fun createHistory(line: String, reversed: Boolean = false): List<List<Int>> {
        var history: MutableList<List<Int>> = mutableListOf()
        var newLine = line.split(" ").map { it.toInt() }
        newLine = if (reversed) newLine.reversed() else newLine

        while (newLine.isNullOrEmpty() || newLine.any { it != 0 }) {
            history.add(newLine)
            newLine = mutableListOf<Int>()
            var lastLine = history.last()
            for( i in 0..lastLine.size-2) {
                newLine.add(lastLine.get(i+1) - lastLine.get(i))
            }
        }

        return history
    }

    fun findNext(history: List<List<Int>>): Int {
        return history.map { it.last() }.reduce { acc, i -> acc + i }
    }
}
