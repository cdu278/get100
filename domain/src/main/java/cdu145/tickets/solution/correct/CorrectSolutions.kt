package cdu145.tickets.solution.correct

import cdu145.tickets.digits.TicketDigits
import cdu145.tickets.digits.digitsOf
import cdu145.tickets.number.TicketNumber
import cdu145.tickets.solution.result.isHundred
import cdu145.tickets.solution.result.resultOf
import cdu145.tickets.solution.Solution

typealias CorrectSolutions = Iterable<Solution>

fun TicketNumber.correctSolutions(
    allPossibleSolutions: Iterable<Solution>,
): CorrectSolutions {
    val digits = digitsOf(this)
    return allPossibleSolutions
        .filter { it.correctlySolves(digits) }
        .toList()
}

private fun Solution.correctlySolves(digits: TicketDigits): Boolean {
    return resultOf(this, digits).isHundred
}