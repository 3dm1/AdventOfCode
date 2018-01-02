package year2017

import year2017.day24.getBestBridge
import year2017.day24.getConnectionValue
import year2017.day24.getPossibleConnections
import year2017.day24.parseInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day24Test {

    private val rawInput = """
    |0/2
    |2/2
    |2/3
    |3/4
    |3/5
    |0/1
    |10/1
    |9/10""".trimMargin().lines()

    @Test
    fun parsing() {
        assertEquals(parseInput(rawInput),
                mapOf(0 to listOf(0 to 2, 0 to 1),
                        2 to listOf(0 to 2, 2 to 2, 2 to 3),
                        1 to listOf(0 to 1, 10 to 1),
                        3 to listOf(2 to 3, 3 to 4, 3 to 5),
                        4 to listOf(3 to 4),
                        5 to listOf(3 to 5),
                        9 to listOf(9 to 10),
                        10 to listOf(10 to 1, 9 to 10)))
    }

    @Test
    fun generatePossibleConnections() {
        val output = mutableSetOf<Set<Pair<Int, Int>>>()
        getPossibleConnections(parseInput(rawInput), connections = output)
        assertEquals(output.size, 12)
        assertEquals(output, setOf(
                setOf(),
                setOf(0 to 2),
                setOf(0 to 2, 2 to 2),
                setOf(0 to 2, 2 to 2, 2 to 3),
                setOf(0 to 2, 2 to 2, 2 to 3, 3 to 4),
                setOf(0 to 2, 2 to 2, 2 to 3, 3 to 5),
                setOf(0 to 2, 2 to 3),
                setOf(0 to 2, 2 to 3, 3 to 4),
                setOf(0 to 2, 2 to 3, 3 to 5),
                setOf(0 to 1),
                setOf(0 to 1, 10 to 1),
                setOf(0 to 1, 10 to 1, 9 to 10)
        ))
        assertEquals(output.map { getConnectionValue(it) }.max(), 31)
    }

    @Test
    fun bestBridge() {
        val output = mutableSetOf<Set<Pair<Int, Int>>>()
        getPossibleConnections(parseInput(rawInput), connections = output)
        assertEquals(getBestBridge(output), 19)
    }
}