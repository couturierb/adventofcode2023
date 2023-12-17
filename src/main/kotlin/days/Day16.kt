package days

class Day16 : Day(16) {

    var tiles : Array<Array<Tile?>> = Array(inputList.size) { arrayOfNulls(inputList[0].length) }

    init {
        createTiles()
    }

    override fun partOne(): Any {
        parcours()
        return countDiese()
    }

    override fun partTwo(): Any {
        val dieses: MutableList<Int> = mutableListOf()
        for (y in tiles.indices) {
            createTiles()
            parcours(0, y, DIRECTION.RIGHT)
            dieses.add(countDiese())
            createTiles()
            parcours(tiles[0].size-1, y, DIRECTION.LEFT)
            dieses.add(countDiese())
        }

        for (x in tiles[0].indices) {
            createTiles()
            parcours(x, 0, DIRECTION.BOTTOM)
            dieses.add(countDiese())
            createTiles()
            parcours(x, tiles.size-1, DIRECTION.TOP)
            dieses.add(countDiese())
        }

        return dieses.max()
    }

    private fun createTiles() {
        tiles = Array(inputList.size) { arrayOfNulls(inputList[0].length) }
        var y = 0
        inputList.forEachIndexed { x, line ->
            tiles[y] = line.toList().map { Tile(it.toString()) }.toTypedArray()
            y++
        }
    }

    private fun parcours(startX: Int = 0, startY: Int = 0, startDirection: DIRECTION = DIRECTION.RIGHT) {
        var nextTile = NextTile(startX, startY, startDirection)
        while (nextTile.x >= 0 && nextTile.y >= 0 && nextTile.x < tiles[0].size && nextTile.y < tiles.size) {
            val nextTiles = tiles[nextTile.y][nextTile.x]!!.getNext(nextTile.x, nextTile.y, nextTile.direction)
            tiles[nextTile.y][nextTile.x]?.energy()
            if (nextTiles.isEmpty()) return

            nextTile = nextTiles[0]
            if (nextTiles.size == 2) {
                parcours(nextTiles[1].x, nextTiles[1].y, nextTiles[1].direction)
            }
        }
    }

    private fun countDiese(): Int {
        return tiles.flatten().filter { it!!.energized }.count()
    }

    private fun print(energized: Boolean = false) {
        println()
        tiles.forEach {
            it.forEach { if (energized && it!!.energized) print("#") else print(it) }
            println()
        }
    }

    class Tile(val symbol: String) {

        var energized = false
        var hasSplit = false

        fun energy() {
            energized = true
        }

        fun getNext(x: Int, y: Int, direction: DIRECTION): List<NextTile> {
            return when (symbol) {
                "/" -> handeSlash(x, y, direction)
                "\\" -> handleBackSlash(x, y, direction)
                "|" -> handlePipe(x, y, direction)
                "-" -> handleDash(x, y, direction)
                else -> handleDot(x, y, direction)
            }
        }

        private fun handleDot(x: Int, y: Int, direction: DIRECTION) : List<NextTile> {
            val next = direction.getNextTile(x, y)
            return listOf(NextTile(next.first, next.second, direction))
        }

        private fun handeSlash(x: Int, y: Int, direction: DIRECTION): List<NextTile> {
            val newDirection = when(direction) {
                DIRECTION.RIGHT -> DIRECTION.TOP
                DIRECTION.LEFT -> DIRECTION.BOTTOM
                DIRECTION.TOP -> DIRECTION.RIGHT
                DIRECTION.BOTTOM -> DIRECTION.LEFT
            }

            val next = newDirection.getNextTile(x, y)
            return listOf(NextTile(next.first, next.second, newDirection))
        }

        private fun handleBackSlash(x: Int, y: Int, direction: DIRECTION): List<NextTile> {
            val newDirection = when(direction) {
                DIRECTION.RIGHT -> DIRECTION.BOTTOM
                DIRECTION.LEFT -> DIRECTION.TOP
                DIRECTION.TOP -> DIRECTION.LEFT
                DIRECTION.BOTTOM -> DIRECTION.RIGHT
            }

            val next = newDirection.getNextTile(x, y)
            return listOf(NextTile(next.first, next.second, newDirection))
        }

        private fun handlePipe(x: Int, y: Int, direction: DIRECTION): List<NextTile> {
            if (hasSplit) {
                return emptyList()
            }

            if (direction == DIRECTION.LEFT || direction == DIRECTION.RIGHT) {
                hasSplit = true
                val nextTop = DIRECTION.TOP.getNextTile(x, y)
                val nextBottom = DIRECTION.BOTTOM.getNextTile(x, y)
                return listOf(
                    NextTile(nextTop.first, nextTop.second, DIRECTION.TOP),
                    NextTile(nextBottom.first, nextBottom.second, DIRECTION.BOTTOM),
                )
            }

            return handleDot(x, y, direction)
        }

        private fun handleDash(x: Int, y: Int, direction: DIRECTION): List<NextTile> {
            if (hasSplit) {
                return emptyList()
            }

            if (direction == DIRECTION.TOP || direction == DIRECTION.BOTTOM) {
                    hasSplit = true
                    val nextRight = DIRECTION.RIGHT.getNextTile(x, y)
                    val nextLeft = DIRECTION.LEFT.getNextTile(x, y)
                    return listOf(
                        NextTile(nextRight.first, nextRight.second, DIRECTION.RIGHT),
                        NextTile(nextLeft.first, nextLeft.second, DIRECTION.LEFT),
                    )
            }

            return handleDot(x, y, direction)
        }

        override fun toString(): String {
            return symbol
        }
    }

    enum class DIRECTION {
        LEFT { override fun getNextTile(x: Int, y: Int): Pair<Int, Int> { return Pair(x-1, y) } },
        RIGHT { override fun getNextTile(x: Int, y: Int): Pair<Int, Int> { return Pair(x+1, y) } },
        TOP { override fun getNextTile(x: Int, y: Int): Pair<Int, Int> { return Pair(x, y-1) } },
        BOTTOM { override fun getNextTile(x: Int, y: Int): Pair<Int, Int> { return Pair(x, y+1) } };

        abstract fun getNextTile(x: Int, y: Int): Pair<Int, Int>
    }

    data class NextTile(val x: Int, val y: Int, val direction: DIRECTION)
}