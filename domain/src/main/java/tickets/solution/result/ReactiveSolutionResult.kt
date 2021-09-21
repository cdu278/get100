package tickets.solution.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import tickets.digits.TicketDigits
import tickets.digits.notEquivalentTo
import tickets.solution.signs.SolutionSigns

@Suppress("FunctionName")
fun ReactiveSolutionResult(
    ticketDigitsFlow: Flow<TicketDigits>,
    solutionUpdatesFlow: Flow<SolutionSigns>,
): Flow<SolutionResult> {
    return ticketDigitsFlow
        .filter { it notEquivalentTo TicketDigits.Zeros }
        .combine(solutionUpdatesFlow) { ticketDigits, solution ->
            resultOf(solution, ticketDigits)
        }
}