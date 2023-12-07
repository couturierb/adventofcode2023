package days

import kotlin.math.log

class Day4 : Day(4) {

    val reg = Regex("\\d+|\\|")

    override fun partOne(): Any {
         return inputList
            .map { reg.findAll(it).drop(1) }
            .map {
                var winNumbers = ArrayList<Int>()
                var numbers = ArrayList<Int>()
                var pipePassed = false
                it.forEach {
                    if (it.value == "|") {
                        pipePassed = true
                    } else {
                        if (pipePassed) numbers.add(it.value.toInt()) else winNumbers.add(it.value.toInt())
                    }
                }
                var howMany = numbers.filter{ winNumbers.contains(it) }.count()
                calcul(howMany)
            }
            .reduce { acc, i -> acc + i }
    }

    override fun partTwo(): Any {
       return ""
    }

    private fun calcul(n: Int): Int {
        return if (n <= 2) n
        else calcul(n-1) * 2
    }
}