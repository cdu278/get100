package tickets.solution.signs.permutations

import tickets.solution.signs.ArithmeticSign

internal class SignPermutationsOfLength(
    private val length: Int,
) : Sequence<SolutionSignsPermutation> {

    override fun iterator(): Iterator<SolutionSignsPermutation> {
        return if (length == 0) {
            OneEmptyPermutation
        } else {
            ArithmeticSign
                .values()
                .asSequence()
                .flatMap { headSign ->
                    PermutationsWithHeadSign(
                        SignPermutationsOfLength(length - 1),
                        headSign,
                    )
                }
        }.iterator()
    }
}