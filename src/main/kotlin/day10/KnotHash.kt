package day10

import common.resourceToString

val part2Prefix = arrayOf(17, 31, 73, 47, 23)

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun <T> MutableList<T>.knotHash(initialIndex: Int = 0, skipSize: Int = 0, knotLengths: List<Int>): Int {
    var currentPosition = initialIndex
    for ((iteration, length) in knotLengths.withIndex()) {
        for (i in 0 until length / 2) {
            val index = (currentPosition + i) % size
            val mirrorIndex = (currentPosition + length - i - 1) % size
            swap(index, mirrorIndex)
        }
        currentPosition = (currentPosition + length + skipSize + iteration) % size
    }
    return currentPosition
}

fun MutableList<Int>.denseKnotHash(knotLengths: List<Int>): String {
    var currentPosition = 0
    for (count in 0 until 64) {
        currentPosition = knotHash(currentPosition, count * knotLengths.size, knotLengths)
    }
    return chunked(16) { it.reduce(Int::xor) }.joinToString("") { "%02x".format(it) }
}

val rawInput by lazy { resourceToString("day10_knot_hash.txt") }
val part1Input = rawInput.split(",").map(String::toInt)
val part2Input = rawInput.map(Char::toInt).plus(part2Prefix)

fun main(args: Array<String>) {
    val numbers = mutableListOf(*Array(256) { it })
    val part1 = numbers.toMutableList()
    val part2 = numbers.toMutableList()
    part1.knotHash(knotLengths = part1Input)
    println(part1[0] * part1[1])
    println(part2.denseKnotHash(part2Input))
}