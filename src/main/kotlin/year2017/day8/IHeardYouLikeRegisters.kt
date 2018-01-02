package year2017.day8

import common.resourceToList

data class Instruction(val register: String,
                       val operation: Int.(other: Int) -> Int,
                       val modifier: Int,
                       val condition: Condition)

data class Condition(val register: String,
                     val predicate: Int.(other: Int) -> Boolean,
                     val value: Int)

fun parseRegisterInstructions(lines: List<String>): List<Instruction> {
    val regex = "(\\w+) (\\w+) (-?\\d+) if (\\w+) ([><=!]+) (-?\\d+)".toRegex()
    return lines.map { regex.matchEntire(it) }
            .mapIndexed { index, match ->
                val operation = when (match?.groupValues?.get(2)) {
                    null -> throw IllegalArgumentException("No operation found @ line $index")
                    "inc" -> fun Int.(other: Int): Int = this + other
                    "dec" -> fun Int.(other: Int): Int = this - other
                    else -> throw IllegalArgumentException("Operation is not 'inc' or 'dec': ${match.groupValues[2]}")
                }
                val predicate = when (match.groupValues[5]) {
                    "==" -> fun Int.(other: Int): Boolean = this == other
                    "!=" -> fun Int.(other: Int): Boolean = this != other
                    ">" -> fun Int.(other: Int): Boolean = this > other
                    "<" -> fun Int.(other: Int): Boolean = this < other
                    ">=" -> fun Int.(other: Int): Boolean = this >= other
                    "<=" -> fun Int.(other: Int): Boolean = this <= other
                    else -> throw IllegalArgumentException("Predicate is not one of the supported types: ${match.groupValues[5]}")
                }
                val condition = Condition(match.groupValues[4], predicate, match.groupValues[6].toInt())
                Instruction(match.groupValues[1], operation, match.groupValues[3].toInt(), condition)
            }
}

fun evaluateInstructions(instructions: List<Instruction>): Pair<Map<String, Int>, Int> {
    val registers = mutableMapOf<String, Int>()
    var maxValue = 0
    for (instruction in instructions) {
        val condition = instruction.condition
        if (condition.predicate(registers[condition.register] ?: 0, condition.value)) {
            registers[instruction.register] = instruction.operation(registers[instruction.register] ?: 0, instruction.modifier)
        }
        maxValue = maxOf(registers.values.max() ?: 0, maxValue)
    }
    return registers to maxValue
}

fun findMaxRegisterValue(registers: Map<String, Int>): Int {
    return registers.values.max() ?: 0
}

fun main(args: Array<String>) {
    val rawInput = resourceToList("2017/day8_i_heard_you_like_registers.txt")
    val parsedInput = parseRegisterInstructions(rawInput)
    val registers = evaluateInstructions(parsedInput)
    println("Part1 = ${findMaxRegisterValue(registers.first)}")
    println("Part2 = ${registers.second}")
}