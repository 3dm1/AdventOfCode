package day7

import common.resourceToList

data class Shout(val name: String, val weight: Int, val children: List<String>?)

fun prepareShouts(input: List<String>): List<Shout> {
    val lineRegex = "(\\w*) \\((\\d*)\\)(?: -> ([\\w, ]*))?".toRegex()
    return input
            .map { lineRegex.matchEntire(it)?.groups!! }
            .map { Shout(it[1]?.value!!, it[2]?.value!!.toInt(), it[3]?.value?.split(", ")) }
}

fun findParentNode(input: List<Shout>): String? {
    return input.flatMap { it.children?.plus(it.name) ?: listOf(it.name) }
            .groupingBy { it }
            .eachCount()
            .minBy { it.value }
            ?.key
}

fun getCorrectedNodeWeight(input: List<Shout>): Int {
    val weightTable = input.associateBy { it.name }
    val totalNodeWeight = input.associate { it.name to getNodeTotalWeight(it, weightTable) }
    val unbalancedNode = totalNodeWeight.filter { (name, totalWeight) ->
        (totalWeight - weightTable[name]!!.weight) % (weightTable[name]!!.children?.size ?: 1) != 0
    }
    val unbalancedChildren = unbalancedNode.filter { weightTable[it.key]?.children!!.all { it !in unbalancedNode.keys } }
            .flatMap { (name, _) -> weightTable[name]?.children!! }
            .distinctBy { totalNodeWeight[it]!! }
    val badChild = unbalancedChildren.maxBy { totalNodeWeight[it]!! }
    val goodChild = unbalancedChildren.minBy { totalNodeWeight[it]!! }

    return weightTable[badChild]!!.weight - (totalNodeWeight[badChild]!! - totalNodeWeight[goodChild]!!)
}

fun getNodeTotalWeight(shout: Shout, weightTable: Map<String, Shout>): Int {
    return shout.weight + (shout.children
            ?.map { weightTable[it]!! }
            ?.sumBy { getNodeTotalWeight(it, weightTable) } ?: 0)
}

fun main(args: Array<String>) {
    val input = resourceToList("day7_recursive_circus.txt")
    val shouts = prepareShouts(input)
    println(findParentNode(shouts))
    println(getCorrectedNodeWeight(shouts))
}