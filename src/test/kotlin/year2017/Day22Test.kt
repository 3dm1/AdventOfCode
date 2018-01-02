package year2017

import year2017.day22.StrongerVirus
import year2017.day22.WeakVirus
import year2017.day22.countInfections
import year2017.day22.getInfectedGrid
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day22Test {

    private val rawInput = """
            |..#
            |#..
            |...""".trimMargin().lines()

    @ParameterizedTest
    @MethodSource("infectionCountProvider1")
    fun part1(iteration: Int, result: Int) {
        val infectedGrid = getInfectedGrid(rawInput)
        val center = rawInput.size / 2
        val virus = WeakVirus(center to center)
        assertEquals(countInfections(virus, infectedGrid, iteration), result)
    }

    @ParameterizedTest
    @MethodSource("infectionCountProvider2")
    fun part2(iteration: Int, result: Int) {
        val infectedGrid = getInfectedGrid(rawInput)
        val center = rawInput.size / 2
        val virus = StrongerVirus(center to center)
        assertEquals(countInfections(virus, infectedGrid, iteration), result)
    }


    companion object {
        @JvmStatic
        fun infectionCountProvider1() = Stream.of(
                Arguments.of(7, 5),
                Arguments.of(70, 41),
                Arguments.of(10_000, 5587)
        )

        @JvmStatic
        fun infectionCountProvider2() = Stream.of(
                Arguments.of(100, 26),
                Arguments.of(10_000_000, 2511944)
        )
    }
}