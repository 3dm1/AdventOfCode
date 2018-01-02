package year2017

import year2017.day17.computeSpinLock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day17Test {

    private val jump = 3

    @ParameterizedTest
    @MethodSource("day17Provider")
    fun part1(iterationCount: Int, lastPosition: Int, buffer: List<Int>) {
        val testBuffer = mutableListOf(0)
        assertEquals(computeSpinLock(jump, iterationCount, testBuffer), lastPosition)
        assertEquals(testBuffer, buffer)
    }

    companion object {
        @JvmStatic
        fun day17Provider() = Stream.of(
                Arguments.of(0, 0, listOf(0)),
                Arguments.of(1, 1, listOf(0, 1)),
                Arguments.of(2, 1, listOf(0, 2, 1)),
                Arguments.of(3, 2, listOf(0, 2, 3, 1)),
                Arguments.of(4, 2, listOf(0, 2, 4, 3, 1)),
                Arguments.of(5, 1, listOf(0, 5, 2, 4, 3, 1)),
                Arguments.of(6, 5, listOf(0, 5, 2, 4, 3, 6, 1)),
                Arguments.of(7, 2, listOf(0, 5, 7, 2, 4, 3, 6, 1)),
                Arguments.of(8, 6, listOf(0, 5, 7, 2, 4, 3, 8, 6, 1)),
                Arguments.of(9, 1, listOf(0, 9, 5, 7, 2, 4, 3, 8, 6, 1))
        )
    }
}