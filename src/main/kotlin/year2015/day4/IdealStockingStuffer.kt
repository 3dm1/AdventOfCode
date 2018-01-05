package year2015.day4

import java.security.MessageDigest

val md5Digest: MessageDigest by lazy { MessageDigest.getInstance("MD5") }

fun String.md5(digest: MessageDigest) = digest.let {
    it.update(this.toByteArray())
    it.digest()
}.joinToString("") { "%02x".format(it) }


fun findLowestNumber(secret: String, zeroCount: Int): Int {
    val prefix = CharArray(zeroCount) { '0' }.joinToString("")
    return (0..Int.MAX_VALUE).asSequence().find { (secret + it).md5(md5Digest).startsWith(prefix) } ?: Int.MIN_VALUE
}

fun main(args: Array<String>) {
    println(findLowestNumber("ckczppom", 5))
    println(findLowestNumber("ckczppom", 6))
}