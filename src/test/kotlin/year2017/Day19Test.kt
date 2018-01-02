package year2017

import year2017.day19.getPipePath
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {

    val rawInput = """
        |     |
        |     |  +--+
        |     A  |  C
        | F---|----E|--+
        |     |  |  |  D
        |     +B-+  +--+
        |                """.trimMargin().lines()

    @Test
    fun part1() {
        assertEquals(getPipePath(rawInput).map { (x, y, _) -> rawInput[y][x] }
                .filter(Char::isLetter)
                .joinToString(""), "ABCDEF")
    }

    @Test
    fun part2() {
        assertEquals(getPipePath(rawInput).count() - 1, 38)
    }

}