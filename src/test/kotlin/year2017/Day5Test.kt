package year2017

import year2017.day5.countStepsToExit
import year2017.day5.countStepsToExitLimitTo3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {

    private val input: List<Int>
        get() {
            return """
            |0
            |3
            |0
            |1
            |-3""".trimMargin()
                    .lines()
                    .map { it.toInt() }
        }

    @Test
    fun part1() {
        assertEquals(countStepsToExit(input), 5)
    }

    @Test
    fun part2() {
        assertEquals(countStepsToExitLimitTo3(input), 10)
    }
}