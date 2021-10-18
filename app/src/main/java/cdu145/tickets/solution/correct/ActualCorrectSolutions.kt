package cdu145.tickets.solution.correct

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import cdu145.actual.Actual
import cdu145.tickets.solution.correct.CorrectSolutions

class ActualCorrectSolutions(
    private val deferredFlow: Flow<Deferred<CorrectSolutions>>,
) : Actual<CorrectSolutions> {

    override suspend fun value(): CorrectSolutions = deferredFlow.first().await()
}