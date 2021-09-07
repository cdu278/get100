package tickets.solution.signs.permutations

internal object OneEmptyPermutation : Iterable<SolutionSignsPermutation> {

    override fun iterator(): Iterator<SolutionSignsPermutation> {
        return listOf(emptyList<Nothing>()).iterator()
    }
}