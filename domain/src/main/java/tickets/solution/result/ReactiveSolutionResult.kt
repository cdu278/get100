package tickets.solution.result

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import tickets.digits.TicketDigits
import tickets.flowable.Flowable
import tickets.persistent.Persistent
import tickets.solution.chain.FullSolutionChain
import tickets.solution.signs.SolutionSigns

class ReactiveSolutionResult(
    private val persistentTicketDigits: Persistent<TicketDigits>,
    private val solutionUpdates: Flowable<SolutionSigns>,
    private val externalScope: CoroutineScope,
) : Flowable<Deferred<SolutionResult>> {

    override val flow: Flow<Deferred<SolutionResult>> by lazy(LazyThreadSafetyMode.NONE) {
        solutionUpdates.flow
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
}