package year2017

import year2017.day3.getMemoryDistance
import year2017.day3.getValueForPosition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day3Test {

    @ParameterizedTest
    @MethodSource("part1Provider")
    fun part1(input: Int, result: Int) {
        assertEquals(getMemoryDistance(input), result)
    }

    @ParameterizedTest
    @MethodSource("part2Provider")
    fun part2(input: Int, result: Int) {
        assertEquals(getValueForPosition(input), result)
    }

    companion object {
        @JvmStatic
        fun part1Provider() = Stream.of(
                Arguments.of(1, 0),
                Arguments.of(12, 3),
                Arguments.of(23, 2),
                Arguments.of(1024, 31)
        )

        @JvmStatic
        fun part2Provider() = Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(4, 4),
                Arguments.of(5, 5),
                Arguments.of(6, 10)
        )
    }
}