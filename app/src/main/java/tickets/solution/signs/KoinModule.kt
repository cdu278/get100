package tickets.solution.signs

import android.content.Context
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.actual.Actual
import tickets.actual.DataStoreMutable
import tickets.flow.DataStoreFlow
import tickets.solution.gap.ActualHighlightedSignPosition
import tickets.solution.result.SolutionResultFlow

val SolutionSignsDataStore = StringQualifier("SolutionSignsDataStore")
val ActualSolutionSigns = StringQualifier("ActualSolutionSigns")
val SolutionSignsFlow = StringQualifier("SolutionSignsFlow")

val SolutionSignsModule = module {
    factory(SolutionSignsDataStore) { get<Context>().solutionSignsDataStore }
    factory<Actual<SolutionSigns>>(ActualSolutionSigns) {
        DataStoreMutable(get(SolutionSignsDataStore))
    } bind Actual.Mutable::class
    factory<Flow<SolutionSigns>>(SolutionSignsFlow) {
        DataStoreFlow(get(SolutionSignsDataStore))
    }
    viewModel {
        SignButtonsViewModelImpl(
            solutionSigns = get(ActualSolutionSigns),
            highlightedSignPosition = get(ActualHighlightedSignPosition),
            solutionResultFlow = get(SolutionResultFlow),
        )
    }
}