package year2017.day25

interface TuringMachine {
    fun run(tape: MutableSet<Int>): TuringMachine
}

data class Part1TuringMachine(val currentPosition: Int = 0, val state: State = State.A) : TuringMachine {
    enum class State { A, B, C, D, E, F }

    override fun run(tape: MutableSet<Int>): TuringMachine {
        return when (state) {
            Part1TuringMachine.State.A -> {
                if (currentPosition in tape) {
                    tape.remove(currentPosition)
                    copy(currentPosition = currentPosition - 1, state = State.B)
                } else {
                    tape.add(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.B)
                }
            }
            Part1TuringMachine.State.B -> {
                if (currentPosition in tape) {
                    tape.remove(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.E)
                } else {
                    tape.add(currentPosition)
                    copy(currentPosition = currentPosition - 1, state = State.C)
                }
            }
            Part1TuringMachine.State.C -> {
                if (currentPosition in tape) {
                    tape.remove(currentPosition)
                    copy(currentPosition = currentPosition - 1, state = State.D)
                } else {
                    tape.add(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.E)
                }
            }
            Part1TuringMachine.State.D -> {
                tape.add(currentPosition)
                copy(currentPosition = currentPosition - 1, state = State.A)
            }
            Part1TuringMachine.State.E -> {
                if (currentPosition in tape) {
                    tape.remove(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.F)
                } else {
                    tape.remove(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.A)
                }
            }
            Part1TuringMachine.State.F -> {
                if (currentPosition in tape) {
                    tape.add(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.A)
                } else {
                    tape.add(currentPosition)
                    copy(currentPosition = currentPosition + 1, state = State.E)
                }
            }
        }
    }
}

fun runBlueprint(tape: MutableSet<Int>, machine: TuringMachine, stepCount: Int) {
    (0 until stepCount).fold(machine) { acc, _ -> acc.run(tape) }
}

fun main(args: Array<String>) {

    val tape = mutableSetOf<Int>()
    runBlueprint(tape, Part1TuringMachine(), 12_861_455)
    println(tape.size)
}