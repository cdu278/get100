package cdu145.tickets.solution.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import cdu145.tickets.digits.TicketDigits
import cdu145.tickets.digits.notEquivalentTo
import cdu145.tickets.solution.Solution

@Suppress("FunctionName")
fun SolutionResultFlow(
    ticketDigitsFlow: Flow<TicketDigits>,
    solutionUpdatesFlow: Flow<Solution>,
): Flow<SolutionResult> {
    return ticketDigitsFlow
        .filter { it notEquivalentTo TicketDigits.Zeros }
        .combine(solutionUpdatesFlow) { ticketDigits, solution ->
            resultOf(solution, ticketDigits)
        }
}