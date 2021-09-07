package tickets.solution.result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import tickets.coroutine.scope.ApplicationCoroutineScope
import tickets.digits.ActualTicketDigits
import tickets.solution.signs.SolutionSignsFlow

val SolutionResultFlow = StringQualifier("SolutionResultFlow")

val SolutionResultModule = module {
    single(SolutionResultFlow) {
        ReactiveSolutionResult(
            actualTicketDigits = get(ActualTicketDigits),
            solutionUpdates = get(SolutionSignsFlow),
            scope = get<CoroutineScope>(ApplicationCoroutineScope) + Dispatchers.Default,
        )
    }
    viewModel { SolutionResultViewModelImpl(get(SolutionResultFlow)) }
}