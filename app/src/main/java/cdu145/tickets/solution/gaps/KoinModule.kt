package cdu145.tickets.solution.gaps

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.actual.DataStoreActual
import cdu145.flow.ChannelFlow
import cdu145.flow.DataStoreFlow
import cdu145.tickets.hint.JustRevealedGapChannel
import cdu145.tickets.solution.result.SolutionResultFlow
import cdu145.tickets.solution.signs.SolutionFlow

val HighlightedGapPosition = StringQualifier("HighlightedGapPosition")

val SolutionGapsModule = module {
    factory<Actual<Int>>(HighlightedGapPosition) {
        DataStoreActual(get<Context>().highlightedGapDataStore)
    } bind Actual.Mutable::class
    viewModel {
        SolutionGapsViewModel(
            get(HighlightedGapPosition),
            highlightedPositionFlow = DataStoreFlow(get<Context>().highlightedGapDataStore),
            get(SolutionFlow),
            get(SolutionResultFlow),
            justRevealedGapFlow = ChannelFlow(
                get(JustRevealedGapChannel),
            ),
        )
    }
}