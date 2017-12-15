package day15

import kotlin.coroutines.experimental.buildSequence

const val GEN_A_SEED = 634L
const val GEN_B_SEED = 301L

fun createGenerator(seed: Long, multiplyingFactor: Int, pickyFactor: Int = 1) = buildSequence {
    var value = seed
    while (true) {
        value = (value * multiplyingFactor) % 2147483647
        if (value % pickyFactor == 0L) yield(value)
    }
}

fun getMatchesCount(genA: Sequence<Long>, genB: Sequence<Long>, iterationCount: Int): Int {
    return genA.take(iterationCount).zip(genB).count { (it.first and 0xFFFF) == (it.second and 0xFFFF) }
}

fun main(args: Array<String>) {
    println(getMatchesCount(createGenerator(GEN_A_SEED, 16807),
            createGenerator(GEN_B_SEED, 48271), 40_000_000))

    println(getMatchesCount(createGenerator(GEN_A_SEED, 16807, 4),
            createGenerator(GEN_B_SEED, 48271, 8), 5_000_000))
}