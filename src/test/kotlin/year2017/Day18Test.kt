package year2017

import year2017.day18.Program
import year2017.day18.getFirstRecover
import year2017.day18.getProgramSendCount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun part1() {
        val input = """
            |set a 1
            |add a 2
            |mul a a
            |mod a 5
            |snd a
            |set a 0
            |rcv a
            |jgz a -1
            |set a 1
            |jgz a -2""".trimMargin().lines()
        assertEquals(getFirstRecover(input), 4)
    }

    @Test
    fun part2() {
        val input = """
            |snd 1
            |snd 2
            |snd p
            |rcv a
            |rcv b
            |rcv c
            |rcv d""".trimMargin().lines()
        val p0Queue = mutableListOf<Long>()
        val p1Queue = mutableListOf<Long>()
        val p0 = Program(input, 0L, p0Queue, p1Queue)
        val p1 = Program(input, 1L, p1Queue, p0Queue)
        assertEquals(getProgramSendCount(listOf(p0, p1), 1), 3)
    }
}