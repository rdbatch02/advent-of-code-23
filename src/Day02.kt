data class GameSet(
    val id: String,
    val redTotals: List<Int>,
    val greenTotals: List<Int>,
    val blueTotals: List<Int>
) {
    companion object {
        fun fromInput(input: String): GameSet {
            val id = input.substringBefore(":").replace("Game ", "")
            val games = input.substringAfter(":").split(";")
            val redTotals = parseColorTotals(games, "red")
            val greenTotals = parseColorTotals(games, "green")
            val blueTotals = parseColorTotals(games, "blue")
            return GameSet(id, redTotals, greenTotals, blueTotals)
        }

        private fun parseColorTotals(games: List<String>, color: String): List<Int> {
            return games
                .flatMap { game -> game.split(",") }
                .filter { it.contains(color) }
                .map { it.replace(color, "").trim().toInt() }
        }
    }
}
fun main() {

    fun part1(input: List<String>, contents: Map<String, Int>): Int {
        val gameSets = input.map { GameSet.fromInput(it) }
        val possibleGameSets = gameSets.filter {
            it.redTotals.max() <= contents["red"]!! &&
            it.greenTotals.max() <= contents["green"]!! &&
            it.blueTotals.max() <= contents["blue"]!!
        }
        return possibleGameSets.sumOf { it.id.toInt() }
    }

    fun part2(input: List<String>): Int {
        val gameSets = input.map { GameSet.fromInput(it) }
        val maxPowers = gameSets.map { it.redTotals.max() * it.greenTotals.max() * it.blueTotals.max() }
        return maxPowers.sum()
    }

    val part1Contents = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )
    val testInput = readInput("Day02_test")
    check(part1(testInput, part1Contents) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    println("Part 1 - ${part1(input, part1Contents)}")
    println("Part 2 - ${part2(input)}")
}
