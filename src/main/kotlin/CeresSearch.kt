package com.rzerocorp

import java.io.IOException

var linesSize = 0
var firstLineLength = 0
val dimension = mutableListOf<Pair<Int, Int>>()
var lines = listOf<String>()

fun main() {
    val content = getResourceAsText("/ceres.txt") ?: throw IOException("No input available")
    ceresPart1(content)
    ceresPart2(content)
}

fun ceresPart1(content: String) {
    var total = 0

    initVars(content)
    initializeDimension()

    for (i in 0 until linesSize) {
        for (j in 0 until firstLineLength) {
            for (d in dimension) {
                total += if (xmasOccurrence(i, j, d, lines)) 1 else 0
            }
        }
    }

    println("Part 1 solution: $total")
}

fun ceresPart2(content: String) {
    var total = 0

    initVars(content)
    initializeDimension()

    for (i in 0 until linesSize) {
        for (j in 0 until firstLineLength) {
            total += if (xmasOccurrence(i, j, lines)) 1 else 0
        }
    }

    println("Part 2 solution: $total")
}

private fun initVars(content: String) {
    lines = content.lines()
    linesSize = lines.size
    firstLineLength = lines[0].length
}

private fun initializeDimension() {
    dimension.clear()

    for (dimensionX in -1..1) {
        for (dimensionY in -1..1) {
            if (dimensionX != 0 || dimensionY != 0) {
                dimension.add(Pair(dimensionX, dimensionY))
            }
        }
    }
}

private fun xmasOccurrence(i: Int, j: Int, d: Pair<Int, Int>, lines: List<String>): Boolean {
    val (dx, dy) = d
    for (k in "XMAS".indices) {
        val ii = i + k * dx
        val jj = j + k * dy
        if (ii !in 0 until linesSize || jj !in 0 until firstLineLength) {
            return false
        }
        if (lines[ii][jj] != "XMAS"[k]) {
            return false
        }
    }
    return true
}

private fun xmasOccurrence(i: Int, j: Int, lines: List<String>): Boolean {
    if (i !in 1 until linesSize - 1 || j !in 1 until firstLineLength - 1) {
        return false
    }
    if (lines[i][j] != 'A') {
        return false
    }

    val diag1 = "${lines[i - 1][j - 1]}${lines[i + 1][j + 1]}"
    val diag2 = "${lines[i - 1][j + 1]}${lines[i + 1][j - 1]}"

    return diag1 in listOf("MS", "SM") && diag2 in listOf("MS", "SM")
}