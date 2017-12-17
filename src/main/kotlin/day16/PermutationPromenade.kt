package day16

import common.resourceToString
import day10.swap

val input = "s1,x3/4,pe/b"

fun String.swap(i1: Int, i2: Int) = this.toCharArray().let {
    it[i1] = this[i2]
    it[i2] = this[i1]
    String(it)
}

fun computeDance(programs: String, moves: List<String>): String {
    return moves.fold(programs) { prev, move ->
        when (move[0]) {
            's' -> {
                val spinCount = prev.length - move.substring(1).toInt()
                prev.substring(spinCount) + prev.substring(0, spinCount)
            }
            'x' -> {
                val position = move.substring(1).split("/").map(String::toInt)
                prev.swap(position[0], position[1])
            }
            'p' -> prev.swap(prev.indexOf(move[1]), prev.indexOf(move[3]))
            else -> throw IllegalArgumentException("Unsupported move")
        }
    }
}

fun main(args: Array<String>) {
    val programs = "abcdefghijklmnop"
    val input = resourceToString("day16_permutation_promenade.txt").split(",")
    println(computeDance(programs, input))
    val history = mutableMapOf<String, String>()
    println((0 until 1_000_000_000).fold(programs) { prev, _ -> history.getOrPut(prev) { computeDance(prev, input) } })
}