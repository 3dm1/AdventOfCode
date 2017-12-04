package day1

import common.resourceToString

fun reverseCaptchaSingle(captcha: String) = computeReverseCaptcha(captcha, 1)

fun reverseCaptchaHalfwayAround(captcha: String) = computeReverseCaptcha(captcha, captcha.length / 2)

private fun computeReverseCaptcha(captcha: String, distance: Int) = captcha
        .filterIndexed { index, char -> char == captcha[(index + distance) % captcha.length] }
        .sumBy { char -> char.toInt() - '0'.toInt() }

fun main(args: Array<String>) {
    val input = resourceToString("day1_captcha.txt")
    println("Part 1 = ${reverseCaptchaSingle(input)}")
    println("Part 2 = ${reverseCaptchaHalfwayAround(input)}")
}
