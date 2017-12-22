package day22

import common.resourceToList


typealias Coord2D = Pair<Int, Int>

operator infix fun Coord2D.plus(other: Coord2D) = this.first + other.first to this.second + other.second

enum class Facing(val position: Coord2D) {
    UP(0 to -1),
    DOWN(0 to 1),
    LEFT(-1 to 0),
    RIGHT(1 to 0);

    val right: Facing
        get() = when (this) {
            Facing.UP -> RIGHT
            Facing.RIGHT -> DOWN
            Facing.DOWN -> LEFT
            Facing.LEFT -> UP
        }

    val left: Facing
        get() = when (this) {
            Facing.UP -> LEFT
            Facing.LEFT -> DOWN
            Facing.DOWN -> RIGHT
            Facing.RIGHT -> UP
        }

    val reverse: Facing
        get() = when (this) {
            Facing.UP -> DOWN
            Facing.DOWN -> UP
            Facing.LEFT -> RIGHT
            Facing.RIGHT -> LEFT
        }
}

enum class NodeState {
    INFECTED,
    CLEAN,
    WEAKENED,
    FLAGGED
}

interface Virus {
    fun burst(infectedGrid: MutableMap<Coord2D, NodeState>): Virus
    val infected: Int
}

data class WeakVirus(val position: Coord2D, val facing: Facing = Facing.UP, override val infected: Int = 0) : Virus {
    override fun burst(infectedGrid: MutableMap<Coord2D, NodeState>): Virus {
        val isInfected = infectedGrid.contains(position)
        val newDirection = if (isInfected) facing.right else facing.left
        val newInfected = infected + if (!isInfected) {
            infectedGrid.put(position, NodeState.INFECTED)
            1
        } else {
            infectedGrid.remove(position)
            0
        }
        val newPosition = position + newDirection.position
        return WeakVirus(newPosition, newDirection, newInfected)
    }
}

data class StrongerVirus(val position: Coord2D, val facing: Facing = Facing.UP, override val infected: Int = 0) : Virus {

    override fun burst(infectedGrid: MutableMap<Coord2D, NodeState>): Virus {
        val nodeState = infectedGrid.getOrDefault(position, NodeState.CLEAN)
        val (newDirection, infectionCount) = when (nodeState) {
            NodeState.CLEAN -> {
                infectedGrid.put(position, NodeState.WEAKENED)
                facing.left to infected
            }
            NodeState.WEAKENED -> {
                infectedGrid.put(position, NodeState.INFECTED)
                facing to infected + 1
            }
            NodeState.INFECTED -> {
                infectedGrid.put(position, NodeState.FLAGGED)
                facing.right to infected
            }
            NodeState.FLAGGED -> {
                infectedGrid.remove(position)
                facing.reverse to infected
            }
        }
        return StrongerVirus(position + newDirection.position, newDirection, infectionCount)
    }
}


fun countInfections(virus: Virus, infectedGrid: MutableMap<Coord2D, NodeState>, iterations: Int): Int {
    return generateSequence(virus) { it.burst(infectedGrid) }.drop(1).take(iterations).last().infected
}

fun getInfectedGrid(input: List<String>): MutableMap<Coord2D, NodeState> {
    return input.mapIndexed { y, s -> s.mapIndexed { x, c -> c.takeIf { c == '#' }?.let { x to y } } }
            .flatten()
            .filterNotNull()
            .associate { it to NodeState.INFECTED }
            .toMutableMap()
}

fun main(args: Array<String>) {
    val rawInput = resourceToList("day22_sporifica_virus.txt")
    val center = rawInput.size / 2
    val weakVirus = WeakVirus(center to center)
    val strongerVirus = StrongerVirus(center to center)

    println(countInfections(weakVirus, getInfectedGrid(rawInput), 10_000))
    println(countInfections(strongerVirus, getInfectedGrid(rawInput), 10_000_000))
}
