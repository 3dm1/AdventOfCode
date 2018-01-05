package year2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import year2015.day4.findLowestNumber

class Day4Test {

    @Test
    fun part1() {
        assertEquals(findLowestNumber("abcdef", 5), 609043)
        assertEquals(findLowestNumber("pqrstuv", 5), 1048970)
    }
}