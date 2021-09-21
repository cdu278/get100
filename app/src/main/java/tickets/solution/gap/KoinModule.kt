package tickets.solution.gap

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.actual.Actual
import tickets.actual.DataStoreMutable
import tickets.flow.ChannelFlow
import tickets.flow.DataStoreFlow
import tickets.hint.JustOpenedGapChannel
import tickets.solution.result.SolutionResultFlow
import tickets.solution.signs.SolutionSignsFlow

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