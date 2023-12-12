package days


class Day11 : Day(11) {

    val galaxies: MutableList<Pair<Int, Int>> = mutableListOf()
    val expandedLine: MutableList<Int> = mutableListOf()
    val expandedColumn: MutableList<Int> = IntRange(0, inputList.size-1).toMutableList()

    init {
        createGalaxy()
    }

    override fun partOne(): Any {
        // Find Paths
        var total = 0
        galaxies.forEachIndexed { index, pair1 ->
            galaxies.subList(index+1, galaxies.size).forEach { pair2 ->
                val distance = Math.abs(pair1.first - pair2.first) + Math.abs(pair1.second - pair2.second)
                val expandedColumnValue = expandedColumn.filter {
                    (it > pair1.first && it < pair2.first) || (it > pair2.first && it < pair1.first)
                }.count()
                val expandedLineValue = expandedLine.filter {
                    (it > pair1.second && it < pair2.second) || (it > pair2.second && it < pair1.second)
                }.count()
                total += distance + expandedColumnValue + expandedLineValue
            }
        }

        return total
    }

    override fun partTwo(): Any {
        var total = 0L
        galaxies.forEachIndexed { index, pair1 ->
            galaxies.subList(index+1, galaxies.size).forEach { pair2 ->
                val distance = Math.abs(pair1.first - pair2.first) + Math.abs(pair1.second - pair2.second)
                val expandedColumnValue = expandedColumn.filter {
                    (it > pair1.first && it < pair2.first) || (it > pair2.first && it < pair1.first)
                }.count() * 999999
                val expandedLineValue = expandedLine.filter {
                    (it > pair1.second && it < pair2.second) || (it > pair2.second && it < pair1.second)
                }.count() * 999999
                total += distance + expandedColumnValue + expandedLineValue
            }
        }

        return total
    }

    private fun createGalaxy() {
        inputList.forEachIndexed { y, line ->
            if (line.contains('#')) {
                line.forEachIndexed { x, char ->
                    if (char == '#') {
                        galaxies.add(Pair(x, y))
                        expandedColumn.remove(x)
                    }
                }
            } else {
                expandedLine.add(y)
            }
        }
    }
}
