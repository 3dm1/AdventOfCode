package day3

import kotlin.math.*

fun getCoordinatesForPosition(position: Int): Pair<Float, Float> {
    if (position == 1) return 0f to 0f
    val ring = ceil((sqrt(position.toFloat()) - 1) / 2)
    val ringLimit = (2 * ring + 1).pow(2)
    val side = floor((ringLimit - position) / (2 * ring)).toInt()
    return when (side) {
        0 -> ring - (ringLimit - position) % (2 * ring) to -ring
        1 -> -ring to (ringLimit - position) % (2 * ring) - ring
        2 -> (ringLimit - position) % (2 * ring) - ring to ring
        else -> ring to ring - (ringLimit - position) % (2 * ring)
    }
}

fun getValueForPosition(input: Int): Int {
    if (input == 1) return 1
    val inputCoordinates = getCoordinatesForPosition(input)
    return (input - 1 downTo 1)
            .filter { position ->
                val coordinates = getCoordinatesForPosition(position)
                euclideanDistance(coordinates, inputCoordinates) <= sqrt(2f)
            }
            .map(::getValueForPosition)
            .sum()
}

fun euclideanDistance(p1: Pair<Float, Float>, p2: Pair<Float, Float>): Float {
    return sqrt((p1.first - p2.first).pow(2) + (p1.second - p2.second).pow(2))
}

fun taxiDistance(p1: Pair<Float, Float>, p2: Pair<Float, Float>): Float {
    return (abs(p2.first - p1.first) + abs(p2.second - p1.second))
}

fun getMemoryDistance(input: Int): Int {
    val inputCoordinate = getCoordinatesForPosition(input)
    return taxiDistance(getCoordinatesForPosition(1), inputCoordinate).toInt()
}

fun stressTest(input: Int) : Int {
    var i = 1
    var positionValue: Int
    while (true) {
        positionValue = getValueForPosition(i)
        if (positionValue > input) {
            return positionValue
        } else {
            i++
        }
    }
}

fun main(args: Array<String>) {
    println("Part1 = ${getMemoryDistance(265149)}")
    println("Part2 = ${stressTest(265149)}")
}