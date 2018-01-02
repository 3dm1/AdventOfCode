package year2017.day9

import common.resourceToString

class GarbageStream(val commands: MutableList<Char>, var currentLevel: Int, var totalValue: Int, var garbageCount: Int) {
    var lastValidCommand: Char? = null
        get() = commands.lastOrNull()

    fun addCommand(command: Char) = commands.add(command)
    override fun toString() = "Commands: $commands - CurrentLevel: $currentLevel - TotalValue: $totalValue"
}

fun computeStreamValue(input: String): GarbageStream {
    return input.fold(GarbageStream(mutableListOf(), 0, 0, 0)) { state, char ->
        when {
            state.lastValidCommand == '!' -> state.apply { commands.removeAt(commands.size - 1) }
            state.lastValidCommand == '<' && char != '>' && char != '!' -> state.apply { garbageCount++ }
            else -> when (char) {
                '{' -> state.apply {
                    addCommand(char)
                    currentLevel++
                    totalValue += currentLevel
                }
                '}' -> state.apply {
                    addCommand(char)
                    currentLevel--
                }
                '<', '>', '!' -> state.apply { addCommand(char) }
                else -> state
            }
        }
    }
}

fun main(args: Array<String>) {
    val input = resourceToString("2017/day9_stream_processing.txt")
    val streamValue = computeStreamValue(input)
    println(streamValue.totalValue)
    println(streamValue.garbageCount)
}