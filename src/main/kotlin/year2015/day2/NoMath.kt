package year2015.day2

import common.resourceToList

typealias Dimensions = Triple<Int, Int, Int>

fun Dimensions.areas() = listOf(first * second, first * third, second * third)

fun Dimensions.faces() = listOf(first, second, third)

fun Dimensions.volume() = faces().reduce(Int::times)

fun Dimensions.totalWrapPaper() = areas().sumBy { it * 2 } + areas().min()!!

fun Dimensions.totalRibbon() = faces().sorted().take(2).sumBy { it * 2 } + volume()

fun getTotalWrapPaperArea(packages: List<Dimensions>) = packages.sumBy(Dimensions::totalWrapPaper)

fun getTotalRibbonLength(packages: List<Dimensions>) = packages.sumBy(Dimensions::totalRibbon)

fun main(args: Array<String>) {
    val input = resourceToList("2015/day2_no_math.txt")
            .map { it.split("x").map(String::toInt) }
            .map { (first, second, third) -> Dimensions(first, second, third) }

    println(getTotalWrapPaperArea(input))
    println(getTotalRibbonLength(input))
}