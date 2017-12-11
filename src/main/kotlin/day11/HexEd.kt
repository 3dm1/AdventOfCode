package day11

import common.resourceToString
import kotlin.math.abs
import kotlin.math.max

typealias Hex = Triple<Int, Int, Int>

// This solution uses cube coordinates (https://www.redblobgames.com/grids/hexagons/#neighbors)
//                \ (0, +1, -1) /
//                 +-----------+
//   (-1, +1, 0)  /             \ (+1, 0, -1)
//              -+               +-
//   (-1, 0, +1)  \             / (+1, -1, 0)
//                 +-----------+
//                / (0, -1, +1) \

operator fun Hex.plus(other: Triple<Int, Int, Int>) = Triple(this.first + other.first,
        this.second + other.second,
        this.third + other.third)

fun Hex.getDistanceToOrigin() = maxOf(abs(this.first), abs(this.second), abs(this.third))

fun getMinDistance(directions: List<String>): Pair<Int, Int> {
    var maxDistance = Int.MIN_VALUE
    val finalPosition = directions.fold(Hex(0, 0, 0)) { position, direction ->
        val newPosition = position + when (direction) {
            "n" -> Hex(0, 1, -1)
            "ne" -> Hex(1, 0, -1)
            "se" -> Hex(1, -1, 0)
            "s" -> Hex(0, -1, 1)
            "sw" -> Hex(-1, 0, 1)
            "nw" -> Hex(-1, 1, 0)
            else -> throw IllegalArgumentException("Unsupported direction $direction")
        }
        maxDistance = max(maxDistance, newPosition.getDistanceToOrigin())
        newPosition
    }
    return finalPosition.getDistanceToOrigin() to maxDistance
}


fun main(args: Array<String>) {
    val input = resourceToString("day11_hex_ed.txt").split(",")
    val (minDistance, maxDistance) = getMinDistance(input)
    println("MinDistance $minDistance")
    println("MaxDistance $maxDistance")
}