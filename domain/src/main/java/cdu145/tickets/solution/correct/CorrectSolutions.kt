package cdu145.tickets.solution.correct

import cdu145.tickets.digits.digitsOf
import cdu145.tickets.number.TicketNumber
import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.signs.SolutionSigns

typealias CorrectSolutions = Iterable<SolutionSigns>

fun TicketNumber.correctSolutions(
    allPossibleSolutions: Iterable<Solution>,
): CorrectSolutions {
    val digits = digitsOf(this)
    return allPossibleSolutions
        .filter { it.correctlySolves(digits) }
        .toList()
}