package year2017.day14

import year2017.day10.denseKnotHash
import year2017.day10.part2Prefix
import kotlin.system.measureTimeMillis

fun parseInput(rawInput: String) = (0..127).map { "$rawInput-$it" }
        .map { (0..255).toMutableList().denseKnotHash(it.map(Char::toInt).plus(part2Prefix)) }
        .map { it.chunked(4).map { "%16s".format(it.toInt(16).toString(2)) }.reduce(String::plus) }

fun getOccupiedPositionsCount(input: String) = parseInput(input).sumBy { it.filter { it == '1' }.length }

fun getGroups(input: String): Int {
    val parsedInput = parseInput(input)
    val visited = mutableSetOf<Int>()
    return (0 until 128 * 128).asSequence()
            .filter { it !in visited && parsedInput[it / 128][it % 128] == '1' }
            .map { getOnesGroup(parsedInput, it).apply { visited.addAll(this) } }
            .count()
}

fun getOnesGroup(input: List<String>, index: Int, visited: MutableSet<Int> = mutableSetOf()): MutableSet<Int> {
    if (index !in visited && input[index / 128][index % 128] == '1') {
        visited.add(index)
        val currentRow = index / 128
        mutableListOf(index - 128, index + 128)
                .apply {
                    if ((index + 1) / 128 == currentRow) add(index + 1)
                    if ((index - 1) / 128 == currentRow) add(index - 1)
                }
                .filter { it >= 0 && it <= 128 * 128 - 1 }
                .forEach { getOnesGroup(input, it, visited) }
    }
    return visited
}

fun main(args: Array<String>) {
    val rawInput = "nbysizxe"
    val time1 = measureTimeMillis { println(getOccupiedPositionsCount(rawInput)) }
    val time2 = measureTimeMillis { println(getGroups(rawInput)) }
    println("$time1 $time2")

}