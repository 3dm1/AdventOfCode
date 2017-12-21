package day21

import common.resourceToList
import kotlin.system.measureTimeMillis


data class Grid(private val rawGrid: String) {
    val grid: List<String> = rawGrid.split("/")
    val size: Int
        get() = grid.size

    fun countOn() = grid.sumBy { it.count { it == '#' } }

    fun slice() = slice(if (grid.size % 2 == 0) 2 else 3)

    fun slice(size: Int): List<Grid> {
        val sectionCount = grid.size / size
        return grid.map { row -> (0 until sectionCount).map { row.slice(it * size until (it + 1) * size) } }
                .chunked(size)
                .map { it.reduce { acc, list -> acc.zip(list) { a, b -> "$a/$b" } } }
                .flatten()
                .map { Grid(it) }
    }

    fun rotate(degrees: Int) = degrees.takeIf { it % 90 == 0 }?.let {
        when ((degrees / 90) % 4) {
            1 -> {
                val currentGrid = grid.map { it.toCharArray() }
                for (x in 0 until currentGrid.size) {
                    for (y in x until size - x - 1) {
                        val temp = currentGrid[x][y]
                        currentGrid[x][y] = currentGrid[y][size - x - 1]
                        currentGrid[y][size - x - 1] = currentGrid[size - x - 1][size - y - 1]
                        currentGrid[size - x - 1][size - y - 1] = currentGrid[size - y - 1][x]
                        currentGrid[size - y - 1][x] = temp
                    }
                }
                Grid(currentGrid.joinToString("/") { it.joinToString("") })
            }
            2 -> Grid(rawGrid.reversed())
            3 -> {
                val currentGrid = grid.map { it.toCharArray() }
                for (x in 0 until currentGrid.size) {
                    for (y in x until size - x - 1) {
                        val temp = currentGrid[x][y]
                        currentGrid[x][y] = currentGrid[y][size - x - 1]
                        currentGrid[y][size - x - 1] = currentGrid[size - x - 1][size - y - 1]
                        currentGrid[size - x - 1][size - y - 1] = currentGrid[size - y - 1][x]
                        currentGrid[size - y - 1][x] = temp
                    }
                }
                Grid(currentGrid.joinToString("/") { it.joinToString("") }.reversed())
            }
            else -> copy()
        }
    } ?: throw IllegalArgumentException("Rotate: $degrees is invalid. Should be a multiple of 90")

    fun flip() = Grid(grid.asReversed().joinToString("/"))

    operator infix fun plus(other: Grid) = Grid(grid.zip(other.grid, String::plus).joinToString("/"))

    infix fun compose(other: Grid) = Grid((grid + other.grid).joinToString("/"))
}

fun computeFractal(rules: Map<Grid, Grid>, grid: Grid): Grid {
    val sectionSize = if (grid.size % 2 == 0) 2 else 3
    return grid.slice(sectionSize)
            .map { section -> rules[section]!! }
            .chunked(grid.size / sectionSize)
            .map { it.reduce(Grid::plus) }
            .reduce(Grid::compose)

}

fun getAllRules(rules: List<String>) = rules.map {
    val (rule, output) = it.split(" => ")
    listOf(Grid(rule), Grid(rule).flip()).map { grid ->
        listOf(90, 180, 270).map { grid.rotate(it) } + grid
    }.flatten().map { it to Grid(output) }
}.flatten().toMap()


fun main(args: Array<String>) {
    val initialState = Grid(".#./..#/###")
    val input = resourceToList("day21_fractal_art.txt")
    val rules = getAllRules(input)

    val fractals = generateSequence(initialState) { computeFractal(rules, it) }
    println(fractals.take(6).last().countOn())
    println(fractals.take(19).last().countOn())
}


