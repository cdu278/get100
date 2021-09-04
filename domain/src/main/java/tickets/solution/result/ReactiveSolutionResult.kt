package tickets.solution.result

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import tickets.digits.TicketDigits
import tickets.persistent.Persistent
import tickets.solution.chain.FullSolutionChain
import tickets.solution.signs.SolutionSigns

@Suppress("FunctionName")
fun ReactiveSolutionResult(
    persistentTicketDigits: Persistent<TicketDigits>,
    solutionUpdates: Flow<SolutionSigns>,
    externalScope: CoroutineScope,
): Flow<Deferred<SolutionResult>> {
    return solutionUpdates
        .map { updatedSolutionSigns ->
            coroutineScope {
                async(Dispatchers.Default) {
                    FullSolutionChain(persistentTicketDigits.actual(), updatedSolutionSigns)
                        .expression()
                        .asSolutionResult()
                }
            }
        }
        .shareIn(externalScope, SharingStarted.Lazily, replay = 1)
}