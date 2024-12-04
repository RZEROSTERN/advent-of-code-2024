package com.rzerocorp

fun main() {
    val lines = getResourceAsText("/rednose.txt")?.lines()
    var partOne = 0
    var partTwo = 0

    lines?.forEach { line ->
        line.trim().split("\n").forEach {
            if(isOkay(it.split(" "))) {
                partOne++
            } else {
                for(i in 0 until it.split(" ").size) {
                    var newline = it.split(" ") as ArrayList<String>
                    newline.removeAt(i)

                    if(isOkay(newline)) {
                        partTwo++
                        break
                    }
                }
            }
        }
    }

    println("Part 1 solution: $partOne")
    println("Part 2 solution: ${partOne + partTwo}")
}

fun isOkay(line: List<String>): Boolean {
    val increasing = if(line[1].toInt() > line[0].toInt()) true else false
    val diffAllowed = if(increasing) arrayListOf(1, 2, 3) else arrayListOf(-1, -2, -3)

    for(i in 1 until line.size) {
        if(line[i].toInt() - line[i-1].toInt() !in diffAllowed ) {
            return false
        }
    }

    return true
}
