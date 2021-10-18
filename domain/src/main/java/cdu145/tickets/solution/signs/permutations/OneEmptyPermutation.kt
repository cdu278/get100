package cdu145.tickets.solution.signs.permutations

internal object OneEmptyPermutation : Sequence<SolutionSignsPermutation> {

    override fun iterator(): Iterator<SolutionSignsPermutation> {
        return sequenceOf(emptyList<Nothing>()).iterator()
    }
}