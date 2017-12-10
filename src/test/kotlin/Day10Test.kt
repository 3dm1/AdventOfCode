import day10.denseKnotHash
import day10.knotHash
import day10.part2Prefix
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day10Test {

    @Test
    fun part1() {
        val knotLengths = "3, 4, 1, 5".split(", ").map(String::toInt)
        val data = mutableListOf(*Array(5) { it }).also { it.knotHash(knotLengths = knotLengths) }
        assertEquals(data[0] * data[1], 12)
    }

    @ParameterizedTest
    @MethodSource("day10Provider")
    fun part2(input: String, result: String) {
        val data = mutableListOf(*Array(256) { it })
        assertEquals(data.denseKnotHash(input.map(Char::toInt).plus(part2Prefix)), result)
    }

    companion object {
        @JvmStatic
        fun day10Provider() = Stream.of(
                Arguments.of("", "a2582a3a0e66e6e86e3812dcb672a272"),
                Arguments.of("AoC 2017", "33efeb34ea91902bb2f59c9920caa6cd"),
                Arguments.of("1,2,3", "3efbe78a8d82f29979031a4aa0b16a9d"),
                Arguments.of("1,2,4", "63960835bcdc130f0b66d7ff4f6a5a8e")
        )
    }
}