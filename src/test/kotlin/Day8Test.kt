import day8.evaluateInstructions
import day8.findMaxRegisterValue
import day8.parseRegisterInstructions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    private val input = """
        |b inc 5 if a > 1
        |a inc 1 if b < 5
        |c dec -10 if a >= 1
        |c inc -20 if c == 10""".trimMargin().lines()

    @Test
    fun part1() {
        val instructions = parseRegisterInstructions(input)
        val registers = evaluateInstructions(instructions)
        assertEquals(findMaxRegisterValue(registers.first), 1)
    }

    @Test
    fun part2() {
        val instructions = parseRegisterInstructions(input)
        val registers = evaluateInstructions(instructions)
        assertEquals(registers.second, 10)
    }
}