package year2017

import year2017.day1.reverseCaptchaHalfwayAround
import year2017.day1.reverseCaptchaSingle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day1Test {

    @ParameterizedTest
    @MethodSource("day1Provider")
    fun day1(input: String, result: Int) {
        assertEquals(reverseCaptchaSingle(input), result)
    }

    @ParameterizedTest
    @MethodSource("day2Provider")
    fun day2(input: String, result: Int) {
        assertEquals(reverseCaptchaHalfwayAround(input), result)
    }

    companion object {
        @JvmStatic
        fun day1Provider() = Stream.of(
                Arguments.of("1122", 3),
                Arguments.of("1111", 4),
                Arguments.of("1234", 0),
                Arguments.of("91212129", 9)
        )

        @JvmStatic
        fun day2Provider() = Stream.of(
                Arguments.of("1212", 6),
                Arguments.of("1221", 0),
                Arguments.of("123425", 4),
                Arguments.of("123123", 12),
                Arguments.of("12131415", 4)
        )
    }
}