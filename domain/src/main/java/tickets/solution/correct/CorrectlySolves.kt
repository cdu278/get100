package tickets.solution.correct

import tickets.digits.TicketDigits
import tickets.solution.Solution
import tickets.solution.result.isHundred
import tickets.solution.result.resultOf

internal fun Solution.correctlySolves(digits: TicketDigits): Boolean {
    return resultOf(this, digits).isHundred
}