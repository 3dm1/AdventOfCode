package day12

import common.resourceToList

fun getProgramGroup(input: Map<Int, List<Int>>, endProgram: Int, visited: MutableSet<Int> = mutableSetOf()): MutableSet<Int> {
    if (endProgram !in visited) {
        visited.add(endProgram)
        input[endProgram]?.forEach { getProgramGroup(input, it, visited) }
    }
    return visited
}

fun getTotalGroups(input: Map<Int, List<Int>>) = input.map { (program, _) -> getProgramGroup(input, program) }.distinct()

fun main(args: Array<String>) {
    val rawInput = resourceToList("day12_digital_plumber.txt")
    val parsedInput = rawInput.map { it.split(" <-> ") }
            .associate { it[0].toInt() to it[1].split(", ").map(String::toInt) }

    println(getProgramGroup(parsedInput, 0).size)
    println(getTotalGroups(parsedInput).size)
}