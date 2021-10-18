package cdu145.tickets.solution.correct

import cdu145.tickets.digits.digitsOf
import cdu145.tickets.number.TicketNumber
import cdu145.tickets.solution.signs.SolutionSigns
import cdu145.tickets.solution.signs.permutations.SolutionsSignsPermutations

typealias CorrectSolutions = Iterable<SolutionSigns>

fun TicketNumber.correctSolutions(
    signsPermutations: SolutionsSignsPermutations,
): CorrectSolutions {
    val digits = digitsOf(this)
    return signsPermutations
        .filter { it.correctlySolves(digits) }
        .toList()
}