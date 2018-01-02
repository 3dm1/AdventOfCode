package year2017

import year2017.day7.findParentNode
import year2017.day7.getCorrectedNodeWeight
import year2017.day7.prepareShouts
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    private val input = """
        |pbga (66)
        |xhth (57)
        |ebii (61)
        |havc (66)
        |ktlj (57)
        |fwft (72) -> ktlj, cntj, xhth
        |qoyq (66)
        |padx (45) -> pbga, havc, qoyq
        |tknk (41) -> ugml, padx, fwft
        |jptl (61)
        |ugml (68) -> gyxo, ebii, jptl
        |gyxo (61)
        |cntj (57)""".trimMargin().lines()

    @Test
    fun part1() {
        val shouts = prepareShouts(input)
        assertEquals(findParentNode(shouts), "tknk")
    }

    @Test
    fun part2() {
        val shouts = prepareShouts(input)
        assertEquals(getCorrectedNodeWeight(shouts), 60)
    }
}