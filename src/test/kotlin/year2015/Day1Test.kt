package year2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import year2015.day1.getFinalFloor
import year2015.day1.getFirstTimeInBasement
import java.util.stream.Stream

class Day1Test {

    @ParameterizedTest
    @MethodSource("part1Provider")
    fun part1(input: String, result: Int) {
        assertEquals(getFinalFloor(input), result)
    }

    @Test
    fun part2() {
        assertEquals(getFirstTimeInBasement(")"), 1)
        assertEquals(getFirstTimeInBasement("()())"), 5)
    }

    companion object {
        @JvmStatic
        fun part1Provider() = Stream.of(
                Arguments.of("(())", 0),
                Arguments.of("()()", 0),
                Arguments.of("(((", 3),
                Arguments.of("(()(()(", 3),
                Arguments.of("())", -1),
                Arguments.of("))(", -1),
                Arguments.of(")))", -3),
                Arguments.of(")())())", -3)
        )
    }
}