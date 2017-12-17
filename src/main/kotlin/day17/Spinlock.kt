package day17

fun computeSpinLock(jump: Int, loopCount: Int, buffer: MutableList<Int>): Int {
    return (1..loopCount).fold(0) {position, count ->
        ((position + jump) % buffer.size + 1).apply { buffer.add(this, count) }
    }
}

fun main(args: Array<String>) {
    val input = 355
    val buffer = mutableListOf(0)
    val lastPosition = computeSpinLock(input, 2017, buffer)
    println(buffer[lastPosition + 1])

    buffer.clear()
    buffer.add(0)
    val (_, valueAtOne) = (1..50_000_000).fold(0 to Int.MIN_VALUE) { (position, indexOne), count ->
        val newPosition = (position + input) % count + 1
        val valueOne = if (newPosition == 1) count else indexOne
        newPosition to valueOne
    }
    println(valueAtOne)
}