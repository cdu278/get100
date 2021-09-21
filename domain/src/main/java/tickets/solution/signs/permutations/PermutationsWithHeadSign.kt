package tickets.solution.signs.permutations

import tickets.solution.signs.SolutionSign

internal class PermutationsWithHeadSign(
        private val permutations: Sequence<SolutionSignsPermutation>,
        private val headSign: SolutionSign,
) : Sequence<SolutionSignsPermutation> {

    private operator fun SolutionSign.plus(
        tail: SolutionSignsPermutation,
    ): SolutionSignsPermutation {
        return mutableListOf(this).apply { addAll(tail) }
    }

    override fun iterator(): Iterator<SolutionSignsPermutation> {
        return permutations.map { headSign + it }.iterator()
    }
}