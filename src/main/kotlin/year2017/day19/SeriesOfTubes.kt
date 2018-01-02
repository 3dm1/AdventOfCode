package year2017.day19

import common.resourceToList

enum class Direction {
    INC_X, DEC_X, INC_Y, DEC_Y, NONE
}

fun findEntry(input: List<String>): Triple<Int, Int, Direction> {
    val columns = input.map { it.length }.max() ?: 0
    val rows = input.size

    val (x, y) = (0 until columns).flatMap { column -> (0 until rows).map { row -> column to row } }
            .filter { (x, y) -> x == 0 || y == 0 }
            .filter { (x, y) -> x < input[y].length }
            .first { (x, y) -> input[y][x] == '|' || input[y][x] == '-' }

    val direction = if (input[y][x] == '|') {
        if (y == 0) Direction.INC_Y else Direction.DEC_Y
    } else {
        if (x == 0) Direction.INC_X else Direction.DEC_X
    }
    return Triple(x, y, direction)
}

fun getPipePath(input: List<String>) = generateSequence(findEntry(input)) { (x, y, direction) ->
    when (direction) {
        Direction.INC_X -> {
            val nextDirection = if (!isValidIndex(input, x + 1, y)) {
                Direction.NONE
            } else if (input[y][x + 1] == '+') {
                if (isValidNextStep(input, x + 1, y + 1)) Direction.INC_Y else Direction.DEC_Y
            } else {
                direction
            }
            Triple(x + 1, y, nextDirection)
        }
        Direction.DEC_X -> {
            val nextDirection = if (!isValidIndex(input, x - 1, y)) {
                Direction.NONE
            } else if (input[y][x - 1] == '+') {
                if (isValidNextStep(input, x - 1, y + 1)) Direction.INC_Y else Direction.DEC_Y
            } else {
                direction
            }
            Triple(x - 1, y, nextDirection)
        }
        Direction.INC_Y -> {
            val nextDirection = if (!isValidIndex(input, x, y + 1)) {
                Direction.NONE
            } else if (input[y + 1][x] == '+') {
                if (isValidNextStep(input, x + 1, y + 1)) Direction.INC_X else Direction.DEC_X
            } else {
                direction
            }
            Triple(x, y + 1, nextDirection)
        }
        Direction.DEC_Y -> {
            val nextDirection = if (!isValidIndex(input, x, y - 1)) {
                Direction.NONE
            } else if (input[y - 1][x] == '+') {
                if (isValidNextStep(input, x + 1, y - 1)) Direction.INC_X else Direction.DEC_X
            } else {
                direction
            }
            Triple(x, y - 1, nextDirection)
        }
        Direction.NONE -> null
    }
}

fun isValidIndex(input: List<String>, x: Int, y: Int) = y >= 0 && y < input.size
        && x >= 0 && x < input[y].length
        && input[y][x] != ' '

fun isValidNextStep(input: List<String>, x: Int, y: Int) = isValidIndex(input, x, y) && input[y][x] != '+'

fun main(args: Array<String>) {
    val input = resourceToList("2017/day19_series_of_tubes.txt")
    val path = getPipePath(input).toList()
    println(path.size - 1)
    println(path.map { (x, y, _) -> input[y][x] }.filter(Char::isLetter).joinToString(""))
}