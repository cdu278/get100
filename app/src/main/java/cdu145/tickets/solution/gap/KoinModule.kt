package cdu145.tickets.solution.gap

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.actual.DataStoreMutable
import cdu145.flow.ChannelFlow
import cdu145.flow.DataStoreFlow
import cdu145.tickets.hint.JustOpenedGapChannel
import cdu145.tickets.solution.result.SolutionResultFlow
import cdu145.tickets.solution.signs.SolutionSignsFlow

val ActualHighlightedSignPosition = StringQualifier("ActualHighlightedSignPosition")

val SolutionGapsModule = module {
    factory<Actual<Int>>(ActualHighlightedSignPosition) {
        DataStoreMutable(get<Context>().highlightedGapDataStore)
    } bind Actual.Mutable::class
    viewModel {
        SolutionGapsViewModel(
            get(ActualHighlightedSignPosition),
            highlightedPositionFlow = DataStoreFlow(get<Context>().highlightedGapDataStore),
            get(SolutionSignsFlow),
            get(SolutionResultFlow),
            justOpenedPositionFlow = ChannelFlow(get(JustOpenedGapChannel)),
        )
    }
}