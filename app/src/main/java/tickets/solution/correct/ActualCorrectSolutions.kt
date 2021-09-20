package tickets.solution.correct

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import tickets.actual.Actual

class ActualCorrectSolutions(
    private val deferredFlow: Flow<Deferred<CorrectSolutions>>,
) : Actual<CorrectSolutions> {

    override suspend fun value(): CorrectSolutions = deferredFlow.first().await()
}