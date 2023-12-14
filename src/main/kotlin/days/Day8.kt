package days

class Day8 : Day(8) {

    val network = mutableMapOf<String, Pair<String, String>>()
    lateinit var directions: CharArray

    override fun partOne(): Any {
        directions = inputList.get(0).toCharArray()
        inputList
            .filterIndexed { index, s -> index > 0 }
            .forEach {
            if (it.length > 0) {
                val inputs = Regex("\\w+").findAll(it)
                network.put(inputs.first().value, Pair(inputs.elementAt(1).value, inputs.last().value))
            }
        }

        var count = 0
        var index = "AAA"

        while (index != "ZZZ") {
            index = if (directions[count%directions.size] == 'L') network.get(index)!!.first else network.get(index)!!.second
            count++
        }

        return count
    }

    override fun partTwo(): Any {
        directions = inputList.get(0).toCharArray()
        inputList
            .filterIndexed { index, s -> index > 0 }
            .forEach {
                if (it.length > 0) {
                    val inputs = Regex("\\w+").findAll(it)
                    network.put(inputs.first().value, Pair(inputs.elementAt(1).value, inputs.last().value))
                }
            }

        var count = 0
        var indexes = network.keys.filter { it.endsWith("A") }.toList().toMutableList()

        while (indexes.any { !it.endsWith("Z") }) {
            indexes.forEachIndexed { i, s ->
                indexes[i] = if (directions[count%directions.size] == 'L') network.get(s)!!.first else network.get(s)!!.second
                if (indexes[i].endsWith("Z")) {
                    println("${i}: ${count+1}")
                }
            }
            count++
        }

        //  Calculer le PPCM de : 13201, 22411, 18727, 18113, 16271, 20569
        // https://www.wolframalpha.com/input?i=what+is+the+lcm+of+13201%2C+22411%2C+18727%2C+18113%2C+16271%2C+20569
        return count
    }

}
