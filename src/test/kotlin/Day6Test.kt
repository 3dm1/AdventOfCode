import day6.countRedistributionCycles
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    private val input = "0 2 7 0".split(" ").map { it.toInt() }

    @Test
    fun part1() {
        val countRedistributionCycles = countRedistributionCycles(input)
        assertEquals(countRedistributionCycles.size - 1, 5)
    }

    @Test
    fun part2() {
        val countRedistributionCycles = countRedistributionCycles(input)
        val repeatedState = countRedistributionCycles.last()
        assertEquals(countRedistributionCycles.size - countRedistributionCycles.indexOf(repeatedState) - 1, 4)
    }
}