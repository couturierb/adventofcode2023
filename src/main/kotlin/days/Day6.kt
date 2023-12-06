package days

class Day6 : Day(6) {

    val races : Array<Pair<Long, Long>> = arrayOf(
        Pair(41, 214),
        Pair(96, 1789),
        Pair(88, 1127),
        Pair(94, 1055)
    )

    override fun partOne(): Any {
        return races.map { howManyBeatRecord(it.first, it.second) }.reduce { acc, i ->  acc*i}
    }

    override fun partTwo(): Any {
        return howManyBeatRecord(41968894, 214178911271055)
    }

    private fun howManyBeatRecord(time: Long, record: Long) : Int {
        var howMany = 0
        for (i in 1..time) {
            if ((time-i) * i > record) howMany++
        }
        return howMany
    }
}
