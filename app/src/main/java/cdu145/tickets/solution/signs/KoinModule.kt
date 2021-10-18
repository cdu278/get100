package cdu145.tickets.solution.signs

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.actual.DataStoreMutable
import cdu145.flow.DataStoreFlow
import cdu145.tickets.solution.gap.ActualHighlightedSignPosition
import cdu145.tickets.solution.result.SolutionResultFlow

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
        SignButtonsViewModel(
            get(ActualSolutionSigns),
            get(ActualHighlightedSignPosition),
            get(SolutionResultFlow),
        )
    }
}