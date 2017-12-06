package day6

import common.resourceToString

fun countRedistributionCycles(input: List<Int>) = generateSequence(mutableListOf(input)) { stack ->
    val modifier = stack.last().toMutableList()
    var max = modifier.max()!!
    val indexOfMax = modifier.indexOf(max)
    var ptr = indexOfMax+1
    while (max > 0) {
        modifier[indexOfMax]--
        modifier[ptr++ % modifier.size]++
        max--
    }
    if (modifier !in stack) {
        stack.apply { add(modifier) }
    } else {
        stack.add(modifier)
        null
    }
}.toList().last()

fun main(args:Array<String>) {
    val input = resourceToString("day6_memoryallocation.txt")
            .split("\t")
            .map { it.toInt() }
    val redistributionCycles = countRedistributionCycles(input)
    val repeatedState = redistributionCycles.last()
    println("Part1 = ${redistributionCycles.size - 1}")
    println("Part2 = ${redistributionCycles.size - redistributionCycles.indexOf(repeatedState) - 1}")
}