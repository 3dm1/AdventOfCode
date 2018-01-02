package year2017.day20

import common.resourceToList
import kotlin.math.abs

val number = "(-?\\d+)"
val inputRegex = "p=<$number,$number,$number>, v=<$number,$number,$number>, a=<$number,$number,$number>".toRegex()

data class Coordinate(val x: Int, val y: Int, val z: Int) : Comparable<Coordinate> {
    private fun toManhattan() = abs(x) + abs(y) + abs(z)
    override fun compareTo(other: Coordinate) = toManhattan().compareTo(other.toManhattan())
    operator infix fun plus(other: Coordinate): Coordinate = Coordinate(x + other.x, y + other.y, z + other.z)
}

data class Particle(val position: Coordinate,
                    val velocity: Coordinate,
                    val acceleration: Coordinate) {
    constructor(px: Int, py: Int, pz: Int, vx: Int, vy: Int, vz: Int, ax: Int, ay: Int, az: Int) :
            this(Coordinate(px, py, pz), Coordinate(vx, vy, vz), Coordinate(ax, ay, az))

    fun move(): Particle {
        val newV = this.velocity + acceleration
        val newPos = this.position + newV
        return copy(position = newPos, velocity = newV)
    }
}

fun getSlowestParticle(input: List<Particle>) = input.withIndex()
        .minBy { it.value.acceleration }
        ?.index ?: throw IllegalStateException("No one is the slowest")

fun getRemainingParticles(input: List<Particle>) = (0..1000).fold(input) { prev, _ ->
    prev.map { it.move() }
            .groupBy { it.position }
            .filterValues { it.size == 1 }
            .flatMap { it.value }
}.size

fun main(args: Array<String>) {
    val input = resourceToList("2017/day20_particle_swarm.txt")
            .mapNotNull { inputRegex.matchEntire(it) }
            .map { it.groupValues.drop(1).map(String::toInt) }
            .map { Particle(it[0], it[1], it[2], it[3], it[4], it[5], it[6], it[7], it[8]) }

    println(getSlowestParticle(input))
    println(getRemainingParticles(input))
}