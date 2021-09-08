package tickets.solution.result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import tickets.digits.TicketDigits
import tickets.solution.chain.FullSolutionChain
import tickets.solution.signs.SolutionSigns

@Suppress("FunctionName")
fun ReactiveSolutionResult(
    ticketDigitsFlow: Flow<TicketDigits>,
    solutionUpdatesFlow: Flow<SolutionSigns>,
    scope: CoroutineScope,
): Flow<SolutionResult> {
    return ticketDigitsFlow
        .combine(solutionUpdatesFlow) { ticketDigits, solutionSigns ->
            FullSolutionChain(ticketDigits, solutionSigns)
                .expression()
                .asSolutionResult()
        }
        .shareIn(scope, SharingStarted.Lazily, replay = 1)
}