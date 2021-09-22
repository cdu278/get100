package tickets.solution.correct

import tickets.digits.digitsOf
import tickets.number.TicketNumber
import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.permutations.SolutionsSignsPermutations

typealias CorrectSolutions = Iterable<SolutionSigns>

fun TicketNumber.correctSolutions(
    signsPermutations: SolutionsSignsPermutations,
): CorrectSolutions {
    val digits = digitsOf(this)
    return signsPermutations
        .filter { it.correctlySolves(digits) }
        .toList()
}