package year2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import year2015.day3.Sledge
import java.util.stream.Stream

class Day3Test {

    @ParameterizedTest
    @MethodSource("part1")
    fun part1(input: String, result: Int) {
        val finalPosition = input.fold(Sledge()) { acc, c -> acc.move(c) }
        assertEquals(finalPosition.housesVisited, result)
    }

    @ParameterizedTest
    @MethodSource("part2")
    fun part2(input: String, result: Int) {
        val (santa, robot) = input.chunked(2).fold(Sledge() to Sledge()) { (santa, robot), c ->
            santa.move(c[0]) to robot.move(c[1])
        }
        assertEquals((santa.visited + robot.visited).size, result)
    }

    companion object {
        @JvmStatic
        fun part1() = Stream.of(
                Arguments.of(">", 2),
                Arguments.of("^>v<", 4),
                Arguments.of("^v^v^v^v^v", 2)
        )

        @JvmStatic
        fun part2() = Stream.of(
                Arguments.of("^v", 3),
                Arguments.of("^>v<", 3),
                Arguments.of("^v^v^v^v^v", 11)
        )
    }
}