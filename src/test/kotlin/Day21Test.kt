import day21.Grid
import day21.computeFractal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day21Test {

    @ParameterizedTest
    @MethodSource("sliceProvider")
    fun slice(input: String, result: List<Grid>) {
        assertEquals(Grid(input).slice(), result)
    }

    @Test
    fun part1() {
        val input = """
            |../.# => ##./#../...
            |.#./..#/### => #..#/..../..../#..#""".trimMargin().lines()
        val initialState = Grid(".#./..#/###")
        val rules = input.map { it.split(" => ") }
                .associate { Grid(it[0]) to Grid(it[1]) }

        val fractal = (1..2).fold(initialState) { acc, _ -> computeFractal(rules, acc) }
        assertEquals(fractal.grid.fold(0) { acc, s -> acc + s.count { it == '#' } }, 12)
    }

    companion object {
        @JvmStatic
        fun sliceProvider() = Stream.of(
                Arguments.of("abcd/efgh/ijkl/mnop",
                        listOf(Grid("ab/ef"),
                                Grid("cd/gh"),
                                Grid("ij/mn"),
                                Grid("kl/op"))),
                Arguments.of("abc/def/ghi",
                        listOf(Grid("abc/def/ghi"))),
                Arguments.of("ab/cd",
                        listOf(Grid("ab/cd"))),
                Arguments.of("abcdef/ghijkl/mnopqr/stuvwy/z01234/56789!",
                        listOf(Grid("ab/gh"),
                                Grid("cd/ij"),
                                Grid("ef/kl"),
                                Grid("mn/st"),
                                Grid("op/uv"),
                                Grid("qr/wy"),
                                Grid("z0/56"),
                                Grid("12/78"),
                                Grid("34/9!")))
        )
    }
}