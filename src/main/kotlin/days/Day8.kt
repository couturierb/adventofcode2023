package days

class Day8 : Day(8) {

    val network = mutableMapOf<String, Pair<String, String>>()
    lateinit var directions: CharArray

    override fun partOne(): Any {
        return ""
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

        var multiples = mutableListOf(13200, 22410, 18726, 18112, 16270, 20568)
        while(true) {
            multiples.min()
        }

//        while (indexes.any { !it.endsWith("Z") }) {
//            indexes.forEachIndexed { i, s ->
//                indexes[i] = if (directions[count%directions.size] == 'L') network.get(s)!!.first else network.get(s)!!.second
//                if (indexes[i].endsWith("Z")) {
//                    println("${i}: ${count}")
//                }
//            }
//            count++
//        }

        return count
    }

}
