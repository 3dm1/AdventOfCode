import day11.getMinDistance
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day11Test {

    @ParameterizedTest
    @MethodSource("day11Part1Provider")
    fun part1(input: String, result: Int) {
        assertEquals(getMinDistance(input.split(",")).first, result)
    }

    @ParameterizedTest
    @MethodSource("day11Part2Provider")
    fun part2(input: String, result: Int) {
        assertEquals(getMinDistance(input.split(",")).second, result)
    }

    companion object {
        @JvmStatic
        fun day11Part1Provider() = Stream.of(
                Arguments.of("ne,ne,ne", 3),
                Arguments.of("ne,ne,sw,sw", 0),
                Arguments.of("ne,ne,s,s", 2),
                Arguments.of("se,sw,se,sw,sw", 3)
        )

        @JvmStatic
        fun day11Part2Provider() = Stream.of(
                Arguments.of("ne,ne,ne", 3),
                Arguments.of("ne,ne,sw,sw", 2),
                Arguments.of("ne,ne,s,s", 2),
                Arguments.of("se,sw,se,sw,sw", 3)
        )
    }
}