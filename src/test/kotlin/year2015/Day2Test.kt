package year2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import year2015.day2.Dimensions
import year2015.day2.totalRibbon
import year2015.day2.totalWrapPaper

class Day2Test {

    @Test
    fun part1() {
        val wrap1 = Dimensions(2, 3, 4)
        val wrap2 = Dimensions(1, 1, 10)

        assertEquals(wrap1.totalWrapPaper(), 58)
        assertEquals(wrap2.totalWrapPaper(), 43)
    }

    @Test
    fun part2() {
        val wrap1 = Dimensions(2, 3, 4)
        val wrap2 = Dimensions(1, 1, 10)

        assertEquals(wrap1.totalRibbon(), 34)
        assertEquals(wrap2.totalRibbon(), 14)
    }
}