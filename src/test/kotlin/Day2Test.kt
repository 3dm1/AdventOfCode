import day2.checksumDivision
import day2.checksumMinMax
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun part1() {
        val input = """
            |5 1 9 5
            |7 5 3
            |2 4 6 8""".trimMargin().lines()
        assertEquals(checksumMinMax(input), 18)
    }

    @Test
    fun part2() {
        val input = """
        |5 9 2 8
        |9 4 7 3
        |3 8 6 5""".trimMargin().lines()
        assertEquals(checksumDivision(input), 9)
    }
}