package year2017

import year2017.day16.computeDance
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun part1() {
        assertEquals(computeDance("abcde", "s1,x3/4,pe/b".split(",")), "baedc")
    }
}