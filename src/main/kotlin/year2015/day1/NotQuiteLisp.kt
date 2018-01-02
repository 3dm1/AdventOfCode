package year2015.day1

import common.resourceToString

fun getFinalFloor(input: String) = input.sumBy { if (it == '(') 1 else -1 }

fun getFirstTimeInBasement(input: String) = input.foldIndexed(0) { index, acc, c ->
    (acc + if (c == '(') 1 else -1).also { if (it < 0) return index + 1 }
}

fun main(args: Array<String>) {
    val input = resourceToString("2015/day1_not_quite_lisp.txt")
    println(getFinalFloor(input))
    println(getFirstTimeInBasement(input))
}