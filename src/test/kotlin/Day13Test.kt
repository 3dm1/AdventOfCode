import day13.getMinDelay
import day13.getSeverity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13Test {

    val input = """
    |0: 3
    |1: 2
    |4: 4
    |6: 4""".trimMargin().lines().map { it.split(": ") }.associate { it[0].toInt() to it[1].toInt() }

    @Test
    fun part1() {
        assertEquals(getSeverity(input), 24)
    }

    @Test
    fun part2() {
        assertEquals(getMinDelay(input), 10)
    }
}