package year2017.day5

import common.resourceToList

fun countStepsToExit(input: List<Int>): Int {
    val maze = input.toMutableList()
    var i = 0
    var steps = 0
    while (i >= 0 && i < maze.size) {
        val nextIndex = i + maze[i]
        maze[i]++
        steps++
        i = nextIndex
    }
    return steps
}

fun countStepsToExitLimitTo3(input: List<Int>): Int {
    val maze = input.toMutableList()
    var i = 0
    var steps = 0
    while (i >= 0 && i < maze.size) {
        val currentInstruction = maze[i]
        maze[i] += if (currentInstruction >= 3) -1 else 1
        i += currentInstruction
        steps++
    }
    return steps
}

fun main(args: Array<String>) {
    val input = resourceToList("2017/day5_maze.txt").map { it.toInt() }
    println(countStepsToExit(input))
    println(countStepsToExitLimitTo3(input))
}