package day18

import common.resourceToList

class Program(private val instructions: List<String>,
              id: Long,
              private val queue: MutableList<Long>,
              private val otherQueue: MutableList<Long>) {

    val registers: MutableMap<String, Long> = mutableMapOf("p" to id)
    private var currentPosition = 0
    var sendCount = 0

    fun exec(): Boolean {
        val instruction = instructions[currentPosition].split(" ")
        when (instruction[0]) {
            "snd" -> {
                otherQueue.add(value(instruction[1]))
                sendCount++
            }
            "rcv" -> queue.takeIf { it.isNotEmpty() }?.apply { registers.put(instruction[1], queue.removeAt(0)) } ?: return true
            "set" -> update(instruction[1], value(instruction[2]))
            "add" -> update(instruction[1], registers.getOrDefault(instruction[1], 0L) + value(instruction[2]))
            "mul" -> update(instruction[1], registers.getOrDefault(instruction[1], 0L) * value(instruction[2]))
            "mod" -> update(instruction[1], registers.getOrDefault(instruction[1], 0L) % value(instruction[2]))
            "jgz" -> if (value(instruction[1]) > 0L) currentPosition += value(instruction[2]).toInt() - 1
        }
        currentPosition++
        return false
    }

    private fun value(value: String) = value.toLongOrNull() ?: registers.getOrDefault(value, 0L)
    private fun update(reg: String, value: Long) = registers.put(reg, value)
}

fun executeInstruction(registers: MutableMap<String, Long>, command: String, sounds: MutableMap<String, Long>): Pair<Int, Long> {
    val instruction = command.split(" ")
    var jump = 1
    var rcv = 0L
    when (instruction[0]) {
        "snd" -> sounds.put(instruction[1], registers.getOrDefault(instruction[1], 0L))
        "rcv" -> if (registers.getOrDefault(instruction[1], 0L) != 0L) rcv = sounds.getOrDefault(instruction[1], 0L)
        "set" -> registers.put(instruction[1], instruction[2].toLongOrNull() ?: registers.getOrDefault(instruction[2], 0))
        "add" -> registers.put(instruction[1], registers.getOrDefault(instruction[1], 0L) +
                (instruction[2].toLongOrNull() ?: registers.getOrDefault(instruction[2], 0L)))
        "mul" -> registers.put(instruction[1], registers.getOrDefault(instruction[1], 0L) *
                (instruction[2].toLongOrNull() ?: registers.getOrDefault(instruction[2], 0L)))
        "mod" -> registers.put(instruction[1], registers.getOrDefault(instruction[1], 0L) %
                (instruction[2].toLongOrNull() ?: registers.getOrDefault(instruction[2], 0L)))
        "jgz" -> if (registers.getOrDefault(instruction[1], 0L) > 0L) {
            jump = instruction[2].toIntOrNull() ?: registers.getOrDefault(instruction[2], 0L).toInt()
        }
    }
    return jump to rcv
}

fun getFirstRecover(input: List<String>, registers: MutableMap<String, Long> = mutableMapOf()): Long {
    val sounds = mutableMapOf<String, Long>()
    var i = 0
    while (true) {
        val command = input[i]
        val (jump, rcv) = executeInstruction(registers, command, sounds)
        i += jump
        rcv.takeIf { it != 0L }?.let { return it }
    }
}

fun getProgramSendCount(programs: List<Program>, index: Int) : Int {
    while (programs.any { !it.exec() }) { }
    return programs[index].sendCount
}

fun main(args: Array<String>) {
    val input = resourceToList("day18_duet.txt")
    println(getFirstRecover(input))

    val p0Queue = mutableListOf<Long>()
    val p1Queue = mutableListOf<Long>()
    val p0 = Program(input, 0L, p0Queue, p1Queue)
    val p1 = Program(input, 1L, p1Queue, p0Queue)
    println(getProgramSendCount(listOf(p0, p1), 1))
}