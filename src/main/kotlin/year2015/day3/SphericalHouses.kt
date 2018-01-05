package year2015.day3

import common.resourceToString

typealias Coord2D = Pair<Int, Int>
operator infix fun Coord2D.plus(other: Coord2D) = this.first + other.first to this.second + other.second

data class Sledge(val visited: Set<Coord2D> = setOf(Coord2D(0, 0)), val currentPosition: Coord2D = Coord2D(0, 0)) {

    val housesVisited
        get() = visited.size

    fun move(action: Char): Sledge {
        val newPosition = when (action) {
            '^' -> currentPosition + Coord2D(1, 0)
            'v' -> currentPosition + Coord2D(-1, 0)
            '>' -> currentPosition + Coord2D(0, 1)
            '<' -> currentPosition + Coord2D(0, -1)
            else -> throw IllegalArgumentException("Unsupported Direction")
        }
        return Sledge(visited + newPosition, newPosition)
    }
}

fun main(args: Array<String>) {
    val input = resourceToString("2015/day3_spherical_houses.txt")

    val finalPosition = input.fold(Sledge()) { acc, c -> acc.move(c) }
    println(finalPosition.housesVisited)

    val (santaFinal, robotFinal) = input.chunked(2).fold(Sledge() to Sledge()) { (santa, robot), c ->
        santa.move(c[0]) to robot.move(c[1])
    }
    println((santaFinal.visited + robotFinal.visited).size)
}