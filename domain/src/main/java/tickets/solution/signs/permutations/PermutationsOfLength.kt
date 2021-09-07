package tickets.solution.signs.permutations

import tickets.solution.signs.ArithmeticSign

internal class PermutationsOfLength(
    private val length: Int,
) : Iterable<SolutionSignsPermutation> {

    override fun iterator(): Iterator<SolutionSignsPermutation> {
        return if (length == 0) {
            OneEmptyPermutation
        } else {
            ArithmeticSign
                .values()
                .flatMap { headSign ->
                    PermutationsWithHeadSign(
                        PermutationsOfLength(length - 1),
                        headSign,
                    )
                }
        }.iterator()
    }
}