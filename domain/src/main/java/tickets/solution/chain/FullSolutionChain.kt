package tickets.solution.chain

import tickets.digits.TicketDigits
import tickets.solution.range.SolutionRange
import tickets.solution.signs.SolutionSigns

@Suppress("FunctionName")
internal fun FullSolutionChain(
    ticketDigits: TicketDigits,
    solutionSigns: SolutionSigns,
): SolutionChain {
    return RangeSolutionChain(
        SolutionRange.Full,
        ticketDigits, solutionSigns,
    )
}