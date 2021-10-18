package cdu145.tickets.solution

import cdu145.tickets.solution.signs.ListSolutionSigns
import cdu145.tickets.solution.signs.SolutionSign

fun allPossibleSolutions(): Iterable<Solution> {
    return signPermutations(length = 5).map(::ListSolutionSigns)
}

private fun signPermutations(length: Int): Iterable<List<SolutionSign>> {
    return if (length == 0) {
        return listOf(
            emptyList(),
        )
    } else {
        signPermutations(length - 1).flatMap { permutation ->
            SolutionSign.values().map { tailSign -> permutation + tailSign }
        }
    }
}