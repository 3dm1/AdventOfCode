import day15.createGenerator
import day15.getMatchesCount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun part1() {
        assertEquals(getMatchesCount(createGenerator(65L, 16807),
                createGenerator(8921L, 48271), 40_000_000), 588)
    }

    @Test
    fun part2() {
        assertEquals(getMatchesCount(createGenerator(65L, 16807, 4),
                createGenerator(8921L, 48271, 8), 5_000_000), 309)
    }
}