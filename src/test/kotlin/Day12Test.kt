import day12.getProgramGroup
import day12.getTotalGroups
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

    val input = """
        |0 <-> 2
        |1 <-> 1
        |2 <-> 0, 3, 4
        |3 <-> 2, 4
        |4 <-> 2, 3, 6
        |5 <-> 6
        |6 <-> 4, 5""".trimMargin()
            .lines()
            .map { it.split(" <-> ") }
            .associate { it[0].toInt() to it[1].split(", ").map(String::toInt) }

    @Test
    fun part1() {
        assertEquals(getProgramGroup(input, 0).size, 6)
    }

    @Test
    fun part2() {
        assertEquals(getTotalGroups(input).size, 2)
    }
}