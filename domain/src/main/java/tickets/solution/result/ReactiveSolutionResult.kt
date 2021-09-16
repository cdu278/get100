package tickets.solution.result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import tickets.digits.TicketDigits
import tickets.digits.notEquivalentTo
import tickets.solution.chain.FullSolutionChain
import tickets.solution.signs.SolutionSigns

@Suppress("FunctionName")
fun ReactiveSolutionResult(
    ticketDigitsFlow: Flow<TicketDigits>,
    solutionUpdatesFlow: Flow<SolutionSigns>,
    scope: CoroutineScope,
): Flow<SolutionResult> {
    return ticketDigitsFlow
        .filter { it notEquivalentTo TicketDigits.Zeros }
        .combine(solutionUpdatesFlow) { ticketDigits, solutionSigns ->
            FullSolutionChain(ticketDigits, solutionSigns)
                .expression()
                .asSolutionResult()
        }
        .shareIn(scope, SharingStarted.Lazily, replay = 1)
}