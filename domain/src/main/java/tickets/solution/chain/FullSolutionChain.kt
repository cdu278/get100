package tickets.solution.chain

import tickets.digits.TicketDigits
import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.position.SignPosition

internal class FullSolutionChain(
    ticketDigits: TicketDigits,
    solutionSigns: SolutionSigns,
) : SolutionChain by RangeSolutionChain(
    from = SignPosition.FIRST,
    to = SignPosition.LAST,
    ticketDigits, solutionSigns,
)