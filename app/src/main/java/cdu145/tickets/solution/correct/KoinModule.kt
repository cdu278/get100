package cdu145.tickets.solution.correct

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.coroutine.scope.ApplicationCoroutineScope
import cdu145.tickets.number.TicketNumberFlow
import cdu145.tickets.solution.signs.permutations.CachedSignsPermutations

val CorrectSolutions = StringQualifier("CorrectSolutions")

val CorrectSolutionsModule = module {
    val flow = StringQualifier("correctSolutionsFlow")
    single<Flow<*>>(flow, createdAtStart = true) {
        val applicationScope = get<CoroutineScope>(ApplicationCoroutineScope)
        DeferredCorrectSolutionsFlow(
            get(TicketNumberFlow),
            get(CachedSignsPermutations),
            scope = applicationScope + Dispatchers.Default,
        ).shareIn(applicationScope, started = SharingStarted.Eagerly, replay = 1)
    }

    factory<Actual<CorrectSolutions>>(CorrectSolutions) { ActualCorrectSolutions(get(flow)) }
}