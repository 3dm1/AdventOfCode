package common

import java.io.File

internal object Resources

fun resourceToString(resourceName: String) = File(Resources.javaClass.classLoader.getResource(resourceName).toURI())
        .readText()

fun resourceToList(resourceName: String) = File(Resources.javaClass.classLoader.getResource(resourceName).toURI())
        .readLines()
