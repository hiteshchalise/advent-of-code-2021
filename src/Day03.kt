import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        val numOfOnes = List(input[0].length) { index ->
            input.count { line ->
                line[index].toString().toInt() == 1
            }
        }

        val gammaBits = numOfOnes.mapIndexed { index, value ->
            if (value > input.size - value) 1
            else 0
        }

        val gamma = gammaBits.reversed().reduceIndexed { index, acc, value ->
            acc + value * 2.toDouble().pow(index.toDouble()).toInt()
        }

        val epsilon = gammaBits.map { 2 - (it + 1) }.reversed().reduceIndexed { index, acc, value ->
            acc + value * 2.toDouble().pow(index.toDouble()).toInt()
        }

        return gamma * epsilon
    }

    fun reduceListOfString(
        input: List<String>,
        numberOfRows: Int,
        bitInput: Int
    ): List<String> {
        var index = 0
        var filteredList = input
        while (true) {
            filteredList = filterList(filteredList, index, bitInput)
            if (filteredList.size == 1) break
            index = (index + 1) % numberOfRows
        }
        return filteredList
    }

    fun part2(input: List<String>): Int {
        val numberOfRows = input[0].length

        val oxygenGeneratorList = reduceListOfString(input, numberOfRows, 1)
        val carbonDioxideScrubberList = reduceListOfString(input, numberOfRows, 0)

        val oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorList[0], 2)
        val carbonDioxideScrubberRating = Integer.parseInt(carbonDioxideScrubberList[0], 2)

        return oxygenGeneratorRating * carbonDioxideScrubberRating
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun filterList(input: List<String>, index: Int, bitInput: Int): List<String> {

    val totalNumberOfOne = input.count { line ->
        line[index].toString().toInt() == 1
    }
    val filteredList = if (totalNumberOfOne >= input.size - totalNumberOfOne) input.filter { line ->
        line[index].toString().toInt() == bitInput
    } else input.filter { line -> line[index].toString().toInt() == 2 - (bitInput + 1) }

    return filteredList
}
