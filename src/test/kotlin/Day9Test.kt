import day9.computeStreamValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day9Test {

    @ParameterizedTest
    @MethodSource("day9ProviderPart1")
    fun part1(input: String, result: Int) {
        Assertions.assertEquals(computeStreamValue(input).totalValue, result)
    }

    @ParameterizedTest
    @MethodSource("day9ProviderPart2")
    fun part2(input: String, result: Int) {
        Assertions.assertEquals(computeStreamValue(input).garbageCount, result)
    }

    companion object {
        @JvmStatic
        fun day9ProviderPart1() = Stream.of(
                Arguments.of("{}", 1),
                Arguments.of("{{{}}}", 6),
                Arguments.of("{{},{}}", 5),
                Arguments.of("{{{},{},{{}}}}", 16),
                Arguments.of("{<a>,<a>,<a>,<a>}", 1),
                Arguments.of("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
                Arguments.of("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
                Arguments.of("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3)
        )

        @JvmStatic
        fun day9ProviderPart2() = Stream.of(
                Arguments.of("{<>}", 0),
                Arguments.of("{<random characters>}", 17),
                Arguments.of("{<<<<>}", 3),
                Arguments.of("{<{!>}>}", 2),
                Arguments.of("{<!!>}", 0),
                Arguments.of("{<!!!>>}", 0),
                Arguments.of("{<{o\"i!a,<{i<a>}", 10)
        )
    }
}