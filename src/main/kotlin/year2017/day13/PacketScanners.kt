package year2017.day13

import common.resourceToList

fun findMatches(input: Map<Int, Int>, delay: Int = 0) = input.entries.filter { (delay + it.key) % (2 * (it.value - 1)) == 0 }

fun getSeverity(input: Map<Int, Int>, delay: Int = 0) = findMatches(input, delay).sumBy { it.key * it.value }

fun getMinDelay(input: Map<Int, Int>) = generateSequence(0, Int::inc).first { findMatches(input, it).isEmpty() }

fun getMinDelayFaster(input: Map<Int, Int>) = generateSequence(0, Int::inc)
        .first { delay -> input.entries.none { (delay + it.key) % (2 * (it.value - 1)) == 0 } }

fun main(args: Array<String>) {
    val rawInput = resourceToList("2017/day13_packet_scanners.txt")
    val input = rawInput.map { it.split(": ") }.associate { it[0].toInt() to it[1].toInt() }
    println(getSeverity(input))
    println(getMinDelay(input))
    println(getMinDelayFaster(input))
}