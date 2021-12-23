import java.nio.charset.UnsupportedCharsetException

fun main() {

    fun part1(input: List<String>): Int {
        var pair = Pair(0, 0)
        input.forEach { line ->
            val instructionPair = line.parseInstruction()
            pair = Pair(
                instructionPair.first + pair.first,
                instructionPair.second + pair.second
            )
        }
        return pair.first * pair.second
    }

    fun part2(input: List<String>): Int {
        var triple = Triple(0, 0, 0)
        input.forEach { line ->
            val instructionPair = line.parseInstruction()
            triple = Triple(
                instructionPair.first + triple.first,
                instructionPair.second + triple.second,
                triple.third + triple.second * instructionPair.first
            )
        }
        return triple.first * triple.third
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private fun String.parseInstruction(): Pair<Int, Int> {
    return when (this.substringBefore(" ")) {
        "forward" -> Pair(substringAfter(" ").toInt(), 0)
        "down" -> Pair(0, substringAfter(" ").toInt())
        "up" -> Pair(0, substringAfter(" ").toInt().times(-1))
        else -> throw UnsupportedCharsetException(substringBefore(" "))
    }
}
