package com.rzerocorp

import kotlin.math.abs

fun main() {
    val left = arrayListOf<Int>()
    val right = arrayListOf<Int>()
    var total = 0
    val lines = getResourceAsText("/hysteria.txt")?.lines()

    lines?.forEach { line ->
        line.trim().split("\n").forEach {
            val items = it.split("   ")
            left.add(items[0].toInt())
            right.add(items[1].toInt())
        }
    }

    left.sort()
    right.sort()

    for(i in 0 until left.size) {
        total += abs(left[i] - right[i])
    }

    println("Part 1 Solution: $total")

    total = 0

    for(i in 0 until left.size) {
        val k = left[i]
        var count = 0

        for(j in 0 until right.size) {
            if(right[j] == k) {
                count++
            }
        }

        total += abs(k * count)
    }

    println("Part 2 Solution: $total")
}

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()
