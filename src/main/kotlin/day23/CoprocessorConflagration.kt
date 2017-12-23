package day23

import common.resourceToList
import kotlin.math.sqrt

fun executeInstruction(registers: MutableMap<String, Long>, command: String): Pair<Int, Int> {
    val instruction = command.split(" ")
    var jump = 1
    var mulCount = 0
    when (instruction[0]) {
        "set" -> registers.put(instruction[1], registers.entryValue(instruction[2]))
        "sub" -> registers.put(instruction[1], registers.getOrDefault(instruction[1], 0) -
                registers.entryValue(instruction[2]))
        "mul" -> {
            registers.put(instruction[1], registers.getOrDefault(instruction[1], 0L) *
                    registers.entryValue(instruction[2]))
            mulCount++
        }
        "jnz" -> if (registers.entryValue(instruction[1]) != 0L) {
            jump = registers.entryValue(instruction[2]).toInt()
        }
    }
    return jump to mulCount
}

fun MutableMap<String, Long>.entryValue(value: String) = value.toLongOrNull() ?: this.getOrDefault(value, 0L)

fun getMulCount(input: List<String>, registers: MutableMap<String, Long> = mutableMapOf()): Int {
    var i = 0
    var mulCount = 0
    while (i < input.size) {
        val (jmp, count) = executeInstruction(registers, input[i])
        i += jmp
        mulCount += count
    }
    return mulCount
}

//The instructions represent the following pseudo-code:
//  while (b != c) {
//      f = 1
//      d = 2
//	    while (d != b) {
//          e = 2
//			while (e != b) {
//              if (e*d == b) f = 0
//              e++
//          }
//          d++
//      }
//      if (f == 0) h++
//      b += 17
//  }
// If the value in register B is non-prime, i.e. there are values E and D such that E * D == B,
// the counter H is incremented. Essentially the program counts non-primes between B and C with a step of 17
fun part2(): Int {
    val b = 109_900
    val c = 126_900
    return (b..c step 17).count { !it.isPrime() }
}

// Implementation based on Wikipedia's Primality Check (https://en.wikipedia.org/wiki/Primality_test#Pseudocode)
private fun Int.isPrime(): Boolean {
    return when {
        this < 2 -> false
        this == 2 || this == 3 -> true
        this % 2 == 0 || this % 3 == 0 -> false
        else -> {
            val sqrt = sqrt(this.toDouble()).toLong() + 1L
            (5..sqrt step 6).none { this % it == 0L || this % (it + 2) == 0L }
        }
    }
}

fun main(args: Array<String>) {
    val input = resourceToList("day23_coprocessor_conflagration.txt")
    val mulCount = getMulCount(input)
    println(mulCount)
    println(part2())
}