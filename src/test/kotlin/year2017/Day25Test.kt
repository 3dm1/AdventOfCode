package year2017

import year2017.day25.TuringMachine
import year2017.day25.runBlueprint
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day25Test {

    data class ExampleTuringMachine(val currentPosition: Int = 0, val state: State = State.A) : TuringMachine {
        enum class State { A, B }

        override fun run(tape: MutableSet<Int>) : TuringMachine {
            return when(state) {
                State.A -> {
                    if (currentPosition in tape) {
                        tape.remove(currentPosition)
                        ExampleTuringMachine(currentPosition - 1, State.B)
                    } else {
                        tape.add(currentPosition)
                        ExampleTuringMachine(currentPosition + 1, State.B)
                    }
                }
                State.B -> {
                    if (currentPosition in tape) {
                        tape.add(currentPosition)
                        ExampleTuringMachine(currentPosition + 1, State.A)
                    } else {
                        tape.add(currentPosition)
                        ExampleTuringMachine(currentPosition - 1, State.A)
                    }
                }
            }
        }
    }

    @Test
    fun part1() {
        val tape = mutableSetOf<Int>()
        runBlueprint(tape, ExampleTuringMachine(), 6)
        assertEquals(tape.size, 3)
    }
}