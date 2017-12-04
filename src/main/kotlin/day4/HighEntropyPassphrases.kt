package day4

import common.resourceToList

fun isValidPassphrase(phrase: String): Boolean {
    val words = phrase.split(" ")
    return words.size == words.distinct().size
}

fun isValidNoAnagramPassphrase(phrase: String): Boolean {
    val chars = phrase.split(" ").map { it.toCharArray().sorted() }
    return chars.size == chars.distinct().size
}

fun main(args: Array<String>) {
    val input = resourceToList("day4_passPhrase.txt")
    val validPassphrase = input.filter(::isValidPassphrase)
    val validNonAnagramPassphraseCount = validPassphrase.filter(::isValidNoAnagramPassphrase)
    println("Part 1 = ${validPassphrase.count()}")
    println("Part 2 = ${validNonAnagramPassphraseCount.count()}")
}