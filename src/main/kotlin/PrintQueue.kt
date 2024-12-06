package com.rzerocorp

import java.io.IOException

fun main() {
    val (rawRules, updates) = getResourceAsText("/queue.txt")?.trim()?.split("\n\n") ?: throw IOException("No input available") // File("./queue.txt").readText().trim().split("\n\n")

    queuePart1(rawRules, updates)
    queuePart2(rawRules, updates)
}

fun queuePart1(rawRules: String, updates: String) {
    val rules = initializeRules(rawRules)
    val updatesList = updates.split("\n").map { it.split(",").map(String::toInt) }
    var total = 0

    for (update in updatesList) {
        val (good, mid) = followsRules(rules, update)
        if (good) {
            total += mid
        }
    }

    println("Part 1 solution: = $total")
}

fun queuePart2(rawRules: String, updates: String) {
    val rules = initializeRules(rawRules)
    val updatesList = updates.split("\n").map { it.split(",").map(String::toInt) }
    var total = 0

    for (update in updatesList) {
        if (followsRules(rules, update).first) {
            continue
        }

        val sorted = sort(rules, update.toMutableList())
        total += sorted[sorted.size / 2]
    }

    println("Part 2 solution: = $total")
}

private fun initializeRules(rawRules: String): List<Pair<Int, Int>> {
    val rules = mutableListOf<Pair<Int, Int>>()

    for (line in rawRules.split("\n")) {
        val (a, b) = line.split("|").map { it.toInt() }
        rules.add(Pair(a, b))
    }

    return rules
}

private fun followsRules(rules: List<Pair<Int, Int>>, update: List<Int>): Pair<Boolean, Int> {
    val index = mutableMapOf<Int, Int>()
    for ((i, num) in update.withIndex()) {
        index[num] = i
    }

    for ((a, b) in rules) {
        if (index.containsKey(a) && index.containsKey(b) && index[a]!! >= index[b]!!) {
            return Pair(false, 0)
        }
    }
    return Pair(true, update[update.size / 2])
}

private fun sort(rules: List<Pair<Int, Int>>, update: MutableList<Int>): List<Int> {
    while (true) {
        var isSorted = true
        for (i in 0 until update.size - 1) {
            if (Pair(update[i + 1], update[i]) in rules) {
                isSorted = false
                update[i] = update[i + 1].also { update[i + 1] = update[i] }
            }
        }
        if (isSorted) {
            return update
        }
    }
}