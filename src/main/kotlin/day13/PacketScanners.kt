package day13

import common.resourceToList

fun findMatches(input: Map<Int, Int>, delay: Int = 0) = input.entries.filter { (delay + it.key) % (2 * (it.value - 1)) == 0 }

fun getSeverity(input: Map<Int, Int>, delay: Int = 0) = findMatches(input, delay).sumBy { it.key * it.value }

fun getMinDelay(input: Map<Int, Int>) = (0..10000000).asSequence().first { findMatches(input, it).isEmpty() }

fun main(args: Array<String>) {
    val rawInput = resourceToList("day13_packet_scanners.txt")
    val input = rawInput.map { it.split(": ") }.associate { it[0].toInt() to it[1].toInt() }
    println(getSeverity(input))
    println(getMinDelay(input))
}