fun main() {
    val numberStrings = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun listToInt(input: List<Int>): Int = input.joinToString("").toInt()

    fun findLeft(input: String): Int? {
        var cursor = 0
        while (cursor < input.length) {
            if (input[cursor].isDigit()) {
                return input[cursor].toString().toInt()
            }
            val currentSub = input.substring(0, cursor + 1)
            numberStrings.forEach {
                if (currentSub.contains(it.key)) {
                    return it.value.toInt()
                }
            }
            cursor += 1
        }
        return null
    }

    fun findRight(input: String): Int? {
        var cursor = input.length - 1
        while (cursor > -1) {
            if (input[cursor].isDigit()) {
                return input[cursor].digitToInt()
            }
            val currentSub = input.substring(cursor, input.length)
            numberStrings.forEach {
                if (currentSub.contains(it.key)) {
                    return it.value.toInt()
                }
            }
            cursor -= 1
        }
        return null
    }

//    fun replaceStrings(input: String): String {
//        var output = input
//        val numberStrings = mapOf(
//            "one" to "1",
//            "two" to "2",
//            "three" to "3",
//            "four" to "4",
//            "five" to "5",
//            "six" to "6",
//            "seven" to "7",
//            "eight" to "8",
//            "nine" to "9"
//        )
//
//        do {
//            val indexOfValues = mapOf(
//                "one" to 0,
//                "two" to 0,
//                "three" to 0,
//                "four" to 0,
//                "five" to 0,
//                "six" to 0,
//                "seven" to 0,
//                "eight" to 0,
//                "nine" to 0
//            )
//
//            val foundValues = indexOfValues.mapValues { output.indexOf(it.key) }.filterValues { it > -1 }
//            if (foundValues.isNotEmpty()) {
//                val firstFind = foundValues.minBy { it.value }
//                output = output.replaceFirst(firstFind.key, numberStrings[firstFind.key]!!)
//            }
//        } while (foundValues.isNotEmpty())
//
//        return output
//    }

    fun part1(input: List<String>): Int {
        val filteredInputs = input.map { line -> line.toList().mapNotNull { it.toString().toIntOrNull() } }
        val calibrationValues = filteredInputs.map { listToInt(listOf(it.first, it.last)) }
        return calibrationValues.sum()
    }

    fun part2(input: List<String>): Int {
        val lines = input.map {
            val left = findLeft(it)
            val right = findRight(it)
            listOfNotNull(left, right).joinToString("").toInt()
        }
        return lines.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInputPart2 = readInput("Day01_part2_test")
    check(part2(testInputPart2) == 281)

    val input = readInput("Day01")
    println("Part 1 - ${part1(input)}")
    println("Part 2 - ${part2(input)}")
}
