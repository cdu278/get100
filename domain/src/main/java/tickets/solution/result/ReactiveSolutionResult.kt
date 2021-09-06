package tickets.solution.result

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import tickets.actual.Actual
import tickets.digits.TicketDigits
import tickets.solution.chain.FullSolutionChain
import tickets.solution.signs.SolutionSigns

@Suppress("FunctionName")
fun ReactiveSolutionResult(
    actualTicketDigits: Actual<TicketDigits>,
    solutionUpdates: Flow<SolutionSigns>,
    externalScope: CoroutineScope,
): Flow<Deferred<SolutionResult>> {
    return solutionUpdates
        .map { updatedSolutionSigns ->
            coroutineScope {
                async(Dispatchers.Default) {
                    FullSolutionChain(actualTicketDigits.value(), updatedSolutionSigns)
                        .expression()
                        .asSolutionResult()
                }
            }
        }
        .shareIn(externalScope, SharingStarted.Lazily, replay = 1)
}