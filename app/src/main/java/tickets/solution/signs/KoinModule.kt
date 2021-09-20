package tickets.solution.signs

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.actual.Actual
import tickets.actual.DataStoreMutable
import tickets.flow.DataStoreFlow
import tickets.solution.gap.ActualHighlightedSignPosition
import tickets.solution.result.SolutionResultFlow

val ActualSolutionSigns = StringQualifier("ActualSolutionSigns")
val SolutionSignsFlow = StringQualifier("SolutionSignsFlow")

val SolutionSignsModule = module {
    factory<Actual<SolutionSigns>>(ActualSolutionSigns) {
        DataStoreMutable(get<Context>().solutionSignsDataStore)
    } bind Actual.Mutable::class
    factory(SolutionSignsFlow) {
        DataStoreFlow(get<Context>().solutionSignsDataStore)
    }
    viewModel {
        SignButtonsViewModelImpl(
            get(ActualSolutionSigns),
            get(ActualHighlightedSignPosition),
            get(SolutionResultFlow),
        )
    }
}