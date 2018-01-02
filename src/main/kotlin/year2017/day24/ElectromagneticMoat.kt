package year2017.day24

import common.resourceToList

fun parseInput(input: List<String>): Map<Int, List<Pair<Int, Int>>> {
    return input.map { conn -> conn.split("/").map(String::toInt) }
            .flatMap { listOf(it[0] to (it[0] to it[1]), it[1] to (it[0] to it[1])) }
            .groupBy { it.first }
            .map { it.key to it.value.map { it.second }.distinct() }
            .toMap()
}

fun getPossibleConnections(connMap: Map<Int, List<Pair<Int, Int>>>,
                           currentPin: Int = 0,
                           currentConnection: Set<Pair<Int, Int>> = emptySet(),
                           connections: MutableSet<Set<Pair<Int, Int>>>) {
    connections.add(currentConnection)
    connMap[currentPin]
            ?.filter { it !in currentConnection && currentConnection + it !in connections }
            ?.forEach {
                val newConn = currentConnection + it
                val newPin = if (it.first != currentPin) it.first else it.second
                getPossibleConnections(connMap, newPin, newConn, connections)
            }
}

fun getConnectionValue(connection: Set<Pair<Int, Int>>): Int {
    return connection.map { it.first + it.second }.sum()
}

fun getBestBridge(bridges: MutableSet<Set<Pair<Int, Int>>>): Int? {
    val maxSize = bridges.maxBy { it.size }?.size
    return bridges.filter { it.size == maxSize }.map(::getConnectionValue).max()
}

fun main(args: Array<String>) {
    val rawInput = resourceToList("2017/day24_electromagnetic_moat.txt")
    val connMap = parseInput(rawInput)

    val connections = mutableSetOf<Set<Pair<Int, Int>>>()
    getPossibleConnections(connMap, connections = connections)
    println(connections.size)
    println(connections.map(::getConnectionValue).max())
    println(getBestBridge(connections))
}
