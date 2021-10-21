package cdu145.tickets.solution.signs

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.actual.DataStoreActual
import cdu145.flow.DataStoreFlow
import cdu145.tickets.solution.gaps.HighlightedGapPosition
import cdu145.tickets.solution.result.SolutionResultFlow

val Solution = StringQualifier("Solution")
val SolutionFlow = StringQualifier("SolutionFlow")

val SolutionSignsModule = module {
    factory<Actual<SolutionSigns>>(Solution) {
        DataStoreActual(get<Context>().solutionSignsDataStore)
    } bind Actual.Mutable::class
    factory(SolutionFlow) {
        DataStoreFlow(get<Context>().solutionSignsDataStore)
    }
    viewModel {
        SignButtonsViewModel(
            get(Solution),
            get(HighlightedGapPosition),
            get(SolutionResultFlow),
        )
    }
}