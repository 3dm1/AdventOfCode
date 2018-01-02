package year2017.day2

import common.resourceToList

fun checksumMinMax(input: List<String>) = checkSum(input) { it.max()!! - it.min()!! }

fun checksumDivision(input: List<String>) = checkSum(input) { row ->
    row.map { x ->
        row.map { y -> x to y }
                .filter { it.second > it.first && it.second % it.first == 0 }
    }.flatten().map { it.second / it.first }.first()
}

inline fun checkSum(input: List<String>, rowEvaluator: (List<Int>) -> Int) = input
        .map { it.split(" ", "\t").map { it.toInt() } }
        .map { rowEvaluator(it) }
        .sum()

fun main(args: Array<String>) {
    val input = resourceToList("2017/day2_checksum.txt")
    println(checksumMinMax(input))
    println(checksumDivision(input))
}