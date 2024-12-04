package com.rzerocorp

import java.io.IOException

var total = 0L

fun main() {
    val content = getResourceAsText("/mull.txt") ?: throw IOException("No input available")

    part1(content)
    part2(content)
}

fun part1(content: String) {

    val regex ="mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

    content?.let { content ->
        regex.findAll(content).forEach { matches ->
            val values = matches.value.split("mul(")[1].split(")")[0].split(",")
            total += (values[0].toLong() * values[1].toLong())
        }

        println("Part 1 solution: $total")
    }
}

fun part2(content: String) {
    total = 0L
    var enabled = true
    val regex = """(do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\))""".toRegex()

    content?.let { content ->
        regex.findAll(content).forEach { matches ->
            val str = matches.groups[0]?.value ?: throw IOException("Error")

            if (str.startsWith("do(")) {
                enabled = true
            }

            if (str.startsWith("don't(")) {
                enabled = false
            }

            if (enabled && str.startsWith("mul(")) {
                total += matches.groups[2]?.value?.toLong()!! * matches.groups[3]?.value?.toLong()!!
            }
        }
    }

    println("Part 2 solution: $total")
}

