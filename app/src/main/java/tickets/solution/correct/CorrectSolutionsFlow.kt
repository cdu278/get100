package tickets.solution.correct

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tickets.number.TicketNumber
import tickets.solution.signs.permutations.SolutionsSignsPermutations

@Suppress("FunctionName")
fun DeferredCorrectSolutionsFlow(
    numberFlow: Flow<TicketNumber>,
    signsPermutations: Deferred<SolutionsSignsPermutations>,
    scope: CoroutineScope,
): Flow<Deferred<CorrectSolutions>> {
    return numberFlow.map {
        scope.async { it.correctSolutions(signsPermutations.await()) }
    }
}