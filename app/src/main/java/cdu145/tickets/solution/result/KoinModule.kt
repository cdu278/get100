package cdu145.tickets.solution.result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import cdu145.coroutine.scope.ApplicationCoroutineScope
import cdu145.tickets.digits.TicketDigitsFlow
import cdu145.tickets.solution.signs.SolutionSignsFlow

val SolutionResultFlow = StringQualifier("SolutionResultFlow")

val SolutionResultModule = module {
    single<Flow<SolutionResult>>(SolutionResultFlow) {
        ReactiveSolutionResult(
            ticketDigitsFlow = get(TicketDigitsFlow),
            solutionUpdatesFlow = get(SolutionSignsFlow),
        ).shareIn(
            scope = get<CoroutineScope>(ApplicationCoroutineScope) + Dispatchers.Default,
            SharingStarted.Lazily,
            replay = 1,
        )
    }
    viewModel { SolutionResultViewModel(get(SolutionResultFlow)) }
}