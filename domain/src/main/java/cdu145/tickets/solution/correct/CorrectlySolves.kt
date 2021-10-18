package cdu145.tickets.solution.correct

import cdu145.tickets.digits.TicketDigits
import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.result.isHundred
import cdu145.tickets.solution.result.resultOf

internal fun Solution.correctlySolves(digits: TicketDigits): Boolean {
    return resultOf(this, digits).isHundred
}