package year2017

import year2017.day4.isValidNoAnagramPassphrase
import year2017.day4.isValidPassphrase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day4Test {

    @ParameterizedTest
    @MethodSource("part1Provider")
    fun part1(input: String, result: Boolean) {
        Assertions.assertEquals(isValidPassphrase(input), result)
    }

    @ParameterizedTest
    @MethodSource("part2Provider")
    fun part2(input: String, result: Boolean) {
        Assertions.assertEquals(isValidNoAnagramPassphrase(input), result)
    }

    companion object {
        @JvmStatic
        fun part1Provider() = Stream.of(
                Arguments.of("aa bb cc dd ee", true),
                Arguments.of("aa bb cc dd aa", false),
                Arguments.of("aa bb cc dd aaa", true)
        )

        @JvmStatic
        fun part2Provider() = Stream.of(
                Arguments.of("abcde fghij", true),
                Arguments.of("abcde xyz ecdab", false),
                Arguments.of("a ab abc abd abf abj", true),
                Arguments.of("iiii oiii ooii oooi oooo", true),
                Arguments.of("oiii ioii iioi iiio", false)
        )
    }
}