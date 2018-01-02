package year2017

import year2017.day20.Particle
import year2017.day20.getRemainingParticles
import year2017.day20.getSlowestParticle
import year2017.day20.inputRegex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun part1() {
        val rawInput = """
            |p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>
            |p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>""".trimMargin().lines()
        val input = rawInput
                .mapNotNull { inputRegex.matchEntire(it) }
                .map { it.groupValues.drop(1).map(String::toInt) }
                .map { Particle(it[0], it[1], it[2], it[3], it[4], it[5], it[6], it[7], it[8]) }

        assertEquals(getSlowestParticle(input), 0)
    }

    @Test
    fun part2() {
        val rawInput = """
            |p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>
            |p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>
            |p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>
            |p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>""".trimMargin().lines()

        val input = rawInput
                .mapNotNull { inputRegex.matchEntire(it) }
                .map { it.groupValues.drop(1).map(String::toInt) }
                .map { Particle(it[0], it[1], it[2], it[3], it[4], it[5], it[6], it[7], it[8]) }
        assertEquals(getRemainingParticles(input), 1)

    }
}