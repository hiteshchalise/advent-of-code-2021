fun main() {
    fun part1(input: List<Int>): Int {
        return List(input.size) { index ->
            input[(index - 1).coerceAtLeast(0)] < input[index]
        }.count { it }
    }

    fun part2(input: List<Int>): Int {
        return List(input.size - 3) { index ->
            (input[index] + input[index + 1] + input[index + 2]) < (input[index + 1] + input[index + 2] + input[index + 3])
        }.count { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map(String::toInt)
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01").map(String::toInt)
    println(part1(input))
    println(part2(input))
}
